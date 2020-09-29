DROP DATABASE IF EXISTS dice;
CREATE DATABASE dice CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE dice;

CREATE TABLE sys_option
(
    id           INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    option_key   VARCHAR(100)    NOT NULL UNIQUE,
    option_value VARCHAR(1023)   NOT NULL,
    created      TIMESTAMP       NOT NULL DEFAULT current_timestamp,
    modified     TIMESTAMP       NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;

create table user
(
    id          int auto_increment
        primary key,
    username    varchar(45)                         not null,
    password    varchar(64)                         not null,
    email       varchar(45)                         null,
    screen_name varchar(45)                         null,
    created     timestamp default CURRENT_TIMESTAMP not null,
    logged      timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    salt        varchar(32)                         null comment '盐值',
    constraint username
        unique (username)
)
    charset = utf8mb4;

create table article
(
    id            int auto_increment
        primary key,
    title         varchar(255)         not null comment '内容标题',
    content       mediumtext           null comment '内容',
    hits          int        default 0 not null comment '点击量',
    tags          varchar(255)         null comment '标签列表',
    category      varchar(255)         null comment '文章分类',
    status        smallint             null comment '内容状态',
    type          varchar(32)          null comment '内容类别',
    allow_comment tinyint(1) default 1 not null comment '是否允许评论',
    comment_count int        default 0 not null comment '评论数量',
    priority      int        default 0 null comment '文章优先级',
    create_time   timestamp            not null comment '创建时间',
    update_time   timestamp            not null on update CURRENT_TIMESTAMP comment '更新时间',
    creator       int                  null comment '创建人',
    modifier      int                  null comment '修改人',
    deleted       int        default 0 null comment '逻辑删除标识(0.未删除,1.已删除)'
)
charset = UTF8MB4;

CREATE TABLE comment
(
    id         INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    article_id INT             NOT NULL,
    p_id       INT,
    content    TEXT            NOT NULL,
    name       VARCHAR(255),
    email      VARCHAR(255),
    website    VARCHAR(255),
    agree      INT             NOT NULL DEFAULT 0,
    disagree   INT             NOT NULL DEFAULT 0,
    ip         VARCHAR(255),
    agent      VARCHAR(255),
    status     INT                      DEFAULT 0 NOT NULL,
    created    TIMESTAMP       NOT NULL DEFAULT current_timestamp
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;

CREATE TABLE meta
(
    id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255)    NOT NULL,
    type VARCHAR(45)     NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;

CREATE TABLE middle
(
    id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    a_id INT             NOT NULL,
    m_id INT             NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;

CREATE TABLE log
(
    id      INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    action  VARCHAR(255),
    data    TEXT,
    message VARCHAR(255),
    type    VARCHAR(255),
    ip      VARCHAR(255),
    user_id INT,
    created TIMESTAMP       NOT NULL DEFAULT current_timestamp
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;

create table snippet_file
(
    id         int auto_increment
        primary key,
    title      varchar(255)                        not null,
    content    text                                null,
    language   varchar(20)                         null,
    tabs       int                                 null,
    snippet_id int                                 null,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    updated_at timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
);

create index index_snippet_files_on_snippet_id
    on snippet_file (snippet_id);


CREATE TABLE media
(
    id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    created TIMESTAMP NOT NULL DEFAULT current_timestamp,
    modified TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    name VARCHAR(255) NOT NULL,
    url VARCHAR(1023) NOT NULL,
    thumb_url VARCHAR(1023),
    suffix VARCHAR(255) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;

CREATE TABLE `auth_group` (
      `group_id` int(11) NOT NULL AUTO_INCREMENT,
      `group_name` varchar(255) NOT NULL DEFAULT '' COMMENT '项目名称',
      `group_url` varchar(255) NOT NULL DEFAULT '',
      `project_type` varchar(16) NOT NULL DEFAULT '' COMMENT '系统类型',
      `order` int(11) DEFAULT NULL,
      `is_display` int(11) DEFAULT NULL COMMENT '显示状态（0不显示，1显示）',
      `style` varchar(255) DEFAULT NULL,
      deleted INT(1) DEFAULT 0 COMMENT '逻辑删除标识(0.未删除,1.已删除)',
      `creator` int(11) DEFAULT NULL,
      `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `modifier` int(11) DEFAULT NULL,
      `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近修改时间',
      PRIMARY KEY (`group_id`),
      KEY `IDX_PROJECT_TYPE` (`project_type`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `auth_classes` (
    `classes_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
    `classes_name` varchar(255) NOT NULL DEFAULT '' COMMENT '分类名',
    `classes_url` varchar(255) NOT NULL,
    `group_id` int(11) NOT NULL COMMENT '所属项目',
    `order` int(11) DEFAULT NULL,
    `is_display` int(11) DEFAULT NULL COMMENT '显示状态（0不显示，1显示）',
    `style` varchar(255) DEFAULT NULL,
    deleted INT(1) DEFAULT 0 COMMENT '逻辑删除标识(0.未删除,1.已删除)',
    `creator` int(11) DEFAULT NULL,
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `modifier` int(11) DEFAULT NULL,
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`classes_id`),
    KEY `IDX_GROUP_ID` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `auth_item` (
     `item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限项ID',
     `item_name` varchar(255) NOT NULL COMMENT '权限项名称',
     `item_code` varchar(255) NOT NULL,
     `classes_id` int(11) NOT NULL,
     `order` int(11) DEFAULT NULL,
     `style` varchar(255) DEFAULT NULL,
     `auto_flag` tinyint(4) DEFAULT NULL,
     `outer_url` varchar(1000) DEFAULT NULL,
     deleted INT(1) DEFAULT 0 COMMENT '逻辑删除标识(0.未删除,1.已删除)',
     `creator` int(11) DEFAULT NULL,
     `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
     `modifier` int(11) DEFAULT NULL,
     `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (`item_id`),
     KEY `IDX_ITEM_CODE` (`item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

create table dim_project
(
    id         bigint auto_increment
        primary key,
    type       varchar(16)  not null comment '产品类型',
    type_name  varchar(255) null comment '产品名',
    is_display tinyint(1)   null comment '是否在顶部显示',
    style      text         null,
    domain     varchar(255) null comment '系统域名'
)
    charset = UTF8MB4;
create index IDX_TYPE
    on dim_project (type);

create table auth_api
(
    api_id       int auto_increment
        primary key,
    api_type     varchar(255) default ''                null comment 'API类型',
    api_path     varchar(255) default ''                not null,
    project_type varchar(64)  default ''                not null comment '类目权限类型',
    deleted INT(1) DEFAULT 0 COMMENT '逻辑删除标识(0.未删除,1.已删除)',
    creator      int                                    null,
    create_time  timestamp    default CURRENT_TIMESTAMP null,
    modifier     int                                    null,
    update_time  timestamp    default CURRENT_TIMESTAMP null
)
    charset = UTF8MB4;
create index IDX_PROJECT_TYPE
    on auth_api (project_type);


create table auth_rel_item_api
(
    id          int auto_increment
        primary key,
    item_id     int                                 not null comment '操作项id',
    api_id      int(4)                              not null comment 'api id',
    deleted INT(1) DEFAULT 0 COMMENT '逻辑删除标识(0.未删除,1.已删除)',
    creator     int                                 null comment '创建人',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    modifier    int                                 null comment '修改人',
    update_time timestamp default CURRENT_TIMESTAMP null comment '修改时间'
)
    charset = UTF8MB4;
create index IDX_ITEM_API
    on auth_rel_item_api (item_id, api_id);

create table auth_rel_role_api
(
    id          bigint(11) auto_increment
        primary key,
    role_id     int(4)                              not null comment '角色id',
    api_id      int(4)                              not null comment '权限项id',
    deleted INT(1) DEFAULT 0 COMMENT '逻辑删除标识(0.未删除,1.已删除)',
    creator     int                                 null comment '创建人',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    modifier    int                                 null comment '修改人',
    update_time timestamp default CURRENT_TIMESTAMP null comment '修改时间'
)
    charset = UTF8MB4;
create index IDX_ROLE_API
    on auth_rel_role_api(role_id, api_id);

create table auth_role
(
    role_id      int auto_increment comment '角色ID'
        primary key,
    role_name    varchar(255) default ''                not null comment '角色名',
    user_type    int(4)                                 not null comment '用户类型',
    role_type    int(4)                                 not null comment '角色类型 1:管理员 2:用户',
    project_type varchar(255)                           null comment '系统类型',
    description  varchar(255)                           null comment '描述',
    deleted INT(1) DEFAULT 0 COMMENT '逻辑删除标识(0.未删除,1.已删除)',
    creator      int                                    null,
    create_time  timestamp    default CURRENT_TIMESTAMP null,
    modifier     int                                    null,
    update_time  timestamp    default CURRENT_TIMESTAMP null
)
    charset = UTF8MB4;

create table auth_rel_role_user
(
    id          int auto_increment
        primary key,
    user_id     int                                 not null comment '用户id',
    role_id     int(4)                              not null comment '角色id',
    deleted INT(1) DEFAULT 0 COMMENT '逻辑删除标识(0.未删除,1.已删除)',
    creator     int                                 null comment '创建人',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    modifier    int                                 null comment '修改人',
    update_time timestamp default CURRENT_TIMESTAMP null comment '修改时间'
)
    charset = utf8mb4;

create index IDX_USER_ROLE
    on auth_rel_role_user (user_id, role_id);

create table auth_content
(
    id            bigint(11) auto_increment
        primary key,
    project_type  varchar(255) default ''                not null,
    content_type  varchar(255) default ''                not null,
    content_name  varchar(255) default ''                not null,
    content_value varchar(255)                           null,
    deleted INT(1) DEFAULT 0 COMMENT '逻辑删除标识(0.未删除,1.已删除)',
    creator       int                                    null,
    create_time   timestamp    default CURRENT_TIMESTAMP null,
    modifier      int                                    null,
    update_time   timestamp    default CURRENT_TIMESTAMP null
)
    charset = UTF8MB4;

create index IDX_CONTENT_TYPE
    on auth_content (content_type);

create index IDX_PROJECT_TYPE
    on auth_content (project_type);


create table auth_rel_role_content
(
    id          bigint(11) auto_increment
        primary key,
    role_id     int(4)                              not null comment '角色id',
    content_id  int(4)                              not null comment '权限项id',
    deleted INT(1) DEFAULT 0 COMMENT '逻辑删除标识(0.未删除,1.已删除)',
    creator     int                                 null comment '创建人',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    modifier    int                                 null comment '修改人',
    update_time timestamp default CURRENT_TIMESTAMP null comment '修改时间'
)
    charset = UTF8MB4;
create index IDX_ROLE_CONTENT
    on auth_rel_role_content (role_id, content_id);

create table auth_rel_role_item
(
    id          bigint(11) auto_increment
        primary key,
    role_id     int(4)                              not null comment '角色id',
    item_id     int(4)                              not null comment '权限项id',
    deleted INT(1) DEFAULT 0 COMMENT '逻辑删除标识(0.未删除,1.已删除)',
    creator     int                                 null comment '创建人',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    modifier    int                                 null comment '修改人',
    update_time timestamp default CURRENT_TIMESTAMP null comment '修改时间'
)
    charset = UTF8MB4;
create index IDX_ROLE_ITEM
    on auth_rel_role_item (role_id, item_id);

CREATE TABLE `task` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `concurrent` tinyint DEFAULT NULL COMMENT '是否允许并发',
    `cron` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '定时规则',
    `data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '执行参数',
    `exec_at` datetime DEFAULT NULL COMMENT '执行时间',
    `exec_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '执行结果',
    `job_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '执行类',
    `job_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务组名',
    `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务名',
    `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务说明',
    `status` int DEFAULT NULL COMMENT '状态（0无效1有效）',
    `creator` int DEFAULT NULL COMMENT '创建人',
    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间/注册时间',
    `modifier` int DEFAULT NULL COMMENT '最后更新人',
    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` int DEFAULT '0' COMMENT '逻辑删除标识(0.未删除,1.已删除)',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='定时任务';

CREATE TABLE `task_log` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `exec_at` datetime DEFAULT NULL COMMENT '执行时间',
    `exec_success` int DEFAULT NULL COMMENT '执行结果（成功:1、失败:0)',
    `id_task` bigint DEFAULT NULL,
    `job_exception` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '抛出异常',
    `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务名',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='定时任务日志';

create table sys_operation_log
(
    id                bigint auto_increment comment '主键'
        primary key,
    request_id        varchar(32)                        null comment '请求ID',
    user_id           bigint                             null comment '用户ID',
    user_name         varchar(32)                        null comment '用户名称',
    name              varchar(200)                       null comment '日志名称',
    ip                varchar(15)                        null comment 'IP',
    area              varchar(45)                        null comment '区域',
    operator          varchar(6)                         null comment '运营商',
    path              varchar(500)                       null comment '全路径',
    module            varchar(100)                       null comment '模块名称',
    class_name        varchar(100)                       null comment '类名',
    method_name       varchar(100)                       null comment '方法名称',
    request_method    varchar(10)                        null comment '请求方式，GET/POST',
    content_type      varchar(100)                       null comment '内容类型',
    request_body      tinyint(1)                         null comment '是否是JSON请求映射参数',
    param             text                               null comment '请求参数',
    token             varchar(32)                        null comment 'tokenMd5值',
    type              int                                null comment '0:其它,1:新增,2:修改,3:删除,4:详情查询,5:所有列表,6:分页列表,7:其它查询,8:上传文件',
    success           tinyint(1)                         null comment '0:失败,1:成功',
    code              int                                null comment '响应结果状态码',
    message           varchar(100)                       null comment '响应结果消息',
    exception_name    varchar(200)                       null comment '异常类名称',
    exception_message varchar(300)                       null comment '异常信息',
    browser_name      varchar(100)                       null comment '浏览器名称',
    browser_version   varchar(100)                       null comment '浏览器版本',
    engine_name       varchar(100)                       null comment '浏览器引擎名称',
    engine_version    varchar(100)                       null comment '浏览器引擎版本',
    os_name           varchar(100)                       null comment '系统名称',
    platform_name     varchar(100)                       null comment '平台名称',
    mobile            tinyint(1)                         null comment '是否是手机,0:否,1:是',
    device_name       varchar(100)                       null comment '移动端设备名称',
    device_model      varchar(100)                       null comment '移动端设备型号',
    remark            varchar(200)                       null comment '备注',
    create_time       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time       datetime                           null comment '修改时间',
    deleted           int      default 0                 null comment '逻辑删除，0：未删除，1：已删除'
)
    comment '系统操作日志';

create table ip_address
(
    id           bigint auto_increment
        primary key,
    ip_start     varchar(15) not null,
    ip_end       varchar(15) not null,
    area         varchar(45) null comment '区域',
    operator     varchar(6)  null comment '运营商',
    ip_start_num bigint      not null,
    ip_end_num   bigint      not null
)
    comment 'IP地址';

create index ip_address_ip_end_num_index
    on ip_address (ip_end_num);

create index ip_address_ip_start_num_index
    on ip_address (ip_start_num);

INSERT INTO dice.user (id, username, password, email, screen_name, created, logged, salt) VALUES (1, 'dice', 'dab6458f688b27c04d86b6f99757e2ce70d533f0092a85e4fbd9668261b1092b', 'tpxcer@outlook.com', 'admin', '2019-05-16 02:24:35', '2020-09-29 15:28:07', '66666');
INSERT INTO dice.user (id, username, password, email, screen_name, created, logged, salt) VALUES (2, 'demo', 'dab6458f688b27c04d86b6f99757e2ce70d533f0092a85e4fbd9668261b1092b', 'demo@bihell.com', 'demo用户', '2019-12-27 15:34:01', '2020-09-29 15:28:07', '66666');

INSERT INTO dice.task (id, concurrent, cron, data, exec_at, exec_result, job_class, job_group, name, note, status, creator, create_time, modifier, update_time, deleted) VALUES (1, 0, '0/6 * * * * ?', '{
"appname": "dice",
"version":2
}', '2020-01-27 14:54:24', '执行成功', 'com.bihell.dice.service.task.job.HelloJob', 'default', '测试任务1', '测试任务1', 0, 1, '2018-12-28 09:54:00', -1, '2019-03-27 11:47:11', 0);

INSERT INTO article (title, create_time, update_time, content, creator, hits, tags, category, status, type)
VALUES ('Hello world', now(), now(), '
欢迎使用[Dice](https://github.com/bihell/Dice)! 这是你的第一篇博客。快点来写点什么吧

```java
public static void main(String[] args){
    System.out.println("Hello world");
}
```

> 想要了解更多详细信息，可以查看[文档](https://github.com/bihell/Dice/blob/master/README.md)。', 1, 0, 'First', 'New', '0', 'post');

INSERT INTO article (title, create_time, update_time, content, creator, tags, category, status, type)
VALUES ('关于', now(), now(), '# About me
### Hello word
这是关于我的页面

* [Github](https://github.com/bihell)
* [哔哩哔哩](https://space.bilibili.com/88900889/video)

### 也可以设置别的页面
* 比如友链页面', 1, NULL, NULL, '0', 'page');

INSERT INTO comment (article_id, content, name, email, website, agree, disagree, ip, agent)
VALUES ('1', '## 测试评论
这是我的网址[Dice](http://bihell.com)', 'tpxcer', 'tpxcer@outlook.com', 'https://bihell.com', '1', '0', '0.0.0.1', '');

INSERT INTO meta (name, type)
VALUES ('First', 'tag');
INSERT INTO meta (name, type)
VALUES ('New', 'category');

INSERT INTO middle (a_id, m_id)
VALUES (1, 1);
INSERT INTO middle (a_id, m_id)
VALUES (1, 2);

INSERT INTO sys_option (option_key, option_value)
VALUES ('dice_init', 'true');

INSERT INTO dice.dim_project (id, type, type_name, is_display, style, domain) VALUES (1, 'dice', 'Dice', 1, null, null);

INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (39, '', '/v1/api/admin/article', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (40, '', '/v1/api/admin/article/count', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (41, '', '/v1/api/admin/auth/api/add', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (42, '', '/v1/api/admin/auth/api/get', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (43, '', '/v1/api/admin/auth/api/list', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (44, '', '/v1/api/admin/auth/api/update', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (45, '', '/v1/api/admin/auth/classes/add', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (46, '', '/v1/api/admin/auth/classes/get', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (47, '', '/v1/api/admin/auth/classes/update', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (48, '', '/v1/api/admin/auth/content/add', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (49, '', '/v1/api/admin/auth/content/get', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (50, '', '/v1/api/admin/auth/content/list', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (51, '', '/v1/api/admin/auth/content/update', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (52, '', '/v1/api/admin/auth/group/add', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (53, '', '/v1/api/admin/auth/group/get', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (54, '', '/v1/api/admin/auth/group/list', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (55, '', '/v1/api/admin/auth/group/update', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (56, '', '/v1/api/admin/auth/item/add', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (57, '', '/v1/api/admin/auth/item/assign/api', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (58, '', '/v1/api/admin/auth/item/get', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (59, '', '/v1/api/admin/auth/item/list', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (60, '', '/v1/api/admin/auth/item/update', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (61, '', '/v1/api/admin/auth/login', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (62, '', '/v1/api/admin/auth/logout', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (63, '', '/v1/api/admin/auth/project/project_list', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (64, '', '/v1/api/admin/auth/reset/password', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (65, '', '/v1/api/admin/auth/reset/user', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (66, '', '/v1/api/admin/auth/role/add', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (67, '', '/v1/api/admin/auth/role/assign/api', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (68, '', '/v1/api/admin/auth/role/assign/content', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (69, '', '/v1/api/admin/auth/role/assign/item', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (70, '', '/v1/api/admin/auth/role/assign/user', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (71, '', '/v1/api/admin/auth/role/get', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (72, '', '/v1/api/admin/auth/role/list', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (73, '', '/v1/api/admin/auth/role/update', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (74, '', '/v1/api/admin/auth/user/add', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (75, '', '/v1/api/admin/auth/user/assign/role', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (76, '', '/v1/api/admin/auth/user/get', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (77, '', '/v1/api/admin/auth/user/list', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (78, '', '/v1/api/admin/auth/user/list/all', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (79, '', '/v1/api/admin/auth/user/update', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (80, '', '/v1/api/admin/auth/user_info', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (81, '', '/v1/api/admin/comment', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (82, '', '/v1/api/admin/comment/count', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (83, '', '/v1/api/admin/media', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (84, '', '/v1/api/admin/media/upload', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (85, '', '/v1/api/admin/meta', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (86, '', '/v1/api/admin/option/all', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (87, '', '/v1/api/admin/option/save', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (88, '', '/v1/api/admin/page', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (89, '', '/v1/api/admin/snippet', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (90, '', '/v1/api/admin/snippet/snippet_title', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (91, '', '/v1/api/archive', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (92, '', '/v1/api/article', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (93, '', '/v1/api/category', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (94, '', '/v1/api/comment', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (95, '', '/v1/api/comment/assess', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (96, '', '/v1/api/option', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (97, '', '/v1/api/page', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (98, '', '/v1/api/tag', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (1, '文章列表', '/blog/article', 1, 0, 1, '', 0, null, '2019-12-24 15:13:20', null, '2019-12-24 15:13:20');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (2, '评论列表', '/blog/comment', 1, 0, 1, '', 0, null, '2019-12-24 15:48:57', null, '2019-12-24 15:48:57');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (3, '标签分类', '/blog/tag', 1, 0, 1, '', 0, null, '2019-12-24 15:49:06', null, '2019-12-24 15:49:06');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (4, '页面列表', '/blog/page', 1, 0, 1, '', 0, null, '2019-12-24 15:49:15', null, '2019-12-24 15:49:15');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (5, '网站设置', '/blog/setting', 1, 0, 1, '', 0, null, '2019-12-24 15:49:24', null, '2019-12-24 15:49:24');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (6, '代码段', '/snippet/index', 2, 0, 1, '', 0, null, '2019-12-24 15:52:39', null, '2019-12-24 15:52:39');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (7, '媒体库', '/media-library/index', 2, 0, 1, '', 0, null, '2019-12-24 15:54:28', null, '2019-12-24 15:54:28');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (8, '用户列表', '/auth/user/list', 4, 0, 1, '', 0, null, '2019-12-24 15:55:44', null, '2019-12-24 15:55:44');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (9, '菜单管理', '/auth/menu/list', 4, 0, 1, '', 0, null, '2019-12-24 15:56:23', null, '2019-12-24 15:56:23');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (11, 'API管理', '', 4, 0, 1, '', 0, null, '2020-01-06 18:22:48', null, '2020-01-06 18:22:48');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (12, '角色管理', '', 4, 0, 1, '', 0, null, '2020-01-07 21:21:23', null, '2020-01-07 21:21:23');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (13, '内容管理', '', 4, 0, 1, '', 0, null, '2020-01-07 22:10:50', null, '2020-01-07 22:10:50');INSERT INTO dice.auth_group (group_id, group_name, group_url, project_type, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (1, '博客', '', 'dice', 0, 1, '', 0, null, '2019-12-23 17:56:50', null, '2019-12-23 17:56:50');
INSERT INTO dice.auth_group (group_id, group_name, group_url, project_type, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (2, '工具', '', 'dice', 0, 1, '', 0, null, '2019-12-23 18:08:01', null, '2019-12-23 18:08:01');
INSERT INTO dice.auth_group (group_id, group_name, group_url, project_type, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (4, '权限', '', 'dice', 0, 1, '', 0, null, '2019-12-23 18:25:39', null, '2019-12-23 18:25:39');INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (1, '新增文章', '/blog/article/new', 1, 0, null, null, '', 0, null, '2019-12-24 16:23:19', null, '2019-12-24 16:23:19');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (2, '编辑文章', '/blog/article/edit', 1, 0, null, null, '', 0, null, '2019-12-24 17:31:55', null, '2019-12-24 17:31:55');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (3, '用户列表', '/auth/user/list', 8, 0, null, null, '', 0, null, '2019-12-31 13:40:22', null, '2019-12-31 13:40:22');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (4, '分配角色', '/auth/user/assign/role', 8, 0, null, null, '', 0, null, '2019-12-31 13:41:49', null, '2019-12-31 13:41:49');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (5, '用户编辑', '/auth/user/update', 8, 0, null, null, '', 0, null, '2019-12-31 13:43:09', null, '2019-12-31 13:43:09');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (6, '添加用户', '/auth/user/add', 8, 0, null, null, '', 0, null, '2019-12-31 13:49:13', null, '2019-12-31 13:49:13');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (7, '添加页面分组', '/auth/menu/addPageGroup', 9, 0, null, null, '', 0, null, '2020-01-06 15:15:10', null, '2020-01-06 15:15:10');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (8, '添加页面', '/auth/menu/addPage', 9, 0, null, null, '', 0, null, '2020-01-06 15:16:30', null, '2020-01-06 15:16:30');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (9, '添加功能', '/auth/menu/addItem', 9, 0, null, null, '', 0, null, '2020-01-06 15:17:09', null, '2020-01-06 15:17:09');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (10, '编辑', '/auth/menu/edit', 9, 0, null, null, '', 0, null, '2020-01-06 15:19:45', null, '2020-01-06 15:19:45');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (11, '删除文章', '/blog/article/delete', 1, 0, null, null, '', 0, null, '2020-01-06 15:26:21', null, '2020-01-06 15:26:21');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (12, '删除评论', '/blog/comment/delete', 2, 0, null, null, '', 0, null, '2020-01-06 15:29:51', null, '2020-01-06 15:29:51');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (13, '删除标签', '/blog/tag/delete', 3, 0, null, null, '', 0, null, '2020-01-06 15:31:29', null, '2020-01-06 15:31:29');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (14, '保存标签', '/blog/tag/save', 3, 0, null, null, '', 0, null, '2020-01-06 15:31:59', null, '2020-01-06 15:31:59');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (15, '新建页面', '/blog/page/new', 4, 0, null, null, '', 0, null, '2020-01-06 15:34:11', null, '2020-01-06 15:34:11');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (16, '编辑页面', '/blog/page/update', 4, 0, null, null, '', 0, null, '2020-01-06 15:34:29', null, '2020-01-06 15:34:29');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (17, '删除页面', '/blog/page/delete', 4, 0, null, null, '', 0, null, '2020-01-06 15:34:47', null, '2020-01-06 15:34:47');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (18, '保存设置', '/blog/setting/save', 5, 0, null, null, '', 0, null, '2020-01-06 15:43:35', null, '2020-01-06 15:43:35');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (19, '邮箱设置', '/blog/setting/mailInfo', 5, 0, null, null, '', 0, null, '2020-01-06 15:46:42', null, '2020-01-06 15:46:42');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (20, '上传', '/tool/media/upload', 7, 0, null, null, '', 0, null, '2020-01-06 15:52:47', null, '2020-01-06 15:52:47');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (21, '菜单列表', '/auth/menu/list', 9, 0, null, null, '', 0, null, '2020-01-06 17:58:38', null, '2020-01-06 17:58:38');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (22, '添加API', '/auth/api/add', 11, 0, null, null, '', 0, null, '2020-01-06 18:23:36', null, '2020-01-06 18:23:36');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (23, 'API 列表', '/auth/api/list', 11, 0, null, null, '', 0, null, '2020-01-06 18:56:58', null, '2020-01-06 18:56:58');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (24, '列表', '/auth/role/list', 12, 0, null, null, '', 0, null, '2020-01-07 21:28:46', null, '2020-01-07 21:28:46');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (25, '新增角色', '/auth/role/list/add', 12, 0, null, null, '', 0, null, '2020-01-07 21:35:06', null, '2020-01-07 21:35:06');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (26, '角色编辑', '/auth/role/update', 12, 0, null, null, '', 0, null, '2020-01-07 21:35:43', null, '2020-01-07 21:35:43');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (27, '分配API', '/auth/role/assign_api', 12, 0, null, null, '', 0, null, '2020-01-07 21:36:36', null, '2020-01-07 21:36:36');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (28, '分配内容', '/auth/role/assign_content', 12, 0, null, null, '', 0, null, '2020-01-07 21:37:15', null, '2020-01-07 21:37:15');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (29, '分配用户', '/auth/role/assign_user', 12, 0, null, null, '', 0, null, '2020-01-07 21:40:38', null, '2020-01-07 21:40:38');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (30, '分配操作项', '/auth/role/assign_item', 12, 0, null, null, '', 0, null, '2020-01-07 21:41:23', null, '2020-01-07 21:41:23');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (31, '查看操作项', '/auth/role/show_item', 12, 0, null, null, '', 0, null, '2020-01-07 21:42:30', null, '2020-01-07 21:42:30');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (32, '新增内容', '/auth/conent/add', 13, 0, null, null, '', 0, null, '2020-01-07 22:11:01', null, '2020-01-07 22:11:01');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (33, '更新内容', '/auth/content/update', 13, 0, null, null, '', 0, null, '2020-01-07 22:14:42', null, '2020-01-07 22:14:42');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (34, '编辑 API', '/auth/api/update', 11, 0, null, null, '', 0, null, '2020-01-07 22:17:48', null, '2020-01-07 22:17:48');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (35, '媒体列表', '/tool/media/list', 7, 0, null, null, '', 0, null, '2020-01-07 22:19:56', null, '2020-01-07 22:19:56');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (36, '删除媒体', '/tool/media/delete', 7, 0, null, null, '', 0, null, '2020-01-07 22:30:23', null, '2020-01-07 22:30:23');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (37, '所有权限', '/tool/snippet', 6, 0, null, null, '', 0, null, '2020-01-07 22:37:20', null, '2020-01-07 22:37:20');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (38, '列表', '/blog/page/list', 4, 0, null, null, '', 0, null, '2020-01-07 22:46:12', null, '2020-01-07 22:46:12');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (39, '列表', '/blog/comment/list', 2, 0, null, null, '', 0, null, '2020-01-07 22:48:43', null, '2020-01-07 22:48:43');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (40, '列表', '/blog/article/list', 1, 0, null, null, '', 0, null, '2020-01-07 22:50:44', null, '2020-01-07 22:50:44');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (41, '列表', '/blog/setting/list', 5, 0, null, null, '', 0, null, '2020-01-09 18:41:22', null, '2020-01-09 18:41:22');INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (2, 1, 1, 0, null, '2019-12-26 16:58:28', null, '2019-12-26 16:58:28');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (4, 4, 5, 0, null, '2019-12-31 13:56:51', null, '2019-12-31 13:56:51');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (5, 4, 32, 0, null, '2019-12-31 13:56:51', null, '2019-12-31 13:56:51');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (6, 4, 33, 0, null, '2019-12-31 13:56:51', null, '2019-12-31 13:56:51');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (7, 4, 27, 0, null, '2019-12-31 13:56:51', null, '2019-12-31 13:56:51');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (8, 5, 3, 0, null, '2019-12-31 13:57:28', null, '2019-12-31 13:57:28');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (9, 5, 2, 0, null, '2019-12-31 13:57:28', null, '2019-12-31 13:57:28');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (10, 6, 4, 0, null, '2019-12-31 13:58:32', null, '2019-12-31 13:58:32');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (12, 10, 34, 0, null, '2020-01-06 18:04:27', null, '2020-01-06 18:04:27');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (13, 10, 11, 0, null, '2020-01-06 18:04:28', null, '2020-01-06 18:04:28');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (15, 22, 63, 0, null, '2020-01-06 18:25:20', null, '2020-01-06 18:25:20');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (17, 21, 63, 0, null, '2020-01-06 18:50:39', null, '2020-01-06 18:50:39');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (18, 21, 59, 0, null, '2020-01-06 18:50:39', null, '2020-01-06 18:50:39');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (19, 21, 54, 0, null, '2020-01-06 18:50:39', null, '2020-01-06 18:50:39');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (20, 3, 72, 0, null, '2020-01-06 18:53:32', null, '2020-01-06 18:53:32');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (21, 3, 77, 0, null, '2020-01-06 18:53:32', null, '2020-01-06 18:53:32');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (22, 23, 43, 0, null, '2020-01-06 18:58:49', null, '2020-01-06 18:58:49');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (26, 35, 83, 0, null, '2020-01-07 22:20:18', null, '2020-01-07 22:20:18');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (29, 37, 90, 0, null, '2020-01-07 22:41:16', null, '2020-01-07 22:41:16');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (30, 37, 89, 0, null, '2020-01-07 22:41:16', null, '2020-01-07 22:41:16');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (31, 37, 85, 0, null, '2020-01-07 22:41:16', null, '2020-01-07 22:41:16');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (32, 38, 88, 0, null, '2020-01-07 22:46:32', null, '2020-01-07 22:46:32');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (33, 39, 81, 0, null, '2020-01-07 22:49:13', null, '2020-01-07 22:49:13');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (35, 24, 78, 0, null, '2020-01-27 18:05:52', null, '2020-01-27 18:05:52');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (36, 24, 50, 0, null, '2020-01-27 18:05:52', null, '2020-01-27 18:05:52');
INSERT INTO dice.auth_rel_item_api (id, item_id, api_id, deleted, creator, create_time, modifier, update_time) VALUES (37, 40, 39, 0, null, '2020-01-27 18:06:38', null, '2020-01-27 18:06:38');INSERT INTO dice.auth_rel_role_item (id, role_id, item_id, deleted, creator, create_time, modifier, update_time) VALUES (293, 2, 40, 0, null, '2020-01-27 18:49:55', null, '2020-01-27 18:49:55');
INSERT INTO dice.auth_rel_role_item (id, role_id, item_id, deleted, creator, create_time, modifier, update_time) VALUES (294, 2, 38, 0, null, '2020-01-27 18:49:55', null, '2020-01-27 18:49:55');
INSERT INTO dice.auth_rel_role_item (id, role_id, item_id, deleted, creator, create_time, modifier, update_time) VALUES (295, 2, 39, 0, null, '2020-01-27 18:49:55', null, '2020-01-27 18:49:55');
INSERT INTO dice.auth_rel_role_item (id, role_id, item_id, deleted, creator, create_time, modifier, update_time) VALUES (296, 2, 35, 0, null, '2020-01-27 18:49:55', null, '2020-01-27 18:49:55');
INSERT INTO dice.auth_rel_role_item (id, role_id, item_id, deleted, creator, create_time, modifier, update_time) VALUES (297, 2, 6, 0, null, '2020-01-27 18:49:55', null, '2020-01-27 18:49:55');
INSERT INTO dice.auth_rel_role_item (id, role_id, item_id, deleted, creator, create_time, modifier, update_time) VALUES (298, 2, 37, 0, null, '2020-01-27 18:49:55', null, '2020-01-27 18:49:55');
INSERT INTO dice.auth_rel_role_item (id, role_id, item_id, deleted, creator, create_time, modifier, update_time) VALUES (299, 2, 3, 0, null, '2020-01-27 18:49:55', null, '2020-01-27 18:49:55');
INSERT INTO dice.auth_rel_role_item (id, role_id, item_id, deleted, creator, create_time, modifier, update_time) VALUES (300, 2, 6, 0, null, '2020-01-27 18:49:55', null, '2020-01-27 18:49:55');
INSERT INTO dice.auth_rel_role_item (id, role_id, item_id, deleted, creator, create_time, modifier, update_time) VALUES (301, 2, 21, 0, null, '2020-01-27 18:49:55', null, '2020-01-27 18:49:55');
INSERT INTO dice.auth_rel_role_item (id, role_id, item_id, deleted, creator, create_time, modifier, update_time) VALUES (302, 2, 31, 0, null, '2020-01-27 18:49:55', null, '2020-01-27 18:49:55');
INSERT INTO dice.auth_rel_role_item (id, role_id, item_id, deleted, creator, create_time, modifier, update_time) VALUES (303, 2, 24, 0, null, '2020-01-27 18:49:55', null, '2020-01-27 18:49:55');
INSERT INTO dice.auth_rel_role_item (id, role_id, item_id, deleted, creator, create_time, modifier, update_time) VALUES (304, 2, 23, 0, null, '2020-01-27 18:49:55', null, '2020-01-27 18:49:55');INSERT INTO dice.auth_rel_role_user (id, user_id, role_id, deleted, creator, create_time, modifier, update_time) VALUES (1, 1, 1, 0, null, '2019-12-31 13:33:31', null, '2019-12-31 13:33:31');
INSERT INTO dice.auth_rel_role_user (id, user_id, role_id, deleted, creator, create_time, modifier, update_time) VALUES (10, 2, 2, 0, null, '2020-01-27 18:59:31', null, '2020-01-27 18:59:31');INSERT INTO dice.auth_role (role_id, role_name, user_type, role_type, project_type, description, deleted, creator, create_time, modifier, update_time) VALUES (1, '管理员', 1, 1, 'dice', '', 0, null, '2019-12-30 16:02:14', null, '2019-12-30 16:02:14');
INSERT INTO dice.auth_role (role_id, role_name, user_type, role_type, project_type, description, deleted, creator, create_time, modifier, update_time) VALUES (2, 'Demo 用户', 1, 2, 'dice', '', 0, null, '2019-12-30 18:48:44', null, '2019-12-30 18:48:44');
