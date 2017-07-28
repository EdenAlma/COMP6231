package server;
import java.io.IOException;
import javax.xml.ws.Endpoint;


public class MTLserver {

	public static void main(String[] args) {
		
		try {
			CenterServer mtl = new CenterServer("MTL", 30000);
			Endpoint.publish("http://localhost:9993/ws/MTL", mtl);
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
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
