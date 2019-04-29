package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.dao.CmsConfigRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/16 18:24
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/16 18:24
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@Service
public class CmsConfigService {
    @Autowired
    CmsConfigRepository cmsConfigRepository;
    public CmsConfig getCmsBannerConfig(String id){
        if(StringUtils.isEmpty(id)){
            throw new RuntimeException();
        }
        CmsConfig cmsConfig= cmsConfigRepository.findCmsConfigById(id);
        if(cmsConfig==null){
            throw new RuntimeException();
        }
        return cmsConfig;

    }
}
