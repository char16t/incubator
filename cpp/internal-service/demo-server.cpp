#include "demo.capnp.h"
#include <kj/debug.h>
#include <capnp/ez-rpc.h>
#include <capnp/message.h>

#include <string>

class DemoImpl final: public Demo::Server {
public: 
    kj::Promise<void> act(ActContext context) override {
        auto params = context.getParams();
        auto version = context.getResults().getResponse().getVersion();
        version.setMajor(1);
        version.setMinor(0);
        version.setPatch(0);
        return kj::READY_NOW;
    }
};

int main(int argc, const char* argv[]) {
    const std::string address = "0.0.0.0:8082";
    capnp::EzRpcServer server(kj::heap<DemoImpl>(), address);
    auto& waitScope = server.getWaitScope();
    kj::NEVER_DONE.wait(waitScope);
}
