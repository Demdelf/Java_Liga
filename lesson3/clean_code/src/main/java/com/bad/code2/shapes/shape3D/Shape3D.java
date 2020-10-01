package com.bad.code2.shapes.shape3D;

import com.bad.code2.shapes.Shape;

/**
 * Интерфейс объёмной фигуры
 */
public interface Shape3D extends Shape {

    /**
     * @return центральную точку фигуры по оси Z
     */
    Double getCenterZ();

    /**
     * @return объём фигуры
     */
    Double getVolume();
}
