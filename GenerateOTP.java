package service;
import java.util.Random;

public class GenerateOTP {
    public static String getOTP(){
        try{
            Random random = new Random();
            return String.format("%06d",random.nextInt(1000000));
        }catch(Exception e){
            System.out.println(e);
        }
        return "000000";
    }
}