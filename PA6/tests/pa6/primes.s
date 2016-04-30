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
	movq	$0, %r8
	cmpq	%rdi, %r8
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
	movq	$1000, %r15
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
	callq	_Ilargestprime_ii
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
	callq	_IunparseInt_aii
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
	pushq	%r8
	movq	%r15, %r8
	movq	%r8, -56(%rbp)
	popq	%r8
	movq	$272, %r15
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
	movq	%r15, %r15
	movq	$33, (%r15)
	movq	$76, 8(%r15)
	movq	$97, 16(%r15)
	movq	$114, 24(%r15)
	movq	$103, 32(%r15)
	movq	$101, 40(%r15)
	movq	$115, 48(%r15)
	movq	$116, 56(%r15)
	movq	$32, 64(%r15)
	movq	$112, 72(%r15)
	movq	$114, 80(%r15)
	movq	$105, 88(%r15)
	movq	$109, 96(%r15)
	movq	$101, 104(%r15)
	movq	$32, 112(%r15)
	movq	$108, 120(%r15)
	movq	$101, 128(%r15)
	movq	$115, 136(%r15)
	movq	$115, 144(%r15)
	movq	$32, 152(%r15)
	movq	$116, 160(%r15)
	movq	$104, 168(%r15)
	movq	$97, 176(%r15)
	movq	$110, 184(%r15)
	movq	$32, 192(%r15)
	movq	$49, 200(%r15)
	movq	$44, 208(%r15)
	movq	$48, 216(%r15)
	movq	$48, 224(%r15)
	movq	$48, 232(%r15)
	movq	$32, 240(%r15)
	movq	$105, 248(%r15)
	movq	$115, 256(%r15)
	movq	$32, 264(%r15)
	movq	%r15, %r15
	addq	$8, %r15
	movq	%r15, %r15
	movq	-8(%r15), %r13
	pushq	%r8
	movq	-56(%rbp), %r8
	addq	-8(%r8), %r13
	movq	%r8, -56(%rbp)
	popq	%r8
	movq	%r13, %r13
	movq	%r13, %r14
	addq	$1, %r14
	imulq	$8, %r14
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
	movq	%r14, %rdi
	callq	_I_alloc_i
	movq	%rax, %r14
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
	movq	%r14, %rbx
	movq	%r13, (%rbx)
	movq	%rbx, %r13
	addq	$8, %r13
	movq	%r13, %rbx
	movq	$0, %r13
l16:
	movq	-8(%r15), %r14
	cmpq	%r14, %r13
	jl	l17
	movq	$0, %r12
	movq	-8(%r15), %r15
l19:
	pushq	%r8
	movq	-56(%rbp), %r8
	movq	-8(%r8), %r13
	movq	%r8, -56(%rbp)
	popq	%r8
	cmpq	%r13, %r12
	jl	l20
	movq	%rbx, %r15
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
	movq	(%r15,%r13,8), %r14
	movq	%r14, (%rbx,%r13,8)
	movq	%r13, %r13
	addq	$1, %r13
	movq	%r13, %r13
	jmp	l16
l20:
	movq	%r12, %r13
	addq	%r15, %r13
	movq	%r13, %r14
	imulq	$8, %r14
	movq	%rbx, %r13
	addq	%r14, %r13
	pushq	%r8
	movq	-56(%rbp), %r8
	movq	(%r8,%r12,8), %r14
	movq	%r8, -56(%rbp)
	popq	%r8
	movq	%r14, (%r13)
	movq	%r12, %r13
	addq	$1, %r13
	movq	%r13, %r12
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
	movq	%rdi, %r12
	movq	$2, %r13
l6:
	movq	%r13, %r15
	imulq	%r13, %r15
	cmpq	%r12, %r15
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
	movq	%r13, %r14
	movq	%r12, %r15
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
	movq	%r15, %rsi
	movq	%r14, %rdi
	callq	_Igcd_iii
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
	movq	$1, %r14
	cmpq	%r15, %r14
	jne	l9
	movq	%r13, %r15
	addq	$1, %r15
	movq	%r15, %r13
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
	movq	%rdi, %r12
	movq	$1, %r14
	movq	$1, %r13
l11:
	cmpq	%r12, %r14
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
	movq	%r14, %r15
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
	callq	_Iisprime_bi
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
	testq	%r15, %r15
	jnz	l14
l15:
	movq	%r14, %r15
	addq	$1, %r15
	movq	%r15, %r14
	jmp	l11
l14:
	movq	%r14, %r13
	jmp	l15
