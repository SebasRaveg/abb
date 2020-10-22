/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl.modelo;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carloaiza
 */
public class ArbolBinarioAVL {

    private NodoAVL raiz;

    //public void adicionarNodo()
    public NodoAVL getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoAVL raiz) {
        this.raiz = raiz;
    }

    public void isLleno() throws ArbolBinarioException {
        if (raiz == null) {
            throw new ArbolBinarioException("El árbol está vacío");
        }
    }

    public void adicionarNodo(int dato, NodoAVL ubicacion) throws ArbolBinarioException {
        if (raiz == null) {
            raiz = new NodoAVL(dato);

        } else {

            if (dato < ubicacion.getDato()) {
                if (ubicacion.getIzquierda() == null) {
                    ubicacion.setIzquierda(new NodoAVL(dato));

                } else {
                    adicionarNodo(dato, ubicacion.getIzquierda());
                }
            } else if (dato > ubicacion.getDato()) {
                if (ubicacion.getDerecha() == null) {
                    ubicacion.setDerecha(new NodoAVL(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getDerecha());
                }
            } else {
                throw new ArbolBinarioException("No se puede repetir");
            }

            ubicacion.actualizarAltura();
            balancear(ubicacion);

        }

    }

    public ArrayList inOrden() throws ArbolBinarioException {
        isLleno();
        ArrayList l = new ArrayList();
        inOrden(raiz, l);
        return l;
    }

    private void inOrden(NodoAVL reco, ArrayList l) {
        if (reco != null) {
            inOrden(reco.getIzquierda(), l);
            l.add(reco.getDato() + " ");
            inOrden(reco.getDerecha(), l);
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

    private void preOrden(NodoAVL temp, ArrayList l)
    {
        //Condición que garantiza que el método finalice
        if(temp!=null)
        {
            l.add(temp.getDato());
            preOrden(temp.getIzquierda(), l);
            preOrden(temp.getDerecha(), l);
        }
    }
    
    //posOrden
    public ArrayList posOrden() throws ArbolBinarioException{
        isLleno();
        ArrayList l=new ArrayList();
        posOrden(raiz,l);
        return l;
    }
    
    private void posOrden(NodoAVL reco,ArrayList l) {
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
    
    private void impNiveles(NodoAVL reco, int nivel, ArrayList l){
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

    private void alturaArbol(NodoAVL pivote, int nivel) {
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
 
    public void imprimirNivel(NodoAVL pivote, int nivel2) {
        if (pivote != null) {
            niveles[nivel2] = pivote.getDato() + ", " + ((niveles[nivel2] != null) ? niveles[nivel2] : "");
            imprimirNivel(pivote.getDerecha(), nivel2 + 1);
            imprimirNivel(pivote.getIzquierda(), nivel2 + 1);
        }
    }
    
        //Hojas
    public ArrayList getHojas() throws ArbolBinarioException {
        isLleno();
        ArrayList l = new ArrayList();
        getHojas(this.raiz, l);
        return (l);
    }
    
    private void getHojas(NodoAVL r, ArrayList l) {
        if (r != null) {
            if (this.esHoja(r)) {
                l.add(r.getDato());
            }
            getHojas(r.getIzquierda(), l);
            getHojas(r.getDerecha(), l);
        }

    }
       
    protected boolean esHoja(NodoAVL x) {
        return (x != null && x.getIzquierda()== null && x.getDerecha()== null);
    }
    
    //Podar
    public void podar() {
        podar(this.raiz);
    }

    private void podar(NodoAVL x) {
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

    public void Balance(NodoAVL reco, boolean lado, int i) {
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
  
    public void obtenernumeroRamas(NodoAVL pivote, int contador) {
        if (pivote != null) {
            contador++;
            obtenernumeroRamas(pivote.getIzquierda(), contador);
            obtenernumeroRamas(pivote.getDerecha(), contador);
        }
        if (contador > this.numeroRamas) {
            this.numeroRamas = contador;
        }
    }

    public ArrayList<String> ObtenerRamamayor(NodoAVL pivote, int contador, String dato, ArrayList lista){
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

    private void cambiar(NodoAVL reco, int nivel) {
        if (reco != null) {
            reco.setDato(reco.getDato() * 3);
            cambiar(reco.getIzquierda(), nivel + 1);
            //System.out.print(reco.getDato() + " Nivel: (" + nivel + ") ,");
            cambiar(reco.getDerecha(), nivel + 1);
        }
    }
    

    //Borrar menor
    public String borrarMenor() {
        NodoAVL reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getIzquierda()== null) {
                raiz = raiz.getDerecha();
            } else {
                NodoAVL anterior = raiz;
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
        NodoAVL reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getDerecha()== null) {
                raiz = raiz.getIzquierda();
            } else {
                NodoAVL anterior = raiz;
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

    public void llenarArbol(String datos) throws ArbolBinarioException {
        String[] arrayDatos = datos.split(",");
        for (String cadena : arrayDatos) {
            adicionarNodo(Integer.parseInt(cadena), raiz);
        }

    }

    
//
//    public void rotarSimpleNuevo(NodoAVL princ, boolean sentido) {
//
//        if (!sentido) {
//             if (princ.getDerecha()!= null) {
//                NodoAVL nodo = princ.getDerecha();
//                princ.setDerecha(new NodoAVL(princ.getDato()));
//                princ.getDerecha().setDerecha(nodo);
//                 try {
//                     // adicionarNodo(nodo.getDato(), princ);
//                     adicionarNodo(nodo.getDato(), princ);
//                     //princ.getDerecha().setDerecha(nodo);
//                 } catch (ArbolBinarioException ex) {
//                     Logger.getLogger(ArbolBinarioAVL.class.getName()).log(Level.SEVERE, null, ex);
//                 }
//            } else {
//                princ.setDerecha(new NodoAVL(princ.getDato()));
//            }
//            princ.setDato(princ.getIzquierda().getDato());
//            if (princ.getIzquierda().getDerecha()!= null) {
//                NodoAVL nodo = princ.getIzquierda().getDerecha();
//                 princ.setIzquierda(princ.getIzquierda().getIzquierda());
//                 princ.getIzquierda().setDerecha(nodo);
//            } else {
//                princ.setIzquierda(princ.getIzquierda().getIzquierda());
//            }            
//            princ.getDerecha().actualizarAltura();
//        } else {
//            if (princ.getIzquierda() != null) {
//                NodoAVL nodo = princ.getIzquierda();
//                princ.setIzquierda(new NodoAVL(princ.getDato()));
//                princ.getIzquierda().setIzquierda(nodo);
//            } else {
//                princ.setIzquierda(new NodoAVL(princ.getDato()));
//            }
//            princ.setDato(princ.getDerecha().getDato());
//            if (princ.getDerecha().getIzquierda() != null) {
//                NodoAVL nodo = princ.getDerecha().getIzquierda();
//                princ.setDerecha(princ.getDerecha().getDerecha());
//                try {
//                    //princ.getDerecha().setIzquierda(nodo);
//                    adicionarNodo(nodo.getDato(), princ.getDerecha());
//                } catch (ArbolBinarioException ex) {
//                    Logger.getLogger(ArbolBinarioAVL.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//            } else {
//                princ.setDerecha(princ.getDerecha().getDerecha());
//            }            
//            princ.getIzquierda().actualizarAltura();
//        }
//        princ.actualizarAltura();
//    }
//    
//    
    
     public void rotarSimpleNuevo(NodoAVL princ, boolean sentido)  {

        if (!sentido) {
            if (princ.getDerecha() != null) {
                NodoAVL nodo = princ.getDerecha();
                princ.setDerecha(new NodoAVL(princ.getDato()));
                princ.getDerecha().setDerecha(nodo);
            } else {
                princ.setDerecha(new NodoAVL(princ.getDato()));
            }
            princ.setDato(princ.getIzquierda().getDato());
            if (princ.getIzquierda().getDerecha() != null) {
                NodoAVL nodo = princ.getIzquierda().getDerecha();
                princ.setIzquierda(princ.getIzquierda().getIzquierda());
                try {
                    adicionarNodo(nodo.getDato(), princ);
//                princ.getIzquierda().setDerecha(nodo);
                } catch (ArbolBinarioException ex) {
                    Logger.getLogger(ArbolBinarioAVL.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                princ.setIzquierda(princ.getIzquierda().getIzquierda());
            }
            princ.getDerecha().actualizarAltura();
        } else {
            if (princ.getIzquierda() != null) {
                NodoAVL nodo = princ.getIzquierda();
                princ.setIzquierda(new NodoAVL(princ.getDato()));
                princ.getIzquierda().setIzquierda(nodo);
            } else {
                princ.setIzquierda(new NodoAVL(princ.getDato()));
            }
            princ.setDato(princ.getDerecha().getDato());
            if (princ.getDerecha().getIzquierda() != null) {
                NodoAVL nodo = princ.getDerecha().getIzquierda();
                princ.setDerecha(princ.getDerecha().getDerecha());
                try {
                    adicionarNodo(nodo.getDato(), princ);
//                princ.getDerecha().setIzquierda(nodo);
                } catch (ArbolBinarioException ex) {
                    Logger.getLogger(ArbolBinarioAVL.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                princ.setDerecha(princ.getDerecha().getDerecha());
            }
            princ.getIzquierda().actualizarAltura();
        }
        princ.actualizarAltura();
    }

    public void balancear(NodoAVL principal) {
        if (!principal.esVacio()) {
            int fe = principal.obtenerFactorEquilibrio();
            boolean signo = true;
            if (fe < 0) {
                signo = false;
                fe = fe * -1;
            }
            if (fe >= 2) {
                //Esta desequilibrado
                //hacia donde
                if (signo) {
                    //Desequilibrio a la derecha
                    //Valido desequilibrio simple a la izq
                    if (principal.getDerecha().obtenerFactorEquilibrio() > 0) {
                        //Desequilibrio simple - Rotacion simple
                        rotarSimpleNuevo(principal, signo);

                    } else {
                        //Desequilibrio doble - Rotación doble
                        rotarSimpleNuevo(principal.getDerecha(), false);
                        rotarSimpleNuevo(principal, true);
                    }
                } else {
                    //Desequilibrio a la izquierda
                    //Valido desequilibrio simple a la izq
                    if (principal.getIzquierda().obtenerFactorEquilibrio() < 0) {
                        rotarSimpleNuevo(principal, signo);
                    } else {
                        //Tengo un zig zag
                        //rotar doble
                        rotarSimpleNuevo(principal.getIzquierda(), true);
                        rotarSimpleNuevo(principal, false);
                    }
                }
            }
        }

    }


}
