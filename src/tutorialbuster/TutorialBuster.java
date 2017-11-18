package tutorialbuster;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Random;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import tutorialbuster.models.Task;
import tutorialbuster.tasks.CreateCharacter;
import tutorialbuster.tasks.HandleGuide;
import tutorialbuster.tasks.HandleSurvivalExpert;
import tutorialbuster.tasks.WalkToFishes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name = "TutorialBuster", description = "Runs tutorial Island", properties = "author=Viko20; topic=999; client=4;")
public class TutorialBuster extends PollingScript<ClientContext> implements PaintListener {

    private List<Task> tasks = new ArrayList<>();

    private static String getRunTime(long time) {

        int seconds = (int) (time / 1000) % 60;
        int minutes = (int) ((time / (1000 * 60)) % 60);
        int hours = (int) ((time / (1000 * 60 * 60)) % 24);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public void start() {

        System.out.println("The bot has started!");
        ctx.camera.pitch(new Random().nextInt(90, 180));

        if (tasks.isEmpty()) {
            tasks.add(new CreateCharacter(ctx));
            tasks.add(new HandleGuide(ctx));
            tasks.add(new WalkToFishes(ctx));
            tasks.add(new HandleSurvivalExpert(ctx));
            System.out.println("Tasks added!");
        }
    }

    @Override
    public void poll() {

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
    public void repaint(Graphics graphics) {

    }
}
