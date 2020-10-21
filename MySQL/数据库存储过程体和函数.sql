# 流程控制函数
# if函数: 在Java中if else的效果类似于三目运算
# 三目运算: 10 > 5 ? "大" : "小";
SELECT IF(10 > 5, "大", "小");
# 案例:查询员工奖金,奖金是零显示"没有奖金",否则显示"有奖金";
SELECT empno,IF(comm = 0, "没有奖金", "有奖金") "有无奖金" FROM emp;

# case函数的使用,类似与Java中的switch case效果
/*
 switch(变量){
	case 变量: 语句;
	break;
	case 变量: 语句;
	break;
	default;
 }
*/
/*
 mysql中
 case 要判断的字段或表达式
 when 常量1 then 要显示的值或语句
 when 常量2 then 要显示的值或语句
 ...
 else 要显示的值或语句
 end
*/
# 查询员工表中员工编号和工资,部门是10号的员工工资提高10倍,
# 部门20号的员工工资提高0.1倍
SELECT empno,sal "原始工资",deptno,CASE deptno WHEN 10 THEN sal WHEN 20 THEN sal * 0.1 ELSE sal * 1 END "最新工资" FROM emp;

/*
 case
 when 条件1 then 要显示的值或语句
 when 条件2 then 要显示的值或语句
 ...
 else 要显示的值或语句
 end
*/

# 案例:查询员工的工资情况要求,如果工资大于等于3000,显示"A"级别,如果工资大于等于2000显示"B"级别
# 如果工资大于等于1000显示"C"级别 否则显示"D"级别
SELECT sal,CASE WHEN sal >= 3000 THEN "A" WHEN sal >= 2000 THEN "B" WHEN sal >= 1000 THEN "C" ELSE "D" END "工资级别" FROM emp;

# 存储过程和函数
/*
 存储过程和函数:类似于Java中的方法
 好处:
 1.提高代码的重用性
 2.简化操作
 3.减少编译次数并且减少了和数据库服务器的连接次数,提高了效率
 注意:一般存储过程处理sql语句的增,删,改
 含义:一组预先编译好的sql语句的集合,理解成批处理的语句
*/
/*
 1.创建语法
 create procedure 存储过程名(参数列表)
 begin
    存储过程体(一组合法的SQL语句);
 end
 
 注意:
 1.参数列表包含三部分:参数模式 参数名 参数类型
 例如: in username varchar(10)
 
 参数模式:
 in:该参数可以作为输入,也就是该参数需要调用方法传入值
 
 out:该参数可以作为输出,也就是该参数可以作为返回值
 
 inout:该参数可以作为输入又可以作为输出,也就是该参数既需要传入值又可以返回值
 
 注意:存储过程中的每条SQL语句的结果要求必须加分号,存储过程的结果可以使用delimiter重新设置
 delimiter 结束标记
 例如: delimiter $ (SQLyog不支持)
 
 2.调用语法
 call 存储过程名(参数列表)
*/

# 1.空参列表
# 创建admin表
CREATE TABLE admin(user_name VARCHAR(10),user_password VARCHAR(10));
# 创建存储过程
DELIMITER $
CREATE PROCEDURE my1()
BEGIN
	INSERT INTO admin VALUES ("小龙","123456"),("小君","654321");
END $

CALL my1()$

# 2.创建in模式参数的存储过程(in 可以省略 但是不建议省略)
# 创建存储过程实现查询对应的部门员工信息
CREATE PROCEDURE my2(IN deptno INT)
BEGIN
   SELECT * FROM emp WHERE emp.deptno = deptno;
END $

CALL my2(30)$

# 创建存储过程实现根据传入的条目数和起始索引,查询emp表中的记录
CREATE PROCEDURE my3(IN size INT,IN start_index INT)
BEGIN
   SELECT * FROM emp LIMIT start_index,size;
END $

CALL my3(3,3)$

# 创建存储过程实现用户是否登陆成功
CREATE PROCEDURE my4(IN ename VARCHAR(10), IN pwd VARCHAR(10))
BEGIN
   SELECT IF((SELECT COUNT(*) FROM admin WHERE admin.user_name = ename AND admin.user_password = pwd) > 0, "成功", "失败") "结果";
END $

CALL my4("小龙","123456")$

# 3.创建带out模式的存储过程
# 创建存储过程实现查询员工表中员工编号对应的名字
CREATE PROCEDURE my5(IN empno INT, OUT ename VARCHAR(10))
BEGIN
   SELECT emp.ename INTO ename FROM emp WHERE emp.empno = empno;
END $

# 调用
CALL my5(7499, @n)$
# 查询@n的返回数据
SELECT @n$

# 创建存储过程或函数实现传入一个日期,格式化为xx年xx月xx日并返回
CREATE PROCEDURE my6(IN mydate DATETIME, OUT str_date VARCHAR(15))
BEGIN
   SELECT DATE_FORMAT(mydate, "%Y年%m月%d日") INTO str_date;
END $

CALL my6(NOW(), @DATE)$
SELECT @DATE$

# 创建存储过程实现根据admin表中的user_name删除对应的数据
CREATE PROCEDURE my7(IN pname VARCHAR(10))
BEGIN
  DELETE FROM admin WHERE admin.user_name = pname;
END $
CALL my7("小龙")$

# 创建存储过程实现根据admin表中的user_name修改对应的数据
CREATE PROCEDURE my8(IN pname VARCHAR(10), IN new_pwd VARCHAR(10))
BEGIN
   UPDATE admin SET admin.user_password = new_pwd WHERE admin.user_name = pname;
END $
CALL my8("小君","666666")$

# 4.创建带有into的存储过程
# 创建存储过程实现根据员工名字查询员工的名字
/*
 create procedure my6(inout ename varchar(10));
 begin
    select emp.ename into ename from emp where emp.ename = ename;
 end $
*/

# 删除存储过程
DROP PROCEDURE 存储过程名
DROP PROCEDURE my7$

# 查询所有数据库中的存储过程
SHOW PROCEDURE STATUS$

# 查询表中指定的存储过程信息
SHOW CREATE PROCEDURE 存储过程名
SHOW CREATE PROCEDURE my6$

/*
 视图:数据库中存在多种对象,表和视图都是数据库中的对象,创建视图时名称不能和表名
 重名,视图实际上代表了一段SQL查询语句,可以理解成视图是一张虚拟的表,表中的数据会
 随着原表的改变而改变
 对视图的操作会影响原表,例如增删改,原表中的数据也会相应的改变
 为什么要使用视图:
 有些数据的查询需要书写大量的SQL语句,每次书写比较麻烦,使用视图可以起到SQL重用的作用
 可以隐藏敏感信息
*/
# 1.创建视图的格式
CREATE VIEW 视图名 AS 子查询
# 创建视图
# 案例:隐藏员工表中的工种,领导,员工入职时间,部门
CREATE VIEW myv1 AS (SELECT empno,ename,sal,comm FROM emp);
# 查询视图
# select * from 视图名;
SELECT * FROM myv1;
# 查询视图中的部分字段
# select 字段1,字段2,... from 视图名;
SELECT empno,sal FROM myv1;
# 1-2.创建视图查询平均工资最高的部门信息
CREATE VIEW myv2 AS (
	SELECT d.* FROM emp e JOIN dept d ON e.deptno = d.deptno GROUP BY e.deptno HAVING AVG(e.sal) = 
	(SELECT AVG(sal) a FROM emp GROUP BY deptno ORDER BY a DESC LIMIT 0,1)
);
SELECT * FROM myv2;

# 3.删除视图
# drop view 视图名
DROP VIEW myv2;
# 删除视图中的数据
DELETE FROM myv1 WHERE empno = 7499;

# 5.给视图插入数据
INSERT INTO myv1 VALUES ("8888","admin",8000,3000);

# 6.隐藏不是30号部门的所有员工信息
CREATE VIEW myv3 AS (SELECT empno,ename,sal,deptno FROM emp WHERE deptno = 30);

# 7.给myv3插入数据
INSERT INTO myv3 VALUES (6666,"admin2",7000,30);

INSERT INTO myv3 VALUES (5555,"admin3",7000,20);

# 8.给视图中插入一条视图中不显示的数据,但是原表会显示的数据成为"数据污染"
# 在创建视图时需要使用with check option的关键字来避免"数据污染"

CREATE VIEW myv4 AS (SELECT empno,ename,sal,deptno FROM emp WHERE deptno = 30) WITH CHECK OPTION;
INSERT INTO myv4 VALUES (5506, "admin3", 7000,20);

DELETE FROM myv4 WHERE empno = 6666;
DELETE FROM myv4 WHERE deptno = 70;# 只能操作视图中存在的数据，同时修改原表数据

# 9.修改myv4中的数据
UPDATE myv4 SET ename = "admin20" WHERE empno = 7521;

# 经验:使用视图修改数据会有很多限制,一般在实际开发中视图仅用于查询