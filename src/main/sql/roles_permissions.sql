delete from role_permission;
delete from permission;

insert into role (id,role,description) values (1,"Superadmin","desc");
insert into role (id,role,description) values (2,"Admin","desc");
insert into role (id,role,description) values (3,"User","desc");
insert into role (id,role,description) values (4,"Guest","desc");
insert into permission (id,permission,description) values (1,"menu:user","desc");
insert into permission (id,permission,description) values (2,"menu:personal_info","desc");
insert into permission (id,permission,description) values (3,"menu:account","desc");
insert into permission (id,permission,description) values (4,"menu:take_test","desc");
insert into permission (id,permission,description) values (5,"menu:test_builder","desc");
insert into permission (id,permission,description) values (6,"menu:quick","desc");
insert into permission (id,permission,description) values (7,"menu:logs","desc");
insert into permission (id,permission,description) values (8,"menu:sessions","desc");
insert into permission (id,permission,description) values (9,"user:new","desc");
insert into permission (id,permission,description) values (10,"user:view","desc");
insert into permission (id,permission,description) values (11,"user:edit","desc");
insert into permission (id,permission,description) values (12,"user:enable","desc");
insert into permission (id,permission,description) values (13,"user:disable","desc");
insert into permission (id,permission,description) values (14,"user:unlock","desc");
insert into permission (id,permission,description) values (15,"user:list_superadmin","desc");
insert into permission (id,permission,description) values (16,"user:list_admin","desc");
insert into permission (id,permission,description) values (17,"user:list_user","desc");
insert into permission (id,permission,description) values (18,"user:list_guest","desc");
insert into role_permission (id_role,id_permission) values ( 1, 1);
insert into role_permission (id_role,id_permission) values ( 1, 2);
insert into role_permission (id_role,id_permission) values ( 1, 3);
insert into role_permission (id_role,id_permission) values ( 1, 4);
insert into role_permission (id_role,id_permission) values ( 1, 5);
insert into role_permission (id_role,id_permission) values ( 1, 6);
insert into role_permission (id_role,id_permission) values ( 1, 7);
insert into role_permission (id_role,id_permission) values ( 1, 8);
insert into role_permission (id_role,id_permission) values ( 1, 9);
insert into role_permission (id_role,id_permission) values ( 1, 10);
insert into role_permission (id_role,id_permission) values ( 1, 11);
insert into role_permission (id_role,id_permission) values ( 1, 12);
insert into role_permission (id_role,id_permission) values ( 1, 13);
insert into role_permission (id_role,id_permission) values ( 1, 14);
insert into role_permission (id_role,id_permission) values ( 1, 15);
insert into role_permission (id_role,id_permission) values ( 1, 16);
insert into role_permission (id_role,id_permission) values ( 1, 17);
insert into role_permission (id_role,id_permission) values ( 1, 18);
insert into role_permission (id_role,id_permission) values ( 2, 1);
insert into role_permission (id_role,id_permission) values ( 2, 2);
insert into role_permission (id_role,id_permission) values ( 2, 3);
insert into role_permission (id_role,id_permission) values ( 2, 4);
insert into role_permission (id_role,id_permission) values ( 2, 5);
insert into role_permission (id_role,id_permission) values ( 2, 6);
insert into role_permission (id_role,id_permission) values ( 2, 8);
insert into role_permission (id_role,id_permission) values ( 2, 9);
insert into role_permission (id_role,id_permission) values ( 2, 10);
insert into role_permission (id_role,id_permission) values ( 2, 11);
insert into role_permission (id_role,id_permission) values ( 2, 12);
insert into role_permission (id_role,id_permission) values ( 2, 13);
insert into role_permission (id_role,id_permission) values ( 2, 14);
insert into role_permission (id_role,id_permission) values ( 2, 16);
insert into role_permission (id_role,id_permission) values ( 2, 17);
insert into role_permission (id_role,id_permission) values ( 2, 18);
insert into role_permission (id_role,id_permission) values ( 3, 1);
insert into role_permission (id_role,id_permission) values ( 3, 2);
insert into role_permission (id_role,id_permission) values ( 3, 3);
insert into role_permission (id_role,id_permission) values ( 3, 4);
insert into role_permission (id_role,id_permission) values ( 3, 5);
insert into role_permission (id_role,id_permission) values ( 3, 6);
insert into role_permission (id_role,id_permission) values ( 3, 8);
insert into role_permission (id_role,id_permission) values ( 3, 18);
insert into role_permission (id_role,id_permission) values ( 4, 2);
insert into role_permission (id_role,id_permission) values ( 4, 4);



insert into `user`(`id`,`firstName`,`lastName`,`email`,`phone`,`username`,`password`,`enabled`,`failed_login_attempt_count`,`pwd_change_on_next_login`,`superadmin`) values (1,'Jane','Doe','jane@super.com','2125551212','wakka','$shiro1$SHA-256$500000$no8bF4L6ou/rwcuv0/7qNw==$qOuLZWUSVBw6KJ+acoajJuDPeeoYoU33bdiYpQivvvo=',1,0,0,1)
, (2,'Luigi','Bonacina','luigi.egidio.bonacina@ericsson.com','1234567890','admin','$shiro1$SHA-256$500000$yZnmkAQSFkP6MnlRAOisng==$LW6he2w5PcPhJC0CBLBtnH4QrkgxPb8Xt9/U0y0/dfc=',1,0,0,0)
, (4,'Stefano','Dominioni','stefano.dominioni@ericsson.com','1234567890','estedom','$shiro1$SHA-256$500000$+K2EnfNa93+KeACrVfA/LQ==$9/CqO/KPcP3hxiHVYoBLBYOjQ/9D37F139kTPc1GPR0=',1,0,0,0);


insert into user_role values (1,1) ;
insert into user_role values (2,2) ;
insert into user_role values (4,3) ;