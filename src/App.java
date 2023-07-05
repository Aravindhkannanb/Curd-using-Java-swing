import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.TextField;
import java.awt.event.*;
import java.sql.*;
public class App{
      static DefaultTableModel tableModel;
    static JTable dataTable;
    public static void main(String args[]){
        JFrame main=new JFrame("Registration");
        JLabel l1,l2,l3,l4;
        JTextField t1,t2,t3,t4;
        JTextArea a1;
        JButton b1,b2,b3,b8;
        a1=new JTextArea(null, null, 0, 0);
        l1=new JLabel("Name");
        l2=new JLabel("Roll no");
        l3=new JLabel("Address");
        l4=new JLabel("Description");
        b1=new JButton("Create");
        b2=new JButton("Update");
        b3=new JButton("Delete");
        b8=new JButton("Read");
        t1=new JTextField("Enter your name");
        t2=new JTextField("Enter your Roll no");
        t3=new JTextField("Enter your Address");
        t4=new JTextField("Enter your Description");
        t1.setBounds(100, 30, 150, 20);
        t2.setBounds(100, 70, 150, 20);
        t3.setBounds(100, 110, 150, 20);
        t4.setBounds(100, 150, 150, 20);
        l1.setBounds(20, 20, 100, 30);
        l2.setBounds(20, 60, 100, 30);
        l3.setBounds(20, 100, 100, 30);
        l4.setBounds(20, 140, 100, 30);
        b1.setBounds(60,200,100,30);
        b2.setBounds(150,200,100,30);
        b3.setBounds(250,200,100,30);
        b8.setBounds(350,200,100,30);
        a1.setBounds(60,250,100,50);
        main.add(a1);
        main.add(l1);
        main.add(l2);
        main.add(l3);
        main.add(l4);
        main.add(b1);
        main.add(b2);
        main.add(b3);
        main.add(t1);
        main.add(t2);
        main.add(t3);
        main.add(t4);
        main.add(b8);
        main.setLayout(null);
        main.setSize(500,1000);
        main.setVisible(true);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                TextField t5=new TextField("Enter Name to deleted:");
                t5.setBounds(250, 250, 170, 30);
                main.add(t5);
                JButton b4=new JButton("Submit");
                b4.setBounds(300,400,100,30);
                main.add(b4);
                b4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        String myname=t5.getText();
                        try{
    Class.forName("com.mysql.jdbc.Driver");
    System.out.println("Connection is successful");
    Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","Aravindh@2023");
    Statement statement;
    statement=connection.createStatement();
    String query="delete from Registrations where names=?";
    PreparedStatement pstate=connection.prepareStatement(query);
    pstate.setString(1, myname);

    pstate.executeUpdate();
    
    }
    catch(Exception exception){

    }
                    }
                });
                JButton b5=new JButton("try");
                b4.setBounds(250,300,100,30);
                main.add(b4);
                

            }
        });
        b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e){
    String name=t1.getText();
    String roll_no=t2.getText();
    String Address=t3.getText();
    String Desc=t4.getText();
    a1.setText("Successfully Inserted");
    try{
    Class.forName("com.mysql.jdbc.Driver");
    System.out.println("Connection is successful");
    Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","Aravindh@2023");
    Statement statement;
    statement=connection.createStatement();
    String query="insert into Registrations(names,roll_no,Address,Description)values(?,?,?,?)";
    PreparedStatement pstate=connection.prepareStatement(query);
    pstate.setString(1, name);
    pstate.setString(2, roll_no);
    pstate.setString(3, Address);
    pstate.setString(4,Desc);
    pstate.executeUpdate();
    
    }
    catch(Exception exception){
System.out.println("error");

    }
}


        });
        b8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                try{
 Class.forName("com.mysql.jdbc.Driver");
    System.out.println("Connection is successful");
    Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/customer","root","Aravindh@2023");
      Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM Registrations");

                    tableModel = new DefaultTableModel(new Object[] { "Name", "Roll no", "Address", "Description" },
                            0);
                    dataTable = new JTable(tableModel);

                    while (resultSet.next()) {
                        String name = resultSet.getString("names");
                        String roll_no = resultSet.getString("roll_no");
                        String address = resultSet.getString("Address");
                        String description = resultSet.getString("Description");

                        tableModel.addRow(new Object[] { name, roll_no, address, description });
                    }

                    JScrollPane scrollPane = new JScrollPane(dataTable);
                    scrollPane.setBounds(100, 350, 300, 200);
                    main.add(scrollPane);
                }
                catch(Exception e){}
            }
        });
    }
}
