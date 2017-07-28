import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DDOClient2 {
	
	public static final String managerID = "DDO4321";
	
	public static void main(String[] args) throws Exception{
		try{

			Registry registry = LocateRegistry.getRegistry(4569);
			ManagerClient ddo = (ManagerClient)registry.lookup("DDO");
			System.out.println(ddo.displayAllRecords(managerID));
			System.out.println(ddo.getRecordCounts(managerID));
			
		}catch(Exception e){
			System.out.println("Exception!");
			e.printStackTrace();
		}

	}

}
