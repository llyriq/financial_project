package org.llyriq;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MonthlyReport {
    HashMap<Integer, ArrayList<MonthData>> data = new HashMap<>();


    public MonthlyReport() {
        for(int i = 0; i < 12; i++){
            data.put(i+1, new ArrayList<>());
        }
    }

    public void replaceMonth(int month, ArrayList<MonthData> monthData){
        data.replace(month, monthData);
    }

    public void readMonth(String path, String report){
        try {
            List<String> lines = Files.readAllLines(Path.of(path + "/" + report)); //Считываются строки из файла
            ArrayList<MonthData> monthData = new ArrayList<>();
            boolean isHeader = true;
            for(String line : lines) {
                if(!isHeader) {//проверка на заголовок
                    String[] lineContents = line.split(",");//строка разбивается на параметры

                    monthData.add(new MonthData(lineContents[0].trim(),//создается экземпляр класса с заполнением параметров
                            Boolean.parseBoolean(lineContents[1].trim()),//которые предварительно очищаются от пробелов
                            Integer.parseInt(lineContents[2].trim()),
                            Integer.parseInt(lineContents[3].trim())));
                } else { isHeader = false; }
            }
            int numberMonth = Integer.parseInt(report.substring(6,8)); //вычисляется номер месяца путём обрезки имени файла
            replaceMonth(numberMonth, monthData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showData(){
        System.out.println(data);
    }
}
