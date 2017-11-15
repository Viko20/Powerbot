package chickengolddigger.listeners;

import org.powerbot.script.rt4.GeItem;
import org.powerbot.script.rt4.Item;

import java.util.EventObject;

/**
 * Created with IntelliJ IDEA
 * User: Phantomist96
 * Date: 08/24/17
 */
public class InventoryEvent extends EventObject {

    private final int inventorySlot;
    private final Item newItem;
    private int totalVal = 0;

    public InventoryEvent(int inventorySlot, Item newItem) {
        super(inventorySlot);
        this.inventorySlot = inventorySlot;
        this.newItem = newItem;
    }

    public int getValueChange() {
        GeItem newItem = new GeItem(this.newItem.id());
        totalVal += newItem.price;
        return totalVal;
    }

}