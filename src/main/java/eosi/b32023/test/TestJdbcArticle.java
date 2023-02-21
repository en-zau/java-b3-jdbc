package eosi.b32023.test;

import eosi.b32023.bo.Article;
import eosi.b32023.bo.Fournisseur;
import eosi.b32023.dal.ArticleJDBCDAO;
import eosi.b32023.dal.FournisseurJDBCDAO;
import java.util.List;


import java.sql.SQLException;

public class TestJdbcArticle {
    private final FournisseurJDBCDAO fournisseurJDBCDAO;
    private final ArticleJDBCDAO articleJDBCDAO;

    public TestJdbcArticle(FournisseurJDBCDAO fournisseurJDBCDAO, ArticleJDBCDAO articleJDBCDAO) {
        this.fournisseurJDBCDAO = fournisseurJDBCDAO;
        this.articleJDBCDAO = articleJDBCDAO;
    }

    public void insert() throws SQLException {
        // inserts
        Fournisseur fournisseur = new Fournisseur("La Maison de la Peinture");
        this.fournisseurJDBCDAO.insert(fournisseur);
        fournisseur = this.fournisseurJDBCDAO.extraire().get(0);

        Article article1 = new Article("Peinture blanche 1L", 12.50, fournisseur);
        Article article2 = new Article("Peinture rouge mate 1L", 15.50, fournisseur);
        Article article3 = new Article("Peinture noire laquée 1L", 17.80, fournisseur);
        Article article4 = new Article("Peinture bleue mate 1L", 15.50, fournisseur);
        this.articleJDBCDAO.insert(article1);
        this.articleJDBCDAO.insert(article2);
        this.articleJDBCDAO.insert(article3);
        this.articleJDBCDAO.insert(article4);

        //updates
        List<Article> articles = this.articleJDBCDAO.findAll();
        for (Article temp : articles) {
            Article newArticle = new Article(temp.getnom(), temp.getprix() * 0.75, temp.getFournisseur());
            this.articleJDBCDAO.update(temp, newArticle);
        }

        //show all
        articles = this.articleJDBCDAO.findAll();
        for (Article temp : articles) {
            System.out.printf("%s; %f; %s%n", temp.getnom(), temp.getprix(), temp.getFournisseur().getNom());
        }

        // moyenne prix
        System.out.println(this.articleJDBCDAO.getMoyenne());

        // delete
        articles = this.articleJDBCDAO.findAll();
        for (Article temp : articles) {
            articleJDBCDAO.delete(temp.getId());
        }
        List<Fournisseur> fournisseurs = this.fournisseurJDBCDAO.extraire();
        for (Fournisseur temp : fournisseurs) {
            fournisseurJDBCDAO.deleteById(temp.getId());
        }
    }
}