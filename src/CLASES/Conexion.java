/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author 21Ernesto00
 */
public class Conexion {
    public String [] opciones = {"Aceptar","Cancelar"};
    public int i;
    private Connection coneccion;
    private PreparedStatement comando;
    private String host="localhost", puerto="3306", bdd="boutique_ernesto", usuario="root", clave="";
    Prenda prenda = new Prenda();
    public Connection conectar()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            coneccion=DriverManager.getConnection("jdbc:mysql://"+host+":"+puerto+"/"+bdd, usuario, clave);
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "ERROR DE CONECCION " + e.getMessage());
            coneccion=null;
        }
        return coneccion;
    }
    public void guardarCartegoria(Categoria categoria1){
        i = JOptionPane.showOptionDialog(null, "¿Desea terminar el proceso ejecutado?", "Pregunta",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
        if(i==0){
            try{
                coneccion=conectar();
                comando=coneccion.prepareStatement("insert into categorias values (null,?,?)");
                comando.setString(1, categoria1.getNombre());
                comando.setString(2, categoria1.getDescripcion());
                comando.executeUpdate();
//                JOptionPane.showMessageDialog(null, "CATEGORIA GUARDADA").timer.schedule(timerTask, 0, 1000);
                JOptionPane.showMessageDialog(null, "CATEGORIA GUARDADA");

            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }else{}
    }
    public void guardarPrenda(Prenda prenda){
        i = JOptionPane.showOptionDialog(null, "¿Desea terminar el proceso ejecutado?", "Pregunta",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
        if(i==0){
            try{
                coneccion=conectar();
                comando=coneccion.prepareStatement("insert into prendas values (null,?,?,?,?,?,?)");
                comando.setString(1, prenda.getNombre());
                comando.setString(2, prenda.getModelo());
                comando.setString(3, prenda.getTalla());
                comando.setDouble(4, prenda.getPrecio());
                comando.setInt(5, prenda.getStock());
                comando.setInt(6, prenda.getFk_idCategoria());
                comando.executeUpdate();
                JOptionPane.showMessageDialog(null, "PRENDA GUARDADA");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }else{}
    }
    
    public boolean eliminarCategoria(int id)
    {
        i = JOptionPane.showOptionDialog(null, "¿Desea terminar el proceso ejecutado?", "Pregunta",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
        if(i==0){
            try{
                coneccion=conectar();
                comando=coneccion.prepareStatement("delete from categorias where IDCATEGORIA=?");
                comando.setInt(1, id);
                comando.executeUpdate();
                return true;
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage()); 
                return false;
            }
        }else{return true;}
    }
    
    public boolean eliminarPrenda(int id)
    {
        i = JOptionPane.showOptionDialog(null, "¿Desea terminar el proceso ejecutado?", "Pregunta",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
        if(i==0){
            try{
                coneccion=conectar();
                comando=coneccion.prepareStatement("delete from prendas where IDPRENDA=?");
                comando.setInt(1, id);
                comando.executeUpdate();
                return true;
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage()); 
                return false;
            }
        }else{return true;}
    }
    
    public ArrayList<Categoria> mostrarCategoria(String dato){
        ArrayList<Categoria> listaCategoria =new ArrayList<>();
        ResultSet rs;
        try{
            coneccion=conectar();
            comando=coneccion.prepareStatement("select * from categorias where NOMBRE like '%"+dato+"%' ");
            rs=comando.executeQuery();
            while (rs.next()){
                Categoria categoria1=new Categoria();
                categoria1.setIdcategoria(rs.getInt(1));
                categoria1.setNombre(rs.getString(2));
                categoria1.setDescripcion(rs.getString(3));
                listaCategoria.add(categoria1);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listaCategoria;
    }
    
    
    public ArrayList<Prenda> mostrarPrenda(String dato){
        ArrayList<Prenda> listaPrenda =new ArrayList<>();
        ResultSet rs;
        try{
            coneccion=conectar();
            comando=coneccion.prepareStatement("select * from prendas where NOMBRE like '%"+dato+"%' ");
            rs=comando.executeQuery();
            while (rs.next()){
                Prenda prenda=new Prenda();
                prenda.setIdPrendas(rs.getInt(1));
                prenda.setNombre(rs.getString(2));
                prenda.setModelo(rs.getString(3));
                prenda.setTalla(rs.getString(4));
                prenda.setPrecio(rs.getFloat(5));
                prenda.setStock(rs.getInt(6));
                prenda.setFk_idCategoria(rs.getInt(7));
                listaPrenda.add(prenda);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listaPrenda;
    }
    
    public void actualizarCategoria(Categoria categoria1){  
        i = JOptionPane.showOptionDialog(null, "¿Desea terminar el proceso ejecutado?", "Pregunta",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
        if(i==0){
            try{
                coneccion=conectar();
                comando=coneccion.prepareStatement("update categorias set NOMBRE=?, DESCRIPCION=? where IDCATEGORIA=? ");
                comando.setString(1, categoria1.getNombre());
                comando.setString(2, categoria1.getDescripcion());
                comando.setInt(3, categoria1.getIdcategoria());  //Añadir
                comando.executeUpdate();
                JOptionPane.showMessageDialog(null, "CATEGOTRIA ACTUALIZADA");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }else{}
    }
    
    public void actualizarPrenda(Prenda prenda){    
        i = JOptionPane.showOptionDialog(null, "¿Desea terminar el proceso ejecutado?", "Pregunta",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
        if(i==0){
            try{
                coneccion=conectar();
                comando=coneccion.prepareStatement("update prendas set NOMBRE=?, MODELO=?, TALLA=?, PRECIO=?, STOCK=?, FK_IDCATEGORIA=? where IDPRENDA=? ");
                comando.setString(1, prenda.getNombre());
                comando.setString(2, prenda.getModelo());
                comando.setString(3, prenda.getTalla());
                comando.setDouble(4, prenda.getPrecio());
                comando.setInt(5, prenda.getStock()); 
                comando.setInt(6, prenda.getIdPrendas());  //Añadir
                comando.setInt(7,prenda.getFk_idCategoria());
                comando.executeUpdate();
                JOptionPane.showMessageDialog(null, "PRENDA ACTUALIZADA");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }else{}
    }
    
}
