package mydisplay;

import dao.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.*;
import mydatabase.MyConnection;

public class UnHideFile extends JFrame implements ActionListener{
    private String email;
    JLabel label1, label2, label3, label4;
    JButton button1, button2;
    JTable table1;
    JTextField field1;
    JPanel P1;

    public UnHideFile(String email){
        this.email = email;
        try{
            setTitle("Hiding Files");
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

            label4 = new JLabel("Enter File ID You Want UnHide`");
            label4.setBounds(50,500,250,30);
            label4.setFont(new Font("SAN_SERIF",Font.BOLD, 14));
            P1.add(label4);

            field1 = new JTextField();
            field1.setBounds(300,500,150,30);
            field1.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field1.setBorder(BorderFactory.createEmptyBorder());
            P1.add(field1);

            button1 = new JButton("UnHide");
            button1.setBounds(500,500,100,30);
            button1.setBackground(Color.WHITE);
            P1.add(button1);

            button2 = new JButton("Back");
            button2.setBounds(650,500,100,30);
            button2.setBackground(Color.WHITE);
            P1.add(button2);

            button1.addActionListener(this); 
            button2.addActionListener(this);  

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
                int id = Integer.parseInt(field1.getText()); //parseInt
                int response = DataDAO.unHide(id);
                if(response == 1){
                    JOptionPane.showMessageDialog(null,"File UnHide Successfully");
                }else{
                    JOptionPane.showMessageDialog(null,"File Not UnHide");
                }  
                setVisible(false);
                new ShowFile(this.email);  
            }else if(ae.getSource() == button2){
                setVisible(false);
                new UserView(this.email); 
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
