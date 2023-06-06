DROP DATABASE IF EXISTS dice;
CREATE DATABASE dice CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE dice;

create table sys_user_role
(
    id      bigint auto_increment comment '主键'
        primary key,
    user_id bigint null comment '用户编号',
    role_id bigint null comment '角色编号'
)
    comment '用户角色表';

INSERT INTO dice.sys_user_role (id, user_id, role_id) VALUES (6, 1, 1);
INSERT INTO dice.sys_user_role (id, user_id, role_id) VALUES (7, 1, 3);
INSERT INTO dice.sys_user_role (id, user_id, role_id) VALUES (8, 2, 3);

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
    dept_id     int                                null,
    username    varchar(32)                        not null comment '用户名',
    real_name   varchar(32)                        null comment '真名',
    nickname    varchar(32)                        null comment '昵称',
    email       varchar(45)                        null,
    phone       varchar(20)                        null comment '手机号码',
    gender      tinyint  default 0                 null comment '性别{0=保密, 1=男, 2=女}',
    avatar      varchar(255)                       null comment '头像',
    password    varchar(64)                        not null,
    status      int      default 1                 not null comment '状态',
    salt        varchar(32)                        null comment '盐值',
    deleted     tinyint  default 0                 null comment '删除标志{0=正常, 1=删除}',
    login_ip    varchar(128)                       null comment '最后登录IP',
    login_date  datetime                           null comment '最后登录时间',
    remark      int                                null,
    create_ty   varchar(32)                        null comment '创建者',
    create_time datetime default CURRENT_TIMESTAMP not null,
    update_by   varchar(32)                        null comment '更新者',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint username
        unique (username)
)
    charset = utf8mb4;

INSERT INTO dice.sys_user (id, dept_id, username, real_name, nickname, email, phone, gender, avatar, password, status, salt, deleted, login_ip, login_date, remark, create_ty, create_time, update_by, update_time) VALUES (1, 1, 'dice', null, null, 'tpxcer@outlook.com', null, 0, 'https://avatars.githubusercontent.com/u/19926035?v=4', 'dab6458f688b27c04d86b6f99757e2ce70d533f0092a85e4fbd9668261b1092b', 1, '66666', 0, null, null, null, null, '2019-05-16 02:24:35', null, '2021-07-15 11:52:15');
INSERT INTO dice.sys_user (id, dept_id, username, real_name, nickname, email, phone, gender, avatar, password, status, salt, deleted, login_ip, login_date, remark, create_ty, create_time, update_by, update_time) VALUES (2, 1, 'demo', null, null, 'demo@bihell.com', '111', 0, 'https://avatars.githubusercontent.com/u/19926035?v=4', 'dab6458f688b27c04d86b6f99757e2ce70d533f0092a85e4fbd9668261b1092b', 1, '66666', 0, null, '2023-06-06 14:40:59', null, null, '2019-12-27 15:34:01', null, '2021-07-16 17:32:47');

create table sys_role
(
    id          bigint auto_increment comment '主键'
        primary key,
    role_name   varchar(32)                         not null comment '角色名称',
    role_code   varchar(100)                        null comment '角色唯一编码',
    type        int                                 null comment '角色类型',
    status      int       default 1                 not null comment '角色状态，0：禁用，1：启用',
    description varchar(200)                        null comment '描述',
    version     int       default 0                 not null comment '版本',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted  int       default 0                 null comment '逻辑删除 默认效果 0 没有删除 1 已经删除',
    constraint sys_role_name_uindex
        unique (role_name)
)
    comment '系统角色';

INSERT INTO dice.sys_role (id, role_name, role_code, type, status, description, version, create_time, update_time, is_deleted) VALUES (1, '管理员', 'admin', null, 1, '神的存在', 0, '2021-07-01 18:53:59', '2021-07-01 18:57:54', 0);
INSERT INTO dice.sys_role (id, role_name, role_code, type, status, description, version, create_time, update_time, is_deleted) VALUES (3, 'Demo用户', 'Demo', null, 1, null, 0, '2021-07-07 15:48:20', '2021-07-07 15:48:20', 0);

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

create table comment
(
    id          int auto_increment
        primary key,
    article_id  int                                 not null,
    p_id        int                                 null,
    content     text                                not null,
    name        varchar(255)                        null,
    email       varchar(255)                        null,
    website     varchar(255)                        null,
    agree       int       default 0                 not null,
    disagree    int       default 0                 not null,
    ip          varchar(255)                        null,
    agent       varchar(255)                        null,
    status      int       default 0                 not null,
    create_time timestamp default CURRENT_TIMESTAMP not null
)
    charset = utf8mb4;

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

create table task
(
    id          bigint auto_increment
        primary key,
    concurrent  tinyint                             null comment '是否允许并发',
    cron        varchar(50)                         null comment '定时规则',
    data        text                                null comment '执行参数',
    exec_at     datetime                            null comment '执行时间',
    exec_result text                                null comment '执行结果',
    job_class   varchar(255)                        null comment '执行类',
    job_group   varchar(50)                         null comment '任务组名',
    name        varchar(50)                         null comment '任务名',
    note        varchar(255)                        null comment '任务说明',
    status      int                                 null comment '状态（0无效1有效）',
    creator     int                                 null comment '创建人',
    create_time timestamp default CURRENT_TIMESTAMP null comment '创建时间/注册时间',
    modifier    int                                 null comment '最后更新人',
    update_time timestamp default CURRENT_TIMESTAMP null comment '最后更新时间',
    is_deleted  int       default 0                 null comment '逻辑删除标识(0.未删除,1.已删除)'
)
    comment '定时任务';

INSERT INTO dice.task (id, concurrent, cron, data, exec_at, exec_result, job_class, job_group, name, note, status, creator, create_time, modifier, update_time, is_deleted) VALUES (1, 0, '0/6 * * * * ?', '{
"appname": "dice",
"version":2
}', '2020-01-27 14:54:24', '执行成功', 'com.bihell.dice.service.task.job.HelloJob', 'default', '测试任务1', '测试任务1', 0, 1, '2018-12-28 09:54:00', -1, '2019-03-27 11:47:11', 0);

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
    id                bigint auto_increment comment '主键' primary key,
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
    update_time       datetime                             null comment '修改时间',
    deleted     tinyint   default 0                 null comment '逻辑删除标识（0.未删除,1.已删除）'
)
    comment '系统登录日志';

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
* [哔哩哔哩](https://space.bilibili.com/88900889)

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
