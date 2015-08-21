package edu.buffalo.cse.cse486586.simpledynamo;

import java.io.Serializable;

/**
 * Created by shahid on 4/18/15.
 */
public class SucPred implements Serializable{

    String succ;
    String sSucc;
    String pred;
    String sPred;

    public String getPred() {
        return pred;
    }

    public void setPred(String pred) {
        this.pred = pred;
    }

    public String getsPred() {
        return sPred;
    }

    public void setsPred(String sPred) {
        this.sPred = sPred;
    }

    public String getSucc() {
        return succ;
    }

    public void setSucc(String succ) {
        this.succ = succ;
    }

    public String getsSucc() {
        return sSucc;
    }

    public void setsSucc(String sSucc) {
        this.sSucc = sSucc;
    }
}