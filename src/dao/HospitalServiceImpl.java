package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Appointment;
import myexceptions.PatientNumberNotFoundException;
import util.DBConnection;

public class HospitalServiceImpl implements IHospitalService {

	@Override
	public Appointment getAppointmentById(int appointmentId) throws ClassNotFoundException {
		Appointment appointment = null;
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Appointment WHERE appointmentId = ?")) {
			stmt.setInt(1, appointmentId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				appointment = new Appointment(rs.getInt("appointmentId"), rs.getInt("patientId"), rs.getInt("doctorId"),
						rs.getString("appointmentDate"), rs.getString("description"));
			} else {
				throw new PatientNumberNotFoundException("Appointment with ID " + appointmentId + " not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appointment;
	}

	@Override
	public List<Appointment> getAppointmentsForPatient(int patientId) throws ClassNotFoundException {
		List<Appointment> appointments = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Appointment WHERE patientId = ?")) {
			stmt.setInt(1, patientId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				appointments.add(new Appointment(rs.getInt("appointmentId"), rs.getInt("patientId"),
						rs.getInt("doctorId"), rs.getString("appointmentDate"), rs.getString("description")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appointments;
	}

	@Override
	public List<Appointment> getAppointmentsForDoctor(int doctorId) throws ClassNotFoundException {
		List<Appointment> appointments = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Appointment WHERE doctorId = ?")) {
			stmt.setInt(1, doctorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				appointments.add(new Appointment(rs.getInt("appointmentId"), rs.getInt("patientId"),
						rs.getInt("doctorId"), rs.getString("appointmentDate"), rs.getString("description")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appointments;
	}

	@Override
	public boolean scheduleAppointment(Appointment appointment) throws ClassNotFoundException {
		boolean success = false;
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(
						"INSERT INTO Appointment (patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?)")) {
			stmt.setInt(1, appointment.getPatientId());
			stmt.setInt(2, appointment.getDoctorId());
			stmt.setString(3, appointment.getAppointmentDate());
			stmt.setString(4, appointment.getDescription());
			success = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean updateAppointment(Appointment appointment) throws ClassNotFoundException {
		boolean success = false;
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(
						"UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?")) {
			stmt.setInt(1, appointment.getPatientId());
			stmt.setInt(2, appointment.getDoctorId());
			stmt.setString(3, appointment.getAppointmentDate());
			stmt.setString(4, appointment.getDescription());
			stmt.setInt(5, appointment.getAppointmentId());
			success = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean cancelAppointment(int appointmentId) throws ClassNotFoundException {
		boolean success = false;
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM Appointment WHERE appointmentId = ?")) {
			stmt.setInt(1, appointmentId);
			success = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
}
