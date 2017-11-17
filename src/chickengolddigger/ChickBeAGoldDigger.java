package chickengolddigger;

import chickengolddigger.listeners.InventoryEventSource;
import chickengolddigger.listeners.InventoryListener;
import chickengolddigger.models.EventSource;
import chickengolddigger.models.Task;
import chickengolddigger.tasks.Bank;
import chickengolddigger.tasks.PickupLoot;
import chickengolddigger.tasks.WalkToBank;
import chickengolddigger.tasks.WalkToChickens;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Random;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name = "ChickBeAGoldDigger", description = "Picks up chick loot", properties = "author=Viko20; topic=999; client=4;")

public class ChickBeAGoldDigger extends PollingScript<ClientContext> implements PaintListener {

    private final RenderingHints antialiasing = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private final Color color1 = new Color(0, 195, 135, 200);
    private final Color color2 = new Color(0, 0, 0);
    private final BasicStroke stroke1 = new BasicStroke(1);
    private final Font font1 = new Font("Arial", 1, 16);
    private final Font font2 = new Font("Arial", 1, 12);

    private List<Task> tasks = new ArrayList<>();

    private List<EventSource> eventSources = new ArrayList<EventSource>() {{
        add(new InventoryEventSource(ctx));
    }};

    private int totalValue = 0;

    public static String getRunTime(long time) {
        int seconds = (int) (time / 1000) % 60;
        int minutes = (int) ((time / (1000 * 60)) % 60);
        int hours = (int) ((time / (1000 * 60 * 60)) % 24);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public void start() {
        System.out.println("The bot has started!");
        ctx.camera.pitch(new Random().nextInt(90, 180));

        ctx.dispatcher.add((InventoryListener) inventoryEvent -> totalValue += inventoryEvent.getValueChange());

        if (tasks.isEmpty()) {
            tasks.add(new WalkToBank(ctx));
            tasks.add(new WalkToChickens(ctx));
            tasks.add(new Bank(ctx));
            tasks.add(new PickupLoot(ctx));

            System.out.println("Tasks added!");
        }
    }

    @Override
    public void poll() {

        for (EventSource r : eventSources)
            r.process(ctx);

        for (Task t : tasks) {
            if (t.activate()) {
                t.execute();
                break;
            }
        }
    }

    @Override
    public void stop() {
        System.out.println("The bot has stopped!");
    }

    @Override
    public void repaint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHints(antialiasing);
        g.setColor(color1);
        g.fillRoundRect(10, 343, 181, 81, 16, 16);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRoundRect(10, 343, 181, 81, 16, 16);
        g.setFont(font1);
        g.drawString("Chicken Gold Digger", 20, 364);
        g.setFont(font2);
        g.drawString("Gp / Hour: " + totalValue / (getTotalRuntime() / 1000) * 60 * 60, 21, 383);
        g.drawString("Runtime: " + getRunTime(getTotalRuntime()), 22, 416);
        g.drawString("Total Gp: " + totalValue, 21, 399);
    }
}
