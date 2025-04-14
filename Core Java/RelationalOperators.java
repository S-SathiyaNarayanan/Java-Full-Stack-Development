// Relational Operators
// Comparison Operators 

public class RelationalOperators {
    public static void main(String[] args) {
      
        // Declare two numbers
        int num1 = 20;
        int num2 = 13;

        // Print initial values
        System.out.println("Initial values:");
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);

        // Perform relational operations
        boolean isEqual = (num1 == num2);
        boolean notEqual = (num1 != num2);
        boolean greaterThan = (num1 > num2);
        boolean lessThan = (num1 < num2);
        boolean greaterOrEqual = (num1 >= num2);
        boolean lessOrEqual = (num1 <= num2);

        // Display results
        System.out.println("\nRelational Operations:");
        System.out.println("Equal to (num1 == num2): " + isEqual);
        System.out.println("Not equal to (num1 != num2): " + notEqual);
        System.out.println("Greater than (num1 > num2): " + greaterThan);
        System.out.println("Less than (num1 < num2): " + lessThan);
        System.out.println("Greater than or equal to (num1 >= num2): " + greaterOrEqual);
        System.out.println("Less than or equal to (num1 <= num2): " + lessOrEqual);

        // Modify values and show changed relationships
        System.out.println("\nAfter modifying values:");
        num1 = 15;  // New value for num1
        num2 = 15;  // New value for num2
        
        // Print modified values
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);

        // Perform relational operations again
        System.out.println("\nRelational Operations after modification:");
        System.out.println("Equal to (num1 == num2): " + (num1 == num2));
        System.out.println("Not equal to (num1 != num2): " + (num1 != num2));
        System.out.println("Greater than (num1 > num2): " + (num1 > num2));
        System.out.println("Less than (num1 < num2): " + (num1 < num2));
        System.out.println("Greater than or equal to (num1 >= num2): " + (num1 >= num2));
        System.out.println("Less than or equal to (num1 <= num2): " + (num1 <= num2));
    }
}
