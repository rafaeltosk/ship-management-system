package services;

import domain.Container;
import domain.Ports;
import domain.Ship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    public static List<Ports> readPortsFromFile(String filePath) {
        List<Ports> portsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double lat = Double.parseDouble(parts[2].trim());
                    double lon = Double.parseDouble(parts[3].trim());
                    Ports port = new Ports(id, name, lat, lon);
                    portsList.add(port);
                } else {
                    System.err.println("Not the right format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return portsList;
    }

    public static List<Ship> readShipsFromFile(String filePath, List<Ports> portsList) {
        List<Ship> shipsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double fuelConsumptionPerKM = Double.parseDouble(parts[2].trim());
                    int totalWeightCapacity = Integer.parseInt(parts[3].trim());
                    int maxNumberOfAllContainers = Integer.parseInt(parts[4].trim());
                    int maxNumberOfHeavyContainers = Integer.parseInt(parts[5].trim());
                    Ports currentPort = portsList.stream()
                            .filter(port -> port.getId() == Integer.parseInt(parts[6].trim()))
                            .findFirst()
                            .orElse(null);

                    if (currentPort != null) {
                        Ship ship = new Ship(id, name,fuelConsumptionPerKM ,totalWeightCapacity, maxNumberOfAllContainers, maxNumberOfHeavyContainers,
                                currentPort );
                        shipsList.add(ship);
                    } else {
                        System.err.println("Invalid port ID: " + parts[1]);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return shipsList;
    }

    public static List<Container> readContainersFromFile(String filePath, List<Ports> portsList) {
        List<Container> containerList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int id = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String type = parts[0].trim();
                    int weight = Integer.parseInt(parts[1].trim());
                    Container container = new Container( id , weight ,type );
                    containerList.add(container);
                    id++;
                    portsList.get(0).addContainer(container);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return containerList;
    }
}
