<p align="center">
  <img align="center" src="https://raw.githubusercontent.com/bihell/blog-img/master/logo.png"/>
</p>

<p align="center">
    <a href="https://www.travis-ci.org/bihell/Dice"><img src="https://www.travis-ci.org/bihell/Dice.svg?branch=master"></a>
    <a href="https://codebeat.co/projects/github-com-bihell-dice-master"><img alt="codebeat badge" src="https://codebeat.co/badges/eb0bdd65-dad1-45e6-aea6-371c64d4d943" /></a>
    <a href="https://github.com/bihell/Dice/blob/master/LICENSE"><img alt="GitHub license" src="https://img.shields.io/github/license/bihell/Dice"></a>
    <a alt="spring boot"><img src="https://img.shields.io/badge/java-11-yellow.svg"/></a>
    <a alt="spring boot"><img src="https://img.shields.io/badge/spring%20boot-2.3.3.RELEASE-blue"/></a>
    <a alt="vue"><img src="https://img.shields.io/badge/vue-2.6.11-orange.svg"></a>
    <a alt="nuxt"><img src="https://img.shields.io/badge/nuxt-2.11.0-yellowgreen.svg"></a>
    <a alt="docker"><img src="https://img.shields.io/badge/docker-19.03.5--ce-ff69b4.svg"></a>
    <a alt="docker-compose"><img src="https://img.shields.io/badge/docker--compose-1.25.2-lightgrey.svg"></a>
</p>

* 基于`node` `java` `spring-boot` `vue` `nuxt` 开发的个人管理系统，目前已经实现「博客」、「权限管理」、「媒体库」、「代码段」、「定时任务」等功能
* 支持`docker`部署
* 功能精简但齐全，界面简洁却美观，满足个人日常使用要求
* 项目会持续更新，如果有不完善的地方，欢迎指出

> 演示站点：博客前端 [https://demo.bihell.com:8082](https://demo.bihell.com:8082) ， 管理前端 [https://demo.bihell.com:8082/admin](https://demo.bihell.com:8082/admin)

> 演示账号：demo 密码：123456

> QQ 群：787519476

## 开发&部署

部署视屏：[西瓜视频](https://www.ixigua.com/i6822907847605486083/) [哔哩哔哩](https://www.bilibili.com/video/BV1EZ4y1s7DF/) [YouTube](https://www.youtube.com/watch?v=yppSdtAk3u0)

## 一、项目结构及依赖

本项目开发环境为 macOS Catalina 10.15.2  部署环境为 Ubuntu 18.04.3 LTS，以下所有步骤均在这两个系统中运行。

::: vue
.
├── `dice-admin`          管理前端 -- 基于[Vue Element Admin](https://github.com/PanJiaChen/vue-element-admin)前端管理框架 主要依赖：[Vue.js](https://github.com/vuejs)、[Element-UI](https://github.com/ElemeFE/element)
├── `dice-auth`           网关 _(**开发环境**)_ -- 基于 [OpenResty](https://github.com/openresty/)和[Lua](https://www.lua.org/) 作为网关和鉴权处理。
├── `dice-docker`         docker部署脚本 _(**正式环境**)_  -- 主要依赖：[Docker](https://www.docker.com/)、[docker-compose](https://github.com/docker/compose)
│   ├── dice-admin
│   ├── dice-mysql
│   ├── dice-openresty
├── `dice-front`          博客前端 -- 修改自[fame-front](https://github.com/zzzzbw/Fame/tree/master/fame-front) 项目
├── `dice-server`         服务后端 -- 基于[Spring Boot](https://spring.io/projects/spring-boot) 全家桶 主要依赖：[MySQL](https://www.mysql.com/)、[Redis](https://redis.io/)
└── `docker-compose.yml`    [docker-compose](https://github.com/docker/compose)镜像配置文件 _(**正式环境**)_
:::

## 二、正式部署

正式部署为 Docker 方式，最好能对 Docker 有所了解，可参考我的文章[Docker 实践](/tools/liqi/docker.html)

部署视频：[西瓜视频](https://www.ixigua.com/i6822907847605486083/) [哔哩哔哩](https://www.bilibili.com/video/BV1EZ4y1s7DF/) [YouTube](https://www.youtube.com/watch?v=yppSdtAk3u0)

### 2.1 安装依赖

```Bash
# 安装CA证书
sudo apt-get update
sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    software-properties-common

# 安装 `GPG`密钥
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

# 向`source.list`中添加Docker软件源
# 以下命令会添加稳定版本的 Docker CE APT 镜像源，如果需要测试或每日构建版本的 Docker CE 请将 stable 改为 test 或者 nightly。
sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"

# 安装 Docker CE
apt-get update
apt-get install docker-ce

# 安装 docker-compose
curl -L https://github.com/docker/compose/releases/download/v1.25.2/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose

# 启动 Docker CE
systemctl enable docker
systemctl start docker
```

### 2.2 开始部署

1. 克隆项目到本地

```Bash
apt-get install git
git clone https://github.com/bihell/Dice.git
```

2. 安装`npm`、`node.js`以及`maven`
   
```Bash
apt-get install npm 
# node.js 源参考 https://github.com/nodesource/distributions/blob/master/README.md
curl -sL https://deb.nodesource.com/setup_13.x | sudo -E bash -
sudo apt-get install -y nodejs
apt-get install maven 
```

3. 准备https证书

项目默认开启HTTPS加密访问，请将证书放在`./dice-docker/dice-openresty/data`目录下，如果没有证书，可以参考我的[文档](/tools/liqi/openresty.html#https)和视频教程申请免费的证书。[西瓜视频](https://www.ixigua.com/i6820037277289284107/) [哔哩哔哩](https://www.bilibili.com/video/BV1c7411D7yh) [YouTube](https://www.youtube.com/watch?v=wImwCkxU_jQ)

4. 注释docker部分配置

注释掉`dice-docker/dice-openresty/openresty-Dockerfile`文件中以下两行的配置

```Bash
COPY ./dice-docker/dice-openresty/bigdata /usr/local/openresty/nginx/html/bigdata
COPY ./dice-docker/dice-openresty/conf.d/bigdata.conf /etc/nginx/conf.d/bigdata.conf
```

5. 部署项目

```Bash
cd dice
./depoly-prod.sh
```

6. 访问地址

`http://xx.xxx.xx.xx/` 为博客前端首页

`http://xx.xxx.xx.xx/admin` 为管理后台首页

**后台默认的账号**

* 管理员：dice 密码：123456
* 普通用户：demo 密码:123456

::: warning 注意
注意项目第一次部署会下载很多依赖包，花费时间较长，请耐心等待。
:::

### 2.3 数据备份恢复

#### 备份数据库

```Bash
docker exec dice-mysql /usr/bin/mysqldump -u root --password=root dice > backup.sql
```

#### 恢复数据库
```Bash
cat backup.sql | docker exec -i dice-mysql /usr/bin/mysql -u root --password=root dice
```

## 三、参与开发

::: danger 注意
请确保系统中已经安装`docker`、`docker-componse`、`nodejs`、`npm`、`Java9`、`Redis`、`MySQL`等必须的依赖。
:::

### 3.1 安装依赖（MacOS）

#### Java

```Bash
brew tap homebrew/cask-versions
brew cask install java11
```

#### Docker

直接Docker官方下载[Docker Desktop](https://www.docker.com/products/docker-desktop)

#### MySQL

```Bash
brew install mysql
mysql.server start
```

#### Redis

```Bash
brew install redis
brew services start redis
```

#### OpenResty

```Bash
brew tap openresty/brew
brew install openresty
```

### 3.2 愉快的开发吧

克隆项目到本地

   ```
   git clone https://github.com/bihell/Dice.git
   ```

#### 3.2.1 `dice-server` Java后台

项目使用lombok插件，如果要在ide中调试要有lombok插件

##### 3.2.1.1 修改相应配置

进入服务端文件夹`cd dice-server`,修改spring-boot配置文件`vi src/main/resources/application-dev.properties`

```
spring:
    datasource:
      driverClassName: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/dice?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
      username: root
      password: root
```

将数据库的用户名和密码修改成对应你数据库的用户名密码

##### 3.2.1.2 项目启动

多种启动方式：

1. 直接运行main方法

直接在IDE中运行`bootstrap`模块的`DiceApplication`启动类的main方法就可以看到项目启动了。

2. 打包启动

```Bash
mvn clean package -P docker,release
java -jar bootstrap/target/bootstrap-2.0.jar
```

#### 3.2.2 `dice-front` 博客前端

进入前端文件夹`cd dice-front`，安装依赖并启动服务：

```Bash
npm install
npm run dev
```

#### 3.2.3 `dice-admin` 管理前端

进入后端文件夹`cd dice-admin`，安装依赖和启动服务：

```Bash
npm install
npm run dev
```

#### 3.2.4 `dice-auth` openresty (仅开发环境使用)

##### 3.2.4.1 安装 openresty

可参考我的[OpenResty 安装文档](/tools/liqi/openresty.html)，安装环境为 MacOS。
    
::: danger 注意
1）项目中lua需要用到Http请求，因此你需要把对应的lua模块添加进来。可以从[lua-resty-http](https://github.com/ledgetech/lua-resty-http/tree/master/lib/resty) 下载`http.lua`和`http_headers.lua`。并放到`/usr/local/Cellar/openresty/1.15.8.3_1/lualib/resty`目录中。

2）如果你的MySQL版本较新，可能会碰到无法连接的错误。可以重新设置一下账号权限，方式如下：
```SQL
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password'
flush privileges;
```
:::

##### 3.2.4.2 启动openresty

进入dice-auth文件夹启动：
    
```Bash
cd Dice/dice-auth
./start.sh
```

#### 3.2.5 访问前端

`http://localhost:81` 为博客前端首页

`http://localhost:81/admin` 为管理后台首页

## 部分界面

![博客前端](https://raw.githubusercontent.com/bihell/blog-img/master/dice1.png)
![](https://raw.githubusercontent.com/bihell/blog-img/master/dice4.png)
![](https://raw.githubusercontent.com/bihell/blog-img/master/dice5.png)
![](https://raw.githubusercontent.com/bihell/blog-img/master/dice7.png)
![代码段](https://raw.githubusercontent.com/bihell/blog-img/master/snippet.png)
![媒体库](https://raw.githubusercontent.com/bihell/blog-img/master/dice-media.png)
![权限](https://raw.githubusercontent.com/bihell/blog-img/master/auth_api.png)
![权限](https://raw.githubusercontent.com/bihell/blog-img/master/auth_menu.png)
![权限](https://raw.githubusercontent.com/bihell/blog-img/master/auth_role.png)
![权限](https://raw.githubusercontent.com/bihell/blog-img/master/auth_user.png)
![tool_task](https://raw.githubusercontent.com/bihell/blog-img/master/tool_task.png)