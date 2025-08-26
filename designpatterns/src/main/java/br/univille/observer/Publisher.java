package br.univille.observer;

import java.util.ArrayList;

public class Publisher {

    //tem que saber subscriver e notifysubscribers
    // java não tem ponteiro só tem referencia
    // private Subscribers[] subscribers = new Subscribers[10]; é uma lista fixa
    private ArrayList<Subscriber> subscribers = new ArrayList<>(); //é uma lista que cresce ao infinito - lista dinâmica

    private String mainState;

    public String getMainState() {
        return mainState;
    }
    
    public void setMainState(String mainState) {
        this.mainState = mainState;
    }

    //adiciona assinante na lista de assinantes
    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    //passa pelos assinantes e notifica a mensagem nova
    public void notifySubscribers(){
        for(Subscriber umAssinante : subscribers){
            umAssinante.update(mainState);
        }
    }

}
