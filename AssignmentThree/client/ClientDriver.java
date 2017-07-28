package client;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;


public class ClientDriver{

	public static void main(String[] args) throws Exception {
		

	
	
	ClientMenu menu = new ClientMenu();
		
	menu.getManagerID();
		
	URL url = new URL("http://localhost:999"+ menu.getPort() +"/ws/"+ menu.managerID.substring(0, 3) +"?wsdl");

        //1st argument service URI, refer to wsdl document above
	//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://server/" ,"CenterServerService");
        Service service = Service.create(url, qname);
       
        ManagerClient server = service.getPort(ManagerClient.class);
        menu.setServer(server);
        
        menu.runClientMenu();
	System.out.println("\nThank you for using DCMS!");

      

    }

}