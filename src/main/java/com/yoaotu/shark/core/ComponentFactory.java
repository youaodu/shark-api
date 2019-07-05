package com.yoaotu.shark.core;

import com.alibaba.fastjson.JSONObject;
import com.yoaotu.shark.api.ComponentAnalysis;
import com.yoaotu.shark.model.VueLabel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author youao.du@gmail.com
 * @create 2019-07-02 21:27
 */
public class ComponentFactory {
    private static Map<String, ComponentAnalysis> factory = new HashMap<>();

    /**
     * 空对象
     * @author youao.du@gmail.com
     * @time 21:32
     */
    private static ComponentAnalysis emp = new ComponentAnalysis() {
        @Override
        public void afterPropertiesSet() throws Exception {
            // 空的
        }

        @Override
        public void analysis(JSONObject jsonString, List<VueLabel> labels) {
            // 空的
        }
    };

    public static void register(String key, ComponentAnalysis value) {
        factory.put(key, value);
    }

    public static ComponentAnalysis get(String key) {
        return factory.get(key) == null ? emp : factory.get(key);
    }
}
