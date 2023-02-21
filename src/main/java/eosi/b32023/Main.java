package eosi.b32023;

import eosi.b32023.bo.Fournisseur;
import eosi.b32023.dal.ArticleJDBCDAO;
import eosi.b32023.dal.FournisseurDAO;
import eosi.b32023.dal.FournisseurJDBCDAO;
import eosi.b32023.test.TestDelete;
import eosi.b32023.test.TestInserion;
import eosi.b32023.test.TestJdbcArticle;
import eosi.b32023.test.TestUpdate;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class Main {

    private static final String DB_URL;
    private static final String DB_LOGIN;
    private static final String DB_PWD;
    static {
        System.out.println("Chargement de classe");
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        DB_URL = bundle.getString("db.url");
        DB_LOGIN = bundle.getString("db.login");
        DB_PWD = bundle.getString("db.password");

    }
    public static void main(String[] args) throws Exception {
        try (Connection cnx = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PWD);
             PreparedStatement psInsert = cnx.prepareStatement("INSERT INTO fournisseur (NOM) VALUES (?)");
             PreparedStatement psUpdate = cnx.prepareStatement("UPDATE fournisseur SET NOM = ? WHERE NOM = ?");
             PreparedStatement psDelete = cnx.prepareStatement("DELETE FROM fournisseur WHERE NOM = ?");
             Statement st = cnx.createStatement();) {

            // Test d'insertion
            TestInserion ti = new TestInserion(cnx, psInsert);
            ti.TS("lolilo");

            // Créer une instance de FournisseurJDBCDAO
            FournisseurDAO fournisseurDAO = new FournisseurJDBCDAO();

          // Extraire les fournisseurs et les afficher
            List<Fournisseur> fournisseurs = fournisseurDAO.extraire();
            for (Fournisseur f : fournisseurs) {
                System.out.printf("id = %d ou alors %d - nom = %s %n", f.getId(), f.getId(), f.getNom());
            }

            // Test de mise à jour
            TestUpdate tu = new TestUpdate(cnx, psUpdate);
            tu.TU("lolilo", "nouveauNom");

            // Extraire les fournisseurs et les afficher
            fournisseurs = fournisseurDAO.extraire();
            for (Fournisseur f : fournisseurs) {
                System.out.printf("id = %d ou alors %d - nom = %s %n", f.getId(), f.getId(), f.getNom());
            }

            // Test de suppression
            TestDelete TD= new TestDelete();
            TD.Delete(1);

            // Extraire les fournisseurs et les afficher
            fournisseurs = fournisseurDAO.extraire();
            for (Fournisseur f : fournisseurs) {
                System.out.printf("id = %d ou alors %d - nom = %s %n", f.getId(), f.getId(), f.getNom());
            }

            TestJdbcArticle test = new TestJdbcArticle(new FournisseurJDBCDAO(), new ArticleJDBCDAO());
            test.insert();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
