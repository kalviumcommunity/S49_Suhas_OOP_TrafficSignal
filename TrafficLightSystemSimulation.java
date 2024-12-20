import java.util.Scanner;

// Base class for all types of lights
abstract class BaseLight {
    protected String color;
    protected int timer;

    public abstract void changeColor(); // Abstract method to change light color

    public abstract void displayStatus(); // Abstract method to display light status
}


class TrafficLight extends BaseLight {
    public static final int DEFAULT_GLOBAL_TIMER = 60;
    private static int globalTimer = DEFAULT_GLOBAL_TIMER; 
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

    @Override
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

    @Override
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


class PedestrianLight extends BaseLight {
    private boolean walkSignal; 

    public PedestrianLight() {
        this.color = "red";
        this.timer = TrafficLight.DEFAULT_GLOBAL_TIMER;
        this.walkSignal = false;
    }

    public void changeSignal() {
        walkSignal = !walkSignal;
        System.out.println("Pedestrian Light: Walk signal " + (walkSignal ? "ON" : "OFF"));
    }

    @Override
    public void changeColor() {
        // Cycles between red and green for pedestrian light
        if (color.equals("red")) {
            color = "green";
        } else {
            color = "red";
        }
        System.out.println("Pedestrian Light changed to " + color);
        changeSignal(); // Update walk signal when color changes
    }

    @Override
    public void displayStatus() {
        System.out.println("Pedestrian Light is " + color + " with " + timer + " seconds remaining.");
        System.out.println("Pedestrian walk signal: " + (walkSignal ? "ON" : "OFF"));
    }
}

// Intersection class managing multiple lights
class Intersection {
    protected BaseLight[] lights; // Array of lights at the intersection
    protected String location;

    public Intersection(BaseLight[] lights, String loc) {
        this.lights = lights;
        this.location = loc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void manageTraffic() {
        for (BaseLight light : lights) {
            light.changeColor();
        }
    }

    public void reportStatus() throws InterruptedException {
        System.out.println("Intersection at " + location + ":");
        for (BaseLight light : lights) {
            light.displayStatus();
            Thread.sleep(1000); // 1-second delay for each light's status display
        }
    }

    public void cleanupTrafficLights() {
        for (BaseLight light : lights) {
            System.out.println("Cleaning up light...");
        }
    }
}


class SmartIntersection extends Intersection {
    public SmartIntersection(BaseLight[] lights, String loc) {
        super(lights, loc);
    }

    public void optimizeTraffic() {
        System.out.println("Optimizing traffic at smart intersection: " + getLocation());
        for (BaseLight light : lights) {
            if (light instanceof TrafficLight) {
                ((TrafficLight) light).setTimer(30); 
            }
            light.displayStatus();
        }
    }
}

// UI for interaction with the user
class IntersectionUI {
    private final Scanner scanner = new Scanner(System.in);

    public String getLocationInput() {
        System.out.println("Enter the location for the intersection:");
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }
}

// Main simulation class
public class TrafficLightSystemSimulation {
    public static void main(String[] args) throws InterruptedException {
        IntersectionUI ui = new IntersectionUI();

        // Get location input from the user
        String location = ui.getLocationInput();

        // Create array of lights for the intersection
        BaseLight[] lights = new BaseLight[2];
        lights[0] = new TrafficLight("green", 45); // Regular traffic light
        lights[1] = new PedestrianLight(); // Pedestrian light

        // Create a smart intersection with the lights
        SmartIntersection intersection = new SmartIntersection(lights, location);

        System.out.println("Managing basic traffic flow:");
        intersection.manageTraffic();
        intersection.reportStatus();

        System.out.println("Optimizing traffic flow:");
        intersection.optimizeTraffic();

        TrafficLight.displayTotalLights();

        intersection.cleanupTrafficLights();
        ui.closeScanner();
    }
}
