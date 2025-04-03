import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FilmesDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    
    public void cadastrarFilme(Filmes filme) {
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO Filmes (titulo, diretor, genero, classificacao) VALUES (?, ?, ?, ?)";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, filme.getTitulo());
            prep.setString(2, filme.getDiretor());
            prep.setString(3, filme.getGenero());
            prep.setString(4, filme.getClassificacao());
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Filme cadastrado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar filme: " + e.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
    
}
