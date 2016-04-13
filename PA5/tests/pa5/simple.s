(COMPUNIT
 Program
 (FUNC
  _Imain_paai
  (SEQ
   (MOVE (TEMP args) (TEMP _ARG0))
   (MOVE (TEMP temp0) (CONST 24))
   (MOVE (TEMP temp1) (CALL (NAME _I_alloc_i) (TEMP temp0)))
   (MOVE (TEMP t0) (TEMP temp1))
   (MOVE (MEM (TEMP t0)) (CONST 2))
   (MOVE (MEM (ADD (CONST 8) (TEMP t0))) (CONST 1))
   (MOVE (MEM (ADD (CONST 16) (TEMP t0))) (CONST 2))
   (MOVE (TEMP t0) (ADD (TEMP t0) (CONST 8)))
   (MOVE (TEMP x) (TEMP t0))
   (RETURN))))
FUNC(Imain_paai):
<<<<<<< HEAD
	enter	$48 $0
	movq	%RDI %RCX
	movq	%RCX -8(%RBP)
	movq	$24 %RCX
	movq	%RCX -16(%RBP)
=======
	enter	$0 $0
	movq	%RDI %args
	movq	$24 %temp0
>>>>>>> 664e0d5ea514dbb8e138d39c05d0d10d1f4a067d
	pushq	%RAX
	pushq	%RCX
	pushq	%RDX
	pushq	%R8
	pushq	%R9
	pushq	%R10
	pushq	%R11
	movq	%temp0 %RDI
	subq	$8 %rsp
	callq	FUNC(I_alloc_i)
<<<<<<< HEAD
	movq	%RAX %RCX
	movq	%RCX -24(%RBP)
	movq	48(%RSP) %RAX
	movq	40(%RSP) %RCX
	movq	32(%RSP) %RDX
	movq	24(%RSP) %R8
	movq	16(%RSP) %R9
	movq	8(%RSP) %R10
	movq	0(%RSP) %R11
	addq	$56 %RSP
	movq	-24(%RBP) %RDX
	movq	%RDX %RCX
	movq	%RCX -32(%RBP)
	movq	-32(%RBP) %RCX
	movq	$2 0(%RCX)
	movq	-32(%RBP) %RCX
	movq	$1 8(%RCX)
	movq	-32(%RBP) %RCX
	movq	$2 16(%RCX)
	movq	-32(%RBP) %RDX
	movq	%RDX %RCX
	movq	%RCX -40(%RBP)
	movq	-40(%RBP) %RCX
	addq	$8 %RCX
	movq	%RCX -40(%RBP)
	movq	-32(%RBP) %RCX
	movq	-40(%RBP) %RDX
	movq	%RDX %RCX
	movq	%RCX -32(%RBP)
	movq	-32(%RBP) %RDX
	movq	%RDX %RCX
	movq	%RCX -48(%RBP)
=======
	movq	%rax %temp1
	movq	48(%rsp) %RAX
	movq	40(%rsp) %RCX
	movq	32(%rsp) %RDX
	movq	24(%rsp) %R8
	movq	16(%rsp) %R9
	movq	8(%rsp) %R10
	movq	0(%rsp) %R11
	addq	$64 %rsp
	movq	%temp1 %t0
	movq	$2 (%t0)
	movq	$8 %tileRegister0
	addq	%t0 %tileRegister0
	movq	$1 (%tileRegister0)
	movq	$16 %tileRegister1
	addq	%t0 %tileRegister1
	movq	$2 (%tileRegister1)
	movq	%t0 %tileRegister2
	addq	$8 %tileRegister2
	movq	%tileRegister2 %t0
	movq	%t0 %x
>>>>>>> 664e0d5ea514dbb8e138d39c05d0d10d1f4a067d
	leave
	ret
nullNOTHING
