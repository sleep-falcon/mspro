create database `test_02`;
use test_02;

create table if not exists user_01
(
    id  bigint not null auto_increment comment '自增ID',
    userId       varchar(9)  null comment '用户ID',
    userNickName varchar(32) null comment '用户昵称',
    userHead     varchar(16) null comment '用户头像',
    userPassword varchar(64) null comment '用户密码',
    createTime   datetime    null comment '创建时间',
    updateTime   datetime    null comment '更新时间',
    primary key(id)
)
    charset = utf8;

create table if not exists user_02
(
    id           bigint not null auto_increment comment '自增ID',
    userId       varchar(9)  null comment '用户ID',
    userNickName varchar(32) null comment '用户昵称',
    userHead     varchar(16) null comment '用户头像',
    userPassword varchar(64) null comment '用户密码',
    createTime   datetime    null comment '创建时间',
    updateTime   datetime    null comment '更新时间',
    primary key(id)
)
    charset = utf8;

create table if not exists user_03
(
    id           bigint not null auto_increment comment '自增ID',
    userId       varchar(9)  null comment '用户ID',
    userNickName varchar(32) null comment '用户昵称',
    userHead     varchar(16) null comment '用户头像',
    userPassword varchar(64) null comment '用户密码',
    createTime   datetime    null comment '创建时间',
    updateTime   datetime    null comment '更新时间',
    primary key(id)
)
    charset = utf8;

create table if not exists user_04
(
    id         bigint not null auto_increment comment '自增ID',
    userId       varchar(9)  null comment '用户ID',
    userNickName varchar(32) null comment '用户昵称',
    userHead     varchar(16) null comment '用户头像',
    userPassword varchar(64) null comment '用户密码',
    createTime   datetime    null comment '创建时间',
    updateTime   datetime    null comment '更新时间',
    primary key(id)
)
    charset = utf8;

