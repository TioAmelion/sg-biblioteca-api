package ao.com.laravel.biblioteca_api.config;

//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//
//import java.time.Duration;

@Configuration
public class RedisConfig {

//    @Bean
//    public RedisCacheConfiguration cacheConfiguration() {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                // Define o tempo de vida (TTL) padrão das chaves no Redis (ex: 10 minutos)
//                .entryTtl(Duration.ofMinutes(10))
//                // Desativa o salvamento de valores nulos
//                .disableCachingNullValues()
//                // Configura a serialização do valor para JSON em vez de binário Java
//                .serializeValuesWith(
//                        RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
//                );
//    }
}
