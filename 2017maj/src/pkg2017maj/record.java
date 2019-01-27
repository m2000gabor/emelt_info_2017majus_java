/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2017maj;

/**
 *
 * @author Gabor
 */
public class record {
    String id;
    String answers;
    record(String ID, String valaszok){this.id=ID; this.answers=valaszok;};

    public String getId() {
        return id;
    }
    
    public String getValaszaiEgyben(){
    return answers;
    }
}