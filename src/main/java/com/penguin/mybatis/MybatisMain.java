package com.penguin.mybatis;

import com.penguin.mybatis.bean.Payment;
import com.penguin.mybatis.mapper.DemoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisMain {
    public static void main(String[] args) {
        /**
         * 1.根据xml(全局)配置文件，创建SqlSessionFactory对象
         * 2.sql映射文件：配置了每一个sql以及封装规则
         * 3.将sql映射文件注册在全局配置文件中
         * 4.比编写业务逻辑代码
         */
        SqlSession sqlSession = null;
        //1.根据xml(全局)配置文件，创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            //2.获取SqlSession实例，从创建SqlSessionFactory对象中获取，能直接执行已经映射的SQL语句
            sqlSession = sqlSessionFactory.openSession();
            //3.获取接口的实现类 会为接口自动创建一个代理对象，代理对象去执行方法
            //执行sql的方法二 接口式获取
            DemoMapper demoMapper = sqlSession.getMapper(DemoMapper.class);
            System.out.println(demoMapper.getClass());
            Payment payment2 = demoMapper.getPayment(10001);
            System.out.println(payment2);
            //执行sql的方法一   原生获取
            // Payment payment1 = (Payment) session.selectOne("com.penguin.mybatis.mapper.PaymentMapper.getPayment", 1);
            //System.out.println(payment1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //4.关闭session
        finally {
            sqlSession.close();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
}


