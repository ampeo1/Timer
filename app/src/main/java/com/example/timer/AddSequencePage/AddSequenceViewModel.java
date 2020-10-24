package com.example.timer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class AddSequenceViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Timer>> timers;
    private MutableLiveData<String> editName;
    private MutableLiveData<String> editColor;
    private Sequence sequence;

    public AddSequenceViewModel(@NonNull Application application){
        super(application);
    }

    public ArrayList<Timer> getTimers(int id){
        if(timers == null){
            Database db = new Database(getApplication());
            timers = new MutableLiveData<ArrayList<Timer>>();
            timers.setValue(db.getTimers(id));
        }
        return timers.getValue();
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    public String getEditName(){
        if(editName == null){
            editName = new MutableLiveData<String>();
            if(sequence != null){
                editName.setValue(sequence.getName());
            }
            else{
                editName.setValue("");
            }
        }
        return editName.getValue();
    }

    public void setEditName(String editName) {
        this.editName.setValue(editName);
    }

    public String getEditColor() {
        if(editColor == null){
            editColor = new MutableLiveData<String>();
            if(sequence != null){
                editColor.setValue(sequence.getColor());
            }
            else{
                editColor.setValue("");
            }
        }
        return editColor.getValue();
    }

    public void setEditColor(String editColor) {
        this.editColor.setValue(editColor);
    }
}
