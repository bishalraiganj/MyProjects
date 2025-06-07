package Adhikary.X;

import java.math.BigInteger;

public class EncryptionTester {

	public static void main(String... args)
	{

		RsaCipher c1 = new RsaCipher();

		String plainText = "Bishal Adhikary Testing RsaCipher class logic 07-06-25";

		BigInteger eInt = c1.encrypt(plainText);

		String cipherText = c1.encryptMessageFrmBi(eInt);

		String originalText= c1.decryptString(cipherText);


		System.out.println("encrypted BigInteger : " + c1.encrypt(plainText));

		System.out.println("Cipher text (encrypted String) : " + cipherText );

		System.out.println("Decrypted String (Original String / plaintext : " + originalText);



	}


}
