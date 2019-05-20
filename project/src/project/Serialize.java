package project;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serialize {
	public static void executeGame(Model model) throws Exception
    {
        FileOutputStream fos = new FileOutputStream("serialize");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(model);
        oos.close();
    }

}