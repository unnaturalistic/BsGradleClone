/*
 NOTES:
System.out.println("\u001B[31mRed text");
System.out.println("\u001B[32mGreen text");
System.out.println("\u001B[33mYellow text");
System.out.println("\u001B[34mBlue text");
System.out.println("\u001B[35mPurple text");
System.out.println("\u001B[36mCyan text");
System.out.println("\u001B[37mWhite text");
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
         System.out.println("Starting BULLSHIT GRADLE CLONE!");
         getjsondata();
         creategroupID();
         System.out.println("Checking for commands");
         System.out.println(args.length);
         System.out.println(args[0]);
         if (args[0].equals("compile")) {
             compile("main.java");
         } else if (args[0].equals("run")) {
             run("main");
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
                 if (entry.getKey() == "net") {
                     Config.net = (String) entry.getValue();
                 } else if (entry.getKey() == "name") {
                     Config.name = (String) entry.getValue();
                 } else if (entry.getKey() == "projectname") {
                     Config.projectname = (String) entry.getValue();
                 } else if (entry.getKey() == "projectnameingroupid") {
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
             COLOREDLOG("CONFIG DOES NOT EXIST!", "red");
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
 }