///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package p1;
//
//import java.util.ArrayList;
//import java.util.EnumSet;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import javax.enterprise.context.ApplicationScoped;
//import javax.security.enterprise.identitystore.CredentialValidationResult;
//import javax.security.enterprise.identitystore.IdentityStore;
//
///**
// *
// * @author dell
// */
//@ApplicationScoped
//public class CustomIdentityStoreTwo implements IdentityStore {
//    
//     private Map<String, List<String>> userRoles = new HashMap<>();
//
//    public CustomIdentityStoreTwo() {
//        System.out.println("Using CustomIdentityStoreTwo");
//        setup();
//    }
//     
//     
//     
//     private void setup() {
//        List<String> roles = new ArrayList<>();
//        roles.add("manager");
//        roles.add("employee");
//        userRoles.put("david", roles);
//
//        roles = new ArrayList<>();
//        roles.add("employee");
//        userRoles.put("ed", roles);
//        
//        roles = new ArrayList<>();
//        roles.add("employee");
//        userRoles.put("michael", roles);
//        
//        roles = new ArrayList<>();
//        roles.add("admin");
//        userRoles.put("sam", roles);
//     }
//     
//    @Override
//    public Set<ValidationType> validationTypes() {
//        return EnumSet.of(ValidationType.PROVIDE_GROUPS);
//    }
//    
//    @Override
//    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
//        List<String> roles = userRoles.get(validationResult.getCallerPrincipal().getName());
//        return new HashSet<>(roles);
//    }
//}
