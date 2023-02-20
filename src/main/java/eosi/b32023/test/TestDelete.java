package eosi.b32023.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDelete {

    Connection cnx;
    PreparedStatement ps;

    TestDelete(Connection cnx, PreparedStatement ps){
        this.cnx = cnx;
        this.ps = ps;
    }

    public int TD(String nom) throws SQLException {
        ps.setString(1, nom);
        int nbDelete = ps.executeUpdate();
        System.out.println(nbDelete);
        return nbDelete;
    }
}