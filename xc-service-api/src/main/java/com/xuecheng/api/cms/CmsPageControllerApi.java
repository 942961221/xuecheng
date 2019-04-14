package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/11 17:17
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/11 17:17
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@Api(value="cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="currentPage",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="pageSize",value = "每页显示记录数",required=true,paramType="path",dataType="int")
    })
    public QueryResponseResult findPage(Integer currentPage, Integer pageSize, QueryPageRequest queryPageRequest);

    /**
     * 增加一个记录
     */
    @ApiOperation("添加方法")
    public CmsPageResult add(CmsPage cmsPage);
    @ApiOperation("根据Id查找一个的方法")
    public CmsPageResult findOne(String id);
    @ApiOperation("修改一条记录")
    public CmsPageResult update(String id,CmsPage cmsPage);
    @ApiOperation("删除一条记录")
    public CmsPageResult delete(String id);
}
