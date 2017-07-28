import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MTLClient2 {
	
	public static final String managerID = "MTL4321";
	
	public static void main(String[] args) throws Exception{
		try{

			Registry registry = LocateRegistry.getRegistry(4568);
			ManagerClient mtl = (ManagerClient)registry.lookup("MTL");
			System.out.println(mtl.displayAllRecords(managerID));
			System.out.println(mtl.displayRecord(managerID, "TR40001"));
			System.out.println(mtl.getRecordCounts(managerID));
		}catch(Exception e){
			System.out.println("Exception!");
			e.printStackTrace();
		}

	
		
	}

}
