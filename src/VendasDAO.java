import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

class VendasDAO {
    Connection conn;
    PreparedStatement prep;
    
    public void vender(Vendas venda) {
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO Vendas (id_sessao, valor_total) VALUES (?, ?)";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setInt(1, venda.getIdSessao());
            prep.setDouble(2, venda.getValorTotal());
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar venda: " + e.getMessage());
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
