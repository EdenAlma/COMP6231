package common;
/**
 * Class for representing StudentRecord (almost identical to TecherRecord)
 * @author Eden
 *
 */
public class StudentRecord extends Record{
	
	private String coursesRegistered;
	private String status;
	private String statusDate;
	
	
	public StudentRecord(String RecID, String fname, String lname, String crsReg, String status, String statusDate){
		setRecordID(RecID);
		setFirstName(fname);
		setLastName(lname);
		setCoursesRegistered(crsReg);
		setStatus(status);
		setStatusDate(statusDate);
	}
	
	public String getCoursesRegistered() {
		return coursesRegistered;
	}


	public void setCoursesRegistered(String coursesRegistered) {
		this.coursesRegistered = coursesRegistered;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getStatusDate() {
		return statusDate;
	}


	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}

	public String toStringS(){
		
		String output = null;
		output = "Record ID: "+ getRecordID() + "\t Name: " + getFirstName() + " " + getLastName() +"\n";
		output += "Courses Registered: " + getCoursesRegistered() + "\t Status: " + getStatus() +"\n";
		output += "Status Date: "+ getStatusDate() + "\n";
		
		return output;
	}

}
