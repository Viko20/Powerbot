package chickengolddigger.tasks;

import chickengolddigger.models.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.TileMatrix;

import java.util.concurrent.Callable;

public class PickupLoot extends Task {

    final static int LOOT_IDS[] = {2138, 526, 314, 1944}; // chicken, bones, feather, egg
    private static final Tile chickLocation = new Tile(3228, 3297, 0);
    //CHICKENREGION

    public PickupLoot(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public boolean activate() {
        System.out.println("checking PickupLoot");
        return ctx.inventory.select().count() < 28;
    }

    @Override
    public void execute() {

        System.out.println("executing PickupLoot");
        GroundItem item = ctx.groundItems.select().id(LOOT_IDS).nearest().first().poll();

        if (!new TileMatrix(ctx, item.tile()).reachable())
            return;
        if (item.inViewport()) {
            item.interact(true, "Take", item.name());
        } else {
            ctx.movement.step(item);
            ctx.camera.turnTo(item);
        }

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !ctx.players.local().inMotion();
            }
        });

    }
}
