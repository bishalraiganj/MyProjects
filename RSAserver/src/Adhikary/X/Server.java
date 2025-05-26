package Adhikary.X;

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



}
