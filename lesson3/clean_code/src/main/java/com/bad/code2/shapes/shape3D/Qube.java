package com.bad.code2.shapes.shape3D;

/**
 * Реализация объёмной фигуры - куб
 */
public class Qube implements Shape3D {

    /**
     * центральная точка куба по оси X
     */
    private Double centerX;
    /**
     * центральная точка куба  по оси Y
     */
    private Double centerY;
    /**
     * центральная точка куба по оси Z
     */
    private Double centerZ;
    /**
     * длина ребра куба
     */
    private Double edgeSize;

    public Qube(Double centerX, Double centerY, Double centerZ, Double edgeSize) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.centerZ = centerZ;
        this.edgeSize = edgeSize;
    }

    @Override
    public Double getCenterX() {
        return centerX;
    }

    @Override
    public Double getCenterY() {
        return centerY;
    }

    @Override
    public Double getCenterZ() {
        return centerZ;
    }

    @Override
    public Double getVolume() {
        return Math.pow(edgeSize, 3);
    }
}
