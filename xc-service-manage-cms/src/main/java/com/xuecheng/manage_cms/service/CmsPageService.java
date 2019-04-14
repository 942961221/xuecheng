package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

/**
 * @author : zhangJW
 * @Description : java类作用描述
 * @CreateDate : 2019/4/12 19:20
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/12 19:20
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@Service
public class CmsPageService {
    @Autowired
    private CmsPageRepository cmsPageRepository;
    public QueryResponseResult findPage(Integer currentPage, Integer pageSize, QueryPageRequest queryPageRequest){
        //如果前台提交的当前页码数小于0或等于0 默认设置第一页查询
        if(currentPage<=0){
            currentPage=1;
        }
        //暴露给前台的参数是从一开始  后台实际参数是从0开始
        currentPage=currentPage-1;
        if(pageSize<=0){
            pageSize=20;
        }
        //封装请求条件
        CmsPage cmsPage=new CmsPage();
        if(queryPageRequest==null){
            queryPageRequest=new QueryPageRequest();
        }
        //设置站点Id
        if(StringUtils.isNotEmpty(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //设置模板Id
        if(StringUtils.isNotEmpty(queryPageRequest.getTemplateId())){
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        if(StringUtils.isNotEmpty(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        ExampleMatcher exampleMatcher=ExampleMatcher.matching();
        exampleMatcher=exampleMatcher.withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        //添加条件
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);
        //分页查询
        PageRequest pageRequest = PageRequest.of(currentPage, pageSize);
        //获得查询结果
        Page<CmsPage> list = cmsPageRepository.findAll(example,pageRequest);
        //创建返回结果对象
        QueryResult queryResult=new QueryResult();
        //给返回结果封装参数
        queryResult.setList(list.getContent());
        queryResult.setTotal(list.getTotalElements());
        QueryResponseResult queryResponseResult=new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        //返回结果
        return queryResponseResult;
    }
    /**
     * 添加的方法
     */
    public CmsPageResult add(CmsPage cmsPage){
        if (cmsPage==null){
            return new CmsPageResult(CommonCode.FAIL,null);
        }
        CmsPage daoCmsPage = cmsPageRepository.findCmsPageByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        //先校验前台传上来的页面是否在数据库中存在  如果不存在，那么执行保存操作
       if(daoCmsPage==null){
            cmsPage.setPageId(null);
           cmsPageRepository.save(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS,cmsPage);
       }
       return new CmsPageResult(CommonCode.FAIL,null);
    }
    public CmsPageResult findOne(String id){
        //判断id是否为空
        if(!StringUtils.isNotEmpty(id)){
            return new CmsPageResult(CommonCode.FAIL,null);
        }
        CmsPage cmsPage = cmsPageRepository.findCmsPageByPageId(id);
        //判断查询结果是否为空
        if(cmsPage==null){
            return new CmsPageResult(CommonCode.FAIL,null);
        }
        //返回查询结果
        return new CmsPageResult(CommonCode.SUCCESS,cmsPage);
    }
    /**
     * 修改一条记录
     */
    public CmsPageResult update(String id,CmsPage cmsPage){
        //先根据id查询到一条记录
        //如果id或者cmsPage中的参数为空，直接返回失败
        if(!StringUtils.isNotEmpty(id) || cmsPage==null){
           return new CmsPageResult(CommonCode.FAIL,null);
        }
        CmsPage one = cmsPageRepository.findCmsPageByPageId(id);
        if(one!=null){
            one.setTemplateId(cmsPage.getTemplateId());
            //更新所属站点
            one.setSiteId(cmsPage.getSiteId());
            //更新页面别名
            one.setPageAliase(cmsPage.getPageAliase());
            //更新页面名称
            one.setPageName(cmsPage.getPageName());
            //更新访问路径
            one.setPageWebPath(cmsPage.getPageWebPath());
            //更新物理路径
            one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            //执行修改
            CmsPage save = cmsPageRepository.save(one);
            return new CmsPageResult(CommonCode.SUCCESS,save);
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }
    public CmsPageResult delete(String id){
        //判断Id是否为空
        if(!StringUtils.isNotEmpty(id)){
            return new CmsPageResult(CommonCode.FAIL,null);
        }
        //先将数据查询出来
        CmsPage one = cmsPageRepository.findCmsPageByPageId(id);
        //如果不为空，进行删除
        if(one!=null){
            cmsPageRepository.delete(one);
            return new CmsPageResult(CommonCode.SUCCESS,one);
        }
        return new CmsPageResult(CommonCode.FAIL,null);
    }
}
