package oopbank.repository;

import static java.lang.Long.parseLong;
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
        } finally {
            try {
                if(rs!=null){
                rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + "getCustomerListInfo");
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
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " getAccountListInfo");
            }
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
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (conn != null) {
                conn.close();
            }
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
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
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
//        finally {
//            if (pstmt != null) {
//                pstmt.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }

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
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    public ArrayList getTransactionListinfo(int accountNo) {
        System.out.println("HEj");
        ArrayList tempTransactionList = new ArrayList();
        int count = 0;
        try {

            //hämtar all information från account table och lägger till konton
            //i arraylist för varje specifik kund
            sql = "Select * FROM transaction WHERE account_accountNumber = ?";
            String tempacc = String.valueOf(accountNo);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, accountNo);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count++;
                String dateTime = rs.getString("dateTime");
                String type = rs.getString("transactionType");
                double amount = rs.getDouble("amount");
                double balance = rs.getDouble("balance");
                System.out.println(dateTime + type + amount + balance);
                //tempTransactionList.add(new Transaction(rs.getString("dateTime"), rs.getString("transactionType"), rs.getDouble("amount"), rs.getDouble("balance")));
               tempTransactionList.add(new Transaction(dateTime,type,amount,balance));
                System.out.println(count);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + " getTransactionListInfo");
        } finally {
            try {
                rs.close();
                pstmt.close();
                System.out.println("Choklad");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() + " getTransactionListInfo");
            }
        }

        return tempTransactionList;
    }
}
