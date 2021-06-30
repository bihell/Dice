package com.bihell.dice.system.controller;

import cn.hutool.core.io.FileUtil;
import com.bihell.dice.config.properties.DiceProperties;
import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.log.annotation.Module;
import com.bihell.dice.framework.log.annotation.OperationLog;
import com.bihell.dice.framework.log.enums.OperationLogType;
import com.bihell.dice.framework.util.LoginUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 上传控制器
 *
 * @author haseochen
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/admin/file_delete")
@Module("system")
@Api(value = "文件删除", tags = {"文件删除"})
public class FileDeleteController {

    @Autowired
    private DiceProperties diceProperties;

    /**
     * 上传单个文件
     *
     * @return
     */
    @PostMapping
    @OperationLog(name = "删除单个文件", type = OperationLogType.DELETE)
    @ApiOperation(value = "删除单个文件", response = ApiResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "__file"),
            @ApiImplicitParam(name = "type", value = "类型", required = true)
    })
    public ApiResult<Boolean> upload(@RequestParam("file") String filePath,
                                     @RequestParam("type") String type) throws Exception {
        String fileDeletePath = diceProperties.getUploadFolder() + LoginUtil.getUserId() + '/' + StringUtils.substringAfterLast(filePath, "/");
        log.info("fileDeletePath:{}", fileDeletePath);
        return ApiResult.ok(FileUtil.del(fileDeletePath));
    }

}
