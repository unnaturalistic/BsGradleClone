package net.unnatural;

import org.jetbrains.annotations.NotNull;
import com.moandjiezana.toml.Toml;

import javax.tools.Tool;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.Objects;

public class Main {
    //\/default value\/
    public static String mainclass;
    public static String GroupID = "gnawmon";
    static FileOutputStream fos;
    static File tomlfile = new File("./config.toml");
    static Toml toml;

    static {
        try {
            Toml toml = new Toml().read(tomlfile);
        } catch (RuntimeException e) {
            System.out.println("Cannot find toml");
            createToml();
        }
    }

    static {
        try {
            fos = new FileOutputStream(GetToml(1));
        } catch (FileNotFoundException e) {
            System.out.println("AN ERROR OCCURRED WHILE TRYING TO READ TOML FILE");
            System.out.println("CAUSE: " + e.getCause());
            System.out.println("STACK TRACE: ");
            e.printStackTrace();
            System.out.println("Creating file");
            createToml();
            try {
                fos = new FileOutputStream(GetToml(1));
            } catch (FileNotFoundException ex) {
                System.out.println("Im not doing another loop fuck you");
            }
        } catch (NullPointerException e) {
            System.out.println("Why");
        }
    }

    static Tool javac = ToolProvider.getSystemJavaCompiler();

    public static void main(String @NotNull [] args) throws Exception {
        taskmanager(args);
    }

    public static void taskmanager(String[] argsy) throws Exception {
        try {
            File directoryPathy = new File(GetToml(1));
            String mainclass = GetToml(2);
            System.out.println("Configuring toml");
            GetToml(1);
            System.out.println("Getting files");
            String[] files = getFiles();
            //\/ tasks \/
            if (Objects.equals(argsy[0], "runa")) {
                runProcess("java " + directoryPathy + mainclass);
            } else if (Objects.equals(argsy[0], "Compile")) {
                System.out.println("Compiling");
                javac.run(null, fos, null, files);
                System.out.println("Done");
            }
        } catch (NullPointerException e) {
            System.out.println("Why");
        }
    }

    public static String[] getFiles() {
        //Creating a File object for directory
        File directoryPath = new File(Objects.requireNonNull(GetToml(1)));
        //List of all files and directories
        return directoryPath.list();
    }

    public static String GetToml(int a) {
        try {
            GroupID = toml.getString("GroupID");
            mainclass = toml.getString("mainclass");
            if (a == 1) {
                return GroupID;
            } else if (a == 2) {
                return mainclass;
            } else {
                System.out.println("Invalid toml mode");
            }
            return null;
        } catch (NullPointerException e) {
            System.out.println("Toml Not Found!");
            return null;
        }
    }

    public static void createToml() {
        File tomlobj = new File("./config.toml");
        try {
            if (tomlobj.createNewFile()) {
                System.out.println("Created Toml");
            }
        } catch (IOException e) {
            System.out.println("AUGH");
        }
    }

    private static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd + " " + line);
        }
    }


    private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
    }

}