import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            fecharConexao();
        }
    }
    
    public void atualizarFilme(int id, String novoTitulo) {
        conn = new conectaDAO().connectDB();
        String sql = "UPDATE Filmes SET titulo = ? WHERE id_filme = ?";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, novoTitulo);
            prep.setInt(2, id);
            int atualizado = prep.executeUpdate();
            
            if (atualizado > 0) {
                JOptionPane.showMessageDialog(null, "Filme atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum filme encontrado com esse ID.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar filme: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }
    
    public void excluirFilme(int id) {
        conn = new conectaDAO().connectDB();
        String sql = "DELETE FROM Filmes WHERE id_filme = ?";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            int deletado = prep.executeUpdate();
            
            if (deletado > 0) {
                JOptionPane.showMessageDialog(null, "Filme excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum filme encontrado com esse ID.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir filme: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }
    
    private void fecharConexao() {
        try {
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
