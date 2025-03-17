package org.example;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 6);
        Triangle triangle = new Triangle(3, 4, 5);

        System.out.println("Circle radius: " + circle.getRadius()); // После обновления до 1.1.0
        System.out.println("Circle area: " + circle.area());
        System.out.println("Circle perimeter: " + circle.perimeter());
        System.out.println("Rectangle area: " + rectangle.area());
        System.out.println("Rectangle perimeter: " + rectangle.perimeter());
        System.out.println("Triangle area: " + triangle.area());
        System.out.println("Triangle perimeter: " + triangle.perimeter());

        double areaInSquareFeet = 100;
        double areaInSquareMeters = GeometryUtils.convertToSquareMeters(areaInSquareFeet);
        System.out.println("Area in square meters: " + areaInSquareMeters);
        GeometryUtils.getVersion(); // Utils 1.1.0-SNAPSHOT

        Cube cube = new Cube(3);
        Sphere sphere = new Sphere(4);

        System.out.println("Cube volume: " + cube.volume());
        System.out.println("Cube surface area: " + cube.surfaceArea());

        System.out.println("Cube area 1.1.0-snapshot " + cube.cubeArea());
    }
}