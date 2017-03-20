package cs5530_Project;

public class test1 {

	public static void main(String[] args) {
		try {

			Connector con = new Connector();
			System.out.println("Connection made.");
			con.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
