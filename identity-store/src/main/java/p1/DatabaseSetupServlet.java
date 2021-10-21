/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author dell
 */
@DataSourceDefinition(
    name = "java:comp/DefaultDataSource", 
    className = "org.h2.jdbcx.JdbcDataSource",
    url = "jdbc:h2:~/testSecurity"
)
@WebServlet(name = "DatabaseSetupServlet", urlPatterns = {"/init"}, loadOnStartup = 0)
public class DatabaseSetupServlet extends HttpServlet {

    @Resource(lookup = "java:comp/DefaultDataSource")
    private DataSource dataSource;
    
    @Inject
    private Pbkdf2PasswordHash passwordHash;
    
    @Override
    public void init() throws ServletException {
        super.init();
        initdb();
    }
    
    private void initdb() {
        
        executeUpdate(dataSource, "DROP TABLE IF EXISTS USERS");
        executeUpdate(dataSource, "DROP TABLE IF EXISTS GROUPS");

        executeUpdate(dataSource, "CREATE TABLE IF NOT EXISTS USERS(username VARCHAR(64) PRIMARY KEY, password VARCHAR(255))");
        executeUpdate(dataSource, "CREATE TABLE IF NOT EXISTS GROUPS(username VARCHAR(64), GROUPNAME VARCHAR(64))");

        executeUpdate(dataSource, "INSERT INTO USERS VALUES('david', '" + passwordHash.generate("david".toCharArray()) + "')");
        executeUpdate(dataSource, "INSERT INTO USERS VALUES('ed', '" + passwordHash.generate("ed".toCharArray()) + "')");
        executeUpdate(dataSource, "INSERT INTO USERS VALUES('michael', '" + passwordHash.generate("michael".toCharArray()) + "')");
        executeUpdate(dataSource, "INSERT INTO USERS VALUES('sam', '" + passwordHash.generate("sam".toCharArray()) + "')");
        
        
        executeUpdate(dataSource, "INSERT INTO GROUPS VALUES('david', 'manager')");
        executeUpdate(dataSource, "INSERT INTO GROUPS VALUES('ed', 'employee')");
        executeUpdate(dataSource, "INSERT INTO GROUPS VALUES('michael', 'employee')");
        executeUpdate(dataSource, "INSERT INTO GROUPS VALUES('sam', 'admin')");
    }
    
    private void executeUpdate(DataSource dataSource, String query) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
