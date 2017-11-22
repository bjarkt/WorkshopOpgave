import Acq.IBuilding;
import Acq.ISensor;
import Business.BusinessFacade;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BusinessFacade businessFacade = new BusinessFacade();
        Scanner sc = new Scanner(System.in);

        System.out.println("New Building: enter a name, address, city");
        String name = sc.nextLine();
        String address = sc.nextLine();
        String city = sc.nextLine();
        businessFacade.addBuilding(name, address, city);

        System.out.println("Add sensors to building " + name);
        System.out.println("Enter unit, and how many sensors");
        String unit = sc.next();
        int howMany = sc.nextInt();
        businessFacade.addSensor(name, unit, howMany);

        System.out.println("Current sensors:");
        for (int i = 0; i < businessFacade.getSensorsForBuilding(name).size(); i++) {
            System.out.println(i + ": " + businessFacade.getSensorsForBuilding(name).get(i));
        }

        for (ISensor iSensor : businessFacade.getSensorsForBuilding(name)) {
            System.out.println("Add measurements to sensors, for building " + name);
            System.out.println("Enter measurement, and which sensor (see list above)");
            double measurement = sc.nextDouble();
            int whichSensor = sc.nextInt();
            businessFacade.addMeasurement(name, measurement, whichSensor);
        }

        System.out.println(businessFacade.getBuildings());
        for (IBuilding iBuilding : businessFacade.getBuildings()) {
            businessFacade.getSensorsForBuilding(iBuilding.getName());
            System.out.println(iBuilding.getDataCollection());
        }

    }
}
