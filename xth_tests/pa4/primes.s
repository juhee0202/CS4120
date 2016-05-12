	.text
	.globl	_Igcd_iii
	.align	4
_Igcd_iii:
	enter	$96, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %rcx
	movq	%rcx, -56(%rbp)
	movq	%rsi, %rcx
	movq	%rcx, -64(%rbp)
l0:
	movq	$0, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	-56(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	jne	l1
	movq	-64(%rbp), %rdx
	movq	%rdx, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l1:
	movq	-56(%rbp), %rcx
	movq	-64(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	jl	l3
	movq	-56(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	-64(%rbp), %rdx
	subq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
l5:
	jmp	l0
l3:
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	-56(%rbp), %rdx
	subq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	jmp	l5

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$304, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %rcx
	movq	%rcx, -56(%rbp)
	movq	$1000, %rcx
	movq	%rcx, -64(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-64(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Ilargestprime_ii
	movq	%rax, %rcx
	movq	%rcx, -72(%rbp)
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
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
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-80(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_IunparseInt_aii
	movq	%rax, %rcx
	movq	%rcx, -88(%rbp)
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	$272, %rcx
	movq	%rcx, -104(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-104(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -112(%rbp)
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-112(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	$33, (%rcx)
	movq	-120(%rbp), %rcx
	movq	$76, 8(%rcx)
	movq	-120(%rbp), %rcx
	movq	$97, 16(%rcx)
	movq	-120(%rbp), %rcx
	movq	$114, 24(%rcx)
	movq	-120(%rbp), %rcx
	movq	$103, 32(%rcx)
	movq	-120(%rbp), %rcx
	movq	$101, 40(%rcx)
	movq	-120(%rbp), %rcx
	movq	$115, 48(%rcx)
	movq	-120(%rbp), %rcx
	movq	$116, 56(%rcx)
	movq	-120(%rbp), %rcx
	movq	$32, 64(%rcx)
	movq	-120(%rbp), %rcx
	movq	$112, 72(%rcx)
	movq	-120(%rbp), %rcx
	movq	$114, 80(%rcx)
	movq	-120(%rbp), %rcx
	movq	$105, 88(%rcx)
	movq	-120(%rbp), %rcx
	movq	$109, 96(%rcx)
	movq	-120(%rbp), %rcx
	movq	$101, 104(%rcx)
	movq	-120(%rbp), %rcx
	movq	$32, 112(%rcx)
	movq	-120(%rbp), %rcx
	movq	$108, 120(%rcx)
	movq	-120(%rbp), %rcx
	movq	$101, 128(%rcx)
	movq	-120(%rbp), %rcx
	movq	$115, 136(%rcx)
	movq	-120(%rbp), %rcx
	movq	$115, 144(%rcx)
	movq	-120(%rbp), %rcx
	movq	$32, 152(%rcx)
	movq	-120(%rbp), %rcx
	movq	$116, 160(%rcx)
	movq	-120(%rbp), %rcx
	movq	$104, 168(%rcx)
	movq	-120(%rbp), %rcx
	movq	$97, 176(%rcx)
	movq	-120(%rbp), %rcx
	movq	$110, 184(%rcx)
	movq	-120(%rbp), %rcx
	movq	$32, 192(%rcx)
	movq	-120(%rbp), %rcx
	movq	$49, 200(%rcx)
	movq	-120(%rbp), %rcx
	movq	$44, 208(%rcx)
	movq	-120(%rbp), %rcx
	movq	$48, 216(%rcx)
	movq	-120(%rbp), %rcx
	movq	$48, 224(%rcx)
	movq	-120(%rbp), %rcx
	movq	$48, 232(%rcx)
	movq	-120(%rbp), %rcx
	movq	$32, 240(%rcx)
	movq	-120(%rbp), %rcx
	movq	$105, 248(%rcx)
	movq	-120(%rbp), %rcx
	movq	$115, 256(%rcx)
	movq	-120(%rbp), %rcx
	movq	$32, 264(%rcx)
	movq	-120(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -128(%rbp)
	movq	-128(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	-8(%rcx), %r11
	movq	%r11, -136(%rbp)
	movq	-136(%rbp), %rcx
	movq	-96(%rbp), %rcx
	addq	-8(%rcx), %r11
	movq	%r11, -136(%rbp)
	movq	-136(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -160(%rbp)
	movq	-160(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -160(%rbp)
	movq	-160(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-168(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -176(%rbp)
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-176(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -184(%rbp)
	movq	-144(%rbp), %r11
	movq	-184(%rbp), %rcx
	movq	%r11, (%rcx)
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -192(%rbp)
	movq	-192(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -192(%rbp)
	movq	-192(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -184(%rbp)
	movq	$0, %rcx
	movq	%rcx, -200(%rbp)
l16:
	movq	-120(%rbp), %rcx
	movq	-8(%rcx), %r11
	movq	%r11, -208(%rbp)
	movq	-200(%rbp), %rcx
	movq	-208(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -200(%rbp)
	jl	l17
	movq	$0, %rcx
	movq	%rcx, -216(%rbp)
	movq	-120(%rbp), %rcx
	movq	-8(%rcx), %r11
	movq	%r11, -224(%rbp)
l19:
	movq	-96(%rbp), %rcx
	movq	-8(%rcx), %r11
	movq	%r11, -232(%rbp)
	movq	-216(%rbp), %rcx
	movq	-232(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -216(%rbp)
	jl	l20
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -240(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-240(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Iprint_pai
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l17:
	movq	-120(%rbp), %rcx
	movq	-200(%rbp), %rdx
	movq	(%rcx,%rdx,8), %r11
	movq	%r11, -248(%rbp)
	movq	-248(%rbp), %r11
	movq	-184(%rbp), %rcx
	movq	-200(%rbp), %rdx
	movq	%r11, (%rcx,%rdx,8)
	movq	-200(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -256(%rbp)
	movq	-256(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -256(%rbp)
	movq	-256(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -200(%rbp)
	jmp	l16
l20:
	movq	-216(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -264(%rbp)
	movq	-264(%rbp), %rcx
	movq	-224(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -264(%rbp)
	movq	-264(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -272(%rbp)
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -280(%rbp)
	movq	-280(%rbp), %rcx
	movq	-272(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -280(%rbp)
	movq	-96(%rbp), %rcx
	movq	-216(%rbp), %rdx
	movq	(%rcx,%rdx,8), %r11
	movq	%r11, -288(%rbp)
	movq	-288(%rbp), %r11
	movq	-280(%rbp), %rcx
	movq	%r11, (%rcx)
	movq	-216(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -296(%rbp)
	movq	-296(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -296(%rbp)
	movq	-296(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -216(%rbp)
	jmp	l19

	.globl	_Iisprime_bi
	.align	4
_Iisprime_bi:
	enter	$112, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %rcx
	movq	%rcx, -56(%rbp)
	movq	$2, %rcx
	movq	%rcx, -64(%rbp)
l6:
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	-64(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	-56(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	jle	l7
	movq	$1, %rax
label2:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l7:
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-56(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-88(%rbp), %rdx
	movq	%rdx, %rsi
	movq	-80(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Igcd_iii
	movq	%rax, %rcx
	movq	%rcx, -96(%rbp)
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	$1, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	movq	-96(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	jne	l9
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	jmp	l6
l9:
	movq	$0, %rax
	jmp	label2

	.globl	_Ilargestprime_ii
	.align	4
_Ilargestprime_ii:
	enter	$96, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %rcx
	movq	%rcx, -56(%rbp)
	movq	$1, %rcx
	movq	%rcx, -64(%rbp)
	movq	$1, %rcx
	movq	%rcx, -72(%rbp)
l11:
	movq	-64(%rbp), %rcx
	movq	-56(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	jl	l12
	movq	-72(%rbp), %rdx
	movq	%rdx, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l12:
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	-80(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_Iisprime_bi
	movq	%rax, %rcx
	movq	%rcx, -88(%rbp)
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	-88(%rbp), %rcx
	movq	-88(%rbp), %rdx
	testq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	jnz	l14
l15:
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -96(%rbp)
	movq	-96(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	jmp	l11
l14:
	movq	-64(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	jmp	l15
