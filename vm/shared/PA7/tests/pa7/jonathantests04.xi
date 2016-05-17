
class Favorite {
}

class Good extends Favorite {
    
}

main(args: int[][]) {
    x: Favorite[5][];
    y: Good[] = {new Good};
    x[0] = y;
}