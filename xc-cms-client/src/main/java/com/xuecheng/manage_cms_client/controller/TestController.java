package com.xuecheng.manage_cms_client.controller;

import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.manage_cms_client.interfaces.CMSClientAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/22 14:55
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/22 14:55
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@RestController
public class TestController {
    @Autowired
    CMSClientAPI cmsClientAPI;
    @RequestMapping("cms/{id}")
    public CmsPageResult test(@PathVariable("id") String id){
        return cmsClientAPI.findOne(id);
    }
}
