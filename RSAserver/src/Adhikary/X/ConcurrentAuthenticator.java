package Adhikary.X;

import java.time.LocalDateTime;

public class ConcurrentAuthenticator {

// It is not truly concurrent YET ! cause : I have to make the entire project's critical sections thread safe , which i haven't done YET !
	public static boolean authenticate(String username , String password , LocalDateTime time)
	{
		LoginObject lo =  new LoginObject(username,password,time);
		if(Authenticator.authenticate(lo))
		{
			return true;
		}
		return false;

	}




}
