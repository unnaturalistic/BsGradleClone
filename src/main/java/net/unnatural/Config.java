package net.unnatural;

public class Config {
    public static String net = "net";
    public static String name = "example";
    public static String projectname = "app";
    public static String projectnameingroupid;

    public static void SetConfig(String net, String name, String projectname, String projectnameingroupid) {
        Config.net = net;
        Config.name = name;
        Config.projectname = projectname;
        Config.projectnameingroupid = projectnameingroupid;
    }

    public static String[] GetConfig() {
        return new String[] {Config.net, Config.name, Config.projectname, Config.projectnameingroupid};
    }
}
