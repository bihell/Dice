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
 * 系统登录日志
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "SysLoginLog对象")
public class SysLoginLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "请求ID")
    private String requestId;

    @Schema(description = "用户名称")
    private String username;

    @Schema(description = "IP")
    private String ip;

    @Schema(description = "区域")
    private String area;

    @Schema(description = "运营商")
    private String operator;

    @Schema(description = "tokenMd5值")
    private String token;

    @Schema(description = "1:登录，2：登出")
    private Integer type;

    @Schema(description = "是否成功 true:成功/false:失败")
    private Boolean success;

    @Schema(description = "响应码")
    private Integer code;

    @Schema(description = "失败消息记录")
    private String exceptionMessage;

    @Schema(description = "浏览器名称")
    private String userAgent;

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
