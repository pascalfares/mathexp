/*
 * Test.java
 *
 * Created on 24 janvier 2004, 01:02
 */

package exp.interprete;
import java.io.*;
/** Programme de test utilisant Le parser du langage mathématique.
 * Ce processus Lit le clavier et envoie la chaine au Tokenizer
 * (Utilisation d'un tube entre 2 threads)
 * @author Pascal Farès ISAE
 * @version $@
 *
 * @see PipedInputStream PipedOutputStream
 */
public class Test extends Thread {
    /** Flux d'enté pour l'interpréteur */    
    BufferedReader in;
    /** Envoi de l'entrée vers le flux du toenizer */    
    PrintWriter out;
    /** Construteur Test
     * @param in Stream d'entrée de l'interpréteur
     * @param out Stream de sortie vers le tokenizer, en général un tube
     */
 
    public Test(InputStream in, OutputStream out) {
        this.in= new java.io.BufferedReader(new InputStreamReader(in));
        this.out= new java.io.PrintWriter(out);
        
    }
    
    /** Création du tube pipeout-->pipein
     * Création du tokenizer sur pipein
     * Lancement des 2 threads (Interpréteur/Parseur du langage mathématique)
     * Le dialogue entre les deux thread est réalisé au moyen du tube
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        PipedInputStream pi = new PipedInputStream();
        PipedOutputStream po = new PipedOutputStream();
         
        try {
            po.connect(pi);
        } catch  (java.io.IOException e) {System.out.println(e);}
        Test t= new Test(System.in,po);
        
        //Lancement du thread
        t.start();


         
           
         /**/
          /*
           * exp.langage.Langage l = exp.langage.Langage.parse(pi);
          Environnement ts= new Environnement();
          NaopTokenizer tokenizer = new NaopTokenizer("p1 = (x +5)");
          tokenizer.nToken();
         Instruction.parse(tokenizer,ts);

         tokenizer = new NaopTokenizer("x=2");
          tokenizer.nToken();
         Instruction.parse(tokenizer,ts);

         tokenizer = new NaopTokenizer("!p1");
         tokenizer.nToken();
         Exp r=Instruction.parse(tokenizer, ts);

         System.out.println(r);

         tokenizer = new NaopTokenizer("x=5");
          tokenizer.nToken();
         Instruction.parse(tokenizer,ts);

         tokenizer = new NaopTokenizer("!p1");
         tokenizer.nToken();
         r=Instruction.parse(tokenizer, ts);

         System.out.println(r);
      */
    }
    /** La boucle de l'interpréteur
     *
     * <PRE>while (true) {
     *   Afficher le prompt
     *   Lire le clavier
     *   Envoyer les entrée vers le Tokenizer
     * }
     * </PRE>
     */    
    public void run() {
        String s;
        System.out.print("$> ");
        System.out.flush();
        try {
            while ((s=this.in.readLine()) != null) {
                
                this.out.println(s);
                this.out.flush();
       
                System.out.print("$> ");
                System.out.flush();
            }
        } catch (IOException e) {}
    }
}
