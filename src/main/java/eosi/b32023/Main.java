package eosi.b32023;

import eosi.b32023.bo.Fournisseur;
import eosi.b32023.dal.FournisseurDAO;
import eosi.b32023.dal.FournisseurJDBCDAO;
import eosi.b32023.test.TestInserion;

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
             PreparedStatement ps = cnx.prepareStatement("INSERT INTO fournisseur (NOM) VALUES (?)");
             Statement st = cnx.createStatement();) {

            TestInserion ts = new TestInserion(cnx, ps);
            ts.TS("lolilo");

            // Créer une instance de FournisseurJDBCDAO
            FournisseurDAO fournisseurDAO = new FournisseurJDBCDAO();

            // Extraire les fournisseurs et les afficher
            List<Fournisseur> fournisseurs = fournisseurDAO.extraire();
            for (Fournisseur f : fournisseurs) {
                System.out.printf("id = %d ou alors %d - nom = %s %n", f.getId(), f.getId(), f.getNom());
            }

            // Mettre à jour un fournisseur
            int nbUpdate = fournisseurDAO.update("lolilo", "nouveauNom");
            System.out.println(nbUpdate);

            // Supprimer un fournisseur
            Fournisseur f = new Fournisseur("nouveauNom");
            boolean isDeleted = fournisseurDAO.delete(f);
            System.out.println(isDeleted);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
