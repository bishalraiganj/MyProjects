package Adhikary.X;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
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






	private static boolean storeNewUserData(String mail,String password)
	{
		String appendedData = mail + password;
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

		try(RandomAccessFile raf = new RandomAccessFile(path.toFile(),"rw") ;
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path.toFile())))
		{
			byte[] bArr = bis.readAllBytes();
			raf.seek(bArr.length);

			 raf.write(appendedData.getBytes());
			 return true;




		}catch(IOException e)
		{
			throw new RuntimeException(e);
		}





	}



}
