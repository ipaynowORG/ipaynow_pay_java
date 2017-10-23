#!/bin/bash
source version.sh
JAVA_HOME=/apps/usr/java/jdk1.7.0_79

if find -name process.pid | grep "process.pid";
then
  echo "PROCESS is running..."
  exit
fi

if [ -z $PROCESS_HOME ]; then
        PROCESS_HOME=../;
fi

for i in $PROCESS_HOME/lib/*
do
        CLASSPATH=$i:$CLASSPATH
done

$JAVA_HOME/bin/java -classpath $CLASSPATH com.aspire.nm.component.miniServerDemo.clientTest.ServerTest_expensive 10 999999999 50 "http://192.168.6.67:8081/s?u=d&s=ASERVICE&ts=13401190417&p=1&ex=001&c=r1r2cc3&mid=12345" Sucess&>$PROCESS_HOME/out.log &


if [ ! -z "process.pid" ]; then
  echo $! > process.pid
fi


