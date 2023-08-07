package com.bihell.dice.blog.controller.admin;

import com.bihell.dice.commons.constant.CommonConstant;
import com.bihell.dice.commons.constant.CommonRedisKey;
import com.bihell.dice.commons.api.ApiResult;
import com.bihell.dice.commons.log.annotation.Module;
import com.bihell.dice.commons.log.annotation.OperationLog;
import com.bihell.dice.commons.log.enums.OperationLogType;
import com.bihell.dice.commons.utils.UUIDUtil;
import com.bihell.dice.commons.utils.VerificationCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 验证码接口
 **/
@Slf4j
@Controller
@Tag(name = "验证码API")
@Module("blog")
@RequestMapping("/verificationCode")
@ConditionalOnProperty(value = {"dice.enable-verify-code"}, matchIfMissing = true)
public class VerificationCodeController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 获取验证码
     */
    @GetMapping("/getImage")
    @OperationLog(name = "获取验证码", type = OperationLogType.OTHER)
    @Operation(summary = "获取验证码")
    public void getImage(HttpServletResponse response) throws Exception {
        VerificationCode verificationCode = new VerificationCode();
        BufferedImage image = verificationCode.getImage();
        String code = verificationCode.getText();
        String verifyToken = UUIDUtil.getUuid();
        // 缓存到Redis
        redisTemplate.opsForValue().set(String.format(CommonRedisKey.VERIFY_CODE, verifyToken), code, 5, TimeUnit.MINUTES);
        response.setHeader(CommonConstant.VERIFY_TOKEN, verifyToken);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, CommonConstant.JPEG, outputStream);
    }

    /**
     * 获取图片Base64验证码
     */
    @GetMapping("/getBase64Image")
    @ResponseBody
    @OperationLog(name = "获取图片Base64验证码", type = OperationLogType.OTHER)
    @Operation(summary = "获取图片Base64验证码")
    public ApiResult<Map<String, Object>> getCode(HttpServletResponse response) throws Exception {
        VerificationCode verificationCode = new VerificationCode();
        BufferedImage image = verificationCode.getImage();
        String code = verificationCode.getText();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, CommonConstant.JPEG, outputStream);
        // 将图片转换成base64字符串
        String base64 = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        // 生成当前验证码会话token
        String verifyToken = UUIDUtil.getUuid();
        Map<String, Object> map = new HashMap<>(2);
        map.put(CommonConstant.IMAGE, CommonConstant.BASE64_PREFIX + base64);
        map.put(CommonConstant.VERIFY_TOKEN, verifyToken);
        // 缓存到Redis
        redisTemplate.opsForValue().set(String.format(CommonRedisKey.VERIFY_CODE, verifyToken), code, 5, TimeUnit.MINUTES);
        return ApiResult.success(map);
    }

}
