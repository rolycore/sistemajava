/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class ClienteDao {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarCliente(Cliente cl){
        String sql = "INSERT INTO clientes (dni, nombre, sexo, grado) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getSexo());
            ps.setString(4, cl.getGrado());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
   public List ListarCliente(){
       List<Cliente> ListaCl = new ArrayList();
       String sql = "SELECT * FROM clientes";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Cliente cl = new Cliente();
               cl.setId(rs.getInt("id"));
               cl.setDni(rs.getString("dni"));
               cl.setNombre(rs.getString("nombre"));
               cl.setSexo(rs.getString("sexo"));
               cl.setGrado(rs.getString("grado"));
               ListaCl.add(cl);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, e.toString(),
      "Ocurrio un Error!", JOptionPane.ERROR_MESSAGE);
       }
       return ListaCl;
   }
   
   public boolean EliminarCliente(int id){
       String sql = "DELETE FROM clientes WHERE id = ?";
       try {
           ps = con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, e.toString(),
      "Ocurrio un Error!", JOptionPane.ERROR_MESSAGE);
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException ex) {
               System.out.println(ex.toString());
           }
       }
   }
   
   public boolean ModificarCliente(Cliente cl){
       String sql = "UPDATE clientes SET dni=?, nombre=?, sexo=?, grado=? WHERE id=?";
       try {
           ps = con.prepareStatement(sql);   
           ps.setString(1, cl.getDni());
           ps.setString(2, cl.getNombre());
           ps.setString(3, cl.getSexo());
           ps.setString(4, cl.getGrado());
           ps.setInt(5, cl.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, e.toString(),
      "Ocurrio un Error!", JOptionPane.ERROR_MESSAGE);
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
                JOptionPane.showMessageDialog(null, e.toString(),
      "Ocurrio un Error!", JOptionPane.ERROR_MESSAGE);
           }
       }
   }
   
   public Cliente Buscarcliente(String ced){
       Cliente cl = new Cliente();
       String sql = "SELECT * FROM clientes WHERE dni = ?";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, ced);
           rs = ps.executeQuery();
           if (rs.next()) {
               cl.setId(rs.getInt("id"));
               cl.setNombre(rs.getString("nombre"));
               cl.setSexo(rs.getString("sexo"));
               cl.setGrado(rs.getString("grado"));
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, e.toString(),
      "Ocurrio un Error!", JOptionPane.ERROR_MESSAGE);
       }
       return cl;
   }
   
}
