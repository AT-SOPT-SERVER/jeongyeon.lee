package org.sopt.global.caffeine;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CacheType {
    ALL_POST("allPosts", 10 * 60, 10000);

    private final String cacheName;
    private final int expireAfterWrite; // 만료 시간
    private final int maximumSize; // 최대 개수
}
