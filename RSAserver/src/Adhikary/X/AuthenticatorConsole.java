package Adhikary.X;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AuthenticatorConsole {


	private static Scanner sc = new Scanner(System.in);

	public static void main(String... args)
	{



		consoleMenu();





	}

	private static void consoleMenu()
	{


		System.out.print("""
				                                                                       Bank Server 1.0
				----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
				
																		  1. EMPLOYEE LOGIN 
																		  2. USER LOGIN
																		  
																	press 1 or 2 to for respective login :-) 	  
				
				
				""");

		int choice = sc.nextInt();
		switch(choice)
		{
			case 1 : employeeLogin();
			break;
			case 2 : System.out.println("Feature Under Development ! ") ; consoleMenu();
			break;
			default : System.out.println("Wrong choice ! Try again !"); consoleMenu();
		}






	}


	private static void employeeLogin()
	{

		String usermail ;
		String password ;
		System.out.println("""
				                                                               -----   Welcome to BAbanK of INDIA ----
				
																			 Enter usermail : 
				""");

		sc.nextLine();
		usermail = sc.nextLine();

		System.out.println("""
				
																			 Enter password :
		
				""");
		password = sc.nextLine();

		LoginObject lo = new LoginObject(usermail,password,LocalDateTime.now());

		if(Authenticator.authenticate(lo))
		{
			System.out.println("Login Successful");
			System.out.println(lo);
		}

	}





}
