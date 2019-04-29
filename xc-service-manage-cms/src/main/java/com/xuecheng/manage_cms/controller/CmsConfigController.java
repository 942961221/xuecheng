package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsConfigControllerApi;
import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/16 18:19
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/16 18:19
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@RestController
@RequestMapping("/cms/config")
public class CmsConfigController implements CmsConfigControllerApi {
    @Autowired
    private CmsConfigService cmsConfigService;
    @Override
    @GetMapping("/getCmsBannerConfig/{id}")
    public CmsConfig getCmsBannerConfig(@PathVariable("id") String id) {
       return cmsConfigService.getCmsBannerConfig(id);
    }
}
