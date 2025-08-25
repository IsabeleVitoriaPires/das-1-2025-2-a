package br.univille.observer;

public class SmsSubscriber implements Observer {
    private final String phone;

    public SmsSubscriber(String phone) {
        this.phone = phone;
    }

    @Override
    public void update(String message) {
        System.out.println("[SMS para " + phone + "] Nova notícia: " + message);
    }
}

