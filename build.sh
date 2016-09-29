#!/bin/bash
# build script

javac -d bin/ -cp bin/ `find src/brainfuck -name \*.java`
cd bin/
# See https://docs.oracle.com/javase/tutorial/deployment/jar/appman.html
jar vcfe ../Bfck.jar brainfuck.Main brainfuck/
cd ..
