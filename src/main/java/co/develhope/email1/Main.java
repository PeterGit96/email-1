package co.develhope.email1;

import co.develhope.email1.emails.services.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        /*EmailService emailService = new EmailService();

        try {
            emailService.sentToClient("pietrogallina96@gmail.com", "email's title", "message email");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }*/

        Email from = new Email("pietrogallina96@gmail.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("ptradventure@hotmail.it");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
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
