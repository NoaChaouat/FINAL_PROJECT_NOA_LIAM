package liam_zimmerman_noa_chaouat;

import java.util.Arrays;

public class Lecturer {
    private final String lecturerName;

    private final String id;
    public Degree degree;
    public enum Degree{
        FIRST("First Degree"),
        SECOND("Second Degree"),
        DR("Doctor"),
        PROFESSOR("Professor");

        private String degree;

        Degree(String degree) {
             this.degree=degree;
        }
    }

    private final String fieldOfStudy;
    private double salary;
    private Department departmentOfLecturer = null;
    private Committee[] CommitteesOfLecturer;
    private int numOfCommitteesOfLecturer;

    //  CONSTRUCTOR:
    public Lecturer(String name, String id, String lecturerDegree, String fieldOfStudy,double salary) {

        this.lecturerName = name;
        this.id = id;
        this.degree= Degree.valueOf(lecturerDegree);
        this.fieldOfStudy = fieldOfStudy;
        this.salary = salary;
        this.CommitteesOfLecturer = new Committee[1];

    }

    //  GET FUNCTIONS:
    public String getLecturerName() {
        return lecturerName;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartmentOfLecturer() {
        return departmentOfLecturer;
    }

    public Degree getDegree() {
        return degree;
    }

    public Committee[] getCommitteesOfLecturer() {
        return CommitteesOfLecturer;
    }

    public int getNumOfCommitteesOfLecturer() {
        return numOfCommitteesOfLecturer;
    }

    //  SET FUNCTION:
    public void setCommitteesOfLecturer(Committee[] committeesOfLecturer) {
        CommitteesOfLecturer = committeesOfLecturer;
    }

    public void setNumOfCommitteesOfLecturer(int numOfCommitteesOfLecturer) {
        this.numOfCommitteesOfLecturer = numOfCommitteesOfLecturer;
    }

    public void setDepartmentOfLecturer(Department departmentOfLecturer) {
        this.departmentOfLecturer = departmentOfLecturer;
    }

    public String getId() {
        return id;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    //  OTHER:
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Lecturer lecturer) {
            return this.lecturerName.equals(lecturer.lecturerName); // השוואה לפי שם
        }
        return false;
    }

    // TO STRING:
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("lecturer Name: ").append(lecturerName).append("\n");
        sb.append("id: ").append(id).append("\n");
        sb.append("degree: ").append(degree).append("\n");
        sb.append("field Of Study: ").append(fieldOfStudy).append("\n");
        sb.append("salary: ").append(salary).append("\n");
        sb.append("department Of Lecturer: ").append(departmentOfLecturer != null ? departmentOfLecturer.getDepartmentName() : "None").append("\n");
        sb.append("committees: ").append(Util.getCommitteeNamesAsString(CommitteesOfLecturer, numOfCommitteesOfLecturer)).append("\n");
        return sb.toString();
    }

}