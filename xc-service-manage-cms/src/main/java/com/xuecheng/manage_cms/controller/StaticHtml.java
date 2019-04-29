package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.model.response.Response;
import com.xuecheng.framework.web.BaseController;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/16 20:09
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/16 20:09
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@Controller
@RequestMapping("/cms/getHtml")
public class StaticHtml extends BaseController {
    @Autowired
    private CmsPageService cmsPageService;
    @GetMapping("/id")
    public void getHtml(@PathVariable("id") String id){
        String staticHtmlStr = cmsPageService.getStaticHtmlStr(id);
        //将html信息输出到页面
        if(StringUtils.isNotEmpty(staticHtmlStr)){
            try {
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(staticHtmlStr.getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
