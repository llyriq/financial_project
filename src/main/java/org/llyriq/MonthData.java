package org.llyriq;

public class MonthData {
    String item_name;
    boolean is_expense;
    int quantity;
    int price;

    public MonthData(String item_name, boolean is_expense, int quantity, int price) {
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.price = price;
    }
}
