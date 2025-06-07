package Adhikary.X;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Server {


	public static void registerUser()
	{
		int loopCounter = 1;
		Scanner sc = new Scanner(System.in);
		String data ;
		do {
			if(loopCounter > 1 )
			{
				System.out.println("           INVALID EMAIL ADDRESS ! Try again ;-)         ");
			}

			System.out.println("Enter your mail :  ");
			data = sc.nextLine();
			loopCounter++;
		}
		while(!validateMail(data));
		System.out.println( "Email : " + data);

		int loopCounter2 = 1;
		String data2 ;

		do {
			if(loopCounter2 >1 )
			{
				System.out.println(" INVALID PASSWORD FORMAT ! (ENTER a password between 8-16 chars and at least one Special character  ! ;-)  ");
			}

			System.out.println("Enter your password: ");
			data2 = sc.nextLine();
			loopCounter2++;

		}while(!validatePassword(data2));

		System.out.printf("""
				Your are registered as 
				 email:%s   |  password : %s 
				""",data,data2);

		storeNewUserData(data,data2);


	}





	private static boolean validateMail(String data)
	{
		Stream<String> lines = data.lines();
		String mail = lines.
				limit(1)
				.collect(Collectors.joining(""));

		Pattern p = Pattern.compile("(^[0-9a-zA-Z]+([-._][0-9a-zA-Z]+)*)@(([0-9a-zA-Z]+([-_.][0-9a-zA-Z]+)*)\\.([0-9a-zA-Z]{2,}))");
		Matcher m = p.matcher(mail);

		return m.matches();


	}





	// Matches if the passed string argument contains 8-16 characters and has atleast one special character "@!%$*?#&<>()\][ "
	private static boolean validatePassword(String data)
	{
		Stream<String> lines =
				data.lines();

		String pass = lines.
				limit(1)
				.collect(Collectors.joining(""));

		Pattern p = Pattern.compile("([0-9a-zA-Z@!%$*?#&<>()\\]\\[]{8,16})");
		Pattern specialCharPattern = Pattern.compile("[@!#%&*$?><)(\\[\\]]");
		Matcher m = p.matcher(pass);
		Matcher specialCharMatcher  = specialCharPattern.matcher(pass);
		if(m.matches() && specialCharMatcher.find())
		{
			return true;
		}
		return false;

	}


	public void serializeIndexMap()
	{
		IndexMap indexMap;


		if (!Files.exists(Path.of("indexMap.dat"))) {
		try {
			Files.createFile(Path.of("indexMap.dat"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

		try {
			if (Files.size(Path.of("indexMap.dat")) == 0) {

				indexMap = new IndexMap();
				try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(Path.of("indexMap.dat").toFile())))) {

					oos.writeObject(indexMap);
				}


			}
		}catch(IOException e) {
			e.printStackTrace();
		}


	}





	private static boolean storeNewUserData(String mail,String password)
	{
		String appendedData ="%|%" + mail + "$|$"+ password;
		Path path = Path.of("users.txt");

		try
		{

			if(!Files.exists(path))
			{
				Files.createFile(path);
			}


		}catch(IOException e)
		{
			System.out.println("Error Message: " + e.getMessage());
		}


		IndexMap indexMap ;

		if(!Files.exists(Path.of("indexMap.dat")))
		{
			try {
				Files.createFile(Path.of("indexMap.dat"));
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}




		try(
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("indexMap.dat")));

			RandomAccessFile raf = new RandomAccessFile(path.toFile(),"rw") ;
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path.toFile()))
			)
		{

			try {


				indexMap = (IndexMap) ois.readObject();

				long offset1 = raf.length();
				raf.seek(raf.length());


				raf.writeUTF(appendedData);


				indexMap.getIndexMap().put(mail,offset1);


				try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("indexMap.dat")))) {

					oos.writeObject(indexMap);
				}
				return true;

			}catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}





		}catch(IOException e)
		{
			e.printStackTrace();
		}


		return false;

	}






	public static long primeGenerator() {
		Random r = new Random();
		long num = r.nextLong(1013, 1015);

		long primeCounter =  2 ;

		long prime  = 0;



		for(int i = 5 ; primeCounter < num ; i += 6)
		{
			int j = i + 2;

			if(isPrime(i))
			{
				prime = i;
				primeCounter++;

			}

			if(isPrime(j) && primeCounter < num)
			{
				prime = j;
				primeCounter++;
			}
		}


		System.out.println("RandomValue (Prime Serial Number : " + num + ") Prime: " + prime  );
			return prime;

	}






	public static boolean isPrime(long n)
	{

		if( n < 0)
		{
			return false;
		}
		if(( n % 2 == 0  && n != 2 )   || ( n % 3 == 0  && n != 3 ) )
		{
			return false;
		}

		for(int i = 5 ; i <= Math.sqrt(n) ; i += 6)
		{

			if(n % i == 0 || n % (i + 2 ) == 0 )
			{
				return false;
			}
		}

		return true;

	}


	public static void rsaCipher(long n)
	{



	}


}
