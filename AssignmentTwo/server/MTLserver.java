package server;
import PackageManager.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

public class MTLserver {

	public static void main(String args[]) {

		try{
			// create and initialize the ORB //// get reference to rootpoa &amp; activate the POAManager
			ORB orb = ORB.init(args, null);      
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			CenterServer mtl = new CenterServer("MTL", 30000);
			mtl.setORB(orb); 

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(mtl);
			ManagerClient href = ManagerClientHelper.narrow(ref);

			org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			NameComponent path[] = ncRef.to_name("MTL");
			ncRef.rebind(path, href);

			mtl.createSRecord("John", "Smith", "Math, English", "active", "12/32/1990", "MTL0000");
			mtl.createSRecord("Eric","Dana", "Math, French", "inactive", "01/12/2000", "MTL0000");			//random data to be added when server is started 
			mtl.createSRecord("Donald","Duck", "Art, Chemistry", "active", "07/12/1967", "MTL0000");
			mtl.createSRecord("Glen", "Close", "Geography, Music", "active", "01/32/22", "MTL0000");
			mtl.createSRecord("Tom", "Brady", "Geography, French", "active", "11/11/12", "MTL0000");
			mtl.createSRecord("Josh", "Papa", "French, Music", "inactive", "09/02/45", "MTL0000");
			mtl.createSRecord("Katy", "Breams", "Music, Art", "active", "01/22/22", "MTL0000");
			mtl.createSRecord("Jin", "Yang", "Music, History", "active", "01/03/20", "MTL0000");
			mtl.createTRecord("Peter", "Jackson", "1234 street" , "5145231123", "Math", "MTL", "MTL0000");
			mtl.createTRecord("Jackson", "Pollack", "1234 street" , "51452312343", "Spanish", "MTL", "MTL0000");
			mtl.createTRecord("Pablo", "Picasso", "1234 street" , "5145231123", "History", "MTL", "MTL0000");
			mtl.createTRecord("George", "West", "1234 street" , "5145231123", "French", "MTL", "MTL0000");
			mtl.createTRecord("Jame", "Joyce", "1234 street" , "5145231123", "English", "MTL", "MTL0000");
			mtl.createTRecord("Eden", "Almakias", "1234 street" , "5145631123", "Geography", "MTL", "MTL0000");
			mtl.createTRecord("Bill", "East", "1234 street" , "5146541123", "Geography", "MTL", "MTL0000");
			System.out.println("Montreal Server is running");

			// wait for invocations from clients
			while (true){
				orb.run();
			}
		} 

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("Montreal Server Exiting ...");

	}

}
