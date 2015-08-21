package edu.buffalo.cse.cse486586.simpledynamo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;

public class SimpleDynamoProvider extends ContentProvider {

    static final String TAG = SimpleDynamoProvider.class.getSimpleName();
    static final String REMOTE_PORT0 = "11108";
    static final String REMOTE_PORT1 = "11112";
    static final String REMOTE_PORT2 = "11116";
    static final String REMOTE_PORT3 = "11120";
    static final String REMOTE_PORT4 = "11124";
    static final int SERVER_PORT = 10000;
    static ServerSocket serverSocket;
    static final String[] portList={REMOTE_PORT0,REMOTE_PORT1,REMOTE_PORT2,REMOTE_PORT3,REMOTE_PORT4};
    static MatrixCursor cursor=new MatrixCursor(new String[]{"key","value"});
    String portArr[]=new String[5];
    static String suc="",sSuc="",pred="",sPred="";
    static String mPort;
    TreeMap<String,String> nBorMap=new TreeMap<>();
    static HashMap<String,SucPred> portMap=new HashMap<>();
    static HashMap<String,Integer> msgMap=new HashMap<>();
    static int starDelCount=0;
    static int singleDelCount=0;
    static Boolean var=true;
    static HashMap<String,Boolean> flagMap=new HashMap<>();

    DBHelper mydb;


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub

        HashMap<String,String> delMap=mydb.getAllCotacts();

        if (selection.equalsIgnoreCase("\"@\""))
        {
            int count=0;
//                String fileDir=getContext().getApplicationContext().getFilesDir().toString();
            if (delMap.size()> 0) {
                // loops through the array of files, outputing the name to console
                MatrixCursor curs = new MatrixCursor(new String[]{"key", "value"});
                for (Map.Entry<String,String> ent:delMap.entrySet()) {
                    String fileOutput = ent.getKey();

                    if(mydb.deleteContact(fileOutput)>0)
                        count++;
                }

//                Log.d("cursor size:",""+curs.getCount());
                return count;
            }

            Log.v("directory in *", ""+getContext().getApplicationContext().getFilesDir());
        }
        else if(selection.equalsIgnoreCase("\"*\""))
        {
            if (delMap.size()>0) {
                // loops through the array of files, outputing the name to console
                MatrixCursor curs = new MatrixCursor(new String[]{"key", "value"});
                for (Map.Entry<String,String> ent:delMap.entrySet()) {
                    String fileOutput = ent.getKey();

                    if(mydb.deleteContact(fileOutput)>0)
                        starDelCount++;
                }
            }

            try {
                return initDel(starDelCount,mPort,portMap.get(mPort).getSucc(),"me");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        else
        {
            delMap=mydb.getAllCotacts();
            int count=1;
            if(mPort.equalsIgnoreCase("11108"))
            {
                if (delMap.size()>0) {
                    for (Map.Entry<String,String> ent:delMap.entrySet()) {
                        String fileOutput = ent.getKey();
                        if(mydb.deleteContact(fileOutput)>0)
                            count++;

                    }
                }
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSuccPort("11108"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSuperSuccPort("11108"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getPredPort("11108"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSupPredPort("11108"));
            }
            if(mPort.equalsIgnoreCase("11116"))
            {
                if (delMap.size()>0) {
                    for (Map.Entry<String,String> ent:delMap.entrySet()) {
                        String fileOutput = ent.getKey();
                        if(mydb.deleteContact(fileOutput)>0)
                            count++;

                    }
                }
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSuccPort("11116"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSuperSuccPort("11116"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getPredPort("11116"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSupPredPort("11116"));
            }
            if(mPort.equalsIgnoreCase("11120"))
            {
                if (delMap.size()>0) {
                    for (Map.Entry<String,String> ent:delMap.entrySet()) {
                        String fileOutput = ent.getKey();
                        if(mydb.deleteContact(fileOutput)>0)
                            count++;

                    }
                }
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSuccPort("11120"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSuperSuccPort("11120"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getPredPort("11120"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSupPredPort("11120"));

            }
            if(mPort.equalsIgnoreCase("11124"))
            {
                if (delMap.size()>0) {
                    for (Map.Entry<String,String> ent:delMap.entrySet()) {
                        String fileOutput = ent.getKey();
                        if(mydb.deleteContact(fileOutput)>0)
                            count++;

                    }
                }
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSuccPort("11124"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSuperSuccPort("11124"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getPredPort("11124"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSupPredPort("11124"));

            }
            if(mPort.equalsIgnoreCase("11112"))
            {
                if (delMap.size()>0) {
                    for (Map.Entry<String,String> ent:delMap.entrySet()) {
                        String fileOutput = ent.getKey();
                        if(mydb.deleteContact(fileOutput)>0)
                            count++;

                    }
                }
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSuccPort("11112"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSuperSuccPort("11112"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getPredPort("11112"));
                new sendDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, selection, getSupPredPort("11112"));
            }

            return 1;
//            if (delMap.size()> 0)
//            {
//                // loops through the array of files, outputing the name to console
//                MatrixCursor curs = new MatrixCursor(new String[]{"key", "value"});
////                if(delMap.size()>0)
//                {
//                    for (Map.Entry<String, String> ent : delMap.entrySet()) {
//                        String fileOutput = ent.getKey();
//
//                        if (mydb.deleteContact(fileOutput) > 0)
//                            count++;
//                    }
//                }
//
////                Log.d("cursor size:",""+curs.getCount());
//                return count;
//            }

//            Log.d("new msg","New Single Message for delete:"+selection);
//            cursor=new MatrixCursor(new String[]{"key", "value"});
//
//            int flg=0;
//            //Working Code for single Query
//
//            //**************************************STARTS HERE**************************************************
//            Log.d("new Single","New Single message for delete");
//            flg=0;
//            String fName="";
//            String keyValPair="";
//
//            if (delMap.size()> 0) {
//                for (Map.Entry<String,String> ent:delMap.entrySet()) {
//                    String fileOutput = ent.getKey();
//                    if(selection.equalsIgnoreCase(fileOutput))
//                    {
//                        flg=1;
//                        fName=selection;
//                        break;
//                    }
//
//                }
//            }
//
//
//            Log.d("flag val","flag is:"+flg);
//            if(flg==1) {
//                Log.v("selection is:", "" + selection);
////                if(mydb.deleteContact(fName)>0)
////                    singleDelCount++;
//                for (Map.Entry<String, String> ent : delMap.entrySet()) {
//                        String fileOutput = ent.getKey();
//
//                        if (mydb.deleteContact(fileOutput) > 0)
//                            singleDelCount++;
//                    }
//            }
//
//
//            try {
//                return initDelSingle(singleDelCount,mPort,getSuccPort(mPort),selection);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            //*************************************ENDS HERE*******************************************************

        }


        return 0;

    }

    private class sendDel extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... msgs) {
            try {
                String key=msgs[0];
                String port=msgs[1];
                Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                        Integer.parseInt(port));
                AllDelete tObj = new AllDelete();
                tObj.setSel(key);
                tObj.setPort(port);


                ObjectOutputStream spObject = new ObjectOutputStream(socket.getOutputStream());
                spObject.writeObject(tObj);


                spObject.close();
                socket.close();
            } catch (IOException e) {
                Log.d("failed","Failed while Sending for Delete:"+msgs[1]);
            }
            return null;
        }

    }

    public int initDelSingle(int curs,String origPort,String sucPort,String selection) throws InterruptedException {
        Log.d("key value pair:","key value pair being passed:"+curs+",succ port:"+sucPort);
        if(curs>0 && mPort.equalsIgnoreCase(origPort))
        {
            return curs;
        }
        else
        {
            Log.d("else?","coming in else");
            new ClientTaskDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,""+curs, origPort,sucPort,selection);
        }

        Thread.sleep(1200);
        return singleDelCount;
    }

    private class ClientTaskDel extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... msgs) {
            try {

                Log.d("coming in CTDel1","coming in client task");
                int curs=Integer.parseInt(msgs[0]);
                String origPort=msgs[1];
                String sucPort=msgs[2];
                String selection=msgs[3];

                Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                        Integer.parseInt(sucPort));

                SingleDelete cObj=new SingleDelete();
                cObj.setOrigPort(origPort);
                cObj.setCurs(curs);
                cObj.setThisPort(sucPort);
                cObj.setStat(selection);
                ObjectOutputStream msgObject = new ObjectOutputStream(socket.getOutputStream());
                msgObject.writeObject(cObj);
                msgObject.close();
                socket.close();

                /*
                 * TODO: Fill in your client code that sends out a message.
                 */
//                socket.close();

            } catch (UnknownHostException e) {
                Log.e(TAG, "ClientTask UnknownHostException");
            } catch (IOException e) {
                Log.e(TAG, "ClientTask socket IOException as:"+getSuccPort(mPort)+" has failed");
                new ClientTaskDel().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, "" + msgs[0], msgs[1], getSuccPort(msgs[2]), msgs[3]);
            }

            return null;
        }
    }


    public int initDel(int cnt,String origPort,String sucPort,String stat) throws InterruptedException {
        Log.d("stat:","stat:"+stat+",mPort:"+mPort+",origPort:"+origPort);
        Log.d("in init","coming in init funct after * with mPort:"+mPort+" and origPort:"+origPort+" cursor size:"+cursor.getCount());
        if(sucPort.equalsIgnoreCase("null") || sucPort.equalsIgnoreCase("0"))
        {
            return cnt;
        }
        else if(mPort.equalsIgnoreCase(origPort)) {

            Log.d("mp==op","in init mPort=OrigPort"+" and count:"+cnt+" and stat:"+stat);
            if(stat.equalsIgnoreCase("me"))
            {
                Log.d("forward","forward from init to succ port:"+sucPort);

                delFiles(cnt,origPort,sucPort,stat);

            }
            else if(stat.equalsIgnoreCase("notme"))
            {
                return cnt;
            }
        }
        else
        {
            Log.d("forward","forward from init to succ port:"+sucPort);
            delFiles(cnt,origPort,sucPort,stat);
        }
        Thread.sleep(1200);
        return cnt;
    }

    public void delFiles(int curs,String origPort,String sucPort,String stat)
    {
        try {
            Log.d("fwd","from init to be forwarded to:"+sucPort);
            Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                    Integer.parseInt(sucPort));

            GlobalDelete cObj=new GlobalDelete();
            cObj.setOrigPort(origPort);
            cObj.setCurs(curs);
            cObj.setThisPort(sucPort);
            cObj.setStat(stat);
            ObjectOutputStream msgObject = new ObjectOutputStream(socket.getOutputStream());

            msgObject.writeObject(cObj);
            msgObject.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public synchronized Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub

        String key=values.getAsString("key");
        String value=values.getAsString("value");
        Log.d("in insert","in insert with key:"+key+" and value:"+value);

        try {
            for(int i=0;i<portArr.length;i++)
            {
                if(i<portArr.length-1) {

                    if(i==0)
                    {
                        if(genHash(key).compareTo(genHash("" + Integer.parseInt(portArr[i]) / 2)) < 0)
                        {
                            Log.d("Lesser", "message " + key + " lesser than the lowest itself:" + portArr[i]);
//                            sendInsert(key,value,portArr[i],0);
                            new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, value, portArr[0], "0");
                            new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, value, getSuccPort(portArr[0]), "1");
                            new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, value, getSuperSuccPort(portArr[0]), "2");
                        }
                        else if (genHash(key).compareTo(genHash("" + Integer.parseInt(portArr[i]) / 2)) > 0 && genHash(key).compareTo(genHash("" + Integer.parseInt(portArr[i+1]) / 2)) <=0) {

                            Log.d("Greater", "message " + key + " greater than lowest but less than successor:" + portArr[i] + "," + portArr[i + 1]);
//                            sendInsert(key, value, portArr[i + 1], 0);
                            new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, value, portArr[i+1], "0");
                            new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, value, getSuccPort(portArr[i+1]), "1");
                            new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, value, getSuperSuccPort(portArr[i+1]), "2");
                        }
                    }
                    else if (genHash(key).compareTo(genHash("" + Integer.parseInt(portArr[i]) / 2)) > 0 && genHash(key).compareTo(genHash("" + Integer.parseInt(portArr[i+1]) / 2)) <=0) {

                        Log.d("Greater", "message " + key + " greater than me but less than successor:" + portArr[i] + "," + portArr[i + 1]);
//                        sendInsert(key, value, portArr[i + 1], 0);
                        new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, value, portArr[i+1], "0");
                        new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, value, getSuccPort(portArr[i + 1]), "1");
                        new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, value, getSuperSuccPort(portArr[i+1]), "2");
                    }

                }
                else if(i==portArr.length-1)
                {
                    if (genHash(key).compareTo(genHash("" + Integer.parseInt(portArr[i]) / 2)) > 0) {

                        Log.d("Greater", "message " + key + " greater than highest port:" + portArr[i]);
//                        sendInsert(key,value,portArr[0],0);
                        new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, value, portArr[0], "0");
                        new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, value, getSuccPort(portArr[0]), "1");
                        new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, value, getSuperSuccPort(portArr[0]), "2");
                    }

                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSuccPort(String prt)
    {

        if(prt.equalsIgnoreCase("11108"))
            return "11116";
        if(prt.equalsIgnoreCase("11116"))
            return "11120";
        if(prt.equalsIgnoreCase("11120"))
            return "11124";
        if(prt.equalsIgnoreCase("11124"))
            return "11112";
        if(prt.equalsIgnoreCase("11112"))
            return "11108";

        return null;
    }

    public String getPredPort(String prt)
    {

        if(prt.equalsIgnoreCase("11108"))
            return "11112";
        if(prt.equalsIgnoreCase("11116"))
            return "11108";
        if(prt.equalsIgnoreCase("11120"))
            return "11116";
        if(prt.equalsIgnoreCase("11124"))
            return "11120";
        if(prt.equalsIgnoreCase("11112"))
            return "11124";

        return null;
    }

    public String getSupPredPort(String prt)
    {

        if(prt.equalsIgnoreCase("11108"))
            return "11124";
        if(prt.equalsIgnoreCase("11116"))
            return "11112";
        if(prt.equalsIgnoreCase("11120"))
            return "11108";
        if(prt.equalsIgnoreCase("11124"))
            return "11116";
        if(prt.equalsIgnoreCase("11112"))
            return "11120";

        return null;
    }


    public String getSuperSuccPort(String prt) {

        if(prt.equalsIgnoreCase("11108"))
            return "11120";
        if(prt.equalsIgnoreCase("11116"))
            return "11124";
        if(prt.equalsIgnoreCase("11120"))
            return "11112";
        if(prt.equalsIgnoreCase("11124"))
            return "11108";
        if(prt.equalsIgnoreCase("11112"))
            return "11116";

        return null;
    }

    private class sendIns extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... msgs) {
            try {
                String key=msgs[0];
                String val=msgs[1];
                String port=msgs[2];
                int cnt=Integer.parseInt(msgs[3]);
                Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                        Integer.parseInt(port));
                Log.d("to fwd", "in sendInsert to fwd:"+key+" with val:"+val+" to:" + port + " and cnt is:" + cnt);

                Mesg2Insert tObj = new Mesg2Insert();
                tObj.setKey(key);
                tObj.setMsg(val);
                tObj.setPort(port);
                tObj.setCnt(cnt);


                ObjectOutputStream spObject = new ObjectOutputStream(socket.getOutputStream());
                spObject.writeObject(tObj);


                spObject.close();
                socket.close();
            } catch (IOException e) {
//            e.printStackTrace();
                String key=msgs[0];
                String val=msgs[1];
                String port=msgs[2];
                int cnt=Integer.parseInt(msgs[3]);
                Log.d("failed", "port:" + port + " has failed and cannot take:" + key + " and cnt is:" + cnt);
                for(int i=0;i<portArr.length;i++)
                {
                    if(port.equalsIgnoreCase(portArr[i]))
                    {
                        if(i==portArr.length-1)
                        {
                            if(cnt<2)
//                                sendInsert(key,val,portArr[0],cnt+1);
                                new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, val, portArr[0], "" + (cnt + 1));
                            else
                                Log.d("cnt", "cnt>=2 so won't forward to its succ");
                        }
                        else
                        {
                            if(cnt<2)
//                                sendInsert(key,val,portArr[i+1],cnt+1);
                                new sendIns().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, key, val, portArr[i + 1], "" + (cnt + 1));
                            else
                                Log.d("cnt","cnt>=2 so won't forward to its succ");

                        }
                    }
                }
            }
            return null;
        }

    }


    public void sendInsert(String key,String val,String port,int cnt)
    {
        try {
            Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                    Integer.parseInt(port));
            Log.d("to fwd","in sendInsert to fwd to:"+port+" and cnt is:"+cnt);
            Mesg2Insert tObj = new Mesg2Insert();
            tObj.setKey(key);
            tObj.setMsg(val);
            tObj.setPort(port);
            tObj.setCnt(cnt);


            ObjectOutputStream spObject = new ObjectOutputStream(socket.getOutputStream());
            spObject.writeObject(tObj);


            spObject.close();
            socket.close();

        } catch (IOException e) {
//            e.printStackTrace();
            Log.d("failed", "port:" + port + " has failed and cannot take:" + key+" and cnt is:"+cnt);
            for(int i=0;i<portArr.length;i++)
            {
                if(port.equalsIgnoreCase(portArr[i]))
                {
                    if(i==portArr.length-1)
                    {
                        if(cnt<2)
                            sendInsert(key,val,portArr[0],cnt+1);
                        else
                            Log.d("cnt","cnt>=2 so won't forward to its succ");
                    }
                    else
                    {
                        if(cnt<2)
                            sendInsert(key,val,portArr[i+1],cnt+1);
                        else
                            Log.d("cnt","cnt>=2 so won't forward to its succ");

                    }
                }
            }
        }
    }

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        mydb = new DBHelper(getContext());
        TelephonyManager tel = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String portStr = tel.getLine1Number().substring(tel.getLine1Number().length() - 4);
        final String myPort = String.valueOf((Integer.parseInt(portStr) * 2));
        mPort = myPort;
        try {
            /*
             * Create a server socket as well as a thread (AsyncTask) that listens on the server
             * port.
             *
             * AsyncTask is a simplified thread construct that Android provides. Please make sure
             * you know how it works by reading
             * http://developer.android.com/reference/android/os/AsyncTask.html

             */
            for(int i=0;i<portList.length;i++)
            {
                nBorMap.put(genHash(""+Integer.parseInt(portList[i])/2),portList[i]);

            }

            serverSocket = new ServerSocket(SERVER_PORT);
            new ServerTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, serverSocket);
        } catch (IOException e) {
            /*
             * Log is a good way to debug your code. LogCat prints out all the messages that
             * Log class writes.
             *
             * Please read http://developer.android.com/tools/debugging/debugging-projects.html
             * and http://developer.android.com/tools/debugging/debugging-log.html
             * for more information on debugging.
             */
            Log.e(TAG, "Can't create a ServerSocket");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        mydb.getWritableDatabase().execSQL("drop table if exists dynamo");
        Log.d("rows", "number of rows in port at Start OnCreate:" + mPort + " is:" + mydb.numberOfRows());
//        mydb.onUpgrade(mydb.getWritableDatabase(),0,1);
        String msg = myPort;
        new ClientTask().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, msg, myPort);

        return false;
    }

    private class ServerTask extends AsyncTask<ServerSocket, String, Void> {

        @Override
        protected Void doInBackground(ServerSocket... sockets) {
            ServerSocket serverSocket = sockets[0];
            String message="";


            /*
             * TODO: Fill in your server code that receives messages and passes them
             * to onProgressUpdate().
             */
            Uri mUri;
            mUri=buildUri("content", "edu.buffalo.cse.cse486586.simpledynamo.provider");

            //String key="key";
            Boolean cond=true;
            try {
                while(cond) {
                    String uri="edu.buffalo.cse.cse486586.simpledynamo.provider";
                    Socket socket = serverSocket.accept();
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    Object recdObj = ois.readObject();
                    ContentValues val=new ContentValues();


                    Log.d("coming in ST?","is it coming in ST at all?");

                    if(recdObj instanceof Message)
                    {

                        Message mObj=(Message)recdObj;
                        Log.d("map Last","map last is:"+nBorMap.get(nBorMap.lastKey())+" map:"+nBorMap.size());
                        int i=0;
                        HashMap<String,String> dataCat=new HashMap<>();
                        dataCat=mydb.getAllCotacts();
                        for(Map.Entry<String,String> eachRec:nBorMap.entrySet())
                        {
                            portArr[i]=eachRec.getValue();
                            i++;
                        }
                        i=0;
                        for(i=0;i<portArr.length;i++)
                        {
//                            Log.d("coming in for",portArr[i]+","+mObj.getMsg());
                            if(portArr[i].equalsIgnoreCase(mObj.getMsg()))
                            {
                                if(i==portArr.length-1)
                                {
                                    suc=portArr[0];
                                    sSuc=portArr[1];
                                    pred=portArr[i-1];
                                    sPred=portArr[i-2];
                                }
                                else if(i==portArr.length-2)
                                {
                                    suc=portArr[i+1];
                                    sSuc=portArr[0];
                                    pred=portArr[i-1];
                                    sPred=portArr[i-2];
                                }
                                else
                                {
                                    if(i==0)
                                    {
                                        pred=portArr[portArr.length-1];
                                        sPred=portArr[portArr.length-2];
                                        suc=portArr[i+1];
                                        sSuc=portArr[i+2];
                                    }
                                    else if(i==1)
                                    {
                                        suc=portArr[i+1];
                                        sSuc=portArr[i+2];
                                        pred=portArr[i-1];
                                        sPred=portArr[portArr.length-1];
                                    }
                                    else {
                                        suc = portArr[i + 1];
                                        sSuc = portArr[i + 2];
                                        pred=portArr[i-1];
                                        sPred=portArr[i-2];

                                    }
                                }

                                val.put("key",Integer.toString(1));
                                val.put("value",mObj.getMsg());
                                SucPred spObj=new SucPred();
                                spObj.setSucc(suc);
                                spObj.setsSucc(sSuc);
                                portMap.put(mObj.getMsg(),spObj);
                                Log.d("mesg in ST","Message received is:"+mObj.getMsg()+" having suc:"+suc+" & sSuc:"+sSuc);

                                break;
                            }
                        }

                        if(mPort.equalsIgnoreCase("11108"))
                        {
                            suc="11116";
                            sSuc="11120";
                            pred="11112";
                            sPred="11124";
                        }
                        else if(mPort.equalsIgnoreCase("11116"))
                        {
                            suc="11120";
                            sSuc="11124";
                            pred="11108";
                            sPred="11112";
                        }
                        else if(mPort.equalsIgnoreCase("11120"))
                        {
                            suc="11124";
                            sSuc="11112";
                            pred="11116";
                            sPred="11108";
                        }
                        else if(mPort.equalsIgnoreCase("11124"))
                        {
                            suc="11112";
                            sSuc="11108";
                            pred="11120";
                            sPred="11116";
                        }
                        else if(mPort.equalsIgnoreCase("11112"))
                        {
                            suc="11108";
                            sSuc="11116";
                            pred="11124";
                            sPred="11120";
                        }

                        getSuccReplica(mPort, suc, sSuc, pred, sPred, dataCat);

                    }

                    else if(recdObj instanceof SuccReplica)
                    {

                        SuccReplica srObj=(SuccReplica)recdObj;
                        HashMap<String,String> gotMap=srObj.getDataCat();
                        HashMap<String,String> getAllData=mydb.getDynamoByPort(srObj.getOrigPort());
                        if(!getAllData.isEmpty())
                        {
                            for(Map.Entry<String,String> ent:getAllData.entrySet())
                            {
                                gotMap.put(ent.getKey(),ent.getValue());
                            }
                        }
                        Log.d("in ST","from getSuccReplica to SuccReplica in ST and mapsize of local is:"+getAllData.size()+" sSucc is:"+srObj.getsSucPort());

//                        if()
                        getPredReplica(srObj.getOrigPort(), srObj.getPredPort(), srObj.getsPredPort(), gotMap);
//                        getSuperSuccReplica(srObj.getOrigPort(), srObj.getsSucPort(), srObj.getPredPort(), srObj.getsPredPort(), gotMap);
                    }

                    else if(recdObj instanceof SuperSuccReplica)
                    {
                        SuperSuccReplica ssrObj=(SuperSuccReplica)recdObj;
                        HashMap<String,String> gotMap=ssrObj.getDataCat();
                        HashMap<String,String> getAllData=mydb.getDynamoByPort(ssrObj.getOrigPort());
                        if(!getAllData.isEmpty())
                        {
                            for(Map.Entry<String,String> ent:getAllData.entrySet())
                            {
                                gotMap.put(ent.getKey(),ent.getValue());
                            }
                        }
                        Log.d("in ST","from getSuperSuccReplica to SuperSuccReplica in ST and mapsize of local is:"+getAllData.size()+" pred is:"+ssrObj.getPredPort());
                        getPredReplica(ssrObj.getOrigPort(), ssrObj.getPredPort(), ssrObj.getsPredPort(), gotMap);
                    }

                    else if(recdObj instanceof PredReplica)
                    {
                        PredReplica prObj=(PredReplica)recdObj;
                        String pPort="";
                        if(prObj.getOrigPort().equalsIgnoreCase("11108"))
                            pPort="11112";
                        else if(prObj.getOrigPort().equalsIgnoreCase("11116"))
                            pPort="11108";
                        else if(prObj.getOrigPort().equalsIgnoreCase("11120"))
                            pPort="11116";
                        else if(prObj.getOrigPort().equalsIgnoreCase("11124"))
                            pPort="11120";
                        else if(prObj.getOrigPort().equalsIgnoreCase("11112"))
                            pPort="11124";

                        Log.d("seeking rep","Port:"+prObj.getOrigPort()+" seeking replica from predecessor:"+pPort);
                        HashMap<String,String> getAllData=mydb.getDynamoByPort(pPort);
                        HashMap<String,String> getFwdMap=prObj.getDataCat();
                        if(!getAllData.isEmpty()) {
                            for (Map.Entry<String, String> ent : getAllData.entrySet()) {
                                getFwdMap.put(ent.getKey(), ent.getValue());
                            }
                        }
                        for (Map.Entry<String, String> ent : getAllData.entrySet()) {
                            Log.d("fwdMap", "We got message:" + ent.getKey() + " in " + mPort + " from:" + pPort);
                        }
                        Log.d("in ST", "from getPredReplica to PredReplica in ST and mapsize of local is:" + getAllData.size() + " and mapsize after addition to succ map is:" + getFwdMap.size());
                        getSuperPredReplica(prObj.getOrigPort(), prObj.getsPredPort(), getFwdMap);

                    }

                    else if(recdObj instanceof SuperPredReplica)
                    {
                        SuperPredReplica sprObj=(SuperPredReplica)recdObj;
                        String spPred="";

                        if(sprObj.getOrigPort().equalsIgnoreCase("11108"))
                            spPred="11124";
                        else if(sprObj.getOrigPort().equalsIgnoreCase("11116"))
                            spPred="11112";
                        else if(sprObj.getOrigPort().equalsIgnoreCase("11120"))
                            spPred="11108";
                        else if(sprObj.getOrigPort().equalsIgnoreCase("11124"))
                            spPred="11116";
                        else if(sprObj.getOrigPort().equalsIgnoreCase("11112"))
                            spPred="11120";


//                        HashMap<String,String> getAllData=mydb.getContactsByPort(sprObj.getsPredPort());
                        Log.d("seeking rep","Port:"+sprObj.getOrigPort()+" seeking replica from super predecessor:"+spPred);
                        HashMap<String,String> getAllData=mydb.getDynamoByPort(spPred);
                        HashMap<String,String> getFwdMap=sprObj.getDataCat();
                        if(!getAllData.isEmpty()) {
                            for (Map.Entry<String, String> ent : getAllData.entrySet()) {
                                getFwdMap.put(ent.getKey(), ent.getValue());
                            }
                        }

                        for (Map.Entry<String, String> ent : getAllData.entrySet()) {
                            Log.d("fwdMap","We got message:"+ent.getKey()+" in "+mPort+" from super Pred:"+spPred);
                        }

                        Log.d("in ST","from getSuperPredReplica to SuperPredReplica in ST and mapsize of local is:"+getAllData.size()+" and mapsize after addition to succ map is:" + getFwdMap.size());
                        replicateOnCordinator(sprObj.getOrigPort(), getFwdMap);
                    }

                    else if(recdObj instanceof ReplicaData)
                    {
                        ReplicaData rdObj=(ReplicaData)recdObj;
                        HashMap<String,String> gotMap=rdObj.getDataCat();
                        for(Map.Entry<String,String> ent:gotMap.entrySet())
                        {
                            if(mydb.getKeyData(ent.getKey()))
                            {
                                mydb.updateContact(ent.getKey(),ent.getValue());
                            }
                            else
                            {
                                mydb.insertContact(ent.getKey(),ent.getValue(),mPort);
                            }
                        }
                        Log.d("in ST", "from getReplicaonCordinator to ReplicaData in ST and mapsize is:" + gotMap.size() + " and also inserted in DB of orig port successfully");
                    }

                    else if(recdObj instanceof CursorClass)
                    {
                        CursorClass cObj=(CursorClass)recdObj;
                        String origPort=cObj.getOrigPort();
                        String thisPort=cObj.getThisPort();
                        Log.d("came","came to ServerTask from init now mPort:"+mPort+",this Port:"+thisPort+" origPort:"+origPort);
                        String rcvCurs=cObj.getCurs();
                        Log.d("map size","map size being received in ST for * collection:"+rcvCurs.split(",").length);
                        String readString = "";
                        String[] cols1={"",""};
                        String fileSplit[];
                        HashMap<String,String> stMap=mydb.getAllCotacts();
                        if (stMap.size()>0)
                        {
                            for (Map.Entry<String,String> ent:stMap.entrySet())
                            {
                                String fileOutput = ent.getKey();
                                try
                                {
                                    readString = stMap.get(fileOutput);
                                    rcvCurs=rcvCurs+","+fileOutput+"-"+readString;

                                    Log.d("file:", fileOutput);

                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            Log.d("cursor size:", "Cursor size in Server Task after adding this port's rec:" + rcvCurs.split(",").length);
                            Log.d("My succ in Query:", "" + portMap.get(mPort).getSucc());
                        }
                        init(rcvCurs, origPort, portMap.get(thisPort).getSucc(), "notme");
                    }

                    else if(recdObj instanceof Mesg2Insert)
                    {
                        Mesg2Insert mObj=(Mesg2Insert)recdObj;
                        int cnt= mObj.getCnt();
                        String portBelongs="";
                        Log.d("cnt in ST","Cnt in ST is:"+cnt+"");


                        if(!mydb.getKeyData(mObj.getKey())) {
                            if(cnt==0)
                            {
                                portBelongs=mPort;
                            }
                            else if(cnt==1)
                            {
                                if(mPort.equalsIgnoreCase("11108"))
                                {
                                    portBelongs="11112";
                                }
                                else if(mPort.equalsIgnoreCase("11116"))
                                {
                                    portBelongs="11108";
                                }
                                else if(mPort.equalsIgnoreCase("11120"))
                                {
                                    portBelongs="11116";
                                }
                                else if(mPort.equalsIgnoreCase("11124"))
                                {
                                    portBelongs="11120";
                                }
                                else if(mPort.equalsIgnoreCase("11112"))
                                {
                                    portBelongs="11124";
                                }
                            }
                            else if(cnt==2)
                            {
                                if(mPort.equalsIgnoreCase("11108"))
                                {
                                    portBelongs="11124";
                                }
                                else if(mPort.equalsIgnoreCase("11116"))
                                {
                                    portBelongs="11112";
                                }
                                else if(mPort.equalsIgnoreCase("11120"))
                                {
                                    portBelongs="11108";
                                }
                                else if(mPort.equalsIgnoreCase("11124"))
                                {
                                    portBelongs="11116";
                                }
                                else if(mPort.equalsIgnoreCase("11112"))
                                {
                                    portBelongs="11120";
                                }
                            }

                            if(mydb.insertContact(mObj.getKey(), mObj.getMsg(), portBelongs))
                                Log.d("inserted","inserted with Success:"+mObj.getKey()+" and it belongs to port:"+portBelongs);
                        }
                        else if(mydb.getKeyData(mObj.getKey())) {

                            mydb.updateContact(mObj.getKey(), mObj.getMsg());
                            Log.d("updated", "Already present key:" + mObj.getKey() + " in:" + mObj.getPort());
                        }


                    }

                    if(recdObj instanceof GlobalDelete) {
                        GlobalDelete dg =(GlobalDelete)recdObj;
                        String thisPort=dg.getThisPort();
                        String origPort=dg.getOrigPort();
                        String fileName;
                        HashMap<String,String> delMap=mydb.getAllCotacts();
                        if (delMap.size()> 0)
                        {
                            for (Map.Entry<String,String> ent:delMap.entrySet())
                            {
                                fileName=ent.getKey();
                                if(mydb.deleteContact(fileName)>0)
                                    starDelCount++;
                            }
                            Log.d("My succ in Query:", "" + portMap.get(mPort).getSucc());

                        }

                        initDel(starDelCount, origPort, portMap.get(thisPort).getSucc(), "notme");
                    }

                    else if(recdObj instanceof AllDelete)
                    {
                        HashMap<String,String> delMap=mydb.getAllCotacts();
                        int flg=0,count=0;
                        if (delMap.size()>0) {
                            for (Map.Entry<String,String> ent:delMap.entrySet()) {
                                String fileOutput = ent.getKey();
                                if(mydb.deleteContact(fileOutput)>0)
                                    count++;

                            }
                        }
                    }

                    else if(recdObj instanceof SingleDelete)
                    {
                        Log.d("forwarded Single Query:","forwarded to port:"+mPort);
                        SingleDelete sObj=(SingleDelete)recdObj;
                        String origPort=sObj.getOrigPort();
                        String thisPort=sObj.getThisPort();
                        String selection=sObj.getStat();
                        int rcvCurs=sObj.getCurs();
                        String fileSplit[];
                        int flg=0;
                        String readString = "";
                        String[] cols1={"",""};
                        String fName="";
                        HashMap<String,String> delMap=mydb.getAllCotacts();
                        flg=0;
                        if (delMap.size()>0) {
                            for (Map.Entry<String,String> ent:delMap.entrySet()) {
                                String fileOutput = ent.getKey();
                                if(mydb.deleteContact(fName)>0)
                                    rcvCurs++;
//                                if(selection.equalsIgnoreCase(fileOutput))
//                                {
//                                    flg=1;
//                                    fName=fileOutput;
//                                }

                            }
                        }

                        Log.d("flag val","flag is:"+flg);
                        if(flg==1) {
                            Log.v("selection is:", "" + selection +" in "+mPort);

                            for (Map.Entry<String,String> ent:delMap.entrySet()) {
                                String fileOutput = ent.getKey();
                                if(mydb.deleteContact(fName)>0)
                                    rcvCurs++;

                            }
                        }

                        if(mPort.equalsIgnoreCase(sObj.getOrigPort()))
                        {
                            Log.d("check","cond satisfied in Servertask");
                        }
                        initDelSingle(rcvCurs, origPort, getSuccPort(thisPort), selection);
                    }



                    else if(recdObj instanceof SingleRecord)
                    {
                        SingleRecord sObj=(SingleRecord)recdObj;
                        Log.d("forwarded Single Query:","forwarded to port in ST:"+mPort+" for:"+sObj.getSelection()+" rcvCurs is:"+sObj.getCurs());
                        String origPort=sObj.getOrigPort();
                        String thisPort=sObj.getThisPort();
                        String selection=sObj.getSelection();
                        String rcvCurs=sObj.getCurs();
                        int flg=0;
                        String fileSplit[];

                        HashMap<String,String> hpST=mydb.getAllCotacts();

                        String readString = "";
                        String[] cols1={"",""};
                        String fName="";

                        if(rcvCurs.contains(",") && !mPort.equalsIgnoreCase(origPort))
                        {
                            initSingleNew(rcvCurs, origPort, origPort, selection);

                        }
                        else if(rcvCurs.contains(",") && mPort.equalsIgnoreCase(origPort))
                        {
                            cursor.addRow(new String[]{rcvCurs.split(",")[0],rcvCurs.split(",")[1]});
                            Log.d("changing","making cursor count="+cursor.getCount()+" and will make flag false");
                            flagMap.put(selection,false);
                        }


                        flg=0;
                        if (hpST.size()>0)
                        {
                            for(Map.Entry<String,String> ent:hpST.entrySet()) {
                                fName=ent.getKey();
                                if(selection.equalsIgnoreCase(fName)) {
                                    flg = 1;
                                    break;
                                }
                            }
                        }

                        Log.d("flag val", "flag  in ST SingleRecord is:" + flg);
                        if(flg==1)
                        {
                            Log.v("file found in ST is:", "" + selection +" in "+mPort);
                            readString = hpST.get(selection);
                            Log.v("query res:", "" + readString +" in "+mPort);
                            cols1[0] = selection;
                            cols1[1] = readString;
                            rcvCurs=selection+","+readString;
                            flagMap.put(selection,false);
                            initSingleNew(rcvCurs, origPort, origPort, selection);
                        }

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        private Uri buildUri(String scheme, String authority) {
            Uri.Builder uriBuilder = new Uri.Builder();
            uriBuilder.authority(authority);
            uriBuilder.scheme(scheme);
            return uriBuilder.build();
        }
    }

    public void getSuccReplica(String origPort,String sucPort, String sSuccPort,String predPort, String sPredPort,HashMap<String, String> dataCat)
    {
        try {
            Log.d("fwd","in getSuccReplica to be forwarded to:"+sucPort);
            Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                    Integer.parseInt(sucPort));

            SuccReplica cObj=new SuccReplica();
            cObj.setOrigPort(origPort);
            cObj.setDataCat(dataCat);
            cObj.setSucPort(sucPort);
            cObj.setPredPort(predPort);
            cObj.setsSucPort(sSuccPort);
            cObj.setsPredPort(sPredPort);
            ObjectOutputStream msgObject = new ObjectOutputStream(socket.getOutputStream());

            msgObject.writeObject(cObj);
            msgObject.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSuperSuccReplica(String origPort,String sSuccPort,String predPort,String sPredPort,HashMap<String,String> dataCat)
    {
        try {
            Log.d("fwd","in getSuperSuccReplica to be forwarded to:"+sSuccPort);
            Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                    Integer.parseInt(sSuccPort));

            SuperSuccReplica cObj=new SuperSuccReplica();
            cObj.setOrigPort(origPort);
            cObj.setDataCat(dataCat);
            cObj.setPredPort(predPort);
            cObj.setsPredPort(sPredPort);
            cObj.setsSuccPort(sSuccPort);
            ObjectOutputStream msgObject = new ObjectOutputStream(socket.getOutputStream());

            msgObject.writeObject(cObj);
            msgObject.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getPredReplica(String origPort,String predPort,String sPredPort,HashMap<String,String> dataCat)
    {
        try {
            Log.d("fwd","in getPredReplica and has map size being forwarded as:"+dataCat.size()+" to be forwarded to:"+predPort);
            Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                    Integer.parseInt(predPort));

            PredReplica cObj=new PredReplica();
            cObj.setOrigPort(origPort);
            cObj.setDataCat(dataCat);
            cObj.setPredPort(predPort);
            cObj.setsPredPort(sPredPort);
            ObjectOutputStream msgObject = new ObjectOutputStream(socket.getOutputStream());

            msgObject.writeObject(cObj);
            msgObject.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSuperPredReplica(String origPort,String sPredPort,HashMap<String,String> dataCat )
    {
        try {
            Log.d("fwd","in getPredReplica and has map size being forwarded as:"+dataCat.size()+" to be forwarded to:"+sPredPort);
            Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                    Integer.parseInt(sPredPort));

            SuperPredReplica cObj=new SuperPredReplica();
            cObj.setOrigPort(origPort);
            cObj.setDataCat(dataCat);
            cObj.setsPredPort(sPredPort);
            ObjectOutputStream msgObject = new ObjectOutputStream(socket.getOutputStream());

            msgObject.writeObject(cObj);
            msgObject.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void replicateOnCordinator(String origPort,HashMap<String,String> fMap)
    {
        try {
            Log.d("fwd","in replicateOnCordinator withmap size:"+fMap.size()+" to be forwarded to:"+origPort);
            Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                    Integer.parseInt(origPort));

            ReplicaData cObj=new ReplicaData();
            cObj.setOrigPort(origPort);
            cObj.setDataCat(fMap);

            ObjectOutputStream msgObject = new ObjectOutputStream(socket.getOutputStream());

            msgObject.writeObject(cObj);
            msgObject.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private class ClientTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... msgs) {
            try {
                String remotePort = REMOTE_PORT0;
                if (msgs[1].equals(REMOTE_PORT0))
                    remotePort = REMOTE_PORT1;
                int i=0;
                remotePort=msgs[0];
                Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                        Integer.parseInt(remotePort));

                String msgToSend = msgs[0];
                /*
                 * TODO: Fill in your client code that sends out a message.
                 */

                Message msg = new Message();
                msg.setMsg(msgToSend);
                ObjectOutputStream msgObject = new ObjectOutputStream(socket.getOutputStream());
                msgObject.writeObject(msg);
                msgObject.close();
                socket.close();
            } catch (UnknownHostException e) {
                Log.e(TAG, "ClientTask UnknownHostException");
            } catch (IOException e) {
                Log.e(TAG, "ClientTask socket IOException");
            }

            return null;
        }
    }

    private class ClientTask1 extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... msgs) {
            try {

                Log.d("coming in CT1Caller","coming in client task for:"+msgs[3]+" to forward to:"+msgs[2]+" from:"+mPort);



                Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                        Integer.parseInt(msgs[2]));

                if(!mPort.equalsIgnoreCase(msgs[1]))
                    flagMap.put(msgs[3],false);

                Log.d("coming","does it come here bfor exception in Query? &flagMap:"+flagMap.get(msgs[3]));

                SingleRecord cObj=new SingleRecord();
                cObj.setOrigPort(msgs[1]);
                cObj.setCurs(msgs[0]);
                cObj.setThisPort(msgs[2]);
                cObj.setSelection(msgs[3]);
                ObjectOutputStream msgObject = new ObjectOutputStream(socket.getOutputStream());
                msgObject.writeObject(cObj);
                msgObject.close();
                socket.close();
            } catch (UnknownHostException e) {
                Log.e(TAG, "ClientTask UnknownHostException");
            } catch (IOException e) {

                Log.d("Failed", "Cannot proceed for Query as " + msgs[2] + " has failed");
                try {
                    for (int i = 0; i < portArr.length; i++)
                    {
                        if (msgs[2].equalsIgnoreCase(portArr[i]))
                        {
                            if (i == portArr.length - 1)
                            {
                                //                            sendInsert(key,val,portArr[0],cnt+1);
                                if(!mPort.equalsIgnoreCase(msgs[1]))
                                    flagMap.put(msgs[3],false);
                                else{
                                    Thread.sleep(1800);
//                                    flagMap.put(msgs[3], false);
                                }
//                                initSingleNew(msgs[0], msgs[1], portArr[0], msgs[3]);
                                new ClientTask1().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,""+msgs[0], msgs[1],portArr[0],msgs[3]);
                            } else {
                                //                            sendInsert(key,val,portArr[i+1],cnt+1);
                                if(!mPort.equalsIgnoreCase(msgs[1]))
                                    flagMap.put(msgs[3],false);
                                else{
                                    Thread.sleep(1800);
//                                    flagMap.put(msgs[3], false);
                                }
//                                initSingleNew(msgs[0], msgs[1], portArr[i + 1], msgs[3]);
                                new ClientTask1().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, "" + msgs[0], msgs[1], portArr[i+1], msgs[3]);
                            }
                        }
                    }
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
            return null;
        }
    }


    public synchronized void ClientTask1Caller(String curs, String origPort,String port, String selection)
    {
        try {

            Log.d("coming in CT1Caller","coming in client task for:"+selection+" to forward to:"+port+" from:"+mPort);

            Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                    Integer.parseInt(port));

            SingleRecord cObj=new SingleRecord();
            cObj.setOrigPort(origPort);
            cObj.setCurs(curs);
            cObj.setThisPort(port);
            cObj.setSelection(selection);
            ObjectOutputStream msgObject = new ObjectOutputStream(socket.getOutputStream());
            msgObject.writeObject(cObj);
            msgObject.close();
            socket.close();
        } catch (UnknownHostException e) {
            Log.e(TAG, "ClientTask UnknownHostException");
        } catch (IOException e) {
            Log.e(TAG, "ClientTask socket IOException");
        }

    }

    public MatrixCursor initSingleNew(String curs,String origPort,String port,String sel) throws InterruptedException
    {

        Log.d("key value pair:","key value pair being passed:"+curs+",succ port:"+port);

        long t1=0,t2=0;
        Log.d("curs size","cursor size  "+sel+" before ifs:"+cursor.getCount());
        if(curs.contains(","))
        {
            if(mPort.equalsIgnoreCase(origPort))
            {

                String cSplit[]=curs.split(",");
                cursor.addRow(new String[]{cSplit[0], cSplit[1]});
                flagMap.put(sel,false);
                Log.d("cond satisfied","contains comma and mport now original for "+sel+" and cursor size:"+cursor.getCount());
                var=false;

            }
            else
            {
                new ClientTask1().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,""+curs, origPort,origPort,sel);
            }

        }
        else
        {
            Log.d("no comma","no comma yet for:"+sel);
            Log.d("curs size","cursor size for "+sel+" just bfore return:"+cursor.getCount()+" and flagMap val is:"+flagMap.get(sel));
            new ClientTask1().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,""+curs, origPort,port,sel);
        }

        Log.d("check","flag val:"+flagMap.get(sel)+" count:"+cursor.getCount());
        do {
            Thread.sleep(100);
        }while(flagMap.get(sel));

        Thread.sleep(700);

        flagMap.put(sel,true);
//        else
//            Thread.sleep(200);
//        setFlagMap(sel,true);

        Log.d("came","came here only bcoz cursor size:"+cursor.getCount()+" and flagMap var is:"+flagMap.get(sel)+" for:"+sel);
        return cursor;
    }

    public synchronized void setFlagMap(String s,boolean v)
    {
        flagMap.put(s,v);
    }

    public void addToCursor(String curs,String origPort,String sucPort,String stat) throws InterruptedException {
        try {
            Log.d("fwd","from init to be forwarded to:"+sucPort);
            Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                    Integer.parseInt(sucPort));

            CursorClass cObj=new CursorClass();
            cObj.setOrigPort(origPort);
            cObj.setCurs(curs);
            cObj.setThisPort(sucPort);
            cObj.setStat(stat);
            ObjectOutputStream msgObject = new ObjectOutputStream(socket.getOutputStream());

            msgObject.writeObject(cObj);
            msgObject.close();
            socket.close();

        } catch (IOException e) {
//            e.printStackTrace();
            String sPort="";
            Log.d("failed", sucPort + " failed in addToCursor and cannot procceed with * query");
            if(sucPort.equalsIgnoreCase("11108"))
                sPort="11116";
            else if(sucPort.equalsIgnoreCase("11116"))
                sPort="11120";
            else if(sucPort.equalsIgnoreCase("11120"))
                sPort="11124";
            else if(sucPort.equalsIgnoreCase("11124"))
                sPort="11112";
            else if(sucPort.equalsIgnoreCase("11112"))
                sPort="11108";

            init(curs,origPort,sPort,stat);
        }
    }


    public MatrixCursor init(String stMp,String origPort,String sucPort,String stat) throws InterruptedException {

        Log.d("stat:","stat:"+stat+",mPort:"+mPort+",origPort:"+origPort);
        Log.d("in init","coming in init funct after * with mPort:"+mPort+" and origPort:"+origPort+" cursor size:"+cursor.getCount());

        if(mPort.equalsIgnoreCase(origPort))
        {
            Log.d("mp==op","in init mPort=OrigPort"+" and stMap size:"+stMp.split(",").length+" and stat:"+stat);
            if(stat.equalsIgnoreCase("me"))
            {
                Log.d("forward","forward from init to succ port:"+sucPort);
                Log.d("map size","map size being forwarded for * collection:"+stMp.split(",").length);

                addToCursor(stMp,origPort,sucPort,stat);

            }
            else if(stat.equalsIgnoreCase("notme"))
            {
                String dashSplit[]=null;
                String stMpSplit[]=stMp.split(",");
                for(int i=0;i<stMpSplit.length;i++)
                {
                    dashSplit=stMpSplit[i].split("-");
                    cursor.addRow(new String[]{dashSplit[0], dashSplit[1]});
                }

            }
        }
        else
        {
            Log.d("forward","forward from init to succ port:"+sucPort);
            addToCursor(stMp,origPort,sucPort,stat);
        }

        if(cursor.getCount()<1)
            Thread.sleep(3000);
        return cursor;
    }



    @Override
    public synchronized Cursor query(Uri uri, String[] projection, String selection,
                                     String[] selectionArgs, String sortOrder) {
        // TODO Auto-generated method stub
        HashMap<String,String> arr=mydb.getAllCotacts();
        int flg=0;
        Log.d("records","records so far in this AVD");
        String concatKP="";

        if (selection.equalsIgnoreCase("\"*\"")) {

            MatrixCursor curs = new MatrixCursor(new String[]{"key", "value"});
            cursor = new MatrixCursor(new String[]{"key","value"});

            if (arr.size()>0)
            {
                for (Map.Entry<String,String> ent:arr.entrySet())
                {
                    String fileOutput = ent.getKey();
                    try
                    {

                        String readString = arr.get(fileOutput);
                        if(concatKP.equalsIgnoreCase(""))
                            concatKP=fileOutput+"-"+readString;
                        else
                            concatKP=concatKP+","+fileOutput+"-"+readString;

//                        Log.d("file:", fileOutput);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                Log.d("in *", "coming in * query");
                Log.d("My succ in Query:", "" + portMap.get(mPort).getSucc());

            }

            try {
                return init(concatKP,mPort,portMap.get(mPort).getSucc(),"me");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        else if (selection.equalsIgnoreCase("\"@\"")) {
            if (arr.size()>0) {
                // loops through the array of files, outputing the name to console
                MatrixCursor curs = new MatrixCursor(new String[]{"key", "value"});
                Log.d("printing @","before return printing local @ recorrds");
                for(Map.Entry<String,String> ent:arr.entrySet())
                {
                    curs.addRow(new String[]{ent.getKey(),ent.getValue()});
                    Log.d("@ Rec:", ent.getKey() + "," + ent.getValue());
                }

                Log.d("cursor size:", "" + curs.getCount());
                return curs;
            }
        }
        else {
            MatrixCursor curs = new MatrixCursor(new String[]{"key", "value"});
            Log.d("new Single", "New Single message:"+selection);
            flg = 0;
//            var=true;
            //if(!flagMap.containsKey(selection))
            flagMap.put(selection,true);
            cursor = new MatrixCursor(new String[]{"key", "value"});
            String fName = "";
            String keyValPair = "";
            if (arr.size()>0) {
                for(Map.Entry<String,String> ent:arr.entrySet())
                {
                    fName=ent.getKey();
                    if(selection.equalsIgnoreCase(fName))
                    {
                        flg=1;
                        break;
                    }
                }
            }
            Log.d("flag val", "flag is:" + flg);
            if (flg == 1) {
                Log.v("file found locally is:", "" + selection);
                String readString=arr.get(selection);
                keyValPair = keyValPair + selection;
                keyValPair = keyValPair + "," + readString;
                Log.v("query res local:", "" + readString);
                curs.addRow(new String[]{selection, readString});
                flagMap.put(selection,false);
                return curs;
            }
            else {
                try {
                    for (int i = 0; i < portArr.length; i++) {
                        if (i < portArr.length - 1)
                        {

                            if (i == 0)
                            {
                                if (genHash(selection).compareTo(genHash("" + Integer.parseInt(portArr[i]) / 2)) < 0)
                                {
                                    Log.d("Lesser", "message " + selection + " lesser than the lowest itself:" + portArr[i]);
                                    MatrixCursor retCurs= initSingleNew(keyValPair, mPort, portArr[i], selection);
                                    Log.d("coming back","curser getting returned for:"+selection+" and has size:"+retCurs.getCount());
                                    return retCurs;

                                } else if (genHash(selection).compareTo(genHash("" + Integer.parseInt(portArr[i]) / 2)) > 0 && genHash(selection).compareTo(genHash("" + Integer.parseInt(portArr[i + 1]) / 2)) < 0)
                                {

                                    Log.d("Greater", "message " + selection + " greater than lowest but less than successor:" + portArr[i] + "," + portArr[i + 1]);
                                    MatrixCursor retCurs= initSingleNew(keyValPair, mPort, portArr[i + 1], selection);
                                    Log.d("coming back","curser getting returned for:"+selection+" and has size:"+retCurs.getCount());
                                    return retCurs;
                                }
                            }
                            else if (genHash(selection).compareTo(genHash("" + Integer.parseInt(portArr[i]) / 2)) > 0 && genHash(selection).compareTo(genHash("" + Integer.parseInt(portArr[i + 1]) / 2)) < 0)
                            {

                                Log.d("Greater", "message " + selection + " greater than me but less than successor:" + portArr[i] + "," + portArr[i + 1]);
                                MatrixCursor retCurs= initSingleNew(keyValPair, mPort, portArr[i + 1], selection);
                                Log.d("coming back","curser getting returned for:"+selection+" and has size:"+retCurs.getCount());
                                return retCurs;
                            }

                        }
                        else if (i == portArr.length - 1)
                        {
                            if (genHash(selection).compareTo(genHash("" + Integer.parseInt(portArr[i]) / 2)) > 0)
                            {
                                Log.d("Greater", "message " + selection + " greater than highest port:" + portArr[i]);
                                MatrixCursor retCurs= initSingleNew(keyValPair, mPort, portArr[0], selection);
                                Log.d("coming back","curser getting returned for:"+selection+" and has size:"+retCurs.getCount());
                                return retCurs;
                            }

                        }
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    private String genHash(String input) throws NoSuchAlgorithmException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        byte[] sha1Hash = sha1.digest(input.getBytes());
        Formatter formatter = new Formatter();
        for (byte b : sha1Hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}
