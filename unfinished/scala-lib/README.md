***Usage***

Add to your `build.sbt`:

```
libraryDependencies += "com.democompany" %% "core" % "0.1"

resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("char16t", "maven-test")
```

***Build from source code***

Build:
```bash
sbt +compile
```

Test:
```bash
sbt +test
```

Install:
```bash
sbt +publishLocal
```

Publish:
```bash
sbt +publish
```

***Publish settings***

Create file `~/.bintray/.credentials` with content:

```
realm = Bintray API Realm
host = api.bintray.com
user = <bintray username>
password = <bintray api key>
```