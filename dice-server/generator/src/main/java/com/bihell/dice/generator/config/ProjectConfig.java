package com.bihell.dice.generator.config;

import com.bihell.dice.generator.constant.GeneratorConstant;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 项目信息配置
 **/
@Data
@Accessors(chain = true)
public class ProjectConfig {

    /**
     * 分页参数后缀
     */
    private String pageParamSuffix = GeneratorConstant.PAGE_PARAM;
    /**
     * QueryVo后缀
     */
    private String queryVoSuffix = GeneratorConstant.QUERY_VO;
    /**
     * 分页参数父类全称，带包名
     */
    private String superPageParamClass = GeneratorConstant.SUPER_PAGE_PARAM_CLASS;
    /**
     * 分页对象类全称，带包名
     */
    private String pagingClass = GeneratorConstant.PAGING_CLASS;
    /**
     * 分页信息类全称，带包名
     */
    private String pageInfoClass = GeneratorConstant.PAGE_INFO_CLASS;
    /**
     * 分页排序参数父类全称，带包名
     */
    private String superPageOrderParamClass = GeneratorConstant.SUPER_PAGE_ORDER_PARAM_CLASS;
    /**
     * 公共id参数类全称，带包名
     */
    private String idParamClass = GeneratorConstant.ID_PARAM_CLASS;
    /**
     * 公共结果类全称，带包名
     */
    private String apiResultClass = GeneratorConstant.API_RESULT_CLASS;
}
