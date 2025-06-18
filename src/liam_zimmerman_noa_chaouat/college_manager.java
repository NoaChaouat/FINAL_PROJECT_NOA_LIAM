package liam_zimmerman_noa_chaouat;


import java.util.ArrayList;
import java.util.Arrays;

import static liam_zimmerman_noa_chaouat.UserMessage.*;

public class college_manager {
    private ArrayList<Lecturer> lecturersArray = new ArrayList<>();
    private ArrayList<Committee> committeesArray = new ArrayList<>();
    private ArrayList<Department> departmentsArray = new ArrayList<>();

    // ADDING FUNCTIONS:
    public void addLecturer(String name, String id, String degree, String fieldOfStudy, double salary) throws ExceptionUserMessage {
        Lecturer lecturer = addBasicLecturer(name, id, degree, fieldOfStudy, salary);
        lecturersArray.add(lecturer);
    }

    public void addLecturer(String name, String id, String degree, String fieldOfStudy, double salary, ArrayList<String> articles) throws ExceptionUserMessage {
        Lecturer lecturer = addBasicLecturer(name, id, degree, fieldOfStudy, salary);
        Doctor doctor = new Doctor(lecturer, articles);  // בונה Doctor על בסיס Lecturer קיים
        lecturersArray.add(doctor);
    }

    public void addLecturer(String name, String id, String degree, String fieldOfStudy, double salary, ArrayList<String> articles, String GRANTOR_PROFESSORA) throws ExceptionUserMessage {
        Lecturer lecturer = addBasicLecturer(name, id, degree, fieldOfStudy, salary);
        Doctor doctor = new Doctor(lecturer, articles);  // בונה Doctor על בסיס Lecturer קיים
        Professor professor = new Professor(doctor, GRANTOR_PROFESSORA);  // בונה Professor על בסיס Doctor
        lecturersArray.add(professor);
    }

    public Lecturer addBasicLecturer(String name, String id, String degree, String fieldOfStudy, double salary) throws ExceptionUserMessage {

        if (Util.isExistLecturer(lecturersArray, name)) {
            throw new ExceptionNameTaken(ADD_LECTURER_FAILED);
        }

        if (salary <= 0) {
            throw new ExceptionCollege(SALARY_CANT_BE_NEGATIVE);
        }


        Lecturer lecturer = new Lecturer(name, id, degree, fieldOfStudy, salary);

        return lecturer;
    }

    public void addCommittee(String committeeName, String chairMan, String degreeOfCommittee) throws ExceptionUserMessage {
        Lecturer lecturerChair = Util.findLecturerByName(lecturersArray, chairMan);
        if (lecturerChair == null) {
            throw new ExceptionsNotExist(ADD_COMMITTEE_FAILED_CHAIRMAN_NOT_EXIST);
        }
        if (!Util.isValidDegree(degreeOfCommittee)) {
            throw new ExceptionsNotExist(DEGREE_ISNT_VALID);
        }

        if (!Util.isValidChairman(lecturerChair)) { // בודק אם הוא מופע של דוקטור או פרופסור
            throw new ExceptionCollege(ADD_COMMITTEE_FAILED_CHAIRMAN);
        }
        if (Util.isExistCommittee(committeesArray, committeeName)) {
            throw new ExceptionNameTaken(ADD_COMMITTEE_FAILED_EXIST);
        }

        Committee committee = new Committee(committeeName, lecturerChair,degreeOfCommittee);

        committeesArray.add(committee);
    }

    public void addLecturerToCommittee(String lecturerName, String committeeName) throws ExceptionUserMessage {
        Lecturer lecturer = Util.findLecturerByName(lecturersArray, lecturerName);
        Committee committee = Util.findCommitteeByName(committeesArray, committeeName);

        if (lecturer == null) {
            throw new ExceptionsNotExist(ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_LECTURER);
        }
        if (committee == null) {
            throw new ExceptionsNotExist(ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_COMMITTEE);
        }
        if (Util.isExistLecturer(committee.getListOfLecturerCommittee(), lecturerName) || committee.getCommitteeChair().equals(lecturer)) {
            throw new ExceptionAlreadyExist(ADD_LECTURER_TO_COMMITTEE_FAIL_ALREADY_IN_COMMITTEE);
        }
        if (!(lecturer.getDegree() == committee.getDegree())){
            throw new ExceptionCollege(ADD_LECTURER_TO_COMMITTEE_FAIL_DEGREE_NOT_EQUAL);
        }
        committee.getListOfLecturerCommittee().add(lecturer);
        lecturer.getCommitteesOfLecturer().add(committee);

    }

    public void addStudyDepartment(String departmentName, int numOfStudents) throws ExceptionUserMessage {
        if (Util.isExistDepartment(departmentsArray, departmentName)) {
            throw new ExceptionNameTaken(ADD_DEPARTMENT_FAIL_NAME_EXISTS);
        }

        Department department = new Department(departmentName, numOfStudents);

        departmentsArray.add(department);

    }

    public void addLecturerToDepartment(String lecturerName, String departmentName) throws ExceptionUserMessage {
        Lecturer lecturer = Util.findLecturerByName(lecturersArray, lecturerName);
        Department department = Util.findDepartmentByName(departmentsArray, departmentName);

        if (lecturer == null) {
            throw new ExceptionsNotExist(ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_LECTURER);
        }

        if (department == null) {
            throw new ExceptionsNotExist(DEPARTMENT_DOESNT_EXIST);
        }

        if (Util.isExistLecturer(department.getListOfLecturerDepartment(), lecturerName)) {
            throw new ExceptionAlreadyExist(ADD_LECTURER_TO_DEPARTMENT_FAIL_ALREADY_IN_DEPARTMENT);
        }

        if (lecturer.getDepartmentOfLecturer() != null) {
            throw new ExceptionAlreadyExist(LECTURER_IS_ALREADY_BELONG_TO_OTHER_DEPARTMENT);
        }


        department.getListOfLecturerDepartment().add(lecturer);
        lecturer.setDepartmentOfLecturer(department);

    }

    // CHANGING FUNCTIONS:
    public void updateCommitteeChair(String chairman, String committeeName) throws ExceptionUserMessage {
        Lecturer lecturer = Util.findLecturerByName(lecturersArray, chairman);
        Committee committee = Util.findCommitteeByName(committeesArray, committeeName);
        if (lecturer == null) {
            throw new ExceptionsNotExist(UPDATE_COMMITTEE_CHAIR_FAIL_NO_SUCH_LECTURER);
        }
        if (committee == null) {
            throw new ExceptionsNotExist(UPDATE_COMMITTEE_CHAIR_FAIL_NO_SUCH_COMMITTEE);
        }
        if (!Util.isValidChairman(lecturer)) {
            throw new ExceptionCollege(UPDATE_COMMITTEE_CHAIR_FAIL);
        }
        if (committee.getCommitteeChair().equals(lecturer)) {
            throw new ExceptionAlreadyExist(UPDATE_COMMITTEE_CHAIR_FAIL_ALREADY_CHAIRMAN);
        }
        if (!Util.isExistLecturer(committee.getListOfLecturerCommittee(), chairman)) {
            throw new ExceptionsNotExist(UPDATE_COMMITTEE_CHAIR_FAIL_NOT_IN_COMMITTEE);
        }
        committee.setCommitteeChair(lecturer);
        committee.getListOfLecturerCommittee().remove(lecturer);
    }

    public void removeLecturerFromCommittee(String lecturerName, String committeeName) throws ExceptionUserMessage {
        Lecturer lecturer = Util.findLecturerByName(lecturersArray, lecturerName);
        Committee committee = Util.findCommitteeByName(committeesArray, committeeName);
        if (lecturer == null) {
            throw new ExceptionsNotExist(REMOVE_LECTURER_FROM_COMMITTEE_FAIL_NO_SUCH_LECTURER);
        }
        if (committee == null) {
            throw new ExceptionsNotExist(REMOVE_LECTURER_FROM_COMMITTEE_FAIL_NO_SUCH_COMMITTEE);
        }
        if (!Util.isExistLecturer(committee.getListOfLecturerCommittee(), lecturerName)) {
            throw new ExceptionsNotExist(REMOVE_LECTURER_FROM_COMMITTEE_FAIL_NO_SUCH_LECTURER_IN_COMMITTEE);
        }
        if (committee.getCommitteeChair().equals(lecturer)) {
            throw new ExceptionCollege(REMOVE_LECTURER_CHAIRMAN_CANNOT_BE_REMOVED);
        }

        committee.getListOfLecturerCommittee().remove(lecturer);
        lecturer.getCommitteesOfLecturer().remove(committee);

    }

    // CALCULATE FUNCTIONS:
    public double averageLecturerSalaryPerCollege() {
        double average = 0;
        for (int i = 0; i < lecturersArray.size(); i++) {
            average += lecturersArray.get(i).getSalary();
        }
        return average / lecturersArray.size();
    }

    public double averageLecturerSalaryPerDepartment(String departmentName) {
        double average = 0;
        Department department = Util.findDepartmentByName(departmentsArray, departmentName);
        if (department == null) {
            return -1;
        }
        average = department.getAverageDepartments();
        return average;
    }


    // SHOWING FUNCTIONS:
    public StringBuilder showCommittee() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < committeesArray.size(); i++) {
            sb.append(committeesArray.get(i).toString()).append("\n");
            sb.append("--------------------------------------------------\n"); // קו מפריד
        }
        return sb;
    }

    public StringBuilder showLecturers() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lecturersArray.size(); i++) {
            sb.append(lecturersArray.get(i).toString()).append("\n");
            sb.append("--------------------------------------------------\n"); // קו מפריד
        }
        return sb;
    }


    public String CompareArticles(String name1, String name2) throws ExceptionUserMessage{
        Lecturer doctor1 = Util.findLecturerByName(lecturersArray, name1);
        Lecturer doctor2 = Util.findLecturerByName(lecturersArray, name2);
        if(doctor1 == null){
            throw new ExceptionsNotExist(ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_LECTURER);
        }
        if(doctor2 == null){
            throw new ExceptionsNotExist(ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_LECTURER);
        }

        if(!Util.isValidChairman(doctor1))
        {
            throw new ExceptionCollege(COMPARE_FAILED_DR);
        }
        if(!Util.isValidChairman(doctor2))
        {
            throw new ExceptionCollege(COMPARE_FAILED_DR);
        }

        Doctor d1 = (Doctor) doctor1;
        Doctor d2 = (Doctor) doctor2;
        int res = d1.compareTo(d2);
        switch (res) {
            case -1 -> {
                return (doctor1.getClass().getSimpleName() + " " + doctor1.getLecturerName() + " has less articles than " + doctor2.getClass().getSimpleName() + " " + doctor2.getLecturerName());
            }
            case 0 -> {
                return (doctor1.getClass().getSimpleName() + " " + doctor1.getLecturerName() + " has equal num of articles as " + doctor2.getClass().getSimpleName() + " " + doctor2.getLecturerName());
            }
            case 1 -> {
                return (doctor1.getClass().getSimpleName() + " " + doctor1.getLecturerName() + " has grater articles than " + doctor2.getClass().getSimpleName() + " " + doctor2.getLecturerName());
            }
        }
        throw new ExceptionCollege(WRONG_INPUT);
    }

    public String compareCommittee(String name1, String name2, int res) throws ExceptionUserMessage{
        Committee committee1 = Util.findCommitteeByName(committeesArray, name1);
        Committee committee2 = Util.findCommitteeByName(committeesArray, name2);

        if (committee1 == null || committee2 == null) {
            throw new ExceptionsNotExist(ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_COMMITTEE);
        }


        if (res == 2){
            CompareCommitteeByArticles comparator = new CompareCommitteeByArticles();
            int result = comparator.compare(committee1, committee2);
            switch (result) {
                case -1 -> {
                    return (committee1.getClass().getSimpleName() + " " + committee1.getNameCommittee() + " has less articles than " + committee2.getClass().getSimpleName() + " " + committee2.getNameCommittee());
                }
                case 0 -> {
                    return (committee1.getClass().getSimpleName() + " " + committee1.getNameCommittee() + " has equal num of articles as " + committee2.getClass().getSimpleName() + " " + committee2.getNameCommittee());
                }
                case 1 -> {
                    return (committee1.getClass().getSimpleName() + " " + committee1.getNameCommittee() + " has greater articles than " + committee2.getClass().getSimpleName() + " " + committee2.getNameCommittee());
                }
            }
        }
        else {
            CompareCommitteeByMembers comparator = new CompareCommitteeByMembers();
            int result = comparator.compare(committee1, committee2);
            switch (result) {
                case -1 -> {
                    return (committee1.getClass().getSimpleName() + " " + committee1.getNameCommittee() + " has less num of members in committee than " + committee2.getClass().getSimpleName() + " " + committee2.getNameCommittee());
                }
                case 0 -> {
                    return (committee1.getClass().getSimpleName() + " " + committee1.getNameCommittee() + " has equal num of members in committee as " + committee2.getClass().getSimpleName() + " " + committee2.getNameCommittee());
                }
                case 1 -> {
                    return (committee1.getClass().getSimpleName() + " " + committee1.getNameCommittee() + " has greater members in committee than " + committee2.getClass().getSimpleName() + " " + committee2.getNameCommittee());
                }
            }

        }
        throw new ExceptionCollege(WRONG_INPUT);

    }

    public void DuplicateCommittee(String name) throws ExceptionUserMessage {
        Committee committee1 = Util.findCommitteeByName(committeesArray, name);
        if (committee1 == null) {
            throw new ExceptionsNotExist(ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_COMMITTEE);
        }
        Committee clonedCommittee = committee1.clone();
        committeesArray.add(clonedCommittee);
    }
}
