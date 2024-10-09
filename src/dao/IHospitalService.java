package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Appointment;

public interface IHospitalService {

	// Method to retrieve an appointment by its ID
	Appointment getAppointmentById(int appointmentId) throws SQLException, ClassNotFoundException, RuntimeException;

	// Method to retrieve all appointments for a specific patient
	List<Appointment> getAppointmentsForPatient(int patientId) throws SQLException, ClassNotFoundException;

	// Method to retrieve all appointments for a specific doctor
	List<Appointment> getAppointmentsForDoctor(int doctorId) throws SQLException, ClassNotFoundException;

	// Method to schedule a new appointment
	boolean scheduleAppointment(Appointment appointment) throws SQLException, ClassNotFoundException;

	// Method to update an existing appointment
	boolean updateAppointment(Appointment appointment) throws SQLException, ClassNotFoundException;

	// Method to cancel an appointment by its ID
	boolean cancelAppointment(int appointmentId) throws SQLException, ClassNotFoundException;
}
