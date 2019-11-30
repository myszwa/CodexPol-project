package codexpol.model;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class Relations {
    private static File directory;
    private HashSet<File> files;

    public Relations() throws IOException {
        this.files = new HashSet<>();

        this.createFilesSet();
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
}