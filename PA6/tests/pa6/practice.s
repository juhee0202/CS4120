	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$96, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	$120, %r14
	movq	%r14, -56(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-56(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -64(%rbp)
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
	movq	-64(%rbp), %r12
	movq	$14, (%r12)
	movq	-64(%rbp), %r12
	movq	$72, 8(%r12)
	movq	-64(%rbp), %r12
	movq	$101, 16(%r12)
	movq	-64(%rbp), %r12
	movq	$108, 24(%r12)
	movq	-64(%rbp), %r12
	movq	$108, 32(%r12)
	movq	-64(%rbp), %r12
	movq	$111, 40(%r12)
	movq	-64(%rbp), %r12
	movq	$44, 48(%r12)
	movq	-64(%rbp), %r12
	movq	$32, 56(%r12)
	movq	-64(%rbp), %r12
	movq	$87, 64(%r12)
	movq	-64(%rbp), %r12
	movq	$111, 72(%r12)
	movq	-64(%rbp), %r12
	movq	$114, 80(%r12)
	movq	-64(%rbp), %r12
	movq	$108, 88(%r12)
	movq	-64(%rbp), %r12
	movq	$100, 96(%r12)
	movq	-64(%rbp), %r12
	movq	$33, 104(%r12)
	movq	-64(%rbp), %r12
	movq	$10, 112(%r12)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-80(%rbp), %r14
	movq	%r14, %rdi
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
	movq	$167, %r14
	movq	%r14, -88(%rbp)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
