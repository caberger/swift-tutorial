package at.ac.htl.model;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class InitialState extends Model {
    @Inject
    public InitialState() {
        this.greeting = "";
    }
}
