package at.ac.htl.model;

import android.app.Activity;

import javax.inject.Inject;

import at.ac.htl.util.GenericRouter;
import dagger.hilt.android.scopes.ActivityScoped;

@ActivityScoped
public class Router extends GenericRouter<Model> {
    @Inject
    public Router(Activity activity, Store store) {
        init(activity, store.subject);
    }
}
