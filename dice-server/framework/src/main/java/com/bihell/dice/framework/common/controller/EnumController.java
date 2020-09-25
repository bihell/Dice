//package com.bihell.dice.framework.common.controller;
//
//import com.bihell.dice.framework.common.api.ApiResult;
//import com.bihell.dice.framework.common.enums.BaseEnum;
//import com.bihell.dice.framework.common.vo.EnumVo;
//import com.bihell.dice.framework.log.annotation.OperationLogIgnore;
//import com.bihell.dice.framework.util.BaseEnumUtil;
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.reflections.Reflections;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * <p>
// * 展示实现BaseEnum接口的所有枚举值 todo
// * </p>
// */
//@RestController
//@Slf4j
//@OperationLogIgnore
//@Api(value = "枚举字典", tags = {"枚举字典"})
//public class EnumController {
//
//    private static final List<String> FRAMEWORK_ENUM_PACKAGES = Arrays.asList(
//            "com.bihell.dice.framework.common.enums",
//            "com.bihell.dice.system.enums");
//
//    /**
//     * 枚举包路径
//     */
//    @Value("${dice.enum-packages}")
//    private List<String> enumPackages;
//
//    @GetMapping("/enum")
//    public ApiResult<Map<String, Map<Integer, EnumVo<? extends BaseEnum>>>> enumList() {
//        log.debug("enumList...");
//        return ApiResult.ok(BaseEnumUtil.getEnumMap());
//    }
//
//    @PostConstruct
//    public void init() {
//        try {
//            if (enumPackages == null) {
//                enumPackages = new ArrayList<>();
//            }
//            enumPackages.addAll(FRAMEWORK_ENUM_PACKAGES);
//
//            // 获取BaseEnum接口的所有实现
//            log.debug("enumPackages:" + enumPackages);
//            Reflections reflections = new Reflections(enumPackages);
//            Set<Class<? extends BaseEnum>> set = reflections.getSubTypesOf(BaseEnum.class);
//            if (CollectionUtils.isEmpty(set)) {
//                return;
//            }
//            // 循环获取BaseEnum枚举
//            for (Class<? extends BaseEnum> clazz : set) {
//                BaseEnum[] enumConstants = clazz.getEnumConstants();
//                Map<Integer, EnumVo<? extends BaseEnum>> enumVoMap = new ConcurrentHashMap<>(enumConstants.length);
//                for (BaseEnum baseEnum : enumConstants) {
//                    Integer code = baseEnum.getCode();
//                    String desc = baseEnum.getDesc();
//                    EnumVo<BaseEnum> enumVo = new EnumVo<BaseEnum>()
//                            .setCode(code)
//                            .setDesc(desc)
//                            .setBaseEnum(baseEnum);
//                    enumVoMap.put(code, enumVo);
//                }
//                // 设置map
//                BaseEnumUtil.getEnumMap().put(clazz.getName(), enumVoMap);
//            }
//            log.debug("enumMap:{}", BaseEnumUtil.getEnumMap());
//        } catch (Exception e) {
//            log.error("获取BaseEnum枚举map异常", e);
//        }
//    }
//
//}
