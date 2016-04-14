	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$424, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$16, %rcx
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
	movq	$1, 0(%rcx)
	movq	$8, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	-32(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	movq	-40(%rbp), %rcx
	movq	$97, 0(%rcx)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -48(%rbp)
	movq	-32(%rbp), %rcx
	movq	-48(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -32(%rbp)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -64(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	subq	$8, %rsp
	callq	_Ifoo_i
	movq	%rax, %rcx
	movq	%rcx, -72(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-72(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-80(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -88(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -96(%rbp)
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %r11
	movq	-96(%rbp), %rcx
	addq	0(%rcx), %r11
	movq	%r11, -104(%rbp)
	movq	-104(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-128(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -136(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-136(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -160(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	subq	$8, %rsp
	callq	_Ifoo_i
	movq	%rax, %rcx
	movq	%rcx, -168(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-168(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -176(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-176(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -184(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -192(%rbp)
	movq	-192(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -192(%rbp)
	movq	-160(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -200(%rbp)
	movq	-200(%rbp), %r11
	movq	-192(%rbp), %rcx
	addq	0(%rcx), %r11
	movq	%r11, -200(%rbp)
	movq	-200(%rbp), %r11
	movq	-144(%rbp), %rcx
	movq	%r11, 0(%rcx)
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -208(%rbp)
	movq	-208(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -208(%rbp)
	movq	-144(%rbp), %rcx
	movq	-208(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	$0, %rcx
	movq	%rcx, -216(%rbp)
l0:
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -224(%rbp)
	movq	-224(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -224(%rbp)
	movq	-224(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -232(%rbp)
	movq	-216(%rbp), %rcx
	movq	-232(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -216(%rbp)
	jl	l1
l2:
	movq	$0, %rcx
	movq	%rcx, -240(%rbp)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -256(%rbp)
l3:
	movq	-240(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -264(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	subq	$8, %rsp
	callq	_Ifoo_i
	movq	%rax, %rcx
	movq	%rcx, -272(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-272(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -280(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-280(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -288(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-288(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -296(%rbp)
	movq	-296(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -296(%rbp)
	movq	-296(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -304(%rbp)
	movq	-264(%rbp), %rcx
	movq	-304(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -264(%rbp)
	jl	l4
l5:
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -312(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-312(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_Iprint_pai
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
l1:
	movq	-216(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -320(%rbp)
	movq	-320(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -320(%rbp)
	movq	-32(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -328(%rbp)
	movq	-328(%rbp), %rcx
	movq	-320(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -328(%rbp)
	movq	-216(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -336(%rbp)
	movq	-336(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -336(%rbp)
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -344(%rbp)
	movq	-344(%rbp), %rcx
	movq	-336(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -344(%rbp)
	movq	-328(%rbp), %rcx
	movq	0(%rcx), %rcx
	movq	-344(%rbp), %rcx
	movq	%rcx, 0(%rcx)
	movq	-216(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -352(%rbp)
	movq	-352(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -352(%rbp)
	movq	-216(%rbp), %rcx
	movq	-352(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -216(%rbp)
	jmp	l0
l4:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	subq	$8, %rsp
	callq	_Ifoo_i
	movq	%rax, %rcx
	movq	%rcx, -360(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-360(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -368(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	movq	-368(%rbp), %rdx
	movq	%rdx, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -376(%rbp)
	movq	48(%rsp), %rax
	movq	40(%rsp), %rcx
	movq	32(%rsp), %rdx
	movq	24(%rsp), %r8
	movq	16(%rsp), %r9
	movq	8(%rsp), %r10
	movq	0(%rsp), %r11
	addq	$64, %rsp
	movq	-240(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -384(%rbp)
	movq	-384(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -384(%rbp)
	movq	-376(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -392(%rbp)
	movq	-392(%rbp), %rcx
	movq	-384(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -392(%rbp)
	movq	-240(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -400(%rbp)
	movq	-400(%rbp), %rcx
	movq	-256(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -400(%rbp)
	movq	-400(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -408(%rbp)
	movq	-408(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -408(%rbp)
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -416(%rbp)
	movq	-416(%rbp), %rcx
	movq	-408(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -416(%rbp)
	movq	-392(%rbp), %rcx
	movq	0(%rcx), %rcx
	movq	-416(%rbp), %rcx
	movq	%rcx, 0(%rcx)
	movq	-240(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -424(%rbp)
	movq	-424(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -424(%rbp)
	movq	-240(%rbp), %rcx
	movq	-424(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -240(%rbp)
	jmp	l3

	.globl	_Ifoo_i
	.align	4
_Ifoo_i:
	enter	$0, $0
	movq	$5, %rax
	leave
	ret
