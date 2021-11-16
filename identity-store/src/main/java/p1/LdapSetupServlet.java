/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldif.LDIFReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dell
 */
@WebServlet(name = "LdapSetupServlet", urlPatterns = {"/init-ldap"}, loadOnStartup = 1)
public class LdapSetupServlet extends HttpServlet {
    
    private InMemoryDirectoryServer inMemoryDirectoryServer;

    @Override
    public void init() throws ServletException {
        initLdap();
        super.init();
    }
    
    private void initLdap() {
        try {
            InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig("dc=test,dc=com");
            System.out.println("^^^^^^");
            config.setListenerConfigs(InMemoryListenerConfig.createLDAPConfig("default", 10389));
            config.setSchema(null);
            inMemoryDirectoryServer = new InMemoryDirectoryServer(config);
            inMemoryDirectoryServer.importFromLDIF(true,
                    new LDIFReader(this.getClass().getResourceAsStream("/users.ldif")));
            inMemoryDirectoryServer.startListening();
        } catch (LDAPException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        inMemoryDirectoryServer.shutDown(true);
    }

}
