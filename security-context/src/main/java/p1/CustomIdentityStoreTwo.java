/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import p1.data.User;

/**
 *
 * @author dell
 */
@ApplicationScoped
public class CustomIdentityStoreTwo implements IdentityStore {
    
     private Map<User, List<String>> userRoles = new HashMap<>();

    public CustomIdentityStoreTwo() {
        System.out.println("Using CustomIdentityStoreTwo");
        setup();
    }
     
     
     
     private void setup() {
        List<String> roles = new ArrayList<>();
        roles.add("manager");
        roles.add("employee");
        userRoles.put(new User("100","david","david@xyz.com","david"), roles);

        roles = new ArrayList<>();
        roles.add("employee");
        userRoles.put(new User("101","ed","ed@xyz.com","ed"), roles);
        
        roles = new ArrayList<>();
        roles.add("employee");
        userRoles.put(new User("102","michael","michael@xyz.com","michael"), roles);
        
        roles = new ArrayList<>();
        roles.add("admin");
        userRoles.put(new User("103","sam","sam@xyz.com","sam"), roles);
     }
     
    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(ValidationType.PROVIDE_GROUPS);
    }
    
    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        List<String> roles = null;
        
        for(User u :userRoles.keySet()) {
            if(u.getName().equalsIgnoreCase(validationResult.getCallerPrincipal().getName())) {
                roles = userRoles.get(u);
            }
        }
        
        return new HashSet<>(roles);
    }
}
