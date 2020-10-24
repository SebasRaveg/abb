/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario.modelo;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carloaiza
 */
public class ArbolBinario {

    public Nodo raiz;
    
    //public void adicionarNodo()
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public void isLleno() throws ArbolBinarioException {
        if (raiz == null) {
            throw new ArbolBinarioException("El árbol está vacío");
        }
    }

    public void adicionarNodo(int dato, Nodo ubicacion) throws ArbolBinarioException {
        if (raiz == null) {
            raiz = new Nodo(dato);

        } else {
            if (dato < ubicacion.getDato()) {
                if (ubicacion.getIzquierda() == null) {
                    ubicacion.setIzquierda(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getIzquierda());
                }
            } else if (dato > ubicacion.getDato()) {
                if (ubicacion.getDerecha() == null) {
                    ubicacion.setDerecha(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getDerecha());
                }
            } else {
                throw new ArbolBinarioException("No se puede repetir");
            }
        }
    }
    
    //preOrden
    public ArrayList preOrden() throws ArbolBinarioException {
        isLleno();
        ArrayList l = new ArrayList();
        if (raiz != null) {
            preOrden(raiz, l);
        }        
        return l;
    }

    private void preOrden(Nodo temp, ArrayList l)
    {
        //Condición que garantiza que el método finalice
        if(temp!=null)
        {
            l.add(temp.getDato());
            preOrden(temp.getIzquierda(), l);
            preOrden(temp.getDerecha(), l);
        }
    }
    
    //inOrden
    public ArrayList inOrden() throws ArbolBinarioException{
        isLleno();
        ArrayList l=new ArrayList();
        inOrden(raiz,l);
        return l;
    }
    
    private void inOrden(Nodo reco,ArrayList l) {
        if (reco != null) {
            inOrden(reco.getIzquierda(),l);
            l.add(reco.getDato() + " ");
            inOrden(reco.getDerecha(),l);
        }
    }
    
    //posOrden
    public ArrayList posOrden() throws ArbolBinarioException{
        isLleno();
        ArrayList l=new ArrayList();
        posOrden(raiz,l);
        return l;
    }
    
    private void posOrden(Nodo reco,ArrayList l) {
        if (reco != null) {
            posOrden(reco.getIzquierda(),l);
            posOrden(reco.getDerecha(),l);
            l.add(reco.getDato() + " ");
        }
    }
    
    // PorNiveles
    public ArrayList impNiveles() throws ArbolBinarioException{
        isLleno();
        ArrayList l=new ArrayList();
        impNiveles(raiz, 1,l);
        return l;
    }
    
    private void impNiveles(Nodo reco, int nivel, ArrayList l){
        if (reco != null) {
            impNiveles(reco.getIzquierda(), nivel + 1, l);
            l.add(reco.getDato() + " Nivel: (" + nivel + ") ");
            impNiveles(reco.getDerecha(), nivel + 1, l);
        }
    }
    
    //NivelesOrdenados
    String[] niveles;
    int altura;
    
    public int alturaArbol() {
        altura = 0;
        alturaArbol(raiz, 0);
        return altura;
    }

    private void alturaArbol(Nodo pivote, int nivel) {
        if (pivote != null) {
            alturaArbol(pivote.getIzquierda(), nivel + 1);
            if (nivel > altura) {
                altura = nivel;
            }
            alturaArbol(pivote.getDerecha(), nivel + 1);
        }
    }
    
    public ArrayList imprimirNivel() {
        niveles = new String[altura + 1];
        ArrayList l=new ArrayList();
        imprimirNivel(raiz, 0);
        for (int i = 0; i < niveles.length; i++) {
            l.add(niveles[i] + " ");
            //System.out.println(niveles[i] + " ");
        }
        return l;
    }
 
    public void imprimirNivel(Nodo pivote, int nivel2) {
        if (pivote != null) {
            niveles[nivel2] = pivote.getDato() + ", " + ((niveles[nivel2] != null) ? niveles[nivel2] : "");
            imprimirNivel(pivote.getDerecha(), nivel2 + 1);
            imprimirNivel(pivote.getIzquierda(), nivel2 + 1);
        }
    }
    
    //Buscar
    public Nodo buscarNodo(int buscarDato, Nodo ref) throws ArbolBinarioException {
        if (ref != null) 
        {
           if (ref.getDato() == buscarDato) 
         {
          return ref;
         }
        else 
           {
             if (buscarDato < ref.getDato()) 
             {
              return buscarNodo(buscarDato, ref.getIzquierda());
             }
             else 
             {
             return buscarNodo(buscarDato, ref.getDerecha());
             }
           }
        }
        else
        {
         throw new ArbolBinarioException("El dato buscado no existe");
        }
        
    }
    
    //Buscar Padre
    public int padre(int info) {
        if (info == 0 || this.raiz == null) {
            return 0;
        }
        Nodo x = padre(this.raiz, info);
        if (x == null) {
            return 0;
        }
        return (x.getDato());
    }

    private Nodo padre(Nodo x, int info) {
        if (x == null) {
            return null;
        }
        if ((x.getIzquierda() != null && x.getIzquierda().getDato()==(info)) || (x.getDerecha() != null && x.getDerecha().getDato()==(info))) {
            return (x);
        }
        Nodo y = padre(x.getIzquierda(), info);
        if (y == null) {
            return (padre(x.getDerecha(), info));
        } else {
            return (y);
        }
    }
    
    private Nodo buscarMin(Nodo r) {
        for (; r.getIzquierda() != null; r = r.getIzquierda());
        return (r);
    }
    
    
    // borrarNivel
    int nivel;
    
    public void borrarNivel(int x) throws ArbolBinarioException{
        isLleno();
        nivel=1;
        borrarNivel(raiz, nivel, x );    
    }    
    
    private void borrarNivel(Nodo reco, int nivel, int x ){
            if (reco != null) {
            borrarNivel(reco.getIzquierda(), nivel + 1,x);
            if (nivel == x) {
              borrarNodo(raiz, reco.getDato());
            }
            borrarNivel(reco.getDerecha(), nivel + 1,x);   
        }
        
    }    

    //Multiplicar
    public boolean Multiplicar(int x) {
            Multiplicar(raiz, 1, x);
            //System.out.println();
            return true;
    }

    private void Multiplicar(Nodo reco, int nivel, int x) {
        if (reco != null) {
            reco.setDato(reco.getDato() * x);
            Multiplicar(reco.getIzquierda(), nivel + 1, x);
            //System.out.print(reco.getDato() + " Nivel: (" + nivel + ") ,");
            Multiplicar(reco.getDerecha(), nivel + 1, x);
        }
    }
     
    //borrar
    public Nodo borrarNodo (Nodo r, int x){
        if (r == null) {
            return null;//<--Dato no encontrado		
        }
        int compara = ((Comparable) r.getDato()).compareTo(x);
        if (compara > 0) {
            r.setIzquierda(borrarNodo(r.getIzquierda(), x));
        } else if (compara < 0) {
            r.setDerecha(borrarNodo(r.getDerecha(), x));
        } else {
            if (r.getIzquierda() != null && r.getDerecha() != null){
                Nodo cambiar = buscarMin(r.getDerecha());
                int aux = cambiar.getDato();
                cambiar.setDato(r.getDato());
                r.setDato(aux);
                r.setDerecha(borrarNodo(r.getDerecha(), x));
            } else {
                r = (r.getIzquierda() != null) ? r.getIzquierda() : r.getDerecha();
            }
        }
        return r;
    }

    //Hojas
    public ArrayList getHojas() throws ArbolBinarioException {
        isLleno();
        ArrayList l = new ArrayList();
        getHojas(this.raiz, l);
        return (l);
    }
    
    private void getHojas(Nodo r, ArrayList l) {
        if (r != null) {
            if (this.esHoja(r)) {
                l.add(r.getDato());
            }
            getHojas(r.getIzquierda(), l);
            getHojas(r.getDerecha(), l);
        }

    }
       
    protected boolean esHoja(Nodo x) {
        return (x != null && x.getIzquierda()== null && x.getDerecha()== null);
    }
    
    //Podar
    public void podar() {
        podar(this.raiz);
    }

    private void podar(Nodo x) {
        if (x == null) {
            return;
        }
        if (this.esHoja(x.getIzquierda())) {
            x.setIzquierda(null);
        }
        if (this.esHoja(x.getDerecha())) {
            x.setDerecha(null);
        }
        podar(x.getIzquierda());
        podar(x.getDerecha());
    }
    
    //Balance
    int subizq = 0;
    int subder = 0;

    public String imprimirBalance() {
         subizq = 0;
         subder = 0;

        Balance(this.raiz, true, 0);
        //System.out.println("lado Izquierdo " + subizq + " Lado Derecho " + subder);
        if (subizq - subder == 0) {
            return ("El balance es: 0 ");
        } else if (subizq - subder == -1) {
            return("El balance es -1, derecha");
        } else if (subizq - subder == 1) {
            return("El balance 1, izquierda");
        } else {
            return("No es balanceado.."
                    + "porque es mas grande el lado "
                    + ((subizq > subder) ? "Izquierdo" : "Derecho"));
        }

    }

    public void Balance(Nodo reco, boolean lado, int i) {
        if (reco != null) {
            if (reco.getDerecha()== null && reco.getIzquierda()== null) {
                if (lado) {
                    subder = (i > subder) ? i : subder;
                } else {
                    subizq = (i > subizq) ? i : subizq;
                }
            }
            Balance(reco.getDerecha(), lado, i + 1);
            if (i == 0) {
                lado = false;
            }
            Balance(reco.getIzquierda(), lado, i + 1);
        }

    }
    
    //Obtener el numero de ramas   
    int numeroRamas = 0;

    public ArrayList<String>ObtenerRamamayor(){
    obtenernumeroRamas(this.raiz, 0);
    return ObtenerRamamayor(this.raiz, 0, "", new ArrayList<String>());
    }
  
    public void obtenernumeroRamas(Nodo pivote, int contador) {
        if (pivote != null) {
            contador++;
            obtenernumeroRamas(pivote.getIzquierda(), contador);
            obtenernumeroRamas(pivote.getDerecha(), contador);
        }
        if (contador > this.numeroRamas) {
            this.numeroRamas = contador;
        }
    }

    public ArrayList<String> ObtenerRamamayor(Nodo pivote, int contador, String dato, ArrayList lista){
        if (pivote != null ) {
            dato+=pivote.getDato()+",";
            contador ++;
            lista=ObtenerRamamayor(pivote.getIzquierda(), contador, dato, lista);
            lista=ObtenerRamamayor(pivote.getDerecha(), contador, dato, lista);
            
            if (contador == this.numeroRamas) {
                lista.add(dato);
            }
        }
        return lista;
    }
    
    //Cambiar Valor    
    public boolean cambiar() {
            cambiar(raiz, 1);
            //System.out.println();
            return true;
    }

    private void cambiar(Nodo reco, int nivel) {
        if (reco != null) {
            reco.setDato(reco.getDato() * 3);
            cambiar(reco.getIzquierda(), nivel + 1);
            //System.out.print(reco.getDato() + " Nivel: (" + nivel + ") ,");
            cambiar(reco.getDerecha(), nivel + 1);
        }
    }
    

    //Borrar menor
    public String borrarMenor() {
        Nodo reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getIzquierda()== null) {
                raiz = raiz.getDerecha();
            } else {
                Nodo anterior = raiz;
                reco = raiz.getIzquierda();
                while (reco.getIzquierda()!= null) {
                    anterior = reco;
                    reco = reco.getIzquierda();
                }
                
                anterior.setIzquierda(reco.getDerecha());
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }

    //Borrar Mayor
    public String borrarMayor() {
        Nodo reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getDerecha()== null) {
                raiz = raiz.getIzquierda();
            } else {
                Nodo anterior = raiz;
                reco = raiz.getDerecha();
                while (reco.getDerecha()!= null) {
                    anterior = reco;
                    reco = reco.getDerecha();
                }
                
                anterior.setDerecha(reco.getIzquierda());
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }
    
    public void llenarArbol(String datos) throws ArbolBinarioException
    {
        String[] arrayDatos= datos.split(",");
        for(String cadena: arrayDatos)
        {
            adicionarNodo(Integer.parseInt(cadena), raiz);
        }
        
    }
    
    //ContarNodos
    public int contarNodos()    
    {
        return this.contarNodos(raiz);        
    }    
    
    public int contarNodos(Nodo reco)
    {
        //Hacen el dllo del contar
        if(reco==null)
        {
            return 0;
        }
        if(reco.isHoja())
        {
            return 1;
        }
        else
        {   
            return 1 + contarNodos(reco.getIzquierda())+ contarNodos(reco.getDerecha());
        }
    }
    
    //SumarNodos
    public int sumarNodos(Nodo ref)
    {
        if(ref != null)
        {
            return ref.getDato() + sumarNodos(ref.getIzquierda()) 
                    + sumarNodos(ref.getDerecha());
        }
        else
        {
            return 0;
        }
    }
    
    public int sumarArbol()
    {
       return sumarNodos(raiz);
    }

   
}
