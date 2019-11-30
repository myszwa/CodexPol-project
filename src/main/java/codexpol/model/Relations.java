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
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;

public class Relations {
    private static File directory;
    private HashSet<File> files;
    private Map<String,String> filesRelations;

    public Relations() throws IOException {
        this.files = new HashSet<>();
        this.filesRelations = new HashMap<>();
        this.configureJavaParser();
        this.createFilesSet();
        this.findRelationsBetweenFiles();
    }

    private void configureJavaParser() throws IOException {
        // stworzenie mapy wszystkich polaczen dla java parsera - wyeliminowanie bledow
        // - wiecej info Michal
        final String mavenPath = System.getProperty("java.class.path");
        final CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
        String[] path = mavenPath.split(";");
        for (int i = 1; i < path.length; i++) {
            combinedTypeSolver.add(new JarTypeSolver(path[i]));
        }
    }

    private void createFilesSet() {
        final String rootPath = "src/main/java/codexpol";
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

    private void findRelationsBetweenFiles() {
        this.files.forEach(file -> {
            try {
                CompilationUnit parsedFile = StaticJavaParser.parse(file);
                parsedFile.getImports().stream().forEach(fileImport -> {
                    Name name = fileImport.getName();
                    if (name.asString().startsWith("codexpol")) {
                        this.filesRelations.put(file.getName().split(".java")[0], name.getIdentifier());
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}