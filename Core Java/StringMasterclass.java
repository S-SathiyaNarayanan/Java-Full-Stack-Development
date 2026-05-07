/*
===========================================================================
          COMPLETE STRING, STRINGBUILDER & STRINGBUFFER MASTERCLASS
===========================================================================

This single Java file covers:

1.  String reference variables and objects
2.  String immutability (with memory diagrams)
3.  String pool vs `new` keyword
4.  == vs equals()
5.  Multiple references to same object and reassigning one of them
6.  Concatenation techniques (+, +=, concat(), String.join(), String.format())
7.  Compile‑time constant folding vs runtime concatenation
8.  Common transformation methods (all return new String)
9.  Information methods (do not return new String)
10. String traversal
11. intern()
12. null, empty, blank
13. StringBuilder (mutable, append, insert, replace, delete, setCharAt, substring, capacity, etc.)
14. StringBuffer (thread‑safe mutable)
15. final keyword and immutability vs reassignment
16. Detailed comparison tables (String vs StringBuilder vs StringBuffer, == vs equals)
17. Common beginner mistakes (with corrections)
18. Primitive vs reference assignment rule
19. Recap of key takeaways

Every concept is demonstrated with runnable code, clear comments, and ASCII diagrams.
===========================================================================
*/

public class StringMasterclass {
    public static void main(String[] args) {

        // ================================================================
        // 1. STRING IS A CLASS – REFERENCE VARIABLES
        // ================================================================
        /*
         * A variable like `name` does NOT store characters.
         * It stores a REFERENCE (address) that “points” to a String OBJECT
         * somewhere on the heap.
         *
         * Diagram:
         *   name  ──────►  "Sathiya"   (actual String object)
         */
        String name = "Sathiya";
        System.out.println("name = " + name);

        // ================================================================
        // 2. STRING IMMUTABILITY EXPLAINED CORRECTLY
        // ================================================================
        /*
         * The characters inside a String object CANNOT be changed after creation.
         * Any method that seems to modify the string actually creates a
         * BRAND NEW String object. The original object remains untouched.
         *
         * If the returned reference is not stored, the variable still points
         * to the old object.
         */
        String original = "Hello";
        original.concat(" World");          // new object "Hello World" is created and discarded
        System.out.println("After ignored concat: " + original);   // still "Hello"

        // To actually use the new content, you must reassign the variable.
        original = original.concat(" World");
        System.out.println("After stored concat: " + original);    // "Hello World"

        /*
         * Memory BEFORE:
         *   original ──► "Hello"
         *
         * Memory AFTER concat() without assignment:
         *   original ──► "Hello"          (still points to old object)
         *                  "Hello World"  (new object has no reference → eligible for GC)
         *
         * Memory AFTER assignment:
         *   original ──► "Hello World"    (points to new object)
         *                  "Hello"        (old object becomes unreferenced)
         */

        // ================================================================
        // 3. STRING POOL VS NEW KEYWORD
        // ================================================================
        /*
         * String literals are stored in the String Pool (a special area inside the heap).
         * If an identical literal already exists there, the SAME object is reused.
         *
         * `new String(...)` ALWAYS creates a completely NEW object on the heap,
         * even if the text already exists in the pool.
         */
        String literal1 = "Java";
        String literal2 = "Java";
        System.out.println("\nliteral1 == literal2 : " + (literal1 == literal2));   // true (same pool object)

        String new1 = new String("Java");
        String new2 = new String("Java");
        System.out.println("new1 == new2       : " + (new1 == new2));             // false (different heap objects)

        /*
         * Diagram:
         *
         *   String pool (part of heap, managed specially)
         *     ┌────────────────┐
         *     │   "Java"       │  ← literal1 and literal2 point here
         *     └────────────────┘
         *
         *   Heap outside pool
         *     ┌────────────────┐
         *     │ "Java" (copy1) │  ← new1 points here
         *     └────────────────┘
         *     ┌────────────────┐
         *     │ "Java" (copy2) │  ← new2 points here
         *     └────────────────┘
         */

        // ================================================================
        // 4. == VS equals()
        // ================================================================
        /*
         * == compares references (are these two variables pointing to the SAME object?)
         * equals() compares the ACTUAL CONTENT of the strings.
         */
        String a = "Hello";
        String b = "Hello";
        String c = new String("Hello");

        System.out.println("\na == b      : " + (a == b));      // true – same pool object
        System.out.println("a == c      : " + (a == c));      // false – different objects
        System.out.println("a.equals(b): " + a.equals(b));    // true – same content
        System.out.println("a.equals(c): " + a.equals(c));    // true – same content

        // ================================================================
        // 5. MULTIPLE REFERENCES TO SAME OBJECT & REASSIGNING ONE
        // ================================================================
        /*
         * When two reference variables point to the same object,
         * reassigning one of them does NOT affect the other.
         */
        String x = "Java";
        String y = x;                 // y now points to the SAME "Java" object

        System.out.println("\nx = " + x);
        System.out.println("y = " + y);

        y = "Spring Boot";            // y now points to a NEW object
        System.out.println("After y = \"Spring Boot\":");
        System.out.println("x = " + x);   // still "Java"
        System.out.println("y = " + y);   // "Spring Boot"

        /*
         * Memory after y = x:
         *   x ──┐
         *       ├────► "Java"
         *   y ──┘
         *
         * Memory after y = "Spring Boot":
         *   x ─────► "Java"
         *   y ─────► "Spring Boot"
         */

        // ================================================================
        // 6. CONCATENATION TECHNIQUES (all produce new String objects,
        //    except compile‑time folding may reuse pool objects)
        // ================================================================
        System.out.println("\n--- CONCATENATION ---");

        // 6.1 Using +
        String p1 = "Java";
        String p2 = "Backend";
        String plus = p1 + p2;                // runtime concat → new String "JavaBackend"
        System.out.println("+ operator : " + plus);

        // 6.2 Using += (shorthand for s = s + ...)
        String addAssign = "Spring";
        addAssign += " Boot";                 // new String "Spring Boot", reassigned
        System.out.println("+= operator : " + addAssign);

        // 6.3 concat()
        String concatStr = "Core";
        concatStr = concatStr.concat(" Java");
        System.out.println("concat()    : " + concatStr);

        // 6.4 String.join(delimiter, elements...)
        String joined = String.join("-", "2026", "05", "08");
        System.out.println("String.join : " + joined);

        // 6.5 String.format()
        String formatted = String.format("Name: %s, Year: %d", "Sathiya", 2026);
        System.out.println("String.format: " + formatted);

        // ================================================================
        // 7. COMPILE‑TIME CONSTANT FOLDING vs RUNTIME CONCATENATION
        // ================================================================
        /*
         * When both operands of + are compile‑time constants (literals or final
         * variables), the compiler folds them into a single constant.
         * That folded string may be reused from the pool.
         *
         * Runtime concatenation (involving non‑final variables) normally creates
         * a new object. The compiler may still apply optimisations, but the
         * conceptual model is that a fresh object is produced.
         */
        String const1 = "Java" + "Backend";       // compiler makes "JavaBackend" – this is a constant
        String const2 = "JavaBackend";
        System.out.println("\nconst1 == const2 : " + (const1 == const2));   // true (same pool object)

        String dynamic1 = p1 + p2;                // runtime concatenation → usually new object
        System.out.println("const2 == dynamic1 : " + (const2 == dynamic1)); // normally false

        // ================================================================
        // 8. TRANSFORMATION METHODS (all return NEW String objects)
        // ================================================================
        System.out.println("\n--- TRANSFORMATION METHODS ---");

        String base = "  Java Programming  ";
        System.out.println("original           : [" + base + "]");

        // toUpperCase / toLowerCase
        System.out.println("toUpperCase        : " + base.toUpperCase());
        System.out.println("toLowerCase        : " + base.toLowerCase());

        // trim() – removes leading/trailing whitespace (returns new String)
        System.out.println("trim()             : [" + base.trim() + "]");

        // replace(char, char)
        System.out.println("replace('a','o')   : " + base.replace('a', 'o'));

        // substring(start, end) – start inclusive, end exclusive
        System.out.println("substring(2,6)     : " + base.substring(2, 6));

        // concat()
        System.out.println("concat(\"!!!\")    : " + base.concat("!!!"));

        /*
         * base is still the original "  Java Programming  ".
         * All these calls created new strings; we printed them but didn't reassign base.
         */
        System.out.println("base unchanged     : [" + base + "]");

        // ================================================================
        // 9. INFORMATION METHODS (do NOT return new String)
        // ================================================================
        System.out.println("\n--- INFORMATION METHODS ---");

        String info = "Backend";
        System.out.println("length()           : " + info.length());           // 7
        System.out.println("charAt(2)          : " + info.charAt(2));         // 'c'
        System.out.println("indexOf('e')       : " + info.indexOf('e'));      // 3
        System.out.println("contains(\"end\")  : " + info.contains("end"));   // true
        System.out.println("isEmpty()          : " + info.isEmpty());         // false

        // ================================================================
        // 10. STRING TRAVERSAL (using length() & charAt())
        // ================================================================
        System.out.println("\n--- STRING TRAVERSAL ---");
        String word = "Java";
        for (int i = 0; i < word.length(); i++) {
            System.out.println("character at " + i + " : " + word.charAt(i));
        }

        // ================================================================
        // 11. intern() METHOD
        // ================================================================
        /*
         * intern() returns the canonical (pool) representation of the string.
         * If an identical string exists in the pool, that pool reference is
         * returned; otherwise the string is added to the pool and returned.
         */
        String heapStr = new String("InternMe");
        String interned = heapStr.intern();
        System.out.println("\nheapStr == \"InternMe\"      : " + (heapStr == "InternMe"));   // false (heap object)
        System.out.println("interned == \"InternMe\"    : " + (interned == "InternMe"));   // true (pool object)

        // ================================================================
        // 12. NULL, EMPTY, AND BLANK
        // ================================================================
        System.out.println("\n--- NULL, EMPTY, BLANK ---");

        String empty = "";
        String blanks = "   ";
        String nothing = null;   // null reference – no object

        System.out.println("empty.isEmpty()   : " + empty.isEmpty());        // true
        System.out.println("blanks.isEmpty()  : " + blanks.isEmpty());       // false (spaces count)
        System.out.println("blanks.isBlank()  : " + blanks.isBlank());       // true (Java 11+)
        // nothing.length() would throw NullPointerException

        // ================================================================
        // 13. STRINGBUILDER – MUTABLE STRING ALTERNATIVE
        // ================================================================
        System.out.println("\n========== STRINGBUILDER ==========");

        /*
         * Problem with String: repeated concatenation creates many temporary objects,
         * wasting memory and CPU.
         *
         * StringBuilder is MUTABLE – the same StringBuilder object is used,
         * and its internal buffer grows automatically when needed.
         */
        StringBuilder sb = new StringBuilder("Java");
        System.out.println("Initial                : " + sb);

        // append() – adds at the end (most used method)
        sb.append(" Backend");
        System.out.println("append()               : " + sb);

        // insert(offset, str)
        sb.insert(4, " Spring");   // insert after index 4
        System.out.println("insert(4,\" Spring\")  : " + sb);

        // replace(start, end, str) – start inclusive, end exclusive
        sb.replace(0, 4, "Core");   // replace first 4 chars
        System.out.println("replace(0,4,\"Core\")  : " + sb);

        // delete(start, end)
        sb.delete(5, 11);           // delete " Spring"
        System.out.println("delete(5,11)           : " + sb);

        // deleteCharAt(index)
        sb.deleteCharAt(4);         // remove the space after "Core"
        System.out.println("deleteCharAt(4)        : " + sb);

        // reverse()
        sb.reverse();
        System.out.println("reverse()              : " + sb);
        sb.reverse();               // reverse back for continuity

        // charAt() & setCharAt() – direct character manipulation
        System.out.println("charAt(1)              : " + sb.charAt(1));
        sb.setCharAt(0, 'K');
        System.out.println("setCharAt(0,'K')       : " + sb);

        // substring() – returns a new String, NOT a StringBuilder
        String part = sb.substring(0, 4);
        System.out.println("substring(0,4)         : " + part + "  (returns String)");

        // length() vs capacity()
        System.out.println("length()               : " + sb.length());      // actual character count
        System.out.println("capacity()             : " + sb.capacity());    // internal buffer size
        // Default capacity = 16. Growth formula: newCapacity = (oldCapacity * 2) + 2

        // toString() – convert back to immutable String
        String immutableAgain = sb.toString();
        System.out.println("toString()             : " + immutableAgain + "  (now String)");

        // ================================================================
        // 14. STRINGBUFFER – THREAD‑SAFE VERSION OF STRINGBUILDER
        // ================================================================
        System.out.println("\n========== STRINGBUFFER ==========");

        /*
         * StringBuffer has the EXACT same methods as StringBuilder,
         * but it is SYNCHRONISED (thread‑safe). This makes it slightly slower.
         * Use StringBuilder in single‑threaded code (most common).
         * Use StringBuffer only when multiple threads modify the same text.
         */
        StringBuffer sbf = new StringBuffer("Hello");
        sbf.append(" World");
        System.out.println("StringBuffer : " + sbf);

        // ================================================================
        // 15. FINAL KEYWORD AND IMMUTABILITY (CRUCIAL INTERVIEW TOPIC)
        // ================================================================
        /*
         * `final` on a reference variable prevents REASSIGNMENT,
         * but does NOT make the object itself immutable.
         *
         * - final String → you cannot point it to a new object.
         * - final StringBuilder → you cannot point it to another StringBuilder,
         *   but you CAN mutate (change) the contents of the existing object.
         *
         * This is a very common interview question.
         */
        final String finalStr = "Hello";
        // finalStr = "World";            // ERROR: cannot reassign final variable
        System.out.println("\nfinalStr : " + finalStr);

        final StringBuilder finalSb = new StringBuilder("Java");
        finalSb.append(" Backend");        // LEGAL: mutating the object is allowed
        System.out.println("finalSb after append : " + finalSb);

        // finalSb = new StringBuilder("New"); // ERROR: cannot reassign final reference

        // ================================================================
        // 16. COMPARISON TABLES (for revision)
        // ================================================================
        System.out.println("\n========== COMPARISON TABLES ==========");

        // Table 1: String vs StringBuilder vs StringBuffer
        // (Internal storage row for String just says "Immutable")
        System.out.println(
            "+---------------------+---------------------+-----------------------+-----------------------+\n" +
            "| Feature             | String              | StringBuilder         | StringBuffer          |\n" +
            "+---------------------+---------------------+-----------------------+-----------------------+\n" +
            "| Mutability          | Immutable           | Mutable               | Mutable               |\n" +
            "| Thread‑safe         | Effectively yes     | No                    | Yes (synchronized)    |\n" +
            "|                     | (because immutable) |                       |                       |\n" +
            "| Performance         | Best for fixed text | Fastest for single    | Slower than           |\n" +
            "|                     |                     | thread modifications  | StringBuilder         |\n" +
            "| Memory behaviour    | New object on       | Same object reused;   | Same object reused;   |\n" +
            "| (when modifying)    | every change        | buffer grows as needed| buffer grows as needed|\n" +
            "| Pool support        | Yes (for literals)  | No                    | No                    |\n" +
            "| equals() behaviour  | Content comparison  | Reference comparison  | Reference comparison  |\n" +
            "|                     | (overridden)        | (not overridden)      | (not overridden)      |\n" +
            "| Internal storage    | Immutable           | Resizable char array  | Resizable char array  |\n" +
            "| Reassignment needed | Yes (for new        | No (modifications     | No (modifications     |\n" +
            "| to “change” text?   | content)            | affect same object)   | affect same object)   |\n" +
            "| Direct char change  | No                  | Yes (setCharAt())     | Yes (setCharAt())     |\n" +
            "| Typical use case    | Constants, API msgs | DSA, parsing, dynamic | Multi‑threaded text   |\n" +
            "|                     |                     | text building         | manipulation          |\n" +
            "+---------------------+---------------------+-----------------------+-----------------------+\n"
        );

        // Table 2: == vs equals()
        System.out.println(
            "+---------------------+------------------------------------------+\n" +
            "| Operator / Method   |  Checks                                   |\n" +
            "+---------------------+------------------------------------------+\n" +
            "| ==                  | Reference / identity (same object?)       |\n" +
            "| equals(Object)      | For String: content equality             |\n" +
            "|                     | For StringBuilder/Buffer: reference       |\n" +
            "+---------------------+------------------------------------------+\n"
        );

        // Table 3: Common mistakes & corrections
        System.out.println(
            "+--------------------------------------------+--------------------------------------------------+\n" +
            "| Wrong / Mistake                            | Correct Way                                      |\n" +
            "+--------------------------------------------+--------------------------------------------------+\n" +
            "| if (s1 == s2) for content check            | s1.equals(s2)                                    |\n" +
            "| str.length                                 | str.length()                                     |\n" +
            "| str[0]                                     | str.charAt(0)                                    |\n" +
            "| Forgetting to reassign after concat/trim   | s = s.concat(\"x\") or store returned String        |\n" +
            "| Using StringBuilder in multi‑threaded code  | Use StringBuffer if threads share the object      |\n" +
            "| Calling methods on null String              | Check for null first (s != null)                 |\n" +
            "+--------------------------------------------+--------------------------------------------------+\n"
        );

        // ================================================================
        // 17. IMPORTANT MECHANICAL RULES (Primitive vs Reference assignment)
        // ================================================================
        /*
         * Primitive variable (int, double, boolean, etc.):
         *    = copies the actual VALUE.
         *
         * Reference variable (String, array, any object):
         *    = copies the REFERENCE/ADDRESS, not the object itself.
         *
         * Reassigning a reference variable only changes WHERE it points;
         * it NEVER modifies the old object.
         */
        System.out.println("\n--- ASSIGNMENT RULE ---");
        int num1 = 10;
        int num2 = num1;        // num2 gets a COPY of the value 10
        num2 = 20;              // changing num2 does NOT affect num1
        System.out.println("Primitive: num1 = " + num1 + ", num2 = " + num2);

        String ref1 = new String("Original");
        String ref2 = ref1;     // ref2 gets a COPY of the reference (pointing to same object)
        ref2 = "New";           // ref2 now points to a different object; ref1 unchanged
        System.out.println("Reference: ref1 = " + ref1 + ", ref2 = " + ref2);

        // ================================================================
        // 18. FINAL RECAP (Key Takeaways)
        // ================================================================
        System.out.println("\n========== KEY TAKEAWAYS ==========");

        /*
         * 1. String objects are immutable – any “change” creates a new object.
         * 2. Whenever you want the new content, capture the returned reference:
         *       s = s.toUpperCase();
         * 3. Use StringBuilder (or StringBuffer if thread safety needed) for
         *    repeated modifications – far more memory‑friendly.
         * 4. `==` checks if two references point to the exact same object.
         *    Use `equals()` for content comparison (in String).
         * 5. `new String("text")` always creates a separate heap object.
         * 6. `intern()` helps move a string into the pool, saving memory.
         * 7. How to remember StringBuilder vs StringBuffer?
         *    Builder = Builds fast, Buffer = Buffered (synchronized).
         * 8. Primitive assignment copies VALUE; reference assignment copies ADDRESS.
         * 9. `final` prevents reassignment of a reference, but does NOT make the object immutable.
         */
    }
}
