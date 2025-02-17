package mydisplay;

import mydatabase.MyConnection;
import service.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgetPassword extends JFrame implements ActionListener{
    private String OTP;
    JLabel label1, label2, label3, label4;
    JTextField field1, field2, field3, field4;
    JButton button1,button2;
    JPanel P1,P2;

    public ForgetPassword(){
        this.OTP = GenerateOTP.getOTP();
        try{
            setTitle("Forget Password");
            setBounds(270,150,950,490);
            setLayout(null);
            getContentPane().setBackground(Color.WHITE);

            P1 = new JPanel();
            P1.setBackground(new Color(131, 193, 233));
            P1.setBounds(0,0,500,500);
            P1.setLayout(null);
            add(P1);

            label1 = new JLabel("Enter Email");
            label1.setBounds(40,50,120,25);
            label1.setFont(new Font("SAN_SERIF",Font.PLAIN, 20));
            field1 = new JTextField();
            field1.setBounds(40,90,350,30);
            field1.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field1.setBorder(BorderFactory.createEmptyBorder());
            P1.add(label1);
            P1.add(field1);

            label2 = new JLabel("Enter Password");
            label2.setBounds(40,130,150,25);
            label2.setFont(new Font("SAN_SERIF",Font.PLAIN, 20));
            field2 = new JTextField();
            field2.setBounds(40,170,250,30);
            field2.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field2.setBorder(BorderFactory.createEmptyBorder());
            P1.add(label2);
            P1.add(field2);

            label3 = new JLabel("Re-Enter Password");
            label3.setBounds(40,210,200,25);
            label3.setFont(new Font("SAN_SERIF",Font.PLAIN, 20));
            field3 = new JTextField();
            field3.setBounds(40,250,250,30);
            field3.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field3.setBorder(BorderFactory.createEmptyBorder());
            P1.add(label3);
            P1.add(field3);

            button1 = new JButton("Send OTP");
            button1.setBounds(40,300,150,30);
            button1.setBackground(Color.WHITE);
            P1.add(button1);

            label4 = new JLabel("Enter OTP");
            label4.setBounds(40,350,120,25);
            label4.setFont(new Font("SAN_SERIF",Font.PLAIN, 20));
            field4 = new JTextField();
            field4.setBounds(40,390,120,30);
            field4.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field4.setBorder(BorderFactory.createEmptyBorder());
            P1.add(label4);
            P1.add(field4);

            button2 = new JButton("Forget Password");
            button2.setBounds(200,390,200,30);
            button2.setBackground(Color.WHITE);
            P1.add(button2);

            P2 = new JPanel();
            P2.setBackground(new Color(209,219,252));
            P2.setLayout(null);
            P2.setBounds(500,0,450,500);
            add(P2);

            ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icon/FPassword.gif"));
            Image i1 = img1.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
            ImageIcon img2 = new ImageIcon(i1);
            JLabel image1 = new JLabel(img2);
            image1.setBounds(70,70,300,300);
            P2.add(image1);

            button1.addActionListener(this);
            button2.addActionListener(this);

            setResizable(false);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
            setVisible(true);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent ae){
        try{
            String email = field1.getText();
            if(ae.getSource() == button1){
                SendOTP.sendOTP(email, this.OTP);
                JOptionPane.showMessageDialog(null,"OTP Send Successfully");
            }else if(ae.getSource() == button2){
                String otp = field4.getText();
                String password = field2.getText();
                String re_password = field3.getText();
                if(this.OTP.equals(otp)){
                    if(password.equals(re_password)){
                        Connection con = MyConnection.getConnection();
                        String q = "update Users set password = ? where email = ?";
                        PreparedStatement ps = con.prepareStatement(q);
                        ps.setString(1, password);
                        ps.setString(2, email);
                        int result = ps.executeUpdate();
                        if(result == 1){
                        JOptionPane.showMessageDialog(null,"Password Successfully Updated");
                        setVisible(false);
                        }else{
                            JOptionPane.showMessageDialog(null,"Password Not Updated, Please try Again");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Both Password are Not Same");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"OTP Invalid");
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}