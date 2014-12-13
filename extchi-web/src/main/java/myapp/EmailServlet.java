package myapp;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import javax.servlet.http.*;

public class EmailServlet extends HttpServlet {
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        String msgBody = req.getParameter("message");
		try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("extchi.services@gmail.com", "Extchi Services"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress("extchi.services@gmail.com", "Extchi Services"));
            msg.setSubject("Extchi message: '" + req.getParameter("name") + "' <" + req.getParameter("email") + ">");
            msg.setText(msgBody);
            Transport.send(msg);
        } catch (AddressException e) {
			System.out.println("AddressException " + e.getMessage());
			e.printStackTrace();
        } catch (MessagingException e) {
			System.out.println("MessagingException " + e.getMessage());
			e.printStackTrace();
        }
        resp.getWriter().println("{ \"name\": \"success\" }");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
			doPost(req, resp);
    }
}
