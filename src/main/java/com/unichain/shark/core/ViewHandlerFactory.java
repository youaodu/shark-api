package com.unichain.shark.core;

import com.unichain.shark.api.ViewHandler;
import com.unichain.shark.model.VueLabel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author youao.du@gmail.com
 * @create 2019-07-02 21:36
 */
public class ViewHandlerFactory {

    private static Map<String, ViewHandler> factory = new HashMap<>();

    /**
     * 空对象
     * @author youao.du@gmail.com
     * @time 21:38
     */
    private static ViewHandler emp = new ViewHandler() {
        @Override
        public void afterPropertiesSet() throws Exception {
            // 空
        }

        @Override
        public String toPage(List<VueLabel> labels) {
            return "";
        }
    };


    public static void register(String key, ViewHandler value) {
        factory.put(key, value);
    }

    public static ViewHandler get(String key) {
        return factory.get(key) == null ? emp : factory.get(key);
    }
}
