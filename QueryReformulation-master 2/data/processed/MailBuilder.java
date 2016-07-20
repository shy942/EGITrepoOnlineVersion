/***/
package org.eclipse.e4.demo.cssbridge.util;

import java.util.Date;
import org.eclipse.e4.demo.cssbridge.model.Importance;
import org.eclipse.e4.demo.cssbridge.model.Mail;

public class MailBuilder {

    private Mail mail;

    public MailBuilder mail() {
        mail = new Mail();
        return this;
    }

    public MailBuilder withImportance(Importance importance) {
        mail.setImportance(importance);
        return this;
    }

    public MailBuilder withSender(String sender) {
        mail.setSender(sender);
        return this;
    }

    public MailBuilder withSubject(String subject) {
        mail.setSubject(subject);
        return this;
    }

    public MailBuilder withDate(Date date) {
        mail.setDate(date);
        return this;
    }

    public MailBuilder withBody(String body) {
        mail.setBody(body);
        return this;
    }

    public Mail create() {
        return mail;
    }
}
