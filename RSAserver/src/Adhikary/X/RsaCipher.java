package Adhikary.X;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class RsaCipher {



		private	BigInteger p   = BigInteger.valueOf(7L) ; //prime 1
		private	BigInteger q =  BigInteger.valueOf(11L) ; //prime 2
		private BigInteger n = p.multiply(q) ; // modulus

		private BigInteger eulerPhi = (p.subtract(BigInteger.valueOf(1L))).multiply( (q.subtract(BigInteger.valueOf(1L)))); // This is the totient function

		private BigInteger publicKey  = BigInteger.valueOf(7L); //public key
		private BigInteger privateKey = BigInteger.valueOf(43L); // private key





		public BigInteger encrypt(BigInteger m)
		{
			BigInteger c = m.modPow(publicKey,n);
			return c;

		}

		private BigInteger decrypt(BigInteger c)
		{
			BigInteger msgVal = c.modPow(privateKey,n);
			return msgVal;
		}

		public BigInteger encrypt(String m)
		{
			BigInteger val =new  BigInteger(1,m.getBytes(StandardCharsets.UTF_8));

			BigInteger c = encrypt(val);
			return c;


		}

		public String  encryptMessageFrmBi(BigInteger c) // converts encrypted bigInteger to string using Base64
		{

			byte[] bArr = c.toByteArray();
			String str = Base64.getEncoder().encodeToString(bArr);
			return str;

		}


		public String decryptString(String c) // This decrypt method reads password from database in string format
		{
			byte[] bArr = Base64.getDecoder().decode(c);
			BigInteger encrypted = new BigInteger(bArr);

			BigInteger decrypted = decrypt(encrypted);

			byte[] decryptedBytes = decrypted.toByteArray();



			return decrypt(decryptedBytes);




		}
		private String decrypt(byte[] c)
		{
			byte[] decryptedBytes ;
			if(c.length > 1 && c[0] == 0)
			{
				decryptedBytes = Arrays.copyOfRange(c,1,c.length);
			}
			else
			{
				decryptedBytes = c;
			}
			return new String(decryptedBytes,StandardCharsets.UTF_8);


		}



}
