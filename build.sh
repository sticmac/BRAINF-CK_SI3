#!/bin/bash
# build script

# Build classes
javac -Xlint:unchecked -d bin/ -cp bin/ `find src/brainfuck -name \*.java`

# Builds the jar archive
# See https://docs.oracle.com/javase/tutorial/deployment/jar/appman.html
cd bin
jar vcfe ../Bfck.jar brainfuck.Main brainfuck/
cd ..

# Builds the JavaDoc
javadoc -d doc -sourcepath src -subpackages brainfuck -private
