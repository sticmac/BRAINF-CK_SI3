#!/bin/bash
# build script

# Clean build dir and build jar archive
mvn clean package

# Builds the JavaDoc
mvn javadoc:javadoc

# Build the summary website
#mvn site
