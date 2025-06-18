package liam_zimmerman_noa_chaouat;

import java.util.ArrayList;
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
            "Add Lecturer To Department",
            "Compare between 2 Doctor/Professors",
            "Compare between Committees by members and articles ",
            "Create Duplication of Committee"
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
                case 12 -> CompareArticles();
                case 13 -> compareCommittee();
                case 14 -> DuplicateCommittee();


            }
        } while (userChosen != 0);
    }


    private static void College_name() {
        System.out.println("Enter the name of your college:  ");
        String collegeName = s.nextLine();
    }

    private static void addLecturer() {
        s.nextLine();
        System.out.println("Enter Lecturer Name");
        String name = s.nextLine();
        System.out.println("Enter ID");
        String id = s.nextLine();
        String degree;

        do {
            System.out.println("Enter Degree - First / Second / Dr / Professor");
            degree = s.nextLine().toUpperCase();
        } while (!Util.isValidDegree(degree));

        System.out.println("Enter Field Of Study");
        String fieldOfStudy = s.nextLine();
        System.out.println("Enter Salary");
        double salary = s.nextDouble();
        s.nextLine();


// איסוף נתונים נוספים במקרה וזה דוקטור או פרופסור
        ArrayList<String> articles = null;
        String grantorProfessora = null;
        if (degree.equals("DR") || degree.equals("PROFESSOR")){
            System.out.println("Enter number of articles:");
            int numOfArticles = s.nextInt();
            s.nextLine();
             articles = new ArrayList<>();
            for (int i = 0; i < numOfArticles; i++) {
                System.out.println("Enter article" + (i + 1));
                articles.add(s.nextLine());
            }
            if (degree.equals("PROFESSOR")){
                System.out.println("Enter institution that awarded the professorship");
                grantorProfessora = s.nextLine();
            }

        }

        while (true) {
            try {
                if (degree.equals("PROFESSOR")) {
                    college.addLecturer(name, id, degree, fieldOfStudy, salary, articles, grantorProfessora);
                } else if (degree.equals("DR")) {
                    college.addLecturer(name, id, degree, fieldOfStudy, salary, articles);
                } else {
                    college.addLecturer(name, id, degree, fieldOfStudy, salary);
                }
                break;
            } catch (ExceptionNameTaken e) {
                System.out.println(e.getMessage());
                name = s.nextLine();

            } catch (ExceptionUserMessage e) {
                System.out.println(e.getMessage());
                break;
            }

        }




    }

    private static void AddLecturerToDepartment() {
        s.nextLine();
        System.out.println("Enter Lecturer Name");
        String lecturerName = s.nextLine();
        System.out.println("Enter Study Department Name");
        String committeeName = s.nextLine();
        try {
            college.addLecturerToDepartment(lecturerName,committeeName);

        } catch (ExceptionUserMessage e) {
            System.out.println(e.getMessage());
        }

    }

    private static void addCommittee() {
        s.nextLine();
        System.out.println("Enter Committee name");
        String committeeName = s.nextLine();
        System.out.println("Enter Degree of The committee");
        String degreeOfCommittee = s.nextLine().toUpperCase();
        System.out.println("Enter Chairman name");
        String chairMan = s.nextLine();
        while (true) {
            try {
                college.addCommittee(committeeName, chairMan,degreeOfCommittee);
                break;
            }
            catch (ExceptionNameTaken e) {
                System.out.println(e.getMessage());
                committeeName = s.nextLine();
            }


            catch (ExceptionUserMessage e) {
                System.out.println(e.getMessage());
                break;
            }

        }
    }

    private static void addLecturerToCommittee() {
        s.nextLine();
        System.out.println("Enter Lecturer Name");
        String lecturerName = s.nextLine();
        System.out.println("Enter Committee Name");
        String committeeName = s.nextLine();
        try {
            college.addLecturerToCommittee(lecturerName,committeeName);

        } catch (ExceptionUserMessage e) {
            System.out.println(e.getMessage());
        }

    }

    private static void addStudyDepartment() {
        s.nextLine();
        System.out.println("Enter Department Name");
        String departmentName = s.nextLine();
        System.out.println("Enter Num of Students in the department");
        int numOfStudents = s.nextInt();
        try {
            college.addStudyDepartment(departmentName,numOfStudents);

        } catch (ExceptionUserMessage e) {
            System.out.println(e.getMessage());
        }


    }

    // CHANGING FUNCTIONS:
    private static void updateCommitteeChair() {
        s.nextLine();
        System.out.println("Enter Chairman Name");
        String chairman = s.nextLine();
        System.out.println("Enter Committee Name");
        String committee = s.nextLine();
        try {
            college.updateCommitteeChair(chairman,committee);

        } catch (ExceptionUserMessage e) {
            System.out.println(e.getMessage());
        }


    }

    private static void removeLecturerFromCommittee() {
        s.nextLine();
        System.out.println("Enter Lecturer Name");
        String lecturerName = s.nextLine();
        System.out.println("Enter Committee Name");
        String committeeName = s.nextLine();
        try{
            college.removeLecturerFromCommittee(lecturerName,committeeName);
        } catch (ExceptionUserMessage e) {
            System.out.println(e.getMessage());
        }


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

    private static void CompareArticles() {
        s.nextLine();
        System.out.println("Enter Doctor/Professor name");
        String name1 = s.nextLine();
        System.out.println("Enter Second Doctor/Professor name");
        String name2 = s.nextLine();
        try {
            String res = college.CompareArticles(name1, name2);
            System.out.println(res);
        } catch (ExceptionUserMessage e) {
            System.out.println(e.getMessage());
        };
    }

    private static void compareCommittee() {
        s.nextLine();
        System.out.println("Enter first committee ");
        String name1= s.nextLine();
        System.out.println("Enter second committee ");
        String name2= s.nextLine();

        int res;
        do {
                System.out.println("To compare between members press 1 for articles press 2 ");
                res = s.nextInt();
        } while (res != 1 && res != 2);

        try {
            String res2 = college.compareCommittee(name1,name2 ,res);
            System.out.println(res2);


        }
        catch(ExceptionUserMessage e) {
            System.out.println(e.getMessage());
        }
    }

    private static void DuplicateCommittee() {
        s.nextLine();
        System.out.println("Enter Committee name to duplicate");
        String name = s.nextLine();

        try{
            college.DuplicateCommittee(name);

        } catch (ExceptionUserMessage e) {
            System.out.println(e.getMessage());
        }


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


