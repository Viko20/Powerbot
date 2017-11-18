package tutorialbuster.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Npc;
import tutorialbuster.models.Task;

public class HandleSurvivalExpert extends Task {

    private int guideId = 3306;
    private int widgetSettings = 548;
    private int componentSettings = 50;
    private int tinderBox = 590;
    private int axe = 1351;
    private int tree = 9730;

    public HandleSurvivalExpert(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public boolean activate() {
        return !ctx.players.local().inMotion() && ctx.varpbits.varpbit(281) >= 20;
    }

    @Override
    public void execute() {

        System.out.println("Executing survival expert");
        Npc guide = ctx.npcs.id(guideId).select().nearest().poll();
        if (ctx.chat.chatting()) {
            if (ctx.chat.canContinue()) {
                Condition.sleep();
                ctx.chat.clickContinue(true);
            }
        } else {
            if (ctx.varpbits.varpbit(1021) == 4) {
                ctx.widgets.widget(widgetSettings).component(componentSettings).click();
            }

            Item hatchet = ctx.inventory.id(axe).select().first().poll();
            if (hatchet != ctx.inventory.nil()) {
                System.out.println("Chopping Tree");
                chopTree();
            }

            Item tinder = ctx.inventory.id(tinderBox).select().first().poll();
            if (tinder != ctx.inventory.nil()) {
                lightWood();
            }

            if (guide.inViewport()) {
                guide.interact("Talk");
            } else {
                ctx.camera.turnTo(guide);
                ctx.movement.step(guide);
            }
            Condition.wait(() -> !ctx.players.local().inMotion());
        }
    }

    private void lightWood() {
    }

    private void chopTree() {

        GameObject treeToChop = ctx.objects.id(tree).select().nearest().poll();

        if (treeToChop.inViewport()) {
            treeToChop.interact("Chop");
        } else {
            ctx.camera.turnTo(treeToChop);
            ctx.movement.step(treeToChop);
        }
        Condition.wait(() -> !ctx.players.local().inMotion() && ctx.players.local().animation() != -1);
    }
}
