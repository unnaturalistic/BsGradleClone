package net.unnatural;

public class Config {
    public static String net = "net";
    public static String name = "example";
    public static String projectname = "app";
    public static String projectnameingroupid;
    public static String MainClass = "Main";
    public static String MainClassfile;


    public static void SetConfig(String net, String name, String projectname, String projectnameingroupid, String mainClass) {
        Config.net = net;
        Config.name = name;
        Config.projectname = projectname;
        Config.projectnameingroupid = projectnameingroupid;
        Config.MainClass = mainClass;
        Config.MainClassfile = getfile(Config.MainClass);
    }

    public static String[] GetConfig() {
        return new String[] {Config.net, Config.name, Config.projectname, Config.projectnameingroupid};
    }
    private static String getfile(String mc) {
        return mc + ".java";
    }
}
