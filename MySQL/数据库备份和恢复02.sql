/*
 1.删掉带有奖金的员工
*/
DELETE FROM emp WHERE comm != 0;

/*
 有奖金的员工恢复到copydb数据库中的emp表中
 语法:load data infile "所恢复的数据及其位置" into table 表名
 首先进入到copydb数据库中
 load data infile "所恢复的数据及其位置" into table emp;
*/
