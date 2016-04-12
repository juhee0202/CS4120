(COMPUNIT
 Program
 (FUNC
  _Imain_paai
  (SEQ
   (MOVE (TEMP args) (TEMP _ARG0))
   (MOVE (TEMP x) (CONST 5))
   (MOVE (TEMP y) ((TEMP x) (CONST 5)))
   (RETURN))))
FUNC(Imain_paai):
	enter	$32 $0
	movq	%RDI %RCX
	movq	%RCX $-8(%RBP)
	movq	$5 %RCX
	movq	%RCX $-16(%RBP)
	movq	$-16(%RBP) %RDX
	movq	%RDX %RCX
	movq	%RCX $-24(%RBP)
	movq	$-24(%RBP) %RCX
	addq	$5 %RCX
	movq	%RCX $-24(%RBP)
	movq	$-24(%RBP) %RDX
	movq	%RDX %RCX
	movq	%RCX $-32(%RBP)
	leave
	ret
FUNC(Imain_paai):
	enter	$32 $0
	movq	%RDI %RCX
	movq	%RCX $-8(%RBP)
	movq	$5 %RCX
	movq	%RCX $-16(%RBP)
	movq	$-16(%RBP) %RDX
	movq	%RDX %RCX
	movq	%RCX $-24(%RBP)
	movq	$-24(%RBP) %RCX
	addq	$5 %RCX
	movq	%RCX $-24(%RBP)
	movq	$-24(%RBP) %RDX
	movq	%RDX %RCX
	movq	%RCX $-32(%RBP)
	leave
	ret
