package br.univille.observer;

import java.util.ArrayList;
import java.util.List;

public class NewsPublisher implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private String lastHeadline;

    @Override
    public void attach(Observer o) {
        if (o == null) return;
        // Evita duplicatas simples
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        // Itera sobre uma cópia para evitar ConcurrentModificationException
        for (Observer o : new ArrayList<>(observers)) {
            o.update(message);
        }
    }

    // "Evento" de domínio: publicar uma notícia
    public void publish(String headline) {
        this.lastHeadline = headline;
        notifyObservers(headline);
    }

    public String getLastHeadline() {
        return lastHeadline;
    }
}

