package eosi.b32023.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDelete {

    private Connection cnx;
    private PreparedStatement ps;

    public TestDelete(Connection cnx, PreparedStatement ps) {
        this.cnx = cnx;
        this.ps = ps;
    }

    public void TDEL(int id) throws SQLException {
        ps.setInt(1, id);
        int nbDeleted = ps.executeUpdate();
        System.out.println(nbDeleted + " lignes supprimées !");
    }
}