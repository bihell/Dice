#!/bin/bash

#====================================================
#	System Request:Ubuntu 18.04+
#	Author:	chenchao
#	Dscription: Dice 部署脚本
#	Version: 1.0
#	email:tpxcer@outlook.com
#	Official document: https://bihell.gitee.io/big-data/project/dice.html
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

# 安装 Dice-Admin 依赖
cd ./dice-admin/ && npm install && npm rebuild node-sass
judge "安装 Dice-Admin 项目依赖"

# 编译 Dice-Admin 项目
npm run build:prod
judge "编译 Dice-Admin 项目"
cd -

# docker 部署
docker-compose build
judge "生成容器"
docker-compose down
judge "停止容器"
docker-compose up -d
judge "启动容器"
