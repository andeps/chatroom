package mon.sof.common.tool;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import mon.sof.common.exception.BaseException;
import org.yaml.snakeyaml.Yaml;

import java.util.*;

public class Generator {

    public static String DATASOURCE_DRIVER_CLASS_NAME;
    public static String DATASOURCE_TYPE;
    public static String DATASOURCE_URL;
    public static String DATASOURCE_USERNAME;
    public static String DATASOURCE_PASSWORD;

    /**
     * 读取控制台内容
     *
     * @Author zhangxiaomei
     * @Date 2020-03-13 14:03:14
     * @Param
     * @Return
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StrUtil.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public void init() {
        Yaml yaml = new Yaml();
        Map load = yaml.load(Generator.class.getResourceAsStream("/application-dev.yml"));
        Map springMap = (HashMap) load.get("spring");
        Map datasourceMap = (HashMap) springMap.get("datasource");
        if (datasourceMap.isEmpty()) {
            throw new BaseException("数据库信息为空！！！");
        }
        DATASOURCE_DRIVER_CLASS_NAME = (String) datasourceMap.get("driver-class-name");
        DATASOURCE_TYPE = (String) datasourceMap.get("type");
        DATASOURCE_URL = (String) datasourceMap.get("url");
        DATASOURCE_USERNAME = (String) datasourceMap.get("username");
        DATASOURCE_PASSWORD = (String) datasourceMap.get("password");
    }


    /**
     * 代码生成
     *
     * @Author zhangxiaomei
     * @Date 2020-03-13 14:05:02
     * @Param
     * @Return
     */
    public void run() {
        init();
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(scanner("作者"));
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        mpg.setGlobalConfig(gc);
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DATASOURCE_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DATASOURCE_DRIVER_CLASS_NAME);
        dsc.setUsername(DATASOURCE_USERNAME);
        dsc.setPassword(DATASOURCE_PASSWORD);
        mpg.setDataSource(dsc);
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("mon.sof.project");
        mpg.setPackageInfo(pc);
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        String templateService = "/templates/service.java.vm";
        String templateMapper = "/templates/mapper.xml.vm";
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templateService) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java/mon/sof/project/"
                        + pc.getModuleName()+"/service/"
                        + tableInfo.getEntityName()+"Service" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig(templateMapper) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mybatis/"
                        + tableInfo.getEntityName() + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setService(null);
        templateConfig.setController("templates/controller.java");
        templateConfig.setXml(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setMapper("templates/mapper.java");
        mpg.setTemplate(templateConfig);
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //生成字段注解
        //strategy.entityTableFieldAnnotationEnable(true);
        strategy.setRestControllerStyle(true);
        //是否隐藏主键
        //strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
