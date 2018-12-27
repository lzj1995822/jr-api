#!/usr/bin/env bash
export BUILD_ID=dontKillMe
NAME=eureka-server
JAR_NAME=${NAME}.jar
cd `dirname $0`

#检查程序是否在运行
is_exist(){
  pid=`ps -ef | grep ${JAR_NAME} | grep -v grep | awk '{print $2}'`
  #如果不存在返回1，存在返回0
  if [[ -z "${pid}" ]]; then
    return 1
  else
    return 0
  fi
}

#复制jar包
copy(){
  if [[ -f target/${JAR_NAME} ]]
  then
    \cp -rf target/${JAR_NAME} ${JAR_NAME}
  fi
}

#启动方法
start(){
  is_exist
  if [[ $? -eq 0 ]]; then
    echo "${JAR_NAME} is already running. pid=${pid}"
  else
    nohup java -jar ${JAR_NAME} > ${NAME}.out 2>&1 &
  fi
}

#停止方法
stop(){
  is_exist
  if [[ $? -eq "0" ]]; then
    kill -9 ${pid}
  else
    echo "${JAR_NAME} is not running"
  fi
}

echo "${NAME} begin......"
stop
sleep 2
copy
start
sleep 2
echo "${NAME} end......"
