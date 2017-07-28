/**
 * Class for representing TeacherRecord 
 * @author Eden
 *
 */
public class TeacherRecord extends Record{		//extend record so that both SR and TR can be stored together
	
	/**
	 * member variable 
	 */
	private String address;
	private String phone;
	private String specialization;
	private String location;

	/**
	 * simple constructor which uses setters to create record (validation is done in other method)
	 */
	public TeacherRecord(String RecID, String fname, String lname, String address, String phone, String special, String location){
		setRecordID(RecID);
		setFirstName(fname);
		setLastName(lname);
		setAddress(address);
		setPhone(phone);
		setSpecialization(special);
		setLocation(location);	
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {       //getters and setters 
		this.phone = phone;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * ToString for teacher records (details)
	 */
	public String toStringT(){

		String output = null;
		output = "Record ID: "+getRecordID() + "\t Name: " + getFirstName() + " " + getLastName() +"\n";
		output += "Address: "+getAddress() + "\t Phone: " + getPhone() +"\n";
		output += "Specialization: "+getSpecialization() + "\t Location: " + getLocation() +"\n";
		
		return output;

	}
}
