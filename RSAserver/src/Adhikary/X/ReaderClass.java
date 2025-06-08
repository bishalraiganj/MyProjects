package Adhikary.X;

import java.io.*;
import java.nio.file.Path;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// This class reads UTF-8 version of data directly from database and cannot read RSA encrypted data, I developed this for debugging purpose during the project's development phase :-)
public class ReaderClass {

	public static void main(String... args)
	{

//		String path = "users.txt";
//		System.out.println(read(path));
//
//		System.out.println("-".repeat(50));
//
//		System.out.println(read(path));
//
//		byte[] byteDat = read(path).getBytes();



		System.out.println("-".repeat(50));


//
//		System.out.println("usermail: b2@123.co " + "Found pass is -> " + extractPass("b2@123.co"));
//
//		System.out.println("-".repeat(50));
//
//		System.out.println("usermail: user1@123.com " + "Found pass is -> " + extractPass("user1@123.com"));
//
//		System.out.println("-".repeat(50));
//
//		System.out.println("usermail: a1@gmail.com " +  " Found pass is -> " + extractPass("a1@gmail.com"));
//
//		System.out.println("-".repeat(50));

		System.out.println("usermail: bishaladhikaryreal@gmail.com " + " | Found pass is -> " + retrieveRawPass("bishaladhikaryreal@gmail.com"));

		System.out.println("usermail: 6297609615@phone.com" + " | Found pass is -> " + retrieveRawPass("6297609615@phone.com"));

		System.out.println("usermail : krishnaadhikary@gmail.com  " + " | Found pass is -> " + retrieveRawPass("krishnaadhikary@gmail.com"));



		System.out.println("usermail : newmail@123.com " + " | Found pass is -> " + retrieveRawPass("newmail@123.com"));



	}

	protected static String extractPass(String userName) // This reads only 8-16 characters as-is
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
						return pass.replaceFirst("\\$\\|\\$","");
					}

					throw new RuntimeException("no pass found");

				}





		}catch(IOException  | ClassNotFoundException e )
		{
			e.printStackTrace();
		}

		throw new RuntimeException("no pass found");



	}




	private static String retrieveRawPass(String userName) // This reads the entire encrypted cipherText then decrypts using the RsaCipher types help
	{
		RsaCipher cipher = new RsaCipher();

		Pattern p  = Pattern.compile("\\$\\|\\$.+");
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
					return cipher.decryptString(pass.replaceFirst("\\$\\|\\$",""));
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
