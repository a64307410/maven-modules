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


        //mybatis�������ļ�
        String resource = "mybatis-config.xml";
        //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        InputStream is = UserTest.class.getClassLoader().getResourceAsStream( resource );
        //����sqlSession�Ĺ���
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build( is );

        SqlSession session = sessionFactory.openSession();

        BlackListMapper bl = session.getMapper( BlackListMapper.class );


        System.out.println( session );

        BlackList blackList = new BlackList();
        List< BlackList > list = bl.select( blackList );


        System.out.println( JSON.toJSONString( list ) );
    }
}