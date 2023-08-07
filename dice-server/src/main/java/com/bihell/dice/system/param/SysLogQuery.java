package com.bihell.dice.system.param;

import com.bihell.dice.commons.pagination.BasePageOrderParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 系统日志查询参数
 *
 * @author geekidea
 * @since 2023-02-16
 */
@Data
@Schema(description = "系统日志查询参数")
public class SysLogQuery extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "模块名称")
    private String moduleName;

    @Schema(description = "请求路径")
    private String requestUrl;

    @Schema(description = "日志名称")
    private String logName;

    @Schema(description = "操作人")
    private String username;

    @Schema(description = "日志类型 0:访问日志,1:新增,2:修改,3:删除,4:详情,5:所有列表,6:分页列表,7:其它查询,8:上传文件,9:登录,10:退出")
    private Integer logType;

    @Schema(description = "响应状态 false:失败,true:成功")
    private Boolean responseSuccess;

    @Schema(description = "请求IP")
    private String requestIp;

    @Schema(description = "创建开始时间")
    private Date createStartTime;

    @Schema(description = "创建结束时间")
    private Date createEndTime;

}

