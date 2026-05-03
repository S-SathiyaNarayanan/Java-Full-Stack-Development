// Class definition
class Calculator {
    
    // Method to perform addition
    int add(int a, int b) {
        int r = a + b;   // store result
        return r;        // return result
    }
}

// Main class (same name as file)
public class ClassAndObjectBasics {
    public static void main(String[] args) {
        
        // Creating variables
        int num1 = 10;
        int num2 = 20;
        
        // Creating object
        Calculator calc = new Calculator();
        
        // Calling method
        int result = calc.add(num1, num2);
        
        // Printing result
        System.out.println("Addition Result: " + result);
    }
}
