/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2017maj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Gabor
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        record[] adatok =new record[500];
        String helyesValaszok ="";
        int versenyzokSzama =0;
        
        //elso feladat
        try{File file = new File("C:\\Users\\Gabor\\Documents\\NetBeansProjects\\emelt_info_2017maj_java\\2017maj\\src\\valaszok.txt");
        Scanner sc = new Scanner(file);
        helyesValaszok=sc.nextLine();
        for(int x=0;x<500;x++){
        if(sc.hasNext()){
        String id;
        id=sc.next();
        String val =sc.next();
        adatok[x]= new record(id,val);
        //System.out.print(adatok[x].getId()+" "+ adatok[x].getValaszaiEgyben()+"\n");
        }}
        sc.close();
        System.out.println("1. Feladat: Az adatok beolvasása");
        }
        catch(FileNotFoundException fnf){System.out.println("A keresett fájl nem található");}
        
        //masodik feladat
        for(int x=0;adatok[x]!=null;x++){versenyzokSzama++;}
        System.out.println("\n2. feladat: A vetélkedőn "+versenyzokSzama+" versenyző indult. ");
        
        //harmadik feladat
        String userInput3;
        System.out.print("\n3. feladat: A versenyző azonosítója = ");
        Scanner scIn = new Scanner(System.in);
        userInput3 = scIn.nextLine();
        System.out.println(userInput3);
        String valaszai = "ismeretlen";
        for (int x=0;x<500;x++) {
            if(adatok[x]!=null)
            {if(adatok[x].getId().equals(userInput3)){valaszai=adatok[x].getValaszaiEgyben();}}
        }
        System.out.println("A versenyző megoldásai: "+valaszai);
        
        //negyedik feladat
        System.out.println("\n4. Feladat:\n"+helyesValaszok);
        String[] helyes = valaszDarabolasa(helyesValaszok);
        String[] adott = valaszDarabolasa(valaszai);
        for(int x=0;x<14;x++){
        if(helyes[x].equals(adott[x])){System.out.print("+");}
        else{System.out.print(" ");}}
        
        //otodik feladat
        System.out.print("\n\n5. Feladat: A feladat sorszáma = ");
        String userInput5="Nem sikerült a beolvasás!";
        if(scIn.hasNext()){userInput5=scIn.nextLine();scIn.close();}
        System.out.println(userInput5);
        int feladatSorszama = Integer.parseInt(userInput5)-1;
        int helyesenValaszolokSzama=0;
        for(int x=0;x<versenyzokSzama;x++){
            String[] v=valaszDarabolasa(adatok[x].getValaszaiEgyben());
            if(v[feladatSorszama].equals(helyes[feladatSorszama])){
            helyesenValaszolokSzama++;}
        }
        DecimalFormat df2 = new DecimalFormat(".##");
        double szazaleka = helyesenValaszolokSzama*100.0/versenyzokSzama;
        String kiirando= df2.format(szazaleka);
        System.out.println("A feladatra "+helyesenValaszolokSzama+" fő, a versenyzők "+kiirando+"%-a adott helyes választ.");
        
        //hatodik feladat
        String[] idEsPontszam =hatodik(adatok,versenyzokSzama,helyes);
        System.out.println("\n6. feladat: A versenyzők pontszámának meghatározása ");
        try (PrintWriter writer = new PrintWriter("pontok.txt", "UTF-8");)
        {for(int x=0;x<versenyzokSzama;x++){
        writer.println(idEsPontszam[x]);}
        writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException ex)
        {System.out.println("A fájlba írás nem sikerült");}
        
        //hetedik feladat
        System.out.println("\n7. feladat: A verseny legjobbjai: ");
        String[] idsForSort=new String[versenyzokSzama];
        Integer[] pontForSort = new Integer[versenyzokSzama];
        
        for(int x=0;x<versenyzokSzama;x++){
        Scanner sc7=new Scanner(idEsPontszam[x]);
        String id = sc7.next();
        int pont =sc7.nextInt();
        idsForSort[x]=id;
        pontForSort[x]=pont;
        }
        Integer[] unSortedArr = pontForSort.clone();
        List<Integer> sorterList= Arrays.asList(pontForSort);
        Collections.sort(sorterList,Collections.reverseOrder());
        
        int actualPont=0;
        int dijazottHelyekDb =3;
        int hely =0;
        for(int y=0;y<dijazottHelyekDb;y++){
        if(sorterList.get(y)!=actualPont){
        actualPont =sorterList.get(y);
        hely++;
        for(int x=0;x<versenyzokSzama;x++){
        if(unSortedArr[x]==actualPont){System.out.println(hely+". hely: "+idsForSort[x]+": "+actualPont+" pont");}
        }
        } else {dijazottHelyekDb++;}
        }
        
    }
    
    //valaszok beolvasasa tagolva
    public static String[] valaszDarabolasa(String s){
    Scanner sc = new Scanner(s);
    sc.useDelimiter("");
    String[] answers = new String[14];
    for(int x=0;x<14;x++){if(sc.hasNext()){String firstLine =sc.next();
            answers[x] = firstLine;}}
    sc.close();
    return answers;
    }
    
    public static String[] hatodik (record[] data,int db,String[] correct){
    String[] ret=new String[db];
    for(int x=0;x<db;x++){
    int feladatszam=0;
    int pontszam=0;
    String[] valaszaiDarabolva= valaszDarabolasa(data[x].getValaszaiEgyben());
    //1-5feladat 3pont
    while(feladatszam<5){
    if(valaszaiDarabolva[feladatszam].equals(correct[feladatszam]) ){
    pontszam= pontszam+3;}
    feladatszam++;}
    //6-10 4pont
    while(feladatszam<10){
    if(valaszaiDarabolva[feladatszam].equals(correct[feladatszam]) ){
    pontszam= pontszam+4;}
    feladatszam++;}
    //11-13 5pont
    while(feladatszam<13){
    if(valaszaiDarabolva[feladatszam].equals(correct[feladatszam]) ){
    pontszam= pontszam+5;}
    feladatszam++;}
    //14 6
    if(valaszaiDarabolva[feladatszam].equals(correct[feladatszam]) ){
    pontszam= pontszam+6;}
    //make the string array
    ret[x] =data[x].getId()+" "+pontszam;
    }
    return ret;
    }
    
    public static int hetedik (Integer[] unSortedPoints,Integer keresettSzam,int versenyzokSz){
    for(int x=0;x<versenyzokSz;x++){
    if(Objects.equals(unSortedPoints[x], keresettSzam)){return x;}}
    return 0;
    }
}
