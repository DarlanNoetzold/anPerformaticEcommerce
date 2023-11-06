package tech.noetzold.anPerformaticEcommerce.cache;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig {

    @Bean
    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> {
            Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
            configurationMap.put("attribute", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("category", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("keyword", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("media", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("product", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("sku", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("boleto", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("card", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("invoice", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("payment", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("paypal", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("pix", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("coupon", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("promotion", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("address", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("shipping", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("commerceitem", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("customer", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("order", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));
            configurationMap.put("shopcart", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)));

            builder.withInitialCacheConfigurations(configurationMap);
        };
    }

}
