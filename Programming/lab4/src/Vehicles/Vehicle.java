package assemblyline.vehicles;

import java.util.Hashtable;
import java.util.Enumeration;

import assemblyline.utils.ValueOutOfRangeException;
import assemblyline.utils.NotEmptyException;
import assemblyline.utils.NotNullException;
import assemblyline.utils.ErrorMessages;
import assemblyline.utils.IO;

public class Vehicle implements Comparable<Vehicle> {
    /**
     * Counts how many cars have already been created
     */
    private static int counter = 0;

    /**
     * <p> Unique vehicle id </p>
     * <p> * Generated automatically </p>
     * <p> * Cannot be null </p>
     * <p> * Must be more than 0 </p>
     */
    private Integer id;
    /**
     * <p> Vehicle's name </p>
     * <p> * Cannot be null </p>
     * <p> * Cannot be an empty string </p>
     */
    private String name;
    /**
     * <p> Vehicle coordinates </p>
     * <p> * Cannot be null </p>
     */
    private Coordinates coordinates;
    /**
     * <p> Vehicle creation date </p>
     * <p> * Generated automatically </p>
     * <p> * Cannot be null </p>
     */
    private java.time.LocalDate creationDate;
    /**
     * <p> Vehicle engine power </p>
     * <p> * Must be more than 0 </p>
     */
    private int enginePower;
    /**
     * <p> Amount of wheels a vehicle has </p>
     * <p> * Must be more than 0 </p>
     */
    private int numberOfWheels;
    /**
     * <p> Vehicle type </p>
     * <p> * Cannot be null </p>
     */
    private VehicleType type;
    /**
     * <p> Vehicle fuel type </p>
     * <p> * Cannot be null </p>
     */
    private FuelType fuelType;

    // =============== Access to variables methods ===============
    public Integer getId() {
        return this.id;
    }
    public Coordinates getCoordinates() {
        return this.coordinates;
    }
    public int getEnginePower() {
        return enginePower;
    }
    public int getNumberOfWheels() {
        return numberOfWheels;
    }
    public VehicleType getVehicleType() {
        return type;
    }
    public FuelType getFuelType() {
        return fuelType;
    }
    // =============== Access to variables methods END ===============

    // =============== Check variable correctness ===============
    public static boolean isNameCorrect(String name) {
        if (name == null) return false;
        if (name.isBlank()) return false;
        return true;
    }
    public static boolean isCoordinatesCorrect(Coordinates coordinates) {
        if (coordinates == null) return false;
        return true;
    }
    public static boolean isEnginePowerCorrect(int enginePower) {
        if (enginePower <= 0) return false;
        return true;
    }
    public static boolean isNumberOfWheelsCorrect(int numberOfWheels) {
        if (numberOfWheels <= 0) return false;
        return true;
    }
    public static boolean isVehicleTypeCorrect(VehicleType vehicleType) {
        if (vehicleType == null) return false;
        return true;
    }
    public static boolean isFuelTypeCorrect(FuelType fuelType) {
        if (fuelType == null) return false;
        return true;
    }
    // =============== Check variable correctness END ===============

    // =============== Set variables ===============
    public void setName(String name) {
        if (!isNameCorrect(name)) {
            throw new NotEmptyException("Name");
        }
        this.name = name;
    }
    public void setCoordinates(Coordinates coordinates) {
        if (!isCoordinatesCorrect(coordinates)) {
            throw new NotNullException("Coordinates");
        }
        this.coordinates = coordinates;
    }
    public void setEnginePower(int enginePower) {
        if (!isEnginePowerCorrect(enginePower)) {
            throw new ValueOutOfRangeException(0, false, "Engine Power");
        }
        this.enginePower = enginePower;
    }
    public void setNumberOfWheels(int numberOfWheels) {
        if (!isNumberOfWheelsCorrect(numberOfWheels)) {
            throw new ValueOutOfRangeException(0, false, "Number of wheels");
        }
        this.numberOfWheels = numberOfWheels;
    }
    public void setVehicleType(VehicleType vehicleType) {
        if (!isVehicleTypeCorrect(vehicleType)) {
            throw new NotNullException("Vehicle type");
        }
        this.type = vehicleType;
    }
    public void setFuelType(FuelType fuelType) {
        if (!isFuelTypeCorrect(fuelType)) {
            throw new NotNullException("Fuel type");
        }
        this.fuelType = fuelType;
    }
    // =============== Set variables END ===============

    /**
     * Asks for user input to update given vehicle's fields.
     * @param vehicle
     * @param ifLower only update fields if given value is lower
     */
    public void updateData(Hashtable<String, Object> listOfParams, boolean ifLower) {
        Enumeration keys = listOfParams.keys();

        //I really should consider finding a better way to do this...
        while (keys.hasMoreElements()) {
            String k = (String)keys.nextElement();
            Object v = listOfParams.get(k);
            switch(k) {
                case "name":
                    this.setName((String)v);
                    IO.print("Name updated%n");
                    break;
                case "enginePower":
                    int enginePower = (int)v;
                    if (ifLower) {
                        if (this.getEnginePower() <= enginePower) {
                            break;
                        }
                    }
                    this.setEnginePower(enginePower);
                    IO.print("Engine power updated%n");
                    break;
                case "numberOfWheels":
                    int numberOfWheels = (int)v;
                    if (ifLower) {
                        if (this.getNumberOfWheels() <= numberOfWheels) {
                            break;
                        }
                    }
                    this.setNumberOfWheels(numberOfWheels);
                    IO.print("Number of wheels updated%n");
                    break;
                case "vehicleType":
                    this.setVehicleType((VehicleType)v);
                    IO.print("Vehicle type updated%n");
                    break;
                case "fuelType":
                    this.setFuelType((FuelType)v);
                    IO.print("Fuel type updated%n");
                    break;
                case "x":
                    double x = (double)v;
                    if (ifLower) {
                        if (coordinates.getX() <= x) {
                            break;
                        }
                    }
                    coordinates.setX(x);
                    IO.print("X updated%n");
                    break;
                case "y":
                    long y = (long)v;
                    if (ifLower) {
                        if (coordinates.getY() <= y) {
                            break;
                        }
                    }
                    coordinates.setY(y);
                    IO.print("Y updated%n");
                    break;
            }
        }
    }

    /**
     * Constructor.
     * @param name <p>* Cannot be null</p><p>* Cannot be an empty string</p>
     * @param coordinates <p>* Cannot be null</p>
     * @param enginePower <p>* Must be more than 0</p>
     * @param numberOfWheels <p>* Must be more than 0</p>
     * @param vehicleType <p>* Cannot be null</p>
     * @param fuelType <p>* Cannot be null</p>
     */
    public Vehicle(String name, Coordinates coordinates, int enginePower,
            int numberOfWheels, VehicleType vehicleType, FuelType fuelType) {
        if (!isNameCorrect(name)) throw new NotEmptyException("Name");
        if (!isCoordinatesCorrect(coordinates)) throw new NotNullException("Coordinates");
        if (!isEnginePowerCorrect(enginePower)) throw new ValueOutOfRangeException(1, false, "Engine Power");
        if (!isNumberOfWheelsCorrect(numberOfWheels)) throw new ValueOutOfRangeException(1, false, "Number of wheels");
        if (!isVehicleTypeCorrect(vehicleType)) throw new NotNullException("Vehicle type");
        if (!isFuelTypeCorrect(fuelType)) throw new NotNullException("Fuel type");

        counter += 1;

        this.id = counter;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.LocalDate.now();
        this.enginePower = enginePower;
        this.numberOfWheels = numberOfWheels;
        this.type = vehicleType;
        this.fuelType = fuelType;
    }

    @Override
	public int compareTo(Vehicle e) {
		return this.getId() - e.getId();
	}

    @Override
    public String toString() {
        String toReturn = String.format("%s - %s #%d%nFuel type: %s%nLocation: %s%nEngine Power: %d%nNumber of wheels: %d%nCreation date: %s",
            this.name, this.type, this.id, this.fuelType, this.coordinates.toString(), this.enginePower, this.numberOfWheels, this.creationDate.toString());

        return toReturn;
    }
}