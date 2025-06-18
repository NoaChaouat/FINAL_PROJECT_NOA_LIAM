package liam_zimmerman_noa_chaouat;

import java.util.ArrayList;
import java.util.Arrays;

public class Department {
    private final String departmentName;
    private int numOfStudents;
    private ArrayList<Lecturer> listOfLecturerDepartment;

    public Department(String departmentName, int numOfStudents) {
        this.departmentName = departmentName;
        this.numOfStudents = numOfStudents;
        this.listOfLecturerDepartment = new ArrayList<>();
    }

    // GET FUNCTIONS:

    public String getDepartmentName() {
        return departmentName;
    }

    public ArrayList<Lecturer> getListOfLecturerDepartment() {
        return listOfLecturerDepartment;
    }

    public double getAverageDepartments() {
        double average=0;
        for (int i = 0; i < listOfLecturerDepartment.size(); i++) {
            average+= listOfLecturerDepartment.get(i).getSalary();
        }
        return average/listOfLecturerDepartment.size();
    }

    // SET FUNCTIONS:
    public void setListOfLecturerDepartment(ArrayList<Lecturer> listOfLecturerDepartment) {
        this.listOfLecturerDepartment = listOfLecturerDepartment;
    }

    // TO STRING FUNCTION:
    @Override
    public String toString() {
        return "Department{" +
                "department Name: '" + departmentName + '\'' +
                ", num Of Students: " + numOfStudents +
                ", list Of Lecturer In Department: " + listOfLecturerDepartment +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Department other)) return false;

        if (this.departmentName == null) {
            return other.departmentName == null;
        }

        return this.departmentName.equals(other.departmentName);
    }


}
