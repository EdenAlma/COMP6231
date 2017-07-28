import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DDOClient1 {
	
	public static final String managerID = "DDO1234";
	
	public static void main(String[] args) throws Exception{
		try{

			Registry registry = LocateRegistry.getRegistry(4569);
			ManagerClient ddo = (ManagerClient)registry.lookup("DDO");
			ddo.createTRecord("Johnny", "Blaze", "1234 street" , "51452312343", "Dance", "DDO", managerID);
			
		}catch(Exception e){
			System.out.println("Exception!");
			e.printStackTrace();
		}

	}

}
