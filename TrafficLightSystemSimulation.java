import java.util.Scanner;

class TrafficLight {
    private String color;
    private int timer;

    public static int globalTimer = 60;
    public static int totalLights = 0;

    // Constructor
    public TrafficLight(String initialColor, int initialTimer) {
        this.color = initialColor;
        this.timer = initialTimer;
        totalLights++;
    }

    // Accessor (getter) for color
    public String getColor() {
        return color;
    }

    // Mutator (setter) for color
    public void setColor(String color) {
        this.color = color;
    }

    // Accessor (getter) for timer
    public int getTimer() {
        return timer;
    }

    // Mutator (setter) for timer
    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void changeColor() {
        if (color.equals("red")) {
            color = "green";
        } else if (color.equals("green")) {
            color = "yellow";
        } else if (color.equals("yellow")) {
            color = "red";
        }
        timer = globalTimer;
    }

    public void displayStatus() {
        System.out.println("Traffic Light is " + color + " with " + timer + " seconds remaining.");
    }

    public static void displayTotalLights() {
        System.out.println("Total number of traffic lights created: " + totalLights);
    }
}

class Intersection {
    private TrafficLight[] trafficLights;
    private String location;

    // Constructor
    public Intersection(TrafficLight[] lights, String loc) {
        this.trafficLights = lights;
        this.location = loc;
    }

    // Accessor (getter) for location
    public String getLocation() {
        return location;
    }

    // Mutator (setter) for location
    public void setLocation(String location) {
        this.location = location;
    }

    public void manageTraffic() {
        for (TrafficLight light : trafficLights) {
            light.changeColor();
        }
    }

    public void reportStatus() throws InterruptedException {
        System.out.println("Intersection at " + location + ":");
        for (TrafficLight light : trafficLights) {
            light.displayStatus();
            Thread.sleep(1000);  // Add 1-second delay between each status report
        }
    }
}

public class TrafficLightSystemSimulation {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the global timer value for all traffic lights:");
        TrafficLight.globalTimer = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the location for the first intersection:");
        String location1 = scanner.nextLine();

        System.out.println("Enter the number of traffic lights for " + location1 + ":");
        int numLights1 = scanner.nextInt();
        scanner.nextLine();

        TrafficLight[] lights1 = new TrafficLight[numLights1];

        for (int i = 0; i < numLights1; i++) {
            System.out.println("Enter the initial color of traffic light " + (i + 1) + " (red, green, yellow):");
            String color = scanner.nextLine();

            System.out.println("Enter the timer for traffic light " + (i + 1) + " in seconds:");
            int timer = scanner.nextInt();
            scanner.nextLine();

            lights1[i] = new TrafficLight(color, timer);
        }

        System.out.println("Enter the location for the second intersection:");
        String location2 = scanner.nextLine();

        System.out.println("Enter the number of traffic lights for " + location2 + ":");
        int numLights2 = scanner.nextInt();
        scanner.nextLine();

        TrafficLight[] lights2 = new TrafficLight[numLights2];

        for (int i = 0; i < numLights2; i++) {
            System.out.println("Enter the initial color of traffic light " + (i + 1) + " (red, green, yellow):");
            String color = scanner.nextLine();

            System.out.println("Enter the timer for traffic light " + (i + 1) + " in seconds:");
            int timer = scanner.nextInt();
            scanner.nextLine();

            lights2[i] = new TrafficLight(color, timer);
        }

        Intersection intersection1 = new Intersection(lights1, location1);
        Intersection intersection2 = new Intersection(lights2, location2);

        intersection1.manageTraffic();
        intersection1.reportStatus();

        intersection2.manageTraffic();
        intersection2.reportStatus();

        TrafficLight.displayTotalLights();

        scanner.close();
    }
}
