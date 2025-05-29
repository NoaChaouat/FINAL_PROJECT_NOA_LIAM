package liam_zimmerman_noa_chaouat;

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

    public static boolean isExist(Lecturer[] arr, int numOf, String name) {
        for (int i = 0; i < numOf; i++) {
            if (arr[i].getLecturerName().equals(name)) {
                return true;
            }

        }
        return false;
    }

    public static boolean isExist(Committee[] arr, int numOf, String name) {
        for (int i = 0; i < numOf; i++) {
            if (arr[i].getNameCommittee().equals(name)) {
                return true;
            }

        }
        return false;
    }

    public static boolean isExist(Department[] arr, int numOf, String name) {
        for (int i = 0; i < numOf; i++) {
            if (arr[i].getDepartmentName().equals(name)) {
                return true;
            }

        }
        return false;
    }

    public static boolean equalsDegree(Lecturer.Degree d, String degree) {
        return (d.name().equals(degree));
    }

    // FINDING FUNCTIONS:
    public static Lecturer findLecturerByName(Lecturer[] arr, int numOf, String name) {
        for (int i = 0; i < numOf; i++) {
            if (arr[i].getLecturerName().equals(name)) {
                return arr[i];
            }
        }
        return null;
    }

    public static Committee findCommitteeByName(Committee[] committees, int numOfCommittees, String committeeName) {
        for (int i = 0; i < numOfCommittees; i++) {
            if (committees[i].getNameCommittee().equals(committeeName)) {
                return committees[i];
            }
        }
        return null;
    }

    public static Department findDepartmentByName(Department[] departmentsArray, int numOfDepartments, String departmentName) {
        for (int i = 0; i < numOfDepartments; i++) {
            if (departmentsArray[i].getDepartmentName().equals(departmentName)) {
                return departmentsArray[i];
            }
        }
        return null;
    }

    // ADD AND REMOVE FUNCTIONS:
    public static void addCommitteeToLecturerCommittees(Lecturer lecturer, Committee committee) {
        if (lecturer.getNumOfCommitteesOfLecturer() == lecturer.getCommitteesOfLecturer().length) {
            lecturer.setCommitteesOfLecturer((Committee[]) Util.resizeArr(lecturer.getCommitteesOfLecturer()));
        }

        lecturer.getCommitteesOfLecturer()[lecturer.getNumOfCommitteesOfLecturer()] = committee;
        lecturer.setNumOfCommitteesOfLecturer(lecturer.getNumOfCommitteesOfLecturer() + 1);
    }

    public static void removeFromArr(Object[] arr, int numOf, Object itemToRemove) {
        for (int i = 0; i < numOf; i++) {
            if (arr[i].equals(itemToRemove)) {
                for (int j = i; j < numOf - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[numOf - 1] = null;
            }
        }
    }

    public static Object[] resizeArr(Object[] arr) {

        return Arrays.copyOf(arr, arr.length == 0 ? 2 : arr.length * 2);
    }

    // GET FUNCTIONS
    public static String getCommitteeNamesAsString(Committee[] committees, int numOfCommittees) {
        if (committees == null || numOfCommittees == 0) {
            return "None";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numOfCommittees; i++) {
            Committee committee = committees[i];
            if (committee != null) {
                sb.append(committee.getNameCommittee());
                if (i < numOfCommittees - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }

    public static String getLecturerNamesAsString(Lecturer[] lecturers, int numOfLecturers) {
        if (lecturers == null || numOfLecturers == 0) {
            return "None";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numOfLecturers; i++) {
            Lecturer lecturer = lecturers[i];
            if (lecturer != null) {
                sb.append(lecturer.getLecturerName());
                if (i < numOfLecturers - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }

    public static int sumOfArticles(Committee c1){
        int counter = 0;
        Lecturer[] arr = c1.getListOfLecturerCommittee();
        for (int i = 0; i < c1.getNumOfLecturerCommittee(); i++) {

            if(isValidChairman(arr[i])) {
                Doctor d1 = (Doctor) arr[i];
                counter += d1.getNumOfArticles();
            }

        }
        return counter;
    }


}
