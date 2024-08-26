class TrafficLight {
    private String color;
    private int timer;

    public TrafficLight(String initialColor, int initialTimer) {
        this.color = initialColor;
        this.timer = initialTimer;
    }

    public void changeColor() {
        if (color.equals("red")) {
            color = "green";
        } else if (color.equals("green")) {
            color = "yellow";
        } else if (color.equals("yellow")) {
            color = "red";
        }
        timer = 60; 
    }

    public void displayStatus() {
        System.out.println("Traffic Light is " + color + " with " + timer + " seconds remaining.");
    }
}

class Intersection {
    private TrafficLight[] trafficLights;
    private String location;

    public Intersection(TrafficLight[] lights, String loc) {
        this.trafficLights = lights;
        this.location = loc;
    }

    public void manageTraffic() {
        for (TrafficLight light : trafficLights) {
            light.changeColor();
        }
    }

    public void reportStatus() {
        System.out.println("Intersection at " + location + ":");
        for (TrafficLight light : trafficLights) {
            light.displayStatus();
        }
    }
}

public class TrafficLightSystemSimulation {
    public static void main(String[] args) {
        TrafficLight light1 = new TrafficLight("red", 60);
        TrafficLight light2 = new TrafficLight("green", 45);
        TrafficLight light3 = new TrafficLight("yellow", 30);
        TrafficLight light4 = new TrafficLight("red", 50);

        TrafficLight[] lights1 = { light1, light2 };

        TrafficLight[] lights2 = { light3, light4 };

        Intersection intersection1 = new Intersection(lights1, "5th Avenue");
        Intersection intersection2 = new Intersection(lights2, "Main Street");

        intersection1.manageTraffic();
        intersection1.reportStatus();

        intersection2.manageTraffic();
        intersection2.reportStatus();
    }
}
