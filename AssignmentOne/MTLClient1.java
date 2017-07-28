import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MTLClient1 {
	
	public static final String managerID = "MTL1234";
	
	public static void main(String[] args) throws Exception{
		try{

			Registry registry = LocateRegistry.getRegistry(4568);
			ManagerClient mtl = (ManagerClient)registry.lookup("MTL");
			
			System.out.println(mtl.displayAllRecords(managerID));
			//mtl.createSRecord("Bobby", "Digital", "History", "active", "01/32/99", managerID);
			//mtl.createTRecord("David", "Tanami", "1234 street" , "51452312343", "Physical Education", "MTL", managerID);
			System.out.println(mtl.displayAllRecords(managerID));
			System.out.println(mtl.displayRecord(managerID, "TR40001"));
			mtl.editRecord("TR40001", "phone", "5149119111", managerID);
			mtl.editRecord("TR40001", "location", "ABC", managerID);
			System.out.println(mtl.displayRecord(managerID, "TR40001"));
			
			
		}catch(Exception e){
			System.out.println("Exception!");
			e.printStackTrace();
		}

	}

}
