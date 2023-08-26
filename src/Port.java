import java.util.Collection;

public class Port {
	protected int pNumber;
	protected String name;
	protected double latitude;
	protected double longitude;
	protected double storingCapacity;
	protected int numOfContainers;
	protected int numOfVehicles;
	protected Collection<Trip> trips;
	protected Collection<Container> containers;
	protected Collection<Vehicle> vehicles;

}
