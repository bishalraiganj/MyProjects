package Adhikary.X;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;

public class Deserializer {

	public static void main(String... args)
	{

		IndexMap iMap;
		try(ObjectInputStream ois  = new ObjectInputStream(new FileInputStream(Path.of("indexMap.dat").toFile()))) {

			iMap =(IndexMap) ois.readObject();
			System.out.println(iMap);
		}catch(IOException  | ClassNotFoundException e)
		{
			e.printStackTrace();
		}






	}


}
