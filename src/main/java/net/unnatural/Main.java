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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.net.*;


import com.google.gson.*;

public class Main {
    public static int[] version = new int[] {0 , 2};

    public static void main(String[] args) throws IOException, NullPointerException {
        try {
            System.out.println("Starting BULLSHIT GRADLE CLONE!");
            boolean update = checkforupdate();
            if (update) {
                System.out.println("Update available");
            } else {
                System.out.println("Running latest!");
            }
            getjsondata();
            creategroupID();
            System.out.println("Checking for commands");
            if (args[0].equals("compile")) {
                    compile(Config.MainClassfile);
            } else if (args[0].equals("run")) {
                run(Config.MainClass);
            } else {
                COLOREDLOG("Invalid command", "red");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Arguments array index is out of bounds which basically means you didn't give it any arguments, its not an error");
        }
    }

    public static void getjsondata() {
        /*
        the json file should look like this:
        {
        "net": "net",
        "name": "example",
        "projectname": "projectname",
        "projectnameingroupid": "projectnameingroupid"
         }
         projectnameingroupid is pretty broken rn so dont even use it
         */
        try {
            System.out.println("Getting JSON Data");
            Gson gson = new Gson();
            Path path = Paths.get("config.json");
            Reader reader = Files.newBufferedReader(path);
            System.out.println("config file: " + path);
            Map<?, ?> map = gson.fromJson(reader, Map.class);
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (entry.getKey().equals("net")) {
                    Config.net = (String) entry.getValue();
                } else if (entry.getKey().equals("name")) {
                    Config.name = (String) entry.getValue();
                } else if (entry.getKey().equals("projectname")) {
                    Config.projectname = (String) entry.getValue();
                } else if (entry.getKey().equals("projectnameingroupid")) {
                    Config.projectnameingroupid = (String) entry.getValue();
                } else if (entry.getKey().equals("MainClass")) {
                    Config.MainClass = (String) entry.getValue();
                    Config.MainClassfile = Config.MainClass + ".java";
                } else if (entry.getKey().equals("deftask")){
                    runtask((String) entry.getKey());
                } else {
                    //🤷‍♀️
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
            Path path = Paths.get("script.json");
            Reader reader = Files.newBufferedReader(path);
            System.out.println("script config file: " + path);
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
        switch (color) {
            case "red":
                System.out.println("\u001B[31m" + log + "\u001B[37m");
                break;
            case "blue":
                System.out.println("\u001B[34m" + log + "\u001B[37m");
                break;
            case "green":
                System.out.println("\u001B[32m" + log + "\u001B[37m");
                break;
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

    public static String getversion() {
        String ver = String.valueOf(version[0] + "" + version[1]);
        return ver;
    }

    public static boolean checkforupdate() throws IOException, NullPointerException {
        URL url = new URL("https://raw.githubusercontent.com/unnaturalistic/BsGradleClone/main/version.txt");
        BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
        String i;
        i = read.readLine();
        read.close();
        if (!i.equals(getversion())) {
            return true;
        } else {
            return false;
        }
    }
}