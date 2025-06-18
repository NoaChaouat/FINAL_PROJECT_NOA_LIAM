package liam_zimmerman_noa_chaouat;

import java.util.ArrayList;

public class Committee implements Cloneable{
    private String nameCommittee;
    private ArrayList<Lecturer> listOfLecturerCommittee;
    private Lecturer committeeChair;
    private Lecturer.Degree degree;

    // CONSTRUCTOR:
    public Committee(String nameCommittee, Lecturer committeeChair, String degreeOfCommittee) {
        this.nameCommittee = nameCommittee;
        this.committeeChair = committeeChair;
        this.degree= Lecturer.Degree.valueOf(degreeOfCommittee);
        this.listOfLecturerCommittee = new ArrayList<>();
    }

    // GET FUNCTIONS:
    public ArrayList<Lecturer> getListOfLecturerCommittee() {
        return listOfLecturerCommittee;
    }

    public Lecturer getCommitteeChair() {
        return committeeChair;
    }

    public String getNameCommittee() {
        return nameCommittee;
    }

    public Lecturer.Degree getDegree() {
        return degree;
    }

    // SET FUNCTIONS:
    public void setListOfLecturerCommittee(ArrayList<Lecturer> listOfLecturerCommittee) {
        this.listOfLecturerCommittee = listOfLecturerCommittee;
    }

    public void setCommitteeChair(Lecturer committeeChair) {
        if (committeeChair.getDegree() == Lecturer.Degree.DR || committeeChair.getDegree() == Lecturer.Degree.PROFESSOR) {
            this.committeeChair = committeeChair;
        }
    }




    // TO STRING FUNCTION:
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("committee Name: ").append(nameCommittee).append("\n");
        sb.append("committee Degree: ").append(degree).append("\n");
        sb.append("committee Chair: ").append(committeeChair != null ? committeeChair.getLecturerName() : "N/A").append("\n");
        sb.append("list Of Lecturer In Committee: ").append(Util.getLecturerNamesAsString(listOfLecturerCommittee)).append("\n");
        return sb.toString();
    }

    @Override
    public Committee clone()  {
        try {
            Committee cloned = (Committee) super.clone();
            cloned.nameCommittee = "New " + this.nameCommittee;
            cloned.listOfLecturerCommittee = new ArrayList<>();
            for (int i = 0; i < this.listOfLecturerCommittee.size(); i++) {
                cloned.listOfLecturerCommittee.set(i, this.listOfLecturerCommittee.get(i));
            }
            cloned.committeeChair = this.committeeChair;

            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (!(obj instanceof Committee other)) return false;

        if (this.nameCommittee == null) {
            return other.nameCommittee == null;
        }

        return this.nameCommittee.equals(other.nameCommittee);
    }
}
