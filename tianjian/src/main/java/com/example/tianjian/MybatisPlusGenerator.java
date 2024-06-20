package com.qiang.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("all")
@Slf4j
@Data
public class MybatisPlusGenerator {

    protected static String URL = "jdbc:mysql://localhost:3306/tijian?useEncoding=utf8mb4&serverTimezone=GMT%2B8&useSSL=false";
    protected static String USERNAME = "root";
    protected static String PASSWORD = "123456";

    protected static DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(URL, USERNAME, PASSWORD);

    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(
                        (scanner/*lamdba*/, builder/*变量*/) ->
                                builder.author(scanner.apply("请输入作者名称？"))
                                        //.enableSwagger()
                                        .fileOverride()
                                        .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")
                                        .commentDate("yyyy-MM-dd")
                                        .dateType(DateType.TIME_PACK)
                )
                .packageConfig((builder) ->
                        builder.parent("com.qiang")
                                .entity("entity")
                                .service("service")
                                .serviceImpl("service.Impl")
                                .mapper("mapper")
                                .xml("mapper.xml")
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper"))
                )
                .injectionConfig((builder) ->
                        builder.beforeOutputFile(
                                (a, b) -> log.warn("tableInfo: " + a.getEntityName())
                        )
                )
                .strategyConfig((scanner, builder) ->
                        builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                                .addTablePrefix("tb_", "t_")
                                .entityBuilder()
                                .enableChainModel()
                                .enableLombok()
                                .enableTableFieldAnnotation()
                                .addTableFills(
                                        new Column("create_time", FieldFill.INSERT)
                                )
                                .controllerBuilder()
                                .enableRestStyle()
                                .enableHyphenStyle()
                                .build())
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
