package com.unichain.shark.parser;

import com.alibaba.fastjson.JSONObject;
import com.unichain.shark.api.ComponentAnalysis;
import com.unichain.shark.core.ComponentFactory;
import com.unichain.shark.model.Prop;
import com.unichain.shark.model.VueLabel;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author youao.du@gmail.com
 * @create 2019-06-26 13:58
 */
@Component
public class InputAnalysis implements ComponentAnalysis {

    @Override
    public void analysis(JSONObject jsonString, List<VueLabel> labels) {
        VueLabel result = new VueLabel();
        result.setName("el-input");
        List<Prop> props = new LinkedList<>();
        // 属性
        JSONObject prop = (JSONObject) jsonString.get("prop");
        for (String key : prop.keySet()) {
            Prop propTmp = new Prop();
            propTmp.setName(key);
            propTmp.setValue(prop.get(key));

            // 判断是否双向绑定值
            if ("modelKey".equals(key)) {
                propTmp.setName("v-model");
                propTmp.setModelKey(key);
                propTmp.setIsModel(true);
            }
            props.add(propTmp);
        }
        result.setProps(props);
        labels.add(result);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ComponentFactory.register("input", this);
    }
}
