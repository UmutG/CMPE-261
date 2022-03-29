// Umut Geyik - 120200145 - CMPE 261.01/0102

package worksheet08;

// Input necessary libraries
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class University {

	/*
	 * for main block:
	 * if (args[0].equals == "server")
	 * 	open server
	 * else if (args[0].equals == "client")
	 * 	make the request
	 */
}

// SERVER-SIDE
class ServerTeacher {

	// port: the port number to be allocated
	// students: list of the students having id and grade information
	private int port;
	private ArrayList<Student> students;

	public ServerTeacher(int port, ArrayList<Student> students) {
		this.port = port;
		this.students = students;
	}

	// find the grade of the student whose id given, if student couldn't find then
	// return -1
	public int findGrade(int id) {

		// *** FILL THIS METHOD FOR PART 1 *** //
		if (id >= 0 && id <= students.size())
			return students.get(id).getGrade();
		else
			return -1;
	}

	// open the server listen for the student ask their grades
	public int respondStudent() throws IOException {

		// *** FILL THIS METHOD FOR PART 2 *** //
		// Open a connection from server to client with given port number and accept connection
		ServerSocket server = new ServerSocket(port);
		System.out.println("Connection is open. Waiting for a client...");
		Socket socket = server.accept();
		System.out.println("Client has arrived! (connection established).");
		
		// To get any request from client-side, open input stream
		InputStream input = socket.getInputStream();
		DataInputStream dInput = new DataInputStream(input);
		
		// Get given student ID and find the related grade
		int stuID = Integer.parseInt(dInput.readUTF().trim());
		int grade = findGrade(stuID);
		
		// Close every open stream, socket and connection
		dInput.close();
		input.close();	
		socket.close();
		server.close();
		
		// Return the student's grade
		return grade;
	}
}

// CLIENT-SIDE
class ClientStudent {

	private int port; // the port number to be allocated

	public ClientStudent(int port) {
		this.port = port;
	}

	// open client and ask the student's grade to server
	public void requestGrade(int id) throws UnknownHostException, IOException {

		// *** FILL THIS METHOD FOR PART 3 *** //
		// Open a connection from client to server with given port number
		// Since the server serving from the local machine, set the IP address as
		// localhost or 127.0.0.1
		Socket client = new Socket("localhost", port);
		
		// To send any request from client, create output stream
		OutputStream out = client.getOutputStream();
		DataOutputStream dOut = new DataOutputStream(out);
		dOut.writeUTF(String.valueOf(id) + "");
		
		// To get any response from server, create input stream
		InputStream input = client.getInputStream();
		DataInputStream dInput = new DataInputStream(input);
		System.out.println(dInput.readUTF());
		
		// Close all streams and connections
		dInput.close();
		input.close();
		dOut.close();
		out.close();
		client.close();
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
