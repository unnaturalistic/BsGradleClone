package net.unnatural;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;


public class Main {
    //public static int[] version = new int[] {0 , 2};

    public static void main(String[] args) throws IOException, NullPointerException {
        try {
            System.out.println("Starting BULLSHIT GRADLE CLONE!");
            //boolean update = checkforupdate();
            //if (update) {
                //System.out.println("Update available");
            //} else {
                //System.out.println("Running latest!");
            //}
            TaskManager.getjsondata();
            creategroupID();
            System.out.println("Checking for commands");
            //here's how tasks work
            //every main call a certain task will get executed which is defined in the
            //deftask value in the json
            //if the cli arguments are not integrated in the code base
            //it will call the script.json reader to execute the one which is named the same as the cli argument
            if (args[0].equals("compile")) {
                compile(Config.MainClassfile);
            } else if (args[0].equals("run")) {
                run(Config.MainClass);
            } else if (args[0].equals("tasks")) {
                System.out.println(Config.taskmap);
            } else {
                TaskManager.runtask(args[0]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No arguments!");
            System.exit(0);
        }
    }

    public static void compile(String file) {
        try {
            System.out.println("Trying to compile");
            ProcessBuilder builder;
            if (Config.projectnameingroupid != null) {
                builder = new ProcessBuilder("cmd.exe", "/c", "javac " + "-sourcepath " + Config.net + "/" + Config.name + "/" + Config.projectnameingroupid + "/" + file + " -d ./build/");

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
            } else {
                builder = new ProcessBuilder("cmd.exe", "/c", "cd " + Config.net + "/" + Config.name + "/" + "&&" + "javac " + file + " -d ../../build/ ");

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
            case "yellow":
                System.out.println("\u001B[33m" + log + "\u001B[37m");
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

    //@SuppressWarnings({"UnnecessaryCallToStringValueOf", "ConcatenationWithEmptyString"})
    //public static String getversion() {
        //the empty string is needed
        //to check for
        //the joined version           this lil guy
        //string, not the sum               |
        //02, not 2                        \/
        //return String.valueOf(version[0] + "" + version[1]);
    //}

    //public static boolean checkforupdate() throws IOException, NullPointerException {
        //URL url = new URL("https://raw.githubusercontent.com/unnaturalistic/BsGradleClone/main/version.txt");
        //BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
        //String i;
        //i = read.readLine();
        //read.close();
        //return !i.equals(getversion());
    //}

    public static void TaskList() {
        for (Map.Entry<String, String> entry : Config.taskmap.entrySet()) {
            COLOREDLOG(entry.getKey() + " = " + entry.getValue(), "yellow");
        }
    }
}