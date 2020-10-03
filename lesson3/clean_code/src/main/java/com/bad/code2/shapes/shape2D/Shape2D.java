package com.bad.code2.shapes.shape2D;

/**
 * Интерфейс фигуры
 */
public interface Shape2D {
    /**
     * Возвращает центральную точку фигуры по оси X
     * @return центральная точка фигуры по оси X
     */
    Double getCenterX();

    /**
     * Возвращает центральную точку фигуры по оси Y
     * @return центральная точка фигуры по оси Y
     */
    Double getCenterY();

    /**
     * Считает площадь фигуры
     * @return площадь фигуры
     */
    Double getArea();
}
