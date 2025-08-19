package br.univille.singleton;

public class Singleton {
    //como se fosse uma variavel global, é a mesma variavel em todas as classes
    private static Singleton instance; // ela existe antes do singleton, se eu criar 3 cópias desse singleton


    // contrutor privado é que ninguém pode chamar, contrutor é chamado quando da um new da classe, contrutores privados só podem chamados nela mesma
    private Singleton(){

    }
 
    // não tem como criar 2 singletons na memória
    public static Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
            
        }

        return instance;
            
    }

}
