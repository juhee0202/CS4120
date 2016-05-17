class Point { // a mutable point
    p_x,p_y: int

    add(p: Point) : Point {
        return createPoint(p_x + p.p_x, p_y + p.p_y)
    }

}

createPoint(x: int, y: int): Point {
    return new Point
}

main(args:int[][]) {

}