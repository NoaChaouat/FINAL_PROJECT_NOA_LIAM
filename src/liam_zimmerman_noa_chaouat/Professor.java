package liam_zimmerman_noa_chaouat;

import java.util.ArrayList;

public class Professor extends Doctor {

    private final String grantorProfessora;


    public Professor(String name, String id, String lecturerDegree, String fieldOfStudy, double salary, String grantorProfessora, ArrayList<String> articles) {
        super(name, id, lecturerDegree, fieldOfStudy, salary, articles);
        this.grantorProfessora =grantorProfessora;
        
    }

    public Professor(Doctor doctor, String grantorProfessora) {
        super(
                doctor.getLecturerName(),
                doctor.getId(),
                String.valueOf(doctor.getDegree()),
                doctor.getFieldOfStudy(),
                doctor.getSalary(),
                doctor.getArticles()
        );
        this.grantorProfessora = grantorProfessora;
    }
    //EQUALS ---- USE EQUALS OF SUPER (DOCTOR)

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("The institution that awarded the professorship: " + grantorProfessora);
        return sb.toString();
    }
}
