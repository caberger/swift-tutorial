package at.htl.leonding.model;

public record ToDo(
        int userId,
        int id,
        String title,
        boolean completed
) {
    public ToDo() {
        this(0, 0, null, false);
    }
}
