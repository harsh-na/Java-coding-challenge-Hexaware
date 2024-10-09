package mainmod;

import java.util.List;

import dao.HospitalServiceImpl;
import entity.Appointment;
import myexceptions.PatientNumberNotFoundException;

public class MainModule {
	public static void main(String[] args) throws ClassNotFoundException {
		HospitalServiceImpl service = new HospitalServiceImpl();

		try {
//
//			// Get Appointments for a Patient
//			int patientId = 1;
//			List<Appointment> patientAppointments = service.getAppointmentsForPatient(patientId);
//
//			if (patientAppointments.isEmpty()) {
//				System.out.println("No appointments found for patient with ID: " + patientId);
//			} else {
//				System.out.println("Appointments for Patient ID: " + patientId);
//				for (Appointment appointment : patientAppointments) {
//					System.out.println(appointment);
//				}
//			}

			// Get Appointments for a Doctor
			int doctorId = 1;
			List<Appointment> doctorAppointments = service.getAppointmentsForDoctor(doctorId);

			if (doctorAppointments.isEmpty()) {
				System.out.println("No appointments found for doctor with ID: " + doctorId);
			} else {
				System.out.println("Appointments for Doctor ID: " + doctorId);
				for (Appointment appointment : doctorAppointments) {
					System.out.println(appointment);
				}
			}
//			// Test: Get Appointment by ID
//			Appointment appointment = service.getAppointmentById(1);
//			System.out.println(appointment);
//
//			// Test: Schedule an Appointment
//			Appointment newAppointment = new Appointment(0, 2, 3, "2024-10-10", "Annual check-up with cardiologist");
//			boolean scheduled = service.scheduleAppointment(newAppointment);
//			System.out.println("Appointment Scheduled: " + scheduled);
//
//			// Test: Update an Appointment
//			// Appointment newAppointment = new Appointment(2, 2, 2, "2024-10-10", "Annual
//			// check-up with cardiologist");
//			newAppointment.setDescription("Updated description");
//			boolean updated = service.updateAppointment(newAppointment);
//			System.out.println("Appointment Updated: " + updated);
//
//			// Test: Cancel an Appointment
//			boolean canceled = service.cancelAppointment(2);
//			System.out.println("Appointment Canceled: " + canceled);

		} catch (PatientNumberNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
