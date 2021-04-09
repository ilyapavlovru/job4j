package ru.job4j.tracker;

public class DeleteAction implements UserAction {

    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Delete item by id ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Enter id to delete item: ");
        if (tracker.delete(id)) {
            out.println("Successfully deleted item");
        } else {
            out.println("Not found id to delete item");
        }
        return true;
    }
}
