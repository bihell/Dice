package com.bihell.dice.framework.log.enums;

import com.bihell.dice.framework.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作日志类型枚举 todo
 **/
@Getter
@AllArgsConstructor
public enum OperationLogType implements BaseEnum {
    /**
     * 其它
     **/
    OTHER(0, "其它"),
    /**
     * 添加
     **/
    ADD(1, "添加"),
    /**
     * 修改
     **/
    UPDATE(2, "修改"),
    /**
     * 删除
     **/
    DELETE(3, "删除"),
    /**
     * 查询
     **/
    query(4, "详情查询"),
    /**
     * 详情查询
     **/
    INFO(5, "详情查询"),
    /**
     * 列表查询
     **/
    LIST(6, "列表查询"),
    /**
     * 分页列表
     **/
    PAGE(7, "分页列表"),
    /**
     * 其它查询
     **/
    OTHER_QUERY(8, "其它查询"),
    /**
     * 文件上传
     **/
    UPLOAD(9, "文件上传"),
    /**
     * 文件下载
     **/
    download(10, "文件下载"),
    /**
     * Excel导入
     **/
    excel_import(11, "Excel导入"),
    /**
     * Excel导出
     **/
    EXCEL_EXPORT(12, "Excel导出");

    private Integer code;
    private String desc;

}
