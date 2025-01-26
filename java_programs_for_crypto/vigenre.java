import java.util.Scanner;

public class vigenre {

    // Function to encrypt text using Vigenère Cipher
    public static String vigenereEncrypt(String plaintext, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyLen = key.length();
        for (int i = 0, j = 0; i < plaintext.length(); i++) {
            char pChar = plaintext.charAt(i);
            if (Character.isLetter(pChar)) {
                char offset = Character.isUpperCase(pChar) ? 'A' : 'a';
                encryptedText.append((char) (((pChar - offset + key.charAt(j % keyLen) - 'A') % 26) + offset));
                j++;
            } else {
                encryptedText.append(pChar);  // Non-alphabet characters are added without change
            }
        }
        return encryptedText.toString();
    }

    // Function to decrypt text using Vigenère Cipher
    public static String vigenereDecrypt(String ciphertext, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLen = key.length();
        for (int i = 0, j = 0; i < ciphertext.length(); i++) {
            char cChar = ciphertext.charAt(i);
            if (Character.isLetter(cChar)) {
                char offset = Character.isUpperCase(cChar) ? 'A' : 'a';
                decryptedText.append((char) (((cChar - offset - (key.charAt(j % keyLen) - 'A') + 26) % 26) + offset));
                j++;
            } else {
                decryptedText.append(cChar);  // Non-alphabet characters are added without change
            }
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take input text and key from the user
        System.out.print("Enter the text to encrypt (uppercase letters only): ");
        String text = scanner.nextLine().toUpperCase();  // Ensure the text is in uppercase

        System.out.print("Enter the key (uppercase letters only): ");
        String key = scanner.nextLine().toUpperCase();  // Ensure the key is in uppercase

        // Encrypt the message
        String encryptedText = vigenereEncrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the message
        String decryptedText = vigenereDecrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
/*output
Enter the text to encrypt (uppercase letters only): HELLO
Enter the key (uppercase letters only): KEY
Encrypted Text: RIJVS
Decrypted Text: HELLO
*/