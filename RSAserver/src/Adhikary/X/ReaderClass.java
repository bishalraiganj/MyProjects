package Adhikary.X;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

public class ReaderClass {

	public static void main(String... args)
	{

		String path = "users.txt";
		System.out.println(read(path));


	}


	public static String read(String path)
	{
		String line ;
		try(RandomAccessFile raf = new RandomAccessFile(path,"rw"))
		{
			line = raf.readUTF();

		}catch(IOException e)
		{
			throw new RuntimeException(e);
		}

		return line;

	}



}
