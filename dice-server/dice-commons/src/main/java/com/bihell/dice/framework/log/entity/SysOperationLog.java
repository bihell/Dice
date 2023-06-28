package com.bihell.dice.framework.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bihell.dice.framework.entity.BaseEntity;
import com.bihell.dice.framework.core.validator.groups.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 系统操作日志
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "SysOperationLog对象")
public class SysOperationLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;

    @Schema(description = "请求ID")
    private String requestId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "日志名称")
    private String name;

    @Schema(description = "IP")
    private String ip;

    @Schema(description = "区域")
    private String area;

    @Schema(description = "运营商")
    private String operator;

    @Schema(description = "全路径")
    private String path;

    @Schema(description = "模块名称")
    private String module;

    @Schema(description = "类名")
    private String className;

    @Schema(description = "方法名称")
    private String methodName;

    @Schema(description = "请求方式，GET/POST")
    private String requestMethod;

    @Schema(description = "内容类型")
    private String contentType;

    @Schema(description = "是否是JSON请求映射参数")
    private Boolean requestBody;

    @Schema(description = "请求参数")
    private String param;

    @Schema(description = "tokenMd5值")
    private String token;

    @Schema(description = "0:其它,1:新增,2:修改,3:删除,4:详情查询,5:所有列表,6:分页列表,7:其它查询,8:上传文件")
    private Integer type;

    @Schema(description = "0:失败,1:成功")
    private Boolean success;

    @Schema(description = "响应结果状态码")
    private Integer code;

    @Schema(description = "响应结果消息")
    private String message;

    @Schema(description = "异常类名称")
    private String exceptionName;

    @Schema(description = "异常信息")
    private String exceptionMessage;

    @Schema(description = "浏览器名称")
    private String browserName;

    @Schema(description = "浏览器版本")
    private String browserVersion;

    @Schema(description = "浏览器引擎名称")
    private String engineName;

    @Schema(description = "浏览器引擎版本")
    private String engineVersion;

    @Schema(description = "系统名称")
    private String osName;

    @Schema(description = "平台名称")
    private String platformName;

    @Schema(description = "是否是手机,0:否,1:是")
    private Boolean mobile;

    @Schema(description = "移动端设备名称")
    private String deviceName;

    @Schema(description = "移动端设备型号")
    private String deviceModel;

    @Schema(description = "备注")
    private String remark;

//    @Schema(description = "创建时间")
//    private Date createTime;
//
//    @Schema(description = "修改时间")
//    private Date updateTime;

}
