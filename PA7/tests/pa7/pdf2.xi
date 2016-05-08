// A 2D Point with integer coordinates (x,y).
class Point {
	move(dx: int, dy: int)
	add(p: Point): Point
	coords(): int, int
	clone(): Point

	// Initialize this to contain (x,y).
	// Requires: p is uninitialized.
	// Returns: this
	initPoint(p: Point, x: int, y: int): Point
}

// Create the point (x,y).
createPoint(x: int, y: int): Point