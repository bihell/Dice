DROP DATABASE IF EXISTS dice;
CREATE DATABASE dice CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE dice;

create table sys_role_permission
(
    id            bigint auto_increment comment '主键'
        primary key,
    role_id       bigint                              not null comment '角色id',
    permission_id bigint                              not null comment '权限id',
    status        int       default 1                 not null comment '状态，0：禁用，1：启用',
    remark        varchar(200)                        null comment '备注',
    version       int       default 0                 not null comment '版本',
    create_time   timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   timestamp                           null comment '修改时间'
)
    comment '角色权限关系';

create index permission_id
    on sys_role_permission (permission_id);

create index role_id
    on sys_role_permission (role_id);

INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (28, 1, 25, 1, null, 0, '2021-07-12 13:23:33', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (29, 1, 26, 1, null, 0, '2021-07-12 13:23:33', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (30, 1, 27, 1, null, 0, '2021-07-12 13:23:33', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (31, 1, 32, 1, null, 0, '2021-07-12 17:03:37', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (32, 1, 33, 1, null, 0, '2021-07-12 17:03:37', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (33, 1, 34, 1, null, 0, '2021-07-12 17:03:37', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (34, 1, 28, 1, null, 0, '2021-07-12 17:03:37', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (35, 1, 29, 1, null, 0, '2021-07-12 17:03:37', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (36, 1, 30, 1, null, 0, '2021-07-12 17:03:37', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (37, 1, 31, 1, null, 0, '2021-07-12 17:03:37', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (38, 1, 35, 1, null, 0, '2021-07-12 17:30:21', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (39, 1, 39, 1, null, 0, '2021-07-12 17:30:21', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (40, 1, 40, 1, null, 0, '2021-07-12 17:30:21', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (41, 1, 41, 1, null, 0, '2021-07-12 17:30:21', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (42, 1, 42, 1, null, 0, '2021-07-12 17:30:21', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (43, 1, 43, 1, null, 0, '2021-07-12 17:30:21', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (44, 1, 44, 1, null, 0, '2021-07-12 17:30:21', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (45, 1, 45, 1, null, 0, '2021-07-12 17:30:21', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (49, 3, 43, 1, null, 0, '2021-07-12 17:32:04', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (50, 3, 31, 1, null, 0, '2021-07-12 17:32:04', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (51, 1, 36, 1, null, 0, '2021-07-12 17:40:27', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (52, 1, 46, 1, null, 0, '2021-07-12 17:40:27', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (53, 1, 47, 1, null, 0, '2021-07-12 17:40:27', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (54, 1, 48, 1, null, 0, '2021-07-12 17:40:27', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (55, 1, 49, 1, null, 0, '2021-07-12 17:40:27', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (56, 1, 50, 1, null, 0, '2021-07-12 17:40:27', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (57, 1, 51, 1, null, 0, '2021-07-12 17:40:27', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (58, 1, 52, 1, null, 0, '2021-07-12 17:40:27', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (59, 1, 53, 1, null, 0, '2021-07-12 17:40:27', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (60, 1, 54, 1, null, 0, '2021-07-12 17:40:27', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (61, 1, 55, 1, null, 0, '2021-07-12 17:40:27', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (62, 1, 56, 1, null, 0, '2021-07-12 17:40:27', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (63, 1, 57, 1, null, 0, '2021-07-12 17:40:27', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (64, 1, 64, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (65, 1, 67, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (66, 1, 68, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (67, 1, 69, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (68, 1, 70, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (69, 1, 71, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (70, 1, 72, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (71, 1, 73, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (72, 1, 74, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (73, 1, 37, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (74, 1, 58, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (75, 1, 59, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (76, 1, 60, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (77, 1, 61, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (78, 1, 62, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (79, 1, 63, 1, null, 0, '2021-07-13 11:13:10', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (80, 1, 75, 1, null, 0, '2021-07-13 11:21:30', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (81, 1, 76, 1, null, 0, '2021-07-13 11:21:30', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (82, 1, 77, 1, null, 0, '2021-07-13 11:21:30', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (83, 3, 81, 1, null, 0, '2021-07-13 13:37:58', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (87, 3, 69, 1, null, 0, '2021-07-13 13:39:05', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (89, 3, 50, 1, null, 0, '2021-07-13 13:39:05', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (90, 3, 62, 1, null, 0, '2021-07-13 13:39:05', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (93, 3, 82, 1, null, 0, '2021-07-13 14:03:39', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (95, 3, 86, 1, null, 0, '2021-07-13 14:53:11', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (97, 3, 89, 1, null, 0, '2021-07-13 15:22:01', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (99, 3, 90, 1, null, 0, '2021-07-13 15:28:14', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (101, 3, 93, 1, null, 0, '2021-07-13 15:33:49', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (102, 1, 38, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (103, 1, 65, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (104, 1, 66, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (105, 1, 78, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (106, 1, 79, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (107, 1, 80, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (108, 1, 81, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (109, 1, 82, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (110, 1, 83, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (111, 1, 84, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (112, 1, 85, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (113, 1, 86, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (114, 1, 87, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (115, 1, 88, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (116, 1, 89, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (117, 1, 90, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (118, 1, 91, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (119, 1, 92, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (120, 1, 93, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (121, 1, 94, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (122, 1, 95, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (123, 1, 96, 1, null, 0, '2021-07-13 15:39:32', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (124, 3, 67, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (125, 3, 68, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (126, 3, 70, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (127, 3, 71, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (128, 3, 72, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (129, 3, 73, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (130, 3, 75, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (131, 3, 76, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (132, 3, 77, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (133, 3, 25, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (134, 3, 26, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (135, 3, 35, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (136, 3, 36, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (137, 3, 37, 1, null, 0, '2021-07-13 15:42:26', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (138, 3, 51, 1, null, 0, '2021-07-13 15:45:47', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (139, 3, 52, 1, null, 0, '2021-07-13 15:45:47', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (140, 3, 53, 1, null, 0, '2021-07-13 15:45:47', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (141, 3, 54, 1, null, 0, '2021-07-13 15:45:47', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (142, 3, 55, 1, null, 0, '2021-07-13 15:45:47', null);
INSERT INTO dice.sys_role_permission (id, role_id, permission_id, status, remark, version, create_time, update_time) VALUES (143, 3, 56, 1, null, 0, '2021-07-13 15:45:47', null);

create table sys_user
(
    id          int auto_increment
        primary key,
    username    varchar(45)                         not null,
    pwd         varchar(64)                         not null,
    email       varchar(45)                         null,
    nickname    varchar(45)                         null,
    create_time timestamp default CURRENT_TIMESTAMP not null,
    logged      timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    salt        varchar(32)                         null comment '盐值',
    avatar      varchar(255)                        null comment '头像',
    role_id     int                                 not null comment '角色id',
    status      int       default 1                 not null comment '状态',
    deleted     int       default 0                 null,
    dept_id     int                                 null,
    phone       varchar(20)                         null comment '手机号码',
    remark      int                                 null,
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint username
        unique (username)
)
    charset = utf8mb4;

INSERT INTO dice.sys_user (id, username, pwd, email, nickname, create_time, logged, salt, avatar, role_id, status, deleted, dept_id, phone, remark, update_time) VALUES (1, 'dice', 'dab6458f688b27c04d86b6f99757e2ce70d533f0092a85e4fbd9668261b1092b', 'tpxcer@outlook.com', 'admin', '2019-05-16 02:24:35', '2021-07-07 15:47:20', '66666', 'https://avatars.githubusercontent.com/u/19926035?v=4', 1, 1, 0, 1, null, null, '2021-07-07 15:47:20');
INSERT INTO dice.sys_user (id, username, pwd, email, nickname, create_time, logged, salt, avatar, role_id, status, deleted, dept_id, phone, remark, update_time) VALUES (2, 'demo', 'dab6458f688b27c04d86b6f99757e2ce70d533f0092a85e4fbd9668261b1092b', 'demo@bihell.com', 'demo用户', '2019-12-27 15:34:01', '2021-07-13 17:39:12', '66666', 'https://avatars.githubusercontent.com/u/19926035?v=4', 3, 1, 0, 1, '1111', null, '2021-07-12 17:04:27');

create table sys_role
(
    id          bigint auto_increment comment '主键'
        primary key,
    role_name   varchar(32)                         not null comment '角色名称',
    code        varchar(100)                        null comment '角色唯一编码',
    type        int                                 null comment '角色类型',
    status      int       default 1                 not null comment '角色状态，0：禁用，1：启用',
    remark      varchar(200)                        null comment '备注',
    version     int       default 0                 not null comment '版本',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint sys_role_name_uindex
        unique (role_name)
)
    comment '系统角色';

INSERT INTO dice.sys_role (id, role_name, code, type, status, remark, version, create_time, update_time) VALUES (1, '管理员', 'admin', null, 1, '神的存在', 0, '2021-07-01 18:53:59', '2021-07-01 18:57:54');
INSERT INTO dice.sys_role (id, role_name, code, type, status, remark, version, create_time, update_time) VALUES (3, 'Demo用户', 'Demo', null, 1, null, 0, '2021-07-07 15:48:20', '2021-07-07 15:48:20');

create table sys_permission
(
    id          bigint auto_increment comment '主键'
        primary key,
    name        varchar(32)                         null comment '权限名称',
    parent_id   bigint                              null comment '父id',
    route_path  varchar(200)                        null comment '路径',
    code        varchar(100)                        null comment '唯一编码',
    icon        varchar(100)                        null comment '图标',
    type        int                                 not null comment '类型，0：目录，1：菜单，2：按钮',
    level       int                                 not null comment '层级，1：第一级，2：第二级，N：第N级',
    status      int       default 1                 not null comment '状态，0：禁用，1：启用',
    sort        int       default 0                 not null comment '排序',
    remark      varchar(200)                        null comment '备注',
    version     int       default 0                 not null comment '版本',
    component   varchar(255)                        null comment '组件',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_show     int                                 null,
    keep_alive  int                                 null,
    is_ext      int                                 null,
    frame       int                                 null,
    constraint sys_permission_code_uindex
        unique (code)
)
    comment '系统权限';

INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (25, '系统', null, 'system', null, 'ri:admin-line', 1, 1, 1, 999, null, 0, null, '2021-07-12 11:49:58', '2021-07-13 21:13:18', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (26, '用户管理', 25, 'user', 'sys:user:management', null, 2, 2, 1, 1, null, 0, '/sys/account/index', '2021-07-12 11:51:44', '2021-07-12 17:33:54', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (27, '用户新增', 26, null, 'sys:user:add', null, 3, 3, 1, 1, null, 0, null, '2021-07-12 11:52:19', '2021-07-12 12:01:48', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (28, '用户修改', 26, null, 'sys:user:update', null, 3, 3, 1, 2, null, 0, null, '2021-07-12 16:57:31', '2021-07-12 16:57:31', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (29, '用户删除', 26, null, 'sys:user:delete', null, 3, 3, 1, 3, null, 0, null, '2021-07-12 16:57:52', '2021-07-12 16:57:52', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (30, '用户详情', 26, null, 'sys:user:info', null, 3, 3, 1, 4, null, 0, null, '2021-07-12 16:58:11', '2021-07-12 16:58:11', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (31, '用户分页列表', 26, null, 'sys:user:page', null, 3, 3, 1, 5, null, 0, null, '2021-07-12 16:58:28', '2021-07-12 16:58:28', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (32, '用户修改密码', 26, null, 'sys:user:update:password', null, 3, 3, 1, 6, null, 0, null, '2021-07-12 16:58:54', '2021-07-12 16:58:54', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (33, '用户修改头像', 26, null, 'sys:user:update:head', null, 3, 3, 1, 7, null, 0, null, '2021-07-12 16:59:12', '2021-07-12 16:59:12', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (34, '用户重置密码', 26, null, 'sys:user:reset:password', null, 3, 3, 1, 8, null, 0, null, '2021-07-12 16:59:37', '2021-07-12 16:59:37', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (35, '角色管理', 25, 'role', 'sys:role:management', null, 2, 2, 1, 2, null, 0, '/sys/role/index', '2021-07-12 17:13:18', '2021-07-12 17:13:18', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (36, '权限管理', 25, 'permission', 'sys:permission:management', null, 2, 2, 1, 3, null, 0, '/sys/menu/index', '2021-07-12 17:14:31', '2021-07-12 17:14:31', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (37, '部门管理', 25, 'department', 'sys:department:management', null, 2, 2, 1, 4, null, 0, '/sys/dept/index', '2021-07-12 17:16:07', '2021-07-12 17:16:07', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (38, '日志管理', 25, 'log', 'sys:log:manager', null, 2, 2, 1, 5, null, 0, null, '2021-07-12 17:16:39', '2021-07-12 17:16:39', 0, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (39, '角色新增', 35, null, 'sys:role:add', null, 3, 3, 1, 1, null, 0, null, '2021-07-12 17:28:18', '2021-07-12 17:28:18', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (40, '角色修改', 35, null, 'sys:role:update', null, 3, 3, 1, 2, null, 0, null, '2021-07-12 17:28:33', '2021-07-12 17:28:33', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (41, '角色删除', 35, null, 'sys:role:delete', null, 3, 3, 1, 3, null, 0, null, '2021-07-12 17:28:50', '2021-07-12 17:28:50', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (42, '角色详情', 35, null, 'sys:role:info', null, 3, 3, 1, 4, null, 0, null, '2021-07-12 17:29:08', '2021-07-12 17:29:08', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (43, '角色分页列表', 35, null, 'sys:role:page', null, 3, 3, 1, 5, null, 0, null, '2021-07-12 17:29:21', '2021-07-12 17:29:21', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (44, '角色列表', 35, null, 'sys:role:list', null, 3, 3, 1, 6, null, 0, null, '2021-07-12 17:29:37', '2021-07-12 17:29:37', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (45, '角色权限ID列表', 35, null, 'sys:permission:three-ids-by-role-id', null, 3, 3, 1, 7, null, 0, null, '2021-07-12 17:29:56', '2021-07-12 17:29:56', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (46, '权限新增', 36, null, 'sys:permission:add', null, 3, 3, 1, 1, null, 0, null, '2021-07-12 17:37:01', '2021-07-12 17:37:01', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (47, '权限修改', 36, null, 'sys:permission:update', null, 3, 3, 1, 2, null, 0, null, '2021-07-12 17:37:14', '2021-07-12 17:37:14', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (48, '权限删除', 36, null, 'sys:permission:delete', null, 3, 3, 1, 3, null, 0, null, '2021-07-12 17:37:26', '2021-07-12 17:37:26', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (49, '权限详情', 36, null, 'sys:permission:info', null, 3, 3, 1, 4, null, 0, null, '2021-07-12 17:37:48', '2021-07-12 17:37:48', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (50, '权限分页列表', 36, null, 'sys:permission:page', null, 3, 3, 1, 5, null, 0, null, '2021-07-12 17:38:06', '2021-07-12 17:38:06', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (51, '权限所有列表', 36, null, 'sys:permission:all:menu:list', null, 3, 3, 1, 6, null, 0, null, '2021-07-12 17:38:27', '2021-07-12 17:38:27', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (52, '权限所有树形列表', 36, null, 'sys:permission:all:menu:tree', null, 3, 3, 1, 7, null, 0, null, '2021-07-12 17:38:46', '2021-07-12 17:38:46', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (53, '权限用户列表', 36, null, 'sys:permission:menu:list', null, 3, 3, 1, 8, null, 0, null, '2021-07-12 17:38:58', '2021-07-12 17:38:58', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (54, '权限用户树形列表', 36, null, 'sys:permission:menu:tree', null, 3, 3, 1, 9, null, 0, null, '2021-07-12 17:39:12', '2021-07-12 17:39:12', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (55, '权限用户代码列表', 36, null, 'sys:permission:codes', null, 3, 3, 1, 19, null, 0, null, '2021-07-12 17:39:26', '2021-07-12 17:39:26', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (56, '导航菜单', 36, null, 'sys:permission:nav-menu', null, 3, 3, 1, 11, null, 0, null, '2021-07-12 17:39:41', '2021-07-12 17:39:41', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (57, '角色权限修改', 36, null, 'sys:role-permission:update', null, 3, 3, 1, 12, null, 0, null, '2021-07-12 17:40:11', '2021-07-12 17:40:11', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (58, '部门新增', 37, null, 'sys:department:add', null, 3, 3, 1, 1, null, 0, null, '2021-07-12 17:41:41', '2021-07-12 17:41:41', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (59, '部门修改', 37, null, 'sys:department:update', null, 3, 3, 1, 2, null, 0, null, '2021-07-12 17:41:53', '2021-07-12 17:41:53', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (60, '部门删除', 37, null, 'sys:department:delete', null, 3, 3, 1, 3, null, 0, null, '2021-07-12 17:42:06', '2021-07-12 17:42:06', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (61, '部门详情', 37, null, 'sys:department:info', null, 3, 3, 1, 4, null, 0, null, '2021-07-12 17:42:17', '2021-07-12 17:42:17', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (62, '部门分页列表', 37, null, 'sys:department:page', null, 3, 3, 1, 5, null, 0, null, '2021-07-12 17:42:44', '2021-07-12 17:42:44', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (63, '部门列表', 37, null, 'sys:department:list', null, 3, 3, 1, 6, null, 0, null, '2021-07-12 17:43:13', '2021-07-12 17:43:13', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (64, '部门树形列表', 37, null, 'sys:department:all:tree', null, 3, 3, 1, 7, null, 0, null, '2021-07-12 17:43:28', '2021-07-12 17:43:28', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (65, '操作日志列表', 38, null, 'sys:operation:log:page', null, 3, 3, 1, 1, null, 0, null, '2021-07-13 11:01:25', '2021-07-13 11:01:25', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (66, '登录日志列表', 38, null, 'sys:login:log:page', null, 3, 3, 1, 2, null, 0, null, '2021-07-13 11:01:37', '2021-07-13 11:01:37', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (67, '博客', null, 'blog', null, 'carbon:blog', 1, 1, 1, 1, null, 0, null, '2021-07-13 11:03:01', '2021-07-13 19:06:14', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (68, '文章列表', 67, 'posts', '', null, 2, 2, 1, 1, null, 0, '/blog/post/PostList', '2021-07-13 11:05:31', '2021-07-13 14:58:06', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (69, '编辑文章', 67, 'postEdit', null, null, 2, 2, 1, 2, null, 0, '/blog/post/PostEdit', '2021-07-13 11:07:03', '2021-07-13 11:17:57', 0, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (70, '评论列表', 67, 'comments', null, null, 2, 2, 1, 3, null, 0, '/blog/comment/CommentList', '2021-07-13 11:08:41', '2021-07-13 11:08:41', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (71, '标签分类', 67, 'tags', null, null, 2, 2, 1, 4, null, 0, '/blog/meta/Tag', '2021-07-13 11:09:51', '2021-07-13 11:09:51', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (72, '页面列表', 67, 'pages', null, null, 2, 2, 1, 5, null, 0, '/blog/page/PageList', '2021-07-13 11:11:01', '2021-07-13 11:11:01', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (73, '编辑页面', 67, 'pageEdit', null, null, 2, 2, 1, 6, null, 0, '/blog/page/PageEdit', '2021-07-13 11:11:59', '2021-07-13 11:11:59', 0, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (74, '博客设置', 67, 'blogSetting', null, null, 2, 2, 1, 7, null, 0, '/blog/setting/index', '2021-07-13 11:12:30', '2021-07-13 11:12:30', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (75, '导航', null, 'nav', null, 'ri:navigation-line', 1, 1, 1, 2, null, 0, null, '2021-07-13 11:19:34', '2021-07-13 21:13:37', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (76, '导航分类', 75, 'navType', null, null, 2, 2, 1, 1, null, 0, '/nav/type/index', '2021-07-13 11:20:33', '2021-07-13 11:20:33', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (77, '导航清单', 75, 'navDetail', null, null, 2, 2, 1, 2, null, 0, '/nav/list/index', '2021-07-13 11:21:14', '2021-07-13 11:21:14', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (78, '新增文章', 68, null, 'blog:posts:add', null, 3, 3, 1, 1, null, 0, null, '2021-07-13 13:31:42', '2021-07-13 15:06:29', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (79, '编辑文章', 68, null, 'blog:posts:update', null, 3, 3, 1, 2, null, 0, null, '2021-07-13 13:32:58', '2021-07-13 15:06:38', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (80, '删除文章', 68, null, 'blog:posts:delete', null, 3, 3, 1, 3, null, 0, null, '2021-07-13 13:33:19', '2021-07-13 15:06:44', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (81, '文章列表', 68, null, 'blog:posts:page', null, 3, 3, 1, 4, null, 0, null, '2021-07-13 13:37:39', '2021-07-13 15:07:00', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (82, '分类列表', 76, null, 'nav:type:list', null, 3, 3, 1, 1, null, 0, null, '2021-07-13 14:03:06', '2021-07-13 14:03:06', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (83, '新增导航', 76, null, 'nav:type:add', null, 3, 3, 1, 2, null, 0, null, '2021-07-13 14:03:29', '2021-07-13 14:03:29', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (84, '设置列表', 74, null, 'blog:option:list', null, 3, 3, 1, 1, null, 0, null, '2021-07-13 14:51:13', '2021-07-13 14:51:13', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (85, '设置更新', 74, null, 'blog:option:update', null, 3, 3, 1, 2, null, 0, null, '2021-07-13 14:51:34', '2021-07-13 14:51:34', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (86, '评论列表', 70, null, 'blog:comment:list', null, 3, 3, 1, 1, null, 0, null, '2021-07-13 14:52:53', '2021-07-13 14:52:53', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (87, '新增导航', 77, null, 'nav:detail:add', null, 3, 3, 1, 1, null, 0, null, '2021-07-13 15:19:18', '2021-07-13 15:19:18', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (88, '删除导航', 77, null, 'nav:detail:delete', null, 3, 3, 1, 2, null, 0, null, '2021-07-13 15:19:45', '2021-07-13 15:19:45', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (89, '导航列表', 77, null, 'nav:detail:page', null, 3, 3, 1, 3, null, 0, null, '2021-07-13 15:20:08', '2021-07-13 15:20:08', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (90, '标签清单', 71, null, 'blog:tag:list', null, 3, 3, 1, 1, null, 0, null, '2021-07-13 15:24:45', '2021-07-13 15:24:45', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (91, '标签更新', 71, null, 'blog:tag:update', null, 3, 3, 1, 2, null, 0, null, '2021-07-13 15:25:26', '2021-07-13 15:25:26', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (92, '标签删除', 71, null, 'blog:tag:delete', null, 3, 3, 1, 3, null, 0, null, '2021-07-13 15:25:46', '2021-07-13 15:25:46', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (93, '页面列表', 72, null, 'blog:pages:page', null, 3, 3, 1, 1, null, 0, null, '2021-07-13 15:32:13', '2021-07-13 15:32:13', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (94, '页面新增', 72, null, 'blog:pages:add', null, 3, 3, 1, 2, null, 0, null, '2021-07-13 15:32:41', '2021-07-13 15:32:41', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (95, '页面删除', 72, null, 'blog:pages:delete', null, 3, 3, 1, 3, null, 0, null, '2021-07-13 15:33:02', '2021-07-13 15:33:02', 1, 1, 0, null);
INSERT INTO dice.sys_permission (id, name, parent_id, route_path, code, icon, type, level, status, sort, remark, version, component, create_time, update_time, is_show, keep_alive, is_ext, frame) VALUES (96, '页面更新', 72, null, 'blog:pages:update', null, 3, 3, 1, 4, null, 0, null, '2021-07-13 15:33:23', '2021-07-13 15:33:23', 1, 1, 0, null);

create table sys_department
(
    id          bigint auto_increment comment '主键'
        primary key,
    dept_name   varchar(32)                         not null comment '部门名称',
    parent_id   bigint                              null comment '父id',
    level       int                                 null comment '部门层级',
    status      int       default 1                 not null comment '状态，0：禁用，1：启用',
    sort        int       default 0                 not null comment '排序',
    remark      varchar(200)                        null comment '备注',
    version     int       default 0                 not null comment '版本',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp                           null comment '修改时间',
    constraint sys_department_name_uindex
        unique (dept_name)
)
    comment '部门';

INSERT INTO dice.sys_department (id, dept_name, parent_id, level, status, sort, remark, version, create_time, update_time) VALUES (1, '管理组', null, null, 1, 1, '拥有至高无上的权利', 0, '2021-07-06 16:51:04', null);

CREATE TABLE sys_option
(
    id           INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    option_key   VARCHAR(100)    NOT NULL UNIQUE,
    option_value VARCHAR(1023)   NOT NULL,
    created      TIMESTAMP       NOT NULL DEFAULT current_timestamp,
    modified     TIMESTAMP       NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;

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

create table nav_type
(
    id          int auto_increment comment '导航类型id'
        primary key,
    name        varchar(50)                         null comment '导航类型名称',
    creator     int                                 null comment '创建人',
    modifier    int                                 null comment '修改人',
    is_public   int       default 1                 null comment '是否公开：1是，0否',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    icon        varchar(255)                        null comment '类型图标',
    status      tinyint   default 1                 null comment '状态（1启用，0禁用）',
    parent_id   int                                 null,
    level       int                                 null,
    sort        int                                 null
)
    charset = utf8mb4;

create index nav_type_creator_index
    on nav_type (creator);

create table nav_detail
(
    id          bigint auto_increment comment '导航明细项id'
        primary key,
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    creator     int                                 null comment '创建人',
    modifier    int                                 null comment '修改人',
    deleted     tinyint   default 0                 null comment '逻辑删除标识（0.未删除,1.已删除）',
    is_public   tinyint   default 1                 null comment '是否公开',
    icon        varchar(255)                        null comment '图标',
    description varchar(255)                        null comment '简介',
    name        varchar(255)                        null comment '导航地址名称',
    type_id     int                                 null comment '类型id',
    status      tinyint                             null comment '状态（1启用，0禁用）',
    url         varchar(255)                        null comment '导航网址',
    sort        int                                 null comment '排序'
)
    comment '导航明细表' charset = utf8mb4;

create index nav_detail_creator_index
    on nav_detail (creator);

create table sys_login_log
(
    id                bigint auto_increment comment '主键'
        primary key,
    request_id        varchar(32)                          null comment '请求ID',
    username          varchar(32)                          null comment '用户名称',
    ip                varchar(15)                          null comment 'IP',
    area              varchar(45)                          null comment '区域',
    operator          varchar(6)                           null comment '运营商',
    token             varchar(32)                          null comment 'tokenMd5值',
    type              int                                  null comment '1:登录，2：登出',
    success           tinyint(1) default 0                 not null comment '是否成功 true:成功/false:失败',
    code              int                                  null comment '响应码',
    exception_message varchar(300)                         null comment '失败消息记录',
    user_agent        varchar(300)                         null comment '浏览器名称',
    browser_name      varchar(100)                         null comment '浏览器名称',
    browser_version   varchar(100)                         null comment '浏览器版本',
    engine_name       varchar(100)                         null comment '浏览器引擎名称',
    engine_version    varchar(100)                         null comment '浏览器引擎版本',
    os_name           varchar(100)                         null comment '系统名称',
    platform_name     varchar(100)                         null comment '平台名称',
    mobile            tinyint(1)                           null comment '是否是手机,0:否,1:是',
    device_name       varchar(100)                         null comment '移动端设备名称',
    device_model      varchar(100)                         null comment '移动端设备型号',
    remark            varchar(200)                         null comment '备注',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    update_time       datetime                             null comment '修改时间'
)
    comment '系统登录日志';

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
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (65, '', '/v1/api/admin/auth/reset/sysUser', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (66, '', '/v1/api/admin/auth/role/add', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (67, '', '/v1/api/admin/auth/role/assign/api', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (68, '', '/v1/api/admin/auth/role/assign/content', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (69, '', '/v1/api/admin/auth/role/assign/item', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (70, '', '/v1/api/admin/auth/role/assign/sysUser', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (71, '', '/v1/api/admin/auth/role/get', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (72, '', '/v1/api/admin/auth/role/list', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (73, '', '/v1/api/admin/auth/role/update', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (74, '', '/v1/api/admin/auth/sysUser/add', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (75, '', '/v1/api/admin/auth/sysUser/assign/role', 'dice', 0, null, '2020-01-06 18:24:42', null, '2020-01-06 18:24:42');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (76, '', '/v1/api/admin/auth/sysUser/get', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (77, '', '/v1/api/admin/auth/sysUser/list', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (78, '', '/v1/api/admin/auth/sysUser/list/all', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
INSERT INTO dice.auth_api (api_id, api_type, api_path, project_type, deleted, creator, create_time, modifier, update_time) VALUES (79, '', '/v1/api/admin/auth/sysUser/update', 'dice', 0, null, '2020-01-06 18:24:43', null, '2020-01-06 18:24:43');
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
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (8, '用户列表', '/auth/sysUser/list', 4, 0, 1, '', 0, null, '2019-12-24 15:55:44', null, '2019-12-24 15:55:44');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (9, '菜单管理', '/auth/menu/list', 4, 0, 1, '', 0, null, '2019-12-24 15:56:23', null, '2019-12-24 15:56:23');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (11, 'API管理', '', 4, 0, 1, '', 0, null, '2020-01-06 18:22:48', null, '2020-01-06 18:22:48');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (12, '角色管理', '', 4, 0, 1, '', 0, null, '2020-01-07 21:21:23', null, '2020-01-07 21:21:23');
INSERT INTO dice.auth_classes (classes_id, classes_name, classes_url, group_id, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (13, '内容管理', '', 4, 0, 1, '', 0, null, '2020-01-07 22:10:50', null, '2020-01-07 22:10:50');INSERT INTO dice.auth_group (group_id, group_name, group_url, project_type, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (1, '博客', '', 'dice', 0, 1, '', 0, null, '2019-12-23 17:56:50', null, '2019-12-23 17:56:50');
INSERT INTO dice.auth_group (group_id, group_name, group_url, project_type, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (2, '工具', '', 'dice', 0, 1, '', 0, null, '2019-12-23 18:08:01', null, '2019-12-23 18:08:01');
INSERT INTO dice.auth_group (group_id, group_name, group_url, project_type, `order`, is_display, style, deleted, creator, create_time, modifier, update_time) VALUES (4, '权限', '', 'dice', 0, 1, '', 0, null, '2019-12-23 18:25:39', null, '2019-12-23 18:25:39');INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (1, '新增文章', '/blog/article/new', 1, 0, null, null, '', 0, null, '2019-12-24 16:23:19', null, '2019-12-24 16:23:19');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (2, '编辑文章', '/blog/article/edit', 1, 0, null, null, '', 0, null, '2019-12-24 17:31:55', null, '2019-12-24 17:31:55');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (3, '用户列表', '/auth/sysUser/list', 8, 0, null, null, '', 0, null, '2019-12-31 13:40:22', null, '2019-12-31 13:40:22');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (4, '分配角色', '/auth/sysUser/assign/role', 8, 0, null, null, '', 0, null, '2019-12-31 13:41:49', null, '2019-12-31 13:41:49');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (5, '用户编辑', '/auth/sysUser/update', 8, 0, null, null, '', 0, null, '2019-12-31 13:43:09', null, '2019-12-31 13:43:09');
INSERT INTO dice.auth_item (item_id, item_name, item_code, classes_id, `order`, style, auto_flag, outer_url, deleted, creator, create_time, modifier, update_time) VALUES (6, '添加用户', '/auth/sysUser/add', 8, 0, null, null, '', 0, null, '2019-12-31 13:49:13', null, '2019-12-31 13:49:13');
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
