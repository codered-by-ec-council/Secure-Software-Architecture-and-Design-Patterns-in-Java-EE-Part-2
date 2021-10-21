/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import java.security.Principal;
import javax.security.enterprise.CallerPrincipal;
import p1.data.User;

/**
 *
 * @author dell
 */
public class CustomPrincipal extends CallerPrincipal {

    private User user;
    
    public CustomPrincipal(User user) {
        super(user.getName());
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
