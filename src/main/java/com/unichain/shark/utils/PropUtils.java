package com.unichain.shark.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youao.du@gmail.com
 * @create 2019-06-26 21:14
 */
public class PropUtils {

    private static String[] keyWord = new String[] {
            "v-model",
            "v-if"
    };

    /**
     * 转换属性字符串
     * @author youao.du@gmail.com
     * @time 22:22
     * @params
     */
    public static String attributeStr(Map<String, Object> attribute) {
        StringBuffer buffer = new StringBuffer();
        for (String key : attribute.keySet()) {
            Object tmp = attribute.get(key);

            // 布尔类型与整数类型需要绑定
            StringBuffer tmpBuffer= new StringBuffer();

            if (tmp instanceof Boolean) {
                tmpBuffer.append(" :");
            }

            // 判断是否特殊  --> 例如关键字   v-model
            if (isKeyWord(key)) {
                tmpBuffer.append(key + "=\"");
            } else {
                tmpBuffer.append("\"");
                tmpBuffer.append(key);
                tmpBuffer.append("\"=\"");
            }
            tmpBuffer.append(tmp);
            tmpBuffer.append("\" ");
            buffer.append(tmpBuffer.toString());
    }
        return buffer.toString();
    }

    /**
     * 判断是否关键词
     * @author youao.du@gmail.com
     * @time 22:11
     */
    private static boolean isKeyWord(String text) {
        boolean flag = false;
        for (int i = 0; i < keyWord.length; i++) {
            if (keyWord[i].equals(text)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
//        Map<String, Object> map =  new HashMap<>();
//        map.put("clearable", true);
//        map.put("disabled", false);
//
//        System.out.println(attributeStr(map));


        String a = "a";
        String b = "a";
        System.out.println(a == b.intern());
    }
}
