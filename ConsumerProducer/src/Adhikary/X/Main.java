package Adhikary.X;


class messageRepository{

	private String message;

	private boolean hasMessage = false;

	public synchronized String read()
	{
		while(!hasMessage)
		{

		}
		hasMessage = false;
		return message;
	}

	public synchronized void write(String message)
	{
		while(hasMessage)
		{

		}
		hasMessage = true;
		this.message = message;
	}
}
public class Main {

	public static void main(String... args)
	{





	}

}
