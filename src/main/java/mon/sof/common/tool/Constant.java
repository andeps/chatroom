package mon.sof.common.tool;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings(value = {"rawtypes", "unchecked"})
public class Constant {

    public static final String PIC_UPLOAD;
    public static final String PIC_PATH;

    static {
        Yaml yaml = new Yaml();
        Map<Object, Object> map;
        Map base = yaml.load(Constant.class.getResourceAsStream("/application.yml"));
        Map spring = (Map)base.get("spring");
        Map profiles = (Map)spring.get("profiles");
        if(profiles.get("include").equals("dev")){
            Map base1 = yaml.load(Constant.class.getResourceAsStream("/application-dev.yml"));
            map = (HashMap) base1.get("myConfig");
        }else{
            Map base1 = yaml.load(Constant.class.getResourceAsStream("/application-prod.yml"));
            map = (HashMap) base1.get("myConfig");
        }
        PIC_UPLOAD = (String) map.get("accessDirectory");
        PIC_PATH = (String) map.get("mappingUrl");
        File picDir = new File(PIC_PATH);
        if(!picDir.exists()){
            picDir.mkdirs();
        }
        //有相关配置信息 继续在这里面编写
    }


    private Constant(){
    }
}
