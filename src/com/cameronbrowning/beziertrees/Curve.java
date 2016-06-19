package com.cameronbrowning.beziertrees;

import processing.core.PApplet;

/**
 * Created by cameron.browning on 6/19/16.
 */
public class Curve{
    DraggablePoint a,b,ca,cb;
    PApplet p;

    float t;
    Point mid;
    boolean isStopped;


    public Curve(PApplet _p, Point _a){
        isStopped = false;
        t = 0.0f;
        p = _p;

        a = new DraggablePoint(_a.x,_a.y,p);
        b = new DraggablePoint((float)Math.random()*(float)p.width,(float)Math.random()*(float)p.height,p);
        ca = new DraggablePoint((float)Math.random()*(float)p.width,(float)Math.random()*(float)p.height,p);
        cb = new DraggablePoint((float)Math.random()*(float)p.width,(float)Math.random()*(float)p.height,p);

        mid = new Point(a.x,a.y);
    }
    public Curve(PApplet _p){
        this(_p, new Point((float)Math.random()*(float)_p.width,(float)Math.random()*(float)_p.height));
    }
    public void update(float _tinc){
        if(!isStopped) {
            t += _tinc;
            if (t > 1.0f) {
                t = 0.0f;
            }
            mid = plot(t);
        }
    }

    public void stop(){
        isStopped = true;
    }

    // plot a point along the curve
    Point plot(float pct_in){
        float pct = Math.max(0.0f,Math.min(pct_in,1.0f));
        float cX = 3*(ca.x-a.x);
        float bX = 3*(cb.x-ca.x)-cX;
        float aX = b.x-a.x-cX-bX;
        float cY = 3*(ca.y-a.y);
        float bY = 3*(cb.y-ca.y)-cY;
        float aY = b.y-a.y-cY-bY; //  +ofRandom(-10,10);

        // get the slope
        // http://stackoverflow.com/a/15399173
        float t = pct;
        float B0_dt = -3*(1-t)*(1-t);
        float B1_dt = 3*(1-t)*(1-t) -6*t*(1-t);
        float B2_dt = - 3*t*t + 6*t*(1-t);
        float B3_dt = 3*t*t;
        float  px_dt = (B0_dt * a.x) + (B1_dt * ca.x) + (B2_dt * cb.x) + (B3_dt * b.x);
        float py_dt = (B0_dt * a.y) + (B1_dt * ca.y) + (B2_dt * cb.y) + (B3_dt * b.y);
        float slope = (float)Math.atan2(py_dt,px_dt);
//println(slope);

        Point returnVal = new Point(0,0);
        returnVal.x = aX*pct*pct*pct + bX*pct*pct + cX*pct + a.x; //+ofRandom(-5,5);
        returnVal.y = aY*pct*pct*pct + bY*pct*pct + cY*pct + a.y;
        p.text("a",aX,aY);
        p.text("b",bX,bY);
        p.text("c",cX,cY);

        return returnVal;
    }

    public Point getMid(){
        return mid;
    }

   public  void draw(){
        p.stroke(212);
        p.noFill();
        p.bezier(a.x,
                a.y,
                ca.x,
                ca.y,
                cb.x,
                cb.y,
                b.x,
                b.y);
       p.stroke(212);
       p.line(a.x,a.y,
                ca.x,ca.y);
       p.line(b.x,b.y,
                cb.x,cb.y);
        a.draw();
        b.draw();
        ca.draw();
        cb.draw();

       p.noStroke();
       if(isStopped){
           p.fill(212);
       } else {
           p.fill(0);
       }

       p.noStroke();
       p.ellipse(mid.x,mid.y,5,5);
    }
}
