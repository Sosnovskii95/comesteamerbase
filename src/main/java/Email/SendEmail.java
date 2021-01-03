package Email;


import EntityLayer.Client;
import EntityLayer.DiscountCard;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class SendEmail {

    private String username = "";
    private String password = "";
    private String fromEmail = "";
    private String toEmail = "";
    private final Properties props;

    public SendEmail() {
        props = new Properties();
        InputStream inputStream = null;
        try{
            inputStream =
                    getClass().getResourceAsStream
                            ("/Config/email_config.properties");
            props.load(inputStream);
            username = props.getProperty("mail.username");
            password = props.getProperty("mail.password");
            fromEmail = props.getProperty("mail.fromemail");
            toEmail = props.getProperty("mail.toemail");
        }catch (IOException e){

        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String magazine, Client client, DiscountCard card){
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(fromEmail));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //Заголовок письма
            message.setSubject("Активация карты №"+card.getNumber_card());
            //Содержимое
            message.setText("Дата создания: "+card.getDate_create()+"\n"+
            "Магазин выдачи: "+magazine+"\n"+
            "ФИО клиента: "+client.getFio_client()+"\n"+
            "Ссылка на активацию: "+"http://194.158.201.105:8080/webesteamer/adminview/editcard?number_card="+card.getNumber_card());

            //Отправляем сообщение
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
