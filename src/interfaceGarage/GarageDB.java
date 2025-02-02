package interfaceGarage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class GarageDB {

    private HashMap<Integer, Queue<Vehicle>> vehicles = new HashMap<>();
    private static int count=0;



    public HashMap<Integer, Queue<Vehicle>> getVehicles() {
        return vehicles;
    }

}