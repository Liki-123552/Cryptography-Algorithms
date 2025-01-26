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

    // Function to compute modular inverse using Extended Euclidean Algorithm
    public static long modInverse(long a, long p) {
        long t = 0;
        long newT = 1;
        long r = p;
        long newR = a;
        long quotient, temp;

        while (newR != 0) {
            quotient = r / newR;
            temp = t;
            t = newT;
            newT = temp - quotient * newT;
            temp = r;
            r = newR;
            newR = temp - quotient * newR;
        }

        if (r > 1) {
            return -1;  // Inverse doesn't exist
        }

        if (t < 0) {
            t = t + p;
        }
        return t;
    }

    // Key Generation
    public static long[] keyGeneration(long p, long e) {
        Random rand = new Random();
        // Step 1: Choose d such that 1 <= d <= p - 2
        long d = rand.nextInt((int) (p - 2)) + 1;
        
        // Step 2: Compute e1 = e^d mod p (Public Key Part 1)
        long e1 = modExp(e, d, p);  // Compute e^d % p
        
        // Public Key = (e1, e2, p), where e2 = e1^d % p
        long[] publicKey = {e1, e, p};  // e1 = e^d, e2 = e, p = large prime
        return publicKey;
    }

    // Encryption
    public static long[] encryption(long p, long e1, long e2, long pt) {
        Random rand = new Random();
        // Step 1: Choose random integer r in the range 1 <= r <= p - 1
        long r = rand.nextInt((int) (p - 1)) + 1;
        
        // Step 2: Compute C1 = e1^r mod p
        long c1 = modExp(e1, r, p);
        
        // Step 3: Compute C2 = (pt * e2^r) mod p
        long c2 = (pt * modExp(e2, r, p)) % p;
        
        // Return ciphertext (C1, C2)
        return new long[] {c1, c2};
    }

    // Decryption
    public static long decryption(long p, long d, long c1, long c2) {
        // Step 1: Compute the modular inverse of C1^d mod p
        long inverseC1 = modExp(c1, p - 1 - d, p);
        
        // Step 2: Compute pt = (C2 * inverse(C1^d)) % p
        long pt = (c2 * inverseC1) % p;
        
        // Return decrypted plaintext
        return pt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Enter a large prime number p
        System.out.print("Enter a large prime number p: ");
        long p = scanner.nextLong();

        // Step 2: Select a primitive root e modulo p
        System.out.print("Enter a primitive root e modulo p: ");
        long e = scanner.nextLong();

        // Step 3: Generate public and private keys
        long[] publicKey = keyGeneration(p, e);
        long e1 = publicKey[0];
        long e2 = publicKey[1];

        System.out.println("Public Key: (e1 = " + e1 + ", e2 = " + e2 + ", p = " + p + ")");
        
        // Step 4: Choose the private key 'd' from the output of keyGeneration
        System.out.print("Enter the private key d: ");
        long d = scanner.nextLong();

        // Step 5: Enter the plaintext message (as a number)
        System.out.print("Enter the plaintext to encrypt (as a number): ");
        long pt = scanner.nextLong();

        // Step 6: Encrypt the message
        long[] ciphertext = encryption(p, e1, e2, pt);
        long c1 = ciphertext[0];
        long c2 = ciphertext[1];

        System.out.println("Encrypted Message: (C1 = " + c1 + ", C2 = " + c2 + ")");

        // Step 7: Decrypt the message (using the private key)
        long decryptedPt = decryption(p, d, c1, c2);

        System.out.println("Decrypted Message: " + decryptedPt);

        scanner.close();
    }
}
/* output
Enter a large prime number p: 7919
Enter a primitive root e modulo p: 2
Public Key: (e1 = 3331, e2 = 2, p = 7919)
Enter the private key d: 1234
Enter the plaintext to encrypt (as a number): 1234
Encrypted Message: (C1 = 4210, C2 = 7113)
Decrypted Message: 1234
*/
