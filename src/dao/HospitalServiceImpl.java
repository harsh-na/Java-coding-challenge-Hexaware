package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Appointment;
import myexceptions.PatientNumberNotFoundException;

public class HospitalServiceImpl implements IHospitalService {

	private Connection connection; // Initialized elsewhere

	public HospitalServiceImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Appointment getAppointmentById(int appointmentId) throws ClassNotFoundException {
		Appointment appointment = null;
		String query = "SELECT * FROM Appointment WHERE appointmentId = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
		String query = "SELECT * FROM Appointment WHERE patientId = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
		String query = "SELECT * FROM Appointment WHERE doctorId = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
		String query = "INSERT INTO Appointment (patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, appointment.getPatientId());
			stmt.setInt(2, appointment.getDoctorId());
			stmt.setString(3, appointment.getAppointmentDate());
			stmt.setString(4, appointment.getDescription());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateAppointment(Appointment appointment) throws ClassNotFoundException {
		String query = "UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, appointment.getPatientId());
			stmt.setInt(2, appointment.getDoctorId());
			stmt.setString(3, appointment.getAppointmentDate());
			stmt.setString(4, appointment.getDescription());
			stmt.setInt(5, appointment.getAppointmentId());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean cancelAppointment(int appointmentId) throws ClassNotFoundException {
		String query = "DELETE FROM Appointment WHERE appointmentId = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, appointmentId);
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
