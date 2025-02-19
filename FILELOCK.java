package mydisplay;

import mydisplay.UserView;
import mydisplay.ForgetPassword;
import mydisplay.SignUp;
import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class FILELOCK extends JFrame implements ActionListener{
    private String OTP;
    private JLabel label1, label2, label3, label4;
    private JTextField field1, field2;
    private JButton button1, button2, button3;
    private JPanel P1,P2;

    public FILELOCK(){
        super("FILELOCK");
        try{
            //setBounds(350,150,850,450);
            setLayout(null);

            getContentPane().setBackground(Color.WHITE);

            P1 = new JPanel();
            P1.setBackground(Color.WHITE);
            P1.setBounds(0,0,500,500);
            P1.setLayout(null);
            add(P1);

            label4 = new JLabel("WELCOME TO THE FILELOCK");
            //label4.setForeground(new Color(119, 82, 254));
            label4.setBounds(90,30,350,30);
            label4.setFont(new Font("Raleway", Font.BOLD, 22));
            
            ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.gif"));
            Image i1 = img1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
            ImageIcon img2 = new ImageIcon(i1);
            JLabel image1 = new JLabel(img2);
            image1.setBounds(70, 90, 300, 300);
            P1.add(label4);
            P1.add(image1);

            P2 = new JPanel();
            P2.setLayout(null);
            P2.setBackground(new Color(131, 193, 233));
            P2.setBounds(500,0,570,500);
            add(P2);

            label1 = new JLabel("Enter Email");
            label1.setBounds(60,20,120,25);
            label1.setFont(new Font("SAN_SERIF",Font.PLAIN, 20));
            field1 = new JTextField();
            field1.setBounds(60,60,350,30);
            field1.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field1.setBorder(BorderFactory.createEmptyBorder());
            P2.add(label1);
            P2.add(field1);

            label2 = new JLabel("Enter Password");
            label2.setBounds(60,110,150,25);
            label2.setFont(new Font("SAN_SERIF",Font.PLAIN, 20));
            field2 = new JTextField();
            field2.setBounds(60,150,350,30);
            field2.setFont(new Font("Helvetica",Font.PLAIN, 14));
            field2.setBorder(BorderFactory.createEmptyBorder());
            P2.add(label2);
            P2.add(field2);

            button1 = new JButton("Login");
            button1.setBounds(60,200,150,40);
            P2.add(button1);

            button2 = new JButton("SingUp");
            button2.setBounds(250,200,150,40);
            P2.add(button2);

            label3 = new JLabel("If You Forget Your Password");
            label3.setBounds(150,270,300,25);
            label3.setForeground(Color.RED);
            button3 = new JButton("Forget Password");
            button3.setBounds(110,300,250,40);
            P2.add(label3);
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
            String email = field1.getText();
            String password = field2.getText();

            if(ae.getSource() == button1){
                login(email, password);
            }else if(ae.getSource() == button2){
                signUp();
                setVisible(false);
            }else if(ae.getSource() == button3){
                new ForgetPassword();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void login(String email, String password){
        try{
            if (UserDAO.isExists(email)){
                String con_password = UserDAO.getPassword(email);

                if(con_password.equals(password)){
                    UserView urs = new UserView(email);
                    setVisible(false);
                    
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Password");
                }   
            }else
                JOptionPane.showMessageDialog(null,"User Not Found");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void signUp() {
        try {
            OTP = GenerateOTP.getOTP();
            SignUp su = new SignUp(OTP);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // Application Stating Point.
    public static void main(String[] args){
        FILELOCK f = new FILELOCK();

        int x = 1; 
        for(int i = 1; i <= 460; x+=7, i+=6){
            f.setLocation(750-(x+i)/2, 400-(i/2));    
            f.setSize(i+x, i);
            try{
                Thread.sleep(10);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
}