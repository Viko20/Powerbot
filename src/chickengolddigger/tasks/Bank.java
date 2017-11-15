package chickengolddigger.tasks;

import chickengolddigger.models.Task;
import org.powerbot.script.rt4.ClientContext;

public class Bank extends Task {

    final static int BANK_ID = 5270;

    public Bank(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public boolean activate() {
        System.out.println("checking Bank");
        return ctx.inventory.select().count() == 28 && ctx.bank.inViewport();
    }

    @Override
    public void execute() {
        System.out.println("Executing Bank");

        ctx.bank.open();
        if (ctx.bank.opened()) {
            ctx.bank.depositInventory();
            ctx.bank.close();
        }
    }
}
