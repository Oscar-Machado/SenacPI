import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SessaoDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    
    public void cadastrarSessao(Sessao sessao) {
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO Sessoes (id_filme, horario, preco) VALUES (?, ?, ?)";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setInt(1, sessao.getIdFilme());
            prep.setString(2, sessao.getHorario());
            prep.setDouble(3, sessao.getPreco());
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Sessão cadastrada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar sessão: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
    
    public ArrayList<Sessao> listarSessoes() {
        ArrayList<Sessao> listagem = new ArrayList<>();
        String sql = "SELECT * FROM Sessoes";
        
        try {
            conn = new conectaDAO().connectDB();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Sessao sessao = new Sessao();
                sessao.setIdSessao(rs.getInt("id_sessao"));
                sessao.setIdFilme(rs.getInt("id_filme"));
                sessao.setHorario(rs.getString("horario"));
                sessao.setPreco(rs.getDouble("preco"));
                listagem.add(sessao);
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar sessões: " + e.getMessage());
        }
        
        return listagem;
    }
}
