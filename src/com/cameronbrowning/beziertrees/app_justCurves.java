package com.cameronbrowning.beziertrees;
import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by cameron.browning on 6/19/16.
 */

public class app_justCurves extends PApplet {

    ArrayList<DraggablePoint> pts;

    ArrayList<Curve> curves;
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
    pts = new ArrayList<DraggablePoint>();
    curves = new ArrayList<Curve>();

    numCurves = 1;
    numPts = 0;

    for(int i=0;i<numCurves;i++){
        Curve c;
        c = new Curve(this);
        curves.add(c);
    }

    for(int i=0;i<numPts;i++){
        DraggablePoint d;
        d = new DraggablePoint(random(width),random(height),this);
        pts.add(d);
    }
}

    public void update(){
        t += 0.025f;
        if(t>1.0f){
            t = 0.0f;
        }


    }
    public void draw(){
       fill(255,255,255,16);
        rect(0,0,width,height);
        for(int i=0;i<numPts;i++) {
            DraggablePoint d = pts.get(i);
            d.draw();
        }

        for(int i=0;i<curves.size();i++){
            Curve c;
            c = curves.get(i);
           c.update(0.0025f);
        }

        for(int i=0;i<curves.size();i++){
            Curve c;
            c = curves.get(i);
            c.draw();
        }


        }
    public void keyPressed(){


        int howManyToSpawn = (int)random(1,5);
        ArrayList<Curve> newCurves = new ArrayList<Curve>();
        int howManyActive = 0;
        for(int i=0;i<curves.size();i++){
            if(!curves.get(i).isStopped){
                howManyActive++;
                for(int j=0;j<howManyToSpawn;j++) {
                    newCurves.add(new Curve(this, curves.get(i).getMid()));
                }
                curves.get(i).stop();
            }
        }
         //   Curve c = new Curve(this, curves.get(curves.size()-1).getMid());
        //for(int i=0;i<curves.size();i++){

       //    curves.get(curves.size()-1).stop();

        //}
            for(int i=0;i<newCurves.size();i++){
                curves.add(newCurves.get(i));
            }
    }

}
