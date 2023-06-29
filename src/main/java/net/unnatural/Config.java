package net.unnatural;

import java.util.HashMap;
import java.util.Map;

public class Config {
    public static String net = "net";
    public static String name = "example";
    public static String projectname = "app";
    public static String projectnameingroupid;
    public static String MainClass = "Main";
    public static String MainClassfile;
    static Map<String, String> map = new HashMap<String, String>();
    static Map<String, String> taskmap = new HashMap<String, String>();

    //normal
    public static void SetConfig(String net, String name, String projectname, String projectnameingroupid, String mainClass) {
        Config.net = net;
        Config.name = name;
        Config.projectname = projectname;
        Config.projectnameingroupid = projectnameingroupid;
        Config.MainClass = mainClass;
        Config.MainClassfile = Config.MainClass + ".java";
    }
    //hashmap
    public static void SetConfigHashMap(String net, String name, String projectname, String projectnameingroupid, String mainClass, String[] Tasks) {
        map.put("net", net);
        map.put("name", name);
        map.put("projectname", projectname);
        map.put("projectnameingroupid", projectnameingroupid);
        map.put("mainClass", mainClass);
    }

    public static void ConfigTasks(String[] tasks) {
        for (int i = 0; i < 200; i++) {
            taskmap.put("task" + i, tasks[i]);
        }
    }

    public static Map<String, String> getMap() {
        return map;
    }
}
