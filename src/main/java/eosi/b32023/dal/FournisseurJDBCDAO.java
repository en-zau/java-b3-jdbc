package eosi.b32023.dal;

import eosi.b32023.bo.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FournisseurJDBCDAO implements FournisseurDAO {

    private static final String SELECT_QUERY = "SELECT * FROM fournisseur";
    private static final String INSERT_QUERY = "INSERT INTO fournisseur (NOM) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE fournisseur SET NOM = ? WHERE NOM = ?";
    private static final String DB_URL;
    private static final String DB_LOGIN;
    private static final String DB_PWD;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle( "db" );
        DB_URL = bundle.getString( "db.url" );
        DB_LOGIN = bundle.getString( "db.login" );
        DB_PWD = bundle.getString( "db.password" );
    }

    @Override
    public List<Fournisseur> extraire() throws SQLException {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        //Try avec ressources
        try ( Connection cnx = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PWD );
              PreparedStatement ps = cnx.prepareStatement(SELECT_QUERY);
              ResultSet rs = ps.executeQuery()) {
            System.out.println(cnx);
            //Récupération & affichage des fournisseurs
            while ( rs.next() ) {
                int id = rs.getInt( "ID" );
                String nom = rs.getString( "NOM" );
                Fournisseur fournisseur = new Fournisseur( id, nom );
                fournisseurs.add( fournisseur );
            }
        }
        return fournisseurs;
    }

    @Override
    public void insert( Fournisseur fournisseur ) throws SQLException {
        try ( Connection cnx = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PWD );
              PreparedStatement ps = cnx.prepareStatement(INSERT_QUERY)) {
            ps.setString(1, fournisseur.getNom());
            //Insertion en base
            ps.executeUpdate();
        }
    }

    @Override
    public int update( String ancienNom, String nouveauNom ) throws SQLException {
        int nb = 0;
        try ( Connection cnx = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PWD );
              PreparedStatement ps = cnx.prepareStatement(UPDATE_QUERY)) {
            ps.setString(1, nouveauNom);
            ps.setString(2, ancienNom);
            //Mise à jour en base
            nb = ps.executeUpdate();
        }
        return nb;
    }

    @Override
    public boolean delete(Fournisseur fournisseur) throws SQLException {
        String DELETE_QUERY = "DELETE FROM fournisseur WHERE ID = ?";
        try (Connection cnx = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PWD);
             PreparedStatement ps = cnx.prepareStatement(DELETE_QUERY)) {
            ps.setInt(1, fournisseur.getId());
            int nbRows = ps.executeUpdate();
            return nbRows > 0;
        }
    }
}