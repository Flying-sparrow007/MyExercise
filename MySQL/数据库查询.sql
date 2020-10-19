# 分组查询 group by:分组查询,通常和聚合函数结合使用(为聚合函数服务),查询条件中每个xx就以xx作为分组的条件
# 1.查询每个部门的最高工资的员工
SELECT deptno,ename,MAX(sal) "该部门最高工资" FROM emp GROUP BY deptno;
# 2.查询每个分类下商品的库存总量
SELECT category_id,SUM(num) FROM t_item GROUP BY category_id;
# 3.查询每个部门有多少人
SELECT deptno,COUNT(empno) "部门人数" FROM emp GROUP BY deptno;
# 4.查询每个部门工资大于2000的有多少人
SELECT deptno,COUNT(empno) FROM emp WHERE sal > 2000 GROUP BY deptno;
# 5.查询每个分类下低于100元的商品数量
SELECT category_id,COUNT(id) "商品数量" FROM t_item WHERE price < 100 GROUP BY category_id;
# 6.查询emp表中每个部门的编号,人数,工资总和,最后根据人数进行升序排列,工资总和的降序排列
SELECT deptno,COUNT(empno) c,SUM(sal) s FROM emp GROUP BY deptno ORDER BY c ASC, s DESC;
# 7.查询工资在1000-3000的每个部门的编号,平均工资,最低工资,最高工资,根据平均工资进行升序排列
SELECT deptno,AVG(sal) a,MIN(sal),MAX(sal) FROM emp WHERE sal BETWEEN 1000 AND 3000 GROUP BY deptno ORDER BY a;
# 8.查询含有上级领导的员工,每个职业的人数,工资的总和,平均工资,最低工资,根据人数进行降序,平均工资升序排列
SELECT job,COUNT(empno) c,SUM(sal),AVG(sal) a,MIN(sal) FROM emp WHERE mgr IS NOT NULL GROUP BY job ORDER BY c DESC, a;
# 9.查询每个部门,每个主管的手下人数
SELECT deptno,mgr,COUNT(empno) "人数" FROM emp WHERE mgr IS NOT NULL GROUP BY deptno,mgr;
# 10.查询每年入职的人数
SELECT EXTRACT(YEAR FROM hiredada) e,COUNT(empno) FROM emp GROUP BY e;

# having有条件的分组统计
# where后面只能对普通字段筛选
# havinggroup by通常是和group by结合使用
# 普通字段的条件写在where后面,聚合函数条件写在Having后面,having写在group by后面
# where条件用于过滤行,而having子句用于过滤分组数据
# 1.查询每个部门的平均工资要求平均工资大于2000
SELECT deptno,AVG(sal) a FROM emp GROUP BY deptno HAVING a > 2000;
# 2.查询所有分类对应的库存总量,要求库存总量高于100000
SELECT category_id,SUM(num) s FROM t_item GROUP BY category_id HAVING s > 100000;
# 3.查询所有分类对应的平均单价低于100的分类
SELECT category_id,AVG(price) a FROM t_item GROUP BY category_id HAVING a < 100;
# 4.查询每个部门中名字包含"A"的员工的平均工资只显示平均工资高于2000的
SELECT deptno,AVG(sal) a FROM emp WHERE ename LIKE "%A%" GROUP BY deptno HAVING a > 2000;

# 练习
# 1.查询emp表中每个部门的平均工资高于2000的部门编号,部门人数,平均工资,根据平均工资升序排列
SELECT deptno,COUNT(empno),AVG(sal) a FROM emp GROUP BY deptno HAVING a > 2000 ORDER BY a;
# 2.查询emp表中,名字不是以"K"开头的信息,每个部门的最低工资低于1000的部门编号,工资总和,平均工资,最低工资,根据平均工资进行升序排列
SELECT deptno,SUM(sal),AVG(sal) a,MIN(sal) m FROM emp WHERE ename NOT LIKE "K%" GROUP BY deptno HAVING m < 1000 ORDER BY a;
# 3.查询emp表中部门编号是10,30的部门的员工,每个职业中的最高工资低于5000的职业名称,人数,平均工资,工资总和,最高工资,根据人数升序,工资总和降序
SELECT job,deptno,COUNT(empno) c,AVG(sal),SUM(sal) s,MAX(sal) m FROM emp WHERE deptno IN(10, 30) GROUP BY job HAVING m < 5000 ORDER BY c,s DESC;
# 4.查询emp表,工资在1000-3000每个部门的编号,工资总和,平均工资,过滤掉平均工资低于2000的部门,平均工资升序,工资总和降序
SELECT deptno,SUM(sal) s,AVG(sal) a FROM emp WHERE sal BETWEEN 1000 AND 3000 GROUP BY deptno HAVING a >= 2000 ORDER BY a,s DESC;

# 子查询
# 1.拿最高工资的员工姓名,工资,和部门
 # 1-1 员工工资最高多少
	SELECT MAX(sal) FROM emp;
 # 1-2 查询工资是5000的员工姓名,工资,部门
	SELECT ename,sal,deptno FROM emp WHERE sal = 5000;
SELECT ename,sal,deptno FROM emp WHERE sal = (SELECT MAX(sal) FROM emp);

# 2.查询工资高于平均工资的员工姓名,工资,部门,上级领导
 # 2-1 查询emp平均工资
	SELECT AVG(sal) FROM emp;
 # 2-2 查询高于平均工资的员工姓名,工资,部门,上级领导
	SELECT ename,sal,deptno,mgr FROM emp WHERE sal > (SELECT AVG(sal) FROM emp) AND mgr IS NOT NULL;
SELECT ename,sal,deptno,mgr FROM emp WHERE sal > (SELECT AVG(sal) FROM emp) AND mgr IS NOT NULL;

# 3.查询最后入职的员工信息
SELECT * FROM emp WHERE EXTRACT(YEAR FROM hiredada) = (SELECT MAX(EXTRACT(YEAR FROM hiredada)) FROM emp);

# 4.查询出有商品的分类信息(有商品指在商品表中出现过的分类)
 # 4-1查询商品分类中出现的分类id
	SELECT DISTINCT category_id FROM t_item WHERE category_id IS NOT NULL;
 # 4-2查询category_id值为238,241,236,163,917(这些id值是4-1查询出的数据)
	SELECT * FROM t_item WHERE category_id IN(238,241,236,163,917);
SELECT * FROM t_item WHERE category_id IN(SELECT DISTINCT category_id FROM emp WHERE category_id IS NOT NULL);

# 5.查询出有员工编号的员工信息(子查询)
SELECT * FROM emp WHERE empno IN(SELECT empno FROM emp WHERE empno IS NOT NULL);
# 6.查询工资高于20号部门里面最高工资的所有员工信息
SELECT * FROM emp WHERE sal > (SELECT MAX(sal) FROM emp WHERE deptno = 20);
# 7.查询工作和"JONES"一样工作的员工信息
SELECT * FROM emp WHERE ename != "JONES" AND job IN(SELECT job FROM emp WHERE ename = "JONES");
# 8.查询部门的平均工资最高的部门信息(提高题)
 # 8-1 部门最高的平均工资的部门
	SELECT deptno FROM emp GROUP BY deptno ORDER BY AVG(sal) DESC LIMIT 0,1;
SELECT * FROM dept WHERE deptno = (SELECT deptno FROM emp GROUP BY deptno ORDER BY AVG(sal) DESC LIMIT 0,1);

# 总结:
# 1.什么是子查询:嵌套在SQL语句里面的SQL语句成为子查询
# 2.子查询可以有多层嵌套
# 3.写在where/having后面做查询条件的值

# 关联查询:同时查询多张表的数据称为关联查询
# 等值连接
# 1.查询每一个员工姓名和所对应的部门名称和部门地址
# select 员工表的员工姓名,部门表的部门名称,部门表的部门地址 from 员工表,部门表 where 员工表的deptno = 部门表的deptno
SELECT emp.ename,dept.dname,dept.loc FROM emp,dept WHERE emp.deptno = dept.deptno;
# 给表起别名,利用别名去访问对应表中的字段
SELECT e.ename,d.dname,d.loc FROM emp e,dept d WHERE e.deptno = d.deptno;

# 查看编号是7369的员工的部门信息和员工名字,工资,上级领导
SELECT dept.*,emp.empno,emp.ename,emp.sal,emp.mgr FROM dept,emp WHERE emp.empno = 7369 AND emp.deptno = dept.deptno;

# join on(内连接)
# 语法: SELECT 表字段1,表字段2，... FROM 表1 JOIN 表2 ON 两表的等值连接
/*
 * 等值连接格式: SELECT * from A表,B表 where A.x = B.x;
 * 内连接: SELECT * from A表 INNER JOIN B表 ON A.x = B.x; (INNER可以不写)
 * 内连接可读性更高,所以我们尽量使用内连接
 */
# 1.查询每一个员工姓名和所对应的部门名称和部门地址
SELECT e.ename,d.dname,d.loc FROM emp e JOIN dept d ON e.deptno = e.deptno;

# 2.查询每个商品的标题,商品单价,商品分类名称
SELECT t.title,t.price,tc.name,tc.id FROM t_item t JOIN t_item_category tc ON t.category_id = tc.id;

# 3.查询在New York工作的所有员工信息
SELECT e.*,d.loc FROM emp e JOIN dept d ON e.deptno = d.deptno AND d.loc = "NEW YORK";
SELECT e.*,d.loc FROM emp e JOIN dept d ON e.deptno = d.deptno WHERE d.loc = "NEW YORK";

# 4.查询emp表中以"S"开头有上级领导的员工信息和所对应的部门名字,部门地址
SELECT e.*,d.dname,d.loc FROM emp e JOIN dept d ON e.mgr IS NOT NULL AND e.ename LIKE "S%" AND e.deptno = d.deptno;
SELECT e.*,d.dname,d.loc FROM emp e JOIN dept d ON e.deptno = d.deptno WHERE e.ename LIKE "S%" AND e.mgr IS NOT NULL;

# 5.查询每个部门的平均工资,最高工资以及他们的部门信息,最高平均工资的前两个部门
SELECT AVG(e.sal) a,MAX(e.sal),d.* FROM emp e JOIN dept d ON e.deptno = d.deptno GROUP BY e.deptno ORDER BY a DESC LIMIT 0,2;

/*
 外连接
 关联查询时有时只查询两张表有关系的数据,不能满足需求,如果需要查询某一张表所有数据(包含没有关系的)
 则使用外连接查询方式
 左外连接:以join左边的表为主表查询所有数据,右边表只查询有关系的数据,没有则会显示数据为null
 右外连接:以join右边的表未主表查询所有数据,左边表只查询有关系的数据,没有则会显示数据为null
 语法:SELECT 表字段1,表字段2,表字段3... FROM 表1 LEFT/RIGHT OUTER JOIN 表2 ON 条件等值 (OUTER省略不写)
 */
# 案例(右外连接)
SELECT * FROM emp e RIGHT JOIN dept d ON e.deptno = d.deptno;

# 1.查询所有员工的名字和对应的所有部门名称(左外连接)
SELECT e.ename,e.deptno "员工所属部门",d.dname,d.deptno "存在部门" FROM emp e LEFT OUTER JOIN dept d ON e.deptno = d.deptno;
# 2.查询所有部门和对应的员工名
SELECT e.ename,d.* FROM emp e RIGHT OUTER JOIN dept d ON e.deptno = d.deptno;

# 练习:
# 1.每个部门的人数,根据人数排序
SELECT deptno,COUNT(empno) c FROM emp GROUP BY deptno ORDER BY c;
# 2.查询每个部门中,每个主管手下的人数
SELECT deptno,mgr,COUNT(*) FROM emp WHERE mgr IS NOT NULL GROUP BY deptno,mgr;
# 3.每种工作的平均工资按照平均工资的降序排列的前两名
SELECT job,AVG(sal) a FROM emp GROUP BY job ORDER BY a DESC LIMIT 0,2;
# 4.每年入职的人数以降序排列
SELECT EXTRACT(YEAR FROM hiredada) e,COUNT(empno) c FROM emp GROUP BY e ORDER BY c DESC;
# 5.少于等于三个人的部门
SELECT deptno,COUNT(empno) c FROM emp GROUP BY deptno HAVING c <= 3;
# 6.少于等于3个人的部门信息
SELECT d.*,COUNT(e.empno) c FROM emp e JOIN dept d ON e.deptno = d.deptno GROUP BY e.deptno HAVING c <= 3;# 内连接
SELECT * FROM dept WHERE deptno IN(SELECT deptno FROM emp GROUP BY mgr HAVING COUNT(*) <= 3)# 子查询
# 7.拿最低工资的员工信息所对应的部门号,部门名称
# select d.dname,e.* from emp e left join dept d on e.deptno = d.deptno order by e.sal limit 0,1;# 左外连接
SELECT d.dname,e.* FROM emp e LEFT JOIN dept d ON e.deptno = d.deptno WHERE e.sal = (SELECT MIN(sal) FROM emp);# 左外连接
SELECT * FROM dept WHERE deptno = (SELECT deptno FROM emp ORDER BY sal LIMIT 0,1);# 子查询
# 8.只有一个下属的主管信息
SELECT p.* FROM emp e JOIN emp p ON e.mgr = p.empno WHERE e.mgr IS NOT NULL GROUP BY e.mgr HAVING COUNT(e.empno) = 1;
SELECT * FROM emp WHERE empno IN(SELECT mgr FROM emp WHERE mgr IS NOT NULL GROUP BY mgr HAVING COUNT(empno) = 1);
# 9.平均工资最高的部门编号,以及部门名称
SELECT d.deptno,d.dname FROM emp e LEFT JOIN dept d ON e.deptno = e.deptno GROUP BY e.deptno HAVING AVG(sal) = 
(SELECT AVG(sal) a FROM emp e JOIN dept d ON e.deptno = d.deptno GROUP BY e.deptno ORDER BY a DESC LIMIT 0,1);
SELECT deptno,dname FROM dept WHERE deptno = (SELECT deptno FROM emp GROUP BY deptno ORDER BY AVG(sal) DESC LIMIT 0,1);
# 10.下属人数最多的领导个人信息
SELECT * FROM emp WHERE empno = (SELECT mgr FROM emp GROUP BY mgr ORDER BY COUNT(empno) DESC LIMIT 0,1);
SELECT p.* FROM emp e JOIN emp p ON e.mgr = p.empno GROUP BY e.mgr ORDER BY COUNT(e.empno) DESC LIMIT 0,1;

# 11.拿最低工资的个人信息和部门名称
SELECT e.*,d.dname FROM emp e LEFT JOIN dept d ON e.deptno = d.deptno ORDER BY sal LIMIT 0,1;
# 12.最后入职的不是以"S"开头的员工编号,员工工资和部门信息
SELECT e.empno,e.sal,d.* FROM emp e LEFT JOIN dept d ON e.deptno = d.deptno WHERE e.hiredada = (SELECT MAX(hiredada) FROM emp);
# 13.工资高于平均工资的员工信息及其部门信息
SELECT e.*,d.* FROM emp e LEFT JOIN dept d ON e.deptno = d.deptno WHERE sal > (SELECT AVG(sal) FROM emp);
# 14.查询有领导的员工信息和部门名称
SELECT e.*,d.dname FROM emp e LEFT JOIN dept d ON e.deptno = d.deptno WHERE e.mgr IS NOT NULL;
# 15.查询不是以"J"开头的员工信息,部门名称,所在城市
SELECT e.*,d.dname,d.loc FROM emp e LEFT JOIN dept d ON e.deptno = d.deptno WHERE e.ename NOT LIKE "J%";
# 16."DALLAS"市所有的员工信息
SELECT e.* FROM emp e RIGHT JOIN dept d ON e.deptno = d.deptno WHERE d.loc = "DALLAS";
# 17.计算每个城市员工数量
SELECT d.loc,COUNT(e.empno) FROM emp e RIGHT JOIN dept d ON e.deptno = d.deptno GROUP BY d.loc
# 18.查询员工信息和他的主管姓名
SELECT e.*,p.ename FROM emp e LEFT JOIN emp p ON e.mgr = p.empno;
# 19.查询最低工资的员工信息,员工主管名字和部门名称
SELECT e.*,p.empno,p.ename,d.* FROM emp e LEFT JOIN emp p ON e.mgr = p.empno LEFT JOIN dept d ON p.deptno = d.deptno WHERE e.sal =
 (SELECT MIN(sal) FROM emp);
# 20.平均工资最高的部门信息
SELECT d.* FROM emp e JOIN dept d ON e.deptno = d.deptno GROUP BY e.deptno HAVING AVG(sal) =
(SELECT AVG(e.sal) a FROM emp e JOIN dept d ON e.deptno = d.deptno GROUP BY e.deptno ORDER BY a DESC LIMIT 0,1);

# 21.查询员工表中所有员工的编号,姓名,职位,工资根据工资进行升序排列的第三条到第六条员工信息
SELECT empno,ename,job,sal FROM emp ORDER BY sal ASC LIMIT 2,4;
# 22.查询员工表中所有员工的编号,姓名,职位,工资以及该员工的上级领导的编号,姓名,职位,工资
SELECT e.empno,e.ename,e.job,e.sal,p.empno,p.ename,p.job,p.sal FROM emp e LEFT JOIN emp p ON e.mgr = p.empno;
#23.查询员工表中名字没有字母k的所有员工的编号姓名职位以及所在部门的编号,名称,地址
SELECT e.empno,e.ename,e.job,d.* FROM emp e LEFT JOIN dept d ON e.deptno = d.deptno WHERE e.ename NOT LIKE "%K%";
#24.查询部门表中所有的部门信息以及与之有关的员工表中员工的编号,姓名职位工资
SELECT d.*,e.empno,e.ename,e.job,e.sal FROM emp e RIGHT JOIN dept d ON e.deptno = d.deptno;

# 笛卡尔积
/*
 关联查询如果不写关联关系则查询结果为两张表的乘积,这个乘积称之为"笛卡尔积"
 笛卡尔积是一种错误的查询结果,工作中不准出现此现象
*/



# 1.创建person表
CREATE TABLE person (id INT PRIMARY KEY AUTO_INCREMENT, pname VARCHAR(10), gender CHAR(1), rel VARCHAR(5));

# 创建trade表
CREATE TABLE trade (id INT PRIMARY KEY AUTO_INCREMENT, t_time DATE, t_money DOUBLE, t_type VARCHAR(5), pid INT);

# 2.给person表插入数据
INSERT INTO person VALUES (NULL,"周润发",'男',"三姑父"),(NULL,"蔡依林",'女',"二姨"),(NULL,"成龙",'男',"大舅"),
(NULL,"特朗普",'男',"侄子"),(NULL,"默克尔","女","外甥女");
INSERT INTO person VALUES (NULL,"刘德华","男","四姨父"),(NULL,"普京","男","老丈人");

SELECT * FROM person;

# 给trade插入数据
INSERT INTO trade VALUES (NULL,"2018-02-14",1000,"微信",1),(NULL,"2018-02-15",300,"现金",2),
(NULL,"2018-02-16",1500,"支付宝",3),(NULL,"2018-02-16",-20,"支付宝",4),(NULL,"2018-02-18",-600,"现金",5);
INSERT INTO trade VALUES (NULL,"2018-02-19",150,"微信",6),(NULL,"2018-02-20",80,"现金",7);
SELECT * FROM trade;

# 3.统计从 2018年春节(2月15号)到现在 收益(收益 = 收入 - 支出) 多少元红包?
SELECT SUM(t_money) "收益" FROM trade WHERE t_time >= "2018-02-15";
SELECT SUM(t_money) "收益" FROM trade WHERE t_time >= STR_TO_DATE("2018年02月15日", "%Y年%m月%d日");
SELECT SUM(t_money) "收益" FROM trade WHERE t_time BETWEEN "2018-02-15" AND DATE(NOW());

# 4.查询从 2018年春节(2月15号)到现在红包大于100元 的所有 女性 亲戚 的 名字和对应的 红包金额
SELECT p.pname "名字",t.t_money "金额" FROM person p JOIN trade t ON t.pid = p.id WHERE p.gender = '女' AND t.t_money > 100 AND t.t_time >= "2018-02-15";
# 5.查询统计 现金,支付宝,微信 三个平台分别 收到 的红包金额
SELECT t_type "支付类型",SUM(t_money) "总金额" FROM trade WHERE t_money > 0 GROUP BY t_type;

SELECT DATE(NOW());

# 权限管理
# 3张主表:用户表,角色表,权限表
# 2个关系:用户角色关系表,角色关系权限表
# 1.创建用户表
CREATE TABLE USER(id INT PRIMARY KEY AUTO_INCREMENT,ename VARCHAR(10),age INT);
# 插入用户数据
INSERT INTO USER VALUES (NULL,"测试1",20),(NULL,"测试2",21),(NULL,"测试3",22),(NULL,"测试4",23);

# 2.创建角色表
CREATE TABLE role(id INT PRIMARY KEY AUTO_INCREMENT,rname VARCHAR(10),create_time TIMESTAMP);
#插入角色数据
INSERT INTO role VALUES (NULL,"管理员",NOW()),(NULL,"店小二",NOW()),(NULL,"用户",NOW());

# 3.创建用户角色
CREATE TABLE user_role(id INT PRIMARY KEY AUTO_INCREMENT,user_id INT,role_id INT);
#插入数据
INSERT INTO user_role VALUES (NULL,1,3),(NULL,2,1),(NULL,3,3),(NULL,4,2);

SELECT u.*,r.*,e.* FROM USER u JOIN user_role r ON u.id = r.user_id JOIN role e ON e.id = r.role_id;

# 查询出用户以及他所对应的角色
SELECT u.ename,e.rname FROM USER u JOIN user_role r ON u.id = r.user_id JOIN role e ON e.id = r.role_id;