package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connessione extends Thread {
    Socket s;
    String name = "";
    Dati dati;

    Connessione(Socket s, Dati dati) {
        super();
        this.s = s;
        this.dati = dati;
    }

    public void run() {
        try {
            System.out.println("un client si è collegato");
            BufferedReader in = new BufferedReader(new InputStreamReader((s.getInputStream())));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            boolean connessione = true, ins = true;
            while(ins){
                name = in.readLine();
                if(!dati.addUsername(name)){
                    out.writeBytes("!" + '\n');
                }
                else{
                    out.writeBytes("" + '\n');
                    ins = false;
                }
            }
            while (connessione) {
                switch (in.readLine()) {
                    case "N":
                        out.writeBytes("" + dati.getNumBiglietti()[Integer.parseInt(in.readLine())] + '\n');
                        break;
                    case "BUY":
                        int compra = Integer.parseInt(in.readLine());
                        int tipo = Integer.parseInt(in.readLine());
                        if(compra > dati.getNumBiglietti()[tipo]){
                            out.writeBytes("KO" + '\n');
                        }
                        else{
                            out.writeBytes("OK" + '\n');
                            dati.changeBiglietti(-compra, tipo);
                        }
                        break;
                    case "QUIT":
                        dati.removeUsername(name);
                        out.writeBytes("!!!\n");
                        connessione = false;
                        break;
                }
            }
    
            s.close();
            System.out.println("client " + name + " si è scollegato");

        } catch (Exception e) {

        }
    }
}
