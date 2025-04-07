CREATE DATABASE college;

USE college;


INSERT INTO student VALUES(1,"Azrof",22);
INSERT INTO student VALUES(2,"Sonakshi",22);
INSERT INTO student VALUES(3,"Erica",22);


select * from student;

SHOW DATABASES;

CREATE DATABASE XYZ;

USE XYZ;

USE college;

CREATE TABLE employee (
id INT PRIMARY KEY,
name VARCHAR(50),
salary INT 
);
INSERT INTO employee (name,salary,id)
values
("adam",25000,1),
("bob",30000,2),
("casey",50000,3);
SHOW tables;
SHOW databases;
select * from employee;

INSERT INTO employee VALUES(4,"babe",3000);
SHOW DATABASES;
SHOW tables;
USE college;
Select * from student;
CREATE TABLE Teacher(
id int not null,
name VARCHAR(50) not null,
course_id int not null,
PRIMARY key(id)

);

DROP TABLE Teacher;

CREATE TABLE course(
student_id int,
course_id int,
teacher_id int,
foreign key(teacher_id) references Teacher(id),
foreign key(student_id) references student(id)

);
CREATE TABLE temp(
id int  ,
salary int default 500


);
INSERT into temp (id) values (1);
SHOW tables;
select * from temp;

CREATE TABLE city(
id INT ,
name VARCHAR(50),
age int,
PRIMARY KEY(id),
Constraint age_check CHECK(age>=18 and name='Delhi') 

);
INSERT INTO city (id,name,age) VALUES (1,'Delhi',18);
-- Create a sample database
CREATE DATABASE college;
USE college;
CREATE DATABASE school;
USE school;
-- Create a table
CREATE TABLE student (
    rollno INT PRIMARY KEY,
    name VARCHAR(50),
    marks INT NOT NULL,
    grade VARCHAR(1),
    city VARCHAR(20)
);

-- Insert sample data into the student table
INSERT INTO student (rollno, name, marks, grade, city)
VALUES
    (101, 'anil', 78, 'C', 'Pune'),
    (102, 'bhumika', 93, 'A', 'Mumbai'),
    (103, 'chetan', 85, 'B', 'Mumbai'),
    (104, 'dhruv', 96, 'A', 'Delhi'),
    (105, 'emanuel', 12, 'F', 'Delhi'),
    (106, 'farah', 82, 'B', 'Delhi');
    

    SELECT DISTINCT city FROM student;
    SELECT  * from student where marks between 80 and 90  or city='Mumbai';
    USE school;
    SELECT * from student LIMIT 3;
    SELECT * from student order by marks DESC LIMIT 3;
    SELECT grade,count(name)
    from student
    group by grade;
    
    SELECT city,avg(marks)
    from student
    group by city
    order by avg(marks) ASC;
    
    
    SELECT city ,count(name)
    from student
    where max(marks)>90
    Group by city;
    
    
    
    SELECT city,count(name)
    from student
    where grade='A'
    group by city
    having max(marks)>=93
    order by city ASC;
    
    
    SET sql_safe_updates=0;

