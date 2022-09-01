package icai.dtc.isw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import icai.dtc.isw.domain.Customer;

public class CustomerDAO {
	
	
	
	public static void getClientes(ArrayList<Customer> lista) {
		Connection con=ConnectionDAO.getInstance().getConnection();
		try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios");
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
            	lista.add(new Customer(rs.getString(1),rs.getString(2)));
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
	}
	public static Customer getCliente(int id) {
		Connection con=ConnectionDAO.getInstance().getConnection();
		Customer cu=null;
		try (PreparedStatement pst = con.prepareStatement("SELECT * FROM usuarios WHERE id="+id);
			 ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				cu= new Customer(rs.getString(1),rs.getString(2));
			}

		} catch (SQLException ex) {

			System.out.println(ex.getMessage());
		}
		return cu;
		//return new Customer("1","Atilano");
	}
	
	public static void main(String[] args) {
		
		
		ArrayList<Customer> lista= new ArrayList<>();
		CustomerDAO.getClientes(lista);
		
		
		 for (Customer customer : lista) {			
			System.out.println("He leído el id: "+customer.getId()+" con nombre: "+customer.getName());
		}
		
	
	}

}
