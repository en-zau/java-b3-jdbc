package eosi.b32023.dal;

import eosi.b32023.bo.Article;
import eosi.b32023.bo.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArticleJDBCDAO implements ArticleDAO {
    private static final String INSERT_QUERY = "INSERT INTO article(NOM, PRIX, ID_FOURNISSEUR) VALUES(?,?,?)";
    private static final String SELECT_QUERY = "SELECT a.*, f.id as ID_FOURNISSEUR, f.NOM as NAME_FOURNISSEUR FROM article a LEFT JOIN fournisseur f ON f.ID = a.ID_FOURNISSEUR";
    private static final String UPDATE_QUERY = "UPDATE article SET NOM=?, PRIX=?, ID_FOURNISSEUR=? WHERE ID=?";
    private static final String DELETE_ID_QUERY = "DELETE FROM article WHERE ID=?";
    private static final String GET_MOYENNE_REQ = "SELECT AVG(PRIX) as MOYENNE FROM article";
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
    public void insert(Article article) throws SQLException {
        try ( Connection cnx = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PWD );
              PreparedStatement ps = cnx.prepareStatement(INSERT_QUERY)) {
            ps.setString(1, article.getnom());
            ps.setDouble(2, article.getprix());
            ps.setInt(3, article.getFournisseur().getId());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Article> findAll() throws SQLException {
        List<Article> Articles = new ArrayList<>();

        try (Connection cnx = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PWD );
             PreparedStatement ps = cnx.prepareStatement(SELECT_QUERY);
             ResultSet rs = ps.executeQuery()) {

            while ( rs.next() ) {
                int id = rs.getInt( "ID" );
                String nom = rs.getString( "NOM" );
                Double prix = rs.getDouble( "PRIX" );
                int fournisseurId = rs.getInt("ID_FOURNISSEUR");
                String fournisseurName = rs.getString("NAME_FOURNISSEUR");
                Fournisseur fournisseur = new Fournisseur(fournisseurId, fournisseurName);
                Article Article = new Article( id, nom, prix, fournisseur);
                Articles.add( Article );
            }
        }


        return Articles;
    }

    @Override
    public void update(Article oldArticle, Article newArticle) throws SQLException {
        try ( Connection cnx = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PWD );
              PreparedStatement ps = cnx.prepareStatement(UPDATE_QUERY)) {
            ps.setString(1, newArticle.getnom());
            ps.setDouble(2, newArticle.getprix());
            ps.setInt(3, newArticle.getFournisseur().getId());
            ps.setString(4, Integer.toString(oldArticle.getId()));
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int articleId) throws SQLException {
        try ( Connection cnx = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PWD );
              PreparedStatement ps = cnx.prepareStatement(DELETE_ID_QUERY)) {
            ps.setString(1, String.valueOf(articleId));
            ps.executeUpdate();
        }
    }

    public Double getMoyenne() throws SQLException {
        double res = 0;
        try ( Connection cnx = DriverManager.getConnection( DB_URL, DB_LOGIN, DB_PWD );
              PreparedStatement ps = cnx.prepareStatement(GET_MOYENNE_REQ)) {
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ) {
                res = rs.getDouble("MOYENNE");
            }
        }
        return res;
    }
}

