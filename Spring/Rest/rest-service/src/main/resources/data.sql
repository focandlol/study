insert into users(id, join_date,name,password,ssn) values(90001,now(),'User1','test111','707010-111112');
insert into users(id, join_date,name,password,ssn) values(90002,now(),'User2','test222','707010-111113');
insert into users(id, join_date,name,password,ssn) values(90003,now(),'User3','test333','707010-111114');

insert into post(description,user_id) values('my first post',90001);
insert into post(description,user_id) values('my sec post',90002);