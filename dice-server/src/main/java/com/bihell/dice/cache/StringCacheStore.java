package com.bihell.dice.cache;

import com.bihell.dice.exception.TipException;
import com.bihell.dice.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * String cache store.
 *
 * @author johnniang
 */
@Slf4j
public abstract class StringCacheStore extends AbstractCacheStore<String, String> {

    public <T> void putAny(String key, T value) {
        try {
            put(key, JsonUtil.objectToJson(value));
        } catch (JsonProcessingException e) {
            throw new TipException("Failed to convert " + value + " to json", e);
        }
    }

    public <T> void putAny(@NonNull String key, @NonNull T value, long timeout, @NonNull TimeUnit timeUnit) {
        try {
            put(key, JsonUtil.objectToJson(value), timeout, timeUnit);
        } catch (JsonProcessingException e) {
            throw new TipException("Failed to convert " + value + " to json", e);
        }
    }

    public <T> Optional<T> getAny(String key, Class<T> type) {
        Assert.notNull(type, "Type must not be null");

        return get(key).map(value -> {
            try {
                return JsonUtil.jsonToObject(value, type);
            } catch (IOException e) {
                log.error("Failed to convert json to type: " + type.getName(), e);
                return null;
            }
        });
    }
}
