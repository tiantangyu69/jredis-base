/**
 * 
 */
package org.sagacity.jredis.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.sagacity.jredis.model.RedisStringEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

/**
 * @author LIZHITAO 操作redis String类型的键值对基类
 */
@Repository
public class BaseRedisStringDao {
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 保存字符串
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return
	 */
	public RedisStringEntity saveStringValue(
			final RedisStringEntity redisStringEntity) {
		RedisStringEntity result = stringRedisTemplate
				.execute(new RedisCallback<RedisStringEntity>() {
					public RedisStringEntity doInRedis(
							RedisConnection connection)
							throws DataAccessException {
						RedisSerializer<String> serializer = stringRedisTemplate
								.getStringSerializer();
						byte[] saveKey = serializer.serialize(redisStringEntity
								.getKey());
						byte[] saveName = serializer
								.serialize(redisStringEntity.getValue());
						if (connection.setNX(saveKey, saveName)) {
							return new RedisStringEntity(redisStringEntity
									.getKey(), redisStringEntity.getValue());
						} else{
							return null;
						}
					}
				});
		return result;
	}

	/**
	 * 通过key获得key保存的值
	 * 
	 * @param key
	 * @return String value
	 */
	public RedisStringEntity getStringValue(final String key) {
		RedisStringEntity result = stringRedisTemplate
				.execute(new RedisCallback<RedisStringEntity>() {
					public RedisStringEntity doInRedis(
							RedisConnection connection)
							throws DataAccessException {
						RedisSerializer<String> serializer = stringRedisTemplate
								.getStringSerializer();
						byte[] saveKey = serializer.serialize(key);
						byte[] saveValue = connection.get(saveKey);
						if (saveValue == null) {
							return null;
						}

						String returnKey = serializer.deserialize(saveKey);
						String returnValue = serializer.deserialize(saveValue);

						return new RedisStringEntity(returnKey, returnValue);
					}
				});
		return result;
	}

	/**
	 * 更新数据
	 * 
	 * @param redisStringEntity
	 * @return
	 */
	public RedisStringEntity updateStringValue(
			final RedisStringEntity redisStringEntity) {
		String key = redisStringEntity.getKey();
		if (null == getStringValue(key)) {
			return null;
		}
		
		RedisStringEntity result = stringRedisTemplate
				.execute(new RedisCallback<RedisStringEntity>() {
					public RedisStringEntity doInRedis(
							RedisConnection connection)
							throws DataAccessException {
						RedisSerializer<String> serializer = stringRedisTemplate
								.getStringSerializer();
						byte[] key = serializer.serialize(redisStringEntity
								.getKey());
						byte[] name = serializer.serialize(redisStringEntity
								.getValue());
						connection.set(key, name);
						return new RedisStringEntity(
								redisStringEntity.getKey(), redisStringEntity
										.getValue());
					}
				});
		return result;
	}

	/**
	 * 删除数据
	 * 
	 * @param key
	 */
	public void deleteStringValue(String key) {
		if (null != getStringValue(key)) {
			stringRedisTemplate.delete(key);
		}
	}
	
	public void hset(){
		stringRedisTemplate.opsForHash().put("department:1", "id", UUID.randomUUID().toString());
		stringRedisTemplate.opsForHash().put("department:1", "name", "铁路总公司");
		stringRedisTemplate.opsForHash().put("department:1", "code", "001");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("address", "复兴路");
		map.put("zipCode", "730070");
		stringRedisTemplate.opsForHash().putAll("department:1", map);
		
	}
	
	public void pop(){
		Set<Object> set =  stringRedisTemplate.opsForHash().keys("department:1");
		for(Object o : set){
			System.out.println(stringRedisTemplate.opsForHash().get("department:1", o));
		}
	}

}
