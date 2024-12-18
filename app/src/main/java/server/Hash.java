package server;
import org.mindrot.jbcrypt.BCrypt;

public class Hash {
    public static void main(String args[]) {
        String password = "123";
        String hased = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("Hased: " + hased);
    }
}
