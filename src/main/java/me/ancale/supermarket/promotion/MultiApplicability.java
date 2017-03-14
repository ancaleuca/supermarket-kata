package me.ancale.supermarket.promotion;

public class MultiApplicability implements Applicability {

    private final int atLeastCount;

    public MultiApplicability(int atLeastCount) {
        this.atLeastCount = atLeastCount;
    }

    @Override
    public int applicabilitySetSize() {
        return atLeastCount;
    }
}
