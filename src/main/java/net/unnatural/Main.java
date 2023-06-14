package net.unnatural;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.google.gson.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting BULLSHIT GRADLE CLONE!");
        getjsondata();
        System.out.println("Checking for commands");
        if (args[0] == "compile") {
            compile("main.java");
        }
    }

    public static void getjsondata() {
        try {
            System.out.println("Getting JSON Data");
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("config.json"));
            System.out.println("config file: " + Paths.get("config.json"));
            Map<?, ?> map = gson.fromJson(reader, Map.class);
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
                /*
                    public static String net;
                    public static String name;
                    public static String projectname;
                    public static String projectnameingroupid;
                 */
                if (entry.getKey() == "net") {
                    Config.net = (String) entry.getValue();
                } else if (entry.getKey() == "name") {
                    Config.name = (String) entry.getValue();
                } else if (entry.getKey() == "projectname") {
                    Config.projectname = (String) entry.getValue();
                } else if (entry.getKey() == "projectnameingroupid") {
                    Config.projectnameingroupid = (String) entry.getValue();
                } else {
                    System.out.println("\u001B[31mFAILED TO SET CONFIGURATION!");
                }
            }
            reader.close();      
        } catch (Exception ex) {
            System.out.println("\u001B[31mFAILED TO GET JSON DATA!");
            ex.printStackTrace();
        }
    }

    public static void compile(String file) {
        try {
            System.out.println("Trying to compile");
            ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "javac " + Paths.get(file));
            builder.redirectErrorStream(true);
            Process p;
            p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                System.out.println(line);
            }
        } catch (IOException e ){
            System.out.println("\u001B[31mFAILED");
            e.printStackTrace();
        }
    }
}