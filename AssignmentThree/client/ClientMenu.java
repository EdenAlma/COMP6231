package client;
import java.util.Scanner;



public class ClientMenu {



	Scanner keyboard = new Scanner(System.in);
	ManagerClient serverObj = null;
	public String managerID ="";

	public void runClientMenu(){
		
		boolean exit = false;
		
		while(!exit){
		
			exit = runMenu();

		}
		
	}

	
	public void setServer(ManagerClient server){

		serverObj = server;
		
	}


	private boolean runMenu() {
	
		int select;

		System.out.print("\n1. CREATE TEACHER RECORD\n"
				+ "2. CREATE STUDNET RECORD\n"
				+ "3. GET RECORD COUNT\n"
				+ "4. EDIT RECORD\n"
				+ "5. DISPLAY ALL RECORDS\n"
				+ "6. DISPLAY SINGLE RECORD\n"
				+ "7. TRANSFER RECORD\n"
				+ "8. QUIT\n\n"
				+ "PLEASE SELECT AN OPTION:");

		while(true){
			try{
				select = keyboard.nextInt();

				if(select == 8){return true;}
				if(select < 0 || select > 8){
					System.out.println("Invalid input! please try again:");
					continue;
				}
				else{
					executeCommnad(select);
					return false;
				}

			}catch(Exception E){
				System.out.println("Invalid input! please try again:");
				continue;
			}

		}
	}


	public void getManagerID()
	{
		boolean valid  = false;

		while (!valid){

			System.out.print("Please enter your manager ID: ");
			managerID = keyboard.nextLine().trim();

			valid  = ((managerID.startsWith("LVL")||managerID.startsWith("MTL")
					||managerID.startsWith("DDO")) && managerID.length() == 7);


			if(!valid){
				System.out.println("Invalid input!\n");
				continue;
			}
			else{
				break;
			}
		}

	}

	
	public void executeCommnad(int cmd){

		switch(cmd){
		case 1:
			createTeacher();
			break;
		case 2:
			createStudent();
			break;
		case 3:
			System.out.println(serverObj.getRecordCounts(managerID));
			break;
		case 4:
			editRecord();
			break;
		case 5:
			System.out.print(serverObj.displayAllRecords(managerID));
			break;
		case 6:
			displayRecord();
			break;
		case 7:
			transferRecord();
			break;
		default:
			System.out.println("Invalid input!");
			break;

		}

		return;
	}

	
	public void createTeacher(){
		
		String fname, lname, address, phone, special, location, junk;
		
		System.out.print("First name: ");
		fname = keyboard.next();
		System.out.print("Last name: ");
		lname = keyboard.next();
		System.out.print("Address: ");
		junk = keyboard.nextLine();
		address = keyboard.nextLine().trim();
		System.out.print("Phone: ");
		phone = keyboard.next();
		System.out.print("Specialization: ");
		special = keyboard.next();
		
		while(true){
			System.out.print("Location: ");
			location = keyboard.next();
			if(!location.equals("DDO")&&!location.equals("MTL")&&!location.equals("LVL")){
				System.out.println("Invalid input...please try again");
				continue;
			}
			else break;
		}
		
		System.out.println(serverObj.createTRecord(fname, lname, address, 
				phone, special, location, managerID));
		
	}
	
	
	public void createStudent(){
		
		String fname, lname, crsReg, status, statusDate, junk;
		
		System.out.print("First name: ");
		fname = keyboard.next();
		System.out.print("Last name: ");
		lname = keyboard.next();
		System.out.print("Courses registered: ");
		junk = keyboard.nextLine();
		crsReg = keyboard.nextLine().trim();
		while(true){
			System.out.print("Status: ");
			status = keyboard.next();
			if(!status.equals("active")&&!status.equals("inactive")){
				System.out.println("Invalid input...please try again");
				continue;
			}
			else break;
		}
		System.out.print("Status date: ");
		statusDate = keyboard.next();
		
	
		System.out.println(serverObj.createSRecord(fname, lname, crsReg, 
				status, statusDate, managerID));		
	}
	
	
	public void editRecord(){
		
		String recordID, fieldName, newValue, junk;
		
		System.out.print("Record ID: ");
		recordID = keyboard.next();
		System.out.print("Field name: ");
		junk =  keyboard.nextLine();
		fieldName = keyboard.nextLine().trim();
		System.out.print("New value: ");
		//junk =  keyboard.nextLine();
		newValue = keyboard.nextLine().trim();
		System.out.println(serverObj.editRecord(recordID, fieldName, newValue, managerID));
			
	}
		
	
	public void displayRecord(){
		
		String recordID;
		
		System.out.print("Record ID: ");
		recordID = keyboard.next();
		
		System.out.print(serverObj.displayRecord(managerID, recordID));
		
	}
	
	
	public void transferRecord(){
		
		String recordID, destServer;
		
		System.out.print("Record ID: ");
		recordID = keyboard.next();
		System.out.print("Destination server: ");
		destServer = keyboard.next();
		
		
		System.out.println(serverObj.transferRecord(recordID, destServer, managerID));
		
		
	}
	
	
	public int getPort()
	{
		if(managerID.startsWith("DDO")){
			return 2;
			
		}else if(managerID.startsWith("LVL")){
			return 1;
			
		}else if(managerID.startsWith("MTL")){
			return 3;
			
		}
		return 0;
	}
	
	
}
