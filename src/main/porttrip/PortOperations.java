package main.porttrip;

import main.container.Container;
import main.porttrip.Port;

import java.util.Collection;

public interface PortOperations {

    double getCurrentContainerWeightAtPort();
    int getNumberOfContainers();
    int getNumberOfVehicles();
    boolean findContainer(int idNumber);
    boolean findVehicle(int idNumber);
    Collection<Container> loadContainerToPort(Container container, int idNumber);
    Collection<Container> unloadContainerFromPort(Container container);
    double distanceTo(Port otherPort);
    boolean canMoveTo(Port otherPort);
    void createPort();
    Collection<Port> readPort();
    void updatePort();
    void deletePort();
}
