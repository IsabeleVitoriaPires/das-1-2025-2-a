package br.univille.singleton;

import br.univille.Main;

public class Cliente {
    public static void main(String[] args) {
        var segredo = "é os guri";

        var singelton = Singleton.getInstance();
        singelton.setSegredo(segredo);

        // depois de mt código

        System.out.println(Singleton.getInstance().getSegredo());
    }

}
