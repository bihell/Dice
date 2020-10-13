#!/bin/bash

#====================================================
#	System Request:Ubuntu 18.04+
#	Author:	chenchao
#	Dscription: Dice 部署脚本
#	Version: 2.0
#	email:tpxcer@outlook.com
#	Official document: https://bigdata.bihell.com/project/dice.html
#====================================================

#fonts color
Green="\033[32m"
Red="\033[31m"
Yellow="\033[33m"
GreenBG="\033[42;37m"
RedBG="\033[41;37m"
Font="\033[0m"

#notification information
Info="${Green}[信息]${Font}"
OK="${Green}[OK]${Font}"
Error="${Red}[错误]${Font}"

judge(){
    if [[ $? -eq 0 ]];then
        echo -e "${OK} ${GreenBG} $1 完成 ${Font}"
        sleep 1
    else
        echo -e "${Error} ${RedBG} $1 失败${Font}"
        exit 1
    fi
}

working_dir=`pwd`

# 安装 Dice-Admin 依赖
cd $working_dir/dice-admin/ && npm install
judge "安装 Dice-Admin 项目依赖"

# 编译 Dice-Admin 项目
npm run build:prod
judge "编译 Dice-Admin 项目"

cd $working_dir/dice-server/ && mvn clean package -P docker,release
judge "编译 Dice-Server 打包"

# docker 部署
cd $working_dir
docker-compose -f dice-prod.yml build
judge "生成容器"
docker-compose -f dice-prod.yml down
judge "停止容器"
docker-compose -f dice-prod.yml up -d
judge "启动容器"
