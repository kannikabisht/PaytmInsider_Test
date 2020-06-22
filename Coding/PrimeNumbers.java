package Package1;
import java.util.Scanner;

public class PrimeNumbers {

	public static void main(String[] args) {
		int count = 0; //Keeps count of number of 'Prime Numbers' found
		int startNum = 2;
		try {
			System.out.println("Enter the number of Prime Numbers to Print: ");
			Scanner sc = new Scanner(System.in);
			int input = Integer.parseInt(sc.nextLine());
			if(input >= 1 && input <= 100) {
				while(count != input) { 
				    boolean isPrime = true;
				    for (int i = 2; i <= Math.sqrt(startNum); i++) {
				        if (startNum % i == 0) {
				        	isPrime = false; 
				            break;
				        }
				    }
				    if (isPrime) {
				        count++;
				        System.out.println(startNum);
				    }
				    startNum++;
				}
			} else {
				System.out.println("Please enter a number between the range 1-100.");
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid Number Type");
		}
	}
}
