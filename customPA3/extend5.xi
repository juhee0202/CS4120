class Super {
    x,y:int
    f():int {
        return x;
    }
}

class Sub {
    z:int
    
    f():int {
        return y
    }

    g(x:int):int {
        return z
    }
}
