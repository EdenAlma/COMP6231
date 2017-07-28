package server;
import common.*;
import PackageManager.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;
import org.omg.CORBA.ORB;



/**
 * This class implements the methods described by the ManagerClient interface and includes other 
 * methods which aid in searching for records and returning a readable display of the records
 * stored within the CenterServer system (in the HashMap)
 * @author Eden
 *
 */
public class CenterServer extends ManagerClientPOA{

	
	ORB orb = null;
	Log server_log = null; //log object meant for logging events on a given server 
	private static final long serialVersionUID = 62151818721421736L;
	private String serverName;  //server name LVL MTL or DDO
	private int recordCount = 0; //a total count of the records created on the server
	private int studentNum;			//a number used to generate record IDs
	private int teacherNum;			//a number used to generate record IDs
	private HashMap<Character, ArrayList<Record>> recordMap = new HashMap<Character, ArrayList<Record>>();  //HashMap which stores records
	Semaphore mutex = new Semaphore(1);			//semaphore which ensures that accesses to the HashMap is atomic 


	/**
	 * 
	 * @param centName name of center LVL DDO or MTL
	 * @param centNum number used for creating record IDs
	 * @throws SecurityException	
	 * @throws IOException
	 */
	protected CenterServer(String centName, int centNum) throws SecurityException, IOException {  

		super();
		studentNum = teacherNum = centNum;  //setting up the numbers used to create record IDs
		serverName = centName;				//setting server name
		setUpHashMap();						//Initializing HashMap
		server_log = new Log(serverName + ".txt");  //Initializing log object

		Thread t1 = new Thread(new Runnable(){	//running thread which will publish record counts using UDP/sockets 
			public void run(){
				sendRecordCount(); 
			}
		});
		
		Thread t2 = new Thread(new Runnable(){	//running thread which will receive records from other servers
			public void run(){
				recieveRecord(); 
			}
		});
		t1.start();
		t2.start();
		server_log.logger.info(centName + " server in running"); //log of server running
	}


	/**
	 * methods used to initialize HashMap
	 */
	private void setUpHashMap(){		//sets up hash map in which the keys are the letters of the alphabet (capital)
		Character a = new Character('A');
		for(int i = 0; i <= 25; i++){  // A --> Z each getting an ArrayList
			recordMap.put(a, new ArrayList<Record>());  //adding ArrayLists to HashMap (values)
			a++;
		}
	}


	/**
	 * Method used to create Teacher record
	 */
	public String createTRecord(String fname, String lname, String address, String phone, String special,
			String location, String manID){

		if(!(location.equals("LVL")||location.equals("MTL")||location.equals("DDO"))){     //ensuring that location is valid
			server_log.logger.info(manID + " has failed in creating a teacher record");		//log invalid record creation
			return "Teacher record creation failed!";}  //return message for client

		try {
			mutex.acquire();   //Acquire mutex to ensure mutual exclusion 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String recID = "TR" + teacherNum;   //creating record ID
		TeacherRecord t  = new TeacherRecord(recID, fname, lname, address, phone, special, location);  //Teacher record constructor
		ArrayList<Record> temp = recordMap.get(lname.charAt(0));			//get first character of last name for placing record in HashMap
		temp.add(t);		//add record into ArrayList from HashMap
		teacherNum++;		//increment number
		recordCount++;		//increment record count
		server_log.logger.info(manID + " created a teacher record ID:" + recID);	//log record creation on server
		mutex.release();	//release mutex
		return recID; //if success --> return user the new record ID
	}


	/**
	 * Identical to the method above (student record instead of teacher record
	 */
	public String createSRecord(String fname, String lname, String crsReg, String status, String statusDate,
			String manID){

		if(!(status.equals("active")||status.equals("inactive"))){
			server_log.logger.info(manID + " has failed in creating a student record");
			return "Student record creation failed!";}

		//Check validity of input before taking mutex

		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String recID = "SR" + studentNum;
		StudentRecord s  = new StudentRecord(recID, fname, lname, crsReg, status, statusDate);
		ArrayList<Record> temp = recordMap.get(lname.charAt(0));
		temp.add(s);
		studentNum++;
		recordCount++;
		server_log.logger.info(manID + " created a student record ID:" + recID);
		mutex.release();
		return recID;  //return "Operation succeeded
	}


	/**
	 * method which gets record counts from other servers (using udp) and returns information
	 * to the client 
	 */
	public String getRecordCounts(String manID){

		int recieve1address = 0; //variables to be used for receiving packets from other servers 
		int recieve2address = 0;	
		String response = null;
		DatagramSocket aSocket1 = null;			//sockets to be used later
		DatagramSocket aSocket2 = null;
		byte[]buffer1 = new byte[200];			//byte arrays for DatagramPackets
		byte[]buffer2 = new byte[200];


		/*
		 *switch statement for setting up sockets according to the server name
		 */
		switch(serverName){
		case("LVL"):
		recieve1address = 7001;
		recieve2address = 7005;
		break;
		case("DDO"):
		recieve1address = 7002;
		recieve2address = 7004;
		break;
		case("MTL"):
		recieve1address = 7003;
		recieve2address = 7006;
		break;
		default:  //case in which server name is invalid
			System.out.println("No server specifications available for " + serverName);
			System.exit(1);
		}

		try {

			aSocket2 = new DatagramSocket(recieve2address);  //creating DatagramSockets 
			aSocket1 = new DatagramSocket(recieve1address);		
			DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length);	//Creating packets 
			DatagramPacket packet2 = new DatagramPacket(buffer2, buffer2.length);
			aSocket1.receive(packet1);		//Receiving packets 
			aSocket2.receive(packet2);
			mutex.acquire();		//mutex for ensuring that local records will not be created before client receives record count 
			String reply1 = new String(packet1.getData()).trim();
			String reply2 = new String(packet2.getData()).trim();  //getting string replies from packets 
			response = serverName + " " + this.recordCount + ", ";
			response += reply1 +", ";
			response += reply2 +".";		//constructing record count summary for client 




		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		server_log.logger.info(manID + " has retrieved the total record count");	//log of record count retrieval activity 
		mutex.release();
		return response;		//return summary to client
	}


	/**
	 * method used to edit records 
	 */
	public String editRecord(String recID, String fieldName, String newValue, String manID){


		try {
			mutex.acquire();		//mutex to prevent creation/editing of records while this function is active 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Record temp = findRecord(recID); //find corresponding record
		
		if(temp == null){     
			server_log.logger.info(manID + " has failed at editing a student record (record not found)"); //log activity 
			mutex.release();
			return "Student record editing failed! (record not found)";}  //send message to client

		if(recID.startsWith("TR")){	//case of Teacher record

			TeacherRecord tempT =  (TeacherRecord) temp;

			switch(fieldName){		//switch for finding which field to edit

			case("address"):
				
			tempT.setAddress(newValue);
			server_log.logger.info(manID + " edited a student record ID: " + recID);
			mutex.release();
			return "Student record has been edited ID: " + recID;
			
			case("phone"):
				
			tempT.setPhone(newValue);
			server_log.logger.info(manID + " edited a student record ID: " + recID);
			mutex.release();
			return "Student record has been edited ID: " + recID;
			
			case("specialization"):
				
			tempT.setSpecialization(newValue);
			server_log.logger.info(manID + " edited a student record ID: " + recID);
			mutex.release();
			return "Student record has been edited ID: " + recID;
			
			case("location"):
				
				if(!(newValue.equals("LVL")||newValue.equals("MTL")||newValue.equals("DDO"))){	//invalid new value
					server_log.logger.info(manID + " has failed at editing a student record (invalid new value)");
					mutex.release();
					return "Student record editing failed! (invalid new value)";}
				else{
					tempT.setLocation(newValue);
					server_log.logger.info(manID + " edited a student record ID: " + recID);
					mutex.release();
					return "Student record has been edited ID: " + recID;
				}
			
			default: //case where field name is not valid
				
				server_log.logger.info(manID + " has failed at editing a student record (invalid field name)");  //log of failure
				mutex.release();
				return "Student record editing failed! (invalid field name)";
			}

		}
		else if(recID.startsWith("SR")){   //same as scenario above (with student instead of teacher record)
			StudentRecord tempS =  (StudentRecord) temp;

			switch(fieldName){

			case("courses registered"):
				
			tempS.setCoursesRegistered(newValue);	
			server_log.logger.info(manID + " edited a teacher record ID: " + recID);
			mutex.release();
			return "Teacher record has been edited ID: " + recID;
			
			case("status"):
				
				if(!(newValue.equals("active")||newValue.equals("inactive"))){
					server_log.logger.info(manID + " has failed at editing a teacher record (invalid new value)");
					mutex.release();
					return "Teacher record editing failed! (invalid new value)";
				}else{
					tempS.setStatus(newValue);
					server_log.logger.info(manID + " edited a teacher record ID: " + recID);
					mutex.release();
					return "Teacher record has been edited ID: " + recID;
				}
			
			case("status date"):
				
			server_log.logger.info(manID + " edited a teacher record ID: " + recID);
			mutex.release();
			return "Teacher record has been edited ID: " + recID;
			
			default:
				
				server_log.logger.info(manID + " has failed at editing a teacher record (invalid field name)");
				mutex.release();
				return "Teacher record editing failed! (invalid field name)";	
			}
		}
		else{
			server_log.logger.info(manID + " has failed at editing a record (invalid record ID)");
			mutex.release();
			return "Record editing failed! (invalid record ID)";		}

	}


	/**
	 * method used for displaying all of the records on a given server object 
	 */
	public String displayAllRecords(String manID){

		try {
			mutex.acquire();   //ensure that records are not added/edited while display is prepared 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String output = "";
		for(Entry<Character, ArrayList<Record>> a : recordMap.entrySet()){			//Iterate through HashMap

			if(a.getValue().isEmpty())continue;
			ArrayList<Record> b = a.getValue();
			for(Record r : b){		//iterate through ArrayList
				output += r.toString();		//construct display string
				output += "\n";
			}
		}

		server_log.logger.info(manID + " has displayed all records");  //log of activity
		mutex.release();
		return output;
	}


	/**
	 * method used to display single record (all details)
	 */
	public String displayRecord(String manID, String recID){


		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Record temp = findRecord(recID);

		if(temp == null){
			server_log.logger.info(manID + " has failed at displaying record (record not found)");
			mutex.release();
			return "Record " + recID + " was not found";}

		if(recID.startsWith("TR")){
			TeacherRecord tempT =  (TeacherRecord) temp;
			server_log.logger.info(manID + " has displayed record " + recID);
			mutex.release();
			return tempT.toStringT();
		}
		else if(recID.startsWith("SR")){
			StudentRecord tempS =  (StudentRecord) temp;
			server_log.logger.info(manID + " has displayed record " + recID);
			mutex.release();
			return tempS.toStringS();
		}

		return "null";
	}


	/**
	 * method used to publish record count in server //runs on thread
	 */
	private void sendRecordCount(){

		int reply1address = 0;		//addresses for sending out info
		int reply2address = 0;
		int socketNum = 0;	//socket for sending


		//switch statement for setting up ports/sockets 
		switch(serverName){
		case("LVL"):
			
		socketNum = 6790;
		reply1address = 7003;
		reply2address = 7004;
		break;
		
		case("DDO"):
			
		socketNum = 6791;
		reply1address = 7005;
		reply2address = 7006;
		break;
		
		case("MTL"):
			
		socketNum = 6789;
		reply1address = 7001;
		reply2address = 7002;
		break;
		
		default:  //scenario where server name is invalid ---> quit
			System.out.println("No server specifications available for " + serverName);
			System.exit(1);

		}

		DatagramSocket aSocket = null;	//socket for sending

		try {

			aSocket = new DatagramSocket(socketNum);

			while(true){			//infinite loop (on thread) for publishing data
				
				mutex.acquire();	//no new records will be added while data is being prepared 
				String replyString = serverName + " " + recordCount;		//string which is to be sent
				byte[] replyByte = replyString.getBytes();					//conver to byte array
				DatagramPacket reply1 = new DatagramPacket(replyByte, replyByte.length, InetAddress.getLocalHost(), reply1address);	//packet for sending
				DatagramPacket reply2 = new DatagramPacket(replyByte, replyByte.length, InetAddress.getLocalHost(), reply2address);
				aSocket.send(reply1);
				aSocket.send(reply2);		//sending packets 
				mutex.release();			//release mutex
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(aSocket != null){aSocket.close();}
		}

	}


	/**
	 *"Helper" method used to retrieve record out of HashMap according to record ID
	 *uses brute force to iterate through HashMap/ArrayLists
	 */
	private Record findRecord(String recID){

		Character a = new Character('A');  //for iterating through HashMap
		
		for(int i = 0; i <= 25; i++){
			
			ArrayList<Record> temp = recordMap.get(a);
			
			for(int x = 0; x < temp.size(); x++){
				if(temp.get(x).getRecordID().equals(recID)){return temp.get(x);}  //if found
			}
			
			a++;
		}
		return null;	//if not found
	}

	
	/**
	 * setting up ORB object for remote object
	 */
	public void setORB(ORB orb_val) {
		orb = orb_val; 
	}

	
	/**
	 * method which transfers Record from one remote obj to another
	 */
	@Override
	public String transferRecord(String recID, String toServer, String manID) {
		
		int sendaddress = 0; //variables to be used for packets from other servers
		int recieveaddress = 0;
		DatagramSocket aSocket = null;			//sockets to be used later
		byte[]buffer = new byte[1024];			//byte array for DatagramPackets
		ByteArrayOutputStream outputstream = new ByteArrayOutputStream(); //stream to output object as byte array
		ObjectOutputStream os = null;
		
		
		try {
			os = new ObjectOutputStream(outputstream);  //connect object stream to byte array
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(toServer.equals(this.serverName)){	//invalid call
			server_log.logger.info(manID + " has failed to transfer " + recID + " (invalid parameters)");
			return "Operation failed (invalid paramaters)";
		}
		
		//switch for setting up sockets (sending)
		switch(serverName){
		case("LVL"):
			sendaddress = 9004;
		break;
		case("DDO"):
			sendaddress = 9005;
		break;
		case("MTL"):
			sendaddress = 9006;
		break;
		default:  //case in which server name is invalid
			
			System.out.println("No server specifications available for " + serverName);
			server_log.logger.info(manID + " has failed to transfer " + recID + " (invalid parameters)");
			return "Operation failed (invalid paramaters)";
		}
		
		//switch for setting up ports (receiving)
		switch(toServer){
		case("LVL"):
			recieveaddress = 9001;
		break;
		case("DDO"):
			recieveaddress = 9002;
		break;
		case("MTL"):
			recieveaddress = 9003;
		break;
		default:  //case in which server name is invalid
			
			System.out.println("No server specifications available for " + serverName);
			server_log.logger.info(manID + " has failed to transfer " + recID + " (invalid parameters)");
			return "Operation failed (invalid paramaters)";
		}
		
		//find record which is to be sent --> save in temp
		Record temp = findRecord(recID);
		
		if(temp == null){ //if record is not found
			server_log.logger.info(manID + " has failed to transfer " + recID + " (invalid parameters)");
			return "Operation failed (Record not found)";
		}
		
		try {
			
			mutex.acquire();
			os.writeObject(temp);  //write object into byte output stream
			buffer = outputstream.toByteArray();  //put into buffer
			
			DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, //datagram which is to be sent
					InetAddress.getLocalHost(), recieveaddress);
			
			aSocket = new DatagramSocket(sendaddress);	//initialize socket for sending
			aSocket.send(sendPacket);	//send packet
			removeRecord(recID);		//remove from HashMap
			recordCount--;
			
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		server_log.logger.info(manID + " has transfered " + recID + " to " + toServer);
		mutex.release();
		return "Operation successful";
	}


	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		orb.shutdown(false);
	}

	
	/**
	 * method which runs in order to receive Record and add to HashMap (runs on thread) 
	 */
	@SuppressWarnings("resource")
	private void recieveRecord(){

		int recieveaddress = 0; 		//port to be used for receiving packets from other servers 
		DatagramSocket aSocket = null;			//socket to be used later
		byte[]buffer = new byte[1024];			//byte array for DatagramPackets
	
		
		/*
		 *switch statement for setting up sockets according to the server name
		 */
		switch(serverName){
		case("LVL"):
			recieveaddress = 9001;
		break;
		case("DDO"):
			recieveaddress = 9002;
		break;
		case("MTL"):
			recieveaddress = 9003;
		break;
		default:  //case in which server name is invalid
			System.out.println("No server specifications available for " + serverName);
		}
		
		
		try {
			aSocket = new DatagramSocket(recieveaddress);  //initialize socket object
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  //creating DatagramSockets 
		
		String recID = null;
		
		while(true){
			
			try {

				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);	//Creating packet
				aSocket.receive(packet);		//Receiving packet --> blocking operation until a packet is recieved 
				
				mutex.acquire();		//mutex for ensuring that no operation will be performed until Record is added 
				ByteArrayInputStream in = new ByteArrayInputStream(buffer);  //byte inputStream for receiving object
				ObjectInputStream is = new ObjectInputStream(in);			
				Record temp = (Record)is.readObject();			//reading object from datagram as Record
				
				ArrayList<Record> arr = recordMap.get(temp.getLastName().charAt(0));  //find location to add in HashMap
				arr.add(temp);		//add to HashMap
				recordCount++;		//increment record count 
				recID = temp.getRecordID();

			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			server_log.logger.info("ID: " + recID + " has been added to server.");	//maybe add record ID to log
			mutex.release();  //release mutex
		
		}
	}
	
	
	/**
	 * method to remove Record from HashMap
	 * @param recID record ID of Record that is to be removed
	 */
	private void removeRecord(String recID){
		
		Character a = new Character('A');  //for iterating through HashMap

		for(int i = 0; i <= 25; i++){
			ArrayList<Record> temp = recordMap.get(a);
			for(int x = 0; x < temp.size(); x++){
				if(temp.get(x).getRecordID().equals(recID)){temp.remove(x);}  //if found
			}
			a++;
		}
	}

}
