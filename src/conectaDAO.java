import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class conectaDAO {
    
    public Connection connectDB(){
        Connection conn = null;
        
        try {
        
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cinemadb?allowPublicKeyRetrieval=true&useSSL=false", 
                "root", 
                "mySQL121105");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
}
