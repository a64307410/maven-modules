1.0.4 : 2015-9-28 15:09:25
    1.修改,分页查询时的getCountSql的生成方式!加快效率
1.0.5 : 2015-10-12 15:54:52
    1.增加自定义sql语句查询,但是使用 criteria 构建 查询条件和排序
1.0.6 : 2015-10-13 11:53:39
    1. jdbcDao , 修改  insert,update   entity的操作 方式,用于使用@Transient 排除属性!!!!
1.0.7 : 2015-10-19 19:31:26
    1. jdbcDao , insert 不返回主键而返回影响条数!!!!
    2. jdbcDao 查询语句 字段名.加上 `` 括起来,防止保留字,字段名出错
    3. jdbcDao update,delete方法返回影响条数
1.0.8 : 2015-11-13 14:45:15
    1. jdbcDao insert,update,delete 语句 字段名.加上 `` 括起来,防止保留字,字段名出错

1.0.9 : 2015-12-9 17:18:26
    1. getEntityAutoField 方法去除,排除空字符串.   空字符串也会被加入.段名出错

1.0.10 : 2016-01-25 16:48:44
    1. DefaultNameHandler.getPKName  缓存实体映射的主键名;


