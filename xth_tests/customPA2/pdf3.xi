class Color {
    r,g,b: int
}

class ColoredPoint extends Point {
    col: Color
    color(): Color { return col }

    initColoredPoint(x0: int, y0: int, col: Color): ColoredPoint {
        c = col
        initPoint(x0, y0)
        return this
    }
}