package net.unnatural;

import net.unnatural.Events.AvailableUpdate;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public List<AvailableUpdate> updateev = new ArrayList<AvailableUpdate>();
    public static void main(String[] args) {

    }

    public void AddToUpdateEvent(AvailableUpdate AU) {
        updateev.add(AU);
    }

    public void UpdateIsReady(URL url, boolean emergency) {
        for (AvailableUpdate availableUpdate : this.updateev) {
            availableUpdate.AvailableUpdate(url, emergency);
        }
    }
}