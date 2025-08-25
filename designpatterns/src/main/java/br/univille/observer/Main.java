package br.univille.observer;

public class Main {
    public static void main(String[] args) {
        NewsPublisher publisher = new NewsPublisher();

        Observer emailCamila = new EmailSubscriber("camila@exemplo.com");
        Observer smsLucas   = new SmsSubscriber("+55 47 99999-0000");

        // Assina notificações
        publisher.attach(emailCamila);
        publisher.attach(smsLucas);

        // Publica eventos (todos observadores são notificados)
        publisher.publish("Edital de bolsas publicado!");
        publisher.publish("Calendário acadêmico atualizado.");

        // Descadastra um observador
        publisher.detach(smsLucas);

        // Apenas quem continua inscrito recebe esta
        publisher.publish("Resultado final divulgado.");
    }
}
