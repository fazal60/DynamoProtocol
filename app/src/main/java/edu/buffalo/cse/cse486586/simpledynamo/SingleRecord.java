package edu.buffalo.cse.cse486586.simpledynamo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shahid on 4/23/15.
 */
public class SingleRecord implements Serializable {

    String origPort;
    String curs;
    String thisPort;
    String selection;
    ConcurrentHashMap<String,String> map;

    public ConcurrentHashMap<String, String> getMap() {
        return map;
    }

    public void setMap(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getOrigPort() {
        return origPort;
    }

    public void setOrigPort(String origPort) {
        this.origPort = origPort;
    }

    public String getCurs() {
        return curs;
    }

    public void setCurs(String curs) {
        this.curs = curs;
    }

    public String getThisPort() {
        return thisPort;
    }

    public void setThisPort(String thisPort) {
        this.thisPort = thisPort;
    }

}
