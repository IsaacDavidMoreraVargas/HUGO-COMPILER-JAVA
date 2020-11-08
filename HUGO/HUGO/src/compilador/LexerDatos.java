/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package compilador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Isaac Morera Vargas
 */
public class LexerDatos 
{
    boolean inicioExiste=false;
    boolean finExiste=false;
    boolean respuestaPermitirLOGO=true;
    String almacenadorvariables="";
    boolean estructuraFinal=false;
    boolean estructuraContinua=false;
    
    private String EstructuraGeneral[]=  
		{
		 "PARA","FIN"
		};

    private char Abecedario[]=  
		{
		 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','_',' '
		};
    
    private String comandosSinVariable[]=  
		{
		 " "," "
		};
    
    private String comandosConVariable[]=  
		{
		 "AV","RE","GD","GI","AVANZA","RETROCEDE","GIRADERECHA","GIRAIZQUIERDA","RELLENA"
		};
    
    private String lapizSinVariable[]=  
		{
		 "OT" ,"OCULTATORTUGA","BP","BORRAPANTALLA","MT", "MUESTRATORTUGA","SL", "SUBELAPIZ","BL", "BAJALAPIZ","LAPIZNORMAL","PONLAPIZ","PLA","CENTRO","GOMA","RELLENA"
		};
    
    private String lapizConVariable[]=  
		{
		 "PONCL", "PONCOLORLAPIZ","PONCL", "PONCOLORRELLENO"
		};
    
    private String tokens[]=  
		{
		"=","(",")","+","-","*","/",";","<",">","{","}","[","]"
		};
    
    private String colores[]=  
                {
                 "NEGRO","AZUL FUERTE","VERDE","AZUL CLARO","ROJO","ROSADO","AMARILLO","BLANCO","CAFE","CAFE CLARO","VERDE MEDIO","VERDEAZUL","SALMON","LILA","NARANJA","GRIS" 
                };
    private String numeroColores[]=  
                {
                 "0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"
                };
    
    private String ERRORES[]=  
                {
                 ";[ERROR 000] CANTIDAD PARAMETROS INCORRECTOS",";[ERROR 001] NO CUMPLE ESTRUCTURA EN GENERAL",";[ERROR 002] NOMBRE ARCHIVO DIFERENTE A NOMBRE PROCEDIMIENTO",";[ERROR 003] PARAMETRO NO ES NUMERO o NUMERO TIPO INCORRECTO",";[ERROR 004] PARAMETRO NO ES VARIABLE",";[ERROR 005] NOMBRE NO ES CORRECTO",
                 ";[ERROR 006] PARAMETRO VARIABLE NO DEFINIDO",";[ERROR 007] ESTRUCTURA VARIABLE INCORRECTA",";[ERROR 008] PARAMETRO PRESENTA SIMBOLOS",";[ERROR 09] PARAMETRO NO ES TOKEN",";[ERROR 010] PARAMETRO NO ES COMENTARIO",";[ERROR 011] PARAMETRO NO ES PALABRA RESERVADA",
                 ";[ERROR 012] PARAMETRO NO ES COMANDO",";[ERROR 013] PARAMETRO NO EXISTE",";[ERROR 014] PARAMETRO NO ES COLOR",";[ERROR 015] ESTRUCTURA COMANDO INCORRECTA",";[ERROR 016] INICIO PARAMETRO ILEGAL",";[ERROR 017] PARAMETRO CANTIDAD INCORRECTA",
                 ";[ERROR 018] INSTRUCCION NO ES SOPORTADO POR ESTA VERSION",";INSTRUCCION NO ES SOPORTADO POR ESTA VERSION",";[ERROR 020] CANTIDAD CORCHETES ERRONEA",";[ERROR 021] CORCHETES INICIO O FIN INEXISTENTE", ";[ERROR 022] ESTRUCTURA CORCHETES ERRONEA", ";[ERROR 023] CORCHETE INICIO ERRONEO",
                 ";[ERROR 024] CORCHETE FIN ERRONEO",";[ERROR 025] PARAMETRO TIPO NO ES CORRECTO"
                };
    
    private String CORRECTO[]=  
                {
                 ";CANTIDAD PARAMETROS CORRECTO",";PARAMETROS CUMPLE ESTRUCTURA EN GENERAL",";NOMBRE ARCHIVO IDENTICO A NOMBRE PROCEDIMIENTO CORRECTO",";PARAMETRO ES NUMERO",";PARAMETRO ES VARIABLE",";PARAMETRO NOMBRE CORRECTO",";PARAMETRO VARIABLE CORRECTO",";ESTRUCTURA VARIABLE CORRECTA",
                 ";PARAMETRO ES SIMBOLO",";PARAMETRO ES TOKEN",";PARAMETRO ES COMENTARIO",";PARAMETRO ES PALABRA RESERVADA",";PARAMETRO ES COMANDO",";PARAMETRO EXISTE",";PARAMETRO ES COLOR",";ESTRUCTURA COMANDO CORRECTA","INICIO PARAMETRO LEGAL",";PARAMETRO CANTIDAD CORRECTA SIMBOLOS",
                 ";COMANDO ES SOPORTADO POR ESTA VERSION",";COMANDO ES SOPORTADO POR ESTA VERSION",";CANTIDAD CORCHETES CORRECTA",";CORCHETES INICIO Y FIN EXISTEN", "; ESTRUCTURA CORCHETES CORRECTA",";CORCHETE INICIO CORRECTO",";CORCHETE FIN CORRECTO",";PARAMETRO TIPO CORRECTO" 
                };
    
    private String noSoportado[]=  
                {
                 "LIMPIA","ROTULA","PONGROSO","PONPOS","PERSPECTIVA","PONBALANCEO","PONCABECEO","BALANCEA","BALANCEAIZQUIERDA","BAJANARIZ","R","PONG","BAL","CAI","BAJAN","MUESTRA","ESCRIBE" ,"MUESTRAT" ,"EDITA CONTENIDO","EDITA PROCEDIMIENTOS","EDITA" ,"EDITAERO","LISTA","LISTA?",
                 "TAPA","TAPADO","BORRA" ,"BORRARPROCEDIMIENTOS","BORRA","CARGA" ,"GUARDA","BOARCHIVO","ES","EDITATODO","EDITAPROCEDIMIENTOS","ED","TAPATODO","BO","BA"
 
                };
    
    
    public boolean CentroEstudio(String lineaOriginal, String lineaModificada, String rutaEscritura,String rutaOrigen, String tokenAEstudiar, String nombreArchivoEscribir, int configurarMensaje)
    {   
                  String[] vector=null;
                  tokenAEstudiar = tokenAEstudiar.toUpperCase();
                  vector = tokenAEstudiar.split(" ");    
                  String CadenaDevolucion[]={" "," "," "," "};
                  
            if(finExiste==false)
                {    
                  CadenaDevolucion[0]="false";
                  CadenaDevolucion[2]="false"; 

                     CadenaDevolucion=cumpleEstructuraGeneral(lineaModificada,"",nombreArchivoEscribir,vector,0);
                     //datoEsComentario("",vector,0);
                     
                     if(CadenaDevolucion[0].equals("true"))
                      {
                          if(!CadenaDevolucion[2].equals("true"))
                          {
                             GenerarEscritura(lineaOriginal, lineaModificada,rutaEscritura,tokenAEstudiar, nombreArchivoEscribir,1); 
                          }
                         GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen,CadenaDevolucion[1], nombreArchivoEscribir,5);
                      }
                     
                     if(CadenaDevolucion[2].equals("true"))
                          {
                             //String escriba=";---------EVALUACION LINEA----------\n"+lineaOriginal+CadenaDevolucion[3];
                             GenerarEscritura(lineaOriginal, lineaModificada,rutaEscritura,tokenAEstudiar, nombreArchivoEscribir,4);
                             GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen,CadenaDevolucion[3], nombreArchivoEscribir,5);
                             GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen, CadenaDevolucion[3], nombreArchivoEscribir+"-HUGO-errores",3);
                          }else
                              {
                                 CadenaDevolucion[0]="false";
                                 CadenaDevolucion[2]="false"; 
                                 
                                 if(inicioExiste==true||finExiste==false)
                                  {  
                                    CadenaDevolucion=datoEsComentario("",vector,0);

                                    if(CadenaDevolucion[0].equals("true"))
                                     {
                                        if(!CadenaDevolucion[2].equals("true"))
                                         { 
                                             GenerarEscritura(lineaOriginal, lineaModificada,rutaEscritura,tokenAEstudiar, nombreArchivoEscribir,1); 
                                         }
                                         GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen,CadenaDevolucion[1], nombreArchivoEscribir,5);
                                     }
                                        
                                     if(CadenaDevolucion[2].equals("true"))
                                      {
                                           GenerarEscritura(lineaOriginal, lineaModificada,rutaEscritura,tokenAEstudiar, nombreArchivoEscribir,4);
                                           GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen,CadenaDevolucion[3], nombreArchivoEscribir,5); 
                                           GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen, CadenaDevolucion[3], nombreArchivoEscribir+"-HUGO-errores",3);
                                      }else
                                          {
                                             CadenaDevolucion[0]="false";
                                             CadenaDevolucion[2]="false";  
                                             CadenaDevolucion=datoEstructuraHaz(lineaOriginal,"",vector,0);

                                               if(CadenaDevolucion[0].equals("true"))
                                                 {
                                                     if(!CadenaDevolucion[2].equals("true"))
                                                       {
                                                          GenerarEscritura(lineaOriginal, lineaModificada,rutaEscritura,tokenAEstudiar, nombreArchivoEscribir,1); 
                                                       }
                                                    GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen,CadenaDevolucion[1], nombreArchivoEscribir,5);
                                                 }
                                               
                                               if(CadenaDevolucion[2].equals("true"))
                                                 {
                                                    GenerarEscritura(lineaOriginal, lineaModificada,rutaEscritura,tokenAEstudiar, nombreArchivoEscribir,4);
                                                    GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen,CadenaDevolucion[3], nombreArchivoEscribir,5); 
                                                    GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen, CadenaDevolucion[3], nombreArchivoEscribir+"-HUGO-errores",3);
                                                 }else
                                                 {
                                                     CadenaDevolucion[0]="false";
                                                     CadenaDevolucion[2]="false"; 
                                                     
                                                             CadenaDevolucion=datoEsComandoConVariable(lineaOriginal,"",vector,0);      

                                                                if(CadenaDevolucion[0].equals("true"))
                                                                 {
                                                                     if(!CadenaDevolucion[2].equals("true"))
                                                                      {
                                                                         GenerarEscritura(lineaOriginal, lineaModificada,rutaEscritura,tokenAEstudiar, nombreArchivoEscribir,1); 
                                                                      }
                                                                   GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen,CadenaDevolucion[1], nombreArchivoEscribir,5);
                                                                 }
                                                                
                                                                if(CadenaDevolucion[2].equals("true"))
                                                                    {
                                                                      GenerarEscritura(lineaOriginal, lineaModificada,rutaEscritura,tokenAEstudiar, nombreArchivoEscribir,4);
                                                                      GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen,CadenaDevolucion[3], nombreArchivoEscribir,5);   
                                                                      GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen, CadenaDevolucion[3], nombreArchivoEscribir+"-HUGO-errores",3);
                                                                    }else
                                                                      {
                                                                          CadenaDevolucion[0]="false";
                                                                          CadenaDevolucion[2]="false"; 
                                                                          CadenaDevolucion=datoEsLapizConVariable(lineaOriginal,"",vector,0);

                                                                            if(CadenaDevolucion[0].equals("true"))
                                                                              {
                                                                                  if(!CadenaDevolucion[2].equals("true"))
                                                                                   {
                                                                                        GenerarEscritura(lineaOriginal, lineaModificada,rutaEscritura,tokenAEstudiar, nombreArchivoEscribir,1); 
                                                                                   }
                                                                                GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen,CadenaDevolucion[1], nombreArchivoEscribir,5);
                                                                              }
                                                                            
                                                                            if(CadenaDevolucion[2].equals("true"))
                                                                                 {
                                                                                   GenerarEscritura(lineaOriginal, lineaModificada,rutaEscritura,tokenAEstudiar, nombreArchivoEscribir,4);
                                                                                   GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen,CadenaDevolucion[3], nombreArchivoEscribir,5);
                                                                                   GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen, CadenaDevolucion[3], nombreArchivoEscribir+"-HUGO-errores",3);
                                                                                 }else
                                                                                    { 
                                                                                       CadenaDevolucion[0]="false";
                                                                                       CadenaDevolucion[2]="false"; 
                                                                                       CadenaDevolucion=datoEsLapizSinVariable(lineaOriginal,"",vector,0);
                                                                                         
                                                                                        if(CadenaDevolucion[0].equals("true"))
                                                                                          {
                                                                                              if(!CadenaDevolucion[2].equals("true"))
                                                                                               {
                                                                                                   GenerarEscritura(lineaOriginal, lineaModificada,rutaEscritura,tokenAEstudiar, nombreArchivoEscribir,1); 
                                                                                               }
                                                                                             GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen,CadenaDevolucion[1], nombreArchivoEscribir,5);
                                                                                          }
                                                                                        
                                                                                        if(CadenaDevolucion[2].equals("true"))
                                                                                              {
                                                                                                  GenerarEscritura(lineaOriginal, lineaModificada,rutaEscritura,tokenAEstudiar, nombreArchivoEscribir,4);
                                                                                                  GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen,CadenaDevolucion[3], nombreArchivoEscribir,5); 
                                                                                                  GenerarEscritura(lineaOriginal, lineaModificada,rutaOrigen, CadenaDevolucion[3], nombreArchivoEscribir+"-HUGO-errores",3);
                                                                                              }else
                                                                                                {  
                                                                                                   if(CadenaDevolucion[2].equals("true"))
                                                                                                   {
                                                                                                      GenerarEscritura(lineaOriginal, lineaModificada,rutaEscritura,tokenAEstudiar, nombreArchivoEscribir,4);  
                                                                                                      GenerarEscritura(lineaOriginal, lineaModificada, rutaOrigen,"\n"+lineaModificada+"-> Este dato NO ES RECONOCIDO, NO SERA PROCESADO",nombreArchivoEscribir, 5); 
                                                                                                      GenerarEscritura(lineaOriginal, lineaModificada, rutaOrigen,lineaModificada+"-> Este dato NO ES RECONOCIDO, NO SERA PROCESADO",nombreArchivoEscribir+"-HUGO-errores",3);
                                                                                                      
                                                                                                   }
                                                                                                }
                                                                                    }      
                                                                     }
                               
                                                         }
                                           }
                                  }
                              }
                     
                        }else if(finExiste==true)
                             {
                                 
                               String como=vector[0];
                                
                               if(como.charAt(0)==';')
                               {
                                  GenerarEscritura(lineaOriginal, lineaModificada, rutaOrigen,"\n"+tokenAEstudiar+" "+CORRECTO[10]+" PARAMETRO DESPUES DE FIN, GENERA ERRORES",nombreArchivoEscribir, 5); 
                                  GenerarEscritura(lineaOriginal, lineaModificada, rutaEscritura,tokenAEstudiar+" "+CORRECTO[10],nombreArchivoEscribir, configurarMensaje);
                                  GenerarEscritura(lineaOriginal, "\n"+lineaModificada, rutaOrigen,"\n;-> PARAMETRO DESPUES DE FIN, GENERA ERRORES",nombreArchivoEscribir+"-HUGO-errores",3);
                               }else
                               {
                                   GenerarEscritura(lineaOriginal, lineaModificada, rutaOrigen,"\n"+lineaModificada+"-> Este dato NO ES RECONOCIDO, NO SERA PROCESADO",nombreArchivoEscribir, 5);
                                   GenerarEscritura(lineaOriginal, "\n"+lineaModificada, rutaOrigen,"\n;-> PARAMETRO DESPUES DE FIN, GENERA ERRORES",nombreArchivoEscribir+"-HUGO-errores",3);
                                   GenerarEscritura(lineaOriginal, lineaModificada, rutaEscritura,lineaModificada+";-> Este dato esta despues de FIN, GENERA ERRORES, si no estas conforme mueve el FIN al final",nombreArchivoEscribir, configurarMensaje); 
                                   estructuraFinal=false;
                               }

                             }      

         return respuestaPermitirLOGO;
    }

    public void GenerarEscritura(String lineaOriginal,String lineaModificada,String rutaEscritura,String DatoEscribir, String nombreArchivoEscribir ,int configurarMensaje)
    {
        switch (configurarMensaje) {
            case 1:
                {
                    int codigoGenerarMensaje=1;
                    String ubicacionActual = rutaEscritura +"\\"+nombreArchivoEscribir+".LGO";
                    String cadenaEscribirFinal="";
                    cadenaEscribirFinal=";---------EVALUACION LINEA----------\n"+DatoEscribir;
                    escritorYejecutor llamadoEscritor1 = new escritorYejecutor();
                    llamadoEscritor1.escribirCentroMando(ubicacionActual,cadenaEscribirFinal,nombreArchivoEscribir, codigoGenerarMensaje);
                    break;
                }
            case 2:
                {
                    int  codigoGenerarMensaje=2;
                    String ubicacionActual = rutaEscritura+"\\"+"llamadaA"+nombreArchivoEscribir+".LGO";
                    escritorYejecutor llamadoEscritor2 = new escritorYejecutor();
                    llamadoEscritor2.escribirCentroMando(ubicacionActual,DatoEscribir,nombreArchivoEscribir, codigoGenerarMensaje);
                    break;
                }
            case 3:
                {
                    respuestaPermitirLOGO=false;
                    int codigoGenerarMensaje=3;
                    String ubicacionActual = rutaEscritura +"\\"+nombreArchivoEscribir+".txt";
                    String cadenaEscribirFinal="";
                    if(DatoEscribir.length()<9)
                    {
                      DatoEscribir="NINGUNO";
                      cadenaEscribirFinal="";
                    }else
                    {
                      
                      cadenaEscribirFinal=lineaOriginal+lineaModificada+" --->PRESENTA ERROR-->"+DatoEscribir+"\n";
                    }
                   
                    escritorYejecutor llamadoEscritor3 = new escritorYejecutor();
                    llamadoEscritor3.escribirCentroMando(ubicacionActual,cadenaEscribirFinal,"", codigoGenerarMensaje);
                    break;
                }
            case 4:
                {
                    int codigoGenerarMensaje=1;
                    String ubicacionActual = rutaEscritura +"\\"+nombreArchivoEscribir+".LGO";
                    String cadenaEscribirFinal="";
                    cadenaEscribirFinal=DatoEscribir;
                    escritorYejecutor llamadoEscritor4 = new escritorYejecutor();
                    llamadoEscritor4.escribirCentroMando(ubicacionActual,cadenaEscribirFinal,nombreArchivoEscribir, codigoGenerarMensaje);
                    break;
                }
                
            case 5:
                {
                    //respuestaPermitirLOGO=false;
                    int codigoGenerarMensaje=4;
                    String ubicacionActual = rutaEscritura +"\\"+"EVALUACION-TOTAL-POSITIVO+NEGATIVO-"+nombreArchivoEscribir+".txt";
                    String cadenaEscribirFinal="";
                    //cadenaEscribirFinal=lineaOriginal+lineaModificada+DatoEscribir+"\n";
                    cadenaEscribirFinal=";---------EVALUACION LINEA----------\n"+lineaOriginal+"\n"+lineaModificada+";"+DatoEscribir;
                    escritorYejecutor llamadoEscritor3 = new escritorYejecutor();
                    llamadoEscritor3.escribirCentroMando(ubicacionActual,cadenaEscribirFinal,"", codigoGenerarMensaje);
                    break;
                }    
                
            default:
                break;
        }
    }

    public String [] cumpleEstructuraGeneral(String lineaModificada, String acumulador,String nombreArchivoEscribir,String cadena[], int banderaInicio)
    {
       
       String CadenaDevolucion[]={" "," "," "," "};
       String CadenaRespuesta[]={" "," "};
       String acumuladorV="";
       String acumuladorF="";
          
       //ANALIZA INICIO

       if(inicioExiste==false||finExiste==false)
       {
         for(int bandera=banderaInicio; bandera<cadena.length; bandera++)
         {
           if(cadena[bandera].equals("PARA"))
             {
                acumuladorV+=datoEsReservada(cadena[bandera]);
                
                if(cadena.length==1)
                { 
                    acumuladorF+="\n"+ERRORES[0];
                    break;
                }else
                {
                    acumuladorV+="\n"+CORRECTO[0];
                    bandera++;
                    acumulador=cadena[bandera];
                    
                    CadenaRespuesta=datoEsVariable(acumulador);
                    
                    if(CadenaRespuesta[1].equals("true"))
                     {
                         acumuladorF+="\n"+ERRORES[1]+" "+CORRECTO[4];
                     }else
                      {
                          acumuladorV+="\n"+CORRECTO[5];
                          String nomb=nombreArchivoEscribir.toUpperCase();
                          if(acumulador.equals(nomb))
                          {
                            acumuladorV+="\n"+CORRECTO[2];  
                            //inicioExiste=true;
                          }else
                          {
                            acumuladorF+="\n"+ERRORES[2];
                          }
                            inicioExiste=true;
                            
                             if(bandera<cadena.length)
                               {
                                 bandera++;  
                                 
                                for(int bandera2=bandera; bandera2<cadena.length; bandera2++)
                                  {
                                     acumulador=cadena[bandera2];
                                     
                                     CadenaRespuesta=datoEsVariable(acumulador); 
                                     
                                    if(CadenaRespuesta[1].equals("true"))
                                      {
                                         acumuladorV+="\n; "+cadena[bandera2]+CORRECTO[7];
                                         almacenadorvariables+=cadena[bandera2]+",";
                                         inicioExiste=true;
                                         CadenaRespuesta[0]="";
                                         CadenaRespuesta[1]="false";
                                      }else
                                       {
                                         acumuladorF+=cadena[bandera2]+ERRORES[7];
                                         inicioExiste=false;
                                         /*
                                         //inicioExiste=false;
                                         CadenaRespuesta[0]="";
                                         CadenaRespuesta[1]="false";
                                         */
                                       }
                                 }
                                 
                               }else
                                {
                                    //acumuladorF+="\n;"+cadena[bandera]+": intento asignar ESTRUCTURA GENERAL es -> FALLIDO, ES NECESARIO NOMBRE PROCEDIMIENTO linea:---> "+lineaModificada;
                                }
                      }
                 }    
             }else
              {
              }
          }     
       }

       //ANALIZA FIN
       if(inicioExiste==true&&finExiste==false)
       {
         if(cadena[banderaInicio].equals("FIN"))
         {
            datoEsReservada(cadena[banderaInicio]);
            
            if(cadena.length>1)
             { 
                acumuladorF="\n"+ERRORES[0];
             }else
              {
                finExiste=true; 
                acumuladorV="\n"+CORRECTO[1];
                estructuraFinal=true;
              }     
         }else
         {
         }
         
       }
       
       if(inicioExiste==false&&finExiste==false)
       {
           estructuraFinal=false;
       }
       
               if(acumuladorF.length()>1)
                {
                  //acumuladorV="";  
                  CadenaDevolucion[2]="true";
                  CadenaDevolucion[3]=acumuladorF;
                }
                
                if(acumuladorV.length()>1)
                {
                  CadenaDevolucion[0]="true";
                  CadenaDevolucion[1]=acumuladorV;
                }
                
       return CadenaDevolucion;
    }
    
    public boolean variableExiste(String cadena)
    {
        boolean respuesta=false;
        String acumuladorComodin[]=  almacenadorvariables.split(",");
        
        for(int banderaC=0; banderaC<acumuladorComodin.length; banderaC++)
        {
            if(cadena.equals(acumuladorComodin[banderaC]))
            {
               respuesta=true;
               break;
            }else
            {
               respuesta=false;
            }
                    
        }
        
        return respuesta;
    }
    
    public boolean variableExisteConEvaluacion(String cadena)
    {
        boolean respuesta=false;
        String acumuladorComodin[]=  almacenadorvariables.split(",");
        
        for(int banderaC=0; banderaC<acumuladorComodin.length; banderaC++)
        {
            if(cadena.equals(acumuladorComodin[banderaC]))
            {
                
               respuesta=variableExiste(cadena);
               break;
            }else
            {
               respuesta=false;
            }
                    
        }
        
        return respuesta;
    }
        
    public boolean datoEsNumero(String cadena)
    {
        Boolean retorno=false;
        
        try{
             int comodin=Integer.parseInt(cadena);
             
             if(cadena.contains("."))
             retorno=true;
             
             if((cadena.contains(".") || cadena.contains(","))&&retorno==true)
             {
                   retorno =false;
             }else
              {
                  retorno=true;
              }
           }catch(Exception e)
           {
             retorno=false;
           }
        
        return retorno;
    }
    
    public String [] datoEsVariable(String cadena)
    {
        Boolean retorno=false;
        String respuesta[]={"",""};
        
        if(cadena.charAt(0)==':')
        {
            respuesta[0]="\n"+CORRECTO[4];
            respuesta[1]="true";
        }else
        {
            retorno=datoEsNumero(cadena);
           
           if(retorno==false)
           {
             respuesta[0]="\n"+ERRORES[3];
             respuesta[1]="false";
           }else
           {
             respuesta[0]="\n"+CORRECTO[3];
             respuesta[1]="true";
           }
        }
        return respuesta;
    }
    
    public String [] datoNombreVariable(String cadena)
    {
        String respuesta[]={"",""};
        
        String retornoA=datoEsReservada(cadena);
        
        if(retornoA.length()>2)
        {
            respuesta[0]+="\n"+ERRORES[0]+"\n"+CORRECTO[11];
            respuesta[1]="false";
        }else
        {
              
               String comodin1=String.valueOf(cadena.charAt(0));
               boolean retorno=datoEsNumero(comodin1);
               boolean retorno2=datoEsNumero(cadena);
               
                if(retorno==true&&cadena.length()>1)
                {
                   respuesta[0]+="\n"+ERRORES[16];
                   respuesta[1]="false";
                   
                }else if(retorno==true)
                 {
                    respuesta[0]+="\n"+ERRORES[5]+" "+CORRECTO[3];
                    respuesta[1]="false";
                 }
               
                char[] arrayChar = cadena.toCharArray();
                  
                 for(int bandera=0; bandera<arrayChar.length; bandera++)
                 {
                     char comodin=arrayChar[bandera];
                      
                   
                      if(comodin == '_'&&bandera==0)
                        {
                          respuesta[0]+="\n"+ERRORES[16];
                          respuesta[1]="false";
                        }
                   
                      if(comodin == '_'&&bandera!=0)
                        {
                            bandera++;
                            comodin=arrayChar[bandera];
                            
                            if(comodin == '_')
                             {
                               respuesta[0]+="\n"+ERRORES[17];
                               respuesta[1]="false";
                               break;
                            }
                        }
                 }
                 
                  String acumular="";
                  
                        for(int bandera=0; bandera<arrayChar.length; bandera++)
                        {
                              for(int bandera2=0; bandera2<Abecedario.length; bandera2++)
                               {   
                                  if(arrayChar[bandera]==Abecedario[bandera2])
                                   {
		                     acumular+=arrayChar[bandera];
                                     //System.out.print("\n"+arrayChar[bandera]);
                                   }else
                                    {
                                       String num=""; 
                                       num+=arrayChar[bandera];
                                       boolean esNumero=datoEsNumero(num);
                                       if(esNumero==true)
                                       {
                                         acumular+=arrayChar[bandera];
                                         //System.out.print("\n"+arrayChar[bandera]);
                                         break;
                                       }
                                    }
                               }
                              
	               }
                        
                        char[] arrayChar1 = acumular.toCharArray();
                        
                        int num1=arrayChar.length;
                        int num2=arrayChar1.length;
                        
                        if(num1!=num2)
                        {
                           respuesta[0]+="\n"+ERRORES[8];
                           respuesta[1]="false";
                        }
                        
                        if(cadena.contains(" "))
                        {
                           respuesta[0]+="\n"+ERRORES[1];
                           respuesta[1]="false";
                        }
        }
        
        return respuesta;
    }

    public String [] datoEsComentario(String acumulador,String cadena[], int banderaInicio)
    {
        String CadenaDevolucion[]={" "," "," "," "};
        String acumuladorV="";
        String acumuladorF="";
        
        String comodin=cadena[banderaInicio];
        
        if(comodin.charAt(0)==';')
        {
            comodin="";
            /*
            for(int flag=0; flag<cadena.length;flag++)
              {
                 comodin+=cadena[flag];
                 comodin+=" ";
              }
*/
               acumuladorV+=comodin+" "+CORRECTO[10]; 
               acumuladorF="";
        }else 
        {
            acumuladorF="";
        }
        
                if(acumuladorF.length()>1)
                {
                  //acumuladorV="";  
                  CadenaDevolucion[2]="true";
                  CadenaDevolucion[3]=acumuladorF;
                }
                
                if(acumuladorV.length()>1)
                {
                  CadenaDevolucion[0]="true";
                  CadenaDevolucion[1]=acumuladorV;
                }
        return CadenaDevolucion;
        
    }

    public String datoEsReservada(String cadena)
    {
        String acumulador="";
        TiposDatos llamadoClaseDatos = new TiposDatos();
        String ubicacionActual = "primitivas.txt";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
     try{
          archivo = new File (ubicacionActual);
          fr = new FileReader (archivo);
          br = new BufferedReader(fr);
          String linea;
          
            while((linea=br.readLine())!=null)
             {
               if(cadena.equals(linea))
               {
                 llamadoClaseDatos.setReservadas(linea);
                 llamadoClaseDatos.setTipo("palabra reservada");
                 acumulador=CORRECTO[11];
                 //GenerarEscritura(lineaOriginal, lineaModificada, rutaEscritura,acumulador,nombreArchivoEscribir, 4);
               }else
               {

               }
             }
            
                     fr.close();
        }
         catch(Exception e) { }finally
          { }
     
     return acumulador;
    }
     
    public String [] datoEstructuraHaz(String lineaOriginal,String acumulador,String cadena[], int banderaInicio)
    {
        String CadenaDevolucion[]={" "," "," "," "};
        String CadenaRespuesta[]={" "," "};
        String acumuladorV="";
        String acumuladorF="";
        boolean posible=false;
        
        if(cadena[banderaInicio].equals("HAZ"))
        {   
            acumuladorV+=datoEsReservada(cadena[banderaInicio]);
            
          if(cadena.length==1)
            { 
               acumuladorF="\n"+ERRORES[0];
            }else
                {
                    banderaInicio++;
                    acumulador=cadena[banderaInicio];
                  
                  if(acumulador.charAt(0)=='"')
                    {
                        acumuladorV+="\n"+CORRECTO[5];
                    }else
                     {
                        acumuladorF+="\n"+ERRORES[5];
                     }
                  
                  try{
                       banderaInicio++;
                       acumulador=cadena[banderaInicio];
                       posible=true;
                  }catch(Exception e)
                  {
                  
                  }
                  
                  if(posible==true)
                  {
                   //banderaInicio++;
                   //acumulador=cadena[banderaInicio];   
                   CadenaRespuesta=datoEsVariable(acumulador);
                  
                  if(CadenaRespuesta[1].equals("true"))
                  {
                    acumuladorV+=CadenaRespuesta[0];
                  }else
                  {
                     acumuladorF+=CadenaRespuesta[0];
                  }
            
                  if(acumulador.charAt(0)==':')
                    {
                        acumuladorV+="\n;"+CORRECTO[1];
                        
                         boolean variableExisteboolean=variableExiste(acumulador);
                           if(variableExisteboolean==true)
                           {
                              acumuladorV+="\n"+CORRECTO[6];
                           }else
                           {
                              acumuladorF+="\n"+ERRORES[6];
                           }
                    }else
                     {
                         Boolean respuesta=datoEsNumero(acumulador);
                         
                         if(respuesta==true)
                          {
                               acumuladorV+="\n"+CORRECTO[3]+"\n"+CORRECTO[4];  
                          }else
                            {
                                acumuladorF+="\n"+ERRORES[3]+"\n"+ERRORES[4];
                            }   
                     }
                  }else
                  {
                  
                  }
                }   
        }
        
              if(acumuladorF.length()>1)
                {
                  //acumuladorV="";  
                  CadenaDevolucion[2]="true";
                  CadenaDevolucion[3]=acumuladorF;
                }
                
                if(acumuladorV.length()>1)
                {
                  CadenaDevolucion[0]="true";
                  CadenaDevolucion[1]=acumuladorV;
                }
                
        return CadenaDevolucion;

    }
    
    public boolean datoEsToken(String cadena)
    {
        Boolean respuesta=false;

       for(int flag=0; flag<tokens.length;flag++)
       {
          if(cadena.equals(tokens[flag]))
          {
            respuesta=true;
            break;
          }else
          {
             respuesta=false;
          }
       }
       
       return respuesta;
    }

    public String [] datoEsComandoConVariable(String lineaOriginal,String acumulador,String cadena[], int banderaInicio)
    {
        String CadenaDevolucion[]={" "," "," "," "};
        String acumuladorV="";
        String acumuladorF="";
        
         for(int flag=banderaInicio; flag<comandosConVariable.length; flag++)
          {
                String comodin=comandosConVariable[flag];
                
                if(comodin.equals("RELLENA")&&cadena.length==1)
                  {
                        acumuladorF="";
                        flag=comandosConVariable.length;
                        break;
                        
                  }else if(comodin.equals(cadena[banderaInicio])&&cadena.length>1)
                   {
                        if(cadena.length>2||cadena.length==1)
                           {
                                acumuladorF+="\n"+ERRORES[0];
                                acumuladorV+=datoEsReservada(cadena[banderaInicio]);
                                flag=comandosConVariable.length;
                                break;
                           }else
                             {
                                
                                acumuladorV+=datoEsReservada(comodin);
                                acumuladorV+="\n"+CORRECTO[12];

                                banderaInicio++;
                                comodin=cadena[banderaInicio];
                                
                                   Boolean respuesta = datoEsNumero(comodin);
                                    if(respuesta==false)
                                    {
                                        if(comodin.charAt(0)==':')
                                        {
                                           acumuladorV+="\n"+CORRECTO[4];
                                           
                                            boolean variableExisteboolean=variableExiste(comodin);
                                             if(variableExisteboolean==true)
                                             {
                                                acumuladorV+="\n"+CORRECTO[6];
                                             }else
                                             {
                                                acumuladorF+="\n"+ERRORES[6];
                                             }
                                        }else
                                        {
                                           acumuladorF+="\n"+ERRORES[4];
                                        } 
                                    }else
                                    {
                                       acumuladorV+="\n"+CORRECTO[3];
                                       break;
                                    }
                                break;
                             }
                   }
          }
                if(acumuladorF.length()>1)
                {
                  //acumuladorV="";  
                  CadenaDevolucion[2]="true";
                  CadenaDevolucion[3]=acumuladorF;
                }
                
                if(acumuladorV.length()>1)
                {
                  CadenaDevolucion[0]="true";
                  CadenaDevolucion[1]=acumuladorV;
                }
                
        return CadenaDevolucion;
    }
    
    public String [] datoEsLapizConVariable(String lineaOriginal, String acumulador,String cadena[], int banderaInicio)
    {
  
        String CadenaDevolucion[]={" "," "," "," "};
        String acumuladorV="";
        String acumuladorF=""; 

       //ENCUENTRA COMANDOS LAPIZ CON VARIABLES
    try{
       for(int flag=banderaInicio; flag<cadena.length;flag++)
       {
           String comodin1=cadena[flag];
           
                if((comodin1.equals("PONCOLORLAPIZ")||(comodin1.equals("PONCL"))))
                 {
                     try
                     {
                       CadenaDevolucion=EstructuraLapizVariable(lineaOriginal,"",cadena,0);
                       return CadenaDevolucion;
                     }catch(Exception e)
                     {
                        System.out.print("\n"+e);
                     }

                }else if(comodin1.equals("REPITE"))
                 {
                    if(cadena.length==1)
                    {
                       acumuladorF="\n"+ERRORES[0];
                       return CadenaDevolucion;
                       //break;
                    }else
                    {
                       CadenaDevolucion=loopRepetir("",cadena,0);
                    }
                    
                 }else if(comodin1.equals("PONCOLORRELLENO"))
                 {
                     try{
                         CadenaDevolucion=EstructuraLapizVariable(lineaOriginal,"",cadena,0); 
                         return CadenaDevolucion;
                     }catch(Exception e)
                     {
                        System.out.print("\nssssssssssssssssssssss "+e);
                     }
                 }else
                  {
                     for(int bandera=0; bandera<noSoportado.length;bandera++)
                     {
                         String evaluador=noSoportado[bandera];
                         
                        if(comodin1.equals(evaluador))
                        {
                          CadenaDevolucion[0]="true";
                          CadenaDevolucion[1]="\n"+ERRORES[19];
                          CadenaDevolucion[2]="false";
                          CadenaDevolucion[3]=ERRORES[18];
                          break;
                        }else
                        { 
                        
                        }
                         
                     }
                  
                  }
       }

       }catch(Exception e)
       {
          System.out.print(e);
       }

            return CadenaDevolucion;
    }
    
    public String [] EstructuraLapizVariable(String lineaOriginal, String acumulador,String cadena[], int banderaInicio)
    {
        String CadenaDevolucion[]={" "," "," "," "};
        String acumuladorV="";
        String acumuladorF="";
         
         if(cadena.length==1||cadena.length>2)
          {    
            acumuladorF+="\n"+ERRORES[0];
          }else
            {     

               for(int flag=banderaInicio; flag<cadena.length;flag++)
                {
                  String comodin1=cadena[flag];
           
                        for(int flag2=0; flag2<lapizConVariable.length;flag2++)
                        {
                             String comodin2=lapizConVariable[flag2];
      
                              if(comodin2.equals(comodin1))
                               {
                                   acumuladorV+="\n"+CORRECTO[1]+"\n"+CORRECTO[18];
                                   boolean encontrado=false;
                                   boolean encontrado2=false;
                                   acumuladorV+=datoEsReservada(comodin1);
                     
                                      flag++;
                                      comodin1=cadena[flag]; 
                                      boolean respuesta=datoEsNumero(comodin1);
                            
                                          if(respuesta==true)
                                           {

                                                if(Integer.parseInt(comodin1)>15)
                                                 {
                                                     acumuladorF+="\n"+ERRORES[13];
                                                     encontrado=false;

                                                     break;
                                                 }else
                                                   {
                                                       acumuladorV+="\n"+CORRECTO[3];
                                                       encontrado=true;
                                                       //break;
                                                   }
                                               
                                            }else
                                              {
                                                      for(int flag4=0; flag4<colores.length;flag4++)
                                                       {
                                                          String comodinEncontrar=colores[flag4];
                                                          
                                                          if(comodin1.equals(comodinEncontrar))
                                                          {
                                                             encontrado=true; 
                                                             acumuladorV+="\n"+numeroColores[flag4]+CORRECTO[3]+"\n"+CORRECTO[14];
                                                             encontrado2=true;
                                                             acumuladorF="";
                                                             break;
                                           
                                                          }else
                                                          {
                                                              if(comodin1.charAt(0)==':')
                                                              {
                                                                acumuladorV+="\n"+CORRECTO[4];
                                              
                                                                boolean variableExisteboolean=variableExiste(comodin1);
                                                                 if(variableExisteboolean==true)
                                                                 {
                                                                    acumuladorV+="\n"+CORRECTO[6];
                                                      
                                                                 }else
                                                                 {
                                                                    acumuladorF+="\n"+ERRORES[6];
                                                                 }
                                             
                                                                encontrado2=true;
                                                                break;
                                                              }else
                                                              {
                                                                  encontrado2=false;
                                                              }
                                                         }
                                                       }
                                    
                                       if(encontrado2==true)
                                         {
                                            acumuladorV+="\n"+CORRECTO[1];
                                         }else if(encontrado2==false)
                                         {
                                             acumuladorF+="\n"+ERRORES[3]+"\n"+ERRORES[4]+"\n"+ERRORES[14];
                                         }
                                       
                                       if(encontrado==true)
                                         {
                                            acumuladorV+="\n"+CORRECTO[14];
                                         }else
                                         {
                                         }
                                    }
                    //break;
                               }
          
                        }

                }
            }
                if(acumuladorF.length()>1)
                {
                  //acumuladorV="";  
                  CadenaDevolucion[2]="true";
                  CadenaDevolucion[3]=acumuladorF;
                }
                
                if(acumuladorV.length()>1)
                {
                  CadenaDevolucion[0]="true";
                  CadenaDevolucion[1]=acumuladorV;
                }
        
       return CadenaDevolucion;
    }
    
    
    public String [] datoEsLapizSinVariable(String lineaOriginal, String acumulador,String cadena[], int banderaInicio)
    {
  
        String CadenaDevolucion[]={" "," "," "," "};
        String acumuladorV="";
        String acumuladorF=""; 
        
           //ENCUENTRA COMANDO TORTUGA SIN VARIABLES
        for(int flag2=banderaInicio; flag2<lapizSinVariable.length;flag2++)
           {
             String comodin2=lapizSinVariable[flag2];
             
             if(comodin2.equals("RELLENA")&&cadena.length>1)
                 {
                   acumuladorF="";
                   flag2=lapizSinVariable.length;
                   break;
                   
                } else if(cadena[banderaInicio].equals(comodin2))
                {
                    if(cadena.length>1)
                      {
                           acumuladorF+=ERRORES[0];
                           break;
                      }else if(cadena.length==1)
                       {
                           acumuladorV+=datoEsReservada(cadena[0]); 
                           acumuladorV+="\n"+CORRECTO[7];
                           break;
                       }
                }
            }

               if(acumuladorF.length()>1)
                {
                  //acumuladorV="";  
                  CadenaDevolucion[2]="true";
                  CadenaDevolucion[3]=acumuladorF;
                }
                
                if(acumuladorV.length()>1)
                {
                  CadenaDevolucion[0]="true";
                  CadenaDevolucion[1]=acumuladorV;
                }
                
            return CadenaDevolucion;
    }
    
    int i=0;
    
    public String loopConVariable(String cadena[])
    {
         String encontrado="";
         String retorn[]={"",""};
                 
         for(int bandera=0; bandera<comandosConVariable.length; bandera++)
         {
            if(cadena[0].equals(comandosConVariable[bandera]))
              {
                retorn=datoEsVariable(cadena[1]);
                 
                if(retorn[1].equals("true"))
                {
                     encontrado="1";
                     
                       boolean resp=variableExiste(cadena[1]);
                       if(resp==true)
                       {
                          encontrado="4";//todo correcto
                       }else
                       {
                          boolean respuesta=datoEsNumero(cadena[1]); 
                          if(respuesta==true)
                          {
                            encontrado="4";//todo correcto
                          }else
                          {
                              encontrado="2";//no encontrado        
                          }
                       }   
                }else
                {
                   encontrado="5";//esta malo
                }
                
                break;
              }else
              {
                  encontrado="3";//no encontrado
              }
         }
         return encontrado;
    }
    
    public String loopSinVariable(String cadena)
    {
         String encontrado="";
         String retorn[]={"",""};
       
         for(int bandera=0; bandera<lapizSinVariable.length; bandera++)
         {
             if(cadena.equals(lapizSinVariable[bandera]))
              {
                encontrado="1";  
                break;
              }else
              {
                 encontrado="3";//no encontrado
              }
         }
         return encontrado;
    }
    
    
    public String[] loopRepetir(String acumulador,String cadena[], int banderaInicio)
    {
 
        String CadenaDevolucion[]={" "," "," "," "};
        String CadenaRespuesta[]={" "," "};
        String acumuladorV="";
        String acumuladorF=""; 
        String finalTipoFinal="";
        String comodin="";
        boolean encontrado=false;
        int contadorToken1=0;
        int contadorToken2=0;

          for(i=banderaInicio; i<cadena.length; i++)
            {
                String evaluador=cadena[i];

                if(evaluador.equals("REPITE"))
                {
                     encontrado=true;
                     banderaInicio++;
                     for(int b=banderaInicio; b<cadena.length; b++)
                      {
                          finalTipoFinal+=cadena[b]+" ";
                      }
                     
                     for(int b=0; b<finalTipoFinal.length(); b++)
                      {
                          if(finalTipoFinal.charAt(b)=='[')
                          {
                             contadorToken1++; 
                             comodin+=" "+finalTipoFinal.charAt(b); 
                             b++;
                             if(finalTipoFinal.charAt(b)==' ')
                             {
                               comodin+=finalTipoFinal.charAt(b++);
                             }else
                             {
                               comodin+=" "+finalTipoFinal.charAt(b);
                             }
                             
                          }else if(finalTipoFinal.charAt(b)==']')
                          {
                              contadorToken2++; 
                              comodin+=" "+"]";
                          }else
                          {
                             comodin+=finalTipoFinal.charAt(b);
                          }
                      }
                     if(comodin.contains("  "))
                     {
                       comodin=comodin.replaceAll("  ", " ");
                     }
                }   
            }//fin for 

                             
            if(encontrado==true)
            {
                finalTipoFinal=comodin;
                
                boolean respuesta=false;
                
                String CadenaEstudiar[]=finalTipoFinal.split(" ");
                
                for(i=0; i<CadenaEstudiar.length; i++)
                  {
                      String evaluador=CadenaEstudiar[i];
                      CadenaRespuesta=datoEsVariable(evaluador);
                      
                      if(CadenaRespuesta[1].equals("true"))
                      {
                          i++;
                          evaluador=CadenaEstudiar[i];
                      }else
                      {
                          //i=CadenaEstudiar.length;
                          acumuladorF+="\n"+ERRORES[1]+" "+ERRORES[3]+" "+ERRORES[4];
                          break;
                      }
                         if(evaluador.equals("["))
                          {
                              i++;
                          }else
                           {
                             acumuladorF+="\n"+ERRORES[1]+" "+ERRORES[23];
                             i=CadenaEstudiar.length;
                             break;
                           } 
                              
                            for(int b=i; b<CadenaEstudiar.length; b++)
                            {
                                   evaluador=CadenaEstudiar[b];  
   
                                if(evaluador.equals("]"))
                                {
                                    b=CadenaEstudiar.length;
                                    i=b;
                                    break;
                                }else
                                {   
                                   
                                   String respu=loopSinVariable(evaluador);
                                   if(respu.equals("1"))
                                   {
                                      b++;
                                      evaluador=CadenaEstudiar[b];
                                      CadenaRespuesta=datoEsVariable(evaluador);
                                      
                                       if(CadenaRespuesta[1].equals("true"))
                                       {
                                         acumuladorF+="\n"+ERRORES[15]+" "+CORRECTO[4]+" "+CORRECTO[3];
                                         b=CadenaEstudiar.length;
                                         i=b+500;
                                         break;
                                         
                                        }else
                                          {
                                              b--;
                                          }
                             
                                   }else if(respu.equals("3"))
                                   {
                                      
                                      CadenaRespuesta[0]=evaluador;
                                      b++;
                                      evaluador=CadenaEstudiar[b];
                                      CadenaRespuesta[1]=evaluador;

                                      respu=loopConVariable(CadenaRespuesta);
                                          
                                          if(respu.equals("2"))
                                          {
                                              acumuladorF+="\n"+ERRORES[4];
                                              
                                          }else if(respu.equals("3")||respu.equals("5"))
                                          {
                                                 acumuladorF+="\n"+ERRORES[1]+" "+ERRORES[15];
                                                 b=CadenaEstudiar.length;
                                                 i=CadenaEstudiar.length;
                                                 acumuladorF+="\n"+ERRORES[1]+" "+ERRORES[24];
                                                 break; 
                                          }else if(respu.equals("4"))
                                          {

                                          }
                                   }
                                }
                            }
                  }
                
                            if(contadorToken1!=contadorToken2)
                             {
                                 acumuladorF+="\n"+ERRORES[20]; 
                             }
                             
                             if(contadorToken1==0)
                             {
                                 acumuladorF+="\n"+ERRORES[21]; 
                             }
                             
                             if(contadorToken2==0)
                             {
                                 acumuladorF+="\n"+ERRORES[21]; 
                             }

                if(acumuladorF.length()>1)
                {
                  CadenaDevolucion[2]="true";
                  CadenaDevolucion[3]=acumuladorF;
                }
                
                if(acumuladorV.length()>1)
                {
                  CadenaDevolucion[0]="true";
                  CadenaDevolucion[1]=acumuladorV;
                }              
            }
 
            return CadenaDevolucion;             

    }//FIN VOID
                              
    public boolean conocerResultadoEstructura()
    {
       return estructuraFinal;
    }
}//fin class
