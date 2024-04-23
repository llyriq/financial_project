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

    public void monthInfo(){
        for(int key : data.keySet()){
            String[] nameMonths = {"Январь", "Февраль", "Март", "Апрель",
                                    "Май", "Июнь", "Июль", "Август",
                                    "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
            ArrayList<MonthData> monthData = data.get(key); //Получаем данные одного месяца
            int maxProfit = 0;
            String nameProfit = "";
            int maxWaste = 0;
            String nameWaste = "";

            for (MonthData month : monthData) {//Пробегаемся по всем данным
                if (!month.is_expense && month.quantity*month.price > maxProfit){//Если это не трата и сумма больше, то обновляем максимум
                    maxProfit = month.quantity*month.price;
                    nameProfit = month.item_name;
                }
                if (month.is_expense && month.quantity*month.price > maxWaste){//Если это трата и сумма больше, то обновляем максимум
                    maxWaste = month.quantity*month.price;
                    nameWaste = month.item_name;
                }
            }
            System.out.println("Информацию по месяцу: " + nameMonths[--key]);
            System.out.println("Самый прибыльный товар - " + nameProfit + " на сумму " + maxProfit);
            System.out.println("Самая большая трата - " + nameWaste + " на сумму " + maxWaste);
        }
    }
}
