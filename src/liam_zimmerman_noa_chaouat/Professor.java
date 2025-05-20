package liam_zimmerman_noa_chaouat;

public class Professor extends Doctor {

    private final String GRANTOR_PROFESSORA;

    public Professor(String name, String id, String lecturerDegree, String fieldOfStudy, double salary, String grantorProfessora, String[] articles) {
        super(name, id, lecturerDegree, fieldOfStudy, salary, articles);
        this.GRANTOR_PROFESSORA =grantorProfessora;
        
    }
    public String getGRANTOR_PROFESSORA() {
        return GRANTOR_PROFESSORA;
    }

    
}
