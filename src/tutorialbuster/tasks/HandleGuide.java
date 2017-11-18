package tutorialbuster.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ChatOption;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import tutorialbuster.models.Task;

public class HandleGuide extends Task {

    private int guideId = 3308;
    private int doorId = 9398;
    private int widgetSettings = 548;
    private int componentSettings = 34;
    private boolean settingsChecked = false;

    public HandleGuide(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public boolean activate() {
        return ctx.varpbits.varpbit(22) == 33554432 && !ctx.players.local().inMotion() && ctx.varpbits.varpbit(281) < 10;
    }

    @Override
    public void execute() {

        Npc guide = ctx.npcs.id(guideId).select().nearest().poll();
        if (ctx.chat.chatting()) {
            if (ctx.chat.canContinue()) {
                Condition.sleep();
                ctx.chat.clickContinue(true);
            } else {
                ChatOption experiencedPlayer = ctx.chat.select().text("I am an experienced player.").poll();
                if (experiencedPlayer != ctx.chat.nil()) {
                    ctx.input.click(experiencedPlayer.select());
                }
            }
        } else {
            if (ctx.widgets.widget(widgetSettings).component(componentSettings).visible() && !settingsChecked) {
                ctx.widgets.widget(widgetSettings).component(componentSettings).click();
                settingsChecked = true;
            }

            if (ctx.varpbits.varpbit(281) < 10) {
                if (guide.inViewport()) {
                    guide.interact("Talk");
                } else {
                    ctx.camera.turnTo(guide);
                    ctx.movement.step(guide);
                }
                Condition.wait(() -> !ctx.players.local().inMotion());
            }
        }
    }
}