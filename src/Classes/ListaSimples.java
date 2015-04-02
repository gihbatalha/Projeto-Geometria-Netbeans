package Classes;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author giovanna
 */
public class ListaSimples {

    public static class NoLista extends Classes.NoLista {

        public NoLista(Linha linha,
                       NoLista prox)
        {
            super(linha, prox);
        }
        

    }
    
    private NoLista primeiro;
    private NoLista ultimo;
    private NoLista anterior;
    private NoLista atual;
    private int quantosNos;

        private boolean primeiroAcessoDoPercurso;
        
        public ListaSimples()
        {
            primeiro = null;
            ultimo = null;
            anterior = null;
            atual = null;
            quantosNos = 0;
            primeiroAcessoDoPercurso = false;
        }
        
        

        public boolean estaVazia()
        {
          return (getPrimeiro() == null);
        }

        public void iniciarPercursoSequencial()
        {
            setAtual(getPrimeiro());
            primeiroAcessoDoPercurso = true;
        }

        public void insereAntesDoInicio(NoLista novoNo)
        {
            if (estaVazia())
                setUltimo(novoNo);

            novoNo.setProx(getPrimeiro());
            setPrimeiro(novoNo);
            setQuantosNos(getQuantosNos() + 1);
        }

        public void insereAposFim(NoLista novoNo)
        { 
          if (estaVazia() )
             setPrimeiro(novoNo);
          else
            getUltimo().setProx(novoNo);

          novoNo.setProx(null);
          setUltimo(novoNo);
          setQuantosNos(getQuantosNos() + 1);
        }

        public void insereAposNo(NoLista qualNo, NoLista novoNo) throws Exception
        {
          if ( (qualNo == null) || estaVazia() )
             throw new Exception("Local inválido para inserção");

          novoNo.setProx(qualNo.getProx());
          qualNo.setProx(novoNo);
          setQuantosNos(getQuantosNos() + 1);

          if (qualNo == getUltimo() )
             setUltimo(novoNo);
        }

        public void insereEmOrdem(NoLista novoNo)
        {
          if (estaVazia() )
             insereAntesDoInicio(novoNo);
          else
            if (getAnterior() == getUltimo())
               insereAposFim(novoNo);
            else
              if (getAnterior() == null )
                 insereAntesDoInicio(novoNo);
              else
              {
                novoNo.setProx(getAtual());
                getAnterior().setProx(novoNo);
                setQuantosNos(getQuantosNos() + 1);
              }
        }

        public boolean podePercorrer()
        {
          if (getAtual() == null)
             return false;
          else
            if (primeiroAcessoDoPercurso)
            {
              primeiroAcessoDoPercurso = false;
              return true;
            }
            else
              {
                setAtual(atual);
                return (getAtual() != null);
              }
        }

        public void removerNo(NoLista qualNo, NoLista noAnterior)
        {
          if (! estaVazia() )
          {
            if (qualNo == getPrimeiro() )
            {
                setPrimeiro(primeiro);
              if (getPrimeiro() == null)  // só havia um único nó
                 setUltimo(null);
            }
            else
              if (qualNo == getUltimo())
              {
                noAnterior.setProx(null);
                setUltimo(noAnterior);
              }
              else
              {
                noAnterior.setProx(qualNo.getProx());
                qualNo.setProx(null);
              }

            qualNo = null;
            setQuantosNos(getQuantosNos() - 1);
          }
        }

        public void removerNo()
        {
          removerNo(getAtual(), getAnterior());
        }

        public int QtdNos()
        {
            int contador = 0;

            if (estaVazia())
                return 0;

            else
            {
                iniciarPercursoSequencial();
                while (podePercorrer())
                    contador++;
            }

            return contador;
        }

    /**
     * @return 
     */
    public NoLista getPrimeiro() {
        return primeiro;
    }

    /**
     * @param primeiro 
     */
    public void setPrimeiro(NoLista primeiro) {
        this.primeiro = primeiro;
    }

    /**
     * @return the ultimo
     */
    public NoLista getUltimo() {
        return ultimo;
    }

    /**
     * @param ultimo the ultimo to set
     */
    public void setUltimo(NoLista ultimo) {
        this.ultimo = ultimo;
    }

    /**
     * @return the anterior
     */
    public NoLista getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(NoLista anterior) {
        this.anterior = anterior;
    }

    /**
     * @return 
     */
    public NoLista getAtual() {
        return atual;
    }

    /**
     * @param atual 
     */
    public void setAtual(NoLista atual) {
        this.atual = atual;
    }

    /**
     * @return the quantosNos
     */
    public int getQuantosNos() {
        return quantosNos;
    }

    /**
     * @param quantosNos the quantosNos to set
     */
    public void setQuantosNos(int quantosNos) {
        this.quantosNos = quantosNos;
    }

    }
