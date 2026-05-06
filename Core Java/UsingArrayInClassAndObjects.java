class Student {
    String name;
    int rollNo;
    double marks;
}

public class UsingArrayInClassAndObjects {
    public static void main(String[] args) {

        // Step 1: Create objects
        Student s1 = new Student();
        s1.name = "Sathiya"; 
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

        // Step 2: Create array of Student
        Student[] students = new Student[3];

        // Step 3: Assign objects to array
        students[0] = s1;
        students[1] = s2;
        students[2] = s3;

        // Step 4: Iterate through array
        for (int i = 0; i < students.length; i++) {
            System.out.println("Name: " + students[i].name);
            System.out.println("Roll No: " + students[i].rollNo);
            System.out.println("Marks: " + students[i].marks);
            System.out.println("----------------------");
        }
    }
}
