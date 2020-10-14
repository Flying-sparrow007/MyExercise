# 字符串相关函数
# 1. concat(a, b)字符串连接函数
SELECT CONCAT("a", "b");
# 2.查询每个员工的工资,后面显示"元";
SELECT CONCAT(sal, "元") sal FROM emp;
# 获取字符串的长度 char_length(str);
SELECT CHAR_LENGTH("益康特");
# 1.查询每个员工的名字及其名字的长度
SELECT ename,CHAR_LENGTH(ename) FROM emp;
# instr(str, substr)获取substr在str中的位置(下标从1开始),如果没有则返回0
SELECT INSTR("erti", "t");
# locate(stbstr, str)获取substr在str中的位置
SELECT LOCATE("d", "asdfqw");
# insert(str, start, length, newstr);
# str:字符串  start:从字符串第几个开始插入
# length:从字符串中的第几个开始插入的位置开始删掉字符串的个数
# newstr:插入的新字符串
SELECT INSERT("kunge", 4, 2, "shuai");
SELECT INSERT("adasdasfefe", 6, 3, "xx");
# lower(str)转小写
SELECT LOWER("INPUT");
# upper(str)转大写
SELECT UPPER("input");
# trim(str)去掉两边的空白
SELECT CHAR_LENGTH(TRIM("   abc   "));
# 1.取消两边的空白并获取他的长度
SELECT CHAR_LENGTH(TRIM("  ab  ad  "));
# left(str, length)从左边截取多少个字符
SELECT LEFT("werty", 3);
# right(str, length)从右边截取多少字符
SELECT RIGHT("werty", 3);
# substring(str, index, length)截取字符串
SELECT SUBSTRING("asdafef", 4, 2);
# replace(str, old, new)替换
SELECT REPLACE("asdnidesd", "sd", "xx");
# repeat(str, length); 重复
SELECT REPEAT("加油", 2);
# reverse(str); 反转
SELECT REVERSE("我爱你");

# 数学相关函数
# 1.向下取整floor(num)
SELECT FLOOR(23.59);
# 2.四舍五入 round(num);
SELECT ROUND(23.556);
# 3.四舍五入round(num, m) m:保留小数点后的位数
SELECT ROUND(23.537, 2);
# truncate(num, m) 非四舍五入 m:保留小数点后的位数
SELECT TRUNCATE(23.537, 2);
# 随机数 rand() 0-1之间的浮点数
SELECT RAND();
SELECT RAND() * 10;
# 随机数并且取整 0-8
SELECT FLOOR(RAND() * 9);

# "案例"
# 1.查询没有上级领导的员工的编号,姓名,工资
SELECT empno,ename,sal FROM emp WHERE mgr IS NULL;
# 2.查询emp表中没有奖金的员工的姓名,职位,工资,奖金
SELECT ename,job,sal,comm FROM emp WHERE comm IS NULL;
# 3.查询emp表中含有奖金的员工的编号,姓名,职位,奖金
SELECT empno,ename,job,comm FROM emp WHERE comm IS NOT NULL;
# 4.查询含有上级领导的员工的姓名,工资以及上级领导的编号
SELECT ename,sal,mgr FROM emp WHERE mgr IS NOT NULL;
# 5.查询emp表中名字以"S"开头的所有员工的姓名
SELECT ename FROM emp WHERE ename LIKE "S%";
SELECT ename FROM emp WHERE LEFT(ename, 1) = "S";
# 6.查询emp表中名字的最后一个字符是"S"的员工的姓名
SELECT ename FROM emp WHERE ename LIKE "%S";
SELECT ename FROM emp WHERE RIGHT(ename, 1) = "s";
# 7.查询倒数的第二个字符是"E"的员工的姓名
SELECT ename FROM emp WHERE ename LIKE "%E_";
# 8.查询emp表中员工的倒数第三个字符是"N"的员工姓名
SELECT ename FROM emp WHERE ename LIKE "%N__";
# 9.查询emp表中员工的名字包含"A"的员工姓名
SELECT ename FROM emp WHERE ename LIKE "%A%";
SELECT ename FROM emp WHERE INSTR(ename, "A") != 0;
# 10.查询emp表中名字不是以"K"开头的员工的所有信息
SELECT * FROM emp WHERE ename NOT LIKE "k%";
SELECT * FROM emp WHERE LEFT(ename, 1) != "k";
# 11.查询emp表中名字不含"A"的所有员工信息
SELECT * FROM emp WHERE ename NOT LIKE "%A%";
# 12.查询job含有"CLERK"的员工数量
SELECT COUNT(ename) FROM emp WHERE job = "CLERK";
# 13.查询job是"SALESMAN"的最高薪水的
SELECT MAX(sal) FROM emp WHERE job = "SALESMAN";
# 14.查询最早和最晚入职的时间
SELECT MAX(hiredada) "最晚入职时间", MIN(hiredada) "最早入职时间" FROM emp;
# 15.查询类别163的商品总库存量
SELECT SUM(num) FROM t_item WHERE category_id = 163;
# 16.查询类别163的商品
SELECT * FROM t_item WHERE category_id = 163;
# 17.查询商品价格不大于100的商品名称和列表
SELECT title,price FROM t_item WHERE price <= 100;
# 18.查询品牌是联想并且价钱在40000以上的商品名称和价钱
SELECT title,price FROM t_item WHERE title LIKE "%联想%" AND price > 40000;
# 19.查询品牌是三木或者价钱在50以下的商品名称和价钱
SELECT title,price FROM t_item WHERE title LIKE "%三木%" OR price < 50;
# 20.查询品牌不是联想,戴尔的商品名称和价钱
SELECT title,price FROM t_item WHERE title NOT LIKE "%联想%" AND title NOT LIKE "%戴尔%";
# 21.查询品牌是三木,广博,齐心的商品名称和价钱
SELECT title,price FROM t_item WHERE title LIKE "%三木%" OR title LIKE "%广博%" OR title LIKE "%齐心%";
# 22.查询品牌是联想且价格大于10000的电脑名称
SELECT title,price FROM t_item WHERE title LIKE "%联想%" AND price > 10000;
# 23.查询联想或者戴尔的电脑名称列表
SELECT title,price FROM t_item WHERE title LIKE "%联想%" OR title LIKE "%戴尔%";
# 24.查询联想,戴尔,三木的商品名称列表
SELECT title FROM t_item WHERE title LIKE "%联想%" OR title LIKE "%戴尔%" OR title LIKE "%三木%";
# 25.查询不是戴尔电脑的名称列表
SELECT title FROM t_item WHERE title NOT LIKE "%戴尔%";
# 26.查询所有记事本的商品名称和价格
SELECT title,price FROM t_item WHERE title LIKE "%记事本%";
# 27.查询品牌字符有"力"的商品的品牌,名称和价格
SELECT title,price FROM t_item WHERE title LIKE "%力%";
# 28.查询名称中有联想字样的商品名称
SELECT title FROM t_item WHERE title LIKE "%联想%";
# 29.查询卖点含有"赠"产品名称
SELECT title,sell_point FROM t_item WHERE sell_point LIKE "%赠%";
# 30.查询emp表中员工的编号,姓名,职位,工资并且工资在1000~2000(包含1000和2000)
SELECT empno,ename,job,sal FROM emp WHERE sal BETWEEN 1000 AND 2000;
SELECT empno,ename,job,sal FROM emp WHERE sal >= 1000 AND sal <= 2000;

# 31.查询emp表中员工在10号部门并且含有上级领导的员工的姓名,职位,上级领导编号以及所属部门的编号
SELECT ename,job,mgr,deptno FROM emp WHERE deptno = 10 AND mgr IS NOT NULL;
# 32.查询emp表中名字包含"E"并且职位不是"MANAGER"的员工的编号,姓名,职位,工资
SELECT empno,ename,job,sal FROM emp WHERE ename LIKE "%E%" AND job != "manager";
# 设置emp表中没有奖金的把奖金设置为0,有的就设置为该奖金
UPDATE emp SET comm = IFNULL(comm, 0);
# 33.查询emp表中没有奖金或名字的倒数第二个字母不是"T"的员工的编号,姓名,职位,奖金
SELECT empno,ename,job,comm FROM emp WHERE comm = 0 OR ename NOT LIKE "%T_";
# 34.查询工资高于3000或部门编号是30的员工的姓名,职位,工资,入职时间以及所属部门编号
SELECT ename,sal,hiredada,deptno FROM emp WHERE sal > 3000 OR deptno = 30;
# 35.查询emp表中10部门或20号部门中员工的编号,姓名,所属部门的编号
SELECT empno,ename,deptno FROM emp WHERE deptno = 10 OR deptno = 20;
SELECT empno,ename,deptno FROM emp WHERE deptno IN(10,20);
# 36.查询不是30号部门的员工的所有信息
SELECT * FROM emp WHERE deptno != 30;
SELECT * FROM emp WHERE deptno <> 30;
# 37.查询奖金不是零的员工的所有信息
SELECT * FROM emp WHERE comm != 0;
# 38.查询emp表中的所有员工的编号,姓名,职位,根据员工的编号进行降序排列
SELECT empno,ename,job FROM emp ORDER BY empno DESC;
# 39.查询emp表中部门编号是10或30的所有员工的姓名,职位,工资,根据工资进行升序排列
SELECT ename,job,sal FROM emp WHERE deptno = 10 OR deptno = 30 ORDER BY sal;
SELECT ename,job,sal FROM emp WHERE deptno IN(10,30) ORDER BY sal;
# 40.查询emp表中所有数据,然后根据部门编号进行升序排列,如果部门编号一致,根据员工的编号进行降序
SELECT * FROM emp ORDER BY deptno, empno DESC;
# 41.查询emp表中工资高于1000或者没有上级领导的员工的编号,姓名,工资,所属部门的编号,以及上级领导的编号,根据部门编号进行降序排列,
# 如果部门编号一致根据工资进行升序排列
SELECT empno,ename,sal,deptno,mgr FROM emp WHERE sal > 1000 OR mgr IS NULL ORDER BY deptno DESC, sal;
# 42.查询emp表中名字不包含"S"的员工的编号,姓名,工资,奖金,根据工资进行升序排列,如果工资一致,根据员工编号进行降序排列
SELECT empno,ename,sal,comm FROM emp WHERE ename NOT LIKE "%S%" ORDER BY sal ASC, empno DESC;

# 43.统计emp表中的员工总数量
SELECT COUNT(empno) "员工数量" FROM emp;
# 44.统计emp表中获取奖金的员工的总数量
SELECT COUNT(empno) "获取奖金的员工数量" FROM emp WHERE comm != 0;
# 45.统计emp表中所有的工资之和
SELECT SUM(sal) "所有工资之和" FROM emp;
# 46.统计emp表中所有的奖金之和
SELECT SUM(comm) "所有奖金之和" FROM emp;
# 47.统计emp表中员工的平均工资
SELECT AVG(sal) "平均工资" FROM emp; 
# 48.统计emp表中员工的平局奖金
SELECT AVG(comm) "平均奖金" FROM emp;
# 49.统计emp表中员工的最高工资
SELECT MAX(sal) "最高工资" FROM emp;
# 50.统计emp表中员工编号的最大值
SELECT MAX(empno) "最大员工编号" FROM emp;
# 51.统计emp表中员工的最低工资
SELECT MIN(sal) "最低工资" FROM emp;

# 52.查询emp表中员工的人数,工资的总和,平均工资,奖金的最大值,奖金的最小值并且返回列表起别名
SELECT COUNT(*) AS "员工人数总和", SUM(sal) AS "工资总和", AVG(sal) AS "平均工资",
MAX(comm) AS "奖金的最大值", MIN(comm) AS "奖金的最小值" FROM emp;
# (超纲)查询emp表中每个部门的编号,人数,工资总和,最后根据人数进行升序排列,如果人数一致,根据工资总和降序排列
# 53.查询工资在1000-2000之间每一个员工的编号,姓名,职位,工资
SELECT empno,ename,job,sal FROM emp WHERE sal > 1000 AND sal < 2000;
# 54.查询emp表中奖金在500-2000之间所有员工的编号,姓名,工资,奖金
SELECT empno,ename,sal,comm FROM emp WHERE comm > 500 AND comm < 2000;
# 55.查询员工的编号是7369,7521的员工信息
SELECT * FROM emp WHERE empno IN(7369,7521);
SELECT * FROM emp WHERE empno = 7369 OR empno = 7521;
# 56.查询emp表中职位是"ANALYST"的员工信息并且以编号的升序排列,工资的降序排列
SELECT * FROM emp WHERE job = "ANALYST" ORDER BY empno ASC,sal DESC;
# 57.查询emp表中职位不是"ANALYST",工资最高的前两名员工信息
SELECT * FROM emp WHERE job != "ANALYST" ORDER BY sal DESC LIMIT 0, 2;
SELECT * FROM emp WHERE job <> "ANALYST" ORDER BY sal DESC LIMIT 0, 2;
# 58.查询商品表中第7 - 15之间的商品信息根据价钱的降序排列
SELECT * FROM t_item ORDER BY price DESC LIMIT 7, 7;
# 59.查询商品表中联想品牌根据价格的降序排列的前一个商品的信息
SELECT * FROM t_item WHERE title LIKE "%联想%" ORDER BY price DESC LIMIT 0, 1;
# 60.查询商品表中分类是163的带有图片的戴尔品牌根据价格的降序排列的前两个商品信息
SELECT * FROM t_item WHERE image IS NOT NULL AND title LIKE "%戴尔%" ORDER BY price DESC LIMIT 0, 2;