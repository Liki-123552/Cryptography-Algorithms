/*#include <stdio.h>

// Function to find GCD using Euclidean Algorithm
int gcd(int a, int b) {
    if (b == 0)
        return a;
    return gcd(b, a % b);
}

// Extended Euclidean Algorithm to find modular inverse
int extended_gcd(int a, int b, int *x, int *y) {
    if (a == 0) {
        *x = 0;
        *y = 1;
        return b;
    }

    int x1, y1;
    int gcd = extended_gcd(b % a, a, &x1, &y1);

    *x = y1 - (b / a) * x1;
    *y = x1;

    return gcd;
}

// Function to find modular inverse of a mod m
int mod_inverse(int a, int m) {
    int x, y;
    int gcd = extended_gcd(a, m, &x, &y);
    if (gcd != 1)
        return -1; // No modular inverse exists
    else
        return (x % m + m) % m;
}

int main() {
    int a = 5, m = 26;

    int inv = mod_inverse(a, m);
    if (inv == -1)
        printf("No modular inverse exists for %d mod %d\n", a, m);
    else
        printf("Modular inverse of %d mod %d is %d\n", a, m, inv);

    return 0;
}
*/
#include <stdio.h>

// Function to implement the Extended Euclidean Algorithm
int extended_gcd(int a, int b, int *x, int *y) {
    if (b == 0) {
        *x = 1;
        *y = 0;
        return a; // gcd(a, b)
    }
    int x1, y1; // Temporary variables to store results of recursive call
    int gcd = extended_gcd(b, a % b, &x1, &y1);
    *x = y1;
    *y = x1 - (a / b) * y1;
    return gcd;
}

// Function to find the modular inverse
void modular_inverse(int a, int m) {
    int x, y;
    int gcd = extended_gcd(a, m, &x, &y);

    if (gcd != 1) {
        printf("No modular inverse exists for %d mod %d\n", a, m);
        return;
    }

    // Ensure the result is positive
    int mod_inverse = (x % m + m) % m;
    printf("The modular inverse of %d mod %d is: %d\n", a, m, mod_inverse);
}

int main() {
    int a, m;
    printf("Enter a number to find its modular inverse: ");
    scanf("%d", &a);
    printf("Enter the modulus (m): ");
    scanf("%d", &m);

    modular_inverse(a, m);

    return 0;
}

/*nter a number to find its modular inverse: 30
Enter the modulus (m): 12
No modular inverse exists for 30 mod 12

Enter a number to find its modular inverse: 3
Enter the modulus (m): 7
The modular inverse of 3 mod 7 is: 5
*/