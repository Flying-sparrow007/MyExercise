# 1.between x and y:在某两个数值之间,包含and两边的数值
# 1.查询员工工资在500-1000的所有员工名字和工资
SELECT ename,sal FROM emp WHERE sal >= 500 AND sal <= 1000;
SELECT ename,sal FROM emp WHERE sal BETWEEN 500 AND 1000;
# 2.查询员工工资不在在500-1000的所有员工名字和工资
SELECT ename,sal FROM emp WHERE sal NOT BETWEEN 500 AND 1000;


# like,模糊查询 _ :代表单个未知的字符  %:代表多个未知的字符
# 1.查询名字以k开头的所有员工名字
SELECT ename FROM emp WHERE ename LIKE "k%";
# 2.查询名字第二个字母是m的所有员工名字
SELECT ename FROM emp WHERE ename LIKE "_m%";
# 3.查询名字第三个字母是l的所有员工名字
SELECT ename FROM emp WHERE ename LIKE "__l%";
# 4.查询名字倒数第二个字母是e的所有员工名字
SELECT ename FROM emp WHERE ename LIKE "%e_";
# 5.查询商品标题包含记事本的商品
SELECT title FROM t_item WHERE title LIKE "%记事本%";
# 6.查询单价低于100的记事本
SELECT title,price FROM t_item WHERE price < 100 AND title LIKE "%记事本%";
# 7.查询有图片的"得力"商品
SELECT title,image FROM t_item WHERE title LIKE "%得力%" AND image IS NOT NULL;
# 8.查询单价介于50到200之间的"得力"商品
SELECT title,price FROM t_item WHERE price >= 50 AND price <= 200 AND title LIKE "%得力%";
SELECT title,price FROM t_item WHERE title LIKE "%得力%" AND price BETWEEN 50 AND 200;
SELECT title,price FROM t_item WHERE price BETWEEN 50 AND 200 AND title LIKE "%得力%";
# 9.查询有"赠"品的"dell"商品
SELECT title,sell_point FROM t_item WHERE title LIKE "%dell%" AND sell_point LIKE "%赠%";

#查询结果排序,格式 order by字段  默认升序,指定升序 asc  降序 desc
# 1.查询员工的名称和工资,按照工资降序排列
SELECT ename,sal FROM emp ORDER BY sal DESC;
# 1.查询员工的名称和工资,按照工资降序排列
SELECT ename,sal FROM emp ORDER BY sal ASC;
SELECT ename,sal FROM emp ORDER BY sal;
# 3.查询单价在100以下的商品名称和价格,按照降序排列
SELECT title,price FROM t_item WHERE price < 100 ORDER BY price DESC;
# 4.查询单价在100一下的记事本商品的名称和价格,按照价格的升序排列.
SELECT title,price FROM t_item WHERE price < 100 AND title LIKE "%记事本%" ORDER BY price ASC;

# 多个字段排序,当第一个字段有相同值时,第二个字段排序开始,字段中间由逗号隔开
# 5.查询所有部门编号,工资,部门编号降序序列,工资升序排列
SELECT deptno,sal FROM emp ORDER BY deptno DESC, sal;
# 6.查询记事本商品的名字,价格 价格在100到30之间包含100到30 按照商品的分类降序排列,价格的升序排列
SELECT title,price FROM t_item WHERE title LIKE "%记事本%" AND price BETWEEN 30 AND 100 ORDER BY category_id DESC, price;

# limit分页查询
# 跳过条数(大于此条数),查询条数,例如:每一页显示5条,第一页则是limit 0,5
# 1.查询商品表中商品名称和价钱,第1页数据,每一页显示五条
SELECT title,price FROM t_item LIMIT 0,5;
# 2.查询商品表中商品名称和价钱,第3页数据,每一页显示五条
SELECT title,price FROM t_item LIMIT 10,5;
# 3.查询拿最高工资的员工信息
SELECT * FROM emp ORDER BY sal DESC LIMIT 0,1;
# 4.查询第二个字母是l,工资小于等于2000的最高的前两名员工信息
SELECT * FROM emp WHERE ename LIKE "_l%" AND sal <= 2000 ORDER BY sal DESC LIMIT 0,2;

# 数值计算+ - * / %(mod)
# 1.查询所有商品的单价,库存,总价
SELECT price,num,price * num "总价" FROM t_item;
# %和mod都是取余的作用
SELECT 8 % 3;
SELECT MOD(8, 3);


# day03
# 日期相关函数
# 1.获取当前日期 + 时间 now();
SELECT NOW();
# 2.获取当前日期 curdate();
SELECT CURDATE();
# 3.获取当前时间 curtime()
SELECT CURTIME();
# 4.从当前的日期和时间中提取日期
SELECT DATE(NOW());
# 5.从当前的日期和时间中提取时间
SELECT TIME(NOW());
# 6.查询商品创建的年月日
SELECT DATE(created_time) FROM t_item;
# 7.查询商品创建的时间
SELECT TIME(created_time) FROM t_item;

# extract()函数用于返回日期/时间的单独部分,比如年,月,日,时,分,秒
# 语法:extract(unit from date)
# 1.提取年
SELECT EXTRACT(YEAR FROM NOW());
# 2.提取月
SELECT EXTRACT(MONTH FROM NOW());
# 3.提取日
SELECT EXTRACT(DAY FROM NOW());
# 4.提取时
SELECT EXTRACT(HOUR FROM NOW());
# 5.提取分
SELECT EXTRACT(MINUTE FROM NOW());
# 6.提取秒
SELECT EXTRACT(SECOND FROM NOW());
# 7.案例:查询入职的年份
SELECT ename,EXTRACT(YEAR FROM hiredada) "年份" FROM emp;

# 日期格式化 data_format(时间, "格式")
# %Y:年份 %m:月 %d:天 %H(24小时制) %h(12小时制) %i:分 %s:秒
# 1.查询现在的时间,以2020年10月12日 15时15分15秒
SELECT DATE_FORMAT(NOW(), "%Y年%m月%d日 %H时%i分%s秒") "当前时间";
SELECT DATE_FORMAT(NOW(), "%Y-%m-%d %H-%i-%s") "当前时间";
# 2.查询商品的创建日期年月日(例如:2020年10月12日)
SELECT DATE_FORMAT(created_time, "%Y年%m月%d") "创建日期" FROM t_item;
# 把不规则的日期格式转换成标准格式,str_to_date("日期字符串", "格式");
SELECT STR_TO_DATE("23号10月2020年", "%d号%m月%Y年") "年-月-日";

# ifnull() 格式:age = ifnull(x, y)判断x是否为null,如果是null则age = y,如果不是null则age = x;
# 1.查询员工表没有奖金的显示为0
SELECT ename,IFNULL(comm, 0) "奖金" FROM emp;
# 2.把员工没有奖金的这些员工的奖金修改为零
UPDATE emp SET comm = IFNULL(comm, 0);
SELECT * FROM emp;

# 聚合函数:对多行数据进行合并统计
# sum():sum函数返回数值列的总数(总和)
# avg():avg函数返回数值列的平均值,null值不包含在计算内
# count():count(column_name)函数返回指定列的值的数据,null值不计入,如果参数为*,即count(*),会统计包括null
# max():max()函数返回一列中的最大值,null值不包含在计算内
# min():min()函数返回一列中的最小值,null值不包含在计算内
# 1.查询员工表的平均工资
SELECT AVG(sal) FROM emp;
# 2.查询10号部门的最高工资
SELECT deptno,MAX(sal) "最高工资" FROM emp WHERE deptno = 10;
# 3.查询dell商品的库存总和
SELECT SUM(num) "总和" FROM t_item WHERE title LIKE "%dell%";
# 4.查询得力商品的条数
SELECT COUNT(title) FROM t_item WHERE title LIKE "%得力%";
# 5.查询员工姓名包含a的最低工资
SELECT ename,MIN(sal) FROM emp WHERE ename LIKE "%a%";