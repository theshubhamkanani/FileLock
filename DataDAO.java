package dao;

import model.Data;
import mydatabase.MyConnection;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class DataDAO {
    public static List<Data> showHideFile(String email) throws SQLException {
        Connection con = MyConnection.getConnection();
        String q = "select * from data where email = ?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setString(1,email);
        ResultSet set = ps.executeQuery();
        List<Data> files = new ArrayList<Data>();
        while (set.next()){
            int id = set.getInt(1);
            String filename = set.getString(2);
            String path = set.getString(3);
            files.add(new Data(id, filename, path));
        }
        return files;
    }

    public static int makeFileHide(Data file) throws SQLException, IOException {
        Connection con = MyConnection.getConnection();
        String q = "insert into data(name, path, email, bin_data) values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setString(1,file.getFileName());
        ps.setString(2, file.getPath());
        ps.setString(3, file.getEmail());
        File fp = new File(file.getPath());
        FileReader fr = new FileReader(fp);
        ps.setCharacterStream(4,fr,fp.length());
        int i = ps.executeUpdate();
        fr.close();
        fp.delete();
        return i;
    }

    public static int unHide(int id)throws SQLException, IOException{
        Connection con = MyConnection.getConnection();
        String q = "select path, bin_data from data where id = ?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1,id);
        ResultSet set = ps.executeQuery();
        set.next();
        String path = set.getString("path");
        //Blob b = set.getBlob("bin_data");
        Clob c = set.getClob("bin_data");
        Reader r = c.getCharacterStream();
        FileWriter fw = new FileWriter(path);
        int i;
        while((i = r.read()) != -1){
            fw.write((char) i);
        }
        fw.close();
        ps = con.prepareStatement("delete from data where id = ?");
        ps.setInt(1,id);
        return ps.executeUpdate();
    }
}
