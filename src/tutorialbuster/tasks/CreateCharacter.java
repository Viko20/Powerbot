package tutorialbuster.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import tutorialbuster.models.Task;


public class CreateCharacter extends Task {

    private static final int widget = 269;
    private static final int accept = 99;
    private static final int male = 136;
    private static final int female = 137;

    private static final int headL = 106;
    private static final int headR = 113;
    private static final int jawL = 107;
    private static final int jawR = 114;
    private static final int torsoL = 108;
    private static final int torsoR = 115;
    private static final int armsL = 109;
    private static final int armsR = 116;
    private static final int handsL = 110;
    private static final int handsR = 117;
    private static final int legsL = 111;
    private static final int legsR = 118;
    private static final int feetL = 112;
    private static final int feetR = 119;
    private static final int hairCL = 105;
    private static final int hairCR = 121;
    private static final int legCL = 122;
    private static final int legCR = 129;
    private static final int torsoCL = 123;
    private static final int torsoCR = 127;
    private static final int feetCL = 124;
    private static final int feetCR = 130;
    private static final int skinCL = 125;
    private static final int skinCR = 131;

    private final Random rand = new Random();

    public CreateCharacter(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public boolean activate() {

        return ctx.widgets.widget(widget).component(accept).visible();
    }

    @Override
    public void execute() {

        int gender = rand.nextInt(1, 3);
        switch (gender) {
            case 1:
                ctx.widgets.widget(widget).component(male).click();
                break;
            case 2:
                ctx.widgets.widget(widget).component(female).click();
                break;
        }

        for (Component val : Component.values()) {

            int direction = rand.nextInt(1, 3);
            designCharacter(val, direction);
        }
        ctx.widgets.widget(widget).component(accept).click();
    }

    private void designCharacter(Component val, int dir) {

        int clicks = rand.nextInt(0, 10);
        for (int i = 0; i <= clicks; i++) {
            switch (val) {
                case HEAD:
                    if (dir == 1) {
                        ctx.widgets.widget(widget).component(headL).click();
                    } else {
                        ctx.widgets.widget(widget).component(headR).click();
                    }
                    break;
                case JAW:
                    if (dir == 1) {
                        ctx.widgets.widget(widget).component(jawL).click();
                    } else {
                        ctx.widgets.widget(widget).component(jawR).click();
                    }
                    break;
                case TORSO:
                    if (dir == 1) {
                        ctx.widgets.widget(widget).component(torsoL).click();
                    } else {
                        ctx.widgets.widget(widget).component(torsoR).click();
                    }
                    break;
                case ARMS:
                    if (dir == 1) {
                        ctx.widgets.widget(widget).component(armsL).click();
                    } else {
                        ctx.widgets.widget(widget).component(armsR).click();
                    }
                    break;
                case HANDS:
                    if (dir == 1) {
                        ctx.widgets.widget(widget).component(handsL).click();
                    } else {
                        ctx.widgets.widget(widget).component(handsR).click();
                    }
                    break;
                case LEGS:
                    if (dir == 1) {
                        ctx.widgets.widget(widget).component(legsL).click();
                    } else {
                        ctx.widgets.widget(widget).component(legsR).click();
                    }
                    break;
                case FEET:
                    if (dir == 1) {
                        ctx.widgets.widget(widget).component(feetL).click();
                    } else {
                        ctx.widgets.widget(widget).component(feetR).click();
                    }
                    break;
                case HAIRC:
                    if (dir == 1) {
                        ctx.widgets.widget(widget).component(hairCL).click();
                    } else {
                        ctx.widgets.widget(widget).component(hairCR).click();
                    }
                    break;
                case LEGC:
                    if (dir == 1) {
                        ctx.widgets.widget(widget).component(legCL).click();
                    } else {
                        ctx.widgets.widget(widget).component(legCR).click();
                    }
                    break;
                case TORSOC:
                    if (dir == 1) {
                        ctx.widgets.widget(widget).component(torsoCL).click();
                    } else {
                        ctx.widgets.widget(widget).component(torsoCR).click();
                    }
                    break;
                case FEETC:
                    if (dir == 1) {
                        ctx.widgets.widget(widget).component(feetCL).click();
                    } else {
                        ctx.widgets.widget(widget).component(feetCR).click();
                    }
                    break;
                case SKINC:
                    if (dir == 1) {
                        ctx.widgets.widget(widget).component(skinCL).click();
                    } else {
                        ctx.widgets.widget(widget).component(skinCR).click();
                    }
                    break;
            }
        }
        Condition.sleep(rand.nextInt(200, 1000));
    }

    private enum Component {
        HEAD, JAW, TORSO, ARMS, HANDS, LEGS, FEET, HAIRC, LEGC, TORSOC, FEETC, SKINC
    }
}
