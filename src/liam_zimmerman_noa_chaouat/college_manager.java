package liam_zimmerman_noa_chaouat;


public class college_manager {
        private Lecturer[] lecturersArray = new Lecturer[0];
        private int numOfLecturers = 0;
        private Committee[] committeesArray = new Committee[0];
        private int numOfCommittees = 0;
        private Department[] departmentsArray = new Department[0];
        private int numOfDepartments = 0;

    // ADDING FUNCTIONS:
    public UserMessage addLecturer(String name, String id, String degree, String fieldOfStudy,double salary) {

        if(Util.isExist(lecturersArray,numOfLecturers,name)){
            return UserMessage.ADD_LECTURER_FAILED;
        }

        if(salary<=0){
            return UserMessage.SALARY_CANT_BE_NEGATIVE;
        }

        if (lecturersArray.length == numOfLecturers){
            lecturersArray = (Lecturer[]) Util.resizeArr(lecturersArray);
        }

        Lecturer lecturer = new Lecturer(name, id, degree, fieldOfStudy,salary);

        lecturersArray[numOfLecturers++] = lecturer;

        return UserMessage.ADD_LECTURER_SUCCESS;

    }

    public UserMessage addCommittee(String committeeName, String chairMan) {
        Lecturer lecturerChair =Util.findLecturerByName(lecturersArray,numOfLecturers,chairMan);
        if(lecturerChair == null) {
            return UserMessage.ADD_COMMITTEE_FAILED_CHAIRMAN_NOT_EXIST;
        }

        if (!Util.isValidChairman(lecturerChair)){
            return UserMessage.ADD_COMMITTEE_FAILED_CHAIRMAN;

        }
        if(Util.isExist(committeesArray,numOfCommittees,committeeName)){
            return UserMessage.ADD_COMMITTEE_FAILED_EXIST;
        }

        Committee committee = new Committee(committeeName,lecturerChair);
        if (committeesArray.length == numOfCommittees){
            committeesArray = (Committee[]) Util.resizeArr(committeesArray);
        }
        committeesArray[numOfCommittees++] = committee;
        //Util.addCommitteeToLecturer(lecturerChair,committee); לזכור לברר אם הוא יושב ראש זה צריך להופיע ברשימת ועדות שלו
        return UserMessage.ADD_COMMITTEE_SUCCESS;


    }

    public UserMessage addLecturerToCommittee(String lecturerName, String committeeName) {
        Lecturer lecturer =Util.findLecturerByName(lecturersArray,numOfLecturers,lecturerName);
        Committee committee = Util.findCommitteeByName(committeesArray,numOfCommittees,committeeName);

        if(lecturer == null){
            return UserMessage.ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_LECTURER;
        }
        if(committee == null){
            return UserMessage.ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_COMMITTEE;
        }
        if(Util.isExist(committee.getListOfLecturerCommittee(),committee.getNumOfLecturerCommittee(),lecturerName) || committee.getCommitteeChair().equals(lecturer)){
            return UserMessage.ADD_LECTURER_TO_COMMITTEE_FAIL_ALREADY_IN_COMMITTEE;
        }

        if (committee.getListOfLecturerCommittee().length == committee.getNumOfLecturerCommittee()){
            committee.setListOfLecturerCommittee((Lecturer[]) Util.resizeArr(committee.getListOfLecturerCommittee()));
        }
        committee.getListOfLecturerCommittee()[committee.getNumOfLecturerCommittee()] = lecturer;
        committee.setNumOfLecturerCommittee(committee.getNumOfLecturerCommittee() + 1);

        Util.addCommitteeToLecturerCommittees(lecturer, committee);

        return UserMessage.ADD_LECTURER_TO_COMMITTEE_SUCCESS;

    }

    public UserMessage addStudyDepartment(String departmentName,int numOfStudents) {
        if(Util.isExist(departmentsArray, numOfDepartments,departmentName)){
            return UserMessage.ADD_DEPARTMENT_FAIL_NAME_EXISTS;
        }
        if (departmentsArray.length == numOfDepartments){
            departmentsArray = (Department[]) Util.resizeArr(departmentsArray);
        }
        Department department = new Department(departmentName,numOfStudents);

        departmentsArray[numOfDepartments++] = department;
        return UserMessage.ADD_DEPARTMENT_SUCCESS;

    }

    public UserMessage addLecturerToDepartment(String lecturerName, String departmentName) {
        Lecturer lecturer =Util.findLecturerByName(lecturersArray,numOfLecturers,lecturerName);
        Department department = Util.findDepartmentByName(departmentsArray,numOfDepartments,departmentName);

        if(lecturer == null){
            return UserMessage.ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_LECTURER;
        }

        if(department == null){
            return UserMessage.DEPARTMENT_DOESNT_EXIST;
        }

        if(Util.isExist(department.getListOfLecturerDepartment(),department.getNumOfLecturerDepartment(),lecturerName)){
            return UserMessage.ADD_LECTURER_TO_DEPARTMENT_FAIL_ALREADY_IN_DEPARTMENT;
        }

        if(lecturer.getDepartmentOfLecturer() != null){
            return UserMessage.LECTURER_IS_ALREADY_BELONG_TO_OTHER_DEPARTMENT;
        }

        if(department.getListOfLecturerDepartment().length == department.getNumOfLecturerDepartment()){
            department.setListOfLecturerDepartment((Lecturer[]) Util.resizeArr(department.getListOfLecturerDepartment()));
        }

        department.getListOfLecturerDepartment()[department.getNumOfLecturerDepartment()] = lecturer;

        department.setNumOfLecturerDepartment(department.getNumOfLecturerDepartment() + 1);

        lecturer.setDepartmentOfLecturer(department);

        return UserMessage.ADD_LECTURER_TO_DEPARTMENT_SUCCESS;

    }

    // CHANGING FUNCTIONS:
    public UserMessage updateCommitteeChair(String chairman, String committeeName) {
        Lecturer lecturer =Util.findLecturerByName(lecturersArray,numOfLecturers,chairman);
        Committee committee = Util.findCommitteeByName(committeesArray,numOfCommittees, committeeName);
        if(lecturer == null){
            return UserMessage.UPDATE_COMMITTEE_CHAIR_FAIL_NO_SUCH_LECTURER;
        }
        if(committee == null){
            return UserMessage.UPDATE_COMMITTEE_CHAIR_FAIL_NO_SUCH_COMMITTEE;
        }
        if (!Util.isValidChairman(lecturer)){
            return UserMessage.UPDATE_COMMITTEE_CHAIR_FAIL;
        }
        if(committee.getCommitteeChair().equals(lecturer)){
            return UserMessage.UPDATE_COMMITTEE_CHAIR_FAIL_ALREADY_CHAIRMAN;
        }
        if(!Util.isExist(committee.getListOfLecturerCommittee(),committee.getNumOfLecturerCommittee(),chairman)){
            return UserMessage.UPDATE_COMMITTEE_CHAIR_FAIL_NOT_IN_COMMITTEE;
        }
        committee.setCommitteeChair(lecturer);
        Util.removeFromArr(committee.getListOfLecturerCommittee(),committee.getNumOfLecturerCommittee(),lecturer);
        committee.setNumOfLecturerCommittee(committee.getNumOfLecturerCommittee() - 1);
        return UserMessage.UPDATE_COMMITTEE_CHAIR_SUCCESS;

    }

    public UserMessage removeLecturerFromCommittee(String lecturerName, String committeeName) {
        Lecturer lecturer =Util.findLecturerByName(lecturersArray,numOfLecturers,lecturerName);
        Committee committee = Util.findCommitteeByName(committeesArray,numOfCommittees, committeeName);
        if(lecturer == null){
            return UserMessage.REMOVE_LECTURER_FROM_COMMITTEE_FAIL_NO_SUCH_LECTURER;
        }
        if(committee == null){
            return UserMessage.REMOVE_LECTURER_FROM_COMMITTEE_FAIL_NO_SUCH_COMMITTEE;
        }
        if(!Util.isExist(committee.getListOfLecturerCommittee(),committee.getNumOfLecturerCommittee(),lecturerName)){
            return UserMessage.REMOVE_LECTURER_FROM_COMMITTEE_FAIL_NO_SUCH_LECTURER_IN_COMMITTEE;
        }
        if(committee.getCommitteeChair().equals(lecturer)){
            return UserMessage.REMOVE_LECTURER_CHAIRMAN_CANNOT_BE_REMOVED;
        }



        Util.removeFromArr(committee.getListOfLecturerCommittee(),committee.getNumOfLecturerCommittee(),lecturer);
        committee.setNumOfLecturerCommittee(committee.getNumOfLecturerCommittee() - 1);

        Util.removeFromArr(lecturer.getCommitteesOfLecturer(),lecturer.getNumOfCommitteesOfLecturer(),committee);
        return UserMessage.REMOVE_LECTURER_FROM_COMMITTEE_SUCCESS;




    }

    // CALCULATE FUNCTIONS:
    public double averageLecturerSalaryPerCollege() {
        double average=0;
        for (int i = 0; i < numOfLecturers; i++) {
            average+=lecturersArray[i].getSalary();
        }
        return average/numOfLecturers;
    }

    public double averageLecturerSalaryPerDepartment(String departmentName) {
        double average=0;
        Department department = Util.findDepartmentByName(departmentsArray,numOfDepartments,departmentName);
        if (department == null){
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


}
