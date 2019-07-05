package com.unichain.shark.viewHandler;

import com.unichain.shark.api.ViewHandler;
import com.unichain.shark.core.ViewHandlerFactory;
import com.unichain.shark.model.Prop;
import com.unichain.shark.model.Result;
import com.unichain.shark.model.VueLabel;
import com.unichain.shark.utils.PropUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * Vue 页面解析器
 * @author youao.du@gmail.com
 * @create 2019-06-27 11:02
 */
@Component
public class VueViewHandler implements ViewHandler {

    /**
     * 转换页面代码
     * @author youao.du@gmail.com
     * @time 11:09
     * @params
     */
    @Override
    public String toPage(List<VueLabel> labels) {

        /**
         * 双向绑定值得集合
         * @author youao.du@gmail.com
         * @time 18:18
         */
        List<String> models = new LinkedList<>();

        /**
         * Template部分
         * @author youao.du@gmail.com
         * @time 13:59
         */
        String page = generateLabelStrs(labels, models);
        Result result = new Result(page, models);

        Configuration config = new Configuration();

        try {
            config.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
         * 把生成好的文件输出
         * @author youao.du@gmail.com
         * @time 09:26
         */
        BufferedWriter bufferedWriter = null;
        try {
            Template template = config.getTemplate("vue.ftl");
            File vueFile = new File(RESULT_PATH + "\\result.vue");
            // 输出流
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(vueFile)));
            template.process(result, bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != bufferedWriter) {
                    bufferedWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 进行读取
         * @author youao.du@gmail.com
         * @time 11:03
         */
        try {
            FileInputStream fis = new FileInputStream(RESULT_PATH + "\\result.vue");
            int len=fis.available(); //available是返回此文件有多长
            byte [] data=new byte[len];//定义一个字节数组，来存放
            fis.read(data);
            String templateStr = new String(data);//使用字符装配字节
            fis.close();
            System.out.println(templateStr);
            return templateStr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 生成前端标签代码
     * @author youao.du@gmail.com
     * @time 15:22
     * @params [labels, models]
     */
    private String generateLabelStrs(List<VueLabel> labels, List<String> models) {
        // 页面的返回值
        StringBuffer page = new StringBuffer();

        labels.forEach(it -> {
            List<Prop> props = it.getProps();

            // 开始标签
            page.append(" <" + it.getName());
            Map<String, Object> propMap = new LinkedHashMap();
            // 属性集合
            for (Prop prop : props) {
                // 判断属性是否双向绑定
                if (null != prop.getIsModel() && prop.getIsModel()) {
                    models.add(prop.getModelKey());
                }

                // 加入属性集合
                propMap.put(prop.getName(), prop.getValue());
            }
//            props.forEach(prop -> {
//                // 判断属性是否双向绑定
//                if (prop.getIsModel()) {
//                    models.add(prop.getModelKey());
//                }
//
//                // 加入属性集合
//                propMap.put(prop.getName(), prop.getValue());
//            });

            // 属性列
            page.append(PropUtils.attributeStr(propMap));
            page.append("> ");

            if (it.getChild() != null && it.getChild().size() != 0) {
                // 递归
                page.append(generateLabelStrs(it.getChild(), models));
            }

            //结束标签
            page.append(" </");
            page.append(it.getName());
            page.append("> ");
        });
        return page.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ViewHandlerFactory.register("vue", this);
    }
}
