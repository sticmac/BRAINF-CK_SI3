#!/bin/bash
# build script

javac -d bin/ -cp bin/ `find src/brainfuck -name \*.java`
cd bin/
mkdir META-INF
echo "Main-Class: brainfuck.Main" > META-INF/MANIFEST.MF
jar cmvf META-INF/MANIFEST.MF ../Bfck.jar brainfuck/*
cd ..
