package client;
import PackageManager.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class LVLclient2 {
	
	public static void main(String[] args) {
		
		final String managerID = "LVL1235";
		
	      try {
	    	  
		    ORB orb = ORB.init(args, null);
		    org.omg.CORBA.Object objRef =   orb.resolve_initial_references("NameService");
		    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		    ManagerClient lvl = (ManagerClient)  ManagerClientHelper.narrow(ncRef.resolve_str("LVL"));
	 
		    lvl.createTRecord("Amine", "Jabor", "1234 street" , "51452312343", "Physics", "MTL", managerID);
		    lvl.transferRecord("SR10002", "DDO", managerID);
		    System.out.println(lvl.displayAllRecords(managerID));
		    
	       }
	       catch (Exception e) {
	          System.out.println(e.getStackTrace());
		  e.printStackTrace();
	       }
	 
	    }

}
