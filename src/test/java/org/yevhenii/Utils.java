package org.yevhenii;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.*;

import static org.yevhenii.ui_tests.pages.BasePage.log;

public class Utils {
    private void captureScreenshot(WebDriver driver, String filename) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
            // Save screenshot or attach to test report
        } catch (Exception e) {
            log.warn("Failed to capture screenshot: {}", e.getMessage());
        }
    }

    public static String getCurrentMillis() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static void main(String[] args) {

        System.out.println("===== Natural order in TreeSet ======");
        List<Integer> normal = List.of(9, 8, 8, 3, 1);
        Set<Integer> reverse = new TreeSet<>(normal);// natural or custom order
        System.out.println(reverse);                // [1, 3, 8, 9]


        System.out.println("===== Reverse order in TreeSet ======");
        List<Integer> custom = List.of(9, 8, 8, 3, 1);
        Set<Integer> reverseCustom = new TreeSet<>(Collections.reverseOrder());// natural or custom order
        reverseCustom.addAll(custom);
        System.out.println(reverseCustom);              // [9, 8, 3, 1]


        System.out.println("===== String Length () ======");//todo: cpy to vkladka2
        TreeSet<String> byLength = new TreeSet<>((a, b) -> a.length() - b.length());

        byLength.add("Dog");
        byLength.add("Catito");
        byLength.add("Han");

        byLength.add("Murmaniko");
        byLength.add("Quagy");
        byLength.add("Okjoser");

        System.out.println(byLength);

        System.out.println("===== String order ======");
        Set<Fua> testCases = new TreeSet<>((tc1, tc2) -> tc1.priority - tc2.priority);
        testCases.add(new Fua("Login test", 0));
        testCases.add(new Fua("Auth test", 1));
        testCases.add(new Fua("Performance test", 2));
        System.out.println(testCases.toString());

        System.out.println("=====1. Queue as LinkedList ======");
        Queue<String> order = new LinkedList<>();
        order.add("Auth");
        order.add("Checkout");
        order.add("Settings");

        System.out.println("0. order:" + order); // [Auth, Checkout, Settings]

        while (!order.isEmpty()) {
            order.poll();  //[Checkout, Settings] -> [Settings] ->  []
            System.out.println("1. order: " + order);
        }
        System.out.println(order); // -> []


        System.out.println("===== Hash Map (order not guaranteed) ======");
        Map<String, String> config = new HashMap<>();
        config.put("browser", "chrome");
        config.put("env", "staging");
        config.put("timeout", "100");

        config.forEach((key, value) -> System.out.println("key:" + key + "value:" + value ));


    }



    public static class Fua {
        String name;
        Integer priority;

        public Fua(String name, Integer priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Fua{" +
                    "name='" + name + '\'' +
                    ", priority=" + priority +
                    '}';
        }
    }
}
