#!/bin/bash
# build script

# Clean build dir and build jar archive
mvn clean package verify

# Builds the JavaDoc
mvn javadoc:javadoc

# Build the summary website
#mvn site
