package br.com.projeto.dao;
import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Cliente;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ClienteDAO {
    private final Connection con;

    public ClienteDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void salvarCliente (Cliente cli) {
        try {
            String sql = "insert into tb_clientes(nome, rg, cpf, email, telefone, celular, cep, endereco , numero, complemento, bairro, cidade, estado)" +
                            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, cli.getNome());
                stmt.setString(2, cli.getRg());
                stmt.setString(3, cli.getCpf());
                stmt.setString(4, cli.getEmail());
                stmt.setString(5, cli.getTelefone());
                stmt.setString(6, cli.getCelular());
                stmt.setString(7, cli.getCep());
                stmt.setString(8, cli.getEndereco());
                stmt.setInt(9, cli.getNumero());
                stmt.setString(10, cli.getComplemento());
                stmt.setString(11, cli.getBairro());
                stmt.setString(12, cli.getCidade());
                stmt.setString(13, cli.getEstado());
                
                stmt.execute();
            }
            
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Algo aconteceu: \n"+e);
        }
    }
    public void editarCliente(Cliente cli) {
        try {
            String sql = "UPDATE tb_clientes SET nome=?, rg=?, cpf=?, email=?, telefone=?, celular=?,"
                    + "cep=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, estado=?"
                    + "WHERE id = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, cli.getNome());
                stmt.setString(2, cli.getRg());
                stmt.setString(3, cli.getCpf());
                stmt.setString(4, cli.getEmail());
                stmt.setString(5, cli.getTelefone());
                stmt.setString(6, cli.getCelular());
                stmt.setString(7, cli.getCep());
                stmt.setString(8, cli.getEndereco());
                stmt.setInt(9, cli.getNumero());
                stmt.setString(10, cli.getComplemento());
                stmt.setString(11, cli.getBairro());
                stmt.setString(12, cli.getCidade());
                stmt.setString(13, cli.getEstado());
                stmt.setInt(14, (int) cli.getId());
                stmt.execute();
            }
            
            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Algo aconteceu: \n"+e);
        }
    }
    public void excluirCliente(Cliente cli) {
        try {
            String sql = "DELETE FROM tb_clientes WHERE id = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, (int) cli.getId());
                stmt.execute();
            }
            
            JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Algo aconteceu: \n"+e);
        }
    }
    
    public List<Cliente> ListarClientes(){
        try {
            List<Cliente> clientes = new ArrayList<>(); 
            
            String sql = "SELECT * FROM tb_clientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cliente cli =  new Cliente();
                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCelular(rs.getString("celular"));
                cli.setCep(rs.getString("cep"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setNumero(rs.getInt("numero"));
                cli.setComplemento(rs.getString("complemento"));
                cli.setBairro(rs.getString("bairro"));
                cli.setCidade(rs.getString("cidade"));
                cli.setEstado(rs.getString("estado"));
                
                clientes.add(cli);
            }
            
            return clientes;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar consulta: \n"+e);
            return null;
        }
    }
    
    
    
    }
    
