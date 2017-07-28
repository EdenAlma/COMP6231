package server;
import java.io.IOException;
import javax.xml.ws.Endpoint;


public class LVLserver {

	public static void main(String[] args) {
		
		try {
			CenterServer lvl = new CenterServer("LVL", 10000);
			Endpoint.publish("http://localhost:9991/ws/LVL", lvl);
			lvl.createSRecord("John", "Smith", "Math, English", "active", "12/32/1990", "LVL0000");
			lvl.createSRecord("Eric","Dana", "Math, French", "inactive", "01/12/2000", "LVL0000");
			lvl.createSRecord("Donald","Duck", "Art, Chemistry", "active", "07/12/1967", "LVL0000");
			lvl.createSRecord("Glen", "Close", "Geography, Music", "active", "01/32/22", "LVL0000");
			lvl.createSRecord("Tom", "Brady", "Geography, French", "active", "11/11/12", "LVL0000");
			lvl.createSRecord("Josh", "Papa", "French, Music", "inactive", "09/02/45", "LVL0000");
			lvl.createSRecord("Katy", "Breams", "Music, Art", "active", "01/22/22", "LVL0000");
			lvl.createSRecord("Jin", "Yang", "Music, History", "active", "01/03/20", "LVL0000");
			lvl.createTRecord("Peter", "Jackson", "1234 street" , "5145231123", "Math", "LVL", "LVL0000");
			lvl.createTRecord("Jackson", "Pollack", "1234 street" , "51452312343", "Spanish", "LVL", "LVL0000");
			lvl.createTRecord("Pablo", "Picasso", "1234 street" , "5145231123", "History", "LVL", "LVL0000");
			lvl.createTRecord("George", "West", "1234 street" , "5145231123", "French", "LVL", "LVL0000");
			lvl.createTRecord("Jame", "Joyce", "1234 street" , "5145231123", "English", "LVL", "LVL0000");
			lvl.createTRecord("Eden", "Almakias", "1234 street" , "5145631123", "Geography", "LVL", "LVL0000");
			lvl.createTRecord("Bill", "East", "1234 street" , "5146541123", "Geography", "LVL", "LVL0000");
			System.out.println("Laval Server is running");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
