<p align="center">
  <img align="center" src="https://raw.githubusercontent.com/bihell/blog-img/master/logo.png"/>
</p>
<p align="center">
    <a href="https://codebeat.co/projects/github-com-bihell-dice-master"><img alt="codebeat badge" src="https://codebeat.co/badges/eb0bdd65-dad1-45e6-aea6-371c64d4d943" /></a>
    <a href="https://github.com/bihell/Dice/blob/master/LICENSE"><img alt="GitHub license" src="https://img.shields.io/github/license/bihell/Dice"></a>
    <a alt="java"><img src="https://img.shields.io/badge/java-17-yellow.svg"/></a>
    <a alt="spring boot"><img src="https://img.shields.io/badge/spring%20boot-3-blue"/></a>
    <a alt="vue2"><img src="https://img.shields.io/badge/vue-2-ff69b4.svg"></a>
    <a alt="vue3"><img src="https://img.shields.io/badge/vue-3-orange.svg"></a>
    <a alt="nuxt"><img src="https://img.shields.io/badge/nuxt-2.11.0-yellowgreen.svg"></a>
</p>




* 基于`java17` `spring-boot3` `spring-security ` `node` `vue2` `vue3` `nuxt2`  开发的个人管理系统: 目前有博客、导航、权限管理三大模块.其中博客除了管理前端外还有SEO前端
* 功能精简但齐全，界面简洁却美观，满足个人日常使用要求
* 项目会持续更新，如果有不完善的地方，欢迎指出

> 演示站点： 

> [博客前端](https://dice.bigdata.icu)  [管理前端](https://dice.bigdata.icu/admin/) 

> 演示账号：

> 普通用户：demo 123456

> 管理员：dice 123456

> 数据库每日零点重制
>
> 微信群：加微信：digital-review，请注明来意

## 一、部分界面

<table>
    <tr>
        <td>
            <img src="https://raw.githubusercontent.com/bihell/blog-img/master/dice1.png"/>
        </td>
        <td>
            <img src="img/post_list.png"/>
        </td>
    </tr>
    <tr>
        <td>
            <img src="img/post_edit.png"/>
        </td>
        <td>
            <img src="img/blog_setting.png"/>
        </td>
    </tr>
    <tr>
        <td>
            <img src="img/tag_list.png"/>
        </td>
        <td>
            <img src="img/nav_type.png"/>
        </td>
    </tr>
    <tr>
        <td>
            <img src="img/nav_list.png"/>
        </td>
        <td>
            <img src="img/system_permission.png"/>
        </td>
    </tr>
</table>

## 二、项目结构

```
Dice
├── dice-admin        --  后台管理前端，基于vue-vben-admin项目开发。
├── dice-front        --  博客SEO前端，基于vue-next项目开发
└── dice-server       --  后端服务，Spring Boot全家桶
    ├── dice-blog          --  博客模块
    ├── dice-bootstrap     --  启动模块
    ├── dice-framework     --  框架核心模块
    ├── dice-nav           --  导航模块
    └── dice-system        --  系统模块
```

## 三、参与开发

目前后端刚切`SpringBoot3`和`SpringSecurity`，一些细节还未调整，欢迎各位参与进来。

> 请确保系统中已经安装`docker`、`docker-componse`、`nodejs`、`npm`、`Java17`、`Redis`、`MySQL`等必须的依赖。

### 3.1 安装依赖（MacOS）

#### Java

[Oracle官方](https://www.oracle.com/hk/java/technologies/downloads/#java17)直接下载安装

> 注：spring-boot 3开始支持Java17以上版本

#### MySQL

```Bash
brew install mysql
mysql.server start
```

> 如果你的MySQL版本较新，可能会碰到无法连接的错误。可以重新设置一下账号权限，方式如下：

> ```
> ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password'
> flush privileges;
> ```

#### Redis

```Bash
brew install redis
brew services start redis
```

#### Pnpm

版本\>=`8.1.0`

```Bash
brew install pnpm
```

#### Node

版本\>=`16.15.1`

```Bash
brew install node
```

#### Git

版本\>= 2.x

```Bash
brew install git
```

### 3.2 启动运行

克隆项目到本地

   ```
   git clone https://github.com/bihell/Dice.git
   ```

#### 3.2.1 `dice-server` Java后台

项目使用lombok插件，如果要在ide中调试要有lombok插件
数据库初始语句:`dice-server/misc/init.sql`

##### 修改相应配置

进入服务端文件夹`cd dice-server`,修改spring-boot配置文件`vi dice-server/dice-bootstrap/src/main/resources/config/application-dev.yml`

```
spring:
    datasource:
      driverClassName: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/dice?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
      username: root
      password: root
```

将数据库的用户名和密码修改成对应你数据库的用户名密码

##### 项目启动

多种启动方式：

1. 直接运行main方法

直接在IDE中运行`dice-bootstrap`模块的`DiceApplication`启动类的main方法就可以看到项目启动了。

2. 打包启动

```Bash
mvn clean package -Dmaven.test.skip=true -Pdev
java -jar dice-bootstrap/target/dice.jar
```

> 注意：Maven版本要>=3.2.5

#### 3.2.2 `dice-front` 博客前端

进入前端文件夹`cd dice-front`，安装依赖并启动服务：

```Bash
npm install
npm run dev
```

#### 3.2.3 `dice-admin` 管理前端

进入后端文件夹`cd dice-admin`，安装依赖和启动服务：

```Bash
npm install -g pnpm
pnpm install
pnpm dev
```

#### 3.2.4 访问

`http://localhost:3000/` 为博客前端首页

`http://localhost:3100/admin/` 为管理后台首页

