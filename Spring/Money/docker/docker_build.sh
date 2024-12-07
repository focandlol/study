#!/bin/sh

# Setting Versions
VERSION='0.0.1'

cd ..
./gradlew clean build -x test
SHELL_PATH=`pwd -P`
echo $SHELL_PATH

echo 'API Image Build...'
cd $SHELL_PATH/product && docker build -t product:$VERSION .
echo 'API Image Build... Done'

echo 'CONSUMER Image Build...'
cd $SHELL_PATH/user && docker build -t user:$VERSION .
echo 'CONSUMER Image Build... Done'

echo 'CONSUMER Image Build...'
cd $SHELL_PATH/swagger && docker build -t swagger:$VERSION .
echo 'CONSUMER Image Build... Done'

echo 'nginx Image Build...'
cd $SHELL_PATH/nginx  && docker build -t nginx:$VERSION .
echo 'nginx Image Build... Done'