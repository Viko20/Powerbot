package chickengolddigger.listeners;

import chickengolddigger.models.EventSource;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import java.util.EventListener;

/**
 * Created with IntelliJ IDEA
 * User: Phantomist96
 * Date: 08/24/17
 */

public class InventoryEventSource implements EventSource<InventoryEvent> {

    private final ClientContext ctx;

    private final int inventorySlots;
    private final int[] itemIDCache;
    private final int[] itemCountCache;

    public InventoryEventSource(ClientContext ctx) {
        this.ctx = ctx;
        this.inventorySlots = 28;
        this.itemIDCache = new int[28];
        this.itemCountCache = new int[28];

        for (int i = 0; i < 28; i++) {
            Item item = ctx.inventory.itemAt(i);
            if (item != null && item.valid()) {
                itemIDCache[i] = item.id();
                itemCountCache[i] = item.stackSize();
            } else {
                itemIDCache[i] = -1;
                itemCountCache[i] = 0;
            }
        }

    }

    private Item getInventoryItemNil() {
        return ((org.powerbot.script.rt4.ClientContext) ctx).inventory.nil();
    }

    private Item getInventoryItem(int inventoryIndex) {
        return ((org.powerbot.script.rt4.ClientContext) ctx).inventory.itemAt(inventoryIndex);
    }

    @Override
    public void process(org.powerbot.script.rt4.ClientContext ctx) {

        for (int i = 0; i < 28; i++) {
            Item item = ctx.inventory.itemAt(i);
            if (item == null || !item.valid()) {
                itemCountCache[i] = 0;
                itemIDCache[i] = -1;
            } else {
                int countBefore = itemCountCache[i];
                int countAfter = item.stackSize();
                if (countBefore != countAfter) {
                    int id = item.id();
                    dispatch(ctx, new InventoryEvent(i, id, countAfter - countBefore));
                    itemCountCache[i] = countAfter;
                    itemIDCache[i] = id;
                }
            }
        }
    }

    @Override
    public void dispatch(org.powerbot.script.rt4.ClientContext ctx, InventoryEvent o) {
        for (EventListener l : ctx.dispatcher) {
            if (l instanceof InventoryListener)
                ((InventoryListener) l).onInventoryChange(o);
        }
    }
}