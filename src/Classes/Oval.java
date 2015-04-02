/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author giovanna
 */
public class Oval extends Ponto {
    
    private int raioA, 
	        raioB;
	
    @Override
	public void desenhar(Color corDesenho, Graphics g) {
		g.setColor(corDesenho);
		g.drawOval(getX()-raioA, getY()-raioB,  // centro - raio
				   2*raioA,2*raioB);  // centro + raio
			
	}

	/* public Oval() throws Exception{
		//super(0,0,0,0,);
               try{
		setRaioA(0);
		setRaioB(0);
		setCor(Color.black);
               }
               
               catch(Exception e){
               throw new Exception("Dados inválidos");
               }
	}*/
        
        
	
	public void setRaioA(int novoRaio) throws Exception{
                if(novoRaio <= 0)
            throw new Exception("Valor inválido");
                
		raioA = novoRaio;
	}

	public void setRaioB(int novoRaio) throws Exception{
                if(novoRaio <= 0)
            throw new Exception("Valor inválido");
		raioB = novoRaio;
	}
	
	public Oval(int xCentro, int yCentro, int novoRaioA, 
			     int novoRaioB, Color novaCor)
	{
		super(xCentro, yCentro, novaCor);  // construtor de Ponto(x,y)
                
              try{
		setRaioA(novoRaioA);
		setRaioB(novoRaioB);	
              }
              
              catch(Exception e){
                  System.out.println("Dados inválidos"); }
	}    
}
