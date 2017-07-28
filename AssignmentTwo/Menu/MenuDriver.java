package Menu;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import PackageManager.ManagerClient;
import PackageManager.ManagerClientHelper;

public class MenuDriver {
	

	

	public static void main(String[] args){
		
		System.out.println("Distributed Course Managment System\n"
				          +"===================================\n");
		ClientMenu menu = new ClientMenu();
		menu.getManagerID();
		
		try {
			ORB orb = ORB.init(args, null);
		    org.omg.CORBA.Object objRef = null;
			objRef = orb.resolve_initial_references("NameService");
		    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		    ManagerClient serverObj = (ManagerClient) ManagerClientHelper.narrow(ncRef.resolve_str(menu.managerID.substring(0, 3)));
		    menu.setServer(serverObj);    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		menu.runClientMenu();
		System.out.println("\nThank you for using DCMS!");
	}

}
