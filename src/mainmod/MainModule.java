package mainmod;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import dao.HospitalServiceImpl;
import entity.Appointment;
import util.DBConnection;

public class MainModule {
	public static void main(String[] args) throws ClassNotFoundException {
		// Initialize the connection using the DBConnection utility
		Connection connection = DBConnection.getConnection();

		// Pass the connection to HospitalServiceImpl
		HospitalServiceImpl service = new HospitalServiceImpl(connection);

		Scanner sc = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			// Menu
			System.out.println("\n--- Hospital Management System ---");
			System.out.println("1. Get Appointments for a Patient");
			System.out.println("2. Get Appointments for a Doctor");
			System.out.println("3. Get Appointment by ID");
			System.out.println("4. Schedule an Appointment");
			System.out.println("5. Update an Appointment");
			System.out.println("6. Cancel an Appointment");
			System.out.println("7. Exit");
			System.out.print("Enter your choice: ");

			int choice = sc.nextInt(); // Get user's choice

			switch (choice) {
			case 1:
				// Get Appointments for a Patient
				System.out.print("Enter Patient ID: ");
				int patientId = sc.nextInt();
				List<Appointment> patientAppointments = service.getAppointmentsForPatient(patientId);

				if (patientAppointments.isEmpty()) {
					System.out.println("No appointments found for patient with ID: " + patientId);
				} else {
					System.out.println("Appointments for Patient ID: " + patientId);
					for (Appointment appointment : patientAppointments) {
						System.out.println(appointment);
					}
				}
				break;

			case 2:
				// Get Appointments for a Doctor
				System.out.print("Enter Doctor ID: ");
				int doctorId = sc.nextInt();
				List<Appointment> doctorAppointments = service.getAppointmentsForDoctor(doctorId);

				if (doctorAppointments.isEmpty()) {
					System.out.println("No appointments found for doctor with ID: " + doctorId);
				} else {
					System.out.println("Appointments for Doctor ID: " + doctorId);
					for (Appointment appointment : doctorAppointments) {
						System.out.println(appointment);
					}
				}
				break;

			case 3:
				// Get Appointment by ID
				System.out.print("Enter Appointment ID: ");
				int appointmentId = sc.nextInt();
				Appointment appointment = service.getAppointmentById(appointmentId);
				if (appointment != null) {
					System.out.println(appointment);
				} else {
					System.out.println("Appointment not found with ID: " + appointmentId);
				}
				break;

			case 4:
				// Schedule an Appointment
				System.out.println("Enter details to schedule a new appointment:");
				System.out.print("Patient ID: ");
				int newPatientId = sc.nextInt();
				System.out.print("Doctor ID: ");
				int newDoctorId = sc.nextInt();
				sc.nextLine(); // Consume leftover newline
				System.out.print("Appointment Date (YYYY-MM-DD): ");
				String date = sc.nextLine();
				System.out.print("Description: ");
				String description = sc.nextLine();

				Appointment newAppointment = new Appointment(0, newPatientId, newDoctorId, date, description);
				boolean scheduled = service.scheduleAppointment(newAppointment);
				System.out.println("Appointment Scheduled: " + scheduled);
				break;

			case 5:
				// Update an Appointment
				System.out.print("Enter Appointment ID to update: ");
				int updateAppointmentId = sc.nextInt();
				sc.nextLine(); // Consume newline
				Appointment appointmentToUpdate = service.getAppointmentById(updateAppointmentId);

				if (appointmentToUpdate != null) {
					System.out.print("Enter new description: ");
					String newDescription = sc.nextLine();
					appointmentToUpdate.setDescription(newDescription);
					boolean updated = service.updateAppointment(appointmentToUpdate);
					System.out.println("Appointment Updated: " + updated);
				} else {
					System.out.println("Appointment not found for update.");
				}
				break;

			case 6:
				// Cancel an Appointment
				System.out.print("Enter Appointment ID to cancel: ");
				int cancelAppointmentId = sc.nextInt();
				boolean canceled = service.cancelAppointment(cancelAppointmentId);
				System.out.println("Appointment Canceled: " + canceled);
				break;

			case 7:
				// Exit the program
				exit = true;
				System.out.println("Exiting... Goodbye!");
				break;

			default:
				System.out.println("Invalid choice! Please enter a valid option.");
			}
		}

		sc.close(); // Close the scanner when exiting
	}
}
