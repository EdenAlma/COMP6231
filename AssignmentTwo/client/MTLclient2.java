package client;
import PackageManager.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class MTLclient2 {

	public static void main(String[] args) {
		
		final String managerID = "MTL1111";
		
	      try {
	    	  
		    ORB orb = ORB.init(args, null);
		    org.omg.CORBA.Object objRef =   orb.resolve_initial_references("NameService");
		    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		    ManagerClient mtl = (ManagerClient)  ManagerClientHelper.narrow(ncRef.resolve_str("MTL"));
	 
			System.out.println(mtl.displayAllRecords(managerID));
			System.out.println(mtl.getRecordCounts(managerID));
			
	       }
	       catch (Exception e) {
	          System.out.println(e.getStackTrace());
		  e.printStackTrace();
	       }
	 
	    }

	
}
