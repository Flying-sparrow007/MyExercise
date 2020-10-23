# 事务(transaction)
/*
 什么是事务?
 是为了完成某个业务而执行的一条或者多条SQL语句的最小逻辑工作单元,保证数据的完整性
 事务具有以下四个特性:
 原子性(Atomicity):事务是一个完整的操作,事务的各部分操作是不可分开的(原子性),要么都执行,要么都不执行
 一致性(Consistency):当事务完成时,数据必须处于一致状态
 隔离性(Isolation):并发事务之间彼此隔离,独立,它不应该以任何方式依赖于或影响其他事务
 持久性(Durability):事务完成后,它对数据库的修改被永久保存
*/
CREATE TABLE bank(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(10),money DOUBLE);
INSERT INTO bank VALUES (NULL,"王云龙",1000),(NULL,"李浩哲",500);
SELECT * FROM bank;
# 查询自动提交的状态 1或者ON 则为自动提交 0或OFF则为手动提交
SHOW VARIABLES LIKE "autocommit";
# 2.设置提交状态
SET autocommit = 0;# 设置提交状态为手动
/*
 A数据库服务端(支付一方),B数据库服务端(接收一方)钱没收到,也就是没有
 提交到数据库中,这是必须需要事务来处理该问题
*/
UPDATE bank SET money = money - 200 WHERE NAME = "王云龙";
UPDATE bank SET money = money + 200 WHERE NAME = "李浩哲";
# 以上两条SQL语句实在事务中执行的
COMMIT;
SET autocommit = 1;# 设置提交状态为自动
# 事务的特性: 一致性,隔离性,原子性,持久性

SET autocommit = 0;
# rollback
INSERT INTO bank VALUES (NULL,"高轲",500),(NULL,"丁静君",500),(NULL,"张欣如",500),(NULL,"闫薇",500),(NULL,"坤哥",500);
COMMIT;
# 在这里假设插入一条数据发生故障,那么回滚数据
INSERT INTO bank VALUES (NULL,"外来人",1500);
ROLLBACK;

# 一条删除语句
DELETE FROM bank WHERE money IN(500,1500);

# 约束:约束就是给表字段添加的限制条件
# not null
# 添加非空约束的字段值不能为null
CREATE TABLE mytable1(id INT PRIMARY KEY AUTO_INCREMENT,ename VARCHAR(10) NOT NULL);
INSERT INTO mytable1 VALUES (NULL,NULL);# 插入失败,ename字段不能为NULL
# unique:唯一(不能重复)
CREATE TABLE mytable2 (id INT PRIMARY KEY AUTO_INCREMENT,ename VARCHAR(10) UNIQUE NOT NULL);
INSERT INTO mytable2 VALUES (NULL,"测试1");
INSERT INTO mytable2 VALUES (NULL,"测试1");# 插入失败,ename字段不允许插入重复的值
# 主键约束:primary key 添加了主键约束的字段,值不能为null也不能重复.
# 一个表只能由一个主键
CREATE TABLE mytable3 (id INT PRIMARY KEY);
INSERT INTO mytable3 VALUES (1);
INSERT INTO mytable3 VALUES (1);# 不能重复
INSERT INTO mytable3 VALUES (NULL);# 不能为null
# 删除主键索引
ALTER TABLE mytable3 DROP PRIMARY KEY;
/*
 外键约束:外键约束是保证一个或两个表之间的参照完整性,保持数据一致性
 表的外键可以是另一张表的主键(也可以是唯一约束),外键有重复,可以为空
 references
*/
# 创建外键约束
# 年级表
CREATE TABLE classes(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(10));
# 学生表
CREATE TABLE student(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(10),class_id INT REFERENCES classes(id));

# 约束方式二:
# CONSTRAINT fk_stud_id 
CREATE TABLE student2(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(10),classes_id INT,CONSTRAINT fk_classes_id FOREIGN KEY(classes_id) REFERENCES classes(id));

/*
 索引:用来加快查询的技术很多,其中最重要的就是索引(index)
 1.通常索引能够快速提高查询速度
 2.如果不使用索引,mysql必须从第一条记录开始然后读完整个表直到找出相关的行,表越大,花费时间越长
 索引可以用来改善性能,有时索引可能降低性能
*/
# 1.创建索引语法:create index 索引名字 on 表名 (字段名)
# 2.删掉索引语法:drop index 索引名 on 表名
# 3.查看索引:show index from 表名
# 在mysql中选择数据库并且导入sql文件:
# 例如: e:/item_backup.sql;
# 导入sql文件:
# source e:/item_backup.sql;# SQLyog不支持
SELECT COUNT(*) FROM item2;
SHOW INDEX FROM emp;
SELECT * FROM item2 WHERE id = 30187;# 索引查询很快
SELECT * FROM item2 WHERE title = "100";# 非索引查询比较慢
# 创建索引
;CREATE INDEX index_num ON item2(num);
SELECT * FROM item2 WHERE num = 857;

CREATE TABLE mytable2 (id INT PRIMARY KEY AUTO_INCREMENT,ename VARCHAR(10));
# 创建唯一索引
CREATE UNIQUE INDEX index_my2 ON mytable2(ename);
INSERT INTO mytable2 VALUES (NULL,"");
INSERT INTO mytable2 VALUES (NULL,"");# 重复,唯一索引限制数据不能重复
# 按照下列标准选择建立索引列
# 1.频繁搜索的列
# 2.经常用作查询选择的列
# 3.经常排序,分组的列
# 4.经常用作连接的列(主键/外键)

# 请不要使用下面的列创建索引
# 表中仅包含几行数据

/*
 主键,外键,索引的区别
 
		主键				外键				索引
 定义:	唯一标识一条记录,不能有重复的,	表的外键时另一张表的主键,	该字段没有重复值,但可以有一个空值
	不允许为空			外键可以有重复的,可以有空值	(也可设置该字段有重复值)
 
 作用:	主键是用来保证数据完整性	用来和其他建立联系用的		提高查询排序的速度
 
 个数:	主键只能有一个			一个表可以有多个外键		一个表可以有多个索引
 
 
*/

/*
 变量:
 系统变量
	全局变量:对整个服务器有效
	会话变量:对当前的客户端的一次连接
 自定义变量
	用户变量
	局部变量
 一.系统变量
 说明:变量有系统提供,不是用户定义,属于服务器层面
 使用语法:
 1.查看所有系统变量
 show global/session variables
 
 2.查看满足条件的部分系统变量
 show global/session variables like "%%";
 
 3.查看指定的某个系统的变量
 select @@global/session.系统变量名;
 
 4.为某个系统变量赋值
 set @@global/session.系统变量名 = 值;
*/
# 查看全局变量
SHOW GLOBAL VARIABLES;
# 查看会话变量 session可以省略不写
SHOW SESSION VARIABLES;
# 查看全局变量带有"char"
SHOW GLOBAL VARIABLES LIKE "%char%";
# 查看会话变量带有"%char%"
SHOW VARIABLES LIKE "%char%";
# 查看系统变量autocommit的值
SELECT @@global.autocommit;
# 查看系统变量中会话的autocommit值
SELECT @@session.autocommit;
SELECT @@autocommit;
# 给全局变量autocommit赋值
SET @@global.autocommit = 1;
# 给会话变量autocommit赋值
SET @@autocommit = 1;

/*
 二:自定义变量
 说明:变量是用户自定义的并不是由系统提供的
 使用步骤:
 声明
 赋值
 使用(查看,比较,运算)
 
 1.用户变量
 作用域:针对当前会话有效,等同于会话变量的作用域一样,只对当前有效
 2.局部变量
 作用域:仅仅在定义它的begin end中有效,必须应用在begin end中的第一句话
*/

# 1.声明并初始化用户变量
# 方式一:set @用户变量名 = 值
SET @ename = "坤哥";
# 查看用户变量名
SELECT @ename;
# 方式二:set @用户变量名:= 值
SET @pname := "呵呵";
SELECT @pname;
SET @pname := 123456;
SELECT @pname;

# 赋值:通过set或者select
# 方式1:set @用户变量名 = 值
# 方式2:set @用户变量名 := 值
# 方式3:select @用户变量名 := 值
# 方式4:通过select into 例如:select 字段 into 变量 from 表(把表查询出来的字段赋值给变量)
SELECT COUNT(*) INTO @c FROM emp;
SELECT @c;

/*
 局部变量:
 1.声明
 declare 变量名 类型 default 值
 2.赋值
 set 局部变量 = 值
 3.查看
 select 局部变量
*/

DELIMITER $
CREATE PROCEDURE my9()
BEGIN
   # 设置局部变量
   DECLARE result VARCHAR(10) DEFAULT "";
   SET result = "测试";
   SELECT result;
END $

CALL my9()$

# 案例:创建存储过程实现用户是否登陆成功
CREATE PROCEDURE my10(IN ename VARCHAR(10),IN pwd VARCHAR(10))
BEGIN
   # 声明局部变量
   DECLARE result VARCHAR(10) DEFAULT "";
   SELECT COUNT(*) INTO result FROM admin WHERE admin.user_name = ename AND admin.user_password = pwd;
   SELECT IF(result > 0, "成功", "失败");
END $

CALL my10("小君", "666666")$

# 创建带inout模式参数的存储过程
# 创建存储过程实现传入a和b两个值,最终a和b都翻倍并返回
CREATE PROCEDURE my11(INOUT a INT, INOUT b INT)
BEGIN
   SET a = a * 2;
   SET b = b * 2;
END $

SET @a1 = 10$
SET @b1 = 20$

# 创建存储过程实现根据员工编号查询员工的领导编号
CREATE PROCEDURE my12(INOUT empno INT)
BEGIN
   SELECT emp.mgr INTO empno FROM emp WHERE emp.empno = empno;
END $

SET @mgr = 7369$

数据备份和恢复