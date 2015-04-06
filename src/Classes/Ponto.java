package Classes;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author giovanna
 */

import java.awt.Color;
import java.awt.Graphics;
public class Ponto{
    
    private int x,  y;
    private Color cor;
    
    public Ponto(int cX, int cY, Color qualCor) {
	x = cX;
	y = cY;
	cor = qualCor;
    }
    
    public void setX(int novoX) throws Exception{
        if(novoX <= 0)
            throw new Exception("Valor inválido");
	  x = novoX;
    }
	  
    public void setY(int novoY) throws Exception {
        if(novoY <= 0)
            throw new Exception("Valor inválido");        
 	y = novoY;
    }
	  
    public void setCor(Color novaCor) throws Exception{
        if(! (novaCor instanceof Color))
            throw new Exception("Valor inválido");
  	cor = novaCor;
    }
	  
    public int getX() {
  	return x;
    }
	  
    public int getY() {
  	return y;
    }
	  
    public Color getCor(){
	return cor;
    }        
	
    public void desenhar(Color cor, Graphics g) {
	 g.setColor(cor);
	 g.drawLine(getX(),getY(),getX(),getY());
    }
    
    public String transformaString(int valor, int quantasPosicoes)	  { 
    String cadeia = new String(valor+"");				
    while (cadeia.length() < quantasPosicoes) 
        cadeia = "0"+cadeia; 
    return cadeia.substring(0,quantasPosicoes); // corta, se necessário, para
 // tamanho máximo 
  }
    
    public String transformaString(String valor, int quantasPosicoes)		
  { 
    String cadeia = new String(valor+"");				
    while (cadeia.length() < quantasPosicoes) 
       cadeia = cadeia+" "; 
    return cadeia.substring(0,quantasPosicoes); // corta, se necessário, para
 // tamanho máximo 
  }
    
    public String toString()
  {
  	return	transformaString("p",5)+
			transformaString(getX(),5)+
			transformaString(getY(),5)+
  			transformaString(getCor().getRed(),5)+
  			transformaString(getCor().getGreen(),5)+
  			transformaString(getCor().getBlue(),5);
  }
}

