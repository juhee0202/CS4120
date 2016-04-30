	.text
	.globl	_Igcd_iii
	.align	4
_Igcd_iii:
	enter	$64, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
l0:
	movq	$0, %rcx
	cmpq	%rdi, %rcx
	jne	l1
	movq	%rsi, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l1:
	cmpq	%rsi, %rdi
	jl	l3
	subq	%rsi, %rdi
l5:
	jmp	l0
l3:
	subq	%rdi, %rsi
	jmp	l5

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$64, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
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
	pushq	%rsp
	movq	%r12, %rdi
	callq	_Ilargestprime_ii
	movq	%rax, %r12
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
	movq	%r12, %rdi
	callq	_IunparseInt_aii
	movq	%rax, %r12
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
	pushq	%r11
	movq	%r12, %r11
	movq	%r11, -56(%rbp)
	popq	%r11
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
	pushq	%rsp
	movq	%r12, %rdi
	callq	_I_alloc_i
	movq	%rax, %r12
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
	movq	%r12, %rbx
	movq	$33, (%rbx)
	movq	$76, 8(%rbx)
	movq	$97, 16(%rbx)
	movq	$114, 24(%rbx)
	movq	$103, 32(%rbx)
	movq	$101, 40(%rbx)
	movq	$115, 48(%rbx)
	movq	$116, 56(%rbx)
	movq	$32, 64(%rbx)
	movq	$112, 72(%rbx)
	movq	$114, 80(%rbx)
	movq	$105, 88(%rbx)
	movq	$109, 96(%rbx)
	movq	$101, 104(%rbx)
	movq	$32, 112(%rbx)
	movq	$108, 120(%rbx)
	movq	$101, 128(%rbx)
	movq	$115, 136(%rbx)
	movq	$115, 144(%rbx)
	movq	$32, 152(%rbx)
	movq	$116, 160(%rbx)
	movq	$104, 168(%rbx)
	movq	$97, 176(%rbx)
	movq	$110, 184(%rbx)
	movq	$32, 192(%rbx)
	movq	$49, 200(%rbx)
	movq	$44, 208(%rbx)
	movq	$48, 216(%rbx)
	movq	$48, 224(%rbx)
	movq	$48, 232(%rbx)
	movq	$32, 240(%rbx)
	movq	$105, 248(%rbx)
	movq	$115, 256(%rbx)
	movq	$32, 264(%rbx)
	movq	%rbx, %r12
	addq	$8, %r12
	movq	%r12, %rbx
	movq	-8(%rbx), %r12
	pushq	%r11
	movq	-56(%rbp), %r11
	addq	-8(%r11), %r12
	movq	%r11, -56(%rbp)
	popq	%r11
	movq	%r12, %r12
	movq	%r12, %r15
	addq	$1, %r15
	imulq	$8, %r15
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
	movq	%r15, %rdi
	callq	_I_alloc_i
	movq	%rax, %r15
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
	movq	%r15, %r14
	movq	%r12, (%r14)
	movq	%r14, %r12
	addq	$8, %r12
	movq	%r12, %r14
	movq	$0, %r12
l16:
	movq	-8(%rbx), %r15
	cmpq	%r15, %r12
	jl	l17
	movq	$0, %r12
	movq	-8(%rbx), %rbx
l19:
	pushq	%r11
	movq	-56(%rbp), %r11
	movq	-8(%r11), %r15
	movq	%r11, -56(%rbp)
	popq	%r11
	cmpq	%r15, %r12
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
	pushq	%rsp
	movq	%r12, %rdi
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
	movq	(%rbx,%r12,8), %r15
	movq	%r15, (%r14,%r12,8)
	movq	%r12, %r12
	addq	$1, %r12
	movq	%r12, %r12
	jmp	l16
l20:
	movq	%r12, %r15
	addq	%rbx, %r15
	movq	%r15, %r15
	imulq	$8, %r15
	movq	%r14, %r13
	addq	%r15, %r13
	pushq	%r11
	movq	-56(%rbp), %r11
	movq	(%r11,%r12,8), %r15
	movq	%r11, -56(%rbp)
	popq	%r11
	movq	%r15, (%r13)
	movq	%r12, %r12
	addq	$1, %r12
	movq	%r12, %r12
	jmp	l19

	.globl	_Iisprime_bi
	.align	4
_Iisprime_bi:
	enter	$64, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r13
	movq	$2, %rbx
l6:
	movq	%rbx, %r12
	imulq	%rbx, %r12
	cmpq	%r13, %r12
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
	movq	%rbx, %r15
	movq	%r13, %r12
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
	movq	%r12, %rsi
	movq	%r15, %rdi
	callq	_Igcd_iii
	movq	%rax, %r12
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
	movq	$1, %r15
	cmpq	%r12, %r15
	jne	l9
	movq	%rbx, %r12
	addq	$1, %r12
	movq	%r12, %rbx
	jmp	l6
l9:
	movq	$0, %rax
	jmp	label2

	.globl	_Ilargestprime_ii
	.align	4
_Ilargestprime_ii:
	enter	$64, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r15
	movq	$1, %rbx
	movq	$1, %r13
l11:
	cmpq	%r15, %rbx
	jl	l12
	movq	%r13, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l12:
	movq	%rbx, %r12
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
	movq	%r12, %rdi
	callq	_Iisprime_bi
	movq	%rax, %r12
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
	testq	%r12, %r12
	jnz	l14
l15:
	movq	%rbx, %r12
	addq	$1, %r12
	movq	%r12, %rbx
	jmp	l11
l14:
	movq	%rbx, %r13
	jmp	l15
