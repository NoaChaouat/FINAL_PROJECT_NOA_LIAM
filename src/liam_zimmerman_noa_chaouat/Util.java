package liam_zimmerman_noa_chaouat;

import java.util.ArrayList;
import java.util.Arrays;

public class Util {
    // BOOLEAN IS FUNCTIONS:
    public static boolean isValidChairman(Lecturer lecturer) {
        return lecturer instanceof Doctor;
    }

    public static boolean isValidDegree(String degree){
        for(Lecturer.Degree d: Lecturer.Degree.values()){

            if(equalsDegree(d,degree)){
                return true;
            }

        }
        return false;
    }

    public static boolean isExistLecturer(ArrayList<Lecturer> arr, String name) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getLecturerName().equals(name)) {
                return true;
            }

        }
        return false;
    }

    public static boolean isExistCommittee(ArrayList<Committee> arr, String name) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getNameCommittee().equals(name)) {
                return true;
            }

        }
        return false;
    }

    public static boolean isExistDepartment(ArrayList<Department> arr, String name) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getDepartmentName().equals(name)) {
                return true;
            }

        }
        return false;
    }

    public static boolean equalsDegree(Lecturer.Degree d, String degree) {
        return (d.name().equals(degree));
    }

    // FINDING FUNCTIONS:
    public static Lecturer findLecturerByName(ArrayList<Lecturer> arr, String name) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getLecturerName().equals(name)) {
                return arr.get(i);
            }
        }
        return null;
    }

    public static Committee findCommitteeByName(ArrayList<Committee> committees, String committeeName) {
        for (int i = 0; i < committees.size(); i++) {
            if (committees.get(i).getNameCommittee().equals(committeeName)) {
                return committees.get(i);
            }
        }
        return null;
    }

    public static Department findDepartmentByName(ArrayList<Department> departmentsArray, String departmentName) {
        for (int i = 0; i < departmentsArray.size(); i++) {
            if (departmentsArray.get(i).getDepartmentName().equals(departmentName)) {
                return departmentsArray.get(i);
            }
        }
        return null;
    }


    // GET FUNCTIONS
    public static String getCommitteeNamesAsString(ArrayList<Committee> committees) {
        if (committees == null || committees.isEmpty()) {
            return "None";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < committees.size(); i++) {
            Committee committee = committees.get(i);
            if (committee != null) {
                sb.append(committee.getNameCommittee());
                if (i < committees.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }

    public static String getLecturerNamesAsString(ArrayList<Lecturer> lecturers) {
        if (lecturers == null || lecturers.isEmpty()) {
            return "None";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lecturers.size(); i++) {
            Lecturer lecturer = lecturers.get(i);
            if (lecturer != null) {
                sb.append(lecturer.getLecturerName());
                if (i < lecturers.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }

    public static int sumOfArticles(Committee c1){
        int counter = 0;
        ArrayList<Lecturer> arr = c1.getListOfLecturerCommittee();
        for (int i = 0; i < arr.size(); i++) {

            if(isValidChairman(arr.get(i))) {
                Doctor d1 = (Doctor) arr.get(i);
                counter += d1.getArticles().size();
            }

        }
        return counter;
    }


    public static String getArticelsAsString(ArrayList<String> articles) {
        if (articles == null || articles.isEmpty()) {
            return "None";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < articles.size(); i++) {
            sb.append(articles.get(i));
            if(i != articles.size()-1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
