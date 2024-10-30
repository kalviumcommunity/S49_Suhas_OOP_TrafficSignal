import java.util.Scanner;

class TrafficLight {
    protected String color;
    protected int timer;

    public static final int DEFAULT_GLOBAL_TIMER = 60;
    private static int globalTimer = DEFAULT_GLOBAL_TIMER; // Made private for encapsulation
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

    public static int getGlobalTimer() {
        return globalTimer;
    }

    public static void setGlobalTimer(int globalTimer) {
        TrafficLight.globalTimer = globalTimer;
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

class PedestrianLight extends TrafficLight {
    private boolean walkSignal;

    public PedestrianLight() {
        super("red", DEFAULT_GLOBAL_TIMER);
        this.walkSignal = false;
    }

    public void changeSignal() {
        walkSignal = !walkSignal;
        if (walkSignal) {
            System.out.println("Pedestrian Light: Walk signal ON.");
        } else {
            System.out.println("Pedestrian Light: Walk signal OFF.");
        }
    }

    @Override
    public void displayStatus() {
        super.displayStatus();
        System.out.println("Pedestrian walk signal: " + (walkSignal ? "ON" : "OFF"));
    }
}

class Intersection {
    protected TrafficLight[] trafficLights;
    protected String location;

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
            Thread.sleep(1000); // 1-second delay for each light's status display
        }
    }

    public void cleanupTrafficLights() {
        for (TrafficLight light : trafficLights) {
            light.cleanup();
        }
    }
}

class SmartIntersection extends Intersection {
    public SmartIntersection(TrafficLight[] lights, String loc) {
        super(lights, loc);
    }

    public void optimizeTraffic() {
        System.out.println("Optimizing traffic at smart intersection: " + getLocation());
        for (TrafficLight light : trafficLights) {
            light.setTimer(30); // Reduce timer for faster flow
            light.displayStatus();
        }
    }
}

public class TrafficLightSystemSimulation {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the location for the intersection:");
        String location = scanner.nextLine();

        TrafficLight[] lights = new TrafficLight[2];
        AbstractclassandVirtualFunction
        lights[0] = new TrafficLight("green", 45);
        lights[1] = new PedestrianLight();

        lights[0] = new TrafficLight("green", 45);  
        lights[1] = new PedestrianLight();  
        main

        SmartIntersection intersection = new SmartIntersection(lights, location);

        System.out.println("Managing basic traffic flow:");
        intersection.manageTraffic();
        intersection.reportStatus();

        System.out.println("Optimizing traffic flow:");
        intersection.optimizeTraffic();

        TrafficLight.displayTotalLights();

        intersection.cleanupTrafficLights();
        scanner.close();
    }
}
