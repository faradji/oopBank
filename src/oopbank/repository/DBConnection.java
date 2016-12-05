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
import oopbank.Account;
import oopbank.CreditAccount;
import oopbank.Customer;
import oopbank.SavingsAccount;

public class DBConnection {

    static final String dbUrl = "jdbc:mysql://127.0.0.1/OOP-bank";
    //  inloggningsuppgifter
    static final String userName = "root";
    static final String passWord = "root";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    String sql;//skapa setter

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

    public ArrayList getCustomerListInfo() {
        ArrayList tempCustomer = new ArrayList();

        try {
            rs = stmt.executeQuery("Select * from customer");

            while (rs.next()) {
                tempCustomer.add(new Customer(rs.getString("firstName"), rs.getString("lastName"), parseLong(rs.getString("PNR"))));
            }

        } catch (SQLException ex) {
          System.out.println(ex.getMessage()+ "getCustomerListInfo");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage()+ "getCustomerListInfo");
            }
        }

        return tempCustomer;
    }

    public ArrayList getAccountListinfo(long pNr) {
        ArrayList tempAccountList = new ArrayList();

        try {
            //hämtar största kontonumret och sätter accountcounter till det
            //plus ett
            rs = stmt.executeQuery("Select MAX(accountNumber) as accountNumber from account;");
            rs.next();
            int temp=rs.getInt("accountNumber");
            Account.setAccountCounter((temp + 1));

            //hämtar all information från account table och lägger till konton
            //i arraylist för varje specifik kund
            sql = "Select * FROM account WHERE customer_PNR = ?";
            String tempLong = String.valueOf(pNr);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tempLong);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("accountType").equals("Savings Account")) {
                    tempAccountList.add(new SavingsAccount(rs.getInt("accountNumber"), rs.getDouble("balance")));
                } else if (rs.getString("accountType").equals("Credit Account")) {
                    tempAccountList.add(new CreditAccount(rs.getInt("accountNumber"), rs.getDouble("balance")));
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage()+ " getAccountListInfo");
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage()+ " getAccountListInfo");
            }
        }

        return tempAccountList;
    }

}
