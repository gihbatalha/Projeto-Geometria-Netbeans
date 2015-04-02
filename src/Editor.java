/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author giovanna
 */
import Classes.*;
import Classes.ListaSimples;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
public class Editor extends JFrame{
    /**
     * @param args the command line arguments
     */
    
      
   private JButton btnPonto, 
                   btnLinha, 
                   btnCirculo,
                   btnElipse,
                   btnCor, 
                   btnAbrir,
                   btnSalvar,
                   btnApagar,
                   btnSair;
   
   private JPanel pnlBotoes;
   
   private Icon imgAbrir;
   
   static private JInternalFrame frame;
   
    
   static private MeuJPanel pnlDesenho;
   private static ListaSimples figuras = new ListaSimples();
              
    public static void main(String[] args) {
        // TODO code application logic here
        Editor aplicacao = new Editor();
    }
    
    
    private class FazAbertura implements ActionListener {
 
    public void actionPerformed(ActionEvent e) // código executado no evento
    {
        Ponto ponto;
        NoLista no;
    
            JFileChooser arqEscolhido = new JFileChooser ();
            arqEscolhido.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int result = arqEscolhido.showOpenDialog(Editor.this);
    //… código de verificação se um arquivo foi selecionado e obtenção de seu nome
            if (result == JFileChooser.APPROVE_OPTION) 
            {
            File arquivo = arqEscolhido.getSelectedFile();
            System.out.println("Processando "+arquivo.getName());

                try {
                    BufferedReader arqFiguras = new BufferedReader(
                    new FileReader(arquivo.getName()));
                    
                    try {
                        String linha = arqFiguras.readLine();
                        while (linha != null)
                        {
                            String tipo = linha.substring(0,5).trim();
                            int xBase = Integer.parseInt(linha.substring(5,10).trim());
                            int yBase = Integer.parseInt(linha.substring(10,15).trim());
                            int corR  = Integer.parseInt(linha.substring(15,20).trim());
                            int corG  = Integer.parseInt(linha.substring(20,25).trim());
                            int corB  = Integer.parseInt(linha.substring(25,30).trim());
                            Color cor = new Color(corR, corG, corB);
                                switch (tipo.charAt(0))
                                {
                                case 'p' : // figura é um ponto
                                    ponto = new Ponto(xBase,yBase, cor);
                                    no = new NoLista(ponto, null);
                                    figuras.insereAposFim(ponto, no);
                                break;
                                case 'l' : // figura é uma linha
                                int xFinal =Integer.parseInt(linha.substring(30,3new NoLista(ponto, null),5).trim());
                                int yFinal =Integer.parseInt(linha.substring(35,40).trim());
                                    figuras.insereAposFim(new NoLista(new Linha(xBase, yBase, xFinal, yFinal, cor), null));
                                break;
                                case 'c' : // figura é um círculo
                                int raio =Integer.parseInt(linha.substring(30,35).trim());
                                    figuras.insereAposFim(new NoLista(new Circulo(xBase, yBase, raio, cor), null));
                                break;
                                case 'o' : // figura é uma oval
                                int raioA =Integer.parseInt(linha.substring(30,35).trim());
                                int raioB =Integer.parseInt(linha.substring(35,40).trim());
                                    figuras.insereAposFim(new NoLista(new Oval(xBase, yBase, raioA, raioB, cor), null));
                                break;
                                }//fim switch
                            linha = arqFiguras.readLine();
                        }//fim while
                    arqFiguras.close();

                    frame.setTitle(arquivo.getName());
                    desenharObjetos(pnlDesenho.getGraphics());
                    }//fim try
                    
                    catch (IOException ioe){
                            System.out.println("Erro de leitura no arquivo");
                    }
                    
                }//fim try
                
                catch(FileNotFoundException ex){
                    System.out.println("Arquivo não pôde ser aberto");
                }
            
            
    }//if
}//método
    
    private class MeuJPanel extends JPanel 
{
	public void paintComponent(Graphics g)
	{
		NoLista atual = figuras.getPrimeiro();
		while (atual != null)
		{
			Ponto figuraAtual = (Ponto) atual.getInfo();
			figuraAtual.desenhar(figuraAtual.getCor(), g);
			atual = atual.getProx();
		}
	}
}
    
  public Editor()	// construtor de Editor que criará o JFrame, colocará seu
  {			// título, estabelecerá um tamanho para o formulário e o
        // exibirá
	super("Editor Gráfico");	// cria o JFrame e coloca um título
        
        // cria os botões do editor       
       btnAbrir = new JButton("Abrir");
       btnSalvar = new JButton("Salvar");
       btnPonto = new JButton("Ponto");
       btnLinha = new JButton("Linha");
       btnCirculo = new JButton("Circulo");
       btnElipse = new JButton("Elipse");
       btnCor = new JButton("Cores");
       btnApagar = new JButton("Apagar");
       btnSair = new JButton("Sair");
                
       //cria o JPanel onde ficará os botões
       pnlBotoes = new JPanel();
       
       //cria o layout usado para dispor fisicamente os botões
      GridLayout flwBotoes = new GridLayout();
       
       //informa que os componentes do pnlBotoes estarão dispostos de forma livre
       pnlBotoes.setLayout(flwBotoes);
       
       //adiciona os controles visuais(botões) ao painel de botões, 
       // de cima para baixo, da esquerda para direita.
       pnlBotoes.add(btnAbrir);
       pnlBotoes.add(btnSalvar);
       pnlBotoes.add(btnPonto);
       pnlBotoes.add(btnLinha);
       pnlBotoes.add(btnCirculo);
       pnlBotoes.add(btnElipse);
       pnlBotoes.add(btnCor);
       pnlBotoes.add(btnApagar);
       pnlBotoes.add(btnSair);
       
       btnAbrir.addActionListener(new FazAbertura());
       
	setSize(700,500);	// dimensões do formulário em pixels
	setVisible(true);	// exibe o formulário
        
        /* Para que todos os botões apareçam, o painel que os contém (pnlBotoes)
         * precisa ser colocado dentro do Frame. Para isso, é usado um objeto da classe 
         * Container, que armazena os controles visuais e conterá os objetos que serão 
         * exibidos no Frame. O painel de botões será colocado na parte superior do formulá-
         * rio, portanto, usaremos no Container o Layout BorderLayout.         
         */
        Container cntForm = getContentPane();
        cntForm.setLayout(new BorderLayout());
        cntForm.add(pnlBotoes, BorderLayout.NORTH);
        
        JDesktopPane panDesenho = new JDesktopPane();
	cntForm.add(panDesenho);
        
        frame = new JInternalFrame("Nenhum arquivo aberto", true, true, true, true);       
        panDesenho.add(frame);
        
        frame.setSize(this.getWidth()/2 , this.getHeight()/2);
        frame.show();
        frame.setOpaque(true);
        
        pnlDesenho = new MeuJPanel();
	Container cntFrame = frame.getContentPane();
	cntFrame.add(pnlDesenho);
        
        
  }    
}
