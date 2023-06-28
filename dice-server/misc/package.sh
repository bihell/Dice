#!/bin/sh

# Into the root directory of the project
cd "$(dirname "$0")/.."

echo "Building the application..."
mvn clean package -Dmaven.test.skip=true -Pdocker
echo "Application built successfully"
