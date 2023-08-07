package com.bihell.dice.system.controller;

import cn.hutool.core.io.FileUtil;
import com.bihell.dice.config.properties.DiceProperties;
import com.bihell.dice.commons.api.ApiResult;
import com.bihell.dice.commons.log.annotation.OperationLog;
import com.bihell.dice.commons.log.enums.OperationLogType;
import com.bihell.dice.commons.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "文件删除")
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
    @Operation(summary = "删除单个文件")
    public ApiResult<Boolean> upload(@RequestParam("file") String filePath,
                                     @RequestParam("type") String type) throws Exception {
        String fileDeletePath = diceProperties.getUploadFolder() + SecurityUtil.getUser().getUserId() + '/' + StringUtils.substringAfterLast(filePath, "/");
        log.info("fileDeletePath:{}", fileDeletePath);
        return ApiResult.success(FileUtil.del(fileDeletePath));
    }

}
