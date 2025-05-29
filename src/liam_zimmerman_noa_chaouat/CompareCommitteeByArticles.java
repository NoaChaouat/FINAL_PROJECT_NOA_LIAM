package liam_zimmerman_noa_chaouat;

import java.util.Comparator;

public class CompareCommitteeByArticles implements Comparator<Committee> {
    @Override
    public int compare(Committee o1, Committee o2) {
        return Integer.compare(Util.sumOfArticles(o1) ,Util.sumOfArticles(o2));
    }
}
