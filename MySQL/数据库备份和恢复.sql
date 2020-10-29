/*
 数据库备份和恢复
 为什么进行数据备份
 1.数据库故障
 2.突然断电
 3.病毒入侵
 4.错误操作导致数据丢失
 使用mysqldump命令备份数据
 mysqldump它就是一个常用的备份工具
 
 1.将create和insert into语句保存到文本文件中
 2.它属于dos命名
 
 备份数据库中所有数据
 语法:mysqldump u 数据库账号 -p数据库密码 -h ip地址 数据库名 > 备份位置及其备份的文件名称
 例如:备份数据库store_ykt的所有数据备份在"D盘中的store.sql"
 mysqldump -u root -p984264 -h localhost store_ykt > e:/store.sql
 
 备份数据库中部分表数据
 语法:mysqldump u 数据库账号 -p数据库密码 -h ip地址 数据库名 表1 表2 ... > 备份位置及其备份文件名称
 例如:备份数据库store_ykt的员工表和员工部门表备份在"D盘中的emp_dept.sql"
 mysqldump -u root -p984264 -h localhost store_ykt emp dept > e:/emp_dept.sql
 
 备份数据库中表中的部分数据
 首先进入MySQL客户端(黑窗口)
 例如:备份员工表中有奖金的员工数据
 语法:select * from emp where comm != 0 into outfile "备份位置及其备份的文件名"
 select * from emp where comm != 0 into outfile "e:/comm.sql"
 
 
 数据的恢复
 1.使用mysql命令恢复数据
 例如:把e盘中的store.sql恢复到数据库copydb中,先创建copydb数据库
 create database copydb;
 语法:mysql -u 数据库账号 -p数据库密码 数据库 < 所要恢复的数据(注意后面没有分号)
*/