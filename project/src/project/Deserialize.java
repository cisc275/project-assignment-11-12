package project;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserialize {
	 public static Model findGame() throws Exception
	    {
	        FileInputStream fis = new FileInputStream("deserialize");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        Model model = (Model) ois.readObject();
	        ois.close();

	        return model;
	    }

}