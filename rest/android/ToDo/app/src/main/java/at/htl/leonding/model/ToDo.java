package at.htl.leonding.model;

/** A ToDo as we get it from <a href="https://jsonplaceholder.typicode.com/todos">jsonplaceholder.typicode.com</a>
*/
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
