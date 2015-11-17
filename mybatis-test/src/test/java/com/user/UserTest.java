package com.user;


import com.alibaba.fastjson.JSON;
import com.user.dao.BlackListMapper;
import com.user.entity.BlackList;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserTest {
    @Test
    public void test() throws IOException {


        //mybatis的配置文件
        String resource = "mybatis-config.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = UserTest.class.getClassLoader().getResourceAsStream( resource );
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build( is );

        SqlSession session = sessionFactory.openSession();

        BlackListMapper bl = session.getMapper( BlackListMapper.class );


        System.out.println( session );

        BlackList blackList = new BlackList();
        List< BlackList > list = bl.select( blackList );


        System.out.println( JSON.toJSONString( list ) );
    }
}