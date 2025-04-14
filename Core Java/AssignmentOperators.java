public class AssignmentOperators {
    public static void main(String[] args) {
      
        // Initialize variables
        int Number = 20;
        int OriginalNumber = Number;  // Preserve original value
        double DecimalNumber = 20.0;

        // Print initial values
        System.out.println("Initial values:");
        System.out.println("Number = " + Number);
        System.out.println("DecimalNumber = " + DecimalNumber);
        System.out.println("OriginalNumber = " + OriginalNumber);

        // Simple Assignment "="
        Number = 10;
        System.out.println("\nAfter simple assignment (Number = 10): " + Number);

        // Compound Assignment Operations
        Number += 5;  // Number = Number + 5
        System.out.println("After += 5: " + Number);

        Number -= 3;  // Number = Number - 3
        System.out.println("After -= 3: " + Number);

        Number *= 2;  // Number = Number * 2
        System.out.println("After *= 2: " + Number);

        Number /= 4;  // Number = Number / 4 (integer division)
        System.out.println("After /= 4: " + Number);

        Number %= 3;  // Number = Number % 3
        System.out.println("After %= 3: " + Number);

        // Floating-point operations
        DecimalNumber /= 3;
        System.out.printf("\nFloating-point division (DecimalNumber /= 3): %.2f%n", DecimalNumber);

        // Multiple Assignments
        int x, y, z;
        x = y = z = OriginalNumber;  // Chain assignment using OriginalNumber
        System.out.println("\nMultiple assignments:");
        System.out.println("x = " + x + ", y = " + y + ", z = " + z);

        // Automatic Type conversion demonstration
        System.out.println("\nType conversion in assignments:");
        int integerValue = OriginalNumber;
        integerValue += 2.5;  // Automatic type conversion
        System.out.println("int += double: " + integerValue + " (decimal truncated)");

        // Final values comparison
        System.out.println("\nFinal values:");
        System.out.println("Modified Number = " + Number);
        System.out.println("DecimalNumber = " + DecimalNumber);
        System.out.println("OriginalNumber (preserved) = " + OriginalNumber);
    }
}
