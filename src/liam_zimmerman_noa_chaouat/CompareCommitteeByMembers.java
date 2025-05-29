package liam_zimmerman_noa_chaouat;

import java.util.Comparator;

public class CompareCommitteeByMembers implements Comparator<Committee> {
    @Override
    public int compare(Committee o1, Committee o2) {
        return Integer.compare(o1.getNumOfLecturerCommittee(), o2.getNumOfLecturerCommittee());
    }
}
