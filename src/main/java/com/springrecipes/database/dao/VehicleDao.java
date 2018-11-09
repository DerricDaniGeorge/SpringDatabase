package com.springrecipes.database.dao;
import java.util.List;
import com.springrecipes.database.beans.Vehicle;

public interface VehicleDao {
	public void insert(Vehicle vehicle);
	public Vehicle findByVehicleNo(String vehicleNo);
	public void insertBatch(List<Vehicle> vehicles);
}
