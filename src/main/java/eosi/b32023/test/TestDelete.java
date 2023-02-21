package eosi.b32023.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import eosi.b32023.dal.FournisseurJDBCDAO;

import java.sql.SQLException;

public class TestDelete {
    private final FournisseurJDBCDAO fournisseurJDBCDAO;
    public TestDelete() {
        this.fournisseurJDBCDAO = new FournisseurJDBCDAO();
    }
    public void Delete(int fournisseurId) throws SQLException {
        this.fournisseurJDBCDAO.deleteById(fournisseurId);
    }
}
