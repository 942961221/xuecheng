package com.xuecheng;

import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.manage_cms_client.interfaces.CMSClientAPI;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/21 23:12
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/21 23:12
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    CMSClientAPI cmsClientAPI;
    @org.junit.Test
    public void test(){
        CmsPageResult one = cmsClientAPI.findOne("5a754adf6abb500ad05688d9");
        System.out.println(one.getCmsPage().getDataUrl());
    }
}
