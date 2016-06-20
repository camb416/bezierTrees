package com.cameronbrowning.beziertrees;
import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by cameron.browning on 6/19/16.
 */

public class app extends PApplet {


    ArrayList<AnimatedCurve> curves;
    int numCurves;
    int numPts;

    float t;

    public void settings(){
        size(720,480);
        pixelDensity(2);
    }

public void setup(){

    t = 0.0f;

    background(255);
    curves = new ArrayList<AnimatedCurve>();

    numCurves = 8;
    numPts = 0;

    for(int i=0;i<numCurves;i++){
        AnimatedCurve c;
        float angle = (float)i/(float)numCurves*(float)Math.PI*2.0f;
        Point m = new Point(width/2,height/2,angle);
        float len = 20.0f;
        float bendiness = 10.0f;

        c = new AnimatedCurve(this,
                m,
                len,
                angle,
                bendiness);
        curves.add(c);
    }



}

    public void sprout(){


        ArrayList<AnimatedCurve> newCurves= new ArrayList<AnimatedCurve>();


        int numSplit = 2;

        for(int i=0;i<curves.size();i++) {
            AnimatedCurve thisCurve = curves.get(i);
            if(!thisCurve.getSprouted()) {
                thisCurve.setSprouted(true);
                for(int j=0;j<numSplit;j++) {
                    AnimatedCurve newCurve;
                    Point startPt = thisCurve.plot(1.0f);
                    float angle = startPt.z;

                    float len = 10.0f;
                    float bendiness = 5.0f;

                    newCurve = new AnimatedCurve(this,
                            startPt,
                            len,
                            angle,
                            bendiness);
                    newCurves.add(newCurve);
                }
            }
        }
        for(int i=0;i<newCurves.size();i++){
            AnimatedCurve newCurve = newCurves.get(i);
            curves.add(newCurve);
        }


        }
    public void update(){
        t += 0.025f;
        if(t>1.0f){
            t = 0.0f;
        }


    }
    public void draw(){
//       fill(255,255,255,16);
//        rect(0,0,width,height);
//background(255);

        for(int i=0;i<curves.size();i++){
            AnimatedCurve c;
            c = curves.get(i);
         //  c.update(0.0025f);
        }

        for(int i=0;i<curves.size();i++){
            AnimatedCurve c;
            c = curves.get(i);
            c.draw();
        }


        }
    public void keyPressed() {
        sprout();
    }

}
