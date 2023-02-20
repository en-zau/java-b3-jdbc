package eosi.b32023.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUpdate {

    Connection cnx;
    PreparedStatement ps;

    public TestUpdate(Connection cnx, PreparedStatement ps){
        this.cnx = cnx;
        this.ps = ps;
    }

    public int TU(String ancienNom, String nouveauNom) throws SQLException {
        ps.setString(1, nouveauNom);
        ps.setString(2, ancienNom);
        int nbUpdate = ps.executeUpdate();
        System.out.println(nbUpdate);
        return nbUpdate;
    }
}