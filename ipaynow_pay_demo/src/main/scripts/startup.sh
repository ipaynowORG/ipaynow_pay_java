#!/bin/bash
source version.sh

if find -name process.pid | grep "process.pid";
then
  echo "PROCESS is running..."
  exit
fi

if [ -z $PROCESS_HOME ]; then
        PROCESS_HOME=../;
fi
$JAVA_HOME/bin/java -Dfile.encoding=UTF-8 -jar $PROCESS_HOME/lib/$demo&>$PROCESS_HOME/out.log &


if [ ! -z "process.pid" ]; then
  echo $! > process.pid
fi


