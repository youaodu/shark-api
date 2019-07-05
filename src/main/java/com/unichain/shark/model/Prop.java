package com.unichain.shark.model;

import lombok.Data;

/**
 * 属性
 * @author youao.du@gmail.com
 * @create 2019-06-26 13:27
 */
@Data
public class Prop {
    /**
     * 属性名
     */
    private String name;

    /**
     * 属性
     */
    private Object value;

    /**
     * 是否双向绑定
     */
    private Boolean isModel;

    /**
     * 双向绑定key
     */
    private String modelKey;
}
