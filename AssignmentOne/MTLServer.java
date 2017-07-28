import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Logger;

/**
 * Montreal server 
 * @author Eden
 *
 */
public class MTLServer {

	public static void main(String[] args) throws Exception{
		
		final String serverName = "MTL";  //server name
		final int serverNum = 40000;		//number for creating IDs
		final int serverPort = 4568;	//port for RMI
		CenterServer mtl = new CenterServer(serverName, serverNum);		//instantiation of remote object
		Registry registry = LocateRegistry.createRegistry(serverPort);	//get registry
		registry.bind("MTL", mtl);										//binding object to registry 
		System.out.println("Montreal server is running on port:" + serverPort);		//message to console informing that server is running
			
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
	}

	
}
