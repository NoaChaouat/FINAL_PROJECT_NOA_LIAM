package liam_zimmerman_noa_chaouat;


import static liam_zimmerman_noa_chaouat.UserMessage.*;

public class college_manager {
    private Lecturer[] lecturersArray = new Lecturer[0];
    private int numOfLecturers = 0;
    private Committee[] committeesArray = new Committee[0];
    private int numOfCommittees = 0;
    private Department[] departmentsArray = new Department[0];
    private int numOfDepartments = 0;


    // ADDING FUNCTIONS:
    public void addLecturer(String name, String id, String degree, String fieldOfStudy, double salary) throws ExceptionUserMessage {
        Lecturer lecturer = addBasicLecturer(name, id, degree, fieldOfStudy, salary);
        lecturersArray[numOfLecturers++] = lecturer;
    }

    public void addLecturer(String name, String id, String degree, String fieldOfStudy, double salary, String[] articles) throws ExceptionUserMessage {
        Lecturer lecturer = addBasicLecturer(name, id, degree, fieldOfStudy, salary);
        Doctor doctor = new Doctor(lecturer, articles);  // בונה Doctor על בסיס Lecturer קיים
        lecturersArray[numOfLecturers++] = doctor;
    }

    public void addLecturer(String name, String id, String degree, String fieldOfStudy, double salary, String[] articles, String GRANTOR_PROFESSORA) throws ExceptionUserMessage {
        Lecturer lecturer = addBasicLecturer(name, id, degree, fieldOfStudy, salary);
        Doctor doctor = new Doctor(lecturer, articles);  // בונה Doctor על בסיס Lecturer קיים
        Professor professor = new Professor(doctor, GRANTOR_PROFESSORA);  // בונה Professor על בסיס Doctor
        lecturersArray[numOfLecturers++] = professor;
    }

    public Lecturer addBasicLecturer(String name, String id, String degree, String fieldOfStudy, double salary) throws ExceptionUserMessage {

        if (Util.isExist(lecturersArray, numOfLecturers, name)) {
            throw new ExceptionNameTaken(ADD_LECTURER_FAILED);
        }

        if (salary <= 0) {
            throw new ExceptionCollege(SALARY_CANT_BE_NEGATIVE);
        }


        if (lecturersArray.length == numOfLecturers) {
            lecturersArray = (Lecturer[]) Util.resizeArr(lecturersArray);
        }

        Lecturer lecturer = new Lecturer(name, id, degree, fieldOfStudy, salary);

        return lecturer;
    }

    public void addCommittee(String committeeName, String chairMan) throws ExceptionUserMessage {
        Lecturer lecturerChair = Util.findLecturerByName(lecturersArray, numOfLecturers, chairMan);
        if (lecturerChair == null) {
            throw new ExceptionsNotExist(ADD_COMMITTEE_FAILED_CHAIRMAN_NOT_EXIST);
        }

        if (!Util.isValidChairman(lecturerChair)) { // בודק אם הוא מופע של דוקטור או פרופסור
            throw new ExceptionCollege(ADD_COMMITTEE_FAILED_CHAIRMAN);
        }
        if (Util.isExist(committeesArray, numOfCommittees, committeeName)) {
            throw new ExceptionNameTaken(ADD_COMMITTEE_FAILED_EXIST);
        }

        Committee committee = new Committee(committeeName, lecturerChair);
        if (committeesArray.length == numOfCommittees) {
            committeesArray = (Committee[]) Util.resizeArr(committeesArray);
        }
        committeesArray[numOfCommittees++] = committee;
    }

    public void addLecturerToCommittee(String lecturerName, String committeeName) throws ExceptionUserMessage {
        Lecturer lecturer = Util.findLecturerByName(lecturersArray, numOfLecturers, lecturerName);
        Committee committee = Util.findCommitteeByName(committeesArray, numOfCommittees, committeeName);

        if (lecturer == null) {
            throw new ExceptionsNotExist(ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_LECTURER);
        }
        if (committee == null) {
            throw new ExceptionsNotExist(ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_COMMITTEE);
        }
        if (Util.isExist(committee.getListOfLecturerCommittee(), committee.getNumOfLecturerCommittee(), lecturerName) || committee.getCommitteeChair().equals(lecturer)) {
            throw new ExceptionAlreadyExist(ADD_LECTURER_TO_COMMITTEE_FAIL_ALREADY_IN_COMMITTEE);
        }

        if (committee.getListOfLecturerCommittee().length == committee.getNumOfLecturerCommittee()) {
            committee.setListOfLecturerCommittee((Lecturer[]) Util.resizeArr(committee.getListOfLecturerCommittee()));
        }
        committee.getListOfLecturerCommittee()[committee.getNumOfLecturerCommittee()] = lecturer;
        committee.setNumOfLecturerCommittee(committee.getNumOfLecturerCommittee() + 1);

        Util.addCommitteeToLecturerCommittees(lecturer, committee);

    }

    public void addStudyDepartment(String departmentName, int numOfStudents) throws ExceptionUserMessage {
        if (Util.isExist(departmentsArray, numOfDepartments, departmentName)) {
            throw new ExceptionNameTaken(ADD_DEPARTMENT_FAIL_NAME_EXISTS);
        }
        if (departmentsArray.length == numOfDepartments) {
            departmentsArray = (Department[]) Util.resizeArr(departmentsArray);
        }
        Department department = new Department(departmentName, numOfStudents);

        departmentsArray[numOfDepartments++] = department;

    }

    public void addLecturerToDepartment(String lecturerName, String departmentName) throws ExceptionUserMessage {
        Lecturer lecturer = Util.findLecturerByName(lecturersArray, numOfLecturers, lecturerName);
        Department department = Util.findDepartmentByName(departmentsArray, numOfDepartments, departmentName);

        if (lecturer == null) {
            throw new ExceptionsNotExist(ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_LECTURER);
        }

        if (department == null) {
            throw new ExceptionsNotExist(DEPARTMENT_DOESNT_EXIST);
        }

        if (Util.isExist(department.getListOfLecturerDepartment(), department.getNumOfLecturerDepartment(), lecturerName)) {
            throw new ExceptionAlreadyExist(ADD_LECTURER_TO_DEPARTMENT_FAIL_ALREADY_IN_DEPARTMENT);
        }

        if (lecturer.getDepartmentOfLecturer() != null) {
            throw new ExceptionAlreadyExist(LECTURER_IS_ALREADY_BELONG_TO_OTHER_DEPARTMENT);
        }

        if (department.getListOfLecturerDepartment().length == department.getNumOfLecturerDepartment()) {
            department.setListOfLecturerDepartment((Lecturer[]) Util.resizeArr(department.getListOfLecturerDepartment()));
        }

        department.getListOfLecturerDepartment()[department.getNumOfLecturerDepartment()] = lecturer;

        department.setNumOfLecturerDepartment(department.getNumOfLecturerDepartment() + 1);

        lecturer.setDepartmentOfLecturer(department);

    }

    // CHANGING FUNCTIONS:
    public void updateCommitteeChair(String chairman, String committeeName) throws ExceptionUserMessage {
        Lecturer lecturer = Util.findLecturerByName(lecturersArray, numOfLecturers, chairman);
        Committee committee = Util.findCommitteeByName(committeesArray, numOfCommittees, committeeName);
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
        if (!Util.isExist(committee.getListOfLecturerCommittee(), committee.getNumOfLecturerCommittee(), chairman)) {
            throw new ExceptionsNotExist(UPDATE_COMMITTEE_CHAIR_FAIL_NOT_IN_COMMITTEE);
        }
        committee.setCommitteeChair(lecturer);
        Util.removeFromArr(committee.getListOfLecturerCommittee(), committee.getNumOfLecturerCommittee(), lecturer);
        committee.setNumOfLecturerCommittee(committee.getNumOfLecturerCommittee() - 1);
    }

    public void removeLecturerFromCommittee(String lecturerName, String committeeName) throws ExceptionUserMessage {
        Lecturer lecturer = Util.findLecturerByName(lecturersArray, numOfLecturers, lecturerName);
        Committee committee = Util.findCommitteeByName(committeesArray, numOfCommittees, committeeName);
        if (lecturer == null) {
            throw new ExceptionsNotExist(REMOVE_LECTURER_FROM_COMMITTEE_FAIL_NO_SUCH_LECTURER);
        }
        if (committee == null) {
            throw new ExceptionsNotExist(REMOVE_LECTURER_FROM_COMMITTEE_FAIL_NO_SUCH_COMMITTEE);
        }
        if (!Util.isExist(committee.getListOfLecturerCommittee(), committee.getNumOfLecturerCommittee(), lecturerName)) {
            throw new ExceptionsNotExist(REMOVE_LECTURER_FROM_COMMITTEE_FAIL_NO_SUCH_LECTURER_IN_COMMITTEE);
        }
        if (committee.getCommitteeChair().equals(lecturer)) {
            throw new ExceptionCollege(REMOVE_LECTURER_CHAIRMAN_CANNOT_BE_REMOVED);
        }


        Util.removeFromArr(committee.getListOfLecturerCommittee(), committee.getNumOfLecturerCommittee(), lecturer);
        committee.setNumOfLecturerCommittee(committee.getNumOfLecturerCommittee() - 1);

        Util.removeFromArr(lecturer.getCommitteesOfLecturer(), lecturer.getNumOfCommitteesOfLecturer(), committee);


    }

    // CALCULATE FUNCTIONS:
    public double averageLecturerSalaryPerCollege() {
        double average = 0;
        for (int i = 0; i < numOfLecturers; i++) {
            average += lecturersArray[i].getSalary();
        }
        return average / numOfLecturers;
    }

    public double averageLecturerSalaryPerDepartment(String departmentName) {
        double average = 0;
        Department department = Util.findDepartmentByName(departmentsArray, numOfDepartments, departmentName);
        if (department == null) {
            return -1;
        }
        average = department.getAverageDepartments();
        return average;
    }


    // SHOWING FUNCTIONS:
    public StringBuilder showCommittee() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numOfCommittees; i++) {
            sb.append(committeesArray[i].toString()).append("\n");
            sb.append("--------------------------------------------------\n"); // קו מפריד
        }
        return sb;
    }

    public StringBuilder showLecturers() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numOfLecturers; i++) {
            sb.append(lecturersArray[i].toString()).append("\n");
            sb.append("--------------------------------------------------\n"); // קו מפריד
        }
        return sb;
    }


    public String CompareArticles(String name1, String name2) throws ExceptionUserMessage{
        Lecturer doctor1 = Util.findLecturerByName(lecturersArray, numOfLecturers, name1);
        Lecturer doctor2 = Util.findLecturerByName(lecturersArray, numOfLecturers, name2);
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
        Committee committee1 = Util.findCommitteeByName(committeesArray, numOfCommittees, name1);
        Committee committee2 = Util.findCommitteeByName(committeesArray, numOfCommittees, name2);

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
        Committee committee1 = Util.findCommitteeByName(committeesArray, numOfCommittees, name);
        if (committee1 == null) {
            throw new ExceptionsNotExist(ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_COMMITTEE);
        }
        Committee clonedCommittee = committee1.clone();
        if (committeesArray.length == numOfCommittees) {
            committeesArray = (Committee[]) Util.resizeArr(committeesArray);
        }
        committeesArray[numOfCommittees++] = clonedCommittee;
    }
}
