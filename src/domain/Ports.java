package domain;

import java.util.ArrayList;
import java.util.List;

public class Ports {
    private int id;
    private String name;
    private double lat;
    private double lon;
    private List<Container> containers;
    private List<Ship> ships;


    public Ports(int id, String name, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.containers = new ArrayList<>();
        this.ships = new ArrayList<>();
    }

    public double calculateDistance(Ports other) {
        return Math.acos((Math.sin(Math.toRadians(this.lat)) * Math.sin(Math.toRadians(other.lat))) +
                (Math.cos(Math.toRadians(this.lat)) * Math.cos(Math.toRadians(other.lat)) *
                        Math.cos(Math.toRadians(other.lon) - Math.toRadians(this.lon)))) * 6371;
    }

    public void addContainer(Container container) {
        containers.add(container);
    }


    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public void removeShip(Ship ship) {
        ships.remove(ship);
    }

    public void removeContainer(Container container) { containers.remove(container); }

    @Override
    public String toString() {
        return "domain.Ports{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", containers=" + containers +
                ", ships=" + ships +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }
}
