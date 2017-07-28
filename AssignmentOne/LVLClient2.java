import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LVLClient2 {
	
	public static final String managerID = "LVL4321";
	
	public static void main(String[] args) throws Exception{
		try{

			Registry registry = LocateRegistry.getRegistry(4567);
			ManagerClient lvl = (ManagerClient)registry.lookup("LVL");
			lvl.createTRecord("Amine", "Jabor", "1234 street" , "51452312343", "Physics", "MTL", managerID);
			
		}catch(Exception e){
			System.out.println("Exception!");
			e.printStackTrace();
		}

	}

}
