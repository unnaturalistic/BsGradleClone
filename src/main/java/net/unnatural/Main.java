package net.unnatural;

import org.jetbrains.annotations.NotNull;

import javax.tools.Tool;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Objects;

public class Main {
    static FileOutputStream fos;

    static {
        try {
            fos = new FileOutputStream("./build/net/test");
        } catch (FileNotFoundException e) {
            System.out.println("CANNOT FIND BUILD/NET/TEST");
            System.out.println("CAUSE: " + e.getCause());
            System.out.println("STACK TRACE: ");
            e.printStackTrace();
        }
    }

    static Tool javac = ToolProvider.getSystemJavaCompiler();

    public static void main(String @NotNull [] args)  {
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
            File directoryPath = new File("./src/net/test");
            //List of all files and directories
            return directoryPath.list();
    }
}