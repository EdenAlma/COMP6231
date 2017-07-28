import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LVLClient1{

	public static final String managerID = "LVL1234";
	
	public static void main(String[] args) throws Exception{
		try{

			Registry registry = LocateRegistry.getRegistry(4567);
			ManagerClient lvl = (ManagerClient)registry.lookup("LVL");
			System.out.println(lvl.displayAllRecords(managerID));
			System.out.println(lvl.getRecordCounts(managerID));
			
		}catch(Exception e){
			System.out.println("Exception!");
			e.printStackTrace();
		}

	}
}


