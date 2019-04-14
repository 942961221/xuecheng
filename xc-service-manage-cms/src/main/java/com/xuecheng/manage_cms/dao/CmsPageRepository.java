package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
   CmsPage findCmsPageByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String pageWebPath);

   /**
    * 根据pageId查询一条记录
    * @param id
    * @return
    */
   CmsPage findCmsPageByPageId(String id);
}
