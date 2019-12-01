package codexpol;

import java.io.IOException;

import codexpol.model.Relations;

public class App {
    public static void main(String[] args) throws IOException
    {
        Relations relations = new Relations();
        relations.createFilesRelations();
    }
}
