#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to compute gcd
int gcd(int a, int b) {
    if (b == 0)
        return a;
    return gcd(b, a % b);
}

// Function to compute modular inverse
int modInverse(int a, int m) {
    for (int x = 1; x < m; x++) {
        if ((a * x) % m == 1) {
            return x;
        }
    }
    return -1;
}

// Function to encrypt the message (either text or number)
int encrypt(int m, int e, int n) {
    int c = 1;
    for (int i = 0; i < e; i++) {
        c = (c * m) % n;
    }
    return c;
}

// Function to decrypt the message (either text or number)
int decrypt(int c, int d, int n) {
    int m = 1;
    for (int i = 0; i < d; i++) {
        m = (m * c) % n;
    }
    return m;
}

// Function to encrypt a string (convert each character to its ASCII value)
void encryptString(char* message, int e, int n, int* encryptedMessage) {
    int i = 0;
    while (message[i] != '\0') {
        encryptedMessage[i] = encrypt(message[i], e, n);
        i++;
    }
    encryptedMessage[i] = -1;  // End of message indicator
}

// Function to decrypt a string (convert each encrypted ASCII value back to character)
void decryptString(int* encryptedMessage, int d, int n, char* decryptedMessage) {
    int i = 0;
    while (encryptedMessage[i] != -1) {
        decryptedMessage[i] = (char)decrypt(encryptedMessage[i], d, n);
        i++;
    }
    decryptedMessage[i] = '\0';  // Null-terminate the decrypted string
}

int main() {
    int p, q, n, phi, e, d, m, c;
    char message[100], decryptedMessage[100];
    int encryptedMessage[100];
    
    // Step 1: Enter prime numbers p and q
    printf("Enter two prime numbers p and q: ");
    scanf("%d %d", &p, &q);
    
    // Step 2: Compute n = p * q and phi = (p-1)*(q-1)
    n = p * q;
    phi = (p - 1) * (q - 1);
    
    // Step 3: Choose e such that 1 < e < phi and gcd(e, phi) = 1
    printf("Enter the public exponent e: ");
    scanf("%d", &e);
    
    // Step 4: Compute d such that (d * e) % phi = 1
    d = modInverse(e, phi);
    
    // Step 5: Enter message to encrypt
    printf("Enter the message to encrypt (string or number): ");
    scanf(" %[^\n]%*c", message);  // Accept a string message (including spaces)
    
    // Check if the input is numeric (an integer)
    if (sscanf(message, "%d", &m) == 1) {
        // If input is a number, encrypt and decrypt it directly
        c = encrypt(m, e, n);
        int decryptedMessageNum = decrypt(c, d, n);
        printf("\nEncrypted Message: %d\n", c);
        printf("Decrypted Message: %d\n", decryptedMessageNum);
    } else {
        // If input is a string, process each character
        encryptString(message, e, n, encryptedMessage);
        decryptString(encryptedMessage, d, n, decryptedMessage);
        printf("\nEncrypted Message: ");
        for (int i = 0; encryptedMessage[i] != -1; i++) {
            printf("%d ", encryptedMessage[i]);  // Print each encrypted ASCII value
        }
        printf("\nDecrypted Message: %s\n", decryptedMessage);  // Print the decrypted string
    }
    
    return 0;
}
/*
output
Enter two prime numbers p and q: 61
53
Enter the public exponent e: 17
Enter the message to encrypt (string or number): heiehi

Encrypted Message: 2170 1313 3179 1313 2170 3179
Decrypted Message: heiehi*/