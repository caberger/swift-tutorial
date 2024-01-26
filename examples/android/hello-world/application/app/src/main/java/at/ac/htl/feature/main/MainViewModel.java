package at.ac.htl.feature.main;

import android.app.Activity;

import javax.inject.Inject;

import at.ac.htl.model.ObdModel;
import at.ac.htl.model.Store;
import dagger.hilt.android.scopes.ActivityScoped;
import io.reactivex.rxjava3.core.Observable;

@ActivityScoped
public class MainViewModel {
    Store store;
    @Inject
    MainViewModel (Store store) {
        this.store = store;
    }
    public void setRpm(double rpm) {
        store.produce(model -> model.obdModel.rpm = rpm);
    }
    public Observable<ObdModel> getObservable() {
        return store.subject.map(model -> model.obdModel);
    }
    public void routeTo(Class<? extends Activity> activity) {
        store.produce(model -> model.activity = activity);
    }
}
