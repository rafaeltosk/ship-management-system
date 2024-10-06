package domain;
import java.util.ArrayList;
import java.util.List;


public class Ship {

    private int ID;
    private String name;
    private double fuelConsumptionPerKM;
    private int totalWeightCapacity;
    private int maxNumberOfAllContainers;
    private int maxNumberOfHeavyContainers;
    private Ports currentPort;
    private double fuelInTank;
    private int currentWeight=0;
    private int currentHeavyCount=0;
    private List<Container> onShipContainerList = new ArrayList<>();

    public Ship(int ID, String name, double fuelConsumptionPerKM, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers, Ports currentPort) {
        this.ID = ID;
        this.name = name;
        this.fuelConsumptionPerKM = fuelConsumptionPerKM;
        this.totalWeightCapacity = totalWeightCapacity;
        this.maxNumberOfAllContainers = maxNumberOfAllContainers;
        this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
        this.currentPort = currentPort;
        this.fuelInTank = 0;
        this.onShipContainerList = new ArrayList<>();
    }

    public boolean sailTo(Ports p) {
        double requiredFuel = fuelCalculatorToPort(p);
        if (requiredFuel <= fuelInTank) {
            fuelInTank -= requiredFuel;
            currentPort.removeShip(this);
            currentPort.addShip(this);
            return true;
        } else {
            return false;
        }
    }
    public double fuelCalculatorToPort(Ports fuelToPort) {
        double sum=0;
        double distance = currentPort.calculateDistance(fuelToPort);
        for (int i=0 ; i<onShipContainerList.size(); i++){
            Container currentContainer=onShipContainerList.get(i);
            double currentMultiplier= currentContainer.getFuelMultiplier();
            double weightContainer= currentContainer.getWeight();
            double fuelNeeded = currentMultiplier * weightContainer * distance * fuelConsumptionPerKM;
            sum+=fuelNeeded;
        }
        return sum;
    }

    public void reFuel(double newFuel) {
        fuelInTank += newFuel;
    }

    public boolean load(Container cont) {
        if (onShipContainerList.size() + 1 <= maxNumberOfAllContainers) {
            if (currentWeight + cont.getWeight() <= totalWeightCapacity) {
                if (cont.isHeavy()) {
                    if (currentHeavyCount + 1 <= maxNumberOfHeavyContainers) {
                        onShipContainerList.add(cont);
                        currentHeavyCount += 1;
                        currentWeight += cont.getWeight();
                        currentPort.removeContainer(cont);
                        return true;
                    }
                } else {
                    onShipContainerList.add(cont);
                    currentWeight += cont.getWeight();
                    currentPort.removeContainer(cont);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean unload(Container cont) {
        if (onShipContainerList.contains(cont)) {
            onShipContainerList.remove(cont);
            currentPort.addContainer(cont);
            return true;
        }
        return false;
    }

    public void unloadAll() {
        for (int j=0; j<getOnShipContainerList().size(); j++){
            unload(getOnShipContainerList().get(0));
        }
        onShipContainerList.clear();
    }


    public int getWeight() {
        int totalWeight = 0;
        for (Container container : onShipContainerList) {
            totalWeight += container.getWeight();
        }
        return totalWeight;
    }

    public int getPortID() {
        return currentPort.getId();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFuelConsumptionPerKM() {
        return fuelConsumptionPerKM;
    }

    public void setFuelConsumptionPerKM(double fuelConsumptionPerKM) {
        this.fuelConsumptionPerKM = fuelConsumptionPerKM;
    }

    public int getTotalWeightCapacity() {
        return totalWeightCapacity;
    }

    public void setTotalWeightCapacity(int totalWeightCapacity) {
        this.totalWeightCapacity = totalWeightCapacity;
    }

    public int getMaxNumberOfAllContainers() {
        return maxNumberOfAllContainers;
    }

    public void setMaxNumberOfAllContainers(int maxNumberOfAllContainers) {
        this.maxNumberOfAllContainers = maxNumberOfAllContainers;
    }

    public int getMaxNumberOfHeavyContainers() {
        return maxNumberOfHeavyContainers;
    }

    public void setMaxNumberOfHeavyContainers(int maxNumberOfHeavyContainers) {
        this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
    }

    public Ports getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Ports currentPort) {
        this.currentPort = currentPort;
    }

    public double getFuelInTank() {
        return fuelInTank;
    }

    public void setFuelInTank(double fuelInTank) {
        this.fuelInTank = fuelInTank;
    }

    public List<Container> getOnShipContainerList() {
        return onShipContainerList;
    }

    public void setOnShipContainerList(List<Container> onShipContainerList) {
        this.onShipContainerList = onShipContainerList;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", fuelConsumptionPerKM=" + fuelConsumptionPerKM +
                ", totalWeightCapacity=" + totalWeightCapacity +
                ", maxNumberOfAllContainers=" + maxNumberOfAllContainers +
                ", maxNumberOfHeavyContainers=" + maxNumberOfHeavyContainers +
                ", fuelInTank=" + fuelInTank +
                ", currentWeight=" + currentWeight +
                '}';
    }
}
