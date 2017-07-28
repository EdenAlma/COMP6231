package server;
import PackageManager.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;


public class DDOserver {

	public static void main(String args[]) {

		try{
			// create and initialize the ORB //// get reference to rootpoa &amp; activate the POAManager
			ORB orb = ORB.init(args, null);      
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			CenterServer ddo = new CenterServer("DDO", 20000);
			ddo.setORB(orb); 

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(ddo);
			ManagerClient href = ManagerClientHelper.narrow(ref);

			org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			NameComponent path[] = ncRef.to_name("DDO");
			ncRef.rebind(path, href);

			ddo.createSRecord("John", "Smith", "Math, English", "active", "12/32/1990", "DDO0000");
			ddo.createSRecord("Eric","Dana", "Math, French", "inactive", "01/12/2000", "DDO0000");
			ddo.createSRecord("Donald","Duck", "Art, Chemistry", "active", "07/12/1967", "DDO0000");
			ddo.createSRecord("Glen", "Close", "Geography, Music", "active", "01/32/22", "DDO0000");
			ddo.createSRecord("Tom", "Brady", "Geography, French", "active", "11/11/12", "DDO0000");
			ddo.createSRecord("Josh", "Papa", "French, Music", "inactive", "09/02/45", "DDO0000");
			ddo.createSRecord("Katy", "Breams", "Music, Art", "active", "01/22/22", "DDO0000");
			ddo.createSRecord("Jin", "Yang", "Music, History", "active", "01/03/20", "DDO0000");
			ddo.createTRecord("Peter", "Jackson", "1234 street" , "5145231123", "Math", "DDO", "DDO0000");
			ddo.createTRecord("Jackson", "Pollack", "1234 street" , "51452312343", "Spanish", "DDO", "DDO0000");
			ddo.createTRecord("Pablo", "Picasso", "1234 street" , "5145231123", "History", "DDO", "DDO0000");
			ddo.createTRecord("George", "West", "1234 street" , "5145231123", "French", "DDO", "DDO0000");
			ddo.createTRecord("Jame", "Joyce", "1234 street" , "5145231123", "English", "DDO", "DDO0000");
			ddo.createTRecord("Eden", "Almakias", "1234 street" , "5145631123", "Geography", "DDO", "DDO0000");
			ddo.createTRecord("Bill", "Zast", "1234 street" , "5146541123", "Geography", "DDO", "DDO0000");
			System.out.println("Dollard des Ormeaux Server is running");

			// wait for invocations from clients
			while (true){
				orb.run();
			}
		} 

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		System.out.println("Dollard des Ormeaux Server Exiting ...");

	}

}
