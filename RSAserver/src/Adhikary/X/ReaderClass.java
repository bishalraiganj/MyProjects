package Adhikary.X;

import java.io.*;
import java.nio.file.Path;
import java.util.Map;
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

	private static String extractPass(String userName)
	{
		Pattern p  = Pattern.compile("\\$\\|\\$.{8,16}");
		String userCred ;
		String pass;




		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(Path.of("indexMap.dat").toFile())));
				RandomAccessFile raf = new RandomAccessFile(Path.of("users.txt").toFile(),"rw"))
		{


				long offset ;
				IndexMap indexMapObject = (IndexMap) ois.readObject();
			    Map<String, Long> map = indexMapObject.getIndexMap();
				if(map.containsKey(userName))
				{
					offset = map.get(userName);
					raf.seek(offset);
					userCred = raf.readUTF();
					Matcher m = p.matcher(userCred);
					if(m.find())
					{
						pass = m.group();
					}

					throw new RuntimeException("no pass found");

				}





		}catch(IOException  | ClassNotFoundException e )
		{
			e.printStackTrace();
		}

		throw new RuntimeException("no pass found");



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
