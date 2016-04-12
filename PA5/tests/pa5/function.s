(COMPUNIT
 Program
 (FUNC
  _Imain_paai
  (SEQ
   (MOVE (TEMP args) (TEMP _ARG0))
   (MOVE (TEMP x) (CONST 5))
   (MOVE (TEMP y) (CONST 6))
   (MOVE (TEMP temp0) (((TEMP x) (TEMP y)) (CONST 3)))
   (EXP (CALL (NAME _If_pi) (TEMP temp0)))
   (RETURN)))
 (FUNC _If_pi (SEQ (MOVE (TEMP x) (TEMP _ARG0)) (RETURN))))
FUNC(Imain_paai):
	enter	$0 $0
	movq	%rdi %args
	movq	$5 %x
	movq	$6 %y
	movq	%tileRegister0 %tileRegister1
	addq	$3 %tileRegister1
	movq	%tileRegister1 %temp0
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	%temp0 %rdi
	callq	FUNC(If_pi)
	movq	$48(%rsp) %rax
	movq	$40(%rsp) %rcx
	movq	$32(%rsp) %rdx
	movq	$24(%rsp) %r8
	movq	$16(%rsp) %r9
	movq	$8(%rsp) %r10
	movq	$0(%rsp) %r11
	addq	$56 %rsp
	leave
	ret
FUNC(If_pi):
	enter	$0 $0
	movq	%rdi %x
	leave
	ret
