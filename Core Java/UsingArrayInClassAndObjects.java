class Student {
    // These are instance variables (stored inside each object in heap)
    String name;     // reference → points to String object
    int rollNo;      // primitive → actual value stored
    double marks;    // primitive → actual value stored
}

public class UsingArrayInClassAndObjects {
    public static void main(String[] args) {

        // ===== OBJECT CREATION =====
        // new Student() → allocates memory in HEAP
        // s1 → reference variable stored in STACK → points to heap object

        Student s1 = new Student();   // heap: Student object created
        // Initially:
        // name = null, rollNo = 0, marks = 0.0

        s1.name = "Sathiya";   // modifies heap object via reference
        s1.rollNo = 1;
        s1.marks = 80.5;

        Student s2 = new Student();
        s2.name = "Bala";
        s2.rollNo = 2;
        s2.marks = 90.0;

        Student s3 = new Student();
        s3.name = "Vishal";
        s3.rollNo = 3;
        s3.marks = 78.2;


        // ===== ARRAY CREATION =====
        // new Student[3] → creates array object in HEAP
        // IMPORTANT: only references are created, NOT Student objects

        Student[] students = new Student[3];

        // Current state in memory:
        // students → [ null, null, null ]


        // ===== ASSIGN OBJECT REFERENCES =====
        // We are storing references (addresses), NOT copying objects

        students[0] = s1;   // now index 0 points to s1 object
        students[1] = s2;
        students[2] = s3;

        // Memory view:
        // students → [ s1, s2, s3 ]
        //                ↓   ↓   ↓
        //              obj obj obj


        // ===== ITERATION =====
        // students.length → property (NOT method)
        // gives number of elements (here: 3)

        for (int i = 0; i < students.length; i++) {

            // students[i] → gives reference at index i
            // students[i].name → access field of that object

            System.out.println("Name: " + students[i].name);
            System.out.println("Roll No: " + students[i].rollNo);
            System.out.println("Marks: " + students[i].marks);
            System.out.println("----------------------");
        }


        // ===== CRITICAL UNDERSTANDING =====
        // 1. No object copy happened → only references stored
        // 2. Changing s1 also affects students[0]

        // Example:
        // s1.name = "Changed";
        // System.out.println(students[0].name); → "Changed"


        // ===== DANGEROUS CASE =====
        // If you skip assigning:
        // Student[] arr = new Student[3];
        // arr[0].name → ❌ NullPointerException
        // because arr[0] is still null


        // ===== SUMMARY (MENTAL MODEL) =====
        // STACK: s1, s2, s3, students (references)
        // HEAP:
        //   - 3 Student objects
        //   - 1 array object holding 3 references

    }
}
