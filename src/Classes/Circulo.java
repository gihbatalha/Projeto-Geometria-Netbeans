/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author giovanna
 */
import java.awt.Color;
import java.awt.Graphics;

public class Circulo extends Ponto{
    
	private int raio;
	
	public void setRaio(int novoRaio) throws Exception {
            if(novoRaio <= 0)
            throw new Exception("Valor inválido");
            
		raio = novoRaio;
	}
        
	public Circulo(int xCentro, int yCentro, int novoRaio, Color novaCor)
	{
		super(xCentro, yCentro, novaCor);  // construtor de Ponto(x,y)
              try{
		setRaio(novoRaio);
              }
              
              catch(Exception e){
                System.out.println("Dados inválidos");
              }
	}
	
    @Override
	public void desenhar(Color corDesenho, Graphics g) {
		g.setColor(corDesenho);
		g.drawOval(getX()-raio, getY()-raio,  	// centro - raio
				   2*raio,2*raio);  	// centro + raio
	}    
    
}
