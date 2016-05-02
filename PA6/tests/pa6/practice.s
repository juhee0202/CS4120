	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$80, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	pushq	%r11
	movq	-56(%rbp), %r11
	popq	%r11
	movq	$120, %rbx
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
	pushq	%r11
	movq	-64(%rbp), %r11
	pushq	%r11
	movq	-64(%rbp), %r11
	movq	%r11, -64(%rbp)
	popq	%r11
	movq	%r11, -64(%rbp)
	popq	%r11
	movq	$14, (%rbx)
	movq	$72, 8(%rbx)
	movq	$101, 16(%rbx)
	movq	$108, 24(%rbx)
	movq	$108, 32(%rbx)
	movq	$111, 40(%rbx)
	movq	$44, 48(%rbx)
	movq	$32, 56(%rbx)
	movq	$87, 64(%rbx)
	movq	$111, 72(%rbx)
	movq	$114, 80(%rbx)
	movq	$108, 88(%rbx)
	movq	$100, 96(%rbx)
	movq	$33, 104(%rbx)
	movq	$10, 112(%rbx)
	pushq	%r11
	movq	-64(%rbp), %r11
	pushq	%r11
	movq	-64(%rbp), %r11
	movq	%r11, -64(%rbp)
	popq	%r11
	movq	%r11, -64(%rbp)
	popq	%r11
	addq	$8, %rbx
	pushq	%r11
	movq	-64(%rbp), %r11
	pushq	%r11
	movq	-64(%rbp), %r11
	movq	%r11, -64(%rbp)
	popq	%r11
	movq	%r11, -64(%rbp)
	popq	%r11
	pushq	%r11
	movq	-64(%rbp), %r11
	pushq	%r11
	movq	-64(%rbp), %r11
	movq	%r11, -64(%rbp)
	popq	%r11
	movq	%r11, -64(%rbp)
	popq	%r11
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
	movq	$167, %r11
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
