package at.ac.htl.util;

import android.app.Activity;
import android.content.Intent;

import io.reactivex.rxjava3.subjects.Subject;

public class GenericRouter<T extends RoutingModel> {
    protected void init(Activity activity, Subject<T> subject) {
        subject
                .filter(model -> model.activity != null)
                .distinctUntilChanged((prev, next) -> prev.activity.equals(next.activity.getClass()))
                .subscribe(model -> {
                    var intent = new Intent(activity, model.activity);
                    activity.startActivity(intent);
                });
    }
}
