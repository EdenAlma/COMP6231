package client;
import PackageManager.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class MTLclient1 {
	
	public static void main(String[] args) {
		
		final String managerID = "MTL3333";
		
	      try {
	    	  
		    ORB orb = ORB.init(args, null);
		    org.omg.CORBA.Object objRef =   orb.resolve_initial_references("NameService");
		    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		    ManagerClient mtl = (ManagerClient)  ManagerClientHelper.narrow(ncRef.resolve_str("MTL"));
		    
		    System.out.println(mtl.displayAllRecords(managerID));
			mtl.createSRecord("Bobby", "Digital", "History", "active", "01/32/99", managerID);
			mtl.createTRecord("David", "Tanami", "1234 street" , "51452312343", "Physical Education", "MTL", managerID);
			System.out.println(mtl.displayAllRecords(managerID));
			System.out.println(mtl.displayRecord(managerID, "TR30002"));
			mtl.editRecord("TR30001", "phone", "5149119111", managerID);
			mtl.editRecord("TR30001", "location", "ABC", managerID);
			mtl.transferRecord("TR30001", "LVL", managerID);
		    	    
	       }
	       catch (Exception e) {
	          System.out.println(e.getStackTrace());
		  e.printStackTrace();
	       }
	 
	    }

}
