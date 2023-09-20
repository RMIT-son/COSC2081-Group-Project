package main.vehicle;

import main.container.Container;
import main.porttrip.Port;

import java.util.Collection;
import java.util.List;

public interface VehicleOperations {
    boolean find(int idNumber);
    boolean loadableContainer(Container container);

    public void loadContainer(Container container);
    public void unloadContainer(Container container);
    Container findingContainer(Container container);

    double countWeight();
    boolean checkPortWeightAvailibity(Port port);
    void movePort(Port port);
    void refuel();
    int checkConNumb();
    void createVehicle();

    void saveVechicle(Collection<Vehicle> vehicles);

    List<Vehicle> readVehicle();

    void updateVehicle();

    void deleteVehicle();


}
