package edu.buffalo.cse.cse486586.simpledynamo;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by shahid on 4/26/15.
 */
public class SuperSuccReplica implements Serializable {

    String origPort;
    String sSuccPort;
    String sucPort;
    String predPort;
    String sPredPort;
    HashMap<String,String> dataCat;


    public String getSucPort() {
        return sucPort;
    }

    public void setSucPort(String sucPort) {
        this.sucPort = sucPort;
    }

    public String getsSuccPort() {
        return sSuccPort;
    }

    public void setsSuccPort(String sSuccPort) {
        this.sSuccPort = sSuccPort;
    }

    public String getsPredPort() {
        return sPredPort;
    }

    public void setsPredPort(String sPredPort) {
        this.sPredPort = sPredPort;
    }

    public String getOrigPort() {
        return origPort;
    }

    public void setOrigPort(String origPort) {
        this.origPort = origPort;
    }

    public String getPredPort() {
        return predPort;
    }

    public void setPredPort(String predPort) {
        this.predPort = predPort;
    }

    public HashMap<String, String> getDataCat() {
        return dataCat;
    }

    public void setDataCat(HashMap<String, String> dataCat) {
        this.dataCat = dataCat;
    }
}
