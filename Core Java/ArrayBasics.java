public class Main {
    public static void ArrayBasics(String[] args) {

        // ===================== arr1 =====================
        // Direct initialization → JVM creates + fills immediately
        int[] arr1 = {1, 2, 3, 4, 5};

        // Reading values using index
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();


        // ===================== arr2 =====================
        // Step 1: Create array (heap → [0,0,0,0,0])
        int[] arr2 = new int[5];

        // Step 2: MODIFY same array (no new object created)
        // IMPORTANT: This changes values inside existing memory
        arr2[0] = 6;
        arr2[1] = 7;
        arr2[2] = 8;
        arr2[3] = 9;
        arr2[4] = 10;

        // Loop prints modified values
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();


        // ===================== arr3 =====================
        // Explicit object creation + initialization
        int[] arr3 = new int[]{11, 12, 13, 14, 15};

        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }
        System.out.println();


        // ===================== arr4 =====================
        // Step 1: Create array (heap → [0,0,0,0,0])
        int[] arr4 = new int[5];

        // Step 2: REASSIGN reference
        // IMPORTANT DIFFERENCE:
        // ❌ NOT modifying old array
        // ✅ Creating NEW array and making arr4 point to it

        // OLD array → lost reference → Garbage Collector will clean it
        arr4 = new int[]{16, 17, 18, 19, 20};

        for (int i = 0; i < arr4.length; i++) {
            System.out.print(arr4[i] + " ");
        }

        System.out.println();


        // ===================== CRITICAL CONCEPT =====================
        // Reference sharing (THIS IS WHERE PEOPLE FAIL)

        int[] a = {1, 2, 3};

        // b is NOT a new array
        // b copies the REFERENCE (both point to SAME heap object)
        int[] b = a;

        // MODIFY through b
        b[0] = 100;

        // This prints 100, NOT 1
        // Because both a and b refer to SAME array
        System.out.println(a[0]);

        // Mental model:
        // a ----\
        //        ---> [100][2][3]
        // b ----/
    }
}
```
