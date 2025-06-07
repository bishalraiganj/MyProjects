package Adhikary.X;

import java.math.BigInteger;

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





		}



}
