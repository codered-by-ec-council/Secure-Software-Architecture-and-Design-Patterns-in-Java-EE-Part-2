/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import p1.data.Approval;

/**
 *
 * @author dell
 */
@Stateless
public class ApproveLeaveEJB {
    @Inject
    SecurityContext securityContext;
    
    public Approval approveLeaveRequest() {
        //Hardcoded data. Should ideally be retrived using a database call
        int leaveBalance = 5;
        boolean monthlyEntitlementExceeded = false;
        boolean isBackupAvailable = true;
        
        Approval approval =new Approval();
        approval.setEmployeeId(100);
        
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -2);
        Date twoDaysAgo = cal.getTime();
        approval.setRequestedDate(twoDaysAgo);
        approval.setApprovedDate(new Date());
        
        if(leaveBalance > 0) {
            if(!monthlyEntitlementExceeded && isBackupAvailable) {
                approval.setStatus("APPROVED");
            } else if(monthlyEntitlementExceeded) {
                approval.setStatus("ESCALATE");
            } else {
                approval.setStatus("ONHOLD");
            }
        } else {
            approval.setStatus("REJECTED");
        }
        
        approval.setApprovedBy(securityContext.getCallerPrincipal().getName());
        
        return approval;
    }
}
