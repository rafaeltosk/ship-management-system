package domain;

public class Container {
    private  int ID;
    private String type;
    private int weight;

    public Container(int ID, int weight, String type) {
        this.ID = ID;
        this.weight = weight;
        this.type = type;
    }

    public double getFuelMultiplier(){
        switch (type) {
            case "απλό":
                return 20;
            case "βυτίο":
                return 27;
            case "ψυγείο":
                return 35;
        }
        return  1;
    }

    public boolean isHeavy (){
        return !type.equals("απλό");
    }

    @Override
    public String toString() {
        return "domain.Container[ID=" + ID + ", weight=" + weight + "]";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
