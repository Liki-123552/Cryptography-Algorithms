import java.util.Scanner;

public class extended_euclidian{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // User input for the number and modulus
        System.out.print("Enter a number to find its modular inverse: ");
        int a = sc.nextInt();
        System.out.print("Enter the modulus (m): ");
        int m = sc.nextInt();

        // Initialize values
        int q, r1, r2, r, t1, t2, t;

        // Initial values for r1, r2, t1, t2
        r1 = a;
        r2 = m;
        t1 = 1;  // t1 starts as 1 (corresponding to 'a')
        t2 = 0;  // t2 starts as 0 (corresponding to 'b')
        
        // Print the table headers
        System.out.printf("%-4s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s\n", "q", "r1", "r2", "r", "t1", "t2", "t");
        
        int step = 1;

        // Loop to calculate GCD and keep track of the values
        while (r2 != 0) {
            q = r1 / r2;       // Quotient
            r = r1 % r2;       // Remainder
            t = t1 - q * t2;   // New t value

            // Print the current state in the table format
            System.out.printf("%-4d | %-3d | %-3d | %-3d | %-3d | %-3d | %-3d\n", q, r1, r2, r, t1, t2, t);

            // Update values for next iteration
            r1 = r2;
            r2 = r;
            t1 = t2;
            t2 = t;
        }

        // The GCD will be r1 when the loop ends
        int gcd = r1;

        // Check if modular inverse exists
        if (gcd != 1) {
            System.out.println("No modular inverse exists for " + a + " mod " + m);
        } else {
            // Ensure the result is positive
            int modInverse = (t1 % m + m) % m;
            System.out.println("The modular inverse of " + a + " mod " + m + " is: " + modInverse);
        }

        sc.close();
    }
}/*output
Enter a number to find its modular inverse: 3
Enter the modulus (m): 7
q    | r1  | r2  | r   | t1  | t2  | t   
-----------------------------------------
1    | 3   | 7   | 4   | 1   | 0   | 1   
1    | 7   | 4   | 3   | 0   | 1   | -2  
1    | 4   | 3   | 1   | 1   | -2  | 3   
1    | 3   | 1   | 0   | -2  | 3   | -5  
The modular inverse of 3 mod 7 is: 5



Enter a number to find its modular inverse: 30
Enter the modulus (m): 12
q    | r1  | r2  | r   | t1  | t2  | t   
-----------------------------------------
2    | 30  | 12  | 6   | 1   | 0   | -2  
2    | 12  | 6   | 0   | 0   | 1   | 5   
No modular inverse exists for 30 mod 12
*/