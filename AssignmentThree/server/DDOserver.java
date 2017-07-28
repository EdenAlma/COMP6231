package server;
import java.io.IOException;
import javax.xml.ws.Endpoint;


public class DDOserver {

	public static void main(String[] args) {
		
		try {
			CenterServer ddo = new CenterServer("DDO", 20000);
			Endpoint.publish("http://localhost:9992/ws/DDO", ddo);
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
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
