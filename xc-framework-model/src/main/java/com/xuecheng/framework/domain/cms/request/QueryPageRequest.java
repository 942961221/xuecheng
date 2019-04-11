package com.xuecheng.framework.domain.cms.request;
import com.xuecheng.framework.model.request.RequestData;
import lombok.Data;

/**
 * @author : zhangJW
 * @Description : 封装一个请求类，用于封装页面请求的参数
 * @CreateDate : 2019/4/11 15:12
 * @Updat :  更新作者
 * @UpdateDate : 2019/4/11 15:12
 * @UpdateRemark : 更新内容注释
 * @Version : 1.0
 */
@Data
public class QueryPageRequest extends RequestData{
    //站点Id
    private String  siteId;
    //页面Id
    private String pageId;
    //页面名称
    private String pageName;
    //别名
    private String pageAliase;
    //模板Id
    private String TemplateId;
}
