/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author isaac
 */
public class HUGO {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) 
    {
             HUGO llamadaIiniciar = new HUGO();
             llamadaIiniciar.ProcedimientoHUGO();
    }
    
        public static int contador=001;
        public static String retornoFinal="";
        boolean respuesta=true;
        boolean respuestaEstructura=true;
        LexerDatos llamadoLexer = new LexerDatos();
        
    public void ProcedimientoHUGO()
    {
        
        JOptionPane.showMessageDialog(null, "Carga el archivo .HUGO que quieras para estudiarlo");
        String rutaOriginalHugo="";
        String nombreHugoyrutaSinHugo[]={"",""};
        String rutaEscritura="";
        
        try
        {
         JFrame aComponent = new JFrame();
         JFileChooser comodinChoose = new JFileChooser();
         comodinChoose.showOpenDialog(aComponent);
         File ventana =  comodinChoose.getSelectedFile();
         rutaOriginalHugo= ventana.getAbsolutePath();
         nombreHugoyrutaSinHugo=recortarRutaHugo(rutaOriginalHugo);
         
             if (rutaOriginalHugo.endsWith(".hugo") || rutaOriginalHugo.endsWith(".HUGO") || rutaOriginalHugo.endsWith(".Hugo"))
                { 
                    //CREACION LOGO
                    rutaEscritura="C:\\Program Files (x86)\\MSWLogo\\bc5\\";
                    leer(rutaOriginalHugo,nombreHugoyrutaSinHugo[0], rutaEscritura,nombreHugoyrutaSinHugo[1]);
                    JOptionPane.showMessageDialog(null, "Hola soy HUGO, Cree los archivos necesarios para poder usar MWSLOGO, \nbusca en C:\\Program Files (x86)\\MSWLogo\\bc5 \npara eliminarlos ya que yo no puedo hacerlo por el bien de tu seguridad \nArchivos creados:\n-"+
                    nombreHugoyrutaSinHugo[1]+".lgo"+"\n-llamadaA"+nombreHugoyrutaSinHugo[1]+".lgo"+"\n\nArchivo generado: \n-"+ nombreHugoyrutaSinHugo[1]+"-Hugo-errores.txt"+"\n-EVALUACION-TOTAL-POSITIVO+NEGATIVO-"+nombreHugoyrutaSinHugo[1]+".txt"+"\nUbicacion: Archivo donde se cargo .HUGO");                    
                   }else
                   {
                      JOptionPane.showMessageDialog(null, "NO ELEGISTES UN ARCHIVO.HUGO");
                      System.exit(0);
                   }

        }catch(Exception e)
        {
            System.exit(0);
        }
        
        if(respuesta==true)
        {
           //CREACION LLAMADA LOGO 
            //LexerDatos llamadoEscritor2= new LexerDatos();
           llamadoLexer.GenerarEscritura("","",rutaEscritura,nombreHugoyrutaSinHugo[1],nombreHugoyrutaSinHugo[1],2);
        }else
        { 
           JOptionPane.showMessageDialog(null, "EL CODIGO PRESENTA ERRORES EN COMANDOS, CORRIGELOS, MIRA ERRORES EN Errores-Hugo.txt\nNO SE PERMITIRA EJECUTAR MSWLOGO"); 
        }
        
            boolean respuestaEstructura=llamadoLexer.conocerResultadoEstructura();
            if(respuestaEstructura==false)
            {
              JOptionPane.showMessageDialog(null, "EL CODIGO PRESENTA ERRORES ESTRUCTURA CORRECTA PARA-COMANDOS-FIN,\nNO SE PERMITIRA EJECUTAR MSWLOGO");
            }
        
        
        System.exit(0);
    }
    
    public void leer(String rutaOriginalHugo,String nombreHugoyrutaSinHugo,  String rutaEscritura, String nombreArchivoEscribir)
    {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

     try{
          archivo = new File (rutaOriginalHugo);
          //eliminar(archivo);
          fr = new FileReader (archivo);
          br = new BufferedReader(fr);
          String linea;
          int codigoESCRIBIR=1;
          
            while((linea=br.readLine())!=null)
             {
                 if (linea.length() == 0) 
                 {
                     continue;
                 }else
                 {
                     if(contador==999)
                     {
                       llamadoLexer.GenerarEscritura(retornoFinal,linea, rutaEscritura,"\nERROR FATAL, LA CANTIDAD DE LINEAS DE CODIGO EXCEDE LAS 999",nombreArchivoEscribir,3);
                       respuesta=false;
                       JOptionPane.showMessageDialog(null, "ERROR FATAL, LA CANTIDAD DE LINEAS DE CODIGO EXCEDE LAS 999, CANTIDAD DEBE SER MENOR O IGUAL A 999");
                       break;
                     }
                     else
                     {
                       retornoFinal = EliminarEspacioBlanco(linea);
                       linea=";"+contador +" ->DATO: "+retornoFinal; 
                       respuesta=llamadoLexer.CentroEstudio(retornoFinal,linea, rutaEscritura,nombreHugoyrutaSinHugo,retornoFinal,nombreArchivoEscribir,codigoESCRIBIR);// evalua linea
                       contador++;
                     }
                 }
             }

           fr.close(); 
        }
        catch(Exception e)
        {   
            System.out.print(e);

        }

    }
    
   public String EliminarEspacioBlanco(String valorAestudiar)
    {
        String retorno="";

              for(int i=0; i<valorAestudiar.length();i++)
              {
                  if(valorAestudiar.charAt(i)!=' ')
                  {
                      retorno+=valorAestudiar.charAt(i);

                  }else if(valorAestudiar.charAt(i)==' ')
                    {
                        retorno+=valorAestudiar.charAt(i);
                        
                       while(valorAestudiar.charAt(i+1)==' ')
                         {
                           i++;
                         }  
                    }
             }
              
        return retorno;

    }

   
 public String [] recortarRutaHugo(String rutaHUGO)
   {
         String nombreHugoSinHugo="";
         String rutaCompleta="";
         String regresoDatos[]={"",""};
         
        for (int i=0; i<rutaHUGO.length(); i++) 
           { 
              if(rutaHUGO.charAt(i)=='/'||rutaHUGO.charAt(i)=='\\')
              {
                      rutaCompleta+=rutaHUGO.charAt(i);
                      nombreHugoSinHugo="";
                  
              }else
              {
                     rutaCompleta+=rutaHUGO.charAt(i);
                     nombreHugoSinHugo+=rutaHUGO.charAt(i);
              }
           }
                   rutaCompleta=rutaCompleta.replaceAll(nombreHugoSinHugo, "");
                   regresoDatos[0]=rutaCompleta;
                   nombreHugoSinHugo=nombreHugoSinHugo.replaceAll(".Hugo", "");
                   nombreHugoSinHugo=nombreHugoSinHugo.replaceAll(".HUGO", "");
                   nombreHugoSinHugo=nombreHugoSinHugo.replaceAll(".hugo", "");
                   regresoDatos[1]=nombreHugoSinHugo;
                   
           return regresoDatos;
   } 
 
}


