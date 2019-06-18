import java.util.Scanner;
import java.lang.reflect.Array;
import java.math.*;
import org.ioblako.math.calculator.CalCEF;
import org.ioblako.math.calculator.jc;

public class EulerRSA {

	public static void main(String[] args) throws Exception {
		
		// ----------------- Input ----------------
		
		// Instantiate Scanner object
		Scanner scan = new Scanner(System.in);
		
		// Instantiate MathContext for precision of .eval() method
		MathContext settings = new MathContext(5000);
		jc.MC = settings;
		
		// Collect decryption key and n (p*q)
		System.out.println("Enter decryption key: ");
		int d = scan.nextInt();
		
		System.out.println("Enter p: ");
		int p = scan.nextInt();
		System.out.println("Enter q: ");
		int q = scan.nextInt();
		int n = p * q;
		
		// ---------------- Creating array of messages ----------------
		
		int[] messages = new int[144];
		
		// Messages from 2 to 135
		
		for (int i = 2; i < messages.length + 2; i++) {
			
			messages[i - 2] = i;
			
		}
		
		// Creates 2D array to display results
		int[][] solutions = new int[messages.length][4];
		
		for (int i  = 0; i < messages.length; i++) {
			
			solutions[i][0] = messages[i];
			
		}
		
		// Create CalCEF to run Euler function
		CalCEF c = new CalCEF();
		int euler;
		

		
		// ---------------- Calculations ----------------
		
		// for each message
		for (int k = 0; k < messages.length; k++) {
			
			int i = 0;
			euler = Integer.parseInt(c.eval(Integer.toString(messages[k]) + "," + Integer.toString(n)));
			// euler = Integer.parseInt(c.eval(Integer.toString(messsages[k]) + "," + Integer.toString(n)));
			
			// first two solutions for each message
			for (int j = 1; j <= 2; j++) {
			
				
			
				while ((euler * i + 1) % d != 0) {
		
					i++;
			
				}
				
				// System.out.println("i is: " + i);
				// System.out.println("Encryption key: " + (euler * i + 1) / d);
				
				solutions[k][j] = (euler * i + 1) / d;
			
				// Increments i so that it won't show the same solution over and over again
				i++;
	
			} // end of for-loop
			
			// Calculates difference between terms
			solutions[k][3] = solutions[k][2] - solutions[k][1];
	
		} // end of outer for-loop
		
		// ---------------- Displays results ----------------
		
		for (int row = 0; row < messages.length; row++) {
			
			for (int col = 0; col < 4; col++) {
				
				if (col == 0) {
					
					System.out.print("Message is " + solutions[row][col] + ": ");
					
				} else if (col == 3) { 
				
					System.out.println("Difference: " + solutions[row][3]);
					
				} else {
					
					System.out.print(solutions[row][col] + " ");
					
				}
				
			}
			
		}
		
		
		
	}	
}

// You know in advance d (113) and euler (6)

// Possible valid integer values of k: Solution Number 1 + EF(d,n) * v, where v is a positive integer

// Interval between solutions for any message is a factor of (p-1) * (q-1) / 2
