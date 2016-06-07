/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailworkshop;

/**
 *
 * @author fabio
 */
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.io.FileOutputStream;
import java.io.IOException;
//import api iText
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class EmailWorkshop {

    public static void main(String[] args) {

        InterfaceGUI gui = new InterfaceGUI();
        gui.setVisible(true);
//        gerarPDF("Rita de Cássia dos Anjos", 2374);
        

    }

    public static void exe(ArrayList<Checkin> lista, String emailAutentica, String senhaAutentica, String emailRecebe, boolean enviaEmailParticipante) {
        if (emailAutentica.isEmpty() || senhaAutentica.isEmpty()) {
            System.exit(0);
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter("relatorioDeEnvio.txt");
        } catch (IOException ex) {
            Logger.getLogger(EmailWorkshop.class.getName()).log(Level.SEVERE, null, ex);
        }
        int numCert = 2217;
        for (Checkin c : lista) {            
            //if ("https://drive.google.com/open?id=0B0LxgGB17-B3aGVNMThsTXpWV0E".equals(c.getQrCode())) {
                gerarPDF(c.getNome(), numCert);
                try {
                    if (!emailRecebe.isEmpty()) {
                        enviaEmailComAnexo(emailAutentica, senhaAutentica, emailRecebe, c.getNome());
                        System.out.println("certificado de " + c.getNome() + " gerado!");

                        try {

                            fw.write("certificado de " + c.getNome() + " gerado!\n");

                        } catch (IOException ex) {
                            Logger.getLogger(EmailWorkshop.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (enviaEmailParticipante) {
                        System.out.println("envia email para " + c.getNome());
                        enviaEmailComAnexo(emailAutentica, senhaAutentica, c.getEmail(), c.getNome());
                    }
                    //avisoDeEnvio(c.getNome());
                } catch (EmailException ex) {
                    Logger.getLogger(EmailWorkshop.class.getName()).log(Level.SEVERE, null, ex);
                }

            //}
            numCert = numCert+1;
        }
        try {
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(EmailWorkshop.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

   

    public static void gerarPDF(String nome, int numCert) {
        Calendar data = Calendar.getInstance();

        // criação do objeto documento
        Document document = new Document() {
        };
        document.setPageSize(new Rectangle(800, 500));
        try {

            PdfWriter.getInstance(document, new FileOutputStream("Certificado.pdf"));
            document.open();
            
            
            // adicionando um parágrafo ao documento
            Image img = Image.getInstance("logo.png");
            

            img.scaleAbsoluteWidth(620);
            img.scaleAbsoluteHeight(130);
            Paragraph texto1 = new Paragraph("\n Conferimos o presente Certificado a " + nome + " por ter "
                    + "participado do II Workshop de Inovação, com duração de 4 horas. Onde foram apresentadas as palestras: ");
            Paragraph texto2 = new Paragraph("Economia compartilha & Inovação Disruptiva - Arthur Schuler da Igreja.\n" +
                        "Empreendedorismo: Um case de sucesso - Cláudio Kopp.");
            Paragraph texto3 = new Paragraph("\n\n\n\n\nRegistrado na UFPR Setor Palotina Livro 4 certificado Nr "+numCert+".");

            Paragraph localData = new Paragraph("\nPalotina, " + new SimpleDateFormat("dd").format(data.getTime()) + " de "
                    + new SimpleDateFormat("MMMM").format(data.getTime()) + " de "
                    + new SimpleDateFormat("yyyy").format(data.getTime()) + ".\n\n");

            texto1.setAlignment("center");
            texto2.setAlignment("center");
            texto3.setAlignment("right");
            localData.setAlignment("center");
            
            
           
            Image assinatura = Image.getInstance("assinatura.png");
            assinatura.scaleAbsoluteHeight(75);
            assinatura.scaleAbsoluteWidth(250);
            assinatura.setAbsolutePosition(275, 110);
            
            document.add(img);
            document.add(texto1);
            document.add(texto2);
            document.add(localData);
            
            
            document.add(assinatura);
            document.add(texto3);
            
            document.close();
        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }
        

    }

    public static void enviaEmailComAnexo(String mailFrom, String senhaFrom, String emailTo, String nomeTo) throws EmailException {
        // cria o anexo 1.
        EmailAttachment anexo1 = new EmailAttachment();
        anexo1.setPath("Certificado.pdf"); //caminho do arquivo (RAIZ_PROJETO/teste/teste.txt)
        anexo1.setDisposition(EmailAttachment.ATTACHMENT);
        anexo1.setDescription("anexo");
        anexo1.setName("Certificado.pdf");

        // configura o email
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
        email.addTo(emailTo, nomeTo); //destinatário
        email.setFrom(mailFrom, "UFPR"); // remetente
        email.setSubject("Certificado II Workshop de Inovação"); // assunto do e-mail
        email.setMsg("Segue anexo o Certificado de participaçao no II Workshop, Obrigado pela presença! \n "+emailTo); //conteudo do e-mail
        email.setAuthentication(mailFrom, senhaFrom);
        email.setSmtpPort(465);
        email.setSSL(true);
        email.setTLS(true);
        // adiciona arquivo(s) anexo(s)
        email.attach(anexo1);
        // envia o email
        email.send();
    }
}
