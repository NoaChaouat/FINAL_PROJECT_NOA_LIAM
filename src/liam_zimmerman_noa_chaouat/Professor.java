package liam_zimmerman_noa_chaouat;

public class Professor extends Doctor {

    private final String grantorProfessora;


    public Professor(String name, String id, String lecturerDegree, String fieldOfStudy, double salary, String grantorProfessora, String[] articles) {
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


    public String getGrantorProfessora() {
        return grantorProfessora;
    }

    
}
