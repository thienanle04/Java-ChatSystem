package server;
import org.mindrot.jbcrypt.BCrypt;

public class Hash {
    public static void main(String args[]) {
        String password = "admin123";
        String hased = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("Hased: " + hased);
    }
}
