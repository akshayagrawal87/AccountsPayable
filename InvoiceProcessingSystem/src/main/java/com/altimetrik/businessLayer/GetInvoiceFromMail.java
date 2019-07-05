package com.altimetrik.businessLayer;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

public class GetInvoiceFromMail {

	private ResourceBundle resourceBundle;
	private String pop3Host;
	private String mailStoreType;
	private String userName;
	private String password;
	private Properties props;
	private Session session;
	public static File targetFile;
	private Folder emailFolder;

	GetInvoiceFromMail() {

		Locale locale = Locale.getDefault();
		String basename = "mailConfig";
		this.resourceBundle = ResourceBundle.getBundle(basename, locale);
		this.pop3Host = resourceBundle.getString("pop3Host");
		this.mailStoreType = resourceBundle.getString("mailStoreType");
		this.userName = resourceBundle.getString("userName");
		this.password = resourceBundle.getString("password");
		this.props = new Properties();
		this.session = Session.getInstance(props);
		this.props.put("mail.store.protocol", "pop3");
		this.props.put("mail.pop3.host", pop3Host);
		this.props.put("mail.pop3.port", "995");
		this.props.put("mail.pop3.starttls.enable", "true");
	}

	protected void recieveMailWithAttachment() {
		try {
			// Create the POP3 store object and connect to the pop store.
			Store store = session.getStore("pop3s");
			store.connect(pop3Host, userName, password);

			// Create the folder object and open it in your mailbox.
			this.emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// Retrieve the messages from the folder object.
			Message[] messages = emailFolder.getMessages();
			System.out.println("Total Message" + messages.length);

			// Iterate the messages
			for (int i = 0; i < messages.length; i++) {

				Message message = messages[i];

				Address[] toAddress = message.getRecipients(Message.RecipientType.TO);

				System.out.println("---------------------------------");

				System.out.println("Details of Email Message " + (i + 1) + " :");

				System.out.println("Subject: " + message.getSubject());

				System.out.println("From: " + message.getFrom()[0]);

				System.out.println("To: ");
				for (int j = 0; j < toAddress.length; j++) {

					System.out.println(toAddress[j].toString());

				}

				Object content = message.getContent();
				if (content instanceof String) {

					String body = (String) content;

				} else if (content instanceof Multipart) {

					Multipart multipart = (Multipart) message.getContent();

					for (int k = 0; k < multipart.getCount(); k++) {

						BodyPart bodyPart = multipart.getBodyPart(k);

						if (bodyPart.getDisposition() != null
								&& bodyPart.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)) {

							System.out.println("file name " + bodyPart.getFileName());

							System.out.println("size " + bodyPart.getSize());

							System.out.println("content type " + bodyPart.getContentType());

							InputStream stream = bodyPart.getInputStream();

							targetFile = new File("D:\\" + bodyPart.getFileName());

							java.nio.file.Files.copy(stream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
							
							
						}

					}
				}
			}

			// close the folder and store objects
			emailFolder.close(false);
			store.close();
			store.removeConnectionListener(null);

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
