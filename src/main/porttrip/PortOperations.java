package main.porttrip;

import main.container.Container;
import main.porttrip.Port;

import java.util.Collection;

public interface PortOperations {

    double getCurrentContainerWeightAtPort();
    int getNumberOfContainers();
    int getNumberOfVehicles();
    boolean findContainer(int idNumber);
    boolean findVehicle(String vname);
    Collection<Container> loadContainerToPort(Container container);
    Collection<Container> unloadContainerFromPort(Container container);
    double distanceTo(Port otherPort);
    boolean canMoveTo(Port otherPort);
    void createPort();

    static Collection<Port> readPort() {
        return null;
    }

    void updatePort();
    void deletePort();
}
