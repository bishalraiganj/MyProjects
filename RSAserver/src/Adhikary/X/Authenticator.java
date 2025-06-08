package Adhikary.X;

public class Authenticator {


	public static boolean authenticate(LoginObject lo)
	{

		if(Server.credValidate(lo))
		{

			if(Server.authenticateLo(lo)) {


				return true;
			}


			return false;
		}
		return false;
	}


}
