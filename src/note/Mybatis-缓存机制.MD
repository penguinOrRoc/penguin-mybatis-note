#一级缓存与二级缓存
    缓存机制减轻数据库压力，提高数据库性能
    mybatis的缓存分为两级：一级缓存、二级缓存
  #基本上就是这样。这个简单语句的效果如下:
    映射语句文件中的所有 select 语句的结果将会被缓存。
    映射语句文件中的所有 insert、update 和 delete 语句会刷新缓存。
    缓存会使用最近最少使用算法（LRU, Least Recently Used）算法来清除不需要的缓存。
    缓存不会定时进行刷新（也就是说，没有刷新间隔）。
    缓存会保存列表或对象（无论查询方法返回哪种）的 1024 个引用。
    缓存会被视为读/写缓存，这意味着获取到的对象并不是共享的，可以安全地被调用者修改，而不干扰其他调用者或线程所做的潜在修改。
    
   #一级缓存：
    一级缓存为 ​sqlsesson​ 缓存，缓存的数据只在 SqlSession 内有效。在操作数据库的时候需要先创建 SqlSession 会话对象，在对象中有一个 HashMap 用于存储缓存数据，此 HashMap 是当前会话对象私有的，别的 SqlSession 会话对象无法访问。
    
    具体流程：
    第一次执行 select 完毕会将查到的数据写入 SqlSession 内的 HashMap 中缓存起来
    第二次执行 select 会从缓存中查数据，如果 select 同传参数一样，那么就能从缓存中返回数据，不用去数据库了，从而提高了效率
    
    注意：
    1、如果 SqlSession 执行了 DML 操作（insert、update、delete），并 commit 了，那么 mybatis 就会清空当前 SqlSession 缓存中的所有缓存数据，这样可以保证缓存中的存的数据永远和数据库中一致，避免出现差异 
    2、当一个 SqlSession 结束后那么他里面的一级缓存也就不存在了， mybatis 默认是开启一级缓存，不需要配置  
    3、 mybatis 的缓存是基于 [namespace:sql语句:参数] 来进行缓存的，意思就是， SqlSession 的 HashMap 存储缓存数据时，是使用 [namespace:sql:参数] 作为 key ，查询返回的语句作为 value 保存的
    
   #二级缓存：
    二级缓存是​ mapper​ 级别的缓存，也就是同一个 namespace 的 mapper.xml ，当多个 SqlSession 使用同一个 Mapper 操作数据库的时候，得到的数据会缓存在同一个二级缓存区域
 
    二级缓存默认是没有开启的。需要在 setting 全局参数中配置开启二级缓存
    
    开启二级缓存步骤：
    1、​conf.xml​ 配置全局变量开启二级缓存
    <settings>
        <setting name="cacheEnabled" value="true"/>默认是false：关闭二级缓存
    <settings>
    2、在​ userMapper.xml ​中配置
    <cache eviction="LRU" flushInterval="60000" size="512" readOnly="true"/>当前mapper下所有语句开启二级缓存
    具体流程：
    
    1.当一个​ sqlseesion ​执行了一次​ select​ 后，在关闭此​ session​ 的时候，会将查询结果缓存到二级缓存
    
    2.当另一个​ sqlsession ​执行​ select​ 时，首先会在他自己的一级缓存中找，如果没找到，就回去二级缓存中找，找到了就返回，就不用去数据库了，从而减少了数据库压力提高了性能
    
    注意:
    
    1、如果 ​SqlSession​ 执行了 DML 操作​（insert、update、delete）​，并 ​commit​ 了，那么 ​mybatis​ 就会清空当前​ mapper​ 缓存中的所有缓存数据，这样可以保证缓存中的存的数据永远和数据库中一致，避免出现差异
    
    2、​ mybatis​ 的缓存是基于​ [namespace:sql语句:参数] ​来进行缓存的，意思就是，​SqlSession​ 的 ​HashMap​ 存储缓存数据时，是使用 ​[namespace:sql:参数] ​作为 ​key​ ，查询返回的语句作为 ​value​ 保存的。