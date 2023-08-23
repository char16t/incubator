cmake_minimum_required(VERSION 3.1)
project("Demo" CXX)
find_package(CapnProto CONFIG REQUIRED)
set(CMAKE_EXE_LINKER_FLAGS "-static")

capnp_generate_cpp(demoSources demoHeaders demo.capnp)
add_executable(demo-server demo-server.cpp ${demoSources})
target_link_libraries(demo-server PRIVATE CapnProto::capnp-rpc)
target_include_directories(demo-server PRIVATE ${CMAKE_CURRENT_BINARY_DIR})
