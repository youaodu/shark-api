package com.unichain.shark.model;

import lombok.Data;

import java.util.List;

/**
 * vue 组件实体
 * @author youao.du@gmail.com
 * @create 2019-06-26 13:25
 */
@Data
public class VueLabel {
    /**
     * 标签名
     * @author youao.du@gmail.com
     */
    private String name;

    /**
     * 标签属性集合
     * @author youao.du@gmail.com
     */
    private List<Prop> props;

    /**
     * 标签事件
     * @author youao.du@gmail.com
     */
    private List<Event> events;

    /**
     * 子标签
     */
    private List<VueLabel> child;
}
