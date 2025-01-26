import java.util.Scanner;

public class affine {

    // Function to compute modular inverse of 'a' mod 26
    public static int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1; // No modular inverse exists
    }

    // Function to encrypt plaintext using Affine Cipher
    public static String affineEncrypt(String plaintext, int k1, int k2) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (Character.isAlphabetic(ch)) {
                ch = Character.toUpperCase(ch); // Convert to uppercase if it's lowercase
                int gamma = (k1 * (ch - 'A') + k2) % 26;  // Gamma = (P*k1 + k2) mod 26
                ciphertext.append((char) (gamma + 'A'));
            } else {
                ciphertext.append(ch);  // Non-alphabetic characters are not encrypted
            }
        }
        return ciphertext.toString();
    }

    // Function to decrypt ciphertext using Affine Cipher
    public static String affineDecrypt(String ciphertext, int k1, int k2) {
        int k1Inverse = modInverse(k1, 26);
        if (k1Inverse == -1) {
            return "Error: 'k1' and 26 are not coprime, decryption not possible.";
        }

        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);
            if (Character.isAlphabetic(ch)) {
                ch = Character.toUpperCase(ch); // Convert to uppercase if it's lowercase
                int gamma = (ch - 'A' - k2 + 26) % 26;  // Gamma = (C - k2) mod 26
                int pt = (k1Inverse * gamma) % 26;  // P = (Gamma * k1^-1) mod 26
                plaintext.append((char) (pt + 'A'));
            } else {
                plaintext.append(ch);  // Non-alphabetic characters are not decrypted
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input plaintext
        System.out.print("Enter the text to encrypt (uppercase letters only): ");
        String text = scanner.nextLine();

        // Input keys
        System.out.print("Enter the value of 'k1' (multiplicative key): ");
        int k1 = scanner.nextInt();
        System.out.print("Enter the value of 'k2' (additive key): ");
        int k2 = scanner.nextInt();

        // Encrypting the text
        String encryptedText = affineEncrypt(text, k1, k2);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypting the text
        String decryptedText = affineDecrypt(encryptedText, k1, k2);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}

/*output
Enter the text to encrypt (uppercase letters only): HI
Enter the value of 'k1' (multiplicative key): 5
Enter the value of 'k2' (additive key): 8
Encrypted Text: RW
Decrypted Text: HI


Enter the text to encrypt (uppercase letters only): hehbdskdj
Enter the value of 'k1' (multiplicative key): 5
Enter the value of 'k2' (additive key): 8
Encrypted Text: RCRNXUGXB
Decrypted Text: HEHBDSKDJ
*/