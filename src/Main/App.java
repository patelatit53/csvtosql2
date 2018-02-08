package Main;

//Initialise commands
//javac App.java
//java -cp .;jdbc.jar App
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;
import java.sql.PreparedStatement;
import model.ModelView;

public class App {

    public static final String csvFile = "/Users/atitpatel/Downloads/Workbook3.csv";

    public static void main(String[] args) {
         CSVReader reader;
        String[] nextLine;
        ModelView view = new ModelView();
        List<ModelView> al = new ArrayList();

        try {
            reader = new CSVReader(new FileReader(csvFile));
            while ((nextLine = reader.readNext()) != null) {
                
                String toString = Arrays.toString(nextLine);
                view.setId(nextLine[0]);
                view.setName(nextLine[1]);
                view.setAddress(nextLine[2]);
                al.add(view);

                for (ModelView view1 : al) {
                    System.out.println(view1.toString());

                }

                insertData(al);
                 }

        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
            }

    private static void insertData(List<ModelView> al) throws SQLException, ClassNotFoundException {
    
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/data1";
        String user = "root";
        String password = "";
        String query = "";
        // connrction
        
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            PreparedStatement ps;
       
        
        for (ModelView modelView1 : al) {

            query = "INSERT INTO rawdata ";
            query += " VALUES(";
            query += modelView1.getId()+ ",";//1
            query += "'" + modelView1.getName() + "',";
            query += "'" +   modelView1.getAddress()+"'" ;
            query += ")";

          //query  = "DELETE FROM `rawdata` WHERE `id` = '0'";
          //
//To change body of generated methods, choose Tools | Templates.
    }
        System.out.println(query);
        //st.executeUpdate(query);
     ps = con.prepareCall(query);
//            System.out.println(ps);
     ps.executeUpdate();
    }
}
