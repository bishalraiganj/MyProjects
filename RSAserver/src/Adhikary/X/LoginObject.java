package Adhikary.X;

import java.time.LocalDateTime;

public record LoginObject(String mail, String password, LocalDateTime timeStamp) {


	@Override
	public String toString()
	{
		return """
				{ Mail: %s | Password : %s | Time: %s }
				""".formatted(mail,password,timeStamp);
	}



}
