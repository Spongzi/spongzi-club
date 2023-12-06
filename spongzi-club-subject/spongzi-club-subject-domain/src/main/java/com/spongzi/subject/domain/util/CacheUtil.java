package com.spongzi.subject.domain.util;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 缓存工具类
 *
 * @author spong
 * @date 2023/12/06
 */
@Component
public class CacheUtil<V> {
    private final Cache<String, String> localCache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build();

    /**
     * 获取数组结果
     *
     * @param cacheKey 缓存键
     * @param clazz    类型
     * @param function 功能
     * @return {@link List}<{@link V}>
     */
    public List<V> getArrayResult(String cacheKey, Class<V> clazz, Function<String, List<V>> function) {
        List<V> resultList = new LinkedList<>();
        String content = localCache.getIfPresent(cacheKey);
        if (StringUtils.isNotBlank(content)) {
            resultList = JSON.parseArray(content, clazz);
            return resultList;
        }
        resultList = function.apply(cacheKey);
        if (!CollectionUtils.isEmpty(resultList)) {
            localCache.put(cacheKey, JSON.toJSONString(resultList));
        }
        return resultList;
    }
}
