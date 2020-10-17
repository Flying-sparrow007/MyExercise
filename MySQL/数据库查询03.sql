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