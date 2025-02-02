package interfaceGarage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

public class Garage {

    private Protocol_Garage protocolGarage;
    private HashMap<Integer, Queue<Vehicle>> vehicles;
    private boolean isGarageOpen = true;
    private int hour = 0;
    private static int currentMapEntry = 1;
    private static Vehicle currentVehicle;
    ;
    private static final int maxMapEntry = 3;

    public Garage(Protocol_Garage protocolGarage) {
        this.protocolGarage = protocolGarage;
    }

    public void setVehicles(HashMap<Integer, Queue<Vehicle>> vehicles) {
        this.vehicles = vehicles;
        currentVehicle = this.vehicles.get(currentMapEntry).poll();
    }

    public void startWork(HashMap<Integer, Queue<Vehicle>> map) {


        Runnable helloRunnable = new Runnable() {
            @Override
            public void run() {
                tick();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
    }

    private void closeGarage() {
        isGarageOpen = false;
        System.out.println("All fixed = day off!!");

    }

    private void tick() {
        if(!isGarageOpen){
            return;

        }
        if (!isGarageOpen || currentMapEntry > maxMapEntry) {
            closeGarage();
            return;
        }
        if (currentVehicle == null) {

            while (currentMapEntry <= maxMapEntry &&
                    (vehicles.get(currentMapEntry) == null ||
                            vehicles.get(currentMapEntry).isEmpty())) {
                currentMapEntry++;
            }


            if (currentMapEntry > maxMapEntry) {
                closeGarage();
                return;
            }


            currentVehicle = vehicles.get(currentMapEntry).poll();
            hour = 0;
        }

        System.out.println("hour:"+hour);

        if (currentVehicle != null) {

            if (hour == currentVehicle.getFixedTime()) {

                currentVehicle.fixed();
                protocolGarage.fixed();


                if (vehicles.get(currentMapEntry).isEmpty()) {
                    currentMapEntry++;
                }


                if (currentMapEntry > maxMapEntry) {
                    closeGarage();
                    return;
                }


                currentVehicle = null;
            } else {

                hour++;
            }
        }
    }
}
