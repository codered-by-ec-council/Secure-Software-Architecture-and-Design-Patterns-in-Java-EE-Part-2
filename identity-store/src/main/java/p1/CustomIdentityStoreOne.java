///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package p1;
//
//import java.util.EnumSet;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import javax.enterprise.context.ApplicationScoped;
//import javax.security.enterprise.credential.UsernamePasswordCredential;
//import javax.security.enterprise.identitystore.CredentialValidationResult;
//import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
//import javax.security.enterprise.identitystore.IdentityStore;
//
///**
// *
// * @author dell
// */
//@ApplicationScoped
//public class CustomIdentityStoreOne implements IdentityStore {
//    
//    private Map<String, String> users = new HashMap<>();
//
//    public CustomIdentityStoreOne() {
//        System.out.println("Using CustomIdentityStoreOne");
//        setup();
//    }
//    
//    
//    
//    private void setup() {
//        users.put("david", "david");
//        users.put("ed", "ed");
//        users.put("michael", "michael");
//        users.put("sam", "sam");
//    }
//    
//    @Override
//    public Set<ValidationType> validationTypes() {
//        return EnumSet.of(ValidationType.VALIDATE);
//    }
//    
//    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
//        String password = users.get(credential.getCaller());
//        if (password != null && password.equals(credential.getPasswordAsString())) {
//            return new CredentialValidationResult(credential.getCaller());
//        }
//        
//        return INVALID_RESULT;
//    }
//}
