package com.yoaotu.shark.api;

import com.alibaba.fastjson.JSONObject;
import com.yoaotu.shark.model.VueLabel;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * 组件解析父
 * @author youao.du@gmail.com
 * @create 2019-06-26 13:24
 */
public interface ComponentAnalysis extends InitializingBean {

    void analysis(JSONObject jsonString, List<VueLabel> labels);
}
