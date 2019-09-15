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
  DEFAULT CHARSET = utf8;

CREATE TABLE user
(
    id           INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username     VARCHAR(45)     NOT NULL UNIQUE,
    password_md5 VARCHAR(45)     NOT NULL,
    email        VARCHAR(45),
    screen_name  VARCHAR(45),
    created      TIMESTAMP       NOT NULL DEFAULT current_timestamp,
    logged       TIMESTAMP       NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table article
(
    type          varchar(45)                          not null,
    id            int auto_increment
        primary key,
    created       timestamp  default CURRENT_TIMESTAMP not null,
    modified      timestamp  default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    allow_comment tinyint(1) default 1                 not null,
    author_id     int                                  null,
    comment_count int        default 0                 not null,
    content       mediumtext                           null,
    hits          int        default 0                 not null,
    priority      int        default 0                 not null,
    status        varchar(32)                          null,
    title         varchar(255)                         not null,
    category      varchar(500)                         null,
    tags          varchar(500)                         null
);

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
  DEFAULT CHARSET = utf8;

CREATE TABLE meta
(
    id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255)    NOT NULL,
    type VARCHAR(45)     NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE middle
(
    id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    a_id INT             NOT NULL,
    m_id INT             NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

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
  DEFAULT CHARSET = utf8;

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
  DEFAULT CHARSET = utf8;


INSERT INTO user (username, password_md5, email, screen_name)
VALUES ('dice', '3e6693e83d186225b85b09e71c974d2d', '', 'admin');

INSERT INTO article (title, created, modified, content, author_id, hits, tags, category, status, type)
VALUES ('Hello world', now(), now(), '
欢迎使用[Dice](https://github.com/bihell/Dice)! 这是你的第一篇博客。快点来写点什么吧

```java
public static void main(String[] args){
    System.out.println("Hello world");
}
```

> 想要了解更多详细信息，可以查看[文档](https://github.com/bihell/Dice/blob/master/README.md)。', 1, 0, 'First', 'New', 'publish', 'post');

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

INSERT INTO article (title, created, modified, content, author_id, tags, category, status, type)
VALUES ('关于', now(), now(), '# About me
### Hello word
这是关于我的页面

* [Github](https://github.com/bihell)
* [哔哩哔哩](https://space.bilibili.com/88900889/video)

### 也可以设置别的页面
* 比如友链页面', 1, NULL, NULL, 'publish', 'page');

INSERT INTO sys_option (option_key, option_value)
VALUES ('dice_init', 'true');
