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


		System.out.println("encrypted BigInteger : " + eInt);

		System.out.println("Cipher text (encrypted String) : " + cipherText );

		System.out.println("Decrypted String (Original String / plaintext : " + originalText);


		System.out.println("-".repeat(50));

		String plainText2 = "abcde@12345";

		BigInteger eInt2 = c1.encrypt(plainText2);

		String cipherText2 = c1.encryptMessageFrmBi(eInt2);

		String originalText2= c1.decryptString(cipherText2);


		System.out.println("encrypted BigInteger : " + eInt2);

		System.out.println("Cipher text (encrypted String) : " + cipherText2 );

		System.out.println("Decrypted String (Original String / plaintext : " + originalText2);





	}


}
