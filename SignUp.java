package mydisplay;

import model.*;
import service.*;
import dao.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame implements ActionListener{
    private String OTP;
    JLabel label1, label2, label3, label4, label5;
    JTextField field1, field2, field3, field4, field5;
    JButton button1, button2, button3;
    JPanel P1,P2;

    public SignUp(String OTP){
        this.OTP = OTP;
        try{
            setTitle("SignUp Form");
            setBounds(270,110,950,600);
            setLayout(null);

            P1 = new JPanel();
            P1.setBackground(Color.WHITE);
            P1.setBounds(0,0,400,600);
            P1.setLayout(null);
            add(P1);

            ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icon/SignUp.gif"));
            Image i1 = img1.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
            ImageIcon img2 = new ImageIcon(i1);
            JLabel image1 = new JLabel(img2);
            image1.setBounds(50,100,300,300);
            P1.add(image1);

            P2 = new JPanel();
            P2.setLayout(null);
            P2.setBackground(new Color(131, 193, 233));
            P2.setBounds(400,0,550,600);
            add(P2);

            label1 = new JLabel("Enter Name");
            label1.setBounds(40,20,120,25);
            label1.setFont(new Font("SAN_SERIF",Font.PLAIN, 20));
            field1 = new JTextField();
            field1.setBounds(40,60,350,30);
            field1.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field1.setBorder(BorderFactory.createEmptyBorder());
            P2.add(label1);
            P2.add(field1);

            label2 = new JLabel("Enter Email");
            label2.setBounds(40,100,120,25);
            label2.setFont(new Font("SAN_SERIF",Font.PLAIN, 20));
            field2 = new JTextField();
            field2.setBounds(40,140,350,30);
            field2.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field2.setBorder(BorderFactory.createEmptyBorder());
            P2.add(label2);
            P2.add(field2);

            button1 = new JButton("Send OTP");
            button1.setBounds(40,200,140,40);
            P2.add(button1);

            label3 = new JLabel("Enter OTP");
            label3.setBounds(200,200,120,25);
            label3.setFont(new Font("SAN_SERIF",Font.PLAIN, 20));
            field3 = new JTextField();
            field3.setBounds(320,200,120,30);
            field3.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field3.setBorder(BorderFactory.createEmptyBorder());
            P2.add(label3);
            P2.add(field3);

            label4 = new JLabel("Enter Password");
            label4.setBounds(40,280,150,25);
            label4.setFont(new Font("SAN_SERIF",Font.PLAIN, 20));
            field4 = new JTextField();
            field4.setBounds(40,320,250,30);
            field4.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field4.setBorder(BorderFactory.createEmptyBorder());
            P2.add(label4);
            P2.add(field4);

            label5 = new JLabel("Re-Enter Password");
            label5.setBounds(40,360,200,25);
            label5.setFont(new Font("SAN_SERIF",Font.PLAIN, 20));
            field5 = new JTextField();
            field5.setBounds(40,400,250,30);
            field5.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field5.setBorder(BorderFactory.createEmptyBorder());
            P2.add(label5);
            P2.add(field5);

            button2 = new JButton("SingUp");
            button2.setBounds(40,450,250,40);
            P2.add(button2);

            button3 = new JButton("Back");
            button3.setBounds(300,450,150,40);
            P2.add(button3);

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
            String name = field1.getText();
            String email = field2.getText();

            if(ae.getSource() == button1){
                SendOTP.sendOTP(email, this.OTP);
                JOptionPane.showMessageDialog(null,"OTP Send Successfully");

            }else if(ae.getSource() == button2){
                String otp = field3.getText();
                String password = field4.getText();
                String re_password = field5.getText();

                if(this.OTP.equals(otp)){
                    if(password.equals(re_password)){
                        User user = new User(name, email, password);
                        boolean response = UserDAO.isExists(email);
                        if(response){
                            JOptionPane.showMessageDialog(null,"User already Exists");
                        }else{
                            UserDAO.saveUser(user);
                            JOptionPane.showMessageDialog(null,"User Registered");
                            setVisible(false);
                            FILELOCK f = new FILELOCK();
                            f.setBounds(255, 171, 990, 457);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Both Password are Not Same");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"OTP Invalid");
                }
            }else if(ae.getSource() == button3){
                setVisible(false);
                FILELOCK f = new FILELOCK();
                f.setBounds(255, 171, 990, 457);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}