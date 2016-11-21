/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MailClient
{
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private String user;
    
    private boolean respuestaAutomatica;
    private String asuntoAutomatico;
    private String mensajeAutomatico;
    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        
        respuestaAutomatica = false;
        asuntoAutomatico = "asuntoAutomatico";
        mensajeAutomatico = "mensajeAutomatico";
    }

    /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        if (respuestaAutomatica) {
            printNextMailItem();
            return server.getNextMailItem(user);
        }
        else {
            return server.getNextMailItem(user);
        }
    }

    /**
     * Print the next mail item (if any) for this user to the text 
     * terminal.
     */
    public void printNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if(item == null) {
            System.out.println("No new mail.");
        }
        else {
            if(respuestaAutomatica){
                sendMailItem(item.getFrom(), asuntoAutomatico, mensajeAutomatico);
            }
            else{
                item.print();
            }
        }
    }

    /**
     * Send the given message to the given recipient via
     * the attached mail server.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public void sendMailItem(String to, String subject, String message)
    {
        MailItem item = new MailItem(user, to, subject, message);
        server.post(item);
    }
    
    public void getMensajesNoLeidos(){
        System.out.println(user + ": " + server.howManyMailItems(user) + " Mensajes no leidos.");
    }
        
    public void autoRespuesta(boolean respuestaAutomatica)
    {
         this.respuestaAutomatica = respuestaAutomatica;
    }
    
    public void modificarRespuestaAutomatica(String asunto, String mensaje)
    {
        asuntoAutomatico = asunto;
        mensajeAutomatico = mensaje;
    }
    
    
}
