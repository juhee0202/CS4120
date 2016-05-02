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
	cmpq	%rsi, %r10
	jl	l3
	subq	%rsi, %r10
l5:
	jmp	l0
l3:
	subq	%r10, %rsi
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
	pushq	%rsi
	movq	-56(%rbp), %rsi
	popq	%rsi
	movq	$1000, %rbx
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
	movq	%rbx, %rdi
	callq	_Ilargestprime_ii
	movq	%rax, %rbx
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
	pushq	%rsi
	movq	-88(%rbp), %rsi
	pushq	%rsi
	movq	-88(%rbp), %rsi
	movq	%rsi, -88(%rbp)
	popq	%rsi
	movq	%rsi, -88(%rbp)
	popq	%rsi
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
	movq	%rbx, %rdi
	callq	_IunparseInt_aii
	movq	%rax, %rbx
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
	pushq	%rsi
	movq	%rbx, %rsi
	movq	%rsi, -64(%rbp)
	popq	%rsi
	movq	$272, %rbx
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
	movq	%rbx, %rdi
	callq	_I_alloc_i
	movq	%rax, %rbx
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
	pushq	%rsi
	movq	-80(%rbp), %rsi
	pushq	%rsi
	movq	-80(%rbp), %rsi
	movq	%rsi, -80(%rbp)
	popq	%rsi
	movq	%rsi, -80(%rbp)
	popq	%rsi
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
	pushq	%rsi
	movq	-80(%rbp), %rsi
	pushq	%rsi
	movq	-80(%rbp), %rsi
	movq	%rsi, -80(%rbp)
	popq	%rsi
	movq	%rsi, -80(%rbp)
	popq	%rsi
	addq	$8, %rbx
	movq	%rbx, %r14
	movq	-8(%r14), %rbx
	pushq	%rsi
	movq	-64(%rbp), %rsi
	addq	-8(%rsi), %rbx
	popq	%rsi
	movq	%rbx, %r13
	addq	$1, %r13
	pushq	%rsi
	movq	-72(%rbp), %rsi
	pushq	%rsi
	movq	-72(%rbp), %rsi
	movq	%rsi, -72(%rbp)
	popq	%rsi
	movq	%rsi, -72(%rbp)
	popq	%rsi
	imulq	$8, %r13
	pushq	%rsi
	movq	-72(%rbp), %rsi
	pushq	%rsi
	movq	-72(%rbp), %rsi
	movq	%rsi, -72(%rbp)
	popq	%rsi
	movq	%rsi, -72(%rbp)
	popq	%rsi
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
	movq	%r13, %rdi
	callq	_I_alloc_i
	movq	%rax, %r13
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
	pushq	%rsi
	movq	-96(%rbp), %rsi
	pushq	%rsi
	movq	-96(%rbp), %rsi
	movq	%rsi, -96(%rbp)
	popq	%rsi
	movq	%rsi, -96(%rbp)
	popq	%rsi
	movq	%rbx, (%r13)
	movq	%r13, %rbx
	addq	$8, %rbx
	movq	%rbx, %r12
	movq	$0, %rbx
	movq	%rbx, %r13
l16:
	cmpq	-8(%r14), %r13
	jl	l17
	movq	$0, %rbx
	movq	-8(%r14), %r15
l19:
	pushq	%rsi
	movq	-64(%rbp), %rsi
	cmpq	-8(%rsi), %rbx
	popq	%rsi
	jl	l20
	movq	%r12, %rbx
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
	movq	%rbx, %rdi
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
	movq	(%r14,%r13,8), %rbx
	movq	%rbx, (%r12,%r13,8)
	movq	%r13, %rbx
	addq	$1, %rbx
	pushq	%rsi
	movq	-112(%rbp), %rsi
	pushq	%rsi
	movq	-112(%rbp), %rsi
	movq	%rsi, -112(%rbp)
	popq	%rsi
	movq	%rsi, -112(%rbp)
	popq	%rsi
	movq	%rbx, %r13
	jmp	l16
l20:
	movq	%rbx, %r14
	addq	%r15, %r14
	movq	%r14, %r13
	imulq	$8, %r13
	movq	%r12, %r14
	addq	%r13, %r14
	pushq	%rsi
	movq	-64(%rbp), %rsi
	movq	(%rsi,%rbx,8), %r13
	popq	%rsi
	movq	%r13, (%r14)
	addq	$1, %rbx
	pushq	%rsi
	movq	-104(%rbp), %rsi
	pushq	%rsi
	movq	-104(%rbp), %rsi
	movq	%rsi, -104(%rbp)
	popq	%rsi
	movq	%rsi, -104(%rbp)
	popq	%rsi
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
	pushq	%rsi
	movq	-64(%rbp), %rsi
	pushq	%rsi
	movq	%rsi, -88(%rbp)
	popq	%rsi
	popq	%rsi
	movq	$2, %rbx
	pushq	%rsi
	movq	-72(%rbp), %rsi
	movq	%rsi, %r15
	popq	%rsi
	pushq	%rsi
	movq	-56(%rbp), %rsi
	movq	%rsi, %r14
	popq	%rsi
	movq	%rbx, %r12
	pushq	%rsi
	movq	-80(%rbp), %rsi
	movq	%rsi, %r13
	popq	%rsi
l6:
	movq	%r12, %rbx
	imulq	%r12, %rbx
	pushq	%rsi
	movq	-88(%rbp), %rsi
	cmpq	%rsi, %rbx
	popq	%rsi
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
	movq	%r12, %r13
	pushq	%rsi
	movq	-88(%rbp), %rsi
	movq	%rsi, %r15
	popq	%rsi
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
	movq	%r13, %rdi
	callq	_Igcd_iii
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
	cmpq	$1, %r14
	jne	l9
	movq	%r12, %rbx
	addq	$1, %rbx
	pushq	%rsi
	movq	-96(%rbp), %rsi
	pushq	%rsi
	movq	-96(%rbp), %rsi
	movq	%rsi, -96(%rbp)
	popq	%rsi
	movq	%rsi, -96(%rbp)
	popq	%rsi
	movq	%rbx, %r12
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
	pushq	%rsi
	movq	-56(%rbp), %rsi
	movq	%rsi, %r12
	popq	%rsi
	movq	$1, %rbx
	movq	$1, %r14
	pushq	%rsi
	movq	-64(%rbp), %rsi
	popq	%rsi
	movq	%r14, %r13
	pushq	%rsi
	movq	-72(%rbp), %rsi
	popq	%rsi
l11:
	cmpq	%r12, %rbx
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
	callq	_Iisprime_bi
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
	testq	%r14, %r14
	jnz	l14
l15:
	pushq	%rsi
	movq	-80(%rbp), %rsi
	pushq	%rsi
	movq	-80(%rbp), %rsi
	movq	%rsi, -80(%rbp)
	popq	%rsi
	movq	%rsi, -80(%rbp)
	popq	%rsi
	addq	$1, %rbx
	pushq	%rsi
	movq	-80(%rbp), %rsi
	pushq	%rsi
	movq	-80(%rbp), %rsi
	movq	%rsi, -80(%rbp)
	popq	%rsi
	movq	%rsi, -80(%rbp)
	popq	%rsi
	pushq	%rsi
	movq	-80(%rbp), %rsi
	pushq	%rsi
	movq	-80(%rbp), %rsi
	movq	%rsi, -80(%rbp)
	popq	%rsi
	movq	%rsi, -80(%rbp)
	popq	%rsi
	jmp	l11
l14:
	movq	%rbx, %r13
	jmp	l15
