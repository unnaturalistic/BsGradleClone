/*
 NOTES:
System.out.println("\u001B[31mRed text");
System.out.println("\u001B[32mGreen text");
System.out.println("\u001B[33mYellow text");
System.out.println("\u001B[34mBlue text");
System.out.println("\u001B[35mPurple text");
System.out.println("\u001B[36mCyan text");
System.out.println("\u001B[37mWhite text");
sex
gex or 
gay sex
 */

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
        try {
        int lengthargs = args.length;
        System.out.println("Starting BULLSHIT GRADLE CLONE!");
        getjsondata();
        creategroupID();
        System.out.println("Checking for commands");
        if (args[0].equals("compile")) {
                compile("main.java");
            } else if (args[0].equals("run")) {
                run("main");
            } else {
                COLOREDLOG("Invalid command", "red");
        }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Arguments array index is out of bounds which basically means you didn't give it any arguments, its not an error");
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
                if (entry.getKey().equals("net")) {
                    Config.net = (String) entry.getValue();
                } else if (entry.getKey().equals("name")) {
                    Config.name = (String) entry.getValue();
                } else if (entry.getKey().equals("projectname")) {
                    Config.projectname = (String) entry.getValue();
                } else if (entry.getKey().equals("projectnameingroupid")) {
                    Config.projectnameingroupid = (String) entry.getValue();
                } else {
                    runtask((String) entry.getKey());
                }
            }
            reader.close();      
        } catch (NullPointerException | IOException e) {
            COLOREDLOG("CONFIG DOES NOT EXIST, USING DEFAULT!", "red");
        }
    }

    public static void runtask(String taskname) {
        try {
            System.out.println("Getting JSON Task Data");
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("script.json"));
            System.out.println("config file: " + Paths.get("script.json"));
            Map<?, ?> map = gson.fromJson(reader, Map.class);
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (entry.getKey() == taskname) {
                    runcmd((String) entry.getValue());
                    break;
                }
            }
            reader.close();      
        } catch (NullPointerException | IOException e) {
            COLOREDLOG("TASK FILE DOES NOT EXIST!", "red");
        }
    }

    public static void compile(String file) {
        try {
            System.out.println("Trying to compile");
            ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "javac " + Config.net + "/" + Config.name + "/" + file);
            builder.redirectErrorStream(true);
            Process p;
            p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
            COLOREDLOG("Done", "green");
        } catch (IOException e ){
            COLOREDLOG("FAILED", "red");
            e.printStackTrace();
        }
    }

    public static void run(String file) {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/C", "java " + Paths.get(file)
                );
            builder.redirectErrorStream(true);
            Process p;
            p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line != null) {
                    System.out.println(line);
                } else {
                    COLOREDLOG("Done", "green");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void COLOREDLOG(String log, String color) {
        if (color == "red") {
            System.out.println("\u001B[31m" + log + "\u001B[37m");
        } else if (color == "blue") {
            System.out.println("\u001B[34m" + log + "\u001B[37m");
        } else if (color == "green") {
            System.out.println("\u001B[32m" + log + "\u001B[37m");
        }
    }

    public static void runcmd(String cmd) {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/C", cmd
                );
            builder.redirectErrorStream(true);
            Process p;
            p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line != null) {
                    System.out.println(line);
                } else {
                    COLOREDLOG("Done", "green");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void creategroupID() {
        runcmd("mkdir " + Config.net + " && cd " + Config.net + " && mkdir " + Config.name + " && cd " + Config.name);
    }

    public static void movetogroupID() {
        runcmd("cd " + Config.net + " && cd " + Config.name + " && cd ");
    }
}