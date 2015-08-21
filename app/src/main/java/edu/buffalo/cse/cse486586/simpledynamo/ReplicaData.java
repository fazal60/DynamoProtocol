package edu.buffalo.cse.cse486586.simpledynamo;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by shahid on 4/25/15.
 */
public class ReplicaData implements Serializable {

    String origPort;
    HashMap<String,String> dataCat;

    public String getOrigPort() {
        return origPort;
    }

    public void setOrigPort(String origPort) {
        this.origPort = origPort;
    }

    public HashMap<String, String> getDataCat() {
        return dataCat;
    }

    public void setDataCat(HashMap<String, String> dataCat) {
        this.dataCat = dataCat;
    }

}
