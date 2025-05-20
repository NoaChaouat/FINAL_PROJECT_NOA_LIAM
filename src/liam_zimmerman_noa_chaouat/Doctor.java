package liam_zimmerman_noa_chaouat;

public class Doctor extends Lecturer{

    private String[] articles;


    public Doctor(String name, String id, String lecturerDegree, String fieldOfStudy, double salary, String[] articles ) {
        super(name, id, lecturerDegree, fieldOfStudy, salary);
        this.articles = new String[0];
    }

    public String[] getArticles() {
        return articles;
    }

    public void setArticles(String[] articles) {
        this.articles = articles;
    }
}
