```java
public class Main {
    public static void ArrayBasics(String[] args) {

        // ===================== arr1 =====================
        // Direct initialization (shorthand)
        // JVM:
        // 1. Creates array object in HEAP of size 5
        // 2. Immediately fills values
        // 3. 'arr1' (reference in stack) points to that array
        int[] arr1 = {1, 2, 3, 4, 5};

        // Loop:
        // i = 0 → arr1[0] → 1
        // i = 1 → arr1[1] → 2
        // ...
        // i = 4 → arr1[4] → 5
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();


        // ===================== arr2 =====================
        // Step 1: Create empty array
        // JVM:
        // Heap → [0][0][0][0][0] (default values)
        // arr2 (stack) → points to this array
        int[] arr2 = new int[5];

        // Step 2: Manually overwrite each index
        // We are MODIFYING the same array (NOT creating new one)
        arr2[0] = 6;
        arr2[1] = 7;
        arr2[2] = 8;
        arr2[3] = 9;
        arr2[4] = 10;

        // Loop reads from same memory block
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();


        // ===================== arr3 =====================
        // Explicit array object creation + initialization
        // Same as arr1 internally, just more explicit syntax
        // Useful when passing directly into methods
        int[] arr3 = new int[]{11, 12, 13, 14, 15};

        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }
        System.out.println();


        // ===================== arr4 =====================
        // Step 1: Create array
        // Heap → [0][0][0][0][0]
        int[] arr4 = new int[5];

        // IMPORTANT:
        // Step 2: REASSIGN reference
        // This DOES NOT modify old array
        // It creates NEW array and changes reference

        // OLD array → becomes unreachable → Garbage Collector will clean it
        // NEW array → [16][17][18][19][20]
        arr4 = new int[]{16, 17, 18, 19, 20};

        // Loop prints from NEW array
        for (int i = 0; i < arr4.length; i++) {
            System.out.print(arr4[i] + " ");
        }
    }
}
```
