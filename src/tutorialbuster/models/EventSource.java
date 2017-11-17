package tutorialbuster.models;

import org.powerbot.script.rt4.ClientContext;

public interface EventSource<T> {

    void process(ClientContext ctx);

    void dispatch(ClientContext ctx, T o);
}
