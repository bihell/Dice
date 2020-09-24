package com.bihell.dice.blog.controller.admin;

import com.bihell.dice.config.properties.DiceProperties;
import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.log.annotation.OperationLog;
import com.bihell.dice.framework.log.enums.OperationLogType;
import com.bihell.dice.framework.util.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.bihell.dice.framework.log.annotation.Module;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 上传控制器 todo
 */
@Slf4j
@RestController
@RequestMapping("/upload")
@Module("blog")
@Api(value = "文件上传", tags = {"文件上传"})
public class UploadController {

    @Autowired
    private DiceProperties diceProperties;

    /**
     * 上传单个文件
     * @return
     */
    @PostMapping
    @OperationLog(name = "上传单个文件", type = OperationLogType.UPLOAD)
    @ApiOperation(value = "上传单个文件", response = ApiResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", required = true,dataType = "__file"),
            @ApiImplicitParam(name = "type", value = "类型 head:头像",required = true)
    })
    public ApiResult<String> upload(@RequestParam("file") MultipartFile multipartFile,
                                    @RequestParam("type") String type) throws Exception {
        log.info("multipartFile = " + multipartFile);
        log.info("ContentType = " + multipartFile.getContentType());
        log.info("OriginalFilename = " + multipartFile.getOriginalFilename());
        log.info("Name = " + multipartFile.getName());
        log.info("Size = " + multipartFile.getSize());
        log.info("type = " + type);

        // 上传文件，返回保存的文件名称 todo 路径需要修改
        String saveFileName = UploadUtil.upload(diceProperties.getUploadFolder(), multipartFile, originalFilename -> {
            // 文件后缀
            String fileExtension = FilenameUtils.getExtension(originalFilename);
            // 这里可自定义文件名称，比如按照业务类型/文件格式/日期
            String dateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssS")) + RandomStringUtils.randomNumeric(6);
            String fileName = dateString + "." + fileExtension;
            return fileName;
        });

        // 上传成功之后，返回访问路径，请根据实际情况设置

        String fileAccessPath = diceProperties.getResourceAccessUrl() + saveFileName;
        log.info("fileAccessPath:{}", fileAccessPath);

        return ApiResult.ok(fileAccessPath);
    }

}
