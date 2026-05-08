/*
================================================================================
                           JAVA ARRAYS MASTERCLASS
================================================================================

Contents (ordered for progressive learning):
 1.  Arrays are objects – reference variables (with memory model)
 2.  Array creation syntaxes
 3.  Default values for all types (table)
 4.  .length – a property, NOT a method
 5.  Mutation vs reassignment (and GC eligibility)
 6.  Reference sharing (the #1 beginner mistake)
 7.  Array copying: clone(), copyOf(), copyOfRange()
 8.  System.arraycopy() – native fast copy
 9.  Shallow copy vs deep copy with object arrays
10. Deep copy vs shallow copy for 2D primitive arrays
11. Deep copy for nested object arrays (Person[][] example)
12. Fixed‑size nature → why ArrayList exists
13. Primitive arrays vs object arrays (memory layout)
14. == vs Arrays.equals() vs Arrays.deepEquals() – with object examples
15. Arrays.binarySearch() – efficient lookup (SORTED REQUIREMENT explained)
16. Arrays.asList() – bridge to Collections (hidden dangers)
17. ArrayIndexOutOfBoundsException & other array‑related exceptions
18. Enhanced for‑loop (for‑each) – CORRECTED full explanation
19. Arrays.toString() & Arrays.deepToString() – readable output
20. Arrays.fill() – bulk assignment
21. Arrays.sort() – sorting with algorithm details, stability, Comparators
22. Recursion on arrays (with stack‑frame note)
23. User‑input arrays with Scanner (validation)
24. 2D arrays (fixed rectangle) & jagged arrays (variable row lengths)
25. Null safety in jagged arrays
26. Pass‑by‑value with arrays – correct mental model
27. Local variable vs field initialisation for arrays
28. Varargs (int... nums) – EXPLAINED: internal array, zero-args behavior
29. Array covariance (why it's dangerous)
30. Complexity cheat‑sheet (Big‑O for array operations) – with precise notes
31. Arrays vs Collections – when to use which
32. Memory layout and cache implications
33. Autoboxing vs primitive arrays (performance trade-offs)
34. Type conversion patterns (int[] ↔ List<Integer>, etc.)
35. Common mistakes and corrections table
36. Key takeaways (condensed)

Every explanation is beginner‑friendly yet technically precise.
ASCII memory diagrams and comparison tables used throughout.
================================================================================
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArrayMasterclass {

    // ─────────────────────────────────────────────────────────────────
    // Static nested class used for deep‑copy demonstrations
    // (Better style than a local class inside main())
    // ─────────────────────────────────────────────────────────────────
    private static class Person {
        String name;
        Person(String name) { this.name = name; }
        Person(Person other) { this.name = other.name; }  // copy constructor
        @Override
        public String toString() { return name; }
    }

    // Book class used for custom sorting demonstration
    private static class Book {
        String title;
        int pages;
        Book(String title, int pages) { this.title = title; this.pages = pages; }
        @Override
        public String toString() { return title + "(" + pages + ")"; }
    }

    // Recursive method to print an int array (shows recursion + array traversal)
    static void printArrayRecursively(int[] arr, int index) {
        if (index >= arr.length) return;          // base case – stop at length
        System.out.print(arr[index] + " ");
        printArrayRecursively(arr, index + 1);    // each call creates a new stack frame
    }

    // Method that modifies the array element – shows pass‑by‑value of the reference
    static void modifyArray(int[] arr) {
        arr[0] = 999;                             // mutates the original object
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // =================================================================
        // 1. ARRAYS ARE OBJECTS – REFERENCE VARIABLES
        // =================================================================
        /*
         * An array variable stores a REFERENCE (a JVM‑managed handle) that
         * points to the actual array object on the heap.
         *
         * Arrays are special JVM‑created objects:
         *   – every array type is a subclass of Object
         *   – they implement Cloneable and Serializable
         *
         * Conceptual memory diagram:
         *   int[] demo = {1,2,3};
         *
         *   variable (stack)     heap
         *   demo ──────────────► [1][2][3]   (array object)
         */
        int[] demo = {1, 2, 3};
        System.out.println("demo instanceof Object       : " + (demo instanceof Object));
        System.out.println("demo instanceof Cloneable    : " + (demo instanceof Cloneable));

        // =================================================================
        // 2. ARRAY CREATION SYNTAXES
        // =================================================================
        /*
         * Four common ways to create an array:
         *
         * A) Direct initialisation (shortcut)
         *    int[] a = {1,2,3};
         *    // compiler internally translates to new int[]{1,2,3}
         *
         * B) Creation with default values
         *    int[] a = new int[5];   // all elements = type default
         *
         * C) Explicit inline initialisation
         *    int[] a = new int[]{10,20,30};
         *
         * D) Separate declaration and later assignment
         *    int[] a;               // reference only, no object yet
         *    a = new int[]{1,2,3};  // object created now
         */
        int[] arr1 = {10, 20, 30};                     // A
        int[] arr2 = new int[3];                      // B (defaults 0)
        int[] arr3 = new int[]{40, 50, 60};           // C
        int[] arr4;                                   // D – declaration
        arr4 = new int[]{70, 80, 90};                 // assignment

        System.out.println("\nArray creation examples:");
        System.out.println("arr1 = " + Arrays.toString(arr1));
        System.out.println("arr2 = " + Arrays.toString(arr2));
        System.out.println("arr3 = " + Arrays.toString(arr3));
        System.out.println("arr4 = " + Arrays.toString(arr4));

        // =================================================================
        // 3. DEFAULT VALUES
        // =================================================================
        /*
         * When you create an array with 'new' and no explicit values,
         * every element is set to the default for its type.
         *
         * +----------------------+-------------------+
         * | Type                 | Default Value     |
         * +----------------------+-------------------+
         * | byte, short, int, long | 0               |
         * | float, double         | 0.0              |
         * | boolean               | false            |
         * | char                  | '\u0000' (null char, NOT null) |
         * | Object references     | null             |
         * +----------------------+-------------------+
         */
        System.out.println("\nDefault values examples:");
        int[] intDef = new int[2];
        double[] dblDef = new double[2];
        boolean[] boolDef = new boolean[2];
        String[] strDef = new String[2];
        char[] charDef = new char[2];

        System.out.println("int[]    : " + Arrays.toString(intDef));
        System.out.println("double[] : " + Arrays.toString(dblDef));
        System.out.println("boolean[]: " + Arrays.toString(boolDef));
        System.out.println("String[] : " + Arrays.toString(strDef));
        System.out.println("char[]   : " + Arrays.toString(charDef) + "  (all are \\u0000)");

        // =================================================================
        // 4. .length – A PROPERTY, NOT A METHOD
        // =================================================================
        /*
         * Arrays use a 'length' property (no parentheses).
         * Strings use a 'length()' method – a classic confusion.
         */
        int[] nums = {5, 10, 15};
        System.out.println("\nArray length (property): " + nums.length);
        // nums.length()  would cause a compilation error

        // =================================================================
        // 5. MUTATION (modifying elements) vs REASSIGNMENT
        // =================================================================
        /*
         * Mutation: arr[index] = value   → modifies the SAME array object.
         * Reassignment: arr = new int[]{...} → creates a NEW object.
         *
         * The old object becomes eligible for Garbage Collection (GC)
         * ONLY if no other live reference still points to it.
         */
        int[] mutable = new int[3];       // [0,0,0]
        mutable[0] = 100;                // mutation – same object, now [100,0,0]
        System.out.println("\nAfter mutation: " + Arrays.toString(mutable));

        mutable = new int[]{1, 2, 3};    // reassignment – new object [1,2,3]
        System.out.println("After reassignment: " + Arrays.toString(mutable));

        // GC nuance: if another reference keeps the old object reachable, it survives.
        int[] backup = mutable;          // backup also references [1,2,3]
        mutable = new int[]{9, 9, 9};    // mutable now points to a new object
        System.out.println("backup still holds old: " + Arrays.toString(backup)); // [1,2,3] survived

        // =================================================================
        // 6. REFERENCE SHARING (THE #1 BEGINNER MISTAKE)
        // =================================================================
        /*
         * b = a  copies the REFERENCE, not the array content.
         * After that, both variables point to the same object.
         * A change through one is visible through the other.
         */
        int[] a = {1, 2, 3};
        int[] b = a;              // b points to the same array object
        b[0] = 999;               // modify via b

        System.out.println("\nReference sharing:");
        System.out.println("a[0] = " + a[0]);   // 999 – affected!
        System.out.println("b[0] = " + b[0]);   // 999

        /*
         * Memory model:
         *   a ──┐
         *       ├────► [999, 2, 3]
         *   b ──┘
         */

        // =================================================================
        // 7. ARRAY COPYING – clone(), copyOf(), copyOfRange()
        // =================================================================
        /*
         * To obtain a SEPARATE copy, use:
         *   - array.clone()              → full copy
         *   - Arrays.copyOf()            → full copy (can also resize)
         *   - Arrays.copyOfRange()       → partial copy (from start to end-1)
         *   - manual loop                → explicit and flexible
         *
         * All these are SHALLOW copies for object arrays:
         * they copy the references, not the objects themselves.
         */
        int[] original = {10, 20, 30};

        int[] cloned = original.clone();            // separate object
        cloned[0] = 555;
        System.out.println("\nArray copying methods:");
        System.out.println("original: " + Arrays.toString(original));   // [10, 20, 30]
        System.out.println("cloned  : " + Arrays.toString(cloned));     // [555, 20, 30]

        int[] copied = Arrays.copyOf(original, original.length);
        copied[1] = 888;
        System.out.println("copyOf  : " + Arrays.toString(copied));     // [10, 888, 30]

        // copyOfRange() – copy a portion of the array
        // Syntax: Arrays.copyOfRange(original, fromIndex, toIndex)
        // toIndex is EXCLUSIVE (like subList)
        int[] range = Arrays.copyOfRange(original, 1, 3);  // indices 1,2 (not 3)
        System.out.println("copyOfRange(1,3): " + Arrays.toString(range));  // [20, 30]

        // You can also resize with copyOf
        int[] resized = Arrays.copyOf(original, 5);  // extends with 0s
        System.out.println("copyOf with resize: " + Arrays.toString(resized));  // [10, 20, 30, 0, 0]

        int[] manual = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            manual[i] = original[i];
        }
        System.out.println("manual copy: " + Arrays.toString(manual));

        // =================================================================
        // 8. System.arraycopy() – native fast copy
        // =================================================================
        /*
         * System.arraycopy() is a highly optimised, native method for
         * copying array sections. It is often used internally by other APIs.
         *
         * Signature:
         * arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
         *
         * More performant than manual loops for large arrays.
         */
        int[] src = {1, 2, 3, 4, 5};
        int[] dest = new int[5];
        System.arraycopy(src, 0, dest, 0, src.length);
        System.out.println("\nSystem.arraycopy: " + Arrays.toString(dest));

        // Partial copy example
        int[] part = new int[3];
        System.arraycopy(src, 1, part, 0, 3);   // copy elements from index 1..3
        System.out.println("Partial copy: " + Arrays.toString(part));   // [2,3,4]

        // =================================================================
        // 9. SHALLOW COPY vs DEEP COPY WITH OBJECT ARRAYS
        // =================================================================
        /*
         * For arrays of mutable objects, a shallow copy still shares the
         * inner objects. You must explicitly create new objects for a
         * full deep copy.
         */
        StringBuilder[] sbArr1 = { new StringBuilder("A"), new StringBuilder("B") };
        StringBuilder[] sbArr2 = sbArr1.clone();     // shallow copy

        sbArr2[0].append("X");                       // modify the shared object
        System.out.println("\nShallow copy of object array:");
        System.out.println("sbArr1[0] = " + sbArr1[0]);   // AX – affected!
        System.out.println("sbArr2[0] = " + sbArr2[0]);   // AX

        // Deep copy – create completely independent objects
        StringBuilder[] sbArr3 = new StringBuilder[sbArr1.length];
        for (int i = 0; i < sbArr1.length; i++) {
            sbArr3[i] = new StringBuilder(sbArr1[i].toString());  // new object with copied content
        }
        sbArr3[0].append("Y");                     // now truly independent
        System.out.println("Deep copy example:");
        System.out.println("sbArr1[0] = " + sbArr1[0]);   // AX (unchanged)
        System.out.println("sbArr3[0] = " + sbArr3[0]);   // AXY (independent)

        /*
         * Memory diagram (conceptual):
         *
         * Shallow copy:
         *   sbArr1 ──► [ref₁, ref₂]
         *   sbArr2 ──► [ref₁, ref₂]   (same references → shared objects)
         *              └─► StringBuilder("AX")
         *              └─► StringBuilder("B")
         *
         * Deep copy:
         *   sbArr1 ──► [ref₁, ref₂]
         *   sbArr3 ──► [ref₃, ref₄]   (different references → independent objects)
         *              └─► StringBuilder("AXY")
         *              └─► StringBuilder("B")
         */

        // =================================================================
        // 10. DEEP COPY vs SHALLOW COPY FOR 2D PRIMITIVE ARRAYS
        // =================================================================
        /*
         * A 2D array is an array of references to int[] objects.
         * clone() only copies the outer array – inner arrays are shared.
         */
        int[][] original2D = { {1, 2}, {3, 4} };
        int[][] shallowCopy2D = original2D.clone();

        shallowCopy2D[0][0] = 999;                    // modify shared inner array
        System.out.println("\nShallow copy of 2D primitive array:");
        System.out.println("original2D[0][0] = " + original2D[0][0]);   // 999 – affected!

        // Deep copy: clone each row individually
        int[][] deepCopy2D = new int[original2D.length][];
        for (int i = 0; i < original2D.length; i++) {
            deepCopy2D[i] = original2D[i].clone();    // each inner int[] cloned
        }
        deepCopy2D[0][0] = 111;
        System.out.println("original2D[0][0] after deep copy modification: " + original2D[0][0]); // stays 999

        /*
         * Memory diagram (CRITICAL):
         *   Original:     [─ ref ─► [1, 2]]
         *                 [─ ref ─► [3, 4]]
         *
         *   ShallowCopy:  [─ same ref ─► [1, 2]]   ← SHARED inner arrays
         *                 [─ same ref ─► [3, 4]]
         *
         *   DeepCopy:     [─ new ref ─► [1, 2]]    ← INDEPENDENT inner arrays
         *                 [─ new ref ─► [3, 4]]
         */

        // =================================================================
        // 11. DEEP COPY FOR NESTED OBJECT ARRAYS (Person[][])
        // =================================================================
        /*
         * When the innermost elements are objects (not primitives), you must
         * also copy each object. Using a copy constructor is a common pattern.
         */

        Person[][] team1 = {
            {new Person("Alice"), new Person("Bob")},
            {new Person("Charlie")}
        };

        // Incorrect shallow clone – inner Person objects are shared
        Person[][] teamShallow = team1.clone();
        teamShallow[0][0].name = "HACKED";
        System.out.println("\nShallow copy of Person[][] shows shared objects:");
        System.out.println("team1[0][0].name = " + team1[0][0].name);   // HACKED

        // Correct deep copy – clone rows AND copy each Person
        Person[][] teamDeep = new Person[team1.length][];
        for (int i = 0; i < team1.length; i++) {
            teamDeep[i] = new Person[team1[i].length];
            for (int j = 0; j < team1[i].length; j++) {
                teamDeep[i][j] = new Person(team1[i][j]); // use copy constructor
            }
        }
        teamDeep[0][0].name = "SAFE";
        System.out.println("Deep copy of Person[][] keeps original safe:");
        System.out.println("team1[0][0].name = " + team1[0][0].name);   // still HACKED

        // =================================================================
        // 12. FIXED SIZE – WHY ArrayList EXISTS
        // =================================================================
        /*
         * An array object's length NEVER changes after creation.
         * To "resize", you must create a new array and copy elements.
         * ArrayList (from Collections) provides automatic resizing internally.
         */

        // =================================================================
        // 13. PRIMITIVE ARRAYS vs OBJECT ARRAYS
        // =================================================================
        /*
         * int[] stores the actual integer values directly.
         * String[] stores REFERENCES to String objects that live elsewhere.
         *
         * Memory layout:
         *   int[] arr = {10,20};
         *   arr ──► [10][20]  (values stored directly)
         *
         *   String[] names = {"A","B"};
         *   names ──► [ref]───► "A"
         *             [ref]───► "B"
         *
         * Performance implication:
         *   - Primitive arrays: direct memory access, no indirection
         *   - Object arrays: reference lookup, cache misses possible
         */
        String[] names = {"Alice", "Bob"};
        System.out.println("\nObject array (references):");
        for (String s : names) System.out.print(s + " ");
        System.out.println();

        // =================================================================
        // 14. == vs Arrays.equals() vs Arrays.deepEquals()
        // =================================================================
        /*
         * == checks reference identity.
         * Arrays.equals(a,b) compares elements one‑by‑one:
         *   - for primitive elements, it uses ==
         *   - for object elements, it uses the element's own .equals() method
         * For nested arrays, Arrays.deepEquals() compares recursively.
         */
        int[] x = {1, 2, 3};
        int[] y = {1, 2, 3};
        System.out.println("\nEquality:");
        System.out.println("x == y            : " + (x == y));               // false
        System.out.println("Arrays.equals     : " + Arrays.equals(x, y));    // true

        // Demonstration with objects – relies on element .equals()
        String[] s1 = {"A", "B"};
        String[] s2 = {"A", "B"};
        String[] s3 = {new String("A"), new String("B")};   // different objects, same content
        System.out.println("s1 == s2: " + (s1 == s2));                     // false
        System.out.println("Arrays.equals(s1,s2): " + Arrays.equals(s1, s2)); // true (both point to same String objects)
        System.out.println("Arrays.equals(s1,s3): " + Arrays.equals(s1, s3)); // true (String.equals compares content, not identity)

        int[][] mat1 = {{1,2},{3,4}};
        int[][] mat2 = {{1,2},{3,4}};
        System.out.println("Arrays.equals 2D : " + Arrays.equals(mat1, mat2));     // false (shallow – inner array references differ)
        System.out.println("Arrays.deepEquals: " + Arrays.deepEquals(mat1, mat2)); // true (recursive comparison)

        // =================================================================
        // 15. Arrays.binarySearch() – efficient lookup (SORTED REQUIRED!)
        // =================================================================
        /*
         * binarySearch() finds an element in a SORTED array in O(log n) time.
         * Returns index if found; otherwise returns -(insertion_point + 1).
         *
         * ⚠️ CRITICAL: The array MUST be sorted before calling binarySearch().
         * Using binarySearch() on an unsorted array gives UNDEFINED RESULTS.
         */
        int[] sorted = {1, 3, 5, 7, 9};  // MUST BE SORTED!
        int idx = Arrays.binarySearch(sorted, 5);
        System.out.println("\nbinarySearch for 5 in sorted array: " + idx);    // 2 (found at index 2)

        int missing = Arrays.binarySearch(sorted, 4);
        System.out.println("binarySearch for 4 (not found): " + missing);  // negative (e.g., -3)
        // If result is negative: insertion_point = -(result + 1)
        // So: 4 should be inserted at index 2 to keep sorted order: 1,3,4,5,7,9

        // ⚠️ DANGEROUS: What happens if array is NOT sorted?
        int[] unsortedSearch = {5, 1, 9, 3, 7};  // NOT SORTED
        int badResult = Arrays.binarySearch(unsortedSearch, 5);
        System.out.println("binarySearch on unsorted array (WRONG): " + badResult);  // undefined!
        // Result is unpredictable – could be wrong index, could be negative, could be anything

        // =================================================================
        // 16. Arrays.asList() – bridge to Collections
        // =================================================================
        /*
         * Arrays.asList() returns a List backed by the original array.
         * Changes to the List affect the array, and the List has fixed size.
         *
         * ⚠️ CRITICAL MISCONCEPTION: It does NOT create an independent copy.
         * ⚠️ LIMITATION: Works with Integer[], NOT with int[] (must use wrapper)
         */
        Integer[] numsObj = {10, 20, 30};                     // must use Integer[] (not int[])
        List<Integer> list = Arrays.asList(numsObj);
        System.out.println("\nasList example:");
        System.out.println("List: " + list);                  // [10, 20, 30]

        list.set(0, 999);                                     // modifies the list AND the backing array
        System.out.println("numsObj[0] after set: " + numsObj[0]);  // 999 – changed!

        // list.add(40);  // throws UnsupportedOperationException – fixed size!

        // To get a truly mutable, independent list:
        List<Integer> mutableCopy = new ArrayList<>(Arrays.asList(numsObj));
        mutableCopy.add(40);  // this works
        System.out.println("Mutable copy: " + mutableCopy);

        // =================================================================
        // 17. EXCEPTIONS RELATED TO ARRAYS
        // =================================================================
        /*
         * Common exceptions with arrays and their causes:
         *
         * ┌─────────────────────────────────────────────┬──────────────────────┐
         * │ Exception                                   │ Cause                │
         * ├─────────────────────────────────────────────┼──────────────────────┤
         * │ ArrayIndexOutOfBoundsException              │ index < 0 or ≥ len   │
         * │ NegativeArraySizeException                  │ new int[-5]          │
         * │ NullPointerException                        │ null row in jagged   │
         * │ ArrayStoreException                         │ covariance violation │
         * │ ClassCastException                          │ wrong cast of array  │
         * └─────────────────────────────────────────────┴──────────────────────┘
         */
        System.out.println("\nException examples:");
        
        // 1. Index out of bounds
        try { 
            int[] arr = {1,2,3}; 
            System.out.println(arr[5]); 
        }
        catch (ArrayIndexOutOfBoundsException e) { 
            System.out.println("✓ Caught: ArrayIndexOutOfBoundsException"); 
        }

        // 2. Negative size
        try { 
            int[] neg = new int[-2]; 
        }
        catch (NegativeArraySizeException e) { 
            System.out.println("✓ Caught: NegativeArraySizeException"); 
        }

        // 3. Null pointer via jagged array
        try { 
            int[][] jag = new int[2][]; 
            System.out.println(jag[0][0]);  // jag[0] is null
        }
        catch (NullPointerException e) { 
            System.out.println("✓ Caught: NullPointerException (null row)"); 
        }

        // 4. Array store exception (covariance)
        try {
            Object[] objArr = new String[3];
            objArr[0] = 42;  // storing Integer into String[]
        }
        catch (ArrayStoreException e) {
            System.out.println("✓ Caught: ArrayStoreException (covariance)");
        }

        // =================================================================
        // 18. ENHANCED FOR‑LOOP (for‑each) – CORRECT FULL EXPLANATION
        // =================================================================
        /*
         * Enhanced for-loop: for (Type var : array)
         * is internally equivalent to:
         *   for (int i = 0; i < array.length; i++) {
         *       Type var = array[i];
         *       // body
         *   }
         *
         * KEY INSIGHT: The loop variable is ALWAYS a LOCAL VARIABLE.
         *
         * ┌──────────────────────────────────────────────────────────────┐
         * │ FOR PRIMITIVES (int x : intArray)                            │
         * ├──────────────────────────────────────────────────────────────┤
         * │ x gets a COPY of the primitive value                         │
         * │ Changing x does NOT affect intArray[i]                       │
         * │ Example: x = 99 does not change intArray[i]                  │
         * └──────────────────────────────────────────────────────────────┘
         *
         * ┌──────────────────────────────────────────────────────────────┐
         * │ FOR OBJECTS (String s : stringArray)                         │
         * ├──────────────────────────────────────────────────────────────┤
         * │ s gets a COPY of the reference (points to same object)       │
         * │ Changing s does NOT replace stringArray[i] with new object   │
         * │ BUT you CAN mutate the object via s.method()                 │
         * │ Example: s.append("X") DOES mutate the object                │
         * │ Example: s = new String(...) does NOT replace stringArray[i] │
         * └──────────────────────────────────────────────────────────────┘
         *
         * BOTTOM LINE: You cannot modify the array itself via the loop
         * variable, whether it contains primitives or objects.
         * However, you CAN modify MUTABLE objects in the array.
         */
        System.out.println("\nEnhanced for-loop behaviour:");

        // Primitives – cannot update
        int[] values = {5, 10, 15};
        for (int v : values) {
            v = v * 2;  // changes only the local variable v
        }
        System.out.println("After failed double: " + Arrays.toString(values)); // [5,10,15] unchanged

        // Objects – cannot replace, but can mutate
        StringBuilder[] builders = { new StringBuilder("X"), new StringBuilder("Y") };
        for (StringBuilder sb : builders) {
            sb.append("Z");  // WORKS – mutates the existing object
            // sb = new StringBuilder("new");  // does NOT change the array
        }
        System.out.println("After append via for-each: " + builders[0] + ", " + builders[1]); // XZ, YZ

        // =================================================================
        // 19. Arrays.toString() & Arrays.deepToString()
        // =================================================================
        /*
         * Directly printing an array variable gives a string like [I@1b6d3586.
         * This encodes:
         *   [ = array
         *   I = int (or L = object, Z = boolean, etc.)
         *   @ = separator
         *   hex = identity hash code (from System.identityHashCode())
         *
         * This is NOT a memory address; it's a JVM‑computed hash.
         * Use Arrays.toString() for 1D, Arrays.deepToString() for nested.
         */
        int[] raw = {77, 88, 99};
        System.out.println("\nDirect print: " + raw);                       // [I@... (unhelpful)
        System.out.println("Arrays.toString: " + Arrays.toString(raw));    // [77, 88, 99]

        int[][] nested = {{1,2},{3,4}};
        System.out.println("Arrays.deepToString: " + Arrays.deepToString(nested)); // [[1, 2], [3, 4]]

        // =================================================================
        // 20. Arrays.fill() – bulk assignment
        // =================================================================
        int[] filled = new int[5];
        Arrays.fill(filled, 42);
        System.out.println("\nAfter Arrays.fill(): " + Arrays.toString(filled)); // [42, 42, 42, 42, 42]

        // You can also fill a range
        int[] partialFill = new int[5];
        Arrays.fill(partialFill, 1, 4, 99);  // fill indices 1-3 (not 4) with 99
        System.out.println("After partial fill: " + Arrays.toString(partialFill)); // [0, 99, 99, 99, 0]

        // =================================================================
        // 21. Arrays.sort() – COMPLETE EXPLANATION
        // =================================================================
        /*
         * Sorts the array in-place using a sophisticated algorithm.
         *
         * ┌────────────────────────────────────────────────────┐
         * │ Algorithm Details                                  │
         * ├────────────────────────────────────────────────────┤
         * │ Primitive arrays: Dual-Pivot Quicksort             │
         * │   - Average: O(n log n)                            │
         * │   - Worst: O(n²) [rare in practice]                │
         * │   - In-place, NOT stable                           │
         * │                                                    │
         * │ Object arrays: Timsort (hybrid of merge + insert)  │
         * │   - Average/Worst: O(n log n)                      │
         * │   - Stable (preserves order of equal elements)     │
         * │   - Uses extra O(n) space                          │
         * └────────────────────────────────────────────────────┘
         *
         * STABILITY: For objects, equal elements keep relative order.
         *           For primitives, order of equal elements is undefined.
         */
        int[] unsorted2 = {5, 2, 9, 1, 7};
        Arrays.sort(unsorted2);
        System.out.println("\nAfter Arrays.sort(): " + Arrays.toString(unsorted2)); // [1, 2, 5, 7, 9]

        // Sorting custom objects – requires Comparable or Comparator
        String[] words = {"zebra", "apple", "mango"};
        Arrays.sort(words);
        System.out.println("Sorted strings: " + Arrays.toString(words)); // [apple, mango, zebra]

        // Reverse order using Comparator
        Integer[] nums2 = {5, 2, 9, 1, 7};
        Arrays.sort(nums2, (a2, b2) -> b2 - a2);  // descending
        System.out.println("Reverse sorted: " + Arrays.toString(nums2)); // [9, 7, 5, 2, 1]

        // Custom object sorting
        Book[] books = {
            new Book("Java", 500),
            new Book("C++", 300),
            new Book("Python", 400)
        };
        Arrays.sort(books, (bk1, bk2) -> Integer.compare(bk1.pages, bk2.pages));  // by page count
        System.out.println("Books sorted by pages: " + Arrays.toString(books));

        // =================================================================
        // 22. RECURSION ON ARRAYS
        // =================================================================
        /*
         * Recursion can traverse arrays. Each recursive call creates a new
         * stack frame. For large arrays, deep recursion can cause StackOverflowError.
         * Iteration is usually preferred for simple traversal.
         */
        System.out.print("\nRecursive print: ");
        printArrayRecursively(arr1, 0);
        System.out.println();

        // =================================================================
        // 23. USER‑INPUT ARRAYS (Scanner)
        // =================================================================
        /*
         * You can read array size and elements at runtime.
         * Always validate input (negative sizes, null, etc.).
         */
        System.out.print("\nEnter size for an array: ");
        int size = sc.nextInt();
        if (size < 0) {
            System.out.println("Size cannot be negative. Exiting.");
            sc.close();
            return;
        }
        int[] userArray = new int[size];
        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            userArray[i] = sc.nextInt();
        }
        System.out.println("You entered: " + Arrays.toString(userArray));

        // =================================================================
        // 24. 2D ARRAYS (FIXED) & JAGGED ARRAYS
        // =================================================================
        /*
         * Java doesn't have true multi-dimensional arrays.
         * A 2D array is: array of arrays (references to 1D arrays).
         */
        int[][] matrix = { {1, 2, 3}, {4, 5, 6} };
        System.out.println("\n2D array (fixed rectangular):");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // Jagged array – rows have different lengths
        int[][] jagged = new int[3][];           // outer array, inner references initially null
        jagged[0] = new int[]{1, 2, 3, 4};
        jagged[1] = new int[]{5, 6, 7};
        jagged[2] = new int[]{8, 9};

        System.out.println("\nJagged array (variable row lengths):");
        for (int i = 0; i < jagged.length; i++) {
            System.out.print("Row " + i + ": ");
            if (jagged[i] != null) {
                for (int j = 0; j < jagged[i].length; j++) {
                    System.out.print(jagged[i][j] + " ");
                }
            } else {
                System.out.print("null");
            }
            System.out.println();
        }

        // =================================================================
        // 25. NULL SAFETY IN JAGGED ARRAYS
        // =================================================================
        int[][] partial = new int[2][];
        partial[0] = new int[]{100, 200};
        // partial[1] is still null

        System.out.println("\nJagged with explicit null check:");
        for (int[] row : partial) {
            if (row != null) {
                for (int val : row) System.out.print(val + " ");
            } else {
                System.out.print("(null row)");
            }
            System.out.println();
        }

        // =================================================================
        // 26. PASS‑BY‑VALUE WITH ARRAYS – CORRECT MENTAL MODEL
        // =================================================================
        /*
         * Java is ALWAYS pass‑by‑value.
         * When you pass an array to a method, the value that gets copied
         * is the REFERENCE itself (JVM-managed handle).
         *
         * This is why the method can mutate the array's content – it uses
         * the same reference to reach the same object.
         *
         * If the method reassigns the local reference to a new array,
         * that change does NOT affect the caller's original reference.
         */
        int[] numbers = {1, 2, 3};
        System.out.println("\nBefore modifyArray: " + Arrays.toString(numbers));
        modifyArray(numbers);
        System.out.println("After modifyArray:  " + Arrays.toString(numbers));   // [999, 2, 3]

        // resetArray only reassigns its local copy – no effect on caller
        resetArray(numbers);
        System.out.println("After resetArray:   " + Arrays.toString(numbers));   // still [999, 2, 3]

        // =================================================================
        // 27. LOCAL VARIABLE vs FIELD INITIALISATION
        // =================================================================
        // int[] localOnly;   // would cause compile error if used without assignment
        int[] localInit = null;   // explicit null assignment is fine
        System.out.println("\nLocal array explicitly null: " + localInit);

        // =================================================================
        // 28. VARARGS – INTERNAL ARRAY, ZERO-ARGS BEHAVIOR
        // =================================================================
        /*
         * A method parameter with ... (varargs) is internally treated as an array.
         * Varargs MUST be the last parameter.
         * Zero arguments are allowed – an empty array is created internally.
         */
        printVarargs("Varargs demo", 10, 20, 30);
        printVarargs("No numbers");   // zero arguments – empty array created
        printVarargs("Single arg", 42);

        // You can also pass an array directly to varargs
        int[] existingArray = {1, 2, 3};
        printVarargs("From array", existingArray[0], existingArray[1], existingArray[2]);

        // =================================================================
        // 29. ARRAY COVARIANCE (DANGEROUS FEATURE)
        // =================================================================
        /*
         * Java allows: SubClass[] to be assigned to SuperClass[].
         * This is called "covariance". It can cause runtime exceptions.
         *
         * This is a legacy feature; generics (with invariance) are safer.
         */
        String[] strArr = {"hello", "world"};
        Object[] objArr = strArr;              // allowed – covariance
        System.out.println("\nCovariance: objArr[0] = " + objArr[0]);

        try {
            objArr[0] = 42;                    // trying to store an Integer into a String[]
        } catch (ArrayStoreException e) {
            System.out.println("✓ ArrayStoreException caught (covariance violation)");
        }

        /*
         * Why generics avoid this:
         * List<String> listStr = new ArrayList<String>();
         * List<Object> listObj = listStr;  // compile error – type-safe!
         *
         * Generics are invariant, not covariant, so type safety is enforced.
         */

        // =================================================================
        // 30. COMPLEXITY CHEAT‑SHEET (Big‑O for arrays) – PRECISE NOTES
        // =================================================================
        /*
         * Time Complexity:
         * ┌────────────────────────────────────────┬─────────────────────┐
         * │ Operation                              │ Time                │
         * ├────────────────────────────────────────┼─────────────────────┤
         * │ Access element by index                │ O(1)                │
         * │ Assignment to index                    │ O(1)                │
         * │ Linear traversal / search              │ O(n)                │
         * │ Copying full array                     │ O(n)                │
         * │ Insert/delete in middle                │ O(n)                │
         * │ Arrays.sort() on objects (Timsort)     │ O(n log n)          │
         * │ Arrays.sort() on primitives (average)  │ O(n log n) average  │
         * │   (worst case Dual-Pivot Quicksort     │ O(n²)               │
         * │    but extremely rare in practice)     │                     │
         * │ Arrays.binarySearch() (sorted)         │ O(log n)            │
         * └────────────────────────────────────────┴─────────────────────┘
         *
         * Space Complexity:
         * ┌────────────────────────────────────────┬─────────────────────┐
         * │ Operation                              │ Space               │
         * ├────────────────────────────────────────┼─────────────────────┤
         * │ Array storage (n elements)             │ O(n)                │
         * │ Arrays.sort() on primitives (in-place) │ O(1)                │
         * │ Arrays.sort() on objects (Timsort)     │ O(n)                │
         * │ Copying array                          │ O(n)                │
         * └────────────────────────────────────────┴─────────────────────┘
         */

        // =================================================================
        // 31. ARRAYS vs COLLECTIONS – WHEN TO USE WHICH
        // =================================================================
        /*
         * ┌──────────────────────────────────────────────────────────────┐
         * │ ARRAYS                                                       │
         * ├──────────────────────────────────────────────────────────────┤
         * │ ✓ Fixed size, known at creation                              │
         * │ ✓ Minimal memory overhead (just the array itself)            │
         * │ ✓ Direct storage for primitives (no boxing)                  │
         * │ ✓ O(1) indexed access                                        │
         * │ ✓ Cache-friendly (contiguous memory)                         │
         * │ ✗ Cannot grow/shrink dynamically                             │
         * │ ✗ Limited APIs (no contains, indexOf, etc.)                  │
         * │ ✗ Covariance issues with objects                             │
         * └──────────────────────────────────────────────────────────────┘
         *
         * ┌──────────────────────────────────────────────────────────────┐
         * │ COLLECTIONS (ArrayList, LinkedList, etc.)                    │
         * ├──────────────────────────────────────────────────────────────┤
         * │ ✓ Dynamic size (add/remove at will)                          │
         * │ ✓ Rich APIs (contains, indexOf, stream operations, etc.)     │
         * │ ✓ Generics provide type safety (no covariance)               │
         * │ ✓ Polymorphism (List interface)                              │
         * │ ✗ Objects only (autoboxing may incur cost)                   │
         * │ ✗ More memory overhead                                       │
         * │ ✗ Slower for fixed-size, performance-critical work           │
         * └──────────────────────────────────────────────────────────────┘
         *
         * Use arrays for:
         *   - Performance-critical, fixed-size data (e.g., pixel buffer)
         *   - Large amounts of primitives (avoid boxing cost)
         *
         * Use ArrayList for:
         *   - Most business logic (simplicity, safety)
         *   - Dynamic sizing needs
         */

        // =================================================================
        // 32. MEMORY LAYOUT AND CACHE IMPLICATIONS
        // =================================================================
        /*
         * Arrays store elements in CONTIGUOUS memory.
         * This is why they're cache-friendly.
         *
         * Memory layout of int[] arr = {10, 20, 30}:
         *   heap:
         *   ┌─────────────┬──────────┬──────────┬──────────┐
         *   │ array obj   │ 10       │ 20       │ 30       │
         *   │ (header)    │ (4 bytes)│ (4 bytes)│ (4 bytes)│
         *   └─────────────┴──────────┴──────────┴──────────┘
         *   ↑                                              ↑
         *   arr points here                              contiguous
         *
         * Memory layout of String[] arr = {"A", "B"}:
         *   heap:
         *   ┌─────────────┬──────────┬──────────┐
         *   │ array obj   │ ref1     │ ref2     │
         *   │ (header)    │ (8 bytes)│ (8 bytes)│
         *   └─────────────┴──────┬───┴────┬─────┘
         *                        │        │
         *                    ┌───▼──┐   ┌─▼──┐
         *                    │ "A"  │   │"B" │
         *                    └──────┘   └────┘
         *   arr points to array object; actual strings are elsewhere
         *
         * Cache implication:
         *   - int[]: all values likely in same cache line → fast iteration.
         *   - String[]: array refs may be cached, but actual strings may be
         *     scattered → possible cache misses.
         *
         * This is why primitive arrays are faster for tight loops.
         */

        // =================================================================
        // 33. AUTOBOXING vs PRIMITIVE ARRAYS
        // =================================================================
        /*
         * Autoboxing: automatic conversion from primitive to wrapper (int → Integer).
         * Unboxing: automatic conversion from wrapper to primitive (Integer → int).
         *
         * ┌─────────────────────────────────────────────────────────┐
         * │ Primitive Array (int[])                                 │
         * ├─────────────────────────────────────────────────────────┤
         * │ Memory: 4 bytes per int, contiguous                     │
         * │ Speed: Direct memory access, cache-friendly             │
         * │ Example: [10][20][30]                                   │
         * │ Size: 12 bytes (+ small header)                         │
         * └─────────────────────────────────────────────────────────┘
         *
         * ┌─────────────────────────────────────────────────────────┐
         * │ Object Array (Integer[])                                │
         * ├─────────────────────────────────────────────────────────┤
         * │ Memory: 8 bytes per ref (64-bit JVM) + Integer objects  │
         * │ Boxing: Creates new Integer object for each primitive   │
         * │ Speed: Reference lookup, potential cache misses         │
         * │ Example: [ref1→10][ref2→20][ref3→30]                   │
         * │ Size: 24+ bytes for array + 3×Integer objects (≈60 bytes)│
         * └─────────────────────────────────────────────────────────┘
         *
         * Trade-off:
         *   int[]: Fast, memory-efficient, limited APIs
         *   Integer[]: Slow, memory-hungry, full generics support
         *
         * Never use Integer[] for performance-critical code.
         */
        int[] primArray = new int[1000];    // ≈4000 bytes
        Integer[] objArray = new Integer[1000];  // ≈8000+ bytes (refs + objects)

        // =================================================================
        // 34. TYPE CONVERSION PATTERNS
        // =================================================================
        /*
         * Common conversions between arrays and collections:
         */
        System.out.println("\nType conversion patterns:");

        // int[] → List<Integer> (requires boxing)
        int[] intArr = {1, 2, 3};
        List<Integer> list1 = new ArrayList<>();
        for (int n : intArr) list1.add(n);  // manual: explicit boxing
        System.out.println("int[] → List<Integer>: " + list1);

        // String[] → List<String>
        String[] strArr2 = {"a", "b", "c"};
        List<String> list2 = Arrays.asList(strArr2);  // backed by array
        List<String> list3 = new ArrayList<>(Arrays.asList(strArr2));  // independent copy
        System.out.println("String[] → List<String>: " + list3);

        // List<String> → String[]
        List<String> strList = new ArrayList<>(Arrays.asList("x", "y", "z"));
        String[] backToArray = strList.toArray(new String[0]);  // pre-sized array
        System.out.println("List<String> → String[]: " + Arrays.toString(backToArray));

        // =================================================================
        // 35. COMMON MISTAKES & CORRECTIONS (COMPREHENSIVE TABLE)
        // =================================================================
        System.out.println("\n================ COMMON MISTAKES & CORRECTIONS =================");
        System.out.println(
            "+───────────────────────────────────────────+─────────────────────────────────────────────────────+\n" +
            "| Mistake                                   | Correct Approach                                    |\n" +
            "+───────────────────────────────────────────+─────────────────────────────────────────────────────+\n" +
            "| arr.length()                              | arr.length (property, no parentheses)              |\n" +
            "| b = a copies the array (deep)             | copies the reference → shared object              |\n" +
            "| Comparing arrays with ==                  | Arrays.equals() (1D) / deepEquals() (nested)       |\n" +
            "| Printing array directly                   | Arrays.toString() / deepToString()                 |\n" +
            "| Forgetting defaults: int[] gets null      | int[] gets 0; String[] gets null                   |\n" +
            "| Modifying for-each loop var updates array | Primitives: no; Objects: only via method calls     |\n" +
            "| Accessing index >= length                 | Causes ArrayIndexOutOfBoundsException               |\n" +
            "| Using jagged row without null check        | Always check row != null before access             |\n" +
            "| Thinking clone() gives deep copy of object | It only copies references; create new objects     |\n" +
            "| Expecting varargs as non-last parameter    | Varargs must be the last parameter                 |\n" +
            "| Using asList() to create independent list  | It's backed by the array; fixed size, shares data  |\n" +
            "| Ignoring array covariance bugs             | Understand covariant assignment can throw ASE      |\n" +
            "| Using binarySearch() on unsorted array     | Array MUST be sorted first                          |\n" +
            "| Assuming all equal elements keep order     | Stable only for objects (Timsort); undefined for   |\n" +
            "|                                           | primitives (Dual-Pivot Quicksort)                  |\n" +
            "| Using Integer[] when int[] needed          | Avoid boxing cost; use primitives for perf-critical |\n" +
            "+───────────────────────────────────────────+─────────────────────────────────────────────────────+\n"
        );

        // =================================================================
        // 36. KEY TAKEAWAYS (COMPLETE)
        // =================================================================
        System.out.println("\n======== KEY TAKEAWAYS (DEFINITIVE) ========");
        /*
         *  1. Arrays are objects; variables hold references (JVM handles).
         *  2. new type[5] fills with type-specific defaults (0, false, null...).
         *  3. arr[i] = x mutates the existing object; arr = new mutates reference only.
         *  4. b = a copies the reference → shared object (THE #1 beginner mistake).
         *  5. clone()/copyOf() are shallow copies; manually copy objects for deep copy.
         *  6. Arrays are fixed size; ArrayList is dynamic (use for most cases).
         *  7. arr.length is a property; str.length() is a method.
         *  8. == checks reference identity; Arrays.equals() checks element content.
         *  9. Java passes a COPY of the reference (pass-by-value), not the array itself.
         * 10. Enhanced for-loop gives local variable copy; cannot update array directly.
         * 11. Arrays.binarySearch() requires SORTED array; undefined on unsorted.
         * 12. Arrays.asList() creates a fixed-size, backed view; not independent.
         * 13. Array covariance is dangerous; prefer generics for type safety.
         * 14. Jagged arrays require null checks before accessing rows.
         * 15. Varargs are arrays internally; must be the last parameter.
         * 16. Primitive arrays are cache-friendly; objects arrays incur reference cost.
         * 17. Integer[] incurs boxing cost; use int[] for performance-critical work.
         * 18. Arrays.sort() uses Timsort (stable) for objects, Dual-Pivot Quicksort for prims.
         * 19. 2D array shallow copy only duplicates outer array; clone each row for independence.
         * 20. Covariance allows String[] → Object[] but can throw ArrayStoreException at runtime.
         */

        sc.close();
    }

    // Varargs method – demonstrates that varargs are just arrays internally
    private static void printVarargs(String label, int... numbers) {
        System.out.print(label + ": ");
        if (numbers.length == 0) {
            System.out.println("(empty array)");
        } else {
            for (int n : numbers) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }

    // Demonstrates that reassigning a local reference does not affect the caller
    private static void resetArray(int[] arr) {
        arr = new int[]{0, 0, 0};   // only local copy of reference is changed
    }
}
