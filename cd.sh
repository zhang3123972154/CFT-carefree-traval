#!/bin/bash

#mvn clean package -Dmaven.test.skip=true
#exit_code=$?
#if [ $exit_code -ne 0 ]; then
#  echo "编译失败，请检查代码"
#  exit 1
#fi

#sshpass -p az6CjPklooasRsO5
scp target/travel-0.0.1-SNAPSHOT.jar root@43.139.8.195:/usr/local/xiaoyao
#sshpass -p az6CjPklooasRsO5
ssh root@43.139.8.195 "cd /usr/local/xiaoyao && docker-compose stop xiaoyao && docker-compose up -d --build xiaoyao"