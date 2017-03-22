package cs5530_Project;

public class test1 {

	public static void main(String[] args) {
		try {

			Connector con = new Connector();
			System.out.println("Connection made.");
			
			RegistrationSQL reg = new RegistrationSQL();
			String result = reg.get(con.stmt,"testLogin2","Test Name","password","123 Address","123-456-7890");
			System.out.println(result);
			
			TestSQL test = new TestSQL();
			result = test.get(con.stmt);
			System.out.println(result);
			
			con.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
