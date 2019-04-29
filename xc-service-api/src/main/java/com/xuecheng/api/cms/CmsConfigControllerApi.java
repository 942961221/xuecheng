package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="cms静态页面的模型数据",description = "cms生成静态页面的模型数据获取接口")
public interface CmsConfigControllerApi {
    @ApiOperation("获取轮播图的模型数据")
    CmsConfig getCmsBannerConfig(String id);
}
