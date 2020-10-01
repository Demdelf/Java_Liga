package com.bad.code2;

import com.bad.code2.shapes.shape2D.Square;
import com.bad.code2.shapes.shape3D.Qube;

/**
 * Пример хорошего кода
 */
public class GoodCode {
    public static void main(String... args) {
        Qube qube = new Qube(1d, 1d, 1d, 10d);
        System.out.println("Qube volume: " + qube.getVolume());

        Square square = new Square(1d, 1d, 5d);
        System.out.println("Square area: " + square.getArea());
    }

}
