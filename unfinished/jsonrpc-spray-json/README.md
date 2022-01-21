Representation classes and [spary-json](https://github.com/spray/spray-json) formats
for JSON RPC 2.0

Original specification text available by url [https://www.jsonrpc.org/specification]().
You also can find specification text in [SPECIFICATION.md]() file.

### Setup

Add `jsonrpc` as a dependency in `build.sbt` file:

```scala
libraryDependencies += "com.manenkov" %% "jsonrpc" % "0.1-alpha"
```

Before you can do this, you must build and install the library locally.

### Build from source code

Build:

```
sbt assembly
```

Test:

```
sbt test
```

Install:

```
sbt publishLocal
```

### Usage

See tests

### Unlicense

Licensed under [Public domain](https://unlicense.org/). See UNLICENSE file
