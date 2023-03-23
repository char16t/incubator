#include "demo.capnp.h"
#include <capnp/ez-rpc.h>
#include <kj/debug.h>

#include <string>
#include <iostream>

int main(int argc, const char* argv[]) {
    const std::string address = "0.0.0.0:8082";
    capnp::EzRpcClient client(address);
    Demo::Client calculator = client.getMain<Demo>();
    auto& waitScope = client.getWaitScope();

    {
        auto req = calculator.actRequest();
        auto version = req.getRequest().getVersion();
        version.setMajor(1);
        version.setMinor(0);
        version.setPatch(0);

        auto actPromise = req.send();
        auto res = actPromise.wait(waitScope);
        KJ_ASSERT(res.getResponse().getVersion().getMajor() == 1);
        KJ_ASSERT(res.getResponse().getVersion().getMinor() == 0);
        KJ_ASSERT(res.getResponse().getVersion().getPatch() == 0);
    }
}
