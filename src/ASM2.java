import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    String firstName;
    String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Không ghi đè phương thức toString. Thay vào đó, sử dụng phương thức này để trả về thông tin sinh viên.
    public String getStudentInfo() {
        return lastName + " " + firstName; // Định dạng "Họ, Tên"
    }
}
public class ASM2 {
    private static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Student Manager System");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Enter Student List");
            System.out.println("2. Find Students by Last Name");
            System.out.println("3. Find and Edit Students by Full Name");
            System.out.println("4. End");
            System.out.print("Enter your choice: ");
            int choice;
            while (true) {
                try {
                    choice = Integer.parseInt(sc.nextLine().trim());
                    break; // Thoát vòng lặp nếu nhập hợp lệ
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }

            switch (choice) {
                case 1:
                    enterStudentList(sc);
                    break;
                case 2:
                    findStudentsByLastName(sc);
                    break;
                case 3:
                    findAndEditStudentByFullName(sc);
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void enterStudentList(Scanner sc) {
        System.out.print("Enter number of students: ");
        int numberOfStudents;
        while (true) {
            try {
                numberOfStudents = Integer.parseInt(sc.nextLine().trim());
                break; // Thoát vòng lặp nếu nhập hợp lệ
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.print("Enter first name: ");
            String firstName = sc.nextLine().trim();
            System.out.print("Enter last name: ");
            String lastName = sc.nextLine().trim();
            if (firstName.isEmpty() || lastName.isEmpty()) {
                System.out.println("First name and last name cannot be empty. Please try again.");
                i--; // Giảm i để lặp lại lần nhập này
                continue;
            }
            studentList.add(new Student(firstName, lastName));
        }
        System.out.println("Students added successfully.");
    }

    private static void findStudentsByLastName(Scanner sc) {
        System.out.print("Enter last name to search: ");
        String lastName = sc.nextLine().trim();
        boolean found = false;

        if (lastName.isEmpty()) {
            System.out.println("Last name cannot be empty.");
            return;
        }

        for (Student student : studentList) {
            if (student.lastName.equalsIgnoreCase(lastName)) {
                System.out.println(student.getStudentInfo()); // Sử dụng phương thức getStudentInfo để in thông tin
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found with last name: " + lastName);
        }
    }

    private static void findAndEditStudentByFullName(Scanner sc) {
        System.out.print("Enter full name (LastName FirstName) to edit: ");
        String fullName = sc.nextLine().trim();

        if (fullName.isEmpty()) {
            System.out.println("Full name cannot be empty.");
            return;
        }

        boolean found = false;

        // Tìm kiếm sinh viên theo tên đầy đủ
        for (Student student : studentList) {
            String currentFullName = student.lastName + " " + student.firstName;

            if (currentFullName.equalsIgnoreCase(fullName)) {
                found = true;
                System.out.println("Student found: " + currentFullName);

                // Nhập thông tin mới
                System.out.print("Enter new first name: ");
                String newFirstName = sc.nextLine().trim();
                System.out.print("Enter new last name: ");
                String newLastName = sc.nextLine().trim();

                if (newFirstName.isEmpty() || newLastName.isEmpty()) {
                    System.out.println("First name and last name cannot be empty. Update failed.");
                    return;
                }

                // Cập nhật thông tin sinh viên
                student.firstName = newFirstName;
                student.lastName = newLastName;
                System.out.println("Student details updated successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("No student found with the name: " + fullName);
        }
    }



}