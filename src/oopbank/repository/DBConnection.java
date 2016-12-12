package oopbank.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oopbank.Account;
import oopbank.CreditAccount;
import oopbank.Customer;
import oopbank.SavingsAccount;
import static java.lang.Long.parseLong;
import oopbank.Transaction;

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
            System.out.println(ex.getMessage() + "getCustomerListInfo");
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
            int temp = rs.getInt("accountNumber");
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
            System.out.println(ex.getMessage() + " getAccountListInfo");
        } 

        return tempAccountList;
    }

    public void addCreditAccountDB(int accountNumber, String accountType, long pNr) throws SQLException {
        try {

            pstmt = conn.prepareStatement("Insert into account (AccountNumber, Balance, "
                    + "AccountType, Customer_PNR) Values (?,?,?,?);");

            pstmt.setInt(1, accountNumber);
            pstmt.setDouble(2, 0);
            pstmt.setString(3, accountType);
            pstmt.setLong(4, pNr);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Fel i metoden addCreditAccountDB");
        } 
            

    }

    public void addSavingsAccountDB(int accountNumber, String accountType, double balance, long pNr) throws SQLException {
        try {
            pstmt = conn.prepareStatement("Insert into account (AccountNumber, Balance, "
                    + "AccountType, Customer_PNR) Values(?,?,?,?);");

            pstmt.setInt(1, accountNumber);
            pstmt.setDouble(2, balance);
            pstmt.setString(3, accountType);
            pstmt.setLong(4, pNr);

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Fel i metoden addSavingsAccountDB");
        } 

    }

    public void depositDB(String dateTime, double amount, double balance, int accountNumber) throws SQLException {
        try {
            pstmt = conn.prepareStatement("INSERT INTO transaction (dateTime, transactionType,"
                    + " amount, balance, account_accountNumber)"
                    + " values (?,?,?,?,?);");

            pstmt.setString(1, dateTime);
            pstmt.setString(2, "Deposit");
            pstmt.setDouble(3, amount);
            pstmt.setDouble(4, balance);
            pstmt.setInt(5, accountNumber);

            pstmt.executeUpdate();

            pstmt = conn.prepareStatement("update account set balance=? where accountNumber=?;");

            pstmt.setDouble(1, balance);
            pstmt.setInt(2, accountNumber);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Fel i metoden depositDB");
        }
//        

    }

    public void withdrawDB(String dateTime, double amount, double balance, int accountNumber) throws SQLException {
        try {
            pstmt = conn.prepareStatement("INSERT INTO transaction (dateTime, transactionType, amount, balance, account_accountNumber)"
                    + " values (?,?,?,?,?);");

            pstmt.setString(1, dateTime);
            pstmt.setString(2, "Withdraw");
            pstmt.setDouble(3, amount);
            pstmt.setDouble(4, balance);
            pstmt.setInt(5, accountNumber);

            pstmt.executeUpdate();

            pstmt = conn.prepareStatement("update account set balance=? where accountNumber=?;");

            pstmt.setDouble(1, balance);
            pstmt.setInt(2, accountNumber);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Fel i metoden withdrawDB");
        } 

    }

    public ArrayList getTransactionListinfo(int accountNo) {
        ArrayList tempTransactionList = new ArrayList();
        try {

            //hämtar all information från account table och lägger till konton
            //i arraylist för varje specifik kund
            sql = "Select * FROM transaction WHERE account_accountNumber = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, accountNo);
            rs = pstmt.executeQuery();
            while (rs.next()) {
               
                String dateTime = rs.getString("dateTime");
                String type = rs.getString("transactionType");
                double amount = rs.getDouble("amount");
                double balance = rs.getDouble("balance");
                tempTransactionList.add((new Transaction(dateTime,type,amount,balance)));
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + " getTransactionListInfo");
        } 
        

        return tempTransactionList;
    }
    
    public void addCustomerDB(String firstName, String lastName, long pNr) throws SQLException
    {
        try {
            pstmt = conn.prepareStatement("Insert into customer (PNR, firstName, lastName) Values(?,?,?);");
            pstmt.setLong(1, pNr);
            pstmt.setString(2, firstName);
            pstmt.setString(3,lastName);
            
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Fel i metoden addCustomerDB.");
        }
    }
public void closeAccountDB(int accountNumber)
    {
        try
        {
            stmt.executeUpdate("DELETE from account WHERE accountNumber = "  + accountNumber + ";");
                        
                      
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage() + " closeAccountDB");
        }
    }

public void changeNameDB(String firstName, String lastName, long pNr) throws SQLException
{
	try 
	{
            String tempLong = String.valueOf(pNr);
		pstmt = conn.prepareStatement("UPDATE customer SET firstName = ?,lastName = ? WHERE PNR = ?");

		pstmt.setString(1, firstName);
		pstmt.setString(2, lastName);
		pstmt.setString(3, tempLong);

		pstmt.executeUpdate();
	} 
	catch (SQLException ex) 
	{
            System.out.println(ex.getMessage());
            System.out.println("changeNameDB");
        } 
	
}

public void removeCustomer(long pNr){
	
	try{

	String tempLong = String.valueOf(pNr);
	
	String sql = "DELETE FROM customer WHERE PNR = ?";
	
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, tempLong);
	
        pstmt.executeUpdate();

	}catch(SQLException e){
		System.out.println(e.getMessage());
	}

}
public void closeConn(){
    try{
    if(conn!=null){
        conn.close();
    }if(rs!=null){
        rs.close();
    }if(stmt!=null){
        stmt.close();
    }if(pstmt!=null){
        pstmt.close();
    }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    
}


}
