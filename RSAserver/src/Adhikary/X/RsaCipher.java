package Adhikary.X;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class RsaCipher {



		private	BigInteger p   = new BigInteger("128906340144552501918870869857677017465225228462220101028679965717175701119262283217694249602680944950567295932483152113164723932597619221823061528143528939849945250936108836041563509176008100342292574010355155134651938710345692412847190377236549592075382736641835672156028403252627147518001180281039623910279") ; //prime 1
		private	BigInteger q =  new BigInteger("103640630331502433822660769074622267052379319443280494758899317912987353070493821575860676367083655734315047484936609977404202601467647703950895476893241848656589460215103859322695600406600104456770426703949346166469281025982420705366816082908624642490371194218220921917757362254706959355068605044831345621409"); //prime 2
		private BigInteger n = p.multiply(q) ; // modulus

		private BigInteger eulerPhi = (p.subtract(BigInteger.valueOf(1L))).multiply( (q.subtract(BigInteger.valueOf(1L)))); // This is the totient function

		private BigInteger publicKey  = BigInteger.valueOf(65537L); //public key
		private BigInteger privateKey = publicKey.modInverse(eulerPhi); // private key





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
			System.out.println("Original BigInteger: " + val);

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
			System.out.println("Encrypted  BigInteger after string to BigInteger conversion : " + encrypted);

			BigInteger decrypted = decrypt(encrypted);

			System.out.println("Decrypted BigInteger (after decryption) :" + decrypted);

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
