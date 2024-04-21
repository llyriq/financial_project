package org.llyriq;

import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    HashMap<Integer, ArrayList<MonthData>> data = new HashMap<>();


    public MonthlyReport() {
        for(int i = 0; i < 12; i++){
            data.put(i+1, new ArrayList<>());
        }
    }

    public void addMonth(int month, ArrayList<MonthData> monthData){
        data.replace(month, monthData);
    }
}
