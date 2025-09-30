package br.univille;

import com.azure.core.amqp.AmqpTransportType;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

public class Producer {

    public static void main(String[] args) {
        
        var servidor = "sb-das1-2025.servicebus.windows.net";

        String chave = System.getenv("CHAVE");
        String queue = "queue-isabelepires";

        ServiceBusSenderClient senderClient = 

        new ServiceBusClientBuilder().fullyQualifiedNamespace(servidor).connectionString(chave).transportType(AmqpTransportType.AMQP_WEB_SOCKETS).sender().queueName(queue).buildClient();

        senderClient.sendMessage(new ServiceBusMessage("1: oiee"));
        senderClient.sendMessage(new ServiceBusMessage("2: olásss"));
        senderClient.sendMessage(new ServiceBusMessage("3: boa noite"));
    }

}
