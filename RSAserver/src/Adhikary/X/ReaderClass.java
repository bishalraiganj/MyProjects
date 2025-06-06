package Adhikary.X;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReaderClass {

	public static void main(String... args)
	{

		String path = "users.txt";
		System.out.println(read(path));

		System.out.println("-".repeat(50));

		System.out.println(read(path));

		byte[] byteDat = read(path).getBytes();

		System.out.println("-".repeat(50));

		System.out.println(extractPass(read("users.txt")));









	}

	private static String extractPass(String path,long offset)
	{
		Pattern p  = Pattern.compile("\\$\\|\\$.{8,16}");
		Matcher m = p.matcher(userCreds);

		try(RandomAccessFile raf = new RandomAccessFile(Path.of(path).toFile(),"rw"))
		{
			raf.seek(offset);



		}catch(IOException e)
		{
			e.printStackTrace();
		}
		m.find();

		return m.group();



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
