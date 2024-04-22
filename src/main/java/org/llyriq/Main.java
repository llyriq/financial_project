package org.llyriq;

import java.io.File;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static MonthlyReport monthlyReport = new MonthlyReport();
    static YearlyReport yearlyReport = new YearlyReport();
    static String resourcesPath = "src/main/resources";
    static File resources = new File(resourcesPath);



    public static void main(String[] args) {
        label:
        while(true){
            System.out.println("Выберите действие:");
            System.out.println("1. Считать все месячные отчёты");
            System.out.println("2. Считать годовой отчёт");
            System.out.println("3. Сверить отчёты");
            System.out.println("4. Вывести информацию о всех месячных отчётах");
            System.out.println("5. Вывести информацию о годовом отчёте");
            System.out.println("Для выхода введите \"stop\".\n");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    readMonths();
                    break;
                case "2":
                    readYear();
                    break;
                case "3":
                    inspectReports();
                    break;
                case "4":
                    mountReportsInfo();
                    break;
                case "5":
                    yearReportInfo();
                    break;
                case "stop":
                    break label;
                default:
                    System.out.println("Введена неверная команда, пожалуйста повторите ввод.\n");
                    break;
            }
        }
    }

    public static void readMonths() {
        String[] reports = resources.list();

        assert reports != null;
        for (String report : reports) {
            if(report.charAt(0) == 'm') {
                monthlyReport.readMonth(resourcesPath, report);
            }
        }

        System.out.println("Все месячные отчёты загружены. Для продолжения нажмите \"Enter\".");
        scanner.nextLine();
        scanner.nextLine();
    }

    public static void readYear(){
        String[] reports = resources.list();

        assert reports != null;
        for (String report : reports) {
            if(report.charAt(0) == 'y') {
                yearlyReport.readYear(resourcesPath, report);
            }
        }

        System.out.println("Все годовые отчёты загружены. Для продолжения нажмите \"Enter\".");
        scanner.nextLine();
        scanner.nextLine();
    }

    public static void inspectReports(){

    }

    public static void mountReportsInfo(){
        monthlyReport.monthInfo();

        System.out.println("Для продолжения нажмите \"Enter\".");
        scanner.nextLine();
        scanner.nextLine();
    }

    public static void yearReportInfo(){
        yearlyReport.yearInfo();

        System.out.println("Для продолжения нажмите \"Enter\".");
        scanner.nextLine();
        scanner.nextLine();
    }
}