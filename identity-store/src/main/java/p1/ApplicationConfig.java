/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

/**
 *
 * @author dell
 */
@BasicAuthenticationMechanismDefinition(realmName = "testRealm")

//@DatabaseIdentityStoreDefinition(
//    dataSourceLookup = "java:comp/DefaultDataSource",
//    callerQuery = "select password from users where username = ?",
//    groupsQuery = "select GROUPNAME from groups where username = ?"
//)

@ApplicationScoped
public class ApplicationConfig {
    
}
