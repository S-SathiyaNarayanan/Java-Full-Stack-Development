// This is a user-defined class (blueprint)
// It defines what a Calculator "can do"
class Calculator {

    // Method: add
    // - return type: int (must return an integer value)
    // - parameters: two integers (a and b)
    int add(int a, int b) {

        // Local variable 'r' is created inside method stack frame
        // It stores the result of addition
        int r = a + b;

        // Returning value back to the caller (main method)
        // Control goes back to where this method was called
        return r;
    }
}


// This is the main class (entry point of program)
// JVM starts execution from main() method ONLY
public class ClassAndObjectBasics {

    public static void main(String[] args) {

        // Primitive variables (stored in stack memory)
        int num1 = 10;
        int num2 = 20;

        // Object creation happens in TWO steps:

        // 1. "new Calculator()"
        //    - creates object in HEAP memory
        //    - allocates memory for Calculator object

        // 2. "Calculator calc = ..."
        //    - 'calc' is a reference variable (stored in stack)
        //    - it holds the address of object in heap

        Calculator calc = new Calculator();

        // Method call:
        // - calc → reference to object
        // - .add → method belongs to Calculator class
        // - (num1, num2) → arguments passed

        // What happens internally:
        // - JVM creates a new stack frame for add()
        // - values 10 and 20 are copied into a and b
        // - method executes and returns result

        int result = calc.add(num1, num2);

        // Printing output to console
        // System → class
        // out → static PrintStream object
        // println → method to print line
        System.out.println("Addition Result: " + result);
    }
}
