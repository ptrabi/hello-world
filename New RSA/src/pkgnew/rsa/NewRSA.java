/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnew.rsa;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author my
 */
public class NewRSA {
    
        private final static SecureRandom random = new SecureRandom();
	private final static BigInteger one = new BigInteger("1");
	
	BigInteger N, phiN, p, q, PublicKey, PrivateKey;
    public NewRSA(){
        int keySize =1024;
        if (keySize < 512)
            throw new IllegalArgumentException("Key size too small.");
        SecureRandom rand = new SecureRandom();
        generatePQ(keySize / 2, rand);
        N = p.multiply(q);
        phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        generateExponents(invertibleSet());
    }
    private void generatePQ(int bitLength, Random rand)
    {
        while (true)
        {
            p = generateOddPrime(bitLength, rand);
            q = generateOddPrime(bitLength, rand);
            if (!p.equals(q))
                return;
        }
    }
    
    private BigInteger generateOddPrime(int bitLength, Random rand)
    {
        BigInteger two = new BigInteger("2");
        while (true)
        {
            BigInteger prime = BigInteger.probablePrime(bitLength, rand);
            if (!prime.mod(two).equals(BigInteger.ZERO))
                return prime;
        }
    }
    private void generateExponents(BigInteger[] invertibleSet)
    {
        Random rand = new Random();
        while (true)
        {
            BigInteger invertible = invertibleSet[rand
                    .nextInt(invertibleSet.length)];
            BigInteger inverse = invertible.modInverse(phiN);
            if (invertible.multiply(inverse).mod(phiN)
                    .equals(BigInteger.ONE.mod(phiN)))
            {
                PublicKey = invertible;
                PrivateKey = inverse;
                return;
            }
        }
    }
    private BigInteger[] invertibleSet()
    {
        final int maxSize = 100000;
        Set<BigInteger> invertibles = new HashSet<BigInteger>();
        BigInteger end = N.subtract(BigInteger.ONE);
        for (BigInteger i = new BigInteger("5"); i.compareTo(end) < 0; i = i
                .add(BigInteger.ONE))
        {
            if (i.gcd(phiN).equals(BigInteger.ONE))
            {
                invertibles.add(i);
                if (invertibles.size() == maxSize)
                    break;
            }
        }
        return invertibles.toArray(new BigInteger[invertibles.size()]);
    }
    public String encrypt(String plainText, BigInteger PublicKey, BigInteger Modulus) throws UnsupportedEncodingException
    {
        BigInteger msg = new BigInteger(plainText.getBytes());
        byte[] encrypted = msg.modPow(PublicKey, Modulus).toByteArray();
        return toHex(encrypted);
    }
    public String decrypt(String cipherText, BigInteger PrivateKey, BigInteger Modulus)
    {
        BigInteger encrypted = new BigInteger(cipherText, 16);
        return new String(encrypted.modPow(PrivateKey, Modulus).toByteArray());
    }
    private String toHex(byte[] bytes) throws UnsupportedEncodingException
    {
        BigInteger bi = new BigInteger(1, bytes);
        //return bytes.toString();
        //return new String(bytes, "US-ASCII");
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here        
    }
    
}
