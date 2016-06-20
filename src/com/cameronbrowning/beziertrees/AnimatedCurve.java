package com.cameronbrowning.beziertrees;

import processing.core.PApplet;

/**
 * Created by cameron.browning on 6/19/16.
 */
public class AnimatedCurve {

    Point a,b,c,d;
    PApplet p;
    boolean sprouted;
    boolean drawOnce, drawn;

    public AnimatedCurve(PApplet _p,
                  Point _a,
                  Point _b,
                  Point _c,
                  Point _d){
        a = _a;
        b = _b;
        c = _c;
        d = _d;
        p = _p;

    }
    public AnimatedCurve(PApplet _p,
                  Point _a,
                  float _length,
                  float _desiredAngle,
                  float _bendiness
                  )
    {
        p = _p;
        a = _a;
        float angle = _desiredAngle;
        d = new Point(a.x+(float)Math.cos(angle)*_length,a.y+(float)Math.sin(angle)*_length);
        b = new Point(a.x +(float)Math.cos(a.z)*_length/2.0f,a.y+(float)Math.sin(angle)*_length/2.0f);
        //c = new Point(d.x+(float)Math.cos(Math.random()*Math.PI/2.0f+angle-Math.PI/4.0f)*_bendiness,d.y+(float)Math.sin(Math.random()*Math.PI/2.0f+angle-Math.PI/4.0f)*_bendiness);
        c = new Point(d.x+(float)Math.cos(Math.random()*Math.PI/4.0f+angle-Math.PI/8.0f-Math.PI)*_bendiness,d.y+(float)Math.sin(Math.random()*Math.PI/4.0f+angle-Math.PI/8.0f-Math.PI)*_bendiness);
        sprouted = false;
        drawOnce = true;
        drawn = false;
    }

    public void setSprouted(boolean _sprouted){
        sprouted = _sprouted;
    }
    public boolean getSprouted(){ return sprouted;}


    public Point plot(float pct_in){
        float pct = Math.max(0.0f,Math.min(pct_in,1.0f));
        float cX = 3*(b.x-a.x);
        float bX = 3*(c.x-b.x)-cX;
        float aX = d.x-a.x-cX-bX;
        float cY = 3*(b.y-a.y);
        float bY = 3*(c.y-b.y)-cY;
        float aY = d.y-a.y-cY-bY; //  +ofRandom(-10,10);

        // get the slope
        // http://stackoverflow.com/a/15399173
        float t = pct;
        float B0_dt = -3*(1-t)*(1-t);
        float B1_dt = 3*(1-t)*(1-t) -6*t*(1-t);
        float B2_dt = - 3*t*t + 6*t*(1-t);
        float B3_dt = 3*t*t;
        float  px_dt = (B0_dt * a.x) + (B1_dt * d.x) + (B2_dt * c.x) + (B3_dt * d.x);
        float py_dt = (B0_dt * a.y) + (B1_dt * d.y) + (B2_dt * c.y) + (B3_dt * d.y);
        float slope = (float)Math.atan2(py_dt,px_dt);


        Point returnVal = new Point(0,0,slope);
        returnVal.x = aX*pct*pct*pct + bX*pct*pct + cX*pct + a.x; //+ofRandom(-5,5);
        returnVal.y = aY*pct*pct*pct + bY*pct*pct + cY*pct + a.y;
        //p.text("a",aX,aY);
        //p.text("b",bX,bY);
        //p.text("c",cX,cY);

        return returnVal;
    }
    public void draw(){
        if(drawOnce && !drawn || !drawOnce){

drawn = true;
        p.stroke(0,0,0,32);
        p.noFill();
        p.bezier(a.x,
                a.y,
                b.x,
                b.y,
                c.x,
                c.y,
                d.x,
                d.y);
        }
     //   Point pt = this.plot(1.0f);
     //   p.ellipse(pt.x,pt.y,5,5);
//        p.stroke(212);
//        p.line(a.x,a.y,
//                b.x,b.y);
//        p.line(d.x,d.y,
//                c.x,c.y);
//        p.ellipse(a.x,a.y,3,3);
//        p.ellipse(b.x,b.y,3,3);
//        p.ellipse(c.x,c.y,3,3);
//        p.ellipse(d.x,d.y,3,3);
    }

}
