  #!/bin/bash

GRAALVMDIR=/opt/jvm/graalvm-ee
PROJECT_DIR="`dirname \"$0\"`"
LIBPATH=$PROJECT_DIR/clibraries/linux-amd64

cd $PROJECT_DIR
mvn clean package
rm jxattr-*
echo "LIB PATH:" $LIBPATH
$GRAALVMDIR/bin/native-image \
 --verbose \
 --no-server \
 --static \
 -H:+JNI \
 -H:JNIConfigurationFiles=./graalvm/jni.json \
 -Djava.library.path=$LIBPATH \
 -Dio.netty.native.workdir=$LIBPATH \
 -H:CLibraryPath=$LIBPATH \
 -H:+ReportUnsupportedElementsAtRuntime \
 -Dfile.encoding=UTF-8 \
 -jar target/jxattr-1.0.0-SNAPSHOT.jar  

