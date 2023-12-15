package laptops;

class Laptop {
    private String model;
    private int ramSize;
    private int hddSize;
    private String operatingSystem;
    private String color;
    private double screenSize;

    public Laptop(String model, int ramSize, int hddSize, String operatingSystem, String color,
                  double screenSize) {
        this.model = model;
        this.ramSize = ramSize;
        this.hddSize = hddSize;
        this.operatingSystem = operatingSystem;
        this.color = color;
        this.screenSize = screenSize;
    }

    // Getters and Setters for screenSize
    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRamSize() {
        return ramSize;
    }

    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }

    public int getHddSize() {
        return hddSize;
    }

    public void setHddSize(int hddSize) {
        this.hddSize = hddSize;
    }

    public String getOperatingSystem() {
        return operatingSystem.toLowerCase();
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getColor() {
        return color.toLowerCase();
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        return "Laptop{" +
                "model='" + model + '\'' +
                ", ramSize=" + ramSize +
                ", hddSize=" + hddSize +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", color='" + color + '\'' +
                ", screenSize=" + screenSize +
                '}';
    }
}