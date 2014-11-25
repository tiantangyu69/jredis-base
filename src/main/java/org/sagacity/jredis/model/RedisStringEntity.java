/**
 * 
 */
package org.sagacity.jredis.model;

import java.io.Serializable;

/**
 * @author LIZHITAO Redis String类
 */
public class RedisStringEntity implements Serializable {
	private static final long serialVersionUID = 7696235683676291422L;
	private String key;
	private String value;

	public RedisStringEntity() {
	}

	public RedisStringEntity(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "RedisString [key=" + key + ", value=" + value + "]";
	}

}
