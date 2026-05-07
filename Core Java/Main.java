/*
============================================================
                      JAVA STRINGS   
============================================================

This program covers:

1. String is a class
2. String object creation
3. String literal vs new String()
4. String Pool concept
5. == vs equals()
6. String immutability
7. Why reassignment is needed
8. Common String methods
9. Memory/reference behavior
10. String traversal
11. Important beginner mistakes

VERY IMPORTANT CORE IDEA:
-------------------------
Strings DO NOT change.

Instead:
- New String objects are created.
- Reference variables are changed.

============================================================
*/

public class Main {

    public static void main(String[] args) {

        /*
        ============================================================
        1. STRING IS A CLASS
        ============================================================

        String -> Class
        s1     -> Reference Variable
        "Hello" -> Object

        Even though it looks simple:

            String s1 = "Hello";

        internally an object is still created.
        */

        String s1 = "Hello";

        System.out.println("s1 = " + s1);



        /*
        ============================================================
        2. STRING LITERAL
        ============================================================

        This uses the STRING POOL.

        JVM checks:

            "Does this String already exist in pool?"

        If YES:
            existing object reused.

        If NO:
            new pooled object created.
        */

        String a = "Java";
        String b = "Java";


        /*
        Both variables point to SAME pooled object.

        Memory Concept:

                a -----\
                        ---> "Java"
                b -----/

        So reference/address is same.
        */

        System.out.println("a == b : " + (a == b));



        /*
        ============================================================
        3. new String()
        ============================================================

        'new' forces creation of NEW object.

        Even if same text already exists.
        */

        String x = new String("Java");
        String y = new String("Java");


        /*
        Memory Concept:

            x ---> Object1("Java")

            y ---> Object2("Java")

        Different heap objects.

        So references are different.
        */

        System.out.println("x == y : " + (x == y));



        /*
        ============================================================
        4. equals() VS ==
        ============================================================

        ==
        --
        Checks:
            "Are references/addresses same?"

        equals()
        --------
        Checks:
            "Is content/text same?"
        */

        System.out.println("x.equals(y) : " + x.equals(y));



        /*
        ============================================================
        5. STRING IMMUTABILITY
        ============================================================

        Strings CANNOT change after creation.

        This is the MOST IMPORTANT String concept.
        */

        String s = "Hello";


        /*
        concat() DOES NOT modify existing object.

        Instead:

            NEW String object created.

        Since result is NOT stored:

            s still points to old object.
        */

        s.concat(" World");

        System.out.println("After concat without reassignment : " + s);



        /*
        ============================================================
        6. REASSIGNMENT
        ============================================================

        This is the REAL mechanism.

        We are NOT changing old String object.

        We are:
            changing reference variable
            to point to NEW object.
        */

        s = s.concat(" World");


        /*
        Internally:

        OLD OBJECT:
            "Hello"

        NEW OBJECT CREATED:
            "Hello World"

        Now:
            s points to new object.
        */

        System.out.println("After concat with reassignment : " + s);



        /*
        ============================================================
        7. toUpperCase()
        ============================================================
        */

        String str1 = "java";


        /*
        New object "JAVA" created.

        But result ignored.

        So:
            str1 still points to "java"
        */

        str1.toUpperCase();

        System.out.println("Upper Case Without reassignment : " + str1);


        /*
        Now returned reference stored.
        */

        str1 = str1.toUpperCase();

        System.out.println("Upper Case With reassignment : " + str1);



        /*
        ============================================================
        8. toLowerCase()
        ============================================================
        */

        String str2 = "JAVA";

        str2 = str2.toLowerCase();

        System.out.println("toLowerCase : " + str2);



        /*
        ============================================================
        9. replace()
        ============================================================

        replace() also creates NEW String object.
        */

        String str3 = "Java";

        str3 = str3.replace('J', 'K');

        System.out.println("replace : " + str3);



        /*
        ============================================================
        10. trim()
        ============================================================

        trim() removes spaces from beginning/end.
        */

        String str4 = "   Java   ";

        str4 = str4.trim();

        System.out.println("trim : " + str4);



        /*
        ============================================================
        11. substring()
        ============================================================

        substring(start,end)

        start -> included
        end   -> excluded
        */

        String str5 = "Programming";

        str5 = str5.substring(0,4);

        System.out.println("substring : " + str5);



        /*
        ============================================================
        12. METHODS RETURNING INFORMATION
        ============================================================

        These methods DO NOT return new String objects.

        They return:
            int
            char
            boolean
            etc.
        */


        /*
        length()
        --------
        Returns number of characters.
        */

        String info = "Java";

        System.out.println("length : " + info.length());


        /*
        charAt(index)
        -------------
        Returns character at index.
        */

        System.out.println("charAt(1) : " + info.charAt(1));


        /*
        contains()
        ----------
        Returns boolean.
        */

        System.out.println("contains av : " + info.contains("av"));


        /*
        indexOf()
        ---------
        Returns position/index.
        */

        System.out.println("indexOf('v') : " + info.indexOf('v'));



        /*
        ============================================================
        13. STRING TRAVERSAL
        ============================================================

        Strings can be traversed like arrays.

        IMPORTANT:

        Arrays:
            arr.length

        Strings:
            str.length()
        */

        String traverse = "Java";

        System.out.println("\nTraversing String:");

        for(int i = 0; i < traverse.length(); i++) {

            /*
            charAt(i)
            gets each character one by one.
            */

            System.out.println(traverse.charAt(i));
        }



        /*
        ============================================================
        14. IMPORTANT BEGINNER MISTAKES
        ============================================================
        */


        /*
        WRONG:

            if(s1 == s2)

        for content comparison.

        Because == checks reference.
        */


        /*
        CORRECT:

            s1.equals(s2)
        */


        /*
        WRONG:

            str.length

        CORRECT:

            str.length()
        */


        /*
        WRONG:

            str[0]

        Java Strings do NOT support indexing like arrays.

        CORRECT:

            str.charAt(0)
        */



        /*
        ============================================================
        15. FINAL CORE MEMORY RULE
        ============================================================

        If String method returns String:

            usually NEW object created.

        So we must store returned reference.

        Example Pattern:

            s = s.concat("abc");

            s = s.toUpperCase();

            s = s.replace('a','b');

            s = s.trim();

        OTHERWISE:

            variable still points to old object.

        ============================================================
        */
    }
}
