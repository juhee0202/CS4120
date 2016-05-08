	.text
	.globl	_Igcd_iii
	.align	4
_Igcd_iii:
	enter	$48, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
l0:
	cmpq	$0, %r10
	jne	l1
	movq	%rcx, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l1:
	cmpq	%rcx, %r10
	jl	l3
	subq	%rcx, %r10
l5:
	jmp	l0
l3:
	subq	%r10, %rcx
	jmp	l5

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$128, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	pushq	%rcx
	movq	-56(%rbp), %rcx
	popq	%rcx
	movq	$1000, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%r12, %rdi
	subq	$8, %rsp
	callq	_Ilargestprime_ii
	movq	%rax, %r12
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
	pushq	%rcx
	movq	-112(%rbp), %rcx
	pushq	%rcx
	movq	-112(%rbp), %rcx
	movq	%rcx, -112(%rbp)
	popq	%rcx
	movq	%rcx, -112(%rbp)
	popq	%rcx
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%r12, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
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
	pushq	%rcx
	movq	%r12, %rcx
	movq	%rcx, -64(%rbp)
	popq	%rcx
	movq	$272, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%r12, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
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
	pushq	%rcx
	movq	-80(%rbp), %rcx
	pushq	%rcx
	movq	-80(%rbp), %rcx
	movq	%rcx, -80(%rbp)
	popq	%rcx
	movq	%rcx, -80(%rbp)
	popq	%rcx
	movq	$33, (%r12)
	movq	$76, 8(%r12)
	movq	$97, 16(%r12)
	movq	$114, 24(%r12)
	movq	$103, 32(%r12)
	movq	$101, 40(%r12)
	movq	$115, 48(%r12)
	movq	$116, 56(%r12)
	movq	$32, 64(%r12)
	movq	$112, 72(%r12)
	movq	$114, 80(%r12)
	movq	$105, 88(%r12)
	movq	$109, 96(%r12)
	movq	$101, 104(%r12)
	movq	$32, 112(%r12)
	movq	$108, 120(%r12)
	movq	$101, 128(%r12)
	movq	$115, 136(%r12)
	movq	$115, 144(%r12)
	movq	$32, 152(%r12)
	movq	$116, 160(%r12)
	movq	$104, 168(%r12)
	movq	$97, 176(%r12)
	movq	$110, 184(%r12)
	movq	$32, 192(%r12)
	movq	$49, 200(%r12)
	movq	$44, 208(%r12)
	movq	$48, 216(%r12)
	movq	$48, 224(%r12)
	movq	$48, 232(%r12)
	movq	$32, 240(%r12)
	movq	$105, 248(%r12)
	movq	$115, 256(%r12)
	movq	$32, 264(%r12)
	pushq	%rcx
	movq	-80(%rbp), %rcx
	pushq	%rcx
	movq	-80(%rbp), %rcx
	movq	%rcx, -80(%rbp)
	popq	%rcx
	movq	%rcx, -80(%rbp)
	popq	%rcx
	addq	$8, %r12
	movq	-8(%r12), %rbx
	pushq	%rcx
	movq	-64(%rbp), %rcx
	addq	-8(%rcx), %rbx
	popq	%rcx
	movq	%rbx, %r15
	movq	%r15, %rbx
	addq	$1, %rbx
	pushq	%rcx
	movq	-72(%rbp), %rcx
	pushq	%rcx
	movq	-72(%rbp), %rcx
	movq	%rcx, -72(%rbp)
	popq	%rcx
	movq	%rcx, -72(%rbp)
	popq	%rcx
	imulq	$8, %rbx
	pushq	%rcx
	movq	-72(%rbp), %rcx
	pushq	%rcx
	movq	-72(%rbp), %rcx
	movq	%rcx, -72(%rbp)
	popq	%rcx
	movq	%rcx, -72(%rbp)
	popq	%rcx
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%rbx, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %rbx
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
	pushq	%rcx
	movq	-104(%rbp), %rcx
	pushq	%rcx
	movq	-104(%rbp), %rcx
	movq	%rcx, -104(%rbp)
	popq	%rcx
	movq	%rcx, -104(%rbp)
	popq	%rcx
	movq	%r15, (%rbx)
	addq	$8, %rbx
	movq	%rbx, %r14
	movq	$0, %rbx
	movq	%rbx, %r15
l16:
	movq	-8(%r12), %rbx
	cmpq	%rbx, %r15
	jl	l17
	movq	$0, %r12
l19:
	pushq	%rcx
	movq	-64(%rbp), %rcx
	cmpq	-8(%rcx), %r12
	popq	%rcx
	jl	l20
	movq	%r14, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%r12, %rdi
	subq	$8, %rsp
	callq	_Iprint_pai
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
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l17:
	movq	(%r12,%r15,8), %rbx
	movq	%rbx, (%r14,%r15,8)
	movq	%r15, %rbx
	addq	$1, %rbx
	pushq	%rcx
	movq	-88(%rbp), %rcx
	pushq	%rcx
	movq	-88(%rbp), %rcx
	movq	%rcx, -88(%rbp)
	popq	%rcx
	movq	%rcx, -88(%rbp)
	popq	%rcx
	movq	%rbx, %r15
	jmp	l16
l20:
	movq	%r12, %r15
	addq	%rbx, %r15
	imulq	$8, %r15
	movq	%r14, %r13
	addq	%r15, %r13
	pushq	%rcx
	movq	-64(%rbp), %rcx
	movq	(%rcx,%r12,8), %r15
	popq	%rcx
	movq	%r15, (%r13)
	addq	$1, %r12
	pushq	%rcx
	movq	-96(%rbp), %rcx
	pushq	%rcx
	movq	-96(%rbp), %rcx
	movq	%rcx, -96(%rbp)
	popq	%rcx
	movq	%rcx, -96(%rbp)
	popq	%rcx
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
	pushq	%rcx
	movq	-64(%rbp), %rcx
	pushq	%rcx
	movq	%rcx, -88(%rbp)
	popq	%rcx
	popq	%rcx
	movq	$2, %r12
	pushq	%rcx
	movq	-56(%rbp), %rcx
	movq	%rcx, %r13
	popq	%rcx
	movq	%r12, %r14
	pushq	%rcx
	movq	-72(%rbp), %rcx
	movq	%rcx, %r12
	popq	%rcx
	pushq	%rcx
	movq	-80(%rbp), %rcx
	movq	%rcx, %r15
	popq	%rcx
l6:
	movq	%r14, %rbx
	imulq	%r14, %rbx
	pushq	%rcx
	movq	-88(%rbp), %rcx
	cmpq	%rcx, %rbx
	popq	%rcx
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
	movq	%r14, %r12
	pushq	%rcx
	movq	-88(%rbp), %rcx
	movq	%rcx, %rbx
	popq	%rcx
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%rbx, %rsi
	movq	%r12, %rdi
	subq	$8, %rsp
	callq	_Igcd_iii
	movq	%rax, %r13
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
	cmpq	$1, %r13
	jne	l9
	movq	%r14, %r15
	addq	$1, %r15
	pushq	%rcx
	movq	-96(%rbp), %rcx
	pushq	%rcx
	movq	-96(%rbp), %rcx
	movq	%rcx, -96(%rbp)
	popq	%rcx
	movq	%rcx, -96(%rbp)
	popq	%rcx
	movq	%r15, %r14
	movq	%rbx, %r15
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
	pushq	%rcx
	movq	-56(%rbp), %rcx
	movq	%rcx, %rbx
	popq	%rcx
	movq	$1, %r12
	movq	$1, %r15
	pushq	%rcx
	movq	-64(%rbp), %rcx
	popq	%rcx
	pushq	%rcx
	movq	-72(%rbp), %rcx
	popq	%rcx
l11:
	cmpq	%rbx, %r12
	jl	l12
	movq	%r15, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l12:
	movq	%r12, %r13
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%r13, %rdi
	subq	$8, %rsp
	callq	_Iisprime_bi
	movq	%rax, %r14
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
	testq	%r14, %r14
	jnz	l14
l15:
	pushq	%rcx
	movq	-80(%rbp), %rcx
	pushq	%rcx
	movq	-80(%rbp), %rcx
	movq	%rcx, -80(%rbp)
	popq	%rcx
	movq	%rcx, -80(%rbp)
	popq	%rcx
	addq	$1, %r12
	pushq	%rcx
	movq	-88(%rbp), %rcx
	pushq	%rcx
	movq	-88(%rbp), %rcx
	movq	%rcx, -88(%rbp)
	popq	%rcx
	movq	%rcx, -88(%rbp)
	popq	%rcx
	pushq	%rcx
	movq	-80(%rbp), %rcx
	pushq	%rcx
	movq	-80(%rbp), %rcx
	movq	%rcx, -80(%rbp)
	popq	%rcx
	movq	%rcx, -80(%rbp)
	popq	%rcx
	jmp	l11
l14:
	movq	%r12, %r15
	jmp	l15
