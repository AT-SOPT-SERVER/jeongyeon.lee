package org.sopt.global.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.sopt.global.caffeine.CacheType;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Configuration
public class CacheConfig {

    @Bean(name = "caffeineCacheManager")
    @Primary
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<CaffeineCache> caches =
                Arrays.stream(CacheType.values())
                        .map(cache -> new CaffeineCache(
                                cache.getCacheName(),
                                Caffeine.newBuilder()
                                        .expireAfterWrite(cache.getExpireAfterWrite(), TimeUnit.MINUTES)
                                        .maximumSize(cache.getMaximumSize())
                                        .recordStats()
                                        .build()))
                        .toList();
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
