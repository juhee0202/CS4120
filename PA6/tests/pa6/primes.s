	.text
	.globl	_Igcd_iii
	.align	4
_Igcd_iii:
	enter	$64, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
l0:
	movq	$0, %r13
	cmpq	%rdi, %r13
	jne	l1
	movq	%rsi, %rax
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rbx
	movq	-32(%rbp), %r12
	movq	-40(%rbp), %r13
	movq	-48(%rbp), %r14
	movq	-56(%rbp), %r15
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
	enter	$80, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	$1000, %r13
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
	callq	_Ilargestprime_ii
	movq	%rax, %r13
	movq	72(%rsp), %rax
	movq	64(%rsp), %rcx
	movq	56(%rsp), %rdx
	movq	48(%rsp), %r8
	movq	40(%rsp), %r9
	movq	32(%rsp), %r10
	movq	24(%rsp), %r11
	movq	16(%rsp), %rdi
	movq	8(%rsp), %rsi
	addq	$80, %rsp
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
	callq	_IunparseInt_aii
	movq	%rax, %r13
	movq	72(%rsp), %rax
	movq	64(%rsp), %rcx
	movq	56(%rsp), %rdx
	movq	48(%rsp), %r8
	movq	40(%rsp), %r9
	movq	32(%rsp), %r10
	movq	24(%rsp), %r11
	movq	16(%rsp), %rdi
	movq	8(%rsp), %rsi
	addq	$80, %rsp
	pushq	%r11
	movq	-64(%rbp), %r11
	movq	%r13, %r11
	movq	%r11, -64(%rbp)
	popq	%r11
	movq	$272, %r13
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
	callq	_I_alloc_i
	movq	%rax, %r13
	movq	72(%rsp), %rax
	movq	64(%rsp), %rcx
	movq	56(%rsp), %rdx
	movq	48(%rsp), %r8
	movq	40(%rsp), %r9
	movq	32(%rsp), %r10
	movq	24(%rsp), %r11
	movq	16(%rsp), %rdi
	movq	8(%rsp), %rsi
	addq	$80, %rsp
	movq	%r13, %r13
	movq	$33, (%r13)
	movq	$76, 8(%r13)
	movq	$97, 16(%r13)
	movq	$114, 24(%r13)
	movq	$103, 32(%r13)
	movq	$101, 40(%r13)
	movq	$115, 48(%r13)
	movq	$116, 56(%r13)
	movq	$32, 64(%r13)
	movq	$112, 72(%r13)
	movq	$114, 80(%r13)
	movq	$105, 88(%r13)
	movq	$109, 96(%r13)
	movq	$101, 104(%r13)
	movq	$32, 112(%r13)
	movq	$108, 120(%r13)
	movq	$101, 128(%r13)
	movq	$115, 136(%r13)
	movq	$115, 144(%r13)
	movq	$32, 152(%r13)
	movq	$116, 160(%r13)
	movq	$104, 168(%r13)
	movq	$97, 176(%r13)
	movq	$110, 184(%r13)
	movq	$32, 192(%r13)
	movq	$49, 200(%r13)
	movq	$44, 208(%r13)
	movq	$48, 216(%r13)
	movq	$48, 224(%r13)
	movq	$48, 232(%r13)
	movq	$32, 240(%r13)
	movq	$105, 248(%r13)
	movq	$115, 256(%r13)
	movq	$32, 264(%r13)
	movq	%r13, %r13
	addq	$8, %r13
	movq	%r13, %r13
	movq	-8(%r13), %r14
	pushq	%r11
	movq	-64(%rbp), %r11
	addq	-8(%r11), %r14
	movq	-64(%rbp), %r11
	movq	%r13, %r11
	movq	%r11, -64(%rbp)
	popq	%r11
	movq	%r14, %r14
	movq	%r14, %r12
	addq	$1, %r12
	imulq	$8, %r12
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
	movq	72(%rsp), %rax
	movq	64(%rsp), %rcx
	movq	56(%rsp), %rdx
	movq	48(%rsp), %r8
	movq	40(%rsp), %r9
	movq	32(%rsp), %r10
	movq	24(%rsp), %r11
	movq	16(%rsp), %rdi
	movq	8(%rsp), %rsi
	addq	$80, %rsp
	movq	%r12, %rbx
	movq	%r14, (%rbx)
	movq	%rbx, %r14
	addq	$8, %r14
	movq	%r14, %rbx
	movq	$0, %r14
l16:
	movq	-8(%r13), %r12
	cmpq	%r12, %r14
	jl	l17
	movq	$0, %r15
	movq	-8(%r13), %r14
l19:
	pushq	%r11
	movq	-8(%r11), %r13
	movq	-64(%rbp), %r11
	addq	-8(%r11), %r14
	movq	-64(%rbp), %r11
	movq	%r13, %r11
	movq	%r11, -64(%rbp)
	popq	%r11
	cmpq	%r13, %r15
	jl	l20
	movq	%rbx, %r13
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
	callq	_Iprint_pai
	movq	72(%rsp), %rax
	movq	64(%rsp), %rcx
	movq	56(%rsp), %rdx
	movq	48(%rsp), %r8
	movq	40(%rsp), %r9
	movq	32(%rsp), %r10
	movq	24(%rsp), %r11
	movq	16(%rsp), %rdi
	movq	8(%rsp), %rsi
	addq	$80, %rsp
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rbx
	movq	-32(%rbp), %r12
	movq	-40(%rbp), %r13
	movq	-48(%rbp), %r14
	movq	-56(%rbp), %r15
	leave
	ret
l17:
	movq	(%r13,%r14,8), %r12
	movq	%r12, (%rbx,%r14,8)
	movq	%r14, %r14
	addq	$1, %r14
	movq	%r14, %r14
	jmp	l16
l20:
	movq	%r15, %r13
	addq	%r14, %r13
	movq	%r13, %r12
	imulq	$8, %r12
	movq	%rbx, %r13
	addq	%r12, %r13
	pushq	%r11
	movq	(%r11,%r15,8), %r13
	movq	-8(%r11), %r13
	movq	-64(%rbp), %r11
	addq	-8(%r11), %r14
	movq	-64(%rbp), %r11
	movq	%r13, %r11
	movq	%r11, -64(%rbp)
	popq	%r11
	movq	%r13, (%r13)
	movq	%r15, %r13
	addq	$1, %r13
	movq	%r13, %r15
	jmp	l19

	.globl	_Iisprime_bi
	.align	4
_Iisprime_bi:
	enter	$64, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	%rdi, %r15
	movq	$2, %r14
l6:
	movq	%r14, %r13
	imulq	%r14, %r13
	cmpq	%r15, %r13
	jle	l7
	movq	$1, %rax
label2:
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rbx
	movq	-32(%rbp), %r12
	movq	-40(%rbp), %r13
	movq	-48(%rbp), %r14
	movq	-56(%rbp), %r15
	leave
	ret
l7:
	movq	%r14, %r12
	movq	%r15, %r13
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%r13, %rsi
	movq	%r12, %rdi
	subq	$8, %rsp
	callq	_Igcd_iii
	movq	%rax, %r13
	movq	72(%rsp), %rax
	movq	64(%rsp), %rcx
	movq	56(%rsp), %rdx
	movq	48(%rsp), %r8
	movq	40(%rsp), %r9
	movq	32(%rsp), %r10
	movq	24(%rsp), %r11
	movq	16(%rsp), %rdi
	movq	8(%rsp), %rsi
	addq	$80, %rsp
	movq	$1, %r12
	cmpq	%r13, %r12
	jne	l9
	movq	%r14, %r13
	addq	$1, %r13
	movq	%r13, %r14
	jmp	l6
l9:
	movq	$0, %rax
	jmp	label2

	.globl	_Ilargestprime_ii
	.align	4
_Ilargestprime_ii:
	enter	$64, $0
	pushq	%rdi
	pushq	%rsi
	pushq	%rbx
	pushq	%r12
	pushq	%r13
	pushq	%r14
	pushq	%r15
	movq	%rdi, %r15
	movq	$1, %r12
	movq	$1, %r14
l11:
	cmpq	%r15, %r12
	jl	l12
	movq	%r14, %rax
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rbx
	movq	-32(%rbp), %r12
	movq	-40(%rbp), %r13
	movq	-48(%rbp), %r14
	movq	-56(%rbp), %r15
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
	movq	%rax, %r13
	movq	72(%rsp), %rax
	movq	64(%rsp), %rcx
	movq	56(%rsp), %rdx
	movq	48(%rsp), %r8
	movq	40(%rsp), %r9
	movq	32(%rsp), %r10
	movq	24(%rsp), %r11
	movq	16(%rsp), %rdi
	movq	8(%rsp), %rsi
	addq	$80, %rsp
	testq	%r13, %r13
	jnz	l14
l15:
	movq	%r12, %r13
	addq	$1, %r13
	movq	%r13, %r12
	jmp	l11
l14:
	movq	%r12, %r14
	jmp	l15
