package cs5530_Project;

public class test1 {

	public static void main(String[] args) {
		try {

			Connector con = new Connector();
			System.out.println("Connection made.");
			TestSQL test = new TestSQL();
			String result = test.get(con.stmt);
			System.out.println(result);
			con.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
