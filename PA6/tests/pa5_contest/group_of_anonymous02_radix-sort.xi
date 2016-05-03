w(a:bool){if(!a){x:int=1/0}}
pow(x:int,p:int):int{ i:int=0 a:int=1 while(i < p) {a = a * x;
i=i+1} return a} gd(num:int, base:int, digit_num:int):int{ div:int = pow(base,
digit_num)return (num/div)%base }split(al:int[], base:int, digit_num:int):
int[][]{bs:int[base][]num:int=0while(num<length(al)) { dig:int =
gd(al[num], base, digit_num)bs[dig]=bs[dig] + { al[num] } num = num + 1 }
return bs } m(al:int[][]): int [] { new_list:int[] = {}i:int =0 while (i <
length(al)) { new_list = new_list + al[i] i = i+1 } return new_list
}maxAbs(al:int[]) :int { i:int=1; max:int = al[0] while(i<length(al)){ if
(al[i] < 0) { if (al[i] > max) { max = al[i] } } else { if (-al[i] > max) { max
= al[i] } } i=i+1 } return
max}sbs(al:int[]):int[][]{bs:int[][]={{},{}}i:int=0while(i< length(al)){if
(al[i] < 0) bs[0] = bs[0] + {al[i]} else bs[1] = bs[1] + {al[i]} i=i+1 } return
bs}l10 (x:int) : int{ res:int = -1; while (x!=0) { res = res +1 ; x = x/10; }
return res;}rs(al:int[], base:int) : int[] { p:int = l10(maxAbs(al)) + 1 i:int
=0 new_list:int[]= al while(i < p) { new_list = m(split(new_list, base, i)) i =
i +1 } return m(sbs(new_list))}main(args:int[][]) {i:int[] =
"helloworld"o:int[]=rs(i, 10) w( o[0]=='d'& o[1]=='e'& o[2]=='h'&
o[3]=='l'& o[4]=='l'& o[5]=='l'& o[6]=='o'& o[7]=='o'& o[8]=='r'& o[9]=='w') }

// FOR you sanity
// RADIX SORT
//use assert
//pow(x: int, p: int): int {
// i: int = 0 a: int = 1
// while (i < p) {
//  a = a * x;
//  i = i + 1
// }
// return a
//}
//gd(num: int, base: int, digit_num: int): int {
// div: int =
//  pow(base, digit_num) return (num / div) % base
//}
//split(al: int[], base: int,
// digit_num: int): int[][] {
// bs: int[base][] num: int = 0
// while (num <
//  length(al)) {
//  dig: int = gd(al[num], base, digit_num) bs[dig] =
//   bs[dig] + {
//    al[num]
//   }
//  num = num + 1
// }
// return bs
//}
//m(al: int[][]):
// int[] {
//  new_list: int[] = {}
//  i: int = 0
//  while (i < length(al)) {
//   new_list =
//    new_list + al[i] i = i + 1
//  }
//  return new_list
// }
//maxAbs(al: int[]): int {
// i: int = 1;
// max: int = al[0]
// while (i < length(al)) {
//  if (al[i] < 0) {
//   if (al[i] > max) {
//    max =
//     al[i]
//   }
//  } else {
//   if (-al[i] > max) {
//    max = al[i]
//   }
//  }
//  i = i + 1
// }
// return
// max
//}
//sbs(al: int[]): int[][] {
// bs: int[][] = {
//  {},
//  {}
// }
// i: int = 0
// while (i <
//  length(al)) {
//  if (al[i] < 0) bs[0] = bs[0] + {
//   al[i]
//  } else bs[1] = bs[1] + {
//   al[i]
//  }
//  i = i + 1
// }
// return bs
//}
//l10(x: int): int {
// res: int = -1;
// while (x != 0) {
//  res =
//   res + 1;
//  x = x / 10;
// }
// return res;
//}
//rs(al: int[], base: int): int[] {
// p: int =
//  l10(maxAbs(al)) + 1 i: int = 0 new_list: int[] = al
// while (i < p) {
//  new_list =
//   m(split(new_list, base, i)) i = i + 1
// }
// return
// m(sbs(new_list))
//}
//main(args: int[][]) {
// i: int[] = "helloworld"
// o: int[] = rs(i, 10)
// assert(o[0] == 'd' & o[1] == 'e' & o[2] == 'h' & o[3] == 'l' & o[4] == 'l' & o[5] == 'l' &
//  o[6] == 'o' & o[7] == 'o' & o[8] == 'r' & o[9] == 'w')
//}
