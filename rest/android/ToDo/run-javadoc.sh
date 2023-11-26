#!/usr/bin/env bash

SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
DESTINATION="${SCRIPT_DIR}/target/doc"
rm -rf $DESTINATION
mkdir -p "$DESTINATION"

echo "Destination is $DESTINATION"
pushd app
pwd
javadoc -d $DESTINATION --ignore-source-errors -sourcepath src/main/java -subpackages at
popd
