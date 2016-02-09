/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ejb.Evento;
import ejb.Listaeventiutente;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import manager.GestoreListaEventiLocal;

/**
 *
 * @author Dario Gallo <your.name at your.org>
 */
public class Eventi {

    GestoreListaEventiLocal gestoreListaEventi = lookupGestoreListaEventiLocal();

    public List<Integer> getIdRegioni() throws ClassNotFoundException, SQLException {
        List<Integer> idregioni = new ArrayList<Integer>();
        try {
            // create our mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "root");
            String query = "SELECT * FROM regioni";
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                int id = rs.getInt("idRegione");
                idregioni.add(i++, id);
            }
            //regioni.set(0, "VUOTA");
            return idregioni;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idregioni;
    }

    public List<String> getRegioni() throws ClassNotFoundException, SQLException {

        List<String> regioni = new ArrayList<String>();
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "root");
            String query = "SELECT * FROM regioni";
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                String nome = rs.getString("nomeRegione");
                regioni.add(i++, nome);
            }
            //regioni.set(0, "VUOTA");
            return regioni;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return regioni;
    }

    public List<String> getProvince(int idregione) throws ClassNotFoundException, SQLException {
        List<String> province = new ArrayList<String>();
        try {
            // create our mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "root");
            String query = "SELECT * FROM province";
            // create the java statement
            java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                if (rs.getInt("idRegione") == idregione) {
                    String nome = rs.getString("nomeProvincia");
                    province.add(i++, nome);
                }
            }
            //regioni.set(0, "VUOTA");
            return province;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return province;
    }

    //Metodo non utilizzato al momento perchè nel DB i luoghi sono salvati con i nomi per intero
//    public List<String> getSiglaProvince(int idregione) throws ClassNotFoundException, SQLException {
//        List<String> sigla = new ArrayList<String>();
//        try {
//            // create our mysql database connection
//            String myDriver = "com.mysql.jdbc.Driver";
//            String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
//            Class.forName(myDriver);
//            Connection conn = DriverManager.getConnection(myUrl, "root", "root");
//            // our SQL SELECT query. 
//            // if you only need a few columns, specify them by name instead of using "*"
//            String query = "SELECT * FROM province";
//            // create the java statement
//            java.sql.Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            int i = 0;
//            while (rs.next()) {
//                if (rs.getInt("idRegione") == idregione) {
//                    String nome = rs.getString("siglaProvincia");
//                    sigla.add(i++, nome);
//                }
//            }
//            //regioni.set(0, "VUOTA");
//            return sigla;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sigla;
//    }
//
//    public List<String> getProvince(String sigla) throws ClassNotFoundException, SQLException {
//
//        List<String> province = new ArrayList<String>();
//
//        try {
//            // create our mysql database connection
//            String myDriver = "com.mysql.jdbc.Driver";
//            String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
//            Class.forName(myDriver);
//            Connection conn = DriverManager.getConnection(myUrl, "root", "root");
//
//            // our SQL SELECT query. 
//            // if you only need a few columns, specify them by name instead of using "*"
//            String query = "SELECT * FROM comuni";
//
//            // create the java statement
//            java.sql.Statement st = conn.createStatement();
//
//            ResultSet rs = st.executeQuery(query);
//            int i = 0;
//
//            while (rs.next()) {
//                if (rs.getString("Provincia").equals(sigla)) {
//                    String nome = rs.getString("Comune");
//                    province.add(i++, nome);
//                }
//            }
//
//            //regioni.set(0, "VUOTA");
//            return province;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return province;
//    }
//
//    public String getCittabySigla(String sigla) throws ClassNotFoundException, SQLException {
//
//        String citta = "";
//        try {
//            // create our mysql database connection
//            String myDriver = "com.mysql.jdbc.Driver";
//            String myUrl = "jdbc:mysql://localhost:3306/newsharegames?zeroDateTimeBehavior=convertToNull";
//            Class.forName(myDriver);
//            Connection conn = DriverManager.getConnection(myUrl, "root", "root");
//
//            // our SQL SELECT query. 
//            // if you only need a few columns, specify them by name instead of using "*"
//            String query = "SELECT * FROM province";
//
//            // create the java statement
//            java.sql.Statement st = conn.createStatement();
//
//            ResultSet rs = st.executeQuery(query);
//            int i = 0;
//
//            while (rs.next()) {
//                if (rs.getString("siglaProvincia").equals(sigla)) {
//                    citta += rs.getString("nomeProvincia");
//                }
//            }
//
//            //regioni.set(0, "VUOTA");
//            return citta;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return citta;
//    }

    private GestoreListaEventiLocal lookupGestoreListaEventiLocal() {
        try {
            Context c = new InitialContext();
            return (GestoreListaEventiLocal) c.lookup("java:global/ShareGames/ShareGames-ejb/GestoreListaEventi!manager.GestoreListaEventiLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
