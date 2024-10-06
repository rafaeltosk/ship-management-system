import domain.Container;
import domain.Ports;
import domain.Ship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static services.Loader.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static List<Ports> portsList = new ArrayList<>();
    static List<Ship> shipList = new ArrayList<>();
    static List<Container> containerList = new ArrayList<>();

    public static void main(String[] args) {
        portsList = readPortsFromFile("files/ports.txt");
        shipList = readShipsFromFile("files/ships.txt", portsList);
        containerList =  readContainersFromFile("files/containers.txt", portsList);

        Simulation_ALPHA();
        Simulation_BETA();
    }

    public static void Simulation_ALPHA() {
        int n=0;
        for (int i=0; i<containerList.size(); i++ ){
            while ( n<shipList.size() && !shipList.get(n).load(containerList.get(i)) ) {
                System.out.println(" Loaded current ship ");
                n++;
                System.out.println("Proceeding to next ship:");
            }
        }

        for (int i=0; i<shipList.size(); i++){
            Ship currentShip= shipList.get(i);
            Ports ports = portsList.get(i+1);
            double fuel = currentShip.fuelCalculatorToPort(ports);
            System.out.println("\n The fuel consumption from " + currentShip.getCurrentPort().getName() + " to " + ports.getName() + " is : " + fuel );
            currentShip.reFuel(fuel);
            currentShip.sailTo(ports);
            currentShip.unloadAll();
        }
        System.out.println("\n");
        for (int i=0; i<portsList.size(); i++ ){
            System.out.println( "\n ΚΑΤΑΣΤΑΣΗ ΛΙΜΑΝΙΟΥ :" + portsList.get(i).toString() );
        }   System.out.println("\n");

        for (int i=0; i<shipList.size(); i++ ){
            System.out.println( "\n ΚΑΤΑΣΤΑΣΗ ΚΑΡΑΒΙΩΝ :" + shipList.get(i).toString() );
        }
    }

    public static void Simulation_BETA() {
        Set<Integer> loadedContainers = new HashSet<>();
        System.out.println("\n____________________ LOADING CONTAINERS... ____________________");
        for (int n = 0; n < shipList.size(); n++ ){
            for ( int i = 0; i < containerList.size(); i++) {
                if ( !loadedContainers.contains(containerList.get(i).getID()) && shipList.get(n).load(containerList.get(i))) {

                    System.out.println(" I loaded container number: " + i );
                    loadedContainers.add(containerList.get(i).getID());
                    System.out.println("Proceeding to next one...");
                }
            }
        }

        System.out.println("____________________ PROCEEDING TO FUEL CONSUMPTION ____________________");
        for (int i = 0; i < shipList.size(); i++) {
            Ship currentShip = shipList.get(i);
            Ports ports = portsList.get(i + 1);
            double fuel = currentShip.fuelCalculatorToPort(ports);
            System.out.println("\n The fuel consumption from : " + currentShip.getCurrentPort().getName() + " to " + ports.getName() + " is : " + fuel);
            currentShip.reFuel(fuel);
            currentShip.sailTo(ports);
            currentShip.unloadAll();
        }
        System.out.println("\n");
        System.out.println("____________________ PROCEEDING TO PORT STATE ____________________");
        for (int i = 0; i < portsList.size(); i++) {
            System.out.println("\n ΚΑΤΑΣΤΑΣΗ ΛΙΜΑΝΙΟΥ: " + portsList.get(i).toString());
        }
        System.out.println("\n");
        System.out.println("____________________ PROCEEDING TO SHIP STATE ____________________");
        for (int i = 0; i < shipList.size(); i++) {
            System.out.println("\n ΚΑΤΑΣΤΑΣΗ ΚΑΡΑΒΙΩΝ: " + shipList.get(i).toString());
        }
        System.out.println("\n\n############### SIMULATION BETA COMPLETED SUCCESSFULLY ############### ");
    }
}