	.text
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
	movq	$1, 8(%rcx)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	addq	$8, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -96(%rbp)
	movq	$0, %rcx
	movq	%rcx, -104(%rbp)
	movq	-96(%rbp), %rcx
	movq	-8(%rcx), %r11
	movq	%r11, -112(%rbp)
	movq	-104(%rbp), %rcx
	cmpq	$0, %rcx
	movq	%rcx, -104(%rbp)
	jl	l1
	movq	-112(%rbp), %rcx
	movq	-104(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	jl	l2
l1:
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
	callq	_I_outOfBounds_p
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
l2:
	movq	-96(%rbp), %rcx
	movq	-104(%rbp), %rdx
	movq	(%rcx,%rdx,8), %r11
	movq	%r11, -120(%rbp)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
