package eosi.b32023.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestSelect {

    Connection cnx;
    PreparedStatement ps;

    TestSelect(Connection cnx, PreparedStatement ps){
        this.cnx = cnx;
        this.ps = ps;
    }

    public void TSEL() throws SQLException {
        try ( ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                System.out.printf("id = %d ou alors %d - nom = %s %n",
                        rs.getInt(1),
                        rs.getInt("ID"), rs.getString("NOM"));
            }
        }
    }
}