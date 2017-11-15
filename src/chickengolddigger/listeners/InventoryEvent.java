package chickengolddigger.listeners;

import org.powerbot.script.rt4.GeItem;

import java.util.EventObject;

/**
 * Created with IntelliJ IDEA
 * User: Phantomist96
 * Date: 08/24/17
 */
public class InventoryEvent extends EventObject {

    private final int id;
    private final int diff;
    private final int slot;

    public InventoryEvent(int slot, int id, int diff) {
        super(id);
        this.id = id;
        this.diff = diff;
        this.slot = slot;
    }

    public int getValueChange() {
        GeItem newItem = new GeItem(id);
        if (diff > 0)
            return newItem.price * diff;
        else
            return newItem.price;
    }

}