class Random {

}

class ColoredPoint extends Point {
    testThis() {
        x:ColoredPoint = this
        y:Point = this
    }
}

class Point { // a mutable point
    p_x,p_y: int

    testThis() {
        x:Point = this
    }

    move(dx: int, dy: int) {
        p_x = p_x + dx
        p_y = p_y + dy
    }
    coords() : int, int {
        return p_x, p_y
    }
    add(p: Point) : Point {
        // return createPoint(p_x + p.x, p_y + p.y)
        return null;
    }
    
    initPoint(x0: int, y0: int): Point {
        p_x = x0
        p_y = y0
        return this
    }
    
    clone(): Point { return createPoint(p_x, p_y) }
    equals(p: Point): bool { return this == p }
}

createPoint(x: int, y: int): Point {
    return new Point.initPoint(x, y)
}

foo() {

}

main(args:int[][]) {
    p:Point = new Point
    p = createPoint(1, 1)   // function call type 1
    foo()                   // function call type 2
    _,_= new Point.coords()
    _ = new Point.initPoint(1, 2)
    length2:int = length({1,2,3})

}