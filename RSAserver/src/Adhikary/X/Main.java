package Adhikary.X;

public class Main {

 // 14-06-2025 project is in perfect shape apart from IndexMap logic (old user data is retained thus retrieval logic failing)

	public static void main(String... args)
	{

		Server gameServer = new Server();
		gameServer.serializeIndexMap();
		Server.registerUser();


//		Server.primeGenerator();
//
//		System.out.println(Server.isPrime(967));

	}


}
