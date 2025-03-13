package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
//        Группируем по продуктам
        Map<String, Long> map = orders.stream().collect(Collectors.groupingBy(Order::product, Collectors.counting()));
        System.out.println(map);

//        Общая стоимость для каждого заказа
        Map<String, Double> summaryMap = orders.stream().collect(Collectors.groupingBy(Order::product, Collectors.summingDouble(Order::cost)));
        System.out.println(summaryMap);

//        Продукты по убыванию общей стоимости
        List<Order> sortedOrders = orders.stream().sorted(Comparator.comparing(Order::cost).reversed()).toList();
        for (Order order : sortedOrders) {
            System.out.printf(order.product() + " " + order.cost() + " ");
        }

        System.out.println("\n3 самых дорогих продукта");
//        3 самых дорогих продукта
        List<Order> expensiveOrders = orders.stream().sorted(Comparator.comparing(Order::cost).reversed()).limit(3).toList();
        for (Order order : expensiveOrders) {
            System.out.printf(order.product() + " " + order.cost() + " ");
        }

        System.out.println("\nCписок трех самых дорогих продуктов и их общая стоимость");
//        список трех самых дорогих продуктов и их общая стоимость
        double totalMap = expensiveOrders
                .stream()
                .peek(v -> System.out.println(v.product() + " " + v.cost()))
                .mapToDouble(Order::cost).sum();
        System.out.println(totalMap);
    }
}
