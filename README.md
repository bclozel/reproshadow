# Gradle shadow plugin IDEA repro project

This project reproduces a compilation error shown in IntelliJ IDEA which
doesn't happen when building the project with Gradle.

## Project configuration

This project is made of 3 modules:
* "library", with a `org.library.GreetingService` interface
* "core", with a class `org.example.core.MyGreetingService implements org.library.GreetingService`
* "module", which tries to apply an instance of `MyGreetingService` to a method accepting `org.example.shadow.GreetingService`

This project uses the Gradle shadow plugin for repackaging the "library" module
in the "core" module and relocating ``"org.library"` to `"org.example.shadow"`.

The "module" subproject imports the "shadow" configuration of the "core" module, so at that point
everything is properly relocated to `"org.example.shadow"` by the shadow plugin.

## How to reproduce this issue

Import this project as a Gradle project in IDEA and build it.

```
IntelliJ IDEA 2017.2.1 EAP
Build #IU-172.3544.6, built on July 19, 2017
JRE: 1.8.0_152-release-915-b6 x86_64
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
Mac OS X 10.12.6
```

## Issue

IntelliJ IDEA seems to not fully take into account the shadow configuration and still links to the "core"
sources as if the package relocation didn't happen.

This leads to the following compilation error:

```
Error:(13, 17) java: cannot access org.library.GreetingService
  class file for org.library.GreetingService not found
```

In that case, `MyGreetingService` still implements `org.library.GreetingService` which doesn't
exist in the "module" subproject.

Also, an intent asks about casting the given instance to `org.example.shadow.GreetingService`
(see `shadow-compilationerror.png` at the root of this repository).

