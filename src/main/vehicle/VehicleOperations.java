package main.vehicle;

import main.container.Container;
import main.porttrip.Port;

import java.util.Collection;
import java.util.List;

public interface VehicleOperations {
    boolean find(int idNumber);
    boolean loadableContainer(Container container);

    public void loadContainer(Container container);
    public void unloadContainer(int cNumber);
    Container findingContainer(int cNumber);

    double countWeight();
    boolean checkPortWeightAvailibity(Port port);
    void movePort(Port port);
    void refuel();
    int checkConNumb();
    void createVehicle(Vehicle vehicle);

    void saveVechicle(Collection<Vehicle> vehicles);

    List<Vehicle> readVehicle();

    void updatePort(Vehicle updatedVehicle);

    void deleteVehicle(Vehicle deletedVehicle);


}
