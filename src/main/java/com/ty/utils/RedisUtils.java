package com.ty.utils;

import com.ty.enums.ResultEnum;
import com.ty.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    @SuppressWarnings("unchecked")
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        if (result == null) return null;
        return objectDeserialization((String)result);
    }


    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, objectSerialiable(value));
            result = true;
        } catch (Exception e) {
            throw new GlobalException(ResultEnum.RESULT_CODE_100500, e.getMessage());
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, objectSerialiable(value));
            result = redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new GlobalException(ResultEnum.RESULT_CODE_100500, e.getMessage());
        }
        return result;
    }


    /**
     * 对象序列化为字符串
     **/
    public static String objectSerialiable(Object obj){
        String serStr = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            serStr = byteArrayOutputStream.toString("ISO-8859-1");
            serStr = java.net.URLEncoder.encode(serStr, "UTF-8");

            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (UnsupportedEncodingException e) {
            throw new GlobalException(ResultEnum.RESULT_CODE_100500, e.getMessage());
        } catch (IOException e) {
            throw new GlobalException(ResultEnum.RESULT_CODE_100500, e.getMessage());
        }
        return serStr;
    }


    /**
     * 字符串反序列化为对象
     * */
    public static Object objectDeserialization(String serStr){
        Object newObj = null;
        try {
            String redStr = java.net.URLDecoder.decode(serStr, "UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            newObj = objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
        } catch (Exception e) {
            return null;
        }
        return newObj;
    }

    public static void main(String[] args) {
        String s = "%C2%AC%C3%AD%00%05sr%005com.brandslink.cloud.common.entity.CustomerInfoEntityG%C3%B2i%0BS%C2%A1%7Ex%02%00%11L%00%07addresst%00%12Ljava%2Flang%2FString%3BL%00%0FbusinessLicenseq%00%7E%00%01L%00%16cancellationRefundCodeq%00%7E%00%01L%00%16cancellationRefundNameq%00%7E%00%01L%00%0BchineseNameq%00%7E%00%01L%00%04cityq%00%7E%00%01L%00%0AcontactWayq%00%7E%00%01L%00%08contactsq%00%7E%00%01L%00%0DcustomerAppIdq%00%7E%00%01L%00%0CcustomerCodeq%00%7E%00%01L%00%11customerSecretKeyq%00%7E%00%01L%00%08districtq%00%7E%00%01L%00%0BenglishNameq%00%7E%00%01L%00%02idt%00%13Ljava%2Flang%2FInteger%3BL%00%0Aprovincialq%00%7E%00%01L%00%06statusq%00%7E%00%01L%00%1AtaxRegistrationCertificateq%00%7E%00%01xpt%00%12%C3%A4%C2%B8%C2%89%C3%A4%C2%BA%C2%9A%C3%A5%C2%B8%C2%82%C3%A9%C2%9D%C2%92%C3%A7%C2%9F%C2%B3%C3%A9%C2%95%C2%87t%00%00t%00%082%7C%C3%A6%C2%8A%C2%BD%C3%A6%C2%A3%C2%80t%00%06%C3%A5%C2%85%C2%A8%C3%A6%C2%A3%C2%80t%00%09%C3%A6%C2%B1%C2%9F%C3%A5%C2%9B%C2%BD%C3%A6%C2%A1%C2%A2t%00%09%C3%A4%C2%B8%C2%89%C3%A4%C2%BA%C2%9A%C3%A5%C2%B8%C2%82t%00%0B13266668888t%00%09%C3%A6%C2%B1%C2%9F%C3%A5%C2%9B%C2%BD%C3%A6%C2%A1%C2%A2t%00%0C101394924396t%00%06123456t%00+33cdb990749349ae3c2b9e400b8c81b7t%00%0C%C3%A4%C2%B8%C2%89%C3%A4%C2%BA%C2%9A%C3%A5%C2%B8%C2%82%C3%A5%C2%8C%C2%BAt%00%05Thridsr%00%11java.lang.Integer%12%C3%A2%C2%A0%C2%A4%C3%B7%C2%81%C2%878%02%00%01I%00%05valuexr%00%10java.lang.Number%C2%86%C2%AC%C2%95%1D%0B%C2%94%C3%A0%C2%8B%02%00%00xp%00%00%00%13t%00%06%C3%A6%C2%B5%C2%B7%C3%A5%C2%8D%C2%97t%00%011t%00%00";
        Object o = objectDeserialization(s);
        System.out.println(o.toString());

    }


	/**
	 * 根据 key 获取自增
	 *
	 * @param key
	 * @return
	 */
	public synchronized Long incrt(String key) {
		return redisTemplate.opsForValue().increment(key, 1);
	}

	/**
	 * @param key
	 * @return
	 */
	public synchronized void setIncrt(String key,Long value) {
		redisTemplate.opsForValue().set(key, value);
	}

}
