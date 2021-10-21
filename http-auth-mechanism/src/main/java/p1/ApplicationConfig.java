/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import org.glassfish.soteria.identitystores.annotation.Credentials;
import org.glassfish.soteria.identitystores.annotation.EmbeddedIdentityStoreDefinition;

/**
 *
 * @author dell
 */

//@BasicAuthenticationMechanismDefinition(realmName="test")

//@FormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//        loginPage="/login-servlet",
//        errorPage="/error-servlet")
//)

@CustomFormAuthenticationMechanismDefinition(
    loginToContinue = @LoginToContinue(
        loginPage="/login.xhtml",
        errorPage="" 
    )
)

@EmbeddedIdentityStoreDefinition({
    @Credentials(callerName = "david", password = "david", groups = {"manager"}),
    @Credentials(callerName = "ed", password = "ed", groups = {"employee",}),
    @Credentials(callerName = "michael", password = "michael", groups = {"employee"}),
    @Credentials(callerName = "sam", password = "sam", groups = {"admin"})
})

@ApplicationScoped
public class ApplicationConfig {
    
}
