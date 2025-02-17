package mydisplay;

import model.Data;
import mydatabase.MyConnection;
import dao.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import java.io.FileInputStream;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.*;


public class UserView extends JFrame implements ActionListener{
    private String email;
    JButton button1, button2, button3, button4;
    JLabel label1, label2, label3;
    JPanel P1,P2,P3;
    String[] columnNames = { "ID", "NAME"};

    public UserView(String email){
        this.email = email;
        try{
            setTitle(this.email);
            setBounds(300,110,800,500);
            setLayout(null);

            P1 = new JPanel();
            P1.setBackground(new Color(131, 193, 233));
            P1.setBounds(0,0,800,40);
            P1.setLayout(null);
            add(P1);

            ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icon/FILELOCK.png"));
            Image i1 = img1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
            ImageIcon img2 = new ImageIcon(i1);
            JLabel image1 = new JLabel(img2);
            image1.setBounds(10,10,30,30);
            P1.add(image1);

            label1 = new JLabel("FILELOCK");
            label1.setBounds(50,10,120,30);
            //label1.setForeground(Color.WHITE);
            label1.setFont(new Font("Tahoma",Font.BOLD, 20));
            P1.add(label1);

            P2 = new JPanel();
            P2.setBackground(new Color(131, 193, 233));
            P2.setBounds(0,0,200,500);
            P2.setLayout(null);
            add(P2);

            button1 = new JButton("Show Hidden File");
            button1.setBounds(20,80,150,30);
            button1.setBackground(Color.WHITE);
            P2.add(button1);

            button2 = new JButton("Hide a new File");
            button2.setBounds(20,180,150,30);
            button2.setBackground(Color.WHITE);
            P2.add(button2);

            button3 = new JButton("Un-Hide a File");
            button3.setBounds(20,280,150,30);
            button3.setBackground(Color.WHITE);
            P2.add(button3);

            button4 = new JButton("Delete Account");
            button4.setBounds(20,380,150,30);
            button4.setBackground(Color.WHITE);
            P2.add(button4);

            P3 = new JPanel();
            P3.setBounds(200,40,600,460);
            P3.setBackground(new Color(240, 227, 202));
            P3.setLayout(null);
            add(P3);

            ImageIcon img_2 = new ImageIcon(ClassLoader.getSystemResource("icon/File.gif"));
            Image i2 = img_2.getImage().getScaledInstance(400,400,Image.SCALE_DEFAULT);
            ImageIcon img3 = new ImageIcon(i2);
            JLabel image_1 = new JLabel(img3);
            image_1.setBounds(100,10,400,400);
            P3.add(image_1);

            button1.addActionListener(this);
            button2.addActionListener(this);
            button3.addActionListener(this);
            button4.addActionListener(this);

            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void hideFile(){
        try{
            JFileChooser j = new JFileChooser("C:\\Users\\shubh\\Desktop");
            j.setDialogTitle("Select File You Want Hide");
            j.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter ff = new FileNameExtensionFilter("Only .txt files", "txt");
            j.addChoosableFileFilter(ff);
            j.showOpenDialog(null);
            File f = j.getSelectedFile();
            Data file = new Data(0,f.getName(), f.getPath(), this.email);
            int result = DataDAO.makeFileHide(file);
            if(result == 1){
                JOptionPane.showMessageDialog(null,"File is Successfully Hide");
            }else{
                JOptionPane.showMessageDialog(null,"File can't Hide");
            }
            setVisible(false);
            new ShowFile(this.email);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == button1){
            setVisible(false);
            new ShowFile(this.email);
        }else if(ae.getSource() == button2){
            hideFile();
        }else if(ae.getSource() == button3){
            setVisible(false);
            new UnHideFile(this.email);
        }else if(ae.getSource() == button4){
            setVisible(false);
            new AccountDelete(this.email);
        }
    }
}