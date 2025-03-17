package org.example;

public class GeometryUtils {
    public static double convertToSquareMeters(double areaInSquareFeet) {
        return areaInSquareFeet * 0.092903;
    }

    public static boolean compareAreas(double area1, double area2) {
        return Double.compare(area1, area2) == 0;
    }

    public static void getVersion() {
        System.out.println("This version is 1.1.0-SNAPSHOT");
    }
}
