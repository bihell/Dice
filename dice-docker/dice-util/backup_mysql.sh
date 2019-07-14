#!/bin/bash
date=`date '+%Y-%m-%dT%H%M%S'`
container=`docker ps | grep mysql | awk '{print $1}'`
mkdir -p /opt/bak
docker exec $container /usr/bin/mysqldump -u root --password=root dice > /opt/bak/${date}backup.sql
ret=$?
if [ $ret -ne 0 ];then
   echo '备份失败'
   exit $ret;
else
   echo '备份完成'
fi
