package br.univille.observer;

public class Client {

    public static void main(String[] args) {
        var grupoFamilia = new Publisher();

        //esse já esta pronto, diferente da interface, que precisa de uma classe concreta
        var eu = new ConcreteSubscriber();
        var mae = new ConcreteSubscriber();
        var pai = new ConcreteSubscriber();
        var irma = new ConcreteSubscriber();

        grupoFamilia.subscribe(eu);
        grupoFamilia.subscribe(mae);
        grupoFamilia.subscribe(pai);
        grupoFamilia.subscribe(irma);

        grupoFamilia.setMainState("Hoje é meu aniversário!");
        grupoFamilia.notifySubscribers();


    }

}
