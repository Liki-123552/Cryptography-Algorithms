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

        // Step 2: Select a primitive root e modulo p
        System.out.print("Enter a primitive root e modulo p: ");
        long e = scanner.nextLong();

        // Step 3: Choose private key d such that 1 <= d <= p - 2
        long d = rand.nextInt((int) (p - 2)) + 1;
        System.out.println("Selected private key d: " + d);

        // Step 4: Compute e1 = e^d mod p (Public Key Part 1)
        long e1 = modExp(e, d, p);
        System.out.println("Computed e1 = " + e1);

        // Step 5: Compute e2 = e (primitive root modulo p)
        long e2 = e;
        System.out.println("Computed e2 = " + e2);

        // Step 6: Enter the plaintext message (as a number)
        System.out.print("Enter the plaintext to encrypt (as a number): ");
        long pt = scanner.nextLong();

        // Step 7: Choose random integer r in the range 1 <= r <= p - 1
        long r = rand.nextInt((int) (p - 1)) + 1;
        System.out.println("Selected random integer r: " + r);

        // Step 8: Compute C1 = e1^r mod p
        long c1 = modExp(e1, r, p);
        System.out.println("Computed C1 = " + c1);

        // Step 9: Compute C2 = (pt * e2^r) mod p
        long c2 = (pt * modExp(e2, r, p)) % p;
        System.out.println("Computed C2 = " + c2);

        // Step 10: Decrypt the message
        // Compute the modular inverse of C1^d mod p
        long inverseC1 = modExp(c1, p - 1 - d, p);
        long decryptedPt = (c2 * inverseC1) % p;
        System.out.println("Decrypted plaintext: " + decryptedPt);

        scanner.close();
    }
}/*
  output
    Enter a large prime number p: 7919
Enter a primitive root e modulo p: 2
Selected private key d: 3456
Computed e1 = 2114
Computed e2 = 2
Enter the plaintext to encrypt (as a number): 1234
Selected random integer r: 5678
Computed C1 = 7425
Computed C2 = 2863
Decrypted plaintext: 1234

Enter a large prime number p: 23
Enter a primitive root e modulo p: 5
Selected private key d: 6
Computed e1 = 8
Computed e2 = 5
Enter the plaintext to encrypt (as a number): 15
Selected random integer r: 4
Computed C1 = 4
Computed C2 = 7
Decrypted plaintext: 15
*/
    
