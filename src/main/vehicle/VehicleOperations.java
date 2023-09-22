package main.vehicle;

import main.container.Container;
import main.porttrip.Port;

import java.util.Collection;
import java.util.List;

public interface VehicleOperations {
    boolean find(int idNumber);
    boolean loadableContainer(Container container);

    void loadContainer(Container container);
    void unloadContainer(Container container);
    Container findingContainer(Container container);

    double countWeight();
    boolean checkPortWeightAvailibity(Port port);
    void movePort(Port port);
    void refuel();
    int checkConNumb();
    void createVehicle();

    void saveVechicle(Collection<Vehicle> vehicles);



    void updateVehicle();

    void deleteVehicle();


}
