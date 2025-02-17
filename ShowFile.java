package mydisplay;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.*;
import mydatabase.MyConnection;

public class ShowFile extends JFrame implements ActionListener{
    private String email;
    JLabel label1, label2, label3;
    JButton button1;
    JTable table1;
    JPanel P1;

    public ShowFile(String email){
        this.email = email;
        try{
            setTitle("Show Hiding File");
            setBounds(400,100,900,600);
            setLayout(null);

            P1 = new JPanel();
            P1.setBounds(0,0,900,600);
            P1.setBackground(new Color(205, 245, 253));
            P1.setLayout(null);
            add(P1);

            P1.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "HIDING FILE", TitledBorder.CENTER, TitledBorder.TOP));

            label1 = new JLabel("ID");
            label1.setBounds(6,30,120,30);
            label1.setFont(new Font("SAN_SERIF",Font.BOLD, 14));
            P1.add(label1);

            label2 = new JLabel("File Name");
            label2.setBounds(305,30,120,30);
            label2.setFont(new Font("SAN_SERIF",Font.BOLD, 14));
            P1.add(label2);

            label3 = new JLabel("Path");
            label3.setBounds(605,30,120,30);
            label3.setFont(new Font("SAN_SERIF",Font.BOLD, 14));
            P1.add(label3);

            table1 = new JTable();
            table1.setBounds(5,60,900,400);
            table1.setBackground(new Color(205, 245, 253));
            P1.add(table1);
            
            Connection con = MyConnection.getConnection();
            String q = "select id, name, path from data where email = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1,email);
            ResultSet set = ps.executeQuery();

            table1.setModel(DbUtils.resultSetToTableModel(set));

            button1 = new JButton("Back");
            button1.setBounds(400,500,100,30);
            button1.setBackground(Color.WHITE);
            P1.add(button1);

            button1.addActionListener(this);  

            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent ae){
        try{
            if(ae.getSource() == button1){
                setVisible(false);
                new UserView(this.email); 
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
