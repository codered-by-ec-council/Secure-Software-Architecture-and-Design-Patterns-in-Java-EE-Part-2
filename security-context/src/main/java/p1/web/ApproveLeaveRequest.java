/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import p1.CustomPrincipal;
import p1.data.Approval;
import p1.ejb.ApproveLeaveEJB;

/**
 *
 * @author dell
 */
@DeclareRoles({"manager, employee", "admin"})
@ServletSecurity(
        value = @HttpConstraint(rolesAllowed = {"manager","admin"}, 
                transportGuarantee = TransportGuarantee.CONFIDENTIAL)
)

@WebServlet(name = "ApproveLeaveRequest", urlPatterns = {"/ApproveLeaveRequest"})
public class ApproveLeaveRequest extends HttpServlet {

    @Inject
    SecurityContext securityContext;
    
    @EJB
    ApproveLeaveEJB approveLeaveEJB;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setIntHeader("Refresh", 1);
        HttpSession session = request.getSession();
        boolean isCallerManager = securityContext.isCallerInRole("manager");
       
      
        if(isCallerManager) {
            //do soemthing
            Approval approval = approveLeaveEJB.approveLeaveRequest();
            session.setAttribute("approval", approval);
                        
            if(approval.getStatus().equals("ESCALATE")) {
                boolean hasAccess = securityContext.hasAccessToWebResource("/EscalateLeaveRequest", "GET");
                if(hasAccess) {
                    request.getRequestDispatcher("/EscalateLeaveRequest").forward(request, response);
                } else {
                    request.getRequestDispatcher("/approval-result-success.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/approval-result-success.jsp").forward(request, response);
            }
            
            //Obtain the email address of the approver user to send an email notification of the approval
             Set<CustomPrincipal> principals = securityContext.getPrincipalsByType(CustomPrincipal.class);
         
             for(CustomPrincipal p : principals) {
                System.out.println(p.getUser().getEmail());
             }
        } else {
             request.getRequestDispatcher("/approval-result-failure.jsp").forward(request, response);
        }
    }
}
