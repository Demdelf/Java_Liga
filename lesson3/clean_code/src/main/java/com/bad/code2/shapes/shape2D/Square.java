package com.bad.code2.shapes.shape2D;

/**
 * Реализация фигуры - квадрат
 */
public class Square implements Shape2D {

    /**
     * центральная точка квадрата по оси X
     */
    private Double x;
    /**
     * центральная точка квадрата по оси Y
     */
    private Double y;
    /**
     * Длина стороны квадрата
     */
    private Double edgeSize;


    public Square(Double x, Double y, Double edgeSize) {
        this.x = x;
        this.y = y;
        this.edgeSize = edgeSize;
    }

    @Override
    public Double getCenterX() {
        return x;
    }

    @Override
    public Double getCenterY() {
        return y;
    }

    @Override
    public Double getArea() {
        return edgeSize * edgeSize;
    }
}
