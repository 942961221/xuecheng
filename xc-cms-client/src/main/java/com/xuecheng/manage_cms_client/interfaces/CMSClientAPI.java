package com.xuecheng.manage_cms_client.interfaces;

import com.xuecheng.framework.client.XcServiceList;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value ="XC-SERVICE-MANAGE-CMS")
public interface CMSClientAPI {
    @GetMapping("/cms/page/findOne/{id}")
    CmsPageResult findOne(@PathVariable("id") String id);
}
