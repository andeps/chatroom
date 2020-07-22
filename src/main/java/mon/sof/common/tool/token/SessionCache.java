package mon.sof.common.tool.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionCache {

    private static final Map<String,String> map = new ConcurrentHashMap<>();

    public static void put(String key,String value){
        map.put(key,value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key){
        return (T) map.get(key);
    }

    public static void remove(String key) {
        map.remove(key);
    }

}
