package com.xuecheng.manage_cms.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResultCode;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/12 18:49
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/12 18:49
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {
    @Autowired
    private CmsPageService cmsPageService;
    @Override
    @GetMapping("/list/{currentPage}/{pageSize}")
    public QueryResponseResult findPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize")Integer pageSize, QueryPageRequest queryPageRequest) {
     return cmsPageService.findPage(currentPage,pageSize,queryPageRequest);
    }

    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return cmsPageService.add(cmsPage);
    }

    /**
     * 根据PageId查找一个
     * @param id
     * @return
     */
    @Override
    @GetMapping("/findOne/{id}")
    public CmsPageResult findOne(@PathVariable("id") String id) {
        return cmsPageService.findOne(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public CmsPageResult update(@PathVariable("id") String id, @RequestBody CmsPage cmsPage) {
        return cmsPageService.update(id,cmsPage);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public CmsPageResult delete(@PathVariable("id") String id) {
        return cmsPageService.delete(id);
    }
}
