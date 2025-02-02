package interfaceGarage;

import javax.swing.plaf.synth.Region;
import java.util.*;

public class MainGarage2 {

    private static GarageDB garageDB;

    public static <T> void start() {
        garageDB = new GarageDB();


        Scanner scanner = new Scanner(System.in);
        int index=0;
        int x=1;

        garageDB.getVehicles().put(1, new PriorityQueue<>((v1, v2) -> v1.getModel().compareTo(v2.getModel())));
        garageDB.getVehicles().put(2, new PriorityQueue<>((v1, v2) -> v1.getModel().compareTo(v2.getModel())));
        garageDB.getVehicles().put(3, new PriorityQueue<>((v1, v2) -> v1.getModel().compareTo(v2.getModel())));


        while(index!=-1){
            System.out.println("pls enter a number between 1-3:\n" +
                    "1 for MotorCycle\n" +
                    "2 for Car\n" +
                    "3 for Truck\n" +
                    "(-1 to finish)");
            index=scanner.nextInt();
            scanner.nextLine();
            if(index==-1){
                break;
            }
            System.out.println("pls enter a name for current Vhicle");
            String name=scanner.nextLine();
            switch(index){
                case 1:
                    garageDB.getVehicles().get(1).add(MotorCycle.build(1,name));
                    break;
                case 2:
                    garageDB.getVehicles().get(2).add(Car.build(2,name));
                    break;
                case 3:
                    garageDB.getVehicles().get(3).add(Truck.build(1,name));
                    break;

            }
        }



        Garage garage = new Garage(protocolGarage);
        garage.setVehicles(garageDB.getVehicles());
        garage.startWork(garageDB.getVehicles());
    }

    private static Protocol_Garage protocolGarage = new Protocol_Garage() {
        @Override
        public void fixed() {
            System.out.println("another vehicle fixed $$");
        }
    };

    public void build(int type, String model) {
        //
    }
}