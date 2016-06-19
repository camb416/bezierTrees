package com.cameronbrowning.beziertrees;

import processing.core.PApplet;
import processing.event.MouseEvent;

/**
 * Created by cameron.browning on 6/19/16.
 */
public class DraggablePoint extends Point{
    boolean isSelected;
    boolean isDragging;
    PApplet p;
    DraggablePoint(float _x, float _y, PApplet _p){
        super(_x,_y);
        isSelected = isDragging = false;
        p = _p;
        p.registerMethod("mouseEvent",this);

    }
    public void mouseEvent(processing.event.MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        boolean clicked;

        switch (e.getAction()) {
            case MouseEvent.PRESS:
                if(this.isSelected){
                    this.isDragging = true;
                }
                break;
            case MouseEvent.RELEASE:
                this.isDragging = false;
                break;
            case MouseEvent.DRAG:
                if(this.isDragging) {
                    this.x = e.getX();
                    this.y = e.getY();
                }
                break;
            case MouseEvent.MOVE:
                if(p.dist(e.getX(),e.getY(),this.x,this.y)<20){
                    this.isSelected = true;
                } else {
                    this.isSelected = false;
                }
                break;
        }
    }
    void draw(){
        if(isDragging){
            p.noStroke();
            p.fill(0,255,0);
        } else if(isSelected){
            p.noStroke();
            p.fill(255,0,0);
        } else {
            p.noStroke();
            p.fill(212);
        }
        p.ellipse(x,y,4,4);

    }
}
