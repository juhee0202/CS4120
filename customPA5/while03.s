	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$192, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	$0, %r14
	movq	%r14, -64(%rbp)
l0:
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_If_b
	movq	%rax, %r12
	movq	%r12, -72(%rbp)
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
	movq	-72(%rbp), %r12
	movq	-72(%rbp), %r14
	testq	%r14, %r12
	movq	%r12, -72(%rbp)
	jnz	l4
l2:
	movq	$40, %r14
	movq	%r14, -80(%rbp)
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
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -88(%rbp)
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
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	$4, (%r12)
	movq	-96(%rbp), %r12
	movq	$100, 8(%r12)
	movq	-96(%rbp), %r12
	movq	$111, 16(%r12)
	movq	-96(%rbp), %r12
	movq	$110, 24(%r12)
	movq	-96(%rbp), %r12
	movq	$101, 32(%r12)
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-112(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Iprintln_pai
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
l4:
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	$7, %r14
	movq	%r14, -128(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-128(%rbp), %r14
	movq	%r14, %rsi
	movq	-120(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Ilt_bii
	movq	%rax, %r12
	movq	%r12, -136(%rbp)
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
	movq	$1, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	-136(%rbp), %r14
	xorq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	-144(%rbp), %r14
	testq	%r14, %r12
	movq	%r12, -144(%rbp)
	jnz	l2
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	subq	$8, %rsp
	callq	_Ig_b
	movq	%rax, %r12
	movq	%r12, -152(%rbp)
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
	movq	$1, %r14
	movq	%r14, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	-152(%rbp), %r14
	xorq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	-160(%rbp), %r14
	testq	%r14, %r12
	movq	%r12, -160(%rbp)
	jnz	l2
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-168(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -176(%rbp)
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
	movq	-176(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-184(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Iprintln_pai
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
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -192(%rbp)
	movq	-192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	jmp	l0

	.globl	_If_b
	.align	4
_If_b:
	enter	$96, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	$88, %r14
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
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r12
	movq	$10, (%r12)
	movq	-72(%rbp), %r12
	movq	$102, 8(%r12)
	movq	-72(%rbp), %r12
	movq	$40, 16(%r12)
	movq	-72(%rbp), %r12
	movq	$41, 24(%r12)
	movq	-72(%rbp), %r12
	movq	$32, 32(%r12)
	movq	-72(%rbp), %r12
	movq	$99, 40(%r12)
	movq	-72(%rbp), %r12
	movq	$97, 48(%r12)
	movq	-72(%rbp), %r12
	movq	$108, 56(%r12)
	movq	-72(%rbp), %r12
	movq	$108, 64(%r12)
	movq	-72(%rbp), %r12
	movq	$101, 72(%r12)
	movq	-72(%rbp), %r12
	movq	$100, 80(%r12)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -80(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-88(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Iprintln_pai
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
	movq	$1, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret

	.globl	_Ilt_bii
	.align	4
_Ilt_bii:
	enter	$224, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	%rsi, %r12
	movq	%r12, -64(%rbp)
	movq	$112, %r14
	movq	%r14, -72(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-72(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -80(%rbp)
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
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	$13, (%r12)
	movq	-88(%rbp), %r12
	movq	$108, 8(%r12)
	movq	-88(%rbp), %r12
	movq	$116, 16(%r12)
	movq	-88(%rbp), %r12
	movq	$40, 24(%r12)
	movq	-88(%rbp), %r12
	movq	$41, 32(%r12)
	movq	-88(%rbp), %r12
	movq	$32, 40(%r12)
	movq	-88(%rbp), %r12
	movq	$99, 48(%r12)
	movq	-88(%rbp), %r12
	movq	$97, 56(%r12)
	movq	-88(%rbp), %r12
	movq	$108, 64(%r12)
	movq	-88(%rbp), %r12
	movq	$108, 72(%r12)
	movq	-88(%rbp), %r12
	movq	$101, 80(%r12)
	movq	-88(%rbp), %r12
	movq	$100, 88(%r12)
	movq	-88(%rbp), %r12
	movq	$58, 96(%r12)
	movq	-88(%rbp), %r12
	movq	$32, 104(%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-104(%rbp), %r14
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
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-112(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -120(%rbp)
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
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-128(%rbp), %r14
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
	movq	$32, %r14
	movq	%r14, -136(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-136(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -144(%rbp)
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
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	$3, (%r12)
	movq	-152(%rbp), %r12
	movq	$32, 8(%r12)
	movq	-152(%rbp), %r12
	movq	$60, 16(%r12)
	movq	-152(%rbp), %r12
	movq	$32, 24(%r12)
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -160(%rbp)
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-168(%rbp), %r14
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
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-176(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -184(%rbp)
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
	movq	-184(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-192(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Iprintln_pai
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
	movq	-56(%rbp), %r12
	movq	-64(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -56(%rbp)
	movq	$1, %r14
	movq	%r14, -200(%rbp)
	movq	$0, %r14
	movq	%r14, -208(%rbp)
	movq	-200(%rbp), %r14
	cmovl	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	-208(%rbp), %r14
	cmovge	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r14
	movq	%r14, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret

	.globl	_Ig_b
	.align	4
_Ig_b:
	enter	$96, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	$88, %r14
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
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r12
	movq	$10, (%r12)
	movq	-72(%rbp), %r12
	movq	$103, 8(%r12)
	movq	-72(%rbp), %r12
	movq	$40, 16(%r12)
	movq	-72(%rbp), %r12
	movq	$41, 24(%r12)
	movq	-72(%rbp), %r12
	movq	$32, 32(%r12)
	movq	-72(%rbp), %r12
	movq	$99, 40(%r12)
	movq	-72(%rbp), %r12
	movq	$97, 48(%r12)
	movq	-72(%rbp), %r12
	movq	$108, 56(%r12)
	movq	-72(%rbp), %r12
	movq	$108, 64(%r12)
	movq	-72(%rbp), %r12
	movq	$101, 72(%r12)
	movq	-72(%rbp), %r12
	movq	$100, 80(%r12)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -80(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-88(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Iprintln_pai
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
	movq	$1, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
