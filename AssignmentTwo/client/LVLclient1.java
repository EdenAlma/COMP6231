package client;
import PackageManager.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class LVLclient1 {
	
	public static void main(String[] args) {
		
		final String managerID = "LVL3421";
		
	      try {
	    	  
		    ORB orb = ORB.init(args, null);
		    org.omg.CORBA.Object objRef =   orb.resolve_initial_references("NameService");
		    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		    ManagerClient lvl = (ManagerClient)  ManagerClientHelper.narrow(ncRef.resolve_str("LVL"));
	 
		    System.out.println(lvl.displayAllRecords(managerID));
			System.out.println(lvl.getRecordCounts(managerID));
			
	       }
	       catch (Exception e) {
	          System.out.println(e.getStackTrace());
		  e.printStackTrace();
	       }
	 
	    }

}
