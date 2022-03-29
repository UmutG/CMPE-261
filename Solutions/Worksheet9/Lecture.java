// Umut Geyik - 120200145 - CMPE 261.01/0102

package worksheet09;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class Lecture implements Serializable {

	private String name;
	private Student[] students;

	public Lecture(String name, Student[] students) {
		this.name = name;
		this.students = students;
	}

	public String getName() {
		return name;
	}
	public Student[] getStudents() {
		return students;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStudents(Student[] students) {
		this.students = students;
	}

	public Lecture initLecture() {
		Student[] tmp = {new Student(1, "Bilgi", 100)};
		return new Lecture("CMPE261", tmp);
	}
}

//STUDENT CLASS
class Student {

	private int id;
	private String name;
	private int grade;

	public Student(int id, String name, int grade) {
		this.id = id;
		this.name = name;
		this.grade = grade;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getGrade() {
		return grade;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
}


// =============================
//				API
// =============================
interface RMIInterface extends Remote {
	public void removeStudent(int id) throws RemoteException;
	public int countStudentsGetHigher(int threshold) throws RemoteException;
	public void readLectureFromFile(String fileName) throws RemoteException;
	public void saveLectureToFile(String fileName) throws RemoteException;
}

class RMI implements RMIInterface {

	private Lecture lecture;

	// Remove the student (whose id is given in parameter) from lecture
	@Override
	public void removeStudent(int id) throws RemoteException {
		// *** FILL THIS METHOD FOR PART 1 ***
		/* First, get the original student array. Second, create a new array
		 * with original student array's one less size. Do not add elements yet.
		 * Then, call the arraycopy function from system in order to copy elements
		 * except the given id (let's say index). In that case, copy process will take
		 * into two parts: Get values until given index and then get the values
		 * after the given index. That's why we have to call it twice.
		 */
		Student[] oldStudents = lecture.getStudents();
		Student[] newStudents = new Student[lecture.getStudents().length - 1];
		System.arraycopy(oldStudents, 0, newStudents, 0, id);
		System.arraycopy(oldStudents, id + 1, newStudents, id, oldStudents.length - 1);
		System.out.println("New students: " + Arrays.toString(newStudents));
		
	}

	// Counting the students got higher grade than given threshold
	@Override
	public int countStudentsGetHigher(int threshold) throws RemoteException {
		// *** FILL THIS METHOD FOR PART 1 ***
		// Create a int variable whose going to count higher grades
		int count = 0;
		// Get student's array
		Student stu [] = lecture.getStudents();
		// Check every student's grade whether its higher than the given threshold
		// Then increase the count by one
 		for (int i = 0; i < lecture.getStudents().length; i++) {
			if (stu[i].getGrade() > threshold)
				count++;
		}
 		
 		// Return the count 
		return count;
	}

	@Override
	public void readLectureFromFile(String fileName) throws RemoteException {
		// *** FILL THIS METHOD FOR PART 2 ***
		// Get the file
		File lectureFile = new File(fileName);
		try {
			// Since we need to read the given file, create input streams
			// Then, read the given file from the Lecture object
			FileInputStream fis = new FileInputStream(lectureFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Lecture readLecture = (Lecture) ois.readObject();
			// Print out every value from the readLecture's student array
			for (Student stu: readLecture.getStudents())
				System.out.println(stu);
			 
		} catch (FileNotFoundException e) {
			System.err.println("File Error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error while reading file: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Error Occurred: " + e.getMessage());
		} 
	}

	@Override
	public void saveLectureToFile(String fileName) throws RemoteException {
		// *** FILL THIS METHOD FOR PART 2 ***
		// Get the file
		File lectureFile = new File(fileName);
		try {
			// Since we are going to write a file, let's call output streams
			FileOutputStream fos = new FileOutputStream(lectureFile);
			ObjectOutputStream outputs = new ObjectOutputStream(fos);
			
			// After we created stream objects, let's write the lecture object
			// to the given file. Then, close all streams.
			outputs.writeObject(lecture);
			System.out.println("File has been saved.");
			outputs.close();
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

//=============================
//			SERVER-SIDE
//=============================
class UniversityServer {

	// rmi:			rmi object for providing api
	// bindAddress: binding address of the stub(registry point)
	private RMI rmi;
	private String bindAddress;

	public UniversityServer(RMI rmi, String bindAddress) {
		this.rmi = rmi;
		this.bindAddress = bindAddress;
	}

	// open the server and wait clients to make requests
	public void openServer() {
		// *** FILL THIS METHOD FOR PART 3 ***
		try {
			rmi = (RMI) UnicastRemoteObject.exportObject(rmi, 0);
			Registry regis = LocateRegistry.getRegistry();
			regis.bind(bindAddress, rmi);
			System.out.println(bindAddress + "=> OPEN.");
			
		} catch (RemoteException e) {
			System.out.println("Error from Server: " + e.getMessage());
		} catch (AlreadyBoundException e) {
			System.out.println("Error from Server: " + e.getMessage());
		}
	}

}

//=============================
//			CLIENT-SIDE
//=============================
class TeacherClient {

	// api:			provides the methods for clients
	// bindAddress: binding address of the stub(registry point)
	private RMIInterface api;
	private String bindAddress;

	public TeacherClient(String bindAddress) {
		this.bindAddress = bindAddress;
	}

	// open the client side of the registry and request for removing the student from the lecture
	public void requestRemoveStudent(int id) {
		// *** FILL THIS METHOD FOR PART 4 ***
		// To make request from client-side, we have to create a registry object and look up
		// for the given address whether its operational. Then, client can make a request.
		// Here, we want to remove a student with given id from the lecture 
		try {
			Registry regis = LocateRegistry.getRegistry("localhost");
			api = (RMIInterface) regis.lookup(bindAddress);
			api.removeStudent(id);
			
		} catch (Exception e) {
			System.err.println("Error from Client: " + e.getMessage());
		}
		
	}

	// open the client side of the registry and request for counting the students who got higher than the threshold
	public int requestCountStudentsGetHigherGrade(int threshold) {
		// *** FILL THIS METHOD FOR PART 4 ***
		// Let's count all the grades which higher than given threshold
		// I wanted to create an int variable, set it to returned number
		// from method and then return it in the end of the method.
		int countHigher = 0;
		try {
			Registry regis = LocateRegistry.getRegistry("localhost");
			api = (RMIInterface) regis.lookup(bindAddress);
			countHigher = api.countStudentsGetHigher(threshold);
			
		} catch (Exception e) {
			System.err.println("Error from Client: " + e.getMessage());
		}
		
		return countHigher;
	}

	// open the client side of the registry and request for saving the lecture into a serial file
	public void requestSaveFile(String fileName) {
		// *** FILL THIS METHOD FOR PART 4 ***
		// Here, we want to save students' info into given file.
		// Let's call the related function and get the job done.
		try {
			Registry regis = LocateRegistry.getRegistry("localhost");
			api = (RMIInterface) regis.lookup(bindAddress);
			api.saveLectureToFile(fileName);
		} catch (Exception e) {
			System.err.println("Error from Client: " + e.getMessage());
		}
	}
}
