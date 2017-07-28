package client;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ManagerClient {
	
	@WebMethod
	String createTRecord (String fname, String lname, String address, String phone, String speical, String location, String manID);
	
	@WebMethod
	String createSRecord (String fname, String lname, String crsReg, String status, String statusDate, String manID);
	
	@WebMethod
	String getRecordCounts (String manID);
	
	@WebMethod
	String editRecord (String recID, String fieldName, String newValue, String manID);
	
	@WebMethod
	String displayAllRecords (String manID);
	
	@WebMethod
	String displayRecord (String manID, String recID);
	
	@WebMethod
	String transferRecord (String recID, String toServer, String manID);

}
