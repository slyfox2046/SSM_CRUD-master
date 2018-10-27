package com.aixs.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aixs.crud.bean.Department;
import com.aixs.crud.bean.Employee;
import com.aixs.crud.dao.DepartmentMapper;
import com.aixs.crud.dao.EmployeeMapper;

/**
 * 测试dao层的额工作
 * 推荐spring项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
 * 1.导入SpringTest模块 （pon.xml）
 * 2.@ContextConfiguration指定Spring配置文件的位置
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
     
	
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	@Test
	public void testCRUD()
	{
		
		System.out.println(departmentMapper);
		//1、插入几个部门
//	    departmentMapper.insertSelective(new Department(null, "综合事业部"));
//		departmentMapper.insertSelective(new Department(null, "生成保障部"));

//	    2.生成员工数据，测试员工插入
		employeeMapper.insertSelective(new Employee(null, "Sumy", "M", "sumy@gmail.com", 2));

//		3.批量插入多个员工：批量，使用可以执行批量操作的sqlSession。
		EmployeeMapper mapper= sqlSession.getMapper(EmployeeMapper.class);
		for (int i = 0; i < 10; i++)
		{
			String uid=UUID.randomUUID().toString().substring(0, 5)+i;
			mapper.insertSelective(new Employee(null, uid, "M",uid+"@atguigu.com", 1));
		}
		
		System.out.println("批量完成");
		
	}
	
}
