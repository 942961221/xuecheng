package com.xuecheng.manage_cms.service;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

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
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    GridFSBucket gridFSBucket;
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

    /**
     * 生成静态页面的方法
     */
    public String getStaticHtmlStr(String pageId){

        //先获取页面的动态数据
        Map temelateData = getTemelateData(pageId);
        //获取模板信息
        String temelate = getTemelate(pageId);
        //生成html页面
        String html = getHtml(temelateData, temelate);
        return html;

    }

    /**
     * 执行生成html页面
     */

    public String getHtml(Map model,String template){
        try{
        //生成配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template",template);
        //配置模板加载器
        configuration.setTemplateLoader(stringTemplateLoader);
        //获取模板
        Template template1 = configuration.getTemplate("template");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template1, model);
        return html;
    } catch (Exception e) {
        e.printStackTrace();
    }
        return null;
    }


    /**
     * 获取模板信息
     */
    private String getTemelate(String id){
        //先根据pageId获取一个CmsPage
        CmsPage cms = cmsPageRepository.findCmsPageByPageId(id);
        if(cms==null){
            throw new RuntimeException();
        }
        String templateId = cms.getTemplateId();
        if(StringUtils.isEmpty(templateId)){
            throw new RuntimeException();
        }
        Optional<CmsTemplate> optional = cmsTemplateRepository.findById(id);
        if(optional.isPresent()){
            CmsTemplate cmsTemplate = optional.get();
            //取出文件模板Id
            String templateFileId = cmsTemplate.getTemplateFileId();
            //取出模板文件内容
            GridFSFile gridFSFile =
                    gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(templateFileId)));
            //打开下载流对象
            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            //创建GridFsResource
            GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
            try {
                String content = IOUtils.toString(gridFsResource.getInputStream(), "utf‐8");
                return content;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 获取页面的模板数据
     */
    private Map getTemelateData(String pageId){
        //先根据pageId获取一个CmsPage
        CmsPage cms = cmsPageRepository.findCmsPageByPageId(pageId);
        if(cms==null){
            throw new RuntimeException();
        }
        //再从cmsPage中获取DataUrl
        String dataUrl = cms.getDataUrl();
        //调用接口获取模型数据
        if(StringUtils.isEmpty(dataUrl)){
            throw new RuntimeException();
        }
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(dataUrl, Map.class);
        Map body = forEntity.getBody();
        return body;
    }
}
