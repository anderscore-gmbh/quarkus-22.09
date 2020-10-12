package net.gfu.quarkus.hello;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EchoService {
    public String echo(String spell) {
        return spell;
    }
}
