@0xe70cd31b0fa07e2a;

struct Version {
    major @0 :UInt64 = 0;
    minor @1 :UInt64 = 1;
    patch @2 :UInt64 = 0;
}

interface Demo {
    act @0 (request :Request) -> (response :Response);
    struct Request {
        version @0 :Version;
    }
    struct Response {
        version @0 :Version;
    }
}
