package com.manenkov;

import org.junit.jupiter.api.Test;

public class ActorsTest {
    @Test
    void testActors() {
        final Actor<Server> server = new Actor<>(new Server());
        final Actor<Client> client = new Actor<>(new Client(server));
        client.tell(Client::request);
    }

    private class Client {

        final Actor<Server> srv;

        public Client(Actor<Server> srv) {
            this.srv = srv;
        }

        void request() {
            srv.ask(p -> p.pong("Hello"), this::response);
        }

        void response(final String response) {
            System.out.println(response);
        }
    }
    private class Server {
        String pong(final String payload) {
            return "pong [" + payload + "]";
        }
    }
}
