package liam_zimmerman_noa_chaouat;

import java.util.ArrayList;
import java.util.Arrays;

public class Doctor extends Lecturer implements Comparable<Doctor>{

    private ArrayList<String> articles;

    public Doctor(String name, String id, String lecturerDegree, String fieldOfStudy, double salary, ArrayList<String> articles ) {
        super(name, id, lecturerDegree, fieldOfStudy, salary);
        this.articles = articles;
    }

    public Doctor(Lecturer lecturer, ArrayList<String> articles) {
        super(
                lecturer.getLecturerName(),
                lecturer.getId(),
                String.valueOf(lecturer.getDegree()),
                lecturer.getFieldOfStudy(),
                lecturer.getSalary()
        );
        this.articles = articles;
    }


    public ArrayList<String> getArticles() {
        return articles;
    }


    public void setArticles(ArrayList<String> articles) {
        this.articles = articles;
    }

    @Override
    public int compareTo(Doctor o) {
        return Integer.compare(this.articles.size(),o.getArticles().size());
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
