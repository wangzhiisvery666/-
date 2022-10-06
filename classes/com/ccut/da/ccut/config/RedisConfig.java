package ccut.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
 import com.fasterxml.jackson.annotation.PropertyAccessor;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import java.time.Duration;
 import org.springframework.cache.CacheManager;
 import org.springframework.cache.annotation.CachingConfigurerSupport;
 import org.springframework.cache.annotation.EnableCaching;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.data.redis.cache.RedisCacheConfiguration;
 import org.springframework.data.redis.cache.RedisCacheManager;
 import org.springframework.data.redis.connection.RedisConnectionFactory;
 import org.springframework.data.redis.core.RedisTemplate;
 import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
 import org.springframework.data.redis.serializer.RedisSerializationContext;
 import org.springframework.data.redis.serializer.RedisSerializer;
 import org.springframework.data.redis.serializer.StringRedisSerializer;

 @EnableCaching
 @Configuration
 public class RedisConfig extends CachingConfigurerSupport {
   @Bean
   public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
     RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
     Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
     ObjectMapper om = new ObjectMapper();
     om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(om);
    redisTemplate.setConnectionFactory(factory);

    redisTemplate.setKeySerializer((RedisSerializer)new StringRedisSerializer());
    redisTemplate.setValueSerializer((RedisSerializer)jackson2JsonRedisSerializer);
    redisTemplate.setHashKeySerializer((RedisSerializer)jackson2JsonRedisSerializer);
    redisTemplate.setHashValueSerializer((RedisSerializer)jackson2JsonRedisSerializer);
    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(RedisConnectionFactory factory) {
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(om);





    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(600L)).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer((RedisSerializer)stringRedisSerializer)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer((RedisSerializer)jackson2JsonRedisSerializer)).disableCachingNullValues();


    RedisCacheManager cacheManager = RedisCacheManager.builder(factory).cacheDefaults(config).build();
    return (CacheManager)cacheManager;
  }
}
