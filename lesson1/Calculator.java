import java.util.Scanner;

public class Calculator {
	public static void main(String[] args) {
		System.out.print("Type expression (for example, 1 + 5): ");
		
		Scanner scanner = new Scanner(System.in);
		double a = scanner.nextDouble();
		String operation = scanner.next();
		double b = scanner.nextDouble();
		
		double result;
		switch(operation) {
			case "+": result = a + b; break;
			case "-": result = a - b; break;
			case "*": result = a * b; break;
			case "/": result = a / b; break;
			default: 
				System.err.printf("An invalid operation was provided: '%s'. Available operations are +, -, * and /", operation);
				return;
		}
		System.out.printf(" = %f", result);
	}
}