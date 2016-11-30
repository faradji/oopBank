package oopbank.repository;

import static java.lang.Long.parseLong;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oopbank.Customer;

public class DBConnection {

    static final String dbUrl = "jdbc:mysql://127.0.0.1/oopbank";
    //  inloggningsuppgifter
    static final String userName = "root";
    static final String passWord = "root";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    String sql = null;//skapa setter

    public DBConnection() {
        try {//connection
            conn = DriverManager.getConnection(dbUrl, userName, passWord);
            stmt = conn.createStatement();
            pstmt = conn.prepareStatement(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public ArrayList getCustomerInfo() {
        ArrayList tempCustomer = new ArrayList();

        try {
            rs = stmt.executeQuery("Select * from customer");

            while (rs.next()) {
                tempCustomer.add(new Customer(rs.getString("firstName"), rs.getString("lastName"), parseLong(rs.getString("PNR"))));
            }

        } catch (SQLException ex) {
           System.out.println("FEL");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("OCKSÅ FEL");
            }
        }
        
            return tempCustomer;
        }
    }
