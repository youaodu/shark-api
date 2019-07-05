package com.yoaotu.shark.api;

import com.yoaotu.shark.model.VueLabel;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * 页面处理
 * @author youao.du@gmail.com
 * @create 2019-06-27 10:19
 */
public interface ViewHandler extends InitializingBean {

    /**
     * 模板所在目录
     * @author youao.du@gmail.com
     * @time 09:21
     */
    String TEMPLATE_PATH = "src/main/resources/template";

    /**
     * 最后生成目录
     * @author youao.du@gmail.com
     * @time 09:22
     */
    String RESULT_PATH = "src/main/resources/result";

    /**
     * 转换页面代码
     * @author youao.du@gmail.com
     * @time 10:37
     * @params [labels]
     */
    String toPage(List<VueLabel> labels);
}
