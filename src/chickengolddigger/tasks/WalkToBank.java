package chickengolddigger.tasks;

import chickengolddigger.models.Task;
import chickengolddigger.models.Walker;
import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class WalkToBank extends Task {

    final static Tile[] pathToBank = {new Tile(3228, 3297, 0), new Tile(3232, 3297, 0), new Tile(3236, 3294, 0),
            new Tile(3238, 3290, 0), new Tile(3238, 3286, 0), new Tile(3238, 3282, 0), new Tile(3238, 3278, 0),
            new Tile(3240, 3274, 0), new Tile(3240, 3270, 0), new Tile(3240, 3266, 0), new Tile(3241, 3262, 0),
            new Tile(3237, 3262, 0), new Tile(3233, 3262, 0), new Tile(3229, 3262, 0), new Tile(3225, 3262, 0),
            new Tile(3221, 3262, 0), new Tile(3218, 3259, 0), new Tile(3217, 3255, 0), new Tile(3217, 3251, 0),
            new Tile(3217, 3247, 0), new Tile(3219, 3243, 0), new Tile(3222, 3239, 0), new Tile(3225, 3236, 0),
            new Tile(3229, 3233, 0), new Tile(3230, 3229, 0), new Tile(3231, 3225, 0), new Tile(3232, 3221, 0),
            new Tile(3228, 3219, 0), new Tile(3224, 3219, 0), new Tile(3220, 3219, 0), new Tile(3216, 3219, 0),
            new Tile(3215, 3215, 0), new Tile(3215, 3211, 0), new Tile(3211, 3211, 0), new Tile(3207, 3210, 0),
            new Tile(3205, 3209, 1), new Tile(3205, 3209, 2), new Tile(3205, 3213, 2), new Tile(3206, 3217, 2),
            new Tile(3209, 3220, 2)};
    final static Area BANK_AREA = new Area(new Tile(3210, 3220, 2), new Tile(3207, 3217, 2));
    final Walker walk = new Walker(ctx);


    public WalkToBank(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public boolean activate() {
        System.out.println("checking WalkToBank");
        return ctx.inventory.select().count() == 28 && (!BANK_AREA.contains(ctx.players.local()) || !ctx.bank.inViewport());
    }

    @Override
    public void execute() {

        System.out.println("Executing WalkToBank");
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            walk.walkPath(pathToBank);
        }
    }
}
