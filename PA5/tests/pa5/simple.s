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
   (MOVE (MEM ((CONST 8) (TEMP t0))) (CONST 1))
   (MOVE (MEM ((CONST 16) (TEMP t0))) (CONST 2))
   (MOVE (TEMP t0) ((TEMP t0) (CONST 8)))
   (MOVE (TEMP x) (TEMP t0))
   (RETURN))))
FUNC(Imain_paai):
	enter	$64 $0
	movq	%RDI %RCX
	movq	%RCX -8(%RBP)
	movq	$24 %RCX
	movq	%RCX -16(%RBP)
	pushq	%RAX
	pushq	%RCX
	pushq	%RDX
	pushq	%R8
	pushq	%R9
	pushq	%R10
	pushq	%R11
	movq	-16(%RBP) %RDX
	movq	%RDX %RDI
	callq	FUNC(I_alloc_i)
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
	movq	$2 %RCX
	movq	%RCX -32(%RBP)
	movq	$8 %RCX
	movq	%RCX -40(%RBP)
	movq	-40(%RBP) %RCX
	movq	-32(%RBP) %RDX
	addq	%RDX %RCX
	movq	%RCX -40(%RBP)
	movq	-40(%RBP) %RCX
	movq	$1 %RCX
	movq	%RCX -40(%RBP)
	movq	$16 %RCX
	movq	%RCX -48(%RBP)
	movq	-48(%RBP) %RCX
	movq	-32(%RBP) %RDX
	addq	%RDX %RCX
	movq	%RCX -48(%RBP)
	movq	-48(%RBP) %RCX
	movq	$2 %RCX
	movq	%RCX -48(%RBP)
	movq	-32(%RBP) %RDX
	movq	%RDX %RCX
	movq	%RCX -56(%RBP)
	movq	-56(%RBP) %RCX
	addq	$8 %RCX
	movq	%RCX -56(%RBP)
	movq	-32(%RBP) %RCX
	movq	-56(%RBP) %RDX
	movq	%RDX %RCX
	movq	%RCX -32(%RBP)
	movq	-32(%RBP) %RDX
	movq	%RDX %RCX
	movq	%RCX -64(%RBP)
	leave
	ret
