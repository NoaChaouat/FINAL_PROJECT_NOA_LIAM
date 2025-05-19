package liam_zimmerman_noa_chaouat;

import java.util.Arrays;

public class Department {
    private final String departmentName;
    private int numOfStudents;
    private Lecturer[] listOfLecturerDepartment;
    private int numOfLecturerDepartment = 0;

    public Department(String departmentName, int numOfStudents) {
        this.departmentName = departmentName;
        this.numOfStudents = numOfStudents;
        this.listOfLecturerDepartment = new Lecturer[0];
    }

    // GET FUNCTIONS:
    public int getNumOfLecturerDepartment() {
        return numOfLecturerDepartment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Lecturer[] getListOfLecturerDepartment() {
        return listOfLecturerDepartment;
    }

    public double getAverageDepartments() {
        double average=0;
        for (int i = 0; i < numOfLecturerDepartment; i++) {
            average+=listOfLecturerDepartment[i].getSalary();
        }
        return average/numOfLecturerDepartment;
    }

    // SET FUNCTIONS:
    public void setNumOfLecturerDepartment(int numOfLecturerDepartment) {
        this.numOfLecturerDepartment = numOfLecturerDepartment;
    }

    public void setListOfLecturerDepartment(Lecturer[] listOfLecturerDepartment) {
        this.listOfLecturerDepartment = listOfLecturerDepartment;
    }

    // TO STRING FUNCTIONS:
    @Override
    public String toString() {
        return "Department{" +
                "department Name: '" + departmentName + '\'' +
                ", num Of Students: " + numOfStudents +
                ", list Of Lecturer In Department: " + Arrays.toString(listOfLecturerDepartment) +
                '}';
    }


}
