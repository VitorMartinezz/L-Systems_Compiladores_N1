package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    private static String htmlStart = "<!DOCTYPE html><html><body><svg height=\"2160\" width=\"3840\">";
    private static String htmlEnd = "</svg></body></html>";
    private static String htmlMiddle = "";

    private static int x1 = 0;
    private static int y1 = 0;
    private static int x2 = 0;
    private static int y2 = 0;

    public static void main(String[] args) {
        System.out.println("Digite a quantidade de rodadas:");
        Scanner scMenu = new Scanner(System.in);
        int total = scMenu.nextInt();
        String initial = "A";
        String st = null;

        for (int j = 0; j < total; j++) {
            System.out.println(initial);
            CreateSvg(initial);
            initial = Result(initial);
        }
        System.out.println(initial);

        SaveSVG();
        System.out.println("");
        System.out.println("------------------------------");
        System.out.println("ARQUIVO TXT GERADO COM SUCESSO");
    }

    private static void SaveSVG(){
        Path path = Paths.get("PerfectLine.html");
        String finalString = htmlStart + htmlMiddle + htmlEnd;
        byte[] bytes = finalString.getBytes();

        try {
            Files.write(path,bytes);
        } catch (IOException ex) {
            // Handle exception
        }
    }

    private static String Result(String st){
        String lastString = "";
        for (char i : st.toCharArray()){
            if(i == 'A'){
                lastString += "AB";
            }
            else{
                lastString += "A";
            }
        }

        return lastString;
    }

    private static void CreateSvg(String st){

        for (char i : st.toCharArray()){
            if(i == 'A'){
                x1 = x2;
                y1 = y2;
                x2 += 50;
                y2 += 50;
                buildLineString();
            }
            else{
                x1 = x2;
                y1 = y2;
                x2 += -20;
                y2 += -35;
                buildLineString();
            }
        }
    }

    private static void buildLineString(){
        String result = "<line ";
        result += "x1=\"" + x1 +"\" ";
        result += "x2=\"" + x2 +"\" ";
        result += "y1=\"" + y1 +"\" ";
        result += "y2=\"" + y2 +"\" ";
        result += "style=\"stroke:rgb(255,0,0);stroke-width:2\" />\"";
        htmlMiddle += result;
    }

}
