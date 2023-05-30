create database `test`

CREATE TABLE
    USER
    (
        id bigint NOT NULL AUTO_INCREMENT COMMENT '����ID',
        userId VARCHAR(9) COMMENT '�û�ID',
        userNickName VARCHAR(32) COMMENT '�û��ǳ�',
        userHead VARCHAR(16) COMMENT '�û�ͷ��',
        userPassword VARCHAR(64) COMMENT '�û�����',
        createTime DATETIME COMMENT '����ʱ��',
        updateTime DATETIME COMMENT '����ʱ��',
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8

insert into user (id, userId, userNickName, userHead, userPassword, createTime, updateTime) values (1, '184172133', 'С����', '01_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
insert into user (id, userId, userNickName, userHead, userPassword, createTime, updateTime) values (2, '980765512', '����', '02_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
insert into user (id, userId, userNickName, userHead, userPassword, createTime, updateTime) values (3, '796542178', '����', '03_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
insert into user (id, userId, userNickName, userHead, userPassword, createTime, updateTime) values (4, '523088136', '�������', '04_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
insert into user (id, userId, userNickName, userHead, userPassword, createTime, updateTime) values (5, '123456001', '����', '05_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
insert into user (id, userId, userNickName, userHead, userPassword, createTime, updateTime) values (6, '123456002', '����', '06_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
insert into user (id, userId, userNickName, userHead, userPassword, createTime, updateTime) values (7, '123456003', 'Alexa', '07_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
insert into user (id, userId, userNickName, userHead, userPassword, createTime, updateTime) values (8, '123456004', 'С��', '08_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
insert into user (id, userId, userNickName, userHead, userPassword, createTime, updateTime) values (9, '123456005', '����', '09_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
insert into user (id, userId, userNickName, userHead, userPassword, createTime, updateTime) values (10, '123456006', '��С˧', '10_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
insert into user (id, userId, userNickName, userHead, userPassword, createTime, updateTime) values (11, '123456007', 'S.A.K', '11_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
insert into user (id, userId, userNickName, userHead, userPassword, createTime, updateTime) values (12, '123456008', '�����е���', '12_50', '123456', '2020-01-01 00:00:00', '2020-01-01 00:00:00');
