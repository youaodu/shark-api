package com.unichain.shark;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.unichain.shark.api.ComponentAnalysis;
import com.unichain.shark.core.ComponentDispatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SharkApplicationTests {

    @Test
    public void contextLoads() {
        JSONArray array = new JSONArray();
        JSONObject jsonObject = JSONObject.parseObject(
                "{\n" +
                        "\t\"name\": \"input\",\n" +
                        "\t\"type\": \"input\",\n" +
                        "\t\"prop\": {\n" +
                        "\t\t\"placeholder\": \"请输入\",\n" +
                        "\t\t\"readonly\": false,\n" +
                        "\t\t\"modelKey\": \"input1\",\n" +
                        "\t\t\"clearable\": true,\n" +
                        "\t\t\"type\": \"text\",\n" +
                        "\t\t\"disabled\": false,\n" +
                        "\t\t\"rows\": 1,\n" +
                        "\t\t\"width\": \"30 px\"\n" +
                        "\t}\n" +
                        "}"
        );
        array.add(jsonObject);

        ComponentDispatch dispatch = new ComponentDispatch();
        dispatch.dispatch(jsonObject);
    }

}
