asser(c:bool) { if (!c) { x:int = 1/0; } }
m(l: int[], ri: int[]): int[] { r: int[] = {} lx: int = 0 rx:
int = 0 while (lx < length(l) & rx < length(ri)) { if (l[lx] <= ri[rx]) { r = r
+ { l[lx] } lx = lx + 1 } else { r = r + { ri[rx] } rx = rx + 1 } } while (rx <
length(ri)) { r = r + { ri[rx] } rx = rx + 1 } while (lx < length(l)) { r = r +
{ l[lx] } lx = lx + 1 } return r } m8(z: int, q: int, w: int, b: int, e:
int, t: int, y: int, u: int): int, int, int, int, int, int, int, int { o:int[]
= m({ z, q, w, b }, { e, t, y, u }) return o[0], o[1], o[2], o[3], o[4],
o[5], o[6], o[7] } m4(z: int, q: int, w: int, b: int): int, int, int, int {
o: int[] = m({ z, q }, { w, b }) return o[3], o[2], o[1], o[0] } s2(a:
int, b: int): int, int { if (a > b) { return b, a } else { return a, b } }
s4(i0: int, i1: int, i2: int, i3: int): int, int, int, int { z: int, q: int
= s2(i0, i1) w: int, b: int = s2(i2, i3) o: int[] = m({ z, q }, { w,
b }) return o[0], o[1], o[2], o[3] } s8(i0: int, i1: int, i2: int, i3: int,
i4: int, i5: int, i6: int, i7: int): int, int, int, int, int, int, int, int {
z: int, q: int, w: int, b: int = s4(i0, i1, i2, i3) e: int, t: int, y: int,
u: int = s4(i4, i5, i6, i7) z, q, w, b, e, t, y, u = m8(z, q, w, b, e,
t, y, u); return z, q, w, b, e, t, y, u } main(a: int[][]) { z: int, q: int, w:
int, b: int, e: int, t: int, y: int, u: int = s8('8', '7', '6', '5', '4',
'3', '2', '1') asser(z=='1'&q=='2'&w=='3'&b=='4'&e =='5'&t=='6'&y=='7'&u=='8')}
// sort8 takes 8 argument and returns 8 values, it sorts them without using the array
// this is a good test for passing arguments and return values on the stack
//use assert m(l: int[], ri: int[]): int[] {
// r: int[] = {}
// lx: int = 0
// rx: int = 0
// while (lx < length(l) & rx < length(ri)) {
//  if (l[lx] <= ri[rx]) {
//   r = r + {
//    l[lx]
//   }
//   lx = lx + 1
//  } else {
//   r = r + {
//    ri[rx]
//   }
//   rx = rx + 1
//  }
// }
// while (rx < length(ri)) {
//  r = r + {
//   ri[rx]
//  }
//  rx = rx + 1
// }
// while (lx < length(l)) {
//  r = r + {
//   l[lx]
//  }
//  lx = lx + 1
// }
// return r
//}
//m8(z: int, q: int, w: int, b: int, e: int, t: int, y: int, u: int):
//int, int, int, int, int, int, int, int {
//  o:int[] = m({ z, q, w, b }, { e, t, y, u })
//  return o[0], o[1], o[2], o[3], o[4], o[5], o[6], o[7]
// }
//m4(z: int, q: int, w: int, b: int): int, int, int, int {
// o: int[] = m({ z, q }, { w, b })
//  return o[3], o[2], o[1], o[0]
//}
//s2(a: int, b: int): int, int {
// if (a > b) {
//  return
//  b, a
// } else {
//  return a, b
// }
//}
//s4(i0: int, i1: int, i2: int, i3: int): int,
// int, int, int {
//  z: int, q: int = s2(i0, i1)
//  w: int, b: int = s2(i2, i3)
//  o: int[] = m({ z, q }, { w, b })
//  return o[0], o[1], o[2], o[3]
// }
//s8(i0: int, i1: int, i2: int, i3: int, i4: int, i5: int, i6: int, i7: int):
// int, int, int, int, int, int, int, int {
//  z: int, q: int, w: int, b: int = s4(i0, i1, i2, i3)
//  e: int, t: int, y: int, u: int = s4(i4, i5, i6, i7)
//  z, q, w, b, e, t, y, u = m8(z, q, w, b, e, t, y, u);
//  return z, q, w, b, e, t, y, u
// }

//main(a: int[][]) {
// z: int,
// q: int,
// w: int,
// b: int,
// e: int,
// t: int,
// y: int,
// u: int = s8('8', '7', '6', '5',
//  '4', '3', '2', '1') assert(z == '1' & q == '2' & w == '3' & b == '4' & e == '5' & t == '6' & y == '7' & u == '8')
//}
