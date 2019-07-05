package com.unichain.shark.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.unichain.shark.core.ComponentDispatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author youao.du@gmail.com
 * @create 2019-06-26 16:58
 */
@RestController
public class TestApi {

    @PostMapping("/test")
    public String test(String json) {
//        JSONArray array = JSONArray.parseArray(json);

        JSONArray array = new JSONArray();
        JSONObject jsonObject = JSONObject.parseObject(
                "{\n" +
                        "\t\"pageType\": \"vue\",\n" +
                        "\t\"labels\": [{\n" +
                        "\t\t\"name\": \"input\",\n" +
                        "\t\t\"type\": \"input\",\n" +
                        "\t\t\"prop\": {\n" +
                        "\t\t\t\"placeholder\": \"请输入\",\n" +
                        "\t\t\t\"readonly\": false,\n" +
                        "\t\t\t\"modelKey\": \"input1\",\n" +
                        "\t\t\t\"clearable\": true,\n" +
                        "\t\t\t\"type\": \"text/textarea\",\n" +
                        "\t\t\t\"disabled\": false,\n" +
                        "\t\t\t\"rows\": 1,\n" +
                        "\t\t\t\"width\": \"30px\"\n" +
                        "\t\t}\n" +
                        "\t}]\n" +
                        "}"
        );
        array.add(jsonObject);
        ComponentDispatch dispatch = new ComponentDispatch();
        dispatch.dispatch(jsonObject);
        return "success";
    }
}
