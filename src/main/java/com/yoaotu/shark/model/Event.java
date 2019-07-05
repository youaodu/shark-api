package com.yoaotu.shark.model;

import java.util.Map;

/**
 * @author youao.du@gmail.com
 * @create 2019-06-26 13:30
 */
public class Event {
    /**
     * 事件名
     */
    private String name;

    /**
     * 访问接口地址
     */
    private String apiPath;

    /**
     * 接口地址返回的参数需要给
     */
    private Map<String, String> resultToModel;
}
