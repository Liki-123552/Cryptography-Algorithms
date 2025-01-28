import java.util.Random;
import java.util.Scanner;

public class elgamal {

    // Function to compute modular exponentiation (base^exp % mod)
    public static long modExp(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            exp = exp >> 1;
            base = (base * base) % mod;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        // Step 1: Enter a large prime number p
        System.out.print("Enter a large prime number p: ");
        long p = scanner.nextLong();

        // Step 2: Select a primitive root g modulo p
        System.out.print("Enter a primitive root g modulo p: ");
        long g = scanner.nextLong();

        // Step 3: Choose private key d such that 1 <= d <= p - 2
        long d = rand.nextInt((int) (p - 2)) + 1;
        System.out.println("Selected private key d: " + d);

        // Step 4: Compute e1 = g (Public Key Part 1)
        long e1 = g;
        System.out.println("Computed e1 = " + e1);

        // Step 5: Compute e2 = g^d mod p (Public Key Part 2)
        long e2 = modExp(g, d, p);
        System.out.println("Computed e2 = " + e2);

        // Step 6: Enter the plaintext message
        System.out.print("Enter the plaintext to encrypt (string or number): ");
        scanner.nextLine(); // Consume newline
        String plaintext = scanner.nextLine();

        // Step 7: Choose random integer r in the range 1 <= r <= p - 1
        long r = rand.nextInt((int) (p - 1)) + 1;
        System.out.println("Selected random integer r: " + r);

        // Encrypt and decrypt each character one by one
        StringBuilder encryptedText = new StringBuilder();
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            // Convert current character to numeric value
            long pt = plaintext.charAt(i) % p;

            // Encrypt each character
            long c1 = modExp(g, r, p);
            long c2 = (pt * modExp(e2, r, p)) % p;

            encryptedText.append("(").append(c1).append(", ").append(c2).append(") ");

            // Decrypt the character
            long inverseC1 = modExp(c1, p - 1 - d, p);
            long decryptedPt = (c2 * inverseC1) % p;
            decryptedText.append((char) decryptedPt);
        }

        // Display encrypted and decrypted results
        System.out.println("Encrypted Text: " + encryptedText.toString());
        System.out.println("Decrypted Text: " + decryptedText.toString());

        scanner.close();
    }
}
