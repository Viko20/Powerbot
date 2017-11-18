package tutorialbuster.tasks;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import tutorialbuster.models.Task;
import tutorialbuster.models.Walker;

public class WalkToFishes extends Task {

    private static final Tile[] PATHTOFISH = {new Tile(3093, 3107, 0), new Tile(3097, 3107, 0), new Tile(3099, 3106, 0), new Tile(3101, 3102, 0), new Tile(3101, 3098, 0), new Tile(3103, 3094, 0)};
    private static final Area FISHAREA = new Area(new Tile(3100, 3093, 0), new Tile(3105, 3097, 0));

    public WalkToFishes(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public boolean activate() {
        return !FISHAREA.contains(ctx.players.local()) && ctx.varpbits.varpbit(281) <= 10 && ctx.varpbits.varpbit(281) < 30;
    }

    @Override
    public void execute() {

        Walker walk = new Walker(ctx);
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            walk.walkPath(PATHTOFISH);
        }
    }
}
