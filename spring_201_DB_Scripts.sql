create database spring;

Create table employee(mid int not null auto_increment primary key, name varchar(75), projectName varchar(50));

insert into employee values(0,"Raj","iphone ui");

insert into employee values(0,"Santhosh","iphone ui");

insert into employee values(0,"Shivam","Samsung ui");

insert into employee values(0,"Praneeth","Xioami ui");

insert into employee values(0,"Sunil","Xioami ui");


Create table project(projectId int not null auto_increment primary key, projectName varchar(75));

insert into project values(0,'iphone ui');

insert into project values(0,'Samsung ui');

insert into project values(0,'Xioami ui');

Create table taskallocation(taskId int not null auto_increment primary key, taskDescription varchar(250),startDate date,endDate date, employeeName varchar(50),projectName varchar(50));
