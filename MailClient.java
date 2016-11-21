/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.11.21 (@revisionAuthor oschariv)
 */
public class MailClient
{
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private String user;
    //Si es true se activa la respuesta automatica.
    private boolean respuestaAutomatica;
    //Variable para guardar el asunto automatico.
    private String asuntoAutomatico;
    //Variable para guardar el mensaje automatico.
    private String mensajeAutomatico;
    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        //La respuesta automatica se inicia desactivada.
        respuestaAutomatica = false;
        // Iniciamos las varibles de la respuesta automatica.
        asuntoAutomatico = "asuntoAutomatico";
        mensajeAutomatico = "mensajeAutomatico";
    }

    /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        //Recibimos algo del servidor.
        MailItem item = server.getNextMailItem(user);
        //Si lo que recibimos es un email y la respuesta automatica esta activada...
        if (item != null && respuestaAutomatica) {          
            //Enviamos un correo de respuesta automaticamente
            
            //Forma ampliada.
            //Creamos el email
            //MailItem email = new MailItem(user, item.getFrom(), asuntoAutomatico, 
                                          //mensajeAutomatico);
            //Enviamos el email.
            //server.post(email);
            
            //Forma reducida(dejamos funcionando esta porque es mas sencilla).
            //Usamos el metodo sendMailItem.
            sendMailItem(item.getFrom(), asuntoAutomatico, mensajeAutomatico);
        }
        //Devolvemos lo recibido por el servidor.
        return item;
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
            item.print();
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
    
    /**
     * Devuelve el numero de mensajes no leidos que hay en el servidor sin 
     * descargarlos.
       */
    public void getMensajesNoLeidos(){
        System.out.println(user + ": " + server.howManyMailItems(user) 
                            + " Mensajes no leidos.");
    }
    
    /**
     * Habilita o no la respuesta automatica
       */    
    public void autoRespuesta()
    {
         respuestaAutomatica = !respuestaAutomatica;
    }
    
    /**
     * Permite modificar el asunto y el mensaje que lleva la respuesta
     * automatica.
       */
    public void modificarRespuestaAutomatica(String asunto, String mensaje)
    {
        asuntoAutomatico = asunto;
        mensajeAutomatico = mensaje;
    }
        
}
