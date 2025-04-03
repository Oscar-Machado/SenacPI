import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

class UsuarioDAO {
    public boolean validarLogin(String email, String senha) {
        Connection conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM Usuarios WHERE email = ? AND senha = ?";
        
        try {
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, email);
            prep.setString(2, senha);
            ResultSet resultSet = prep.executeQuery();
            
            if (resultSet.next()) {
                return true; 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao validar login: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return false;
    }
}