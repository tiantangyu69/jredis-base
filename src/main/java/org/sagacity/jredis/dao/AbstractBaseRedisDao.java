/**
 * 
 */
package org.sagacity.jredis.dao;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

/**
 * @author Admin
 * 
 */
@Repository
public abstract class AbstractBaseRedisDao<K, V> {
	@Resource
	private RedisTemplate<K, V> redisTemplate;

	/**
	 * 获取 RedisSerializer <br>
	 * ------------------------------<br>
	 */
	public RedisSerializer<String> getRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}

	/**
	 * 设置redisTemplate
	 * 
	 * @param redisTemplate
	 *            the redisTemplate to set
	 */
	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public RedisTemplate<K, V> getRedisTemplate() {
		return redisTemplate;
	}
}
