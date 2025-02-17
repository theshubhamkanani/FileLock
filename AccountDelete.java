package mydisplay;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.*;
import mydatabase.MyConnection;
import service.GenerateOTP;

public class AccountDelete extends JFrame implements ActionListener{
    private String email;
    JLabel label1, label2, label3;
    JTextField field1, field2;
    JButton button1, button2, button3;
    JPanel P1, P2;

    public AccountDelete(String email){
        this.email = email;
        try{
            setTitle("DELETE ACCOUNT");
            setBounds(400,150,800,400);
            setLayout(null);

            P1 = new JPanel();
            P1.setBounds(0,0,450,400);
            P1.setBackground(new Color(205, 245, 253));
            P1.setLayout(null);
            add(P1);

            P2 = new JPanel();
            P2.setBounds(450,0,350,400);
            P2.setBackground(Color.WHITE);
            P2.setLayout(null);
            add(P2);

            ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icon/delete.png"));
            Image i1 = img1.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
            ImageIcon img2 = new ImageIcon(i1);
            JLabel image1 = new JLabel(img2);
            image1.setBounds(30,30,300,300);
            P2.add(image1);

            label1 = new JLabel("Do You Want To Delete This Account");
            label1.setBounds(20,20,350,30);
            label1.setForeground(Color.RED);
            label1.setFont(new Font("SAN_SERIF",Font.BOLD, 14));
            P1.add(label1);

            label2 = new JLabel("Enter Email");
            label2.setBounds(40,50,120,30);
            label2.setFont(new Font("SAN_SERIF",Font.BOLD, 14));

            field1 = new JTextField();
            field1.setBounds(40,90,350,30);
            field1.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field1.setBorder(BorderFactory.createEmptyBorder());
            P1.add(label2);
            P1.add(field1);

            label3 = new JLabel("Enter Password");
            label3.setBounds(40,130,120,30);
            label3.setFont(new Font("SAN_SERIF",Font.BOLD, 14));

            field2 = new JTextField();
            field2.setBounds(40,170,350,30);
            field2.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field2.setBorder(BorderFactory.createEmptyBorder());
            P1.add(label3);
            P1.add(field2);

            button1 = new JButton("Delete");
            button1.setBounds(40,220,100,30);
            button1.setBackground(Color.WHITE);
            P1.add(button1);

            button2 = new JButton("Back");
            button2.setBounds(170,220,100,30);
            button2.setBackground(Color.WHITE);
            P1.add(button2);

            button3 = new JButton("Forget Password");
            button3.setBounds(55,270,200,30);
            button3.setBackground(Color.WHITE);
            P1.add(button3);

            button1.addActionListener(this);
            button2.addActionListener(this);
            button3.addActionListener(this);  

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
                Connection con = MyConnection.getConnection();
                String q = "delete from users where email = ?";
                PreparedStatement st = con.prepareStatement(q);

                st.setString(1,this.email);
                int response = st.executeUpdate();

                switch (response){
                    case 0 :
                        JOptionPane.showMessageDialog(null,"User Not Deleted.");
                        break;
                    case 1 :
                        JOptionPane.showMessageDialog(null,"User Successfully Deleted.");
                        break;
                }
                setVisible(false);
                FILELOCK f = new FILELOCK();
                f.setBounds(255, 171, 990, 457);
            } else if (ae.getSource() == button2) {
                
                new UserView(this.email);
                setVisible(false);
                
            }else if(ae.getSource() == button3){
                new ForgetPassword();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
