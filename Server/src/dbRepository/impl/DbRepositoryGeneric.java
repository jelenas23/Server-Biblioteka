/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbRepository.impl;

import dbRepository.DbConnectionFactory;
import dbRepository.DbRepository;
import domain.GenericEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jelena
 */
public class DbRepositoryGeneric implements DbRepository<GenericEntity>{

   

    @Override
    public void add(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(entity.getNazivTabele())
                    .append(" (").append(entity.getKoloneZaDodavanje()).append(")")
                    .append(" VALUES (")
                    .append(entity.vrednostiZaDodavanje())
                    .append(")");
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                int id = rsKey.getInt(1);
                entity.setId(id);
            }
            statement.close();
            rsKey.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void edit(GenericEntity param) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ")
                    .append(param.getNazivTabele())
                    .append(" SET ").append(param.getVrednostiZaEdit()).append(" ")
                    .append("WHERE ")
                    .append(param.getPrimarniKljuc());
                    
        System.out.println(sb);
        Statement s = connection.createStatement();
        s.executeUpdate(sb.toString());
        connection.close();
    }

    @Override
    public void delete(GenericEntity param) throws Exception {
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ")
                    .append(param.getNazivTabele())
                    .append(" WHERE ").append(param.getKriterijumZaBrisanje());
         
        Statement s = connection.createStatement();
        s.executeUpdate(sb.toString());
    }

    @Override
    public List<GenericEntity> getAll(GenericEntity param) throws Exception {
         List<GenericEntity> data = new ArrayList<>();
        
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ")
                    .append(param.getNazivTabele());
           System.out.println(sb.toString());
           Statement st = connection.createStatement();
           ResultSet rs = st.executeQuery(sb.toString());
           data=param.dodajNovi(rs);
         
           rs.close();
           st.close();
           if(data.size()==0){
               throw new Exception("Nema podataka u bazi.");
           }         
       
        return data;
    }

    @Override
    public GenericEntity getOne(GenericEntity param) throws Exception {
       
         GenericEntity jedan;
        
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            
            sb.append("SELECT * FROM ")
                    .append(param.getNazivTabele())
                    .append(" WHERE ")
                    .append(param.getKriterijumZaJednog());
            String query = sb.toString();
            System.out.println(query);
           Statement st = connection.createStatement();
           ResultSet rs = st.executeQuery(query);
           jedan=param.dodajJednog(rs);
         
           rs.close();
           st.close();
   
        return jedan;
        
    }

    @Override
    public List<GenericEntity> getPoUslovu(GenericEntity param) throws Exception{
        List<GenericEntity> data=new ArrayList<>();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ")
                .append(param.getNazivTabele())
                .append(" WHERE ")
                .append(param.getKriterijumZaJednog());
        String query = sb.toString();
            System.out.println(query);
            
            Statement statement = connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
        
            data=param.dodajNovi(rs);
            rs.close(); statement.close();
            return data;
    }

    @Override
    public List<GenericEntity> getAllJoin(GenericEntity param) throws Exception {
        List<GenericEntity> data=new ArrayList<>();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        String upit = "SELECT k.idKnjige,k.nazivKnjige,k.idAutora,a.idAutora,a.ime,a.prezime,k.idZanra,z.idZanra,z.nazivZanra,k.polica,k.godinaIzdanja,k.brPrimeraka\n" +
"FROM knjiga k INNER JOIN autor a ON k.idAutora=a.idAutora \n" +
"INNER JOIN zanr z ON k.idZanra = z.idZanra";
        
        Statement statement = connection.createStatement();
        ResultSet rs=statement.executeQuery(upit);
        
        data=param.dodajNovi(rs);
            rs.close(); statement.close();
            System.out.println(upit);
            return data;
    }

    @Override
      public List<GenericEntity> getJoinPoUslovu(GenericEntity param) throws Exception{
        List<GenericEntity> data=new ArrayList<>();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        String upit = "SELECT k.idKnjige,k.nazivKnjige,k.idAutora,a.idAutora,a.ime,a.prezime,k.idZanra,z.idZanra,z.nazivZanra,k.polica,k.godinaIzdanja,k.brPrimeraka\n" +
"FROM knjiga k INNER JOIN autor a ON k.idAutora=a.idAutora \n" +
"INNER JOIN zanr z ON k.idZanra = z.idZanra WHERE k."+param.getKriterijumZaJednog();
        
            System.out.println(upit);
            
            Statement statement = connection.createStatement();
            ResultSet rs=statement.executeQuery(upit);
        
            data=param.dodajNovi(rs);
            rs.close(); statement.close();
            return data;
    }

}
