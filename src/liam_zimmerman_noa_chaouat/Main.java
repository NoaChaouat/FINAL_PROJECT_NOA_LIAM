package liam_zimmerman_noa_chaouat;

import java.util.Scanner;

public class Main {
    private static final Scanner s = new Scanner(System.in);
    private static college_manager college;

    public static void main(String[] args) {
//        Liam Zimmerman Noa Chaouat
        college = new college_manager();
        College_name();
        run();
        s.close();
    }
    hello
    private static final String[] MENU = {
            "Exit program",
            "Add lecturer",
            "Add committee",
            "Assign lecturer to Committee",
            "Update committee chair",
            "Remove lecturer from committee",
            "Add Study Department",
            "Show average salary of all lecturer",
            "Show Average Salary of Lecturers in Specific Study Department",
            "Show all lecturer info",
            "Show all committee info",
            "Add Lecturer To Department"
    };

    private static void run() {
        int userChosen;
        do {
            userChosen = showMenu();
            switch (userChosen) {
                case 0 -> System.out.println("Done... Bye!");
                case 1 -> addLecturer();
                case 2 -> addCommittee();
                case 3 -> addLecturerToCommittee();
                case 4 -> updateCommitteeChair();
                case 5 -> removeLecturerFromCommittee();
                case 6 -> addStudyDepartment();
                case 7 -> showAverageLecturerSalaryCollege();
                case 8 -> showAverageSalaryDepartment();
                case 9 -> showLecturers();
                case 10 -> showCommittees();
                case 11 -> AddLecturerToDepartment();

            }
        } while (userChosen != 0);
    }

    private static void College_name() {
        System.out.println("Enter the name of your college:  ");
        String collegeName = s.nextLine();
    }

    //    ADDING FUNCTIONS:
    private static String addDegree() {
        String degree = s.nextLine().toUpperCase();

        while (!Util.isValidDegree(degree)){
            System.out.println(UserMessage.DEGREE_ISNT_VALID);
            System.out.println("Enter Degree - First / Second / Dr / Professor");
            degree = s.nextLine().toUpperCase();
        }
        return degree;
    }

    private static void addLecturer() {
        s.nextLine();
        System.out.println("Enter Lecturer Name");
        String name = s.nextLine();
        System.out.println("Enter ID");
        String id = s.nextLine();
        System.out.println("Enter Degree - First / Second / Dr / Professor");
        String degree = addDegree();
        System.out.println("Enter Field Of Study");
        String fieldOfStudy = s.nextLine();
        System.out.println("Enter Salary");
        double salary = s.nextDouble();
        s.nextLine();


        UserMessage res;
        do {
            res = college.addLecturer(name, id, degree, fieldOfStudy, salary);
            if (res == UserMessage.ADD_LECTURER_FAILED) {
                System.out.println(res);
                name = s.nextLine();
            }
        } while (res == UserMessage.ADD_LECTURER_FAILED);
        System.out.println(res);
    }

    private static void AddLecturerToDepartment() {
        s.nextLine();
        System.out.println("Enter Lecturer Name");
        String lecturerName = s.nextLine();
        System.out.println("Enter Study Department Name");
        String committeeName = s.nextLine();

        UserMessage res = college.addLecturerToDepartment(lecturerName,committeeName);
        System.out.println(res);

    }

    private static void addCommittee() {
        s.nextLine();
        System.out.println("Enter Committee name");
        String committeeName = s.nextLine();
        System.out.println("Enter Chairman name");
        String chairMan = s.nextLine();

        UserMessage res = college.addCommittee(committeeName,chairMan);
        System.out.println(res);

    }

    private static void addLecturerToCommittee() {
        s.nextLine();
        System.out.println("Enter Lecturer Name");
        String lecturerName = s.nextLine();
        System.out.println("Enter Committee Name");
        String committeeName = s.nextLine();

        UserMessage res = college.addLecturerToCommittee(lecturerName,committeeName);
        System.out.println(res);

    }

    private static void addStudyDepartment() {
        s.nextLine();
        System.out.println("Enter Department Name");
        String departmentName = s.nextLine();
        System.out.println("Enter Num of Students in the department");
        int numOfStudents = s.nextInt();

        UserMessage res;
        do {
            res = college.addStudyDepartment(departmentName,numOfStudents);
            if (res == UserMessage.ADD_DEPARTMENT_FAIL_NAME_EXISTS) {
                System.out.println(res);
                departmentName= s.nextLine();
            }

        } while (res == UserMessage.ADD_DEPARTMENT_FAIL_NAME_EXISTS);
        System.out.println(res);


    }

    // CHANGING FUNCTIONS:
    private static void updateCommitteeChair() {
        s.nextLine();
        System.out.println("Enter Chairman Name");
        String chairman = s.nextLine();
        System.out.println("Enter Committee Name");
        String committee = s.nextLine();

        UserMessage res = college.updateCommitteeChair(chairman,committee);
        System.out.println(res);


    }

    private static void removeLecturerFromCommittee() {
        s.nextLine();
        System.out.println("Enter Lecturer Name");
        String lecturerName = s.nextLine();
        System.out.println("Enter Committee Name");
        String committeeName = s.nextLine();

        UserMessage res = college.removeLecturerFromCommittee(lecturerName,committeeName);
        System.out.println(res);

    }

    // SHOWING FUNCTIONS:
    private static void showAverageLecturerSalaryCollege() {
        s.nextLine();
        System.out.println("The average of all lecturer in this college : "+college.averageLecturerSalaryPerCollege());

}

    private static void showAverageSalaryDepartment() {
        s.nextLine();
        double res;
        System.out.println("Enter department name: ");
        String department = s.nextLine();
        res=college.averageLecturerSalaryPerDepartment(department);
        if(res==-1){
            System.out.println(UserMessage.DEPARTMENT_DOESNT_EXIST);
        }else {
            System.out.println("The average of all lecturer in this department is: " + res);
        }
    }

    private static void showLecturers() {

        System.out.println("The Lecturers of The College:" + '\n');
        StringBuilder res = college.showLecturers();
        System.out.println(res);
    }

    private static void showCommittees() {
        System.out.println("The Committees of The College:" + '\n');
        StringBuilder res = college.showCommittee();
        System.out.println(res);
    }

    private static int showMenu() {
        System.out.println("\n====== Menu =======");
        for (int i = 0; i < MENU.length; i++) {
            System.out.println(i + ". " + MENU[i]);
        }
        System.out.println("Please enter your choice : ");
        int userChoice = s.nextInt();
        return userChoice;

    }
}


