import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface is meant to be used by the clients in order to invoke methods (remotely)
 * on servers which implement the methods which are written below
 * @author Eden
 *
 */
public interface ManagerClient extends Remote{
	
	public String createTRecord(String fname, String lname, String address, String phone, String speical, String location, String manID) throws RemoteException;
	public String createSRecord(String fname, String lname, String crsReg, String status, String statusDate, String manID) throws RemoteException;
	public String getRecordCounts(String manID) throws RemoteException;
	public String editRecord(String recID, String fieldName, String newValue, String manID) throws RemoteException;
	public String displayAllRecords(String manID) throws RemoteException;
	public String displayRecord(String manID, String recID) throws RemoteException;
	

}
