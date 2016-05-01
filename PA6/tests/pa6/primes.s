	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$272, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %rcx
	movq	%rcx, -56(%rbp)
	movq	$16, %rcx
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
	callq	_I_alloc_i
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
	movq	-80(%rbp), %rcx
	movq	$1, (%rcx)
	movq	-80(%rbp), %rcx
	movq	$76, 8(%rcx)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	$16, %rcx
	movq	%rcx, -96(%rbp)
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
	movq	-96(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -104(%rbp)
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
	movq	-104(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	$1, (%rcx)
	movq	-112(%rbp), %rcx
	movq	$105, 8(%rcx)
	movq	-112(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-80(%rbp), %rcx
	movq	-8(%rcx), %r11
	movq	%r11, -128(%rbp)
	movq	-128(%rbp), %rcx
	movq	-112(%rbp), %rcx
	addq	-8(%rcx), %r11
	movq	%r11, -128(%rbp)
	movq	-128(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -160(%rbp)
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
	movq	-160(%rbp), %rdx
	movq	%rdx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rcx
	movq	%rcx, -168(%rbp)
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
	movq	-168(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -176(%rbp)
	movq	-136(%rbp), %r11
	movq	-176(%rbp), %rcx
	movq	%r11, (%rcx)
	movq	-176(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -184(%rbp)
	movq	-184(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -184(%rbp)
	movq	-184(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -176(%rbp)
	movq	$0, %rcx
	movq	%rcx, -192(%rbp)
l0:
	movq	-192(%rbp), %rcx
	movq	-80(%rbp), %rcx
	cmpq	-8(%rcx), %r11
	movq	%r11, -192(%rbp)
	jl	l1
	movq	$0, %rcx
	movq	%rcx, -200(%rbp)
	movq	-80(%rbp), %rcx
	movq	-8(%rcx), %r11
	movq	%r11, -208(%rbp)
l3:
	movq	-200(%rbp), %rcx
	movq	-112(%rbp), %rcx
	cmpq	-8(%rcx), %r11
	movq	%r11, -200(%rbp)
	jl	l4
	movq	-176(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -216(%rbp)
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
	movq	-216(%rbp), %rdx
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
l1:
	movq	-80(%rbp), %rcx
	movq	-192(%rbp), %rdx
	movq	(%rcx,%rdx,8), %r11
	movq	%r11, -224(%rbp)
	movq	-224(%rbp), %r11
	movq	-176(%rbp), %rcx
	movq	-192(%rbp), %rdx
	movq	%r11, (%rcx,%rdx,8)
	movq	-192(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -232(%rbp)
	movq	-232(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -232(%rbp)
	movq	-232(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -192(%rbp)
	jmp	l0
l4:
	movq	-200(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -240(%rbp)
	movq	-240(%rbp), %rcx
	movq	-208(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -240(%rbp)
	movq	-240(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -248(%rbp)
	movq	-248(%rbp), %rcx
	imulq	$8, %rcx
	movq	%rcx, -248(%rbp)
	movq	-176(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -256(%rbp)
	movq	-256(%rbp), %rcx
	movq	-248(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -256(%rbp)
	movq	-112(%rbp), %rcx
	movq	-200(%rbp), %rdx
	movq	(%rcx,%rdx,8), %r11
	movq	%r11, -264(%rbp)
	movq	-264(%rbp), %r11
	movq	-256(%rbp), %rcx
	movq	%r11, (%rcx)
	movq	-200(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -272(%rbp)
	movq	-272(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -200(%rbp)
	jmp	l3
