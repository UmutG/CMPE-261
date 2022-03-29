import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

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
	}

	// Counting the students got higher grade than given threshold
	@Override
	public int countStudentsGetHigher(int threshold) throws RemoteException {
		// *** FILL THIS METHOD FOR PART 1 ***
		return 0;
	}

	@Override
	public void readLectureFromFile(String fileName) throws RemoteException {
		// *** FILL THIS METHOD FOR PART 2 ***
	}

	@Override
	public void saveLectureToFile(String fileName) throws RemoteException {
		// *** FILL THIS METHOD FOR PART 2 ***
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
	}

	// open the client side of the registry and request for counting the students who got higher than the threshold
	public int requestCountStudentsGetHigherGrade(int threshold) {
		// *** FILL THIS METHOD FOR PART 4 ***
		return 0;
	}

	// open the client side of the registry and request for saving the lecture into a serial file
	public void requestSaveFile(String fileName) {
		// *** FILL THIS METHOD FOR PART 4 ***
	}
}
