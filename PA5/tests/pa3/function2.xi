// tests function mutual reference

foo() {
  bar:int = 3;
}

bar() {
  a:int = 4
}