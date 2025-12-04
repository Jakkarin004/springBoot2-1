package pccth.sp.pccthspseedservice.utils;

import org.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jsonString = "{'name' : 'Bob','profession' : 'Software engineer','department' : 'Research','age' : 40}";
		
		JSONObject obj = new JSONObject();
		obj.put("Age", 27);
		
//				"{" +  
//				"Employee ID: 092789," +  
//				"Employee Name: Helen Mirren," +  
//				"Age: '27', " +  
//				"Designation: Assistant Manager," +  
//				"City: Florida," +  
//				"Salary: 67000.69, " +  
//				"Experience: '26' " +  
//				"}"  
//				);  
		
		//System.out.println(obj.toString());
		//System.out.println("Salary Int: "+obj.getInt("Salary"));  //67000
		//System.out.println("Salary Double: "+obj.getDouble("Salary"));  //67000.69
		//System.out.println("Experience String: "+obj.get("Age"));  //67000.69
		
		String txt2 = "   ";
		String txt3 = null;
		String txt8 = "1";
		
		if( !"1".equals(txt2) && !"1".equals(txt3) && "1".equals(txt8) ) {
			System.out.println("TRUE");
		}else {
			System.out.println("FALSE");
		}
	}

}
