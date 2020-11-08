/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Isaac Morera Vargas
 */


public class escritorYejecutor 
{
    
    public void escribirCentroMando(String rutaEscritura,String cadenaEscribir, String nombreArchivoEscribir,int codigoESCRIBIR)
    {

        switch(codigoESCRIBIR)
        {
             case 1:
                 //GENERAR LOGO
                 escribir(rutaEscritura, cadenaEscribir);
                 //System.out.print("\n"+cadenaEscribir);
                 break;
                 case 2:
                       //LLAMADA LOGO
                       escribir(rutaEscritura, cadenaEscribir);
                       ejecutor("llamadaA"+nombreArchivoEscribir+".LGO");
                       
                     break;
                     case 3:
                         System.out.print("\n\nHUGO-ERRORES:");
                         escribir(rutaEscritura,cadenaEscribir);
                         //System.out.print("\n"+cadenaEscribir);
                       break;
                       case 4:
                         escribir(rutaEscritura,cadenaEscribir);
                       break;
        }
    }
    
    public void escribir(String ubicacionActual, String cadenaEscribir)
    {
        FileWriter fichero = null;
          PrintWriter pw = null;
          
             try      
               {
                    fichero = new FileWriter(ubicacionActual,true);
                    pw = new PrintWriter(fichero);
                    pw.println(cadenaEscribir);
                    System.out.print("\n"+cadenaEscribir);
                    fichero.close();
                    
               } catch (Exception e) 
                   {
                     e.printStackTrace();
                   } 
    }
        
        
        public void ejecutor(String nombreArchivoEjecutar)
        {
            
            String cmd="cmd.exe /c start cmd /k \"cd C:\\Program Files (x86)\\MSWLogo\\bc5\\"+"&&"+"logo32 -l "+nombreArchivoEjecutar;
            try
            {
                Runtime.getRuntime().exec(cmd);
            }catch(Exception e){System.out.print(e);}
        }
}
