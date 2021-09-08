package util;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @Description:
 * @Author: nianjie.chen
 * @Date: 4/29/2020
 */
public class EmailUtil {

    private static final long expireseconds = 1800; //30mins

    private String host;
    private String from;
    private List<String> to;
    private String title;
    private String content;

    class SendEmailThread implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("email sending" + to + "....");
                String result = sendEmail(host, from, to, title, content);
                System.out.println("send result?" + result);
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("send error....");
            }
        }
    }

    /**
     *
     * @param host
     * @param from
     * @param to
     * @param title
     * @param content
     * @return
     */
    public String send(String host, String from, List<String> to, String title,
                       String content) {
        String result = "success";
        this.host = host;
        this.from = from;
        this.to = to;
        this.title = title;
        this.content = content;
        Thread threadE = null;
        try {
            System.out.println("start thread....");
            SendEmailThread sendEmailThread = new SendEmailThread();
            threadE = new Thread(sendEmailThread);
            threadE.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            System.out.println(e);
            threadE.stop();
            if (threadE != null) {
                threadE.interrupt();
            }
            System.err.println("error stop thread...");
            System.out.println(Thread.currentThread().getName() + "------");
        }
        return result;
    }


    /**
     *
     * @param host
     * @param from
     * @param to
     * @param title
     * @param content
     * @return
     */
    public static String sendEmail(String host, String from, List<String> to,
                                   String title, String content) {
        //????SSL??
        boolean ssl = true;
        String result = "success";
        // Get system properties
        Properties props = System.getProperties();

        // Setup mail server
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");

        // Setup mail port
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.ssl.trust", host);
        // Get session

//        Session session = Session.getDefaultInstance(props, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("jaychen1@refinitiv.com","1573297836CNJ.");
//            }
//        });

        Session session = Session.getDefaultInstance(props,  null);
        session.setDebug(true);
        MimeMessage message = null;
        for (int i = 0; i < to.size(); i++) {
            // Set the to address
            // Define message
            System.out.println("init config and send email to:" + to.get(i));
            message = new MimeMessage(session);
            try {
                message.setText(content);
                message.setFrom(new InternetAddress(from));
            } catch (MessagingException e1) {
                System.out.println(e1);
            }

            // Set the subject
            try {
                message.setSubject(title);
            } catch (MessagingException e) {
                result = "set subject erro";
            }
            // Set the content
            try {
                MailcapCommandMap mailcapCommandMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
                mailcapCommandMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
                mailcapCommandMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
                mailcapCommandMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
                mailcapCommandMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
                mailcapCommandMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
                CommandMap.setDefaultCommandMap(mailcapCommandMap);

                BodyPart mdp = new MimeBodyPart();
                mdp.setContent(content, "text/html;charset=utf-8");
                MimeMultipart mm = new MimeMultipart();
                mm.addBodyPart(mdp);
                mm.setSubType("related");
                message.setContent(mm);
            } catch (MessagingException e) {
                System.out.println(e);
                System.out.println(e);
                result = "set content error";
            }
            try {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to.get(i)));
            } catch (AddressException e) {
                System.out.println(e);
                result = "receiver address parse error";
            } catch (MessagingException e) {
                result = "email info error";
            }
            try {
                message.saveChanges();
            } catch (MessagingException e) {
                System.out.println(e);
                result = "fail to email send";
            }
            try {
                Transport.send(message);
            } catch (MessagingException e) {
                System.out.println(e);
                result = "fail to email send";
            }
        }
        return result;
    }
    /*
     * date1 = "2008-10-13 18:35:00"; date2 = "2008-10-12 18:35:00";
     */
    public static long diferentSecend(String date1, String date2) {
        long secend = 0;


        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        try {
            Date d1 = sf.parse(date1);
            Date d2 = sf.parse(date2);
            if (d1.getTime() > d2.getTime()) {
                secend = (d1.getTime() - d2.getTime()) / 1000;
            } else {
                secend = (d2.getTime() - d1.getTime()) / 1000;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return secend;
    }


    public String checkVerifycodeIsValid(String inputCode, String verifycode, String senddate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowdate = df.format(new Date());
        System.out.println("senddate:" + senddate + "-->" + "nowdate:" + nowdate);
        Long seconds = diferentSecend(nowdate, senddate);
        System.out.println("gap seconds:" + seconds);
        if (seconds > expireseconds) { //code expire
            return "1";
        }
        if (!inputCode.equals(verifycode)) { //code mismatch
            return "2";
        }
        return "0"; //code verify success
    }

    public static void main(String[] args) {

        ArrayList to = new ArrayList<String>();
        to.add("jaychen1@refinitiv.com");
        new EmailUtil().send("corprelay.int.refinitiv.com","IQM.DevSupport@refinitiv.com",to,"IQM_NAV_UI Verrification Code","success");
    }
}
