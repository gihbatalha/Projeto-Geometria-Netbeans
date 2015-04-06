/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author giovanna
 */
public class NoLista implements Comparable<Object> {
    private Object info;
    private NoLista prox;

    public NoLista(Object novaInfo,
                       NoLista proximo)
    {
        info = novaInfo;
        prox = proximo;
    }
    
    public NoLista getProx(){
        return this.prox;
    }
    
    public Object getInfo(){
        return this.info;
    }
    
    public void setProx(NoLista novoNo){
        this.prox = novoNo;
    }
    
    public void setInfo(Object novoInfo){
        this.info = novoInfo;
    }
    
    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
