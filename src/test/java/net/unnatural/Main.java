package net.unnatural;

import org.jetbrains.annotations.NotNull;
import com.moandjiezana.toml.Toml;

import javax.tools.Tool;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class Main {
    //\/default value\/
    public static String GroupID = "./build/net/test";
    static FileOutputStream fos;
    static File tomlfile = new File("./config.toml");
    static Toml toml = new Toml().read(tomlfile);

    static {
        try {
            fos = new FileOutputStream(GetToml());
        } catch (FileNotFoundException e) {
            System.out.println("AN ERROR OCCURRED WHILE TRYING TO READ TOML FILE");
            System.out.println("CAUSE: " + e.getCause());
            System.out.println("STACK TRACE: ");
            e.printStackTrace();
            System.out.println("Creating file");
            createToml();
            try {
                fos = new FileOutputStream(GetToml());
            } catch (FileNotFoundException ex) {
                System.out.println("Im not doing another loop fuck you");
            }
        }
    }

    static Tool javac = ToolProvider.getSystemJavaCompiler();

    public static void main(String @NotNull [] args)  {
        System.out.println("Configuring toml");
        GetToml();
        System.out.println("Getting files");
        String[] files = getFiles();
        System.out.println("Compiling");
        javac.run(null, fos, null, files);
        System.out.println("Done");
        if (Objects.equals(args[0], "runa")) {
            System.out.println("Run after not implemented");
        }
    }

    public static String[] getFiles() {
        //Creating a File object for directory
        File directoryPath = new File(GetToml());
        //List of all files and directories
        return directoryPath.list();
    }

    public static String GetToml() {
        GroupID = toml.getString("GroupID");
        return GroupID;
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
}