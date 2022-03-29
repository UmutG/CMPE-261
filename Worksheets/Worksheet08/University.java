
import java.util.ArrayList;

public class University {

}

// SERVER-SIDE
class ServerTeacher {
	
	// port:     the port number to be allocated
	// students: list of the students having id and grade information 
	private int port;
	private ArrayList<Student> students;
	
	public ServerTeacher(int port, ArrayList<Student> students) {
		this.port = port;
		this.students = students;
	}
	
	// find the grade of the student whose id given, if student couldn't find then return -1
	public int findGrade(int id) {
	
		// *** FILL THIS METHOD FOR PART 1 *** //
	
	}

	// open the server listen for the student ask their grades
	public int respondStudent() {
		
		// *** FILL THIS METHOD FOR PART 2 *** //
		
	}
}

// CLIENT-SIDE
class ClientStudent {
	
	private int port; // the port number to be allocated
	
	public ClientStudent(int port) {
		this.port = port;
	}
	
	// open client and ask the student's grade to server
	public void requestGrade(int id) {
		
		// *** FILL THIS METHOD FOR PART 3 *** //
		
	}
		
}


// STUDENT OBJECT
class Student {
	private int id;
	private int grade;
	
	public Student(int id, int grade) {
		this.id = id;
		this.grade = grade;		
	}
	
	public int getId() {
		return id;
	}
	public int getGrade() {
		return grade;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
