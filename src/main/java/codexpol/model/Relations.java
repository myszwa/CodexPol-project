package codexpol.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.Name;

public class Relations {
    private static File directory;
    private HashSet<File> files;
    private Map<String, String> filesRelations;

    public Relations() throws IOException {
        this.files = new HashSet<>();
        this.filesRelations = new HashMap<>();
    }

    public void createFilesRelations(String packageName, String rootPath) {
        this.createFilesSet(rootPath);
        this.findRelationsBetweenFiles(packageName);
        System.out.println(this.filesRelations);
    }

    private void createFilesSet(String rootPath) {
        directory = new File(rootPath);
        this.findAllFiles(directory);
    }

    private void findAllFiles(File path) {
        Arrays.stream(path.listFiles()).forEach(file -> {
            if (file.isDirectory()) {
                this.findAllFiles(file);
            } else {
                this.files.add(file);
            }
        });
    }

    private void findRelationsBetweenFiles(String packageName) {
        this.files.forEach(file -> {
            try {
                CompilationUnit parsedFile = StaticJavaParser.parse(file);
                parsedFile.getImports().stream().forEach(fileImport -> {
                    Name name = fileImport.getName();
                    if (name.asString().startsWith(packageName)) {
                        this.filesRelations.put(file.getName().split(".java")[0], name.getIdentifier());
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}