public class StringBasics {

    public static void main(String[] args) {

        /*
        =========================================================
        PART 1 : Creating a String object
        =========================================================
        */

        // "name" is a REFERENCE VARIABLE
        // It does NOT store actual characters directly
        // It stores the reference/address of a String object

        String name = "Sathiya";

        /*
        MEMORY IDEA:

        name  ─────►  "Sathiya"

        "Sathiya" is the actual String object
        name is only pointing to it
        */


        System.out.println("name = " + name);


        /*
        =========================================================
        PART 2 : Reassigning the reference variable
        =========================================================
        */

        // Here we are NOT modifying old String object

        // We are making "name" point to a NEW String object

        name = "Narayanan";

        /*
        BEFORE:

        name  ─────►  "Sathiya"


        AFTER:

        name  ─────►  "Narayanan"

        Old object "Sathiya" now has no reference
        pointing to it anymore
        */

        System.out.println("name = " + name);


        /*
        =========================================================
        PART 3 : Multiple references pointing to SAME object
        =========================================================
        */

        String a = "Java";

        // b gets SAME reference as a
        // No new object is created here

        String b = a;

        /*
        MEMORY:

        a ──┐
            ├────► "Java"
        b ──┘

        Both references point to SAME object
        */

        System.out.println("a = " + a);
        System.out.println("b = " + b);


        /*
        =========================================================
        PART 4 : Changing one reference
        =========================================================
        */

        // b is now changed to point to NEW object

        b = "Spring Boot";

        /*
        NOW MEMORY:

        a ─────► "Java"

        b ─────► "Spring Boot"

        Only b changed

        a still points to old object
        */

        System.out.println("a = " + a);
        System.out.println("b = " + b);


        /*
        =========================================================
        PART 5 : Using new keyword
        =========================================================
        */

        // Explicitly creating object using new keyword

        String x = new String("Hello");

        /*
        MEMORY:

        x ─────► String object "Hello"
        */

        System.out.println("x = " + x);


        // Reassigning x to another NEW object

        x = new String("World");

        /*
        NOW MEMORY:

        x ─────► String object "World"

        Old "Hello" object becomes unused
        */

        System.out.println("x = " + x);


        /*
        =========================================================
        IMPORTANT CONCEPT
        =========================================================

        Strings are IMMUTABLE

        Immutable means:
        String object content cannot be changed after creation

        So operations like reassignment do NOT modify old object

        Instead:
        A NEW object is created
        And reference variable is changed to point to it
        */


        /*
        =========================================================
        SIMPLE MECHANICAL RULE
        =========================================================

        Primitive variable:
            = copies VALUE

        Object variable:
            = copies REFERENCE/ADDRESS

        Reassigning object variable:
            changes where reference points

        It does NOT modify old object
        */
    }
}
