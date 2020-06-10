create database li_0610 charset utf8mb4;
use li_0610;

create table persons (
    name varchar(20),
    height double,
    weight double
);

insert into persons (name, height, weight) values 
    ("张三", 172, 80),
    ("李四", 193, 93),
    ("王五", 163, 70),
    ("孙六", 166, 93),
    ("钱七", 213, 121);
	
mysql> select *from persons;
+------+--------+--------+
| name | height | weight |
+------+--------+--------+
| 张三 |    172 |     80 |
| 李四 |    193 |     93 |
| 王五 |    163 |     70 |
| 孙六 |    166 |     93 |
| 钱七 |    213 |    121 |
+------+--------+--------+
5 rows in set (0.00 sec)

-- count 的能力
select count(*) from persons;        
+----------+
| count(*) |
+----------+
|        5 |
+----------+
select count(1) from persons;                      
+----------+
| count(1) |
+----------+
|        5 |
+----------+
select count(distinct 1) from persons;             
+-------------------+
| count(distinct 1) |
+-------------------+
|                 1 |
+-------------------+
select count(name) from persons;                    
+-------------+
| count(name) |
+-------------+
|           5 |
+-------------+
select count(height) from persons;                
+---------------+
| count(height) |
+---------------+
|             5 |
+---------------+
select count(weight) from persons;                 
+---------------+
| count(weight) |
+---------------+
|             5 |
+---------------+
select count(distinct name) from persons;           
+----------------------+
| count(distinct name) |
+----------------------+
|                    5 |
+----------------------+
select count(distinct height) from persons;         
+------------------------+
| count(distinct height) |
+------------------------+
|                      5 |
+------------------------+
select count(distinct weight) from persons;         
+------------------------+
| count(distinct weight) |
+------------------------+
|                      4 |
+------------------------+

-- 多插入一个数据
insert into persons (name, height, weight) values ("赵四", null, null);

select count(*) from persons;                       -- 6
select count(1) from persons;                       -- 6
select count(distinct 1) from persons;              -- 1
select count(name) from persons;                    -- 6
select count(height) from persons;                  -- 5    null 不算
select count(weight) from persons;                  -- 5
select count(distinct name) from persons;           -- 6
select count(distinct height) from persons;         -- 5
select count(distinct weight) from persons;         -- 4

-- 如何知道 count 的结果呢？
-- 去掉 count 后得出的结果中
-- 再次去掉所有的 null
-- 剩下多少行，count 的结果就是多少

select max(*) from persons;                  -- 无法使用
select max(name) from persons;               
-- 根据字符串大小做比较的
+-----------+
| max(name) |
+-----------+
| 钱七      |
+-----------+
select max(height) from persons;             
+-------------+
| max(height) |
+-------------+
|         213 |
+-------------+
select max(weight) from persons;             
+-------------+
| max(weight) |
+-------------+
|         121 |
+-------------+
select max(distinct height) from persons;    
+----------------------+
| max(distinct height) |
+----------------------+
|                  213 |
+----------------------+
select max(distinct weight) from persons;   
+----------------------+
| max(distinct weight) |
+----------------------+
|                  121 |
+----------------------+

-- null 都不计算在内

-- 演示分组聚合
create table class_persons (
    class_name varchar(20),
    name varchar(20),
    height double,
    weight double
);

insert into class_persons (class_name, name, height, weight) values
    ("向日葵班", "林黛玉", 183, 92),
    ("向日葵班", "贾宝玉", 192, 95),
    ("向日葵班", "薛宝钗", 177, 83),

    ("玫瑰班", "唐三藏", 166, 82),
    ("玫瑰班", "孙悟空", 101, 63),
    ("玫瑰班", "猪悟能", 202, 400),

    ("月季班", "曹孟德", 173, 65),
    ("月季班", "刘玄德", 177, 58);

mysql> select *from class_persons;
+------------+--------+--------+--------+
| class_name | name   | height | weight |
+------------+--------+--------+--------+
| 向日葵班   | 林黛玉 |    183 |     92 |
| 向日葵班   | 贾宝玉 |    192 |     95 |
| 向日葵班   | 薛宝钗 |    177 |     83 |
| 玫瑰班     | 唐三藏 |    166 |     82 |
| 玫瑰班     | 孙悟空 |    101 |     63 |
| 玫瑰班     | 猪悟能 |    202 |    400 |
| 月季班     | 曹孟德 |    173 |     65 |
| 月季班     | 刘玄德 |    177 |     58 |
+------------+--------+--------+--------+
8 rows in set (0.00 sec)

-- 需要分组统计-分组聚合
-- 可以统计每个班各自有多少人 -- count
-- 可以统计每个班，各自的最高身高是多少 -- max
-- 可以统计每个班，各自的平均体重是多少 -- avg

-- group by 后边跟 分组的凭据（相同的值分到一组去聚合）
select class_name, count(*) from class_persons group by class_name; 
+------------+----------+
| class_name | count(*) |
+------------+----------+
| 向日葵班   |        3 |
| 月季班     |        2 |
| 玫瑰班     |        3 |
+------------+----------+
3 rows in set (0.00 sec)
select class_name, max(height) from class_persons group by class_name;
+------------+-------------+
| class_name | max(height) |
+------------+-------------+
| 向日葵班   |         192 |
| 月季班     |         177 |
| 玫瑰班     |         202 |
+------------+-------------+
3 rows in set (0.00 sec)
select class_name, avg(weight) from class_persons group by class_name;
+------------+--------------------+
| class_name | avg(weight)        |
+------------+--------------------+
| 向日葵班   |                 90 |
| 月季班     |               61.5 |
| 玫瑰班     | 181.66666666666666 |
+------------+--------------------+
3 rows in set (0.00 sec)

-- 多次分组
create table jobs (
    company_name varchar(20),
    group_name varchar(20),
    name varchar(20),
    salary double
);

insert into jobs (company_name, group_name, name, salary) values 
    ("腾讯", "A", "孙悟空", 80000),
    ("腾讯", "A", "六耳猕猴", 60000),
    ("腾讯", "A", "大马猴", 30000),

    ("腾讯", "B", "唐僧", 180000),
    ("腾讯", "B", "唐三藏", 180000),
    ("腾讯", "B", "大唐高僧", 30000),

    ("阿里", "A", "张无忌", 300),
    ("阿里", "A", "赵敏", 8000),
    ("阿里", "A", "周芷若", 100),

    ("阿里", "B", "郭靖", 4000),
    ("阿里", "B", "杨康", 5000),
    ("阿里", "B", "黄蓉", 6000),
    ("阿里", "B", "哈利波特", 80000);
mysql> select *from jobs;
+--------------+------------+----------+--------+
| company_name | group_name | name     | salary |
+--------------+------------+----------+--------+
| 腾讯         | A          | 孙悟空   |  80000 |
| 腾讯         | A          | 六耳猕猴 |  60000 |
| 腾讯         | A          | 大马猴   |  30000 |
| 腾讯         | B          | 唐僧     | 180000 |
| 腾讯         | B          | 唐三藏   | 180000 |
| 腾讯         | B          | 大唐高僧 |  30000 |
| 阿里         | A          | 张无忌   |    300 |
| 阿里         | A          | 赵敏     |   8000 |
| 阿里         | A          | 周芷若   |    100 |
| 阿里         | B          | 郭靖     |   4000 |
| 阿里         | B          | 杨康     |   5000 |
| 阿里         | B          | 黄蓉     |   6000 |
| 阿里         | B          | 哈利波特 |  80000 |
+--------------+------------+----------+--------+
13 rows in set (0.00 sec)

select company_name, count(*) from jobs group by company_name;
+--------------+----------+
| company_name | count(*) |
+--------------+----------+
| 腾讯         |        6 |
| 阿里         |        7 |
+--------------+----------+
2 rows in set (0.00 sec)
select group_name, count(*) from jobs group by group_name;
+------------+----------+
| group_name | count(*) |
+------------+----------+
| A          |        6 |
| B          |        7 |
+------------+----------+
2 rows in set (0.00 sec)
select company_name, group_name, count(*) from jobs group by company_name, group_name;
+--------------+------------+----------+
| company_name | group_name | count(*) |
+--------------+------------+----------+
| 腾讯         | A          |        3 |
| 腾讯         | B          |        3 |
| 阿里         | A          |        3 |
| 阿里         | B          |        4 |
+--------------+------------+----------+
4 rows in set (0.00 sec)
select company_name, group_name, count(*) from jobs group by group_name, company_name;
+--------------+------------+----------+
| company_name | group_name | count(*) |
+--------------+------------+----------+
| 腾讯         | A          |        3 |
| 阿里         | A          |        3 |
| 腾讯         | B          |        3 |
| 阿里         | B          |        4 |
+--------------+------------+----------+
4 rows in set (0.00 sec)

-- group by 的 select 后边跟的内容有限制
-- 只能出现两类：
-- 1. 聚合函数
-- 2. group by 后的分组凭证

select salary from jobs group by company_name;
 -- 分出了腾讯和阿里两个组，那 salary 应该放什么合适呢？salary 放谁的都不合适
+--------+
| salary |
+--------+
|  80000 |
|    300 |
+--------+
2 rows in set (0.00 sec)

-- having 是在 group 之后，再次对结果做筛选时使用
select company_name, group_name, sum(salary) from jobs group by group_name, company_name having sum(salary) > 10000;
+--------------+------------+-------------+
| company_name | group_name | sum(salary) |
+--------------+------------+-------------+
| 腾讯         | A          |      170000 |
| 腾讯         | B          |      390000 |
| 阿里         | B          |       95000 |
+--------------+------------+-------------+
3 rows in set (0.00 sec)

-- 联表查询
-- 没有条件的联表，结果是一个笛卡尔积
-- 有效数据，需要通过一些条件过滤
-- 例如这里的 文章表的作者 id = 用户表的.id
select 
    articles.id as aid, 
    title, 
    users.id as uid, 
    nickname 
from articles, users 
where articles.author_id = users.id;
+-----+----------+-----+----------+
| aid | title    | uid | nickname |
+-----+----------+-----+----------+
|   1 | 张三的歌 |   1 | zss      |
|   2 | 李四的歌 |   2 | lss      |
+-----+----------+-----+----------+
2 rows in set (0.00 sec)

-- 使用联表查询，完成以下功能
-- 指定文章 id，查询出文章的信息（不包括点赞和评论），需要包含作者的昵称
select
    a.id,
    a.title,
    u.nickname,
    a.content,
    a.published_at
from
    articles as a, users as u
where
    a.author_id = u.id
    and
    a.id = 2;
+----+----------+----------+------------+---------------------+
| id | title    | nickname | content    | published_at        |
+----+----------+----------+------------+---------------------+
|  2 | 李四的歌 | lss      | 大约在冬季 | 2020-06-10 15:26:08 |
+----+----------+----------+------------+---------------------+
1 row in set (0.00 sec)

-- 指定文章 id，查询出评论列表，需要包含评论者的昵称
select
    c.id,
    c.content,
    c.published_at,
    u.nickname
from
    comments as c, users as u
where
    c.user_id = u.id
    and
    c.article_id = 3
order by c.published_at desc;
