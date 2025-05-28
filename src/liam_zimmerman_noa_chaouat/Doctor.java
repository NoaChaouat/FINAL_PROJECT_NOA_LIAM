package liam_zimmerman_noa_chaouat;

public class Doctor extends Lecturer{

    private String[] articles;
    private int numOfArticles;


    public Doctor(String name, String id, String lecturerDegree, String fieldOfStudy, double salary, String[] articles ) {
        super(name, id, lecturerDegree, fieldOfStudy, salary);
        this.articles = articles;
    }

    public Doctor(Lecturer lecturer, String[] articles) {
        super(
                lecturer.getLecturerName(),
                lecturer.getId(),
                String.valueOf(lecturer.getDegree()),
                lecturer.getFieldOfStudy(),
                lecturer.getSalary()
        );
        this.articles = articles;}

    public String[] getArticles() {
        return articles;
    }

    public void setArticles(String[] articles) {
        this.articles = articles;
    }
}
