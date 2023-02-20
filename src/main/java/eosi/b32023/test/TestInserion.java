package eosi.b32023.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestInserion {

    Connection cnx;
    PreparedStatement ps;

    public TestInserion(Connection cnx, PreparedStatement ps){
        this.cnx = cnx;
        this.ps = ps;
    }

    public void TS(String nom) throws SQLException {
        ps.setString(1, nom);
        int nbInsert = ps.executeUpdate();
        System.out.println(nbInsert);
    }
}