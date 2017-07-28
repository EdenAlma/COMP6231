package client;
import PackageManager.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class DDOclient2 {
	

	public static void main(String[] args) {
		
		final String managerID = "DDO5555";
		
	      try {
	    	  
		    ORB orb = ORB.init(args, null);
		    org.omg.CORBA.Object objRef =   orb.resolve_initial_references("NameService");
		    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		    ManagerClient ddo = (ManagerClient)  ManagerClientHelper.narrow(ncRef.resolve_str("DDO"));
	 
		    System.out.println(ddo.displayAllRecords(managerID));
		    System.out.println(ddo.getRecordCounts(managerID));
		    
	       }
	       catch (Exception e) {
	          System.out.println(e.getStackTrace());
		  e.printStackTrace();
	       }
	 
	    }

}
