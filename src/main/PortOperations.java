package main;

import java.util.Collection;

public interface PortOperations {

    double getCurrentContainerWeight();
    int getNumberOfContainers();
    int getNumberOfVehicles();
    boolean addContainer(Container container);
    double distanceTo(Port otherPort);
    boolean canMoveTo(Port otherPort);
    void createPort(Port port);
    Collection<Port> readPort();
    void updatePort(Port updatedPort);
    void deletePort(Port portToDelete);
}
