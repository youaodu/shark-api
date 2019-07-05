package com.unichain.shark.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.unichain.shark.api.ComponentAnalysis;
import com.unichain.shark.api.ViewHandler;
import com.unichain.shark.model.VueLabel;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author youao.du@gmail.com
 * @create 2019-06-26 14:01
 */
public class ComponentDispatch{

    public String dispatch(JSONObject page) {
        List<VueLabel> labels = new LinkedList<>();

        JSONArray json = page.getJSONArray("labels");
        json.forEach(it -> {
            JSONObject tmp = (JSONObject) it;
            try {
                // 工厂中获取组件
                ComponentAnalysis bean = ComponentFactory.get(tmp.getString("type"));
                // json转换实体类
                bean.analysis(tmp, labels);
            }catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 页面加载方式
        String pageType = page.getString("pageType");
        if (StringUtils.isNotBlank(pageType)) {
            // 工厂中获取试图解析
            ViewHandler bean = ViewHandlerFactory.get(pageType);
            // 标签集合转换前端页面
            return bean.toPage(labels);
        }
        return "";
    }
}