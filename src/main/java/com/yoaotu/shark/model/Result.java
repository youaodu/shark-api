package com.yoaotu.shark.model;

import lombok.Data;

import java.util.List;

/**
 * @author youao.du@gmail.com
 * @create 2019-06-28 18:19
 */
@Data
public class Result {

    public Result(String page, List<String> models) {
        this.page = page;
        this.models = models;
    }

    /**
     * 页面代码
     * @author youao.du@gmail.com
     * @time 20:33
     */
    private String page;

    /**
     * 双向绑定列表
     * @author youao.du@gmail.com
     * @time 20:33
     */
    private List<String> models;
}