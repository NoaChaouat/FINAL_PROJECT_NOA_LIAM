package liam_zimmerman_noa_chaouat;

public enum UserMessage {
    //הוספת מרצה
//    ADD_LECTURER_SUCCESS("Lecturer Added Successfully"),
//    ADD_LECTURER_FAILED("Lecturer Name Taken. please Enter Different Name"),
    SALARY_CANT_BE_NEGATIVE("Salary can not be negative"),

    //הוספת ועדה#
//    ADD_COMMITTEE_SUCCESS("Committee Added successfully "),
    ADD_COMMITTEE_FAILED_CHAIRMAN("Chairman Must Be DR Or Professor"),
//    ADD_COMMITTEE_FAILED_CHAIRMAN_NOT_EXIST("Chairman is not Lecturer in this college"),
//    ADD_COMMITTEE_FAILED_EXIST("Committee Name Taken Choose Another Name"),

    //הוספת מרצה לועדה
//    ADD_LECTURER_TO_COMMITTEE_SUCCESS("Lecturer added to committee successfully."),
//    ADD_LECTURER_TO_COMMITTEE_FAIL_ALREADY_IN_COMMITTEE("Lecturer is already part of the committee."),
//    ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_LECTURER("Lecturer not found in the college."),
//    ADD_LECTURER_TO_COMMITTEE_FAIL_NO_SUCH_COMMITTEE("Committee does not exist."),

    // הוספת תואר למרצה
    DEGREE_ISNT_VALID("Degree do not exist."),

    //עדכון יושב ראש
//    UPDATE_COMMITTEE_CHAIR_FAIL_NO_SUCH_LECTURER("Lecturer not found in the college."),
//    UPDATE_COMMITTEE_CHAIR_FAIL_NO_SUCH_COMMITTEE("Committee does not exist."),
//    UPDATE_COMMITTEE_CHAIR_SUCCESS("Committee chairman updated successfully."),
//    UPDATE_COMMITTEE_CHAIR_FAIL_ALREADY_CHAIRMAN("Lecturer already chairman in this committee"),
    UPDATE_COMMITTEE_CHAIR_FAIL("Failed to update committee chairman, Committee chairman must be DR or Professor"),
//    UPDATE_COMMITTEE_CHAIR_FAIL_NOT_IN_COMMITTEE("The lecturer not found in the committee."),

    //הסרת מרצה מועדה
//    REMOVE_LECTURER_FROM_COMMITTEE_FAIL_NO_SUCH_LECTURER("Lecturer not found in the college."),
//    REMOVE_LECTURER_FROM_COMMITTEE_FAIL_NO_SUCH_COMMITTEE("Committee does not exist."),
//    REMOVE_LECTURER_FROM_COMMITTEE_SUCCESS("Member removed from committee successfully."),
//    REMOVE_LECTURER_FROM_COMMITTEE_FAIL_NO_SUCH_LECTURER_IN_COMMITTEE("Committee does not exist."),
    REMOVE_LECTURER_CHAIRMAN_CANNOT_BE_REMOVED("Chairman Cannot Be Removed From Committee"),


    //הוספת מחלקת לימוד
//    ADD_DEPARTMENT_SUCCESS("Study department added successfully."),
//    ADD_DEPARTMENT_FAIL_NAME_EXISTS("Department name already exists. Please enter different name."),
//    ADD_LECTURER_TO_DEPARTMENT_FAIL_ALREADY_IN_DEPARTMENT("Lecturer is already exist in the department."),
//    ADD_LECTURER_TO_DEPARTMENT_SUCCESS("Lecturer added to Department successfully."),
//    DEPARTMENT_DOESNT_EXIST("Department does not exist"),
    LECTURER_IS_ALREADY_BELONG_TO_OTHER_DEPARTMENT("Lecturer is already in another department.");


    private final String message;
    UserMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }


}






