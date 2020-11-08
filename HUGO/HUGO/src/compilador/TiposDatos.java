/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author Isaac Morera Vargas
 */
public class TiposDatos 
{
    private boolean paraFin;
        private int numero;
        private String token;
	private String tipo;
        private String comentarios;
        private String declaracion;
        private String variable;
        private String palabrasReservadas;
        private String comandos;
        private String lapiz;
        private String color;
                
	public void datosTipos(boolean paraFin, int numero,String token,String tipo, String comentarios,String declaracion,String variable,String palabrasReservadas, String comandos, String lapiz, String Color)
	{
                setparaYfin(paraFin);
		setNume(numero);
                setToken(token);
		setTipo(tipo);
                setComent(comentarios);
                setDecla(declaracion);
                setVariable(variable);
                setReservadas(palabrasReservadas);
                setComandos(comandos);
                setLapiz(lapiz);
                setColor(color);
	}
        
        public void setparaYfin(boolean paraFin)
	{
		this.paraFin=paraFin;
	}
        
	public void setNume(int numero)
	{
		this.numero=numero;
	}
        
        public void setToken(String token)
	{
		this.token=token;
	}
        
	public void setTipo(String tipo)
	{
		this.tipo=tipo;
	}

        public void setComent(String comentarios)
	{
		this.comentarios=comentarios;
	}
        
        public void setDecla(String declaracion)
	{
		this.declaracion=declaracion;
	}
        
        public void setVariable(String variable)
        
        {
		this.variable=variable;
	}
        
        public void setReservadas(String palabrasReservadas)
	{
		this.palabrasReservadas=palabrasReservadas;
	}
        
        public void setComandos(String comandos)
        {
		this.comandos=comandos;
	}
        
        public void setLapiz(String lapiz)
        {
		this.lapiz=lapiz;
	}        
        
        public void setColor(String color)
        {
		this.color=color;
	}  
                
        public boolean getparaYfin()
	{
		return paraFin;
	}
        
	public int getNume()
	{
		return numero;
	}
        
        public String getToken()
	{
		return token;
	}

	public String getTipo()
	{
		return tipo;
	}

        public String getComent()
	{
		return comentarios;
	}
        
        public String getDecla()
	{
		return declaracion;
	}
        
        public String getVariable()
        
        {
		return variable;
	}
        
        public String getReservadas()
	{
		return palabrasReservadas;
	}
        
        public String getComandos()
        {
		return comandos;
	}

        public String getLapiz()
        {
		return lapiz;
	}       
        
        public String getColor()
        {
		return color;
	}
        
}
