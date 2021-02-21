#Mybatis ——org.apache.ibatis （源码路径） 
   #数据源
   #执行语句
   #操作（Connection、PrepareStatement、ResultSet）
    mybatis执行器有哪些？
        SIMPLE、BATCH、REUSE
   #mysql、oracle
#ORM框架：Object Relational Mapping
    用于实现面向对象编程语言中，不同类型系统数据的转换
  

#构建 SqlSessionFactory
    #XML方式
        String resource = "org/mybatis/example/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    #代码方式
#从SqlSessionFactory中获取 SqlSession    
      SqlSession session = sqlSessionFactory.openSession();//初始化执行器
      BlogMapper mapper = session.getMapper(BlogMapper.class);
      Blog blog = mapper.selectBlog(101);
    