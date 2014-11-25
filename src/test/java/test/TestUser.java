/**
 * 
 */
package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sagacity.jredis.dao.BaseRedisStringDao;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Admin
 * 
 */
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUser {
	@Resource
	private BaseRedisStringDao dao;

//	@Test
//	public void testSave() {
//		String id = UUID.randomUUID().toString();
//		System.out.println(id);
//		String name = "我是李智涛2";
//		dao.saveStringValue(new RedisStringEntity(id, name));
//	}
	
//	@Test
//	public void update(){
//		String id = "f93eae17-9248-4e59-ba24-61e38e7f00e8";
//		String name = "我是被改变的李智涛";
//		dao.updateStringValue(new RedisStringEntity(id, name));
//	}
	
	@Test
	public void fetch(){
//		String id = "f93eae17-9248-4e59-ba24-61e38e7f00e8";
//		System.out.println(dao.getStringValue(id));
		dao.pop();
//		dao.hset();
	}
}
