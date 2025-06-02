package liam_zimmerman_noa_chaouat;

import java.util.Arrays;

public class Doctor extends Lecturer implements Comparable<Doctor>{

    private String[] articles;


    private final int numOfArticles;

    public Doctor(String name, String id, String lecturerDegree, String fieldOfStudy, double salary, String[] articles ) {
        super(name, id, lecturerDegree, fieldOfStudy, salary);
        this.articles = articles;
        this.numOfArticles = articles.length;
    }

    public Doctor(Lecturer lecturer, String[] articles) {
        super(
                lecturer.getLecturerName(),
                lecturer.getId(),
                String.valueOf(lecturer.getDegree()),
                lecturer.getFieldOfStudy(),
                lecturer.getSalary()
        );
        this.articles = articles;
        this.numOfArticles = articles.length;
    }


    public String[] getArticles() {
        return articles;
    }

    public int getNumOfArticles() {
        return numOfArticles;
    }


    public void setArticles(String[] articles) {
        this.articles = articles;
    }

    @Override
    public int compareTo(Doctor o) {
        return Integer.compare(this.numOfArticles,o.getNumOfArticles());
    }

    //EQUALS ---- USE EQUALS OF SUPER(LECTURER)


    //TO STRING
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("list Of Articles: ").append(Util.getArticelsAsString(articles)).append("\n");

        return sb.toString();
    }
}
