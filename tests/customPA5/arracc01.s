	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$256, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	$5, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	$8, %r14
	movq	%r14, -88(%rbp)
	movq	-80(%rbp), %r12
	movq	-88(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-96(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -104(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-104(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-64(%rbp), %r14
	movq	-112(%rbp), %r12
	movq	%r14, (%r12)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	$0, %r14
	movq	%r14, -128(%rbp)
l0:
	movq	-128(%rbp), %r12
	movq	-64(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -128(%rbp)
	jl	l1
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	$0, %r14
	movq	%r14, -144(%rbp)
l3:
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -152(%rbp)
	movq	-144(%rbp), %r14
	movq	-152(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -144(%rbp)
	jl	l4
	movq	$0, %r14
	movq	%r14, -144(%rbp)
l9:
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -160(%rbp)
	movq	-144(%rbp), %r14
	movq	-160(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -144(%rbp)
	jl	l10
l11:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l1:
	movq	-112(%rbp), %r12
	movq	-128(%rbp), %r13
	movq	$0, (%r12,%r13,8)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	jmp	l0
l4:
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -192(%rbp)
	movq	-176(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -176(%rbp)
	jl	l7
	movq	-176(%rbp), %r12
	movq	-192(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -176(%rbp)
	jl	l8
l7:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	l11
l8:
	movq	-144(%rbp), %r14
	movq	-136(%rbp), %r12
	movq	-176(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	jmp	l3
l10:
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -224(%rbp)
	movq	-208(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -208(%rbp)
	jl	l13
	movq	-208(%rbp), %r12
	movq	-224(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -208(%rbp)
	jl	l14
l13:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_I_outOfBounds_p
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	jmp	l11
l14:
	movq	-136(%rbp), %r12
	movq	-208(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -232(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-232(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -240(%rbp)
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-240(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -248(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-248(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Iprintln_pai
	addq	$8, %rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -256(%rbp)
	movq	-256(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -256(%rbp)
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	jmp	l9
