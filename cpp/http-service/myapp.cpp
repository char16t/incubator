#include "crow.h"

class CustomLogger : public crow::ILogHandler {
 public:
  CustomLogger() {}
  void log(std::string message, crow::LogLevel /*level*/) {
    // "message" doesn't contain the timestamp and loglevel
    // prefix the default logger does and it doesn't end
    // in a newline.
    std::cout << message << std::endl;
  }
};

class A
{
public:
    int field1;
    double field2;
    std::string field3;

    A(int f1, double f2, std::string f3) : field1(f1), field2(f2), field3(f3) {}
    
    crow::json::wvalue dump()
    {
        crow::json::wvalue obj;
        obj["field1"] = field1;
        obj["field2"] = field2;
        obj["field3"] = field3;
        return obj;
    }
};

int main()
{
    CustomLogger logger;
    crow::logger::setHandler(&logger);

    crow::SimpleApp app;
    app.loglevel(crow::LogLevel::Warning);

    CROW_ROUTE(app, "/").methods(crow::HTTPMethod::GET)([](){
        return "Hello GET world";
    });

    CROW_ROUTE(app, "/add/<string>/<string>").methods(crow::HTTPMethod::POST)
    ([](const crow::request& req, std::string a, std::string b){
        const char* param_name = req.url_params.get("param_name");
        return A(12, 14.5, a+b+std::string(param_name)).dump();
    });

    CROW_ROUTE(app, "/json")
    ([]{
        crow::json::wvalue x({{"message", "Hello, World!"}});
        x["message2"] = "Hello, World.. Again!";
        return x;
    });

    app.port(9090).multithreaded().run();
}
