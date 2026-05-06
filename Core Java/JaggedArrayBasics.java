// Main class
public class JaggedArrayBasics {

    // JVM starts program execution from main method
    public static void main(String[] args) {



        // =====================================================
        // DIFFERENT WAYS OF CREATING 1D ARRAYS
        // =====================================================
        // ---------------------------------
        // METHOD 1 : Shortcut Initialization
        // ---------------------------------

        // Creates array object directly in heap memory
        //
        // Memory:
        //
        // arr1
        //   |
        //   v
        // [1,2,3,4,5]
        //
        int[] arr1 = {1,2,3,4,5};



        // ---------------------------------
        // METHOD 2 : Using new keyword
        // ---------------------------------

        // Explicitly creates array object
        //
        // Memory:
        //
        // arr2
        //   |
        //   v
        // [1,2,3,4,5]
        //
        int[] arr2 = new int[]{1,2,3,4,5};



        // ---------------------------------
        // METHOD 3 : Separate declaration
        // ---------------------------------

        // Only reference variable created
        //
        // IMPORTANT:
        // No array object yet
        //
        // arr3 currently stores nothing useful
        //
        int[] arr3;



        // Now actual array object is created
        //
        // arr3
        //   |
        //   v
        // [1,2,3,4,5]
        //
        arr3 = new int[]{1,2,3,4,5};





        // =====================================================
        // GARBAGE COLLECTION IMPORTANT EXAMPLE
        // =====================================================



        // Creates FIRST array object
        //
        // Since this is int array,
        // default values become 0
        //
        // arr4
        //   |
        //   v
        // [0,0,0,0,0]
        //
        int[] arr4 = new int[5];



        // Creates SECOND array object
        //
        // arr4 now points to new object
        //
        // arr4
        //   |
        //   v
        // [1,2,3,4,5]
        //
        arr4 = new int[]{1,2,3,4,5};



        // IMPORTANT:
        //
        // Old object:
        //
        // [0,0,0,0,0]
        //
        // now has NO reference pointing to it
        //
        // So it becomes:
        //
        // "Eligible for Garbage Collection"
        //
        // JVM may remove it later





        // =====================================================
        // NORMAL FOR LOOP
        // =====================================================



        // Normal for loop uses INDEXES
        //
        // i values:
        // 0,1,2,3,4
        //
        // arr1[i]
        // means:
        //
        // arr1[0]
        // arr1[1]
        // arr1[2]
        // ...
        //
        for(int i = 0; i < arr1.length; i++) {

            // Prints each element using index
            System.out.print(arr1[i] + " ");
        }



        // Moves cursor to next line
        System.out.println();





        // =====================================================
        // ENHANCED FOR LOOP / FOR-EACH LOOP
        // =====================================================



        // j directly stores VALUES one by one
        //
        // Internal flow:
        //
        // j = 1
        // j = 2
        // j = 3
        // j = 4
        // j = 5
        //
        // IMPORTANT:
        // No indexes are used here
        //
        for(int j : arr2) {

            System.out.print(j + " ");
        }



        System.out.println();





        // =====================================================
        // FIXED 2D ARRAY
        // =====================================================



        // Creates complete 2D structure immediately
        //
        // Memory:
        //
        // [
        //   [1,2,3],
        //   [4,5,6]
        // ]
        //
        int[][] arr5 = new int[][]{
            {1,2,3},
            {4,5,6}
        };





        // =====================================================
        // JAGGED ARRAY
        // =====================================================



        // Creates ONLY outer array
        //
        // IMPORTANT:
        // Inner arrays are NOT created yet
        //
        // Memory initially:
        //
        // [
        //   null,
        //   null,
        //   null
        // ]
        //
        // Why null?
        //
        // Because:
        // int[][] stores REFERENCES to int[]
        //
        // Default value of reference variables = null
        //
        int[][] arr6 = new int[3][];





        // Creates first inner array object
        //
        // [
        //   [1,2,3,4],
        //   null,
        //   null
        // ]
        //
        arr6[0] = new int[]{1,2,3,4};





        // Creates second inner array object
        //
        // [
        //   [1,2,3,4],
        //   [5,6,7],
        //   null
        // ]
        //
        arr6[1] = new int[]{5,6,7};





        // Creates third inner array object
        //
        // Final structure:
        //
        // [
        //   [1,2,3,4],
        //   [5,6,7],
        //   [8,9]
        // ]
        //
        arr6[2] = new int[]{8,9};





        // =====================================================
        // ANOTHER JAGGED ARRAY
        // =====================================================



        int[][] arr7 = new int[3][];



        // Creates first inner array
        arr7[0] = new int[]{1,2,3};



        // Creates second inner array
        arr7[1] = new int[]{4,5};



        // IMPORTANT:
        //
        // Third row is STILL NULL
        //
        // Final structure:
        //
        // [
        //   [1,2,3],
        //   [4,5],
        //   null
        // ]
        //





        // =====================================================
        // PRINTING FIXED 2D ARRAY USING NORMAL FOR LOOP
        // =====================================================



        // Outer loop controls ROWS
        //
        // i values:
        // 0,1
        //
        for(int i = 0; i < arr5.length; i++) {



            // Inner loop controls COLUMNS
            //
            // arr5[i].length changes based on row size
            //
            for(int j = 0; j < arr5[i].length; j++) {



                // arr5[i][j]
                //
                // i = row index
                // j = column index
                //
                // Example:
                //
                // arr5[0][0] = 1
                // arr5[0][1] = 2
                // arr5[1][2] = 6
                //
                System.out.print(arr5[i][j] + " ");
            }
        }



        System.out.println();





        // =====================================================
        // PRINTING JAGGED ARRAY USING ENHANCED FOR LOOP
        // =====================================================



        // row stores COMPLETE int[] arrays
        //
        // Iteration flow:
        //
        // row = [1,2,3,4]
        // row = [5,6,7]
        // row = [8,9]
        //
        for(int[] row : arr6) {



            // val stores integers one by one
            //
            // Example:
            //
            // val = 1
            // val = 2
            // ...
            //
            for(int val : row) {

                System.out.print(val + " ");
            }
        }



        System.out.println();





        // =====================================================
        // NULL SAFETY CHECK IN JAGGED ARRAY
        // =====================================================



        // Iteration flow:
        //
        // row = [1,2,3]
        // row = [4,5]
        // row = null
        //
        for(int[] row : arr7) {



            // IMPORTANT:
            //
            // Prevents NullPointerException
            //
            // because third row is null
            //
            if(row != null) {



                // Only executes if row contains array object
                //
                // If row is null,
                // this block is skipped
                //
                for(int val : row) {

                    System.out.print(val + " ");
                }
            }
        }
    }
}
