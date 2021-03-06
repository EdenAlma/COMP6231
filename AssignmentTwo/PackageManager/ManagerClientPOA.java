package PackageManager;


/**
* PackageManager/ManagerClientPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from IDLmanager.idl
* Saturday, July 8, 2017 1:27:51 o'clock PM EDT
*/

public abstract class ManagerClientPOA extends org.omg.PortableServer.Servant
 implements PackageManager.ManagerClientOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("createTRecord", new java.lang.Integer (0));
    _methods.put ("createSRecord", new java.lang.Integer (1));
    _methods.put ("getRecordCounts", new java.lang.Integer (2));
    _methods.put ("editRecord", new java.lang.Integer (3));
    _methods.put ("displayAllRecords", new java.lang.Integer (4));
    _methods.put ("displayRecord", new java.lang.Integer (5));
    _methods.put ("transferRecord", new java.lang.Integer (6));
    _methods.put ("shutdown", new java.lang.Integer (7));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // PackageManager/ManagerClient/createTRecord
       {
         String fname = in.read_string ();
         String lname = in.read_string ();
         String address = in.read_string ();
         String phone = in.read_string ();
         String speical = in.read_string ();
         String location = in.read_string ();
         String manID = in.read_string ();
         String $result = null;
         $result = this.createTRecord (fname, lname, address, phone, speical, location, manID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 1:  // PackageManager/ManagerClient/createSRecord
       {
         String fname = in.read_string ();
         String lname = in.read_string ();
         String crsReg = in.read_string ();
         String status = in.read_string ();
         String statusDate = in.read_string ();
         String manID = in.read_string ();
         String $result = null;
         $result = this.createSRecord (fname, lname, crsReg, status, statusDate, manID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // PackageManager/ManagerClient/getRecordCounts
       {
         String manID = in.read_string ();
         String $result = null;
         $result = this.getRecordCounts (manID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 3:  // PackageManager/ManagerClient/editRecord
       {
         String recID = in.read_string ();
         String fieldName = in.read_string ();
         String newValue = in.read_string ();
         String manID = in.read_string ();
         String $result = null;
         $result = this.editRecord (recID, fieldName, newValue, manID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // PackageManager/ManagerClient/displayAllRecords
       {
         String manID = in.read_string ();
         String $result = null;
         $result = this.displayAllRecords (manID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 5:  // PackageManager/ManagerClient/displayRecord
       {
         String manID = in.read_string ();
         String recID = in.read_string ();
         String $result = null;
         $result = this.displayRecord (manID, recID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 6:  // PackageManager/ManagerClient/transferRecord
       {
         String recID = in.read_string ();
         String toServer = in.read_string ();
         String manID = in.read_string ();
         String $result = null;
         $result = this.transferRecord (recID, toServer, manID);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 7:  // PackageManager/ManagerClient/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:PackageManager/ManagerClient:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public ManagerClient _this() 
  {
    return ManagerClientHelper.narrow(
    super._this_object());
  }

  public ManagerClient _this(org.omg.CORBA.ORB orb) 
  {
    return ManagerClientHelper.narrow(
    super._this_object(orb));
  }


} // class ManagerClientPOA
