	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$320, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$32, %rcx
	movq	%rcx, -16(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-16(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -24(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-24(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rcx
	movq	$3, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -40(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-40(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -48(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-48(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	-56(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	$1, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	-56(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	-56(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -80(%rbp)
	movq	-56(%rbp), %rcx
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	$8, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-56(%rbp), %r11
	movq	-88(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -96(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-96(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -104(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-104(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	-112(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	$3, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	-112(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	$4, 0(%rcx)
	movq	-112(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -136(%rbp)
	movq	-112(%rbp), %rcx
	movq	-136(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	$16, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	-112(%rbp), %r11
	movq	-144(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	$24, %rcx
	movq	%rcx, -152(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-152(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -160(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-160(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rcx
	movq	$2, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -176(%rbp)
	movq	-176(%rbp), %rcx
	movq	-168(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -176(%rbp)
	movq	-176(%rbp), %rcx
	movq	$5, 0(%rcx)
	movq	$16, %rcx
	movq	%rcx, -184(%rbp)
	movq	-184(%rbp), %rcx
	movq	-168(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -184(%rbp)
	movq	-184(%rbp), %rcx
	movq	$6, 0(%rcx)
	movq	-168(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -192(%rbp)
	movq	-192(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -192(%rbp)
	movq	-168(%rbp), %rcx
	movq	-192(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
	movq	$24, %rcx
	movq	%rcx, -200(%rbp)
	movq	-200(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -200(%rbp)
	movq	-168(%rbp), %r11
	movq	-200(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -208(%rbp)
	movq	-208(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -208(%rbp)
	movq	-32(%rbp), %rcx
	movq	-208(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -216(%rbp)
	movq	-216(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -224(%rbp)
	movq	-224(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -224(%rbp)
	movq	-224(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -232(%rbp)
	movq	$1, %r9
	cmpq	$0, %r9
	jl	l1
l0:
	movq	$1, %r9
	movq	-232(%rbp), %rdx
	cmpq	%rdx, %r9
	jl	l2
l1:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
l2:
	movq	$8, %rcx
	movq	%rcx, -240(%rbp)
	movq	-240(%rbp), %rcx
	imulq	$1, %rcx
	movq	%rcx, -240(%rbp)
	movq	-216(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rcx
	movq	-240(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -256(%rbp)
	movq	-256(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -256(%rbp)
	movq	-256(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -264(%rbp)
	movq	$3, %r9
	cmpq	$0, %r9
	jl	l4
l3:
	movq	$3, %r9
	movq	-264(%rbp), %rdx
	cmpq	%rdx, %r9
	jl	l5
l4:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
l5:
	movq	$8, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rcx
	imulq	$1, %rcx
	movq	%rcx, -272(%rbp)
	movq	-216(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -280(%rbp)
	movq	-280(%rbp), %rcx
	movq	-272(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -280(%rbp)
	movq	$8, %rcx
	movq	%rcx, -288(%rbp)
	movq	-288(%rbp), %rcx
	imulq	$3, %rcx
	movq	%rcx, -288(%rbp)
	movq	-280(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -296(%rbp)
	movq	-296(%rbp), %rcx
	movq	-288(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -296(%rbp)
	movq	-296(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -304(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-304(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -312(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-312(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -320(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-320(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_Iprintln_pai
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	leave
	ret
