
 #结果映射
        constructor - 用于在实例化类时，注入结果到构造方法中
            idArg - ID 参数；标记出作为 ID 的结果可以帮助提高整体性能
            arg - 将被注入到构造方法的一个普通结果
        id – 一个 ID 结果；标记出作为 ID 的结果可以帮助提高整体性能
        result – 注入到字段或 JavaBean 属性的普通结果
        association – 一个复杂类型的关联；许多结果将包装成这种类型
            嵌套结果映射 – 关联可以是 resultMap 元素，或是对其它结果映射的引用
        collection – 一个复杂类型的集合
            嵌套结果映射 – 集合可以是 resultMap 元素，或是对其它结果映射的引用
        discriminator – 使用结果值来决定使用哪个 resultMap
            case – 基于某些值的结果映射
                嵌套结果映射 – case 也是一个结果映射，因此具有相同的结构和元素；或者引用其它的结果映射
 # 结果集一对多
        <select id="getAll"  resultMap="oneToPlusMap">
            select p.p_id,p.p_value,s.s_id,s.s_value from demoparent p , demoson s
            WHERE p.p_id = s.p_id
        </select>
    
        <resultMap id="oneToPlusMap" type="com.penguin.springcloud.entities.demo.DemoParent">
            <result property="p_id" column="p_id"/>
            <result property="p_value" column="p_value"/>
            <collection property="sons" ofType="com.penguin.springcloud.entities.demo.DemoSon">
                <result property="s_id" column="s_id"/>
                <result property="s_value" column="s_value"/>
            </collection>
        </resultMap>
 