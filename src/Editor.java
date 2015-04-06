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
import Classes.NoLista;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Editor extends JFrame {

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
    private JLabel statusBar1, statusBar2;
    
    public static void main(String[] args) {
        // TODO code application logic here
        Editor aplicacao = new Editor();
    }
    
    private class MeuJPanel extends JPanel implements MouseMotionListener, MouseListener{
             
        JPanel pnlStatus = new JPanel();
        
        public MeuJPanel() {
            super();
            pnlStatus.setLayout(new GridLayout(1,2));	// painel com 2 colunas
            statusBar1 = new JLabel("Mensagem");
            statusBar2 = new JLabel("Coordenada");
            pnlStatus.add(statusBar1);					// label na coluna da esquerda
            pnlStatus.add(statusBar2);                                    // label na coluna da direita
            getContentPane().add(pnlStatus,BorderLayout.SOUTH);

        public void paintComponent(Graphics g) {
            NoLista atual = figuras.getPrimeiro();
            while (atual != null) {
                Ponto figuraAtual = (Ponto) atual.getInfo();
                figuraAtual.desenhar(figuraAtual.getCor(), g);
                atual = atual.getProx();
            }
        }    

        @Override
        public void mouseDragged(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        
    }
    
    public Editor() // construtor de Editor que criará o JFrame, colocará seu
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

            setSize(700, 500);	// dimensões do formulário em pixels
            setVisible(true);	// exibe o formulário

            /*
             * Para que todos os botões apareçam, o painel que os contém
             * (pnlBotoes) precisa ser colocado dentro do Frame. Para isso, é
             * usado um objeto da classe Container, que armazena os controles
             * visuais e conterá os objetos que serão exibidos no Frame. O
             * painel de botões será colocado na parte superior do formulá- rio,
             * portanto, usaremos no Container o Layout BorderLayout.
             */
            Container cntForm = getContentPane();
            cntForm.setLayout(new BorderLayout());
            cntForm.add(pnlBotoes, BorderLayout.NORTH);

            JDesktopPane panDesenho = new JDesktopPane();
            cntForm.add(panDesenho);

            frame = new JInternalFrame("Nenhum arquivo aberto", true, true, true, true);
            panDesenho.add(frame);

            frame.setSize(this.getWidth()/2, this.getHeight()/2);
            
            frame.show();
            frame.setOpaque(true);

            pnlDesenho = new MeuJPanel();
            Container cntFrame = frame.getContentPane();
            cntFrame.add(pnlDesenho);


        }

    private class FazAbertura implements ActionListener {
                
        public void desenharObjetos(Graphics g){
            pnlDesenho.paintComponent(g);
        }

        @Override
        public void actionPerformed(ActionEvent ex) // código executado no evento
        {
            Oval oval;
            Circulo circ;
            Linha linha;
            Ponto ponto;
            NoLista no;

            JFileChooser arqEscolhido = new JFileChooser();
            arqEscolhido.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int result = arqEscolhido.showOpenDialog(Editor.this);
            //… código de verificação se um arquivo foi selecionado e obtenção de seu nome
            if (result == JFileChooser.APPROVE_OPTION) {
                File arquivo = arqEscolhido.getSelectedFile();
                System.out.println("Processando " + arquivo.getName());

                try {
                    BufferedReader arqFiguras = new BufferedReader(
                            new FileReader(arquivo.getName()));

                    try {
                        String umaLinha = arqFiguras.readLine();
                        while (umaLinha != null) {
                            String tipo = umaLinha.substring(0, 5).trim();
                            int xBase = Integer.parseInt(umaLinha.substring(5, 10).trim());
                            int yBase = Integer.parseInt(umaLinha.substring(10, 15).trim());
                            int corR = Integer.parseInt(umaLinha.substring(15, 20).trim());
                            int corG = Integer.parseInt(umaLinha.substring(20, 25).trim());
                            int corB = Integer.parseInt(umaLinha.substring(25, 30).trim());
                            Color cor = new Color(corR, corG, corB);
                            switch (tipo.charAt(0)) {
                                case 'p': // figura é um ponto
                                    ponto = new Ponto(xBase, yBase, cor);
                                    no = new NoLista(ponto, null);
                                    figuras.insereAposFim(no);
                                    break;
                                case 'l': // figura é uma linha
                                    int xFinal = Integer.parseInt(umaLinha.substring(30, 3).trim());
                                    int yFinal = Integer.parseInt(umaLinha.substring(35, 40).trim());
                                    linha = new Linha(xBase, yBase, xFinal, yFinal, cor);
                                    no = new NoLista(linha, null);
                                    figuras.insereAposFim(no);
                                    break;
                                case 'c': // figura é um círculo
                                    int raio = Integer.parseInt(umaLinha.substring(30, 35).trim());
                                    circ = new Circulo(xBase, yBase, raio, cor);
                                    no = new NoLista(circ, null);
                                    figuras.insereAposFim(no);
                                    break;
                                case 'o': // figura é uma oval
                                    int raioA = Integer.parseInt(umaLinha.substring(30, 35).trim());
                                    int raioB = Integer.parseInt(umaLinha.substring(35, 40).trim());
                                    oval = new Oval(xBase, yBase, raioA, raioB, cor);
                                    no = new NoLista(oval, null);
                                    figuras.insereAposFim(no);
                                    break;
                            }//fim switch
                            umaLinha = arqFiguras.readLine();
                        }//fim while
                        arqFiguras.close();

                        frame.setTitle(arquivo.getName());
                        desenharObjetos(pnlDesenho.getGraphics());
                    }//fim try
                    catch (IOException ioe) {
                        System.out.println("Erro de leitura no arquivo");
                    }

                }//fim try
                catch (FileNotFoundException fnfe) {
                    System.out.println("Arquivo não pôde ser aberto");
                }


            }//if
        }//método  
        
        
    }
    }
