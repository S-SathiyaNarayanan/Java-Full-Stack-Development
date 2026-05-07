/*
====================================================================
                  STRINGBUILDER & STRINGBUFFER 
====================================================================

This file covers:

1. Why StringBuilder/StringBuffer were introduced
2. Problem with String immutability
3. Mutable vs Immutable
4. StringBuilder creation
5. StringBuffer creation
6. append()
7. insert()
8. replace()
9. delete()
10. deleteCharAt()
11. reverse()
12. charAt()
13. setCharAt()
14. substring()
15. length()
16. capacity()
17. toString()
18. StringBuilder vs StringBuffer
19. Memory behavior
20. Important interview concepts

====================================================================
IMPORTANT CORE IDEA
====================================================================

String:
-------
Immutable.

Modification creates NEW object.


StringBuilder / StringBuffer:
--------------------------------
Mutable.

Modification changes SAME object.

====================================================================
*/

public class Main {

    public static void main(String[] args) {

        /*
        ============================================================
        1. PROBLEM WITH STRING
        ============================================================

        String objects are immutable.

        Every modification creates NEW object.

        Example:
        */

        String s = "A";

        s = s + "B";
        s = s + "C";
        s = s + "D";


        /*
        Internally many objects created:

            "A"
            "AB"
            "ABC"
            "ABCD"

        This causes:

        - More memory usage
        - More garbage objects
        - Slower performance

        To solve this:

            StringBuilder
            StringBuffer

        were introduced.
        */

        System.out.println("String Result : " + s);



        /*
        ============================================================
        2. STRINGBUILDER CREATION
        ============================================================

        StringBuilder is mutable.

        SAME object changes directly.
        */

        StringBuilder sb1 = new StringBuilder("Java");


        /*
        Memory:

            sb1 ---> mutable object "Java"
        */

        System.out.println("Initial StringBuilder : " + sb1);



        /*
        ============================================================
        3. append()
        ============================================================

        Adds data at end.

        MOST USED METHOD.

        IMPORTANT:

        No reassignment needed.

        Because SAME object changes.
        */

        sb1.append(" Backend");


        /*
        Internally:

        BEFORE:
            "Java"

        AFTER:
            "Java Backend"

        SAME object modified.
        */

        System.out.println("After append : " + sb1);



        /*
        ============================================================
        4. append() WITH DIFFERENT DATATYPES
        ============================================================

        append() accepts many datatypes.

        Everything internally converted to String.
        */

        sb1.append(2026);
        sb1.append(true);
        sb1.append('A');
        sb1.append(5.5);

        System.out.println("append with different datatypes : " + sb1);



        /*
        ============================================================
        5. insert()
        ============================================================

        Inserts value at specific index.
        */

        StringBuilder sb2 = new StringBuilder("Java");

        sb2.insert(4, " SpringBoot");


        /*
        BEFORE:
            "Java"

        AFTER:
            "Java SpringBoot"
        */

        System.out.println("insert : " + sb2);



        /*
        ============================================================
        6. replace()
        ============================================================

        Replaces characters between indexes.

        start -> included
        end   -> excluded
        */

        StringBuilder sb3 = new StringBuilder("Hello");

        sb3.replace(0,5,"Java");

        System.out.println("replace : " + sb3);



        /*
        ============================================================
        7. delete()
        ============================================================

        Deletes range of characters.
        */

        StringBuilder sb4 = new StringBuilder("Programming");

        sb4.delete(0,4);


        /*
        Deletes:
            Prog

        Remaining:
            ramming
        */

        System.out.println("delete : " + sb4);



        /*
        ============================================================
        8. deleteCharAt()
        ============================================================

        Deletes single character.
        */

        StringBuilder sb5 = new StringBuilder("Java");

        sb5.deleteCharAt(1);


        /*
        BEFORE:
            Java

        AFTER:
            Jva
        */

        System.out.println("deleteCharAt : " + sb5);



        /*
        ============================================================
        9. reverse()
        ============================================================

        Reverses characters.
        */

        StringBuilder sb6 = new StringBuilder("Java");

        sb6.reverse();


        /*
        BEFORE:
            J a v a

        AFTER:
            a v a J
        */

        System.out.println("reverse : " + sb6);



        /*
        ============================================================
        10. charAt()
        ============================================================

        Gets character at index.

        Similar to String.
        */

        StringBuilder sb7 = new StringBuilder("Backend");

        System.out.println("charAt(2) : " + sb7.charAt(2));



        /*
        ============================================================
        11. setCharAt()
        ============================================================

        VERY IMPORTANT METHOD.

        Directly changes character.

        Impossible in String.
        */

        StringBuilder sb8 = new StringBuilder("Java");

        sb8.setCharAt(0,'K');


        /*
        BEFORE:
            J a v a

        AFTER:
            K a v a

        SAME object modified.
        */

        System.out.println("setCharAt : " + sb8);



        /*
        ============================================================
        12. substring()
        ============================================================

        Returns part of string.

        IMPORTANT:

        Returns String object.

        NOT StringBuilder.
        */

        StringBuilder sb9 = new StringBuilder("Programming");

        String result = sb9.substring(0,4);

        System.out.println("substring : " + result);



        /*
        ============================================================
        13. length()
        ============================================================

        Returns actual character count.
        */

        StringBuilder sb10 = new StringBuilder("Java");

        System.out.println("length : " + sb10.length());



        /*
        ============================================================
        14. capacity()
        ============================================================

        VERY IMPORTANT INTERVIEW METHOD.

        capacity() returns internal storage size.

        Default capacity = 16
        */

        StringBuilder sb11 = new StringBuilder();

        System.out.println("Default capacity : " + sb11.capacity());


        /*
        Existing characters increase capacity.

        Formula:

            16 + existing characters
        */

        StringBuilder sb12 = new StringBuilder("Java");

        System.out.println("Capacity with existing String : " + sb12.capacity());



        /*
        ============================================================
        15. toString()
        ============================================================

        Converts StringBuilder into String object.

        VERY IMPORTANT.
        */

        StringBuilder sb13 = new StringBuilder("Java Backend");

        String converted = sb13.toString();


        /*
        converted is now actual String object.
        */

        System.out.println("toString : " + converted);



        /*
        ============================================================
        16. STRINGBUFFER
        ============================================================

        StringBuffer works almost same as StringBuilder.

        Difference:

        StringBuffer is thread-safe.

        Uses synchronization.

        Slower than StringBuilder.
        */

        StringBuffer buffer = new StringBuffer("Java");

        buffer.append(" Backend");

        System.out.println("StringBuffer append : " + buffer);



        /*
        ============================================================
        17. STRINGBUILDER VS STRINGBUFFER
        ============================================================

        StringBuilder:
        --------------
        - Faster
        - Not thread-safe
        - Used mostly in real applications


        StringBuffer:
        -------------
        - Thread-safe
        - Slower
        - Used in multithreaded shared environments
        */



        /*
        ============================================================
        18. IMPORTANT MEMORY DIFFERENCE
        ============================================================

        STRING:
        -------

            String s = "Java";

            s.concat(" Backend");

        Creates NEW object.


        STRINGBUILDER:
        ----------------

            sb.append(" Backend");

        SAME object modified.
        */



        /*
        ============================================================
        19. IMPORTANT REAL-WORLD METHODS
        ============================================================

        MOST USED:

        append()
        toString()
        charAt()
        setCharAt()
        reverse()
        deleteCharAt()
        length()

        Especially important in:

        - DSA
        - LeetCode
        - Backend development
        - Parsing
        - Query building
        - JSON creation
        */



        /*
        ============================================================
        20. FINAL CORE UNDERSTANDING
        ============================================================

        String:
            Immutable
            modification -> NEW object

        StringBuilder/StringBuffer:
            Mutable
            modification -> SAME object changes


        StringBuilder:
            Faster
            not thread-safe

        StringBuffer:
            Thread-safe
            slower

        ============================================================
        */


        /*
        ============================================================
        EXTRA FINAL REVISION TABLE
        ============================================================

        -----------------------------------------------------------------------------
        | Feature              | String | StringBuilder | StringBuffer |
        -----------------------------------------------------------------------------
        | Type                 | Class  | Class         | Class        |
        -----------------------------------------------------------------------------
        | Mutable?             | NO     | YES           | YES          |
        -----------------------------------------------------------------------------
        | Modification         | NEW    | SAME          | SAME         |
        | Behavior             | Object | Object        | Object       |
        |                      | Created| Modified      | Modified     |
        -----------------------------------------------------------------------------
        | Reassignment Needed? | YES    | NO            | NO           |
        -----------------------------------------------------------------------------
        | Performance          | Slow   | Fastest       | Slower than  |
        |                      | for    |               | StringBuilder|
        |                      | repeated modifications               |
        -----------------------------------------------------------------------------
        | Thread-safe?         | YES    | NO            | YES          |
        -----------------------------------------------------------------------------
        | Synchronization      | Not    | No            | Uses         |
        |                      | Needed | Synchronization| Synchronization|
        -----------------------------------------------------------------------------
        | Memory Usage         | More   | Less          | Less         |
        |                      | Objects| Objects       | Objects      |
        -----------------------------------------------------------------------------
        | Pool Support         | YES    | NO            | NO           |
        -----------------------------------------------------------------------------
        | Internal Working     | Fixed Immutable Object | Editable Mutable Character Storage |
        -----------------------------------------------------------------------------
        | Internal Modification| Cannot edit existing | Directly edits SAME object         |
        | Behavior             | object               | internally                         |
        -----------------------------------------------------------------------------
        | Direct Character     | NO     | YES           | YES          |
        | Modification         |        | setCharAt()   | setCharAt()  |
        -----------------------------------------------------------------------------
        | Best Use Case        | Fixed  | Single-thread | Multi-thread |
        |                      | Text   | heavy         | heavy        |
        |                      |        | modifications | modifications|
        -----------------------------------------------------------------------------


        FINAL CORE MEMORY RULE:
        -----------------------

        String:
            modification -> NEW object

        StringBuilder/StringBuffer:
            modification -> SAME object changes


        EXTRA IMPORTANT POINTS:
        -----------------------

        1. StringBuilder and StringBuffer mostly have same methods.

        2. StringBuilder/StringBuffer objects are mutable.
           So methods directly modify internal character buffer.

        3. StringBuilder is generally preferred in modern Java
           because it is faster.

        4. StringBuffer is used when multiple threads share
           same mutable text data.

        5. StringBuilder/StringBuffer do NOT use String Pool.

        6. append() is most important method in real-world usage.

        7. substring() returns String object.
           NOT StringBuilder/StringBuffer.

        8. capacity() is different from length().

           length()  -> actual characters count
           capacity() -> internal storage size

        9. Internal capacity expansion formula:

           newCapacity = (oldCapacity * 2) + 2

           when current capacity becomes full.

        10. StringBuilder/StringBuffer internally use mutable
            resizable character storage.

        11. String is safer because immutability prevents
            accidental modification.

        12. StringBuilder/StringBuffer inherit from:

                AbstractStringBuilder

        13. equals() behavior:

            String overrides equals() for content comparison.

            StringBuilder/StringBuffer do NOT override equals().

            So:

                sb1.equals(sb2)

            checks reference equality like ==.

        14. toString() is commonly used before sending data
            to APIs/methods expecting actual String object.

        15. StringBuilder is heavily used in:

            - DSA
            - LeetCode
            - Parsing
            - Backend development
            - Query building
            - JSON/XML creation
            - Logging systems

        ============================================================
        */
    }
}
