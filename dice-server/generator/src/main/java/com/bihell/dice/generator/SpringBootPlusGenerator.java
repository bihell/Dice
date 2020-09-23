package com.bihell.dice.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.bihell.dice.generator.config.GeneratorStrategy;
import com.bihell.dice.generator.constant.GeneratorConstant;
import com.bihell.dice.generator.properties.GeneratorProperties;
import org.springframework.stereotype.Component;

/**
 * dice代码生成器入口类
 **/
@Component
public class SpringBootPlusGenerator {

    /**
     * 生成代码
     * @param args
     */
    public static void main(String[] args) {
        GeneratorProperties generatorProperties = new GeneratorProperties();

        // 设置基本信息
        generatorProperties
                .setMavenModuleName("example")
                .setParentPackage("com.example")
                .setModuleName("foobar")
                .setAuthor("tpxcer")
                .setFileOverride(true);

        // 设置表信息
        generatorProperties.addTable("foo_bar","id");
        // 设置表前缀
        // generatorProperties.setTablePrefix(Arrays.asList("tb_"));

        // 数据源配置
        generatorProperties.getDataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setUsername("root")
                .setPassword("root")
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/spring_boot_plus?useUnicode=true&characterEncoding=UTF-8&useSSL=false");

        // 生成配置
        generatorProperties.getGeneratorConfig()
                .setGeneratorStrategy(GeneratorStrategy.SINGLE)
                .setGeneratorEntity(true)
                .setGeneratorController(true)
                .setGeneratorService(true)
                .setGeneratorServiceImpl(true)
                .setGeneratorMapper(true)
                .setGeneratorMapperXml(true)
                .setGeneratorPageParam(true)
                .setGeneratorQueryVo(true)
                .setRequiresPermissions(false)
                .setPageListOrder(true)
                .setParamValidation(true)
                .setSwaggerTags(true)
                .setOperationLog(true);

        // 全局配置
        generatorProperties.getMybatisPlusGeneratorConfig().getGlobalConfig()
                .setOpen(true)
                .setSwagger2(true)
                .setIdType(IdType.AUTO)
                .setDateType(DateType.ONLY_DATE);

        // 策略配置
        generatorProperties.getMybatisPlusGeneratorConfig().getStrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(true)
                .setVersionFieldName(GeneratorConstant.VERSION)
                .setLogicDeleteFieldName(GeneratorConstant.DELETED);

        // 生成代码
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.generator(generatorProperties);

    }

}
