package co.develhope.email1.emails.services;

import com.sendgrid.Method;
import com.sendgrid.Response;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private Environment environment;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendWithJavaMailSender(String email, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(title);
        message.setFrom("development@develhope.co");
        message.setText(text);
        javaMailSender.send(message);
    }

    public void sendWithSendGrid(String email, String title, String text) throws IOException {
        Email from = new Email(environment.getProperty("sendGrid.email"));
        Email to = new Email(email);
        Content content = new Content("text/plain", text);
        Mail mail = new Mail(from, title, to, content);

        SendGrid sg = new SendGrid(environment.getProperty("sendGrid.apiKey"));
        //SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

}
