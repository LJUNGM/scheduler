create table calendar (
                          id int auto_increment primary key,
                          userid varchar(100) not null COMMENT '작성자',
                          task varchar(100) not null COMMENT '일정',
                          password varchar(50) not null COMMENT '비밀번호',
                          createDate timestamp default current_timestamp,
                          updateDate timestamp default current_timestamp on update current_timestamp
);