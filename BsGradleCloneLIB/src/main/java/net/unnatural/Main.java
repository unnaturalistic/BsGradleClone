package net.unnatural;

import net.unnatural.Events.AvailableUpdate;
import net.unnatural.Events.MainCall;
import net.unnatural.Events.MsgEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public List<AvailableUpdate> updateev = new ArrayList<AvailableUpdate>();
    public List<MainCall> MainC = new ArrayList<MainCall>();
    public List<MsgEvent> MsgE = new ArrayList<MsgEvent>();
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
    public void MainCallAdd(MainCall Mainc) {
        MainC.add(Mainc);
    }
    public void MainCallEvent(String[] args) {
        for (MainCall mainCall : this.MainC) {
            mainCall.MainCall(args);
        }
    }
    public void AddMsgEvent(MsgEvent ME) {
        MsgE.add(ME);
    }
    public void MsgEvent(String msg) {
       for (MsgEvent MsgEvent : this.MsgE) {
           MsgEvent.MsgEvent(msg);
       }
    }
}