package org.llyriq;

public class YearData {
    int month;
    int amount;
    boolean is_expense;

    public YearData(int month, int amount, boolean is_expense) {
        this.month = month;
        this.amount = amount;
        this.is_expense = is_expense;
    }
}
