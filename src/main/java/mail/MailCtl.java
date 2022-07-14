package mail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/MailCtl")
public class MailCtl extends HttpServlet {
    private static final long serialVersionUID = 1;
    public static final String OP_GO = "Send";
    //constructor
    public MailCtl() {
        super();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        String op = request.getParameter("operation");
        if(OP_GO.equalsIgnoreCase(op)){
            // sending mail
            EmailMessage msg = new EmailMessage();
            msg.setTo(email);
            msg.setSubject(subject);
            msg.setMessage(message);
            msg.setMessageType(EmailMessage.HTML_MSG);

            EmailUtility.sendMail(msg);
            request.setAttribute("msg","Mail has been successfully");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
