package com.cameronbrowning.beziertrees;

import processing.core.PGraphics;

/**
 * Created by cameron.browning on 6/19/16.
 */
public class Point extends PGraphics{
    float x,y,z;
    Point(float _x, float _y){
        this(_x,_y,0.0f);
    }
    Point(float _x, float _y, float _z){
        x = _x;
        y = _y;
        z = _z;
    }
}

