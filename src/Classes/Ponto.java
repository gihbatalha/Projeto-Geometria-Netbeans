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
}

