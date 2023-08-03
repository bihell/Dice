package com.bihell.dice.system.controller;

import com.bihell.dice.framework.properties.DiceProperties;
import com.bihell.dice.framework.api.ApiResult;
import com.bihell.dice.framework.log.annotation.OperationLog;
import com.bihell.dice.framework.log.enums.OperationLogType;
import com.bihell.dice.framework.utils.SecurityUtil;
import com.bihell.dice.framework.utils.UploadUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 上传控制器
 *
 * @author haseochen
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/admin/upload")
@Tag(name = "文件上传")
public class UploadController {

    @Autowired
    private DiceProperties diceProperties;

    /**
     * 上传单个文件
     *
     * @return
     */
    @PostMapping
    @OperationLog(name = "上传单个文件", type = OperationLogType.UPLOAD)
    @Operation(summary = "上传单个文件")
    public ApiResult<String> upload(@RequestParam("file") MultipartFile multipartFile,
                                    @RequestParam("type") String type) throws Exception {
        log.info("multipartFile = " + multipartFile);
        log.info("ContentType = " + multipartFile.getContentType());
        log.info("OriginalFilename = " + multipartFile.getOriginalFilename());
        log.info("Name = " + multipartFile.getName());
        log.info("Size = " + multipartFile.getSize());
        log.info("type = " + type);

        // 上传文件，返回保存的文件名称
        String saveFileName = UploadUtil.upload(diceProperties.getUploadFolder() + '/' + SecurityUtil.getUser().getUserId(), multipartFile, originalFilename -> {

            // 文件后缀
            String fileExtension = FilenameUtils.getExtension(originalFilename);

            // 这里可自定义文件名称，比如按照业务类型/文件格式/日期
            String dateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssS")) + RandomStringUtils.randomNumeric(6);
            String fileName = dateString + "." + fileExtension;
            return fileName;
        });

        // 上传成功之后，返回访问路径，请根据实际情况设置
        String fileAccessPath = diceProperties.getResourceAccessUrl() + SecurityUtil.getUser().getUserId() + '/' + saveFileName;
        log.info("fileAccessPath:{}", fileAccessPath);

        return ApiResult.ok(fileAccessPath);
    }

}
