package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/11 17:17
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/11 17:17
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
public interface CmsPageControllerApi {
    public QueryResponseResult findPage(Integer currentPage, Integer pageSize, QueryPageRequest queryPageRequest);
}
