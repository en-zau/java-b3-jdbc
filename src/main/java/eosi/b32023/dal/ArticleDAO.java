package eosi.b32023.dal;

import eosi.b32023.bo.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleDAO {
    void insert(Article article) throws SQLException;

    List<Article> findAll() throws SQLException;

    void update(Article oldArticle, Article newArticle) throws SQLException;

    void delete(int articleId) throws SQLException;

}
