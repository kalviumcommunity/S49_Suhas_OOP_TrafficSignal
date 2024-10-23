import java.util.Scanner;

class TrafficLight {
    private String color;
    private int timer;

    public static final int DEFAULT_GLOBAL_TIMER = 60; 
    public static int globalTimer = DEFAULT_GLOBAL_TIMER;
    public static int totalLights = 0;

    public TrafficLight() {
        this.color = "red"; 
        this.timer = DEFAULT_GLOBAL_TIMER; 
        totalLights++;
    }

    public TrafficLight(String initialColor, int initialTimer) {
        this.color = initialColor;
        this.timer = initialTimer;
        totalLights++;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void changeColor() {
        switch (color) {
            case "red":
                color = "green";
                break;
            case "green":
                color = "yellow";
                break;
            case "yellow":
                color = "red";
                break;
            default:
                System.out.println("Invalid color! Setting to red by default.");
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

    public void cleanup() {
        System.out.println("Traffic Light at " + color + " with timer " + timer + " is being removed.");
        totalLights--;
    }
}

class Intersection {
    private TrafficLight[] trafficLights;
    private String location;

    public Intersection(TrafficLight[] lights, String loc) {
        this.trafficLights = lights;
        this.location = loc;
    }

    public String getLocation() {
        return location;
    }

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
            Thread.sleep(1000); 
        }
    }

    public void cleanupTrafficLights() {
        for (TrafficLight light : trafficLights) {
            light.cleanup();
        }
    }
}

public class TrafficLightSystemSimulation {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the global timer value for all traffic lights (default is " + TrafficLight.DEFAULT_GLOBAL_TIMER + "):");
        int globalTimerInput = scanner.nextInt();
        TrafficLight.globalTimer = globalTimerInput > 0 ? globalTimerInput : TrafficLight.DEFAULT_GLOBAL_TIMER; // Validate input
        scanner.nextLine();

        System.out.println("Enter the location for the first intersection:");
        String location1 = scanner.nextLine();

        System.out.println("Enter the number of traffic lights for " + location1 + ":");
        int numLights1 = scanner.nextInt();
        scanner.nextLine();

        TrafficLight[] lights1 = new TrafficLight[numLights1];

        for (int i = 0; i < numLights1; i++) {
            System.out.println("Enter the initial color of traffic light " + (i + 1) + " (red, green, yellow):");
            String color = scanner.nextLine().toLowerCase();

            System.out.println("Enter the timer for traffic light " + (i + 1) + " in seconds:");
            int timer = scanner.nextInt();
            scanner.nextLine();

            while (!color.equals("red") && !color.equals("green") && !color.equals("yellow")) {
                System.out.println("Invalid color! Please enter red, green, or yellow:");
                color = scanner.nextLine().toLowerCase();
            }

            if (timer <= 0) {
                System.out.println("Invalid timer! Setting timer to default value of 60 seconds.");
                timer = 60;
            }

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
            String color = scanner.nextLine().toLowerCase();

            System.out.println("Enter the timer for traffic light " + (i + 1) + " in seconds:");
            int timer = scanner.nextInt();
            scanner.nextLine();

            while (!color.equals("red") && !color.equals("green") && !color.equals("yellow")) {
                System.out.println("Invalid color! Please enter red, green, or yellow:");
                color = scanner.nextLine().toLowerCase();
            }

            if (timer <= 0) {
                System.out.println("Invalid timer! Setting timer to default value of 60 seconds.");
                timer = 60;
            }

            lights2[i] = new TrafficLight(color, timer);
        }

        Intersection intersection1 = new Intersection(lights1, location1);
        Intersection intersection2 = new Intersection(lights2, location2);

        intersection1.manageTraffic();
        intersection1.reportStatus();

        intersection2.manageTraffic();
        intersection2.reportStatus();

        TrafficLight.displayTotalLights();

        intersection1.cleanupTrafficLights();
        intersection2.cleanupTrafficLights();

        scanner.close();
    }
}
