import java.util.*;
import java.math.*;
import java.nio.charset.StandardCharsets;

public class rsa {
    public static void main(String[] args) {
        BigInteger p, q, N, phi, e, d;

        // Scanner object for input
        Scanner sc = new Scanner(System.in);

        // Input p and q from the user
        System.out.println("Enter the value for prime number p:");
        p = new BigInteger(sc.nextLine());
        System.out.println("Enter the value for prime number q:");
        q = new BigInteger(sc.nextLine());

        // Calculate N and φ (phi)
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Generate public key (e)
        e = BigInteger.probablePrime(512, new Random());

        // Ensure e and phi are co-prime (gcd(e, phi) = 1) & 0 < e < phi
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }

        // Calculate private key (d)
        d = e.modInverse(phi);

        // Display keys
        System.out.println("\nPrime number p: " + p);
        System.out.println("Prime number q: " + q);
        System.out.println("Value of N (p * q): " + N);
        System.out.println("Value of φ (phi): " + phi);
        System.out.println("Public key (e): " + e);
        System.out.println("Private key (d): " + d);

        // Encrypt and decrypt a message
        System.out.print("\nEnter the plain text: ");
        String plainText = sc.nextLine();
        System.out.println("Encrypting String: " + plainText);

        // Encrypt each character of the string
        List<BigInteger> encryptedChars = new ArrayList<>();
        for (char c : plainText.toCharArray()) {
            encryptedChars.add(BigInteger.valueOf(c).modPow(e, N));
        }

        // Decrypt each character back to plain text
        StringBuilder decryptedText = new StringBuilder();
        for (BigInteger encryptedChar : encryptedChars) {
            decryptedText.append((char) encryptedChar.modPow(d, N).intValue());
        }

        // Display encrypted and decrypted results
        System.out.print("Encrypted Bytes: ");
        for (BigInteger encryptedChar : encryptedChars) {
            System.out.print(encryptedChar + " ");
        }
        System.out.println();

        System.out.println("Decrypted String: " + decryptedText.toString());
    }
}
