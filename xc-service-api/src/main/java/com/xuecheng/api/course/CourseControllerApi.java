package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="课程管理管理接口",description = "课程管理接口，提供课程管理的增、删、改、查")
public interface CourseControllerApi {
    @ApiOperation("查找课程子课程的接口")
    TeachplanNode findChilder(String courseId);
}
