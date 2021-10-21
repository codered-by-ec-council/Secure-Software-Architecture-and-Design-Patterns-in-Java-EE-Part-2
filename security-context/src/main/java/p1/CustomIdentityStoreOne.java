/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;
import p1.data.User;

/**
 *
 * @author dell
 */
@ApplicationScoped
public class CustomIdentityStoreOne implements IdentityStore {
    
    //private Map<String, String> users = new HashMap<>();
    List<User> users = new ArrayList<>();

    public CustomIdentityStoreOne() {
        System.out.println("Using CustomIdentityStoreOne");
        setup();
    }
    
    
    
    private void setup() {
        users.add(new User("100","david","david@xyz.com","david"));
        users.add(new User("101","ed","ed@xyz.com","ed"));
        users.add(new User("102","michael","michael@xyz.com","michael"));
        users.add(new User("103","sam","sam@xyz.com","sam"));
    }
    
    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(ValidationType.VALIDATE);
    }
    
    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
        String password = null;
        User user = null;
        
        for(User u : users) {
            if(u.getName().equalsIgnoreCase(credential.getCaller())) {
                password = u.getPassword();
                user = u;
                break;
            }
        }
                
        
        if (password != null && password.equals(credential.getPasswordAsString())) {
            return new CredentialValidationResult(new CustomPrincipal(user));
        }
        
        return INVALID_RESULT;
    }
}
