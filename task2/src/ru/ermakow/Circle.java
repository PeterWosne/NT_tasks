package ru.ermakow;

public class Circle {

    private float x;

    private float y;

    private float r;

    public Circle(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int isInside(Point p) {
        int result;

        if((x - p.getX())*(x - p.getX()) + (y - p.getY())*(y - p.getY()) < r * r) {
            result = 1;
        }else if((x - p.getX())*(x - p.getX()) + (y - p.getY())*(y - p.getY()) == r * r) {
            result = 0;
        }else {
            result = 2;
        }
        return result;
    }
}
