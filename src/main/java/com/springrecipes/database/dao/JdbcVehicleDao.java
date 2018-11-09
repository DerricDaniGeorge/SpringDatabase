package com.springrecipes.database.dao;

import com.springrecipes.database.beans.Vehicle;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
public class JdbcVehicleDao implements VehicleDao{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource=dataSource;
	}
/*	public void insert(Vehicle vehicle) {
		String sql="INSERT INTO vehicle(VEHICLE_NO,COLOR,WHEEL,SEAT) VALUES(?,?,?,?)";
		Connection conn=null;
		try {
			conn=dataSource.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,vehicle.getVehicleNo());
			ps.setString(2, vehicle.getColor());
			ps.setInt(3, vehicle.getWheel());
			ps.setInt(4, vehicle.getSeat());
			System.out.println(ps.executeUpdate()+" rows updated in table");
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public Vehicle findByVehicleNo(String vehicleNo) {
		String sql="SELECT * FROM vehicle WHERE VEHICLE_NO=?";
		Connection conn=null;
		Vehicle vehicle=null;
		try {
			conn=dataSource.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, vehicleNo);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				vehicle=new Vehicle(rs.getString("VEHICLE_NO"),rs.getString("COLOR"),rs.getInt("WHEEL"),rs.getInt("SEAT"));
			}
			ps.close();
			rs.close();
			return vehicle;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
				conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return vehicle;
	} */
	public void insert(Vehicle vehicle) {
		String sql="INSERT INTO vehicle(VEHICLE_NO,COLOR,WHEEL,SEAT) VALUES (?,?,?,?)";
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql,vehicle.getVehicleNo(),vehicle.getColor(),vehicle.getWheel(),vehicle.getSeat());
		System.out.println("Row inserted");
	}
	public Vehicle findByVehicleNo(String vehicleNo) {
		String sql="SELECT * FROM vehicle WHERE vehicle_no=?";
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		final Vehicle vehicle=new Vehicle();
		jdbcTemplate.query(sql,new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException{
				vehicle.setVehicleNo(rs.getString("VEHICLE_NO"));
				vehicle.setColor(rs.getString("COLOR"));
				vehicle.setWheel(rs.getInt("WHEEL"));
				vehicle.setSeat(rs.getInt("SEAT"));
			}
		},vehicleNo);
		return vehicle;
	}
	public void insertBatch(final List<Vehicle> vehicles) {
		String sql="INSERT INTO vehicle(VEHICLE_NO,COLOR,WHEEL,SEAT) VALUES (?,?,?,?)";
		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
		jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
			public int getBatchSize() {
				return vehicles.size();
			}
			public void setValues(PreparedStatement ps,int i) throws SQLException{
				Vehicle vehicle=vehicles.get(i);
				ps.setString(1, vehicle.getVehicleNo());
				ps.setString(2, vehicle.getColor());
				ps.setInt(3, vehicle.getWheel());
				ps.setInt(4, vehicle.getSeat());
			}
		});
	}
}
