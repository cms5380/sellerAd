package com.charminseok.settlement.job;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class ShareDataBean<T> {

    private final Map<String, T> shareDataMap;


    public ShareDataBean() {
        this.shareDataMap = Maps.newConcurrentMap();
    }

    public void putData(String key, T data) {
        if (shareDataMap ==  null) {
            log.error("Map is not initialize");
            return;
        }

        shareDataMap.put(key, data);
    }

    public T getData (String key) {

        if (shareDataMap == null) {
            return null;
        }

        return shareDataMap.get(key);
    }

}
