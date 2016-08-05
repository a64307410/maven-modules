===============================================================================
Overview
--------
MyBatis Generator (MBG) is a code generator for the MyBatis (and iBATIS) SQL
mapping framework.  MBG will introspect database tables (through JDBC
DatabaseMetaData) and generate SQL Map XML files, Java model object (POJOs)
that match the table, and (optionally) Java client classes that use the other
generated objects.

For full documentation, please refer to the user's manual at
docs/index.html in this distribution.

Dependencies
------------
There are no dependencies beyond the JRE.  JRE 5.0 or above is required.
Also required is a JDBC driver that implements the DatabaseMetaData interface,
especially the "getColumns" and "getPrimaryKeys" methods.

Support
-------
Support is provided through the user mailing list.  Mail
questions or bug reports to:

  mybatis-user@googlegroups.com





===============================================================================
leijianjun 修改的
1.XML 添加   insertSelectiveGetKey  方法用于插入后获得主键
org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator

2. 修改 org.mybatis.generator.internal.DefaultCommentGenerator 注释内容
