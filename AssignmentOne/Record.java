/**
 * Record class used to create TeacherRecord and StudentRecord subclasses
 * since the two classes share a superclass they can both be stored as Record object
 * @author Eden
 *
 */
public class Record {
	
	private String RecordID;
	private String firstName;		//variables 
	private String lastName;
	
	protected String getRecordID() {
		return RecordID;
	}
	protected void setRecordID(String recordID) {
		RecordID = recordID;
	}
	protected String getFirstName() {
		return firstName;
	}														//getters and setters 
	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	protected String getLastName() {
		return lastName;
	}
	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String toString(){		//simple toString method

		String output = null;
		output = "Record ID: "+getRecordID() + "\t Name: " + getFirstName() + " " + getLastName();
		return output;

	}
}
