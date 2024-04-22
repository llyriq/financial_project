package org.llyriq;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class YearlyReport {
    ArrayList<YearData> data = new ArrayList<>();


    public YearlyReport() {
    }

    public void readYear(String path, String report){
        try {
            List<String> lines = Files.readAllLines(Path.of(path + "/" + report)); //Считываются строки из файла
            boolean isHeader = true;
            for(String line : lines) {
                if(!isHeader) {//проверка на заголовок
                    String[] lineContents = line.split(",");//строка разбивается на параметры

                    data.add(new YearData(Integer.parseInt(lineContents[0].trim()),//создается экземпляр класса с заполнением параметров
                                        Integer.parseInt(lineContents[1].trim()),//которые предварительно очищаются от пробелов
                                        Boolean.parseBoolean(lineContents[2].trim())));
                } else { isHeader = false; }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void yearInfo() {
        String[] nameMonths = {"Январь", "Февраль", "Март", "Апрель",
                "Май", "Июнь", "Июль", "Август",
                "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        int absoluteProfit = 0;
        int profit = 0;
        int waste = 0;
        int month = 0;
        int count = 0;

        for (YearData yearData : data) {
            if (month != yearData.month) {
                if (month != 0) {
                    System.out.println("Прибыль за " + nameMonths[--month] + " - " + absoluteProfit);
                }
                absoluteProfit = 0;
                month = yearData.month;
            }
            if (yearData.is_expense) {
                absoluteProfit -= yearData.amount;
                waste += yearData.amount;
            } else {
                absoluteProfit += yearData.amount;
                profit += yearData.amount;
            }
            count++;
        }

        profit /= count;
        waste /= count;

        System.out.println("Средний расход за все месяцы - " + waste);
        System.out.println("Средний доход за все месяцы - " + profit);

    }
}
