package codexpol;

import java.awt.*;
import java.io.IOException;

import codexpol.model.ApplicationsWindow;
import codexpol.model.Relations;

public class App {
    public static void main(String[] args) throws IOException
    {
        //Relations relations = new Relations();
        //relations.createFilesRelations("codexpol", "src/main/java/codexpol");

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ApplicationsWindow();
            }
        });

    }
}
