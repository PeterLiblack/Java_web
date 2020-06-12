-- 建库
CREATE DATABASE li_0612 CHARSET utf8mb4;
USE li_0612;

-- 建立一个学生表 Student
CREATE TABLE Student (
	Sno VARCHAR(9) PRIMARY KEY,
	Sname VARCHAR(20) UNIQUE,
	Ssex VARCHAR(2),
	Sage SMALLINT,
	Sdept VARCHAR(20)
);

INSERT INTO Student(Sno,Sname,Ssex,Sage,Sdept) VALUES
	("202015121","李勇","男",20,"CS"),
	("202015122","刘晨","女",19,"CS"),
	("202015123","王敏","女",18,"MA"),
	("202015124","张立","男",19,"IS");
+-----------+-------+------+------+-------+
| Sno       | Sname | Ssex | Sage | Sdept |
+-----------+-------+------+------+-------+
| 202015121 | 李勇  | 男   |   20 | CS    |
| 202015122 | 刘晨  | 女   |   19 | CS    |
| 202015123 | 王敏  | 女   |   18 | MA    |
| 202015124 | 张立  | 男   |   19 | IS    |
+-----------+-------+------+------+-------+
4 rows in set (0.00 sec)

-- 建立一个课程表 Course
CREATE TABLE Course (
	Cno CHAR(4) PRIMARY KEY,
	Cname CHAR(40) NOT NULL,
	Cpno CHAR(4),
	Ccredit SMALLINT,
	FOREIGN KEY(Cpno) REFERENCES Course(Cno)
);
ERROR 1452 (23000): Cannot add or update a child row: 
a foreign key constraint fails (`li_0612`.`course`, 
CONSTRAINT `course_ibfk_1` FOREIGN KEY (`Cpno`) REFERENCES `course` (`Cno`))

INSERT INTO Course(Cno,Cname,Cpno,Ccredit) VALUES
	("1","数据库","5",4),
	("2","数学",NULL,2),
	("3","信息系统","1",4),
	("4","操作系统","6",3),
	("5","数据结构","7",4),
	("6","数据处理",NULL,2),
	("7","PASCAL语言","6",4);
+-----+------------+------+---------+
| Cno | Cname      | Cpno | Ccredit |
+-----+------------+------+---------+
| 1   | 数据库     | 5    |       4 |
| 2   | 数学       | NULL |       2 |
| 3   | 信息系统   | 1    |       4 |
| 4   | 操作系统   | 6    |       3 |
| 5   | 数据结构   | 7    |       4 |
| 6   | 数据处理   | NULL |       2 |
| 7   | PASCAL语言 | 6    |       4 |
+-----+------------+------+---------+
7 rows in set (0.00 sec)
	
-- 建立学生选课表 SC
CREATE TABLE SC (
	Sno VARCHAR(9),
	Cno VARCHAR(4),
	Grade SMALLINT,
	PRIMARY KEY(Sno,Cno),
	FOREIGN KEY(Sno) REFERENCES Student(Sno),
	FOREIGN KEY(Cno) REFERENCES Course（Cno)
);

INSERT INTO SC(Sno,Cno,Grade) VALUES
	("202015121","1",92),
	("202015121","2",85),
	("202015121","3",88),
	("202015122","2",90),
	("202015122","3",80);
+-----------+-----+-------+
| Sno       | Cno | Grade |
+-----------+-----+-------+
| 202015121 | 1   |    92 |
| 202015121 | 2   |    85 |
| 202015121 | 3   |    88 |
| 202015122 | 2   |    90 |
| 202015122 | 3   |    80 |
+-----------+-----+-------+
5 rows in set (0.00 sec)

-- 修改基本表
-- 向Student表增加“入学时间”列，其数据类型为日期型
ALTER TABLE Student ADD S_entrance DATE;
+-----------+-------+------+------+-------+------------+
| Sno       | Sname | Ssex | Sage | Sdept | S_entrance |
+-----------+-------+------+------+-------+------------+
| 202015121 | 李勇  | 男   |   20 | CS    | NULL       |
| 202015122 | 刘晨  | 女   |   19 | CS    | NULL       |
| 202015123 | 王敏  | 女   |   18 | MA    | NULL       |
| 202015124 | 张立  | 男   |   19 | IS    | NULL       |
+-----------+-------+------+------+-------+------------+
4 rows in set (0.00 sec)

-- 将年龄的数据类型由字符型改成整数
ALTER TABLE Student ALTER COLUMN Sage INT;

-- 增加课程名称必须取唯一值的约束条件
ALTER TABLE Course ADD UNIQUE(Cname);

-- 删除Student表
DROP TABLE Student CASCADE;

-- 索引
-- Student 表按学号升序唯一索引
-- Course 表按课程号升序唯一索引
-- SC 表按学号升序和课程号降序唯一索引
CREATE UNIQUE INDEX Stusno ON Student(Sno);
CREATE UNIQUE INDEX Coucno ON Course(Cno);
CREATE UNIQUE INDEX SCno ON SC(Sno ASC,Cno DESC);

-- 修改索引
-- 将SC表的SCno索引名改为SCSno
ALTER INDEX SCno RENAME TO SCSno;
ERROR 1064 (42000): You have an error in your SQL syntax; 
check the manual that corresponds to your MySQL server 
version for the right syntax to use near 'INDEX SCno RENAME TO SCSno' at line 1

-- 删除Student表的Stusname索引
DROP INDEX Stusname;

-- 数据查询
-- 单表查询
-- 查询全体学生的学号和姓名
SELECT Sno,Sname FROM Student;
+-----------+-------+
| Sno       | Sname |
+-----------+-------+
| 202015122 | 刘晨  |
| 202015124 | 张立  |
| 202015121 | 李勇  |
| 202015123 | 王敏  |
+-----------+-------+
4 rows in set (0.00 sec)

-- 查询全体学生的姓名，学号，所在系
SELECT Sname,Sno,Sdept FROM Student;
+-------+-----------+-------+
| Sname | Sno       | Sdept |
+-------+-----------+-------+
| 李勇  | 202015121 | CS    |
| 刘晨  | 202015122 | CS    |
| 王敏  | 202015123 | MA    |
| 张立  | 202015124 | IS    |
+-------+-----------+-------+
4 rows in set (0.00 sec)

-- 查询全体学生的详细记录
SELECT * FROM Student;
+-----------+-------+------+------+-------+------------+
| Sno       | Sname | Ssex | Sage | Sdept | S_entrance |
+-----------+-------+------+------+-------+------------+
| 202015121 | 李勇  | 男   |   20 | CS    | NULL       |
| 202015122 | 刘晨  | 女   |   19 | CS    | NULL       |
| 202015123 | 王敏  | 女   |   18 | MA    | NULL       |
| 202015124 | 张立  | 男   |   19 | IS    | NULL       |
+-----------+-------+------+------+-------+------------+
4 rows in set (0.00 sec)

-- 查询全体学生的姓名及其出生年份
SELECT Sname,2020-Sage FROM Student;
+-------+-----------+
| Sname | 2020-Sage |
+-------+-----------+
| 李勇  |      2000 |
| 刘晨  |      2001 |
| 王敏  |      2002 |
| 张立  |      2001 |
+-------+-----------+
4 rows in set (0.00 sec)

-- 查询全体学生的姓名、出生年份和所在院系，要求用小写字母表示系名
SELECT Sname,'Year of Birth:',2020-Sage,LOWER(Sdept) FROM Student;
+-------+----------------+-----------+--------------+
| Sname | Year of Birth: | 2020-Sage | LOWER(Sdept) |
+-------+----------------+-----------+--------------+
| 李勇  | Year of Birth: |      2000 | cs           |
| 刘晨  | Year of Birth: |      2001 | cs           |
| 王敏  | Year of Birth: |      2002 | ma           |
| 张立  | Year of Birth: |      2001 | is           |
+-------+----------------+-----------+--------------+
4 rows in set (0.00 sec)
-- 加上别名
SELECT Sname NAME,'Year of Birth:' BIRTH,2020-Sage BIRTHDAY,LOWER(Sdept) DEPARTMENT FROM Student;
+------+----------------+----------+------------+
| NAME | BIRTH          | BIRTHDAY | DEPARTMENT |
+------+----------------+----------+------------+
| 李勇 | Year of Birth: |     2000 | cs         |
| 刘晨 | Year of Birth: |     2001 | cs         |
| 王敏 | Year of Birth: |     2002 | ma         |
| 张立 | Year of Birth: |     2001 | is         |
+------+----------------+----------+------------+
4 rows in set (0.00 sec)

-- 查询选修了课程的学生的学号
SELECT Sno FROM SC;
+-----------+
| Sno       |
+-----------+
| 202015121 |
| 202015121 |
| 202015121 |
| 202015122 |
| 202015122 |
+-----------+
5 rows in set (0.00 sec)
-- 去重
SELECT DISTINCT Sno FROM SC;
+-----------+
| Sno       |
+-----------+
| 202015121 |
| 202015122 |
+-----------+
2 rows in set (0.00 sec)

-- 查询计算机科学系全体学生的名单
SELECT Sname FROM Student WHERE Sdept='CS';
+-------+
| Sname |
+-------+
| 李勇  |
| 刘晨  |
+-------+
2 rows in set (0.00 sec)

-- 查询所有年龄在20岁以下的学生姓名及年龄
SELECT Sname,Sage FROM Student WHERE Sage<20;
+-------+------+
| Sname | Sage |
+-------+------+
| 刘晨  |   19 |
| 王敏  |   18 |
| 张立  |   19 |
+-------+------+
3 rows in set (0.00 sec)

-- 查询考试成绩不及格的学生的学号
SELECT DISTINCT Sno FROM SC WHERE Grade<60;
Empty set (0.00 sec)

-- 查询年龄在20~23岁（包含）之间的学生姓名、系别和年龄
SELECT Sname,Sdept,Sage FROM Student WHERE Sage BETWEEN 20 and 23;
+-------+-------+------+
| Sname | Sdept | Sage |
+-------+-------+------+
| 李勇  | CS    |   20 |
+-------+-------+------+
1 row in set (0.00 sec)
-- 查询年龄不在20~23岁之间的学生姓名、系别和年龄
SELECT Sname,Sdept,Sage FROM Student WHERE Sage NOT BETWEEN 20 and 23;
+-------+-------+------+
| Sname | Sdept | Sage |
+-------+-------+------+
| 刘晨  | CS    |   19 |
| 王敏  | MA    |   18 |
| 张立  | IS    |   19 |
+-------+-------+------+
3 rows in set (0.00 sec)

-- 查询计算机科学系（CS）、数学系（MA）和信息系（IS）学生姓名和性别
SELECT Sname,Ssex FROM Student WHERE Sdept IN('CS','MA','IS');
+-------+------+
| Sname | Ssex |
+-------+------+
| 李勇  | 男   |
| 刘晨  | 女   |
| 王敏  | 女   |
| 张立  | 男   |
+-------+------+
4 rows in set (0.00 sec)
-- 查询不是计算机科学系（CS）、数学系（MA）和信息系（IS）学生姓名和性别
SELECT Sname,Ssex FROM Student WHERE Sdept NOT IN('CS','MA','IS');
Empty set (0.00 sec)

-- 查询学号为202015121的学生的详细情况
SELECT * FROM Student WHERE Sno LIKE '202015121';
SELECT * FROM Student WHERE Sno='202015121';
+-----------+-------+------+------+-------+------------+
| Sno       | Sname | Ssex | Sage | Sdept | S_entrance |
+-----------+-------+------+------+-------+------------+
| 202015121 | 李勇  | 男   |   20 | CS    | NULL       |
+-----------+-------+------+------+-------+------------+
1 row in set (0.00 sec)

-- 查询所有姓刘的学生的姓名、学号和性别
SELECT Sname,Sno,Ssex FROM Student WHERE Sname LIKE '刘%';
+-------+-----------+------+
| Sname | Sno       | Ssex |
+-------+-----------+------+
| 刘晨  | 202015122 | 女   |
+-------+-----------+------+
1 row in set (0.00 sec)

-- 查询姓“欧阳”且全名三个汉字的学生的姓名
SELECT Sname FROM Student WHERE Sname LIKE '欧阳_';
Empty set (0.00 sec)

-- 查询名字中第二个字为“阳”的学生的姓名和学号
SELECT Sname,Sno FROM Student WHERE Sname LIKE '_阳%';

-- 查询所有不姓刘的学生的姓名、学号和性别
SELECT Sname,Sno,Ssex FROM Student WHERE Sname NOT LIKE '刘%';
+-------+-----------+------+
| Sname | Sno       | Ssex |
+-------+-----------+------+
| 李勇  | 202015121 | 男   |
| 王敏  | 202015123 | 女   |
| 张立  | 202015124 | 男   |
+-------+-----------+------+
3 rows in set (0.00 sec)

-- 查询选修课程未考试的学生的学号和课程号
SELECT Sno,Cno FROM SC WHERE Grade IS NULL;

-- 查询所有有成绩的学生学号和课程号
SELECT Sno,Cno FROM SC WHERE Grade IS NOT NULL;
+-----------+-----+
| Sno       | Cno |
+-----------+-----+
| 202015121 | 1   |
| 202015121 | 2   |
| 202015121 | 3   |
| 202015122 | 2   |
| 202015122 | 3   |
+-----------+-----+
5 rows in set (0.00 sec)

-- 查询计算机科学系年龄在20岁以下的学生姓名
SELECT Sname FROM Student WHERE Sdept='CS' AND Sage<20;
+-------+
| Sname |
+-------+
| 刘晨  |
+-------+
1 row in set (0.00 sec)

-- ORDER BY
-- 查询选修了3号课程的学生的学号及其成绩，查询结果按降序排列
SELECT Sno,Grade FROM SC WHERE Cno='3' ORDER BY Grade DESC;
+-----------+-------+
| Sno       | Grade |
+-----------+-------+
| 202015121 |    88 |
| 202015122 |    80 |
+-----------+-------+
2 rows in set (0.00 sec)

-- 查询全体学生情况，查询结果按所在系的系号升序，同一系中的学生按年龄降序
SELECT * FROM Student ORDER BY Sdept,Sage DESC;
+-----------+-------+------+------+-------+------------+
| Sno       | Sname | Ssex | Sage | Sdept | S_entrance |
+-----------+-------+------+------+-------+------------+
| 202015121 | 李勇  | 男   |   20 | CS    | NULL       |
| 202015122 | 刘晨  | 女   |   19 | CS    | NULL       |
| 202015124 | 张立  | 男   |   19 | IS    | NULL       |
| 202015123 | 王敏  | 女   |   18 | MA    | NULL       |
+-----------+-------+------+------+-------+------------+
4 rows in set (0.00 sec)

-- 聚集函数
-- 查询学生总人数
SELECT COUNT(*) FROM Student;
+----------+
| COUNT(*) |
+----------+
|        4 |
+----------+
1 row in set (0.00 sec)

-- 查询选修了课程的学生人数
SELECT COUNT(DISTINCT Sno) FROM SC;
+---------------------+
| COUNT(DISTINCT Sno) |
+---------------------+
|                   2 |
+---------------------+
1 row in set (0.00 sec)

-- 计算选修1号课程学生的平均成绩
SELECT AVG(Grade) FROM SC WHERE Cno='1';
+------------+
| AVG(Grade) |
+------------+
|    92.0000 |
+------------+
1 row in set (0.00 sec)

-- 查询1号课程的学生最高分数
SELECT MAX(Grade) FROM SC WHERE Cno='1';
+------------+
| MAX(Grade) |
+------------+
|         92 |
+------------+
1 row in set (0.00 sec)

-- 查询学生202015121 选修课程总学分数
SELECT SUM(Ccredit) FROM SC,Course WHERE Sno='202015121' AND SC.Cno=Course.Cno;
+--------------+
| SUM(Ccredit) |
+--------------+
|           10 |
+--------------+
1 row in set (0.00 sec)

-- GROUP BY 
-- 求各个课程号及相应的选课人数
SELECT Cno,COUNT(Sno) FROM SC GROUP BY Cno;
+-----+------------+
| Cno | COUNT(Sno) |
+-----+------------+
| 1   |          1 |
| 2   |          2 |
| 3   |          2 |
+-----+------------+
3 rows in set (0.00 sec)

-- 查询选修了三门以上课程的学生学号
SELECT Sno FROM SC GROUP BY Sno HAVING COUNT(*)>3;
Empty set (0.00 sec)

-- 连接查询
-- 查询每个学生及其选修课程的情况
SELECT Student.*,SC.* FROM Student,SC WHERE Student.Sno=SC.Sno;
+-----------+-------+------+------+-------+------------+-----------+-----+-------+
| Sno       | Sname | Ssex | Sage | Sdept | S_entrance | Sno       | Cno | Grade |
+-----------+-------+------+------+-------+------------+-----------+-----+-------+
| 202015121 | 李勇  | 男   |   20 | CS    | NULL       | 202015121 | 1   |    92 |
| 202015121 | 李勇  | 男   |   20 | CS    | NULL       | 202015121 | 2   |    85 |
| 202015121 | 李勇  | 男   |   20 | CS    | NULL       | 202015121 | 3   |    88 |
| 202015122 | 刘晨  | 女   |   19 | CS    | NULL       | 202015122 | 2   |    90 |
| 202015122 | 刘晨  | 女   |   19 | CS    | NULL       | 202015122 | 3   |    80 |
+-----------+-------+------+------+-------+------------+-----------+-----+-------+
5 rows in set (0.00 sec)

-- 自然连接
SELECT Student.Sno,Sname,Ssex,Sage,Sdept,Cno,Grade FROM Student,SC WHERE Student.Sno=SC.Sno;
+-----------+-------+------+------+-------+-----+-------+
| Sno       | Sname | Ssex | Sage | Sdept | Cno | Grade |
+-----------+-------+------+------+-------+-----+-------+
| 202015121 | 李勇  | 男   |   20 | CS    | 1   |    92 |
| 202015121 | 李勇  | 男   |   20 | CS    | 2   |    85 |
| 202015121 | 李勇  | 男   |   20 | CS    | 3   |    88 |
| 202015122 | 刘晨  | 女   |   19 | CS    | 2   |    90 |
| 202015122 | 刘晨  | 女   |   19 | CS    | 3   |    80 |
+-----------+-------+------+------+-------+-----+-------+
5 rows in set (0.00 sec)

-- 自身连接
-- 查询每一门课的间接先修课
SELECT FIRST.Cno,SECOND.Cpno FROM Course FIRST,Course SECOND WHERE FIRST.Cpno=SECOND.Cno;
+-----+------+
| Cno | Cpno |
+-----+------+
| 1   | 7    |
| 3   | 5    |
| 4   | NULL |
| 5   | 6    |
| 7   | NULL |
+-----+------+
5 rows in set (0.00 sec)

-- 外连接
SELECT Student.Sno,Sname,Ssex,Sage,Sdept,Cno,Grade FROM Student LEFT OUTER JOIN SC ON(Student.Sno=SC.Sno);
+-----------+-------+------+------+-------+------+-------+
| Sno       | Sname | Ssex | Sage | Sdept | Cno  | Grade |
+-----------+-------+------+------+-------+------+-------+
| 202015121 | 李勇  | 男   |   20 | CS    | 1    |    92 |
| 202015121 | 李勇  | 男   |   20 | CS    | 2    |    85 |
| 202015121 | 李勇  | 男   |   20 | CS    | 3    |    88 |
| 202015122 | 刘晨  | 女   |   19 | CS    | 2    |    90 |
| 202015122 | 刘晨  | 女   |   19 | CS    | 3    |    80 |
| 202015123 | 王敏  | 女   |   18 | MA    | NULL |  NULL |
| 202015124 | 张立  | 男   |   19 | IS    | NULL |  NULL |
+-----------+-------+------+------+-------+------+-------+
7 rows in set (0.00 sec)

-- 多表连接
-- 查询每个学生的学号、姓名、选修的课程名及成绩
SELECT Student.Sno,Sname,Cname,Grade FROM Student,SC,Course WHERE Student.Sno=SC.Sno AND SC.Cno=Course.Cno;
+-----------+-------+----------+-------+
| Sno       | Sname | Cname    | Grade |
+-----------+-------+----------+-------+
| 202015121 | 李勇  | 数据库   |    92 |
| 202015121 | 李勇  | 数学     |    85 |
| 202015121 | 李勇  | 信息系统 |    88 |
| 202015122 | 刘晨  | 数学     |    90 |
| 202015122 | 刘晨  | 信息系统 |    80 |
+-----------+-------+----------+-------+
5 rows in set (0.00 sec)

-- 嵌套查询 
-- 带有IN谓词的子查询
-- 查询与“刘晨”在同一个系学习的学生
-- 分步查询
SELECT Sdept FROM Student WHERE Sname='刘晨';
+-------+
| Sdept |
+-------+
| CS    |
+-------+
1 row in set (0.00 sec)
SELECT Sno,Sname,Sdept FROM Student WHERE Sdept='CS';
+-----------+-------+-------+
| Sno       | Sname | Sdept |
+-----------+-------+-------+
| 202015121 | 李勇  | CS    |
| 202015122 | 刘晨  | CS    |
+-----------+-------+-------+
2 rows in set (0.00 sec)
-- 子查询
SELECT Sno,Sname,Sdept FROM Student WHERE Sdept IN (
	SELECT Sdept FROM Student WHERE Sname='刘晨');
+-----------+-------+-------+
| Sno       | Sname | Sdept |
+-----------+-------+-------+
| 202015121 | 李勇  | CS    |
| 202015122 | 刘晨  | CS    |
+-----------+-------+-------+
2 rows in set (0.00 sec)

-- 查询选修了课程名为“信息系统”的学生学号和姓名
SELECT Sno,Sname FROM Student WHERE Sno IN(
	SELECT Sno FROM SC WHERE Cno IN(
		SELECT Cno FROM Course WHERE Cname='信息系统'));
+-----------+-------+
| Sno       | Sname |
+-----------+-------+
| 202015121 | 李勇  |
| 202015122 | 刘晨  |
+-----------+-------+
2 rows in set (0.00 sec)

-- 带有比较运算符的子查询
-- 找出每个学生超过他自己选修课程平均成绩的课程号
SELECT Sno,Cno FROM SC x WHERE Grade>=(
	SELECT AVG(Grade) FROM SC y WHERE y.Sno=x.Sno);
+-----------+-----+
| Sno       | Cno |
+-----------+-----+
| 202015121 | 1   |
| 202015122 | 2   |
+-----------+-----+
2 rows in set (0.00 sec)

-- 带有ANY（SOME）或ALL谓词的子查询
-- 查询非计算机科学系中比计算机科学系任意一个学生年龄小的学生姓名和年龄
SELECT Sname,Sage FROM Student WHERE Sage<ANY(
	SELECT Sage FROM Student WHERE Sdept='CS') AND Sdept<>'CS';
+-------+------+
| Sname | Sage |
+-------+------+
| 王敏  |   18 |
| 张立  |   19 |
+-------+------+
2 rows in set (0.00 sec)

-- 查询非计算机科学系中比计算机科学系所有学生年龄小的学生姓名和年龄
SELECT Sname,Sage FROM Student WHERE Sage<ALL(
	SELECT Sage FROM Student WHERE Sdept='CS') AND Sdept<>'CS';
+-------+------+
| Sname | Sage |
+-------+------+
| 王敏  |   18 |
+-------+------+
1 row in set (0.00 sec)

-- 带有EXISTS谓词的子查询
-- 查询所有选修了1号课程学生姓名
SELECT Sname FROM Student WHERE EXISTS(
	SELECT * FROM SC WHERE Sno=Student.Sno AND Cno='1');
+-------+
| Sname |
+-------+
| 李勇  |
+-------+
1 row in set (0.00 sec)

-- 查询没有选修1号课程学生姓名
SELECT Sname FROM Student WHERE NOT EXISTS(
	SELECT * FROM SC WHERE Sno=Student.Sno AND Cno='1');
+-------+
| Sname |
+-------+
| 刘晨  |
| 张立  |
| 王敏  |
+-------+
3 rows in set (0.00 sec)

-- 查询选修了全部课程的学生姓名
SELECT Sname FROM Student WHERE  NOT EXISTS(
	SELECT * FROM Course WHERE NOT EXISTS(
		SELECT * FROM SC WHERE Sno=Student.Sno AND Cno=Course.Cno));
Empty set (0.00 sec)

-- 查询至少选修了学生202015122选修的全部课程的学生号码
SELECT DISTINCT Sno FROM SC SCX WHERE NOT EXISTS(
	SELECT * FROM SC SCY WHERE SCY.Sno='202015122'AND NOT EXISTS(
		SELECT * FROM SC SCZ WHERE SCZ WHERE SCZ.Sno=SCX.Sno AND SCZ.Cno=SCY.Cno));
ERROR 1064 (42000): You have an error in your SQL syntax; 
check the manual that corresponds to your MySQL server version for 
the right syntax to use near 'WHERE SCZ.Sno=SCX.Sno AND SCZ.Cno=SCY.Cno))' at line 3	

-- 集合查询
-- 查询计算机科学系的学生及年龄不大于19岁的学生
SELECT * FROM Student WHERE Sdept='CS' UNION
	SELECT * FROM Student WHERE Sage<=19;
+-----------+-------+------+------+-------+------------+
| Sno       | Sname | Ssex | Sage | Sdept | S_entrance |
+-----------+-------+------+------+-------+------------+
| 202015121 | 李勇  | 男   |   20 | CS    | NULL       |
| 202015122 | 刘晨  | 女   |   19 | CS    | NULL       |
| 202015123 | 王敏  | 女   |   18 | MA    | NULL       |
| 202015124 | 张立  | 男   |   19 | IS    | NULL       |
+-----------+-------+------+------+-------+------------+
4 rows in set (0.00 sec)

-- 查询计算机科学系的学生与年龄不大于19岁的学生的交集
SELECT * FROM Student WHERE Sdept='CS' INTERSECT
	SELECT * FROM Student WHERE Sage<=19;
ERROR 1064 (42000): You have an error in your SQL syntax; 
check the manual that corresponds to your MySQL server version for 
the right syntax to use near 'INTERSECT
SELECT * FROM Student WHERE Sage<=19' at line 1

-- 查询计算机科学系的学生与年龄不大于19岁的学生的差集
SELECT * FROM Student WHERE Sdept='CS' EXCEPT
	SELECT * FROM Student WHERE Sage<=19;
	
-- 基于派生表的查询
-- 查询所有选修了1号课程学生姓名
SELECT Sname FROM Student,(
	SELECT Sno FROM SC WHERE Cno='1') AS SC1 WHERE Student.Sno=SC1.Sno;
+-------+
| Sname |
+-------+
| 李勇  |
+-------+
1 row in set (0.00 sec)


-- 数据更新
-- 插入数据
-- 插入一个新学生元组
INSERT INTO Student(Sno,Sname,Ssex,Sdept,Sage) VALUES
	('202015125','陈东','男','IS',18);

-- 将学生信息插入Student表中
INSERT INTO Student VALUES('202015126','张明','男',18,'CS');
+-----------+-------+------+------+-------+------------+
| Sno       | Sname | Ssex | Sage | Sdept | S_entrance |
+-----------+-------+------+------+-------+------------+
| 202015121 | 李勇  | 男   |   20 | CS    | NULL       |
| 202015122 | 刘晨  | 女   |   19 | CS    | NULL       |
| 202015123 | 王敏  | 女   |   18 | MA    | NULL       |
| 202015124 | 张立  | 男   |   19 | IS    | NULL       |
| 202015125 | 陈东  | 男   |   18 | IS    | NULL       |
| 202015126 | 张明  | 男   |   18 | CS    | NULL       |
+-----------+-------+------+------+-------+------------+
6 rows in set (0.00 sec)

-- 插入一条选课记录（‘202015125’，‘1’）
INSERT INTO SC(Sno,Cno) VALUES('202015125','1');
INSERT INTO SC VALUES('202015125','1',NULL);
+-----------+-----+-------+
| Sno       | Cno | Grade |
+-----------+-----+-------+
| 202015121 | 1   |    92 |
| 202015121 | 2   |    85 |
| 202015121 | 3   |    88 |
| 202015122 | 2   |    90 |
| 202015122 | 3   |    80 |
| 202015125 | 1   |  NULL |
+-----------+-----+-------+
6 rows in set (0.00 sec)

-- 插入子查询结果
-- 对每一个系，求学生的平均年龄，并把结果放入数据库
CREATE TABLE Dept_age(
	Sdept CHAR(15),
	Avg_age SMALLINT);

INSERT INTO Dept_age(Sdept,Avg_age)
	SELECT Sdept,AVG(Sage) FROM Student GROUP BY Sdept;
+-------+---------+
| Sdept | Avg_age |
+-------+---------+
| CS    |      19 |
| IS    |      19 |
| MA    |      18 |
+-------+---------+
3 rows in set (0.00 sec)

-- 修改数据
-- 修改某一个元组的值
-- 将学生202015121的年龄改为22岁
UPDATE Student SET Sage=22 WHERE Sno='202015121';
+-----------+-------+------+------+-------+------------+
| Sno       | Sname | Ssex | Sage | Sdept | S_entrance |
+-----------+-------+------+------+-------+------------+
| 202015121 | 李勇  | 男   |   22 | CS    | NULL       |
| 202015122 | 刘晨  | 女   |   19 | CS    | NULL       |
| 202015123 | 王敏  | 女   |   18 | MA    | NULL       |
| 202015124 | 张立  | 男   |   19 | IS    | NULL       |
| 202015125 | 陈东  | 男   |   18 | IS    | NULL       |
| 202015126 | 张明  | 男   |   18 | CS    | NULL       |
+-----------+-------+------+------+-------+------------+
6 rows in set (0.00 sec)

-- 将所有学生的年龄增加一岁
UPDATE Student SET Sage=Sage+1;
+-----------+-------+------+------+-------+------------+
| Sno       | Sname | Ssex | Sage | Sdept | S_entrance |
+-----------+-------+------+------+-------+------------+
| 202015121 | 李勇  | 男   |   23 | CS    | NULL       |
| 202015122 | 刘晨  | 女   |   20 | CS    | NULL       |
| 202015123 | 王敏  | 女   |   19 | MA    | NULL       |
| 202015124 | 张立  | 男   |   20 | IS    | NULL       |
| 202015125 | 陈东  | 男   |   19 | IS    | NULL       |
| 202015126 | 张明  | 男   |   19 | CS    | NULL       |
+-----------+-------+------+------+-------+------------+
6 rows in set (0.00 sec)

-- 带子查询的修改语句
-- 将计算机科学系全体学生的成绩置0；
UPDATE SC SET Grade=0 WHERE Sno IN(
	SELECT Sno FROM Student FROM Student WHERE Sdept='CS');
ERROR 1064 (42000): You have an error in your SQL syntax; 
check the manual that corresponds to your MySQL server version for 
the right syntax to use near 'FROM Student WHERE Sdept='CS')' at line 2

-- 删除数据
-- 删除学号202015125的学生记录
DELETE FROM Student WHERE Sno='202015125';

-- 删除所有学生选课记录
DELETE FROM SC;

-- 带子查询的删除语句
-- 删除计算机科学系所有学生的选课记录
DELETE FROM SC WHERE Sno IN(
	SELECT Sno FROM Student WHERE Sdept='CS');