	.text
	.globl	_Ifn_t3iiaiiiiiiiiiii
	.align	4
_Ifn_t3iiaiiiiiiiiiii:
	enter	$240, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rsi, %r12
	movq	%r12, -56(%rbp)
	movq	%rdx, %r12
	movq	%r12, -64(%rbp)
	movq	%rcx, %r12
	movq	%r12, -72(%rbp)
	movq	%r8, %r12
	movq	%r12, -80(%rbp)
	movq	%r9, %r12
	movq	%r12, -88(%rbp)
	movq	24(%rbp), %r14
	movq	%r14, -96(%rbp)
	movq	32(%rbp), %r14
	movq	%r14, -104(%rbp)
	movq	40(%rbp), %r14
	movq	%r14, -112(%rbp)
	movq	48(%rbp), %r14
	movq	%r14, -120(%rbp)
	movq	56(%rbp), %r14
	movq	%r14, -128(%rbp)
	movq	$1, %rax
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	-64(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	-72(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	-96(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	-104(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	-112(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	-120(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r12
	movq	-128(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r14
	movq	%r14, %rdx
	movq	$16, %r14
	movq	%r14, -208(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-208(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -216(%rbp)
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
	movq	-216(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-224(%rbp), %r12
	movq	$1, (%r12)
	movq	-224(%rbp), %r12
	movq	$0, 8(%r12)
	movq	-224(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -232(%rbp)
	movq	-232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-224(%rbp), %r14
	movq	%r14, (%rdi)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$288, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	$1, %r14
	movq	%r14, -64(%rbp)
	movq	$2, %r14
	movq	%r14, -72(%rbp)
	movq	$3, %r14
	movq	%r14, -80(%rbp)
	movq	$4, %r14
	movq	%r14, -88(%rbp)
	movq	$5, %r14
	movq	%r14, -96(%rbp)
	movq	$6, %r14
	movq	%r14, -104(%rbp)
	movq	$7, %r14
	movq	%r14, -112(%rbp)
	movq	$8, %r14
	movq	%r14, -120(%rbp)
	movq	$9, %r14
	movq	%r14, -128(%rbp)
	movq	$10, %r14
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
	subq	$8, %rsp
	movq	%rsp, %rdi
	movq	-136(%rbp), %r14
	pushq	%r14
	movq	-128(%rbp), %r14
	pushq	%r14
	movq	-120(%rbp), %r14
	pushq	%r14
	movq	-112(%rbp), %r14
	pushq	%r14
	movq	-104(%rbp), %r14
	pushq	%r14
	movq	-96(%rbp), %r14
	movq	%r14, %r9
	movq	-88(%rbp), %r14
	movq	%r14, %r8
	movq	-80(%rbp), %r14
	movq	%r14, %rcx
	movq	-72(%rbp), %r14
	movq	%r14, %rdx
	movq	-64(%rbp), %r14
	movq	%r14, %rsi
	subq	$8, %rsp
	callq	_Ifn_t3iiaiiiiiiiiiii
	movq	%rax, %r12
	movq	%r12, -144(%rbp)
	movq	%rdx, %r12
	movq	%r12, -152(%rbp)
	movq	(%rdi), %r14
	movq	%r14, -160(%rbp)
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
	movq	$0, %r14
	movq	%r14, -192(%rbp)
	movq	-160(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -200(%rbp)
	movq	-192(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -192(%rbp)
	jl	l1
	movq	-192(%rbp), %r12
	movq	-200(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -192(%rbp)
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
	subq	$8, %rsp
	callq	_I_outOfBounds_p
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
label1:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l2:
	movq	-160(%rbp), %r12
	movq	-192(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -208(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-208(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -216(%rbp)
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
	movq	-216(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-224(%rbp), %r14
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
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r14
	addq	$-55, %r14
	movq	%r14, -232(%rbp)
	movq	-232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -240(%rbp)
	movq	-160(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -248(%rbp)
	movq	-240(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -240(%rbp)
	jl	l4
	movq	-240(%rbp), %r12
	movq	-248(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -240(%rbp)
	jl	l5
l4:
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
	callq	_I_outOfBounds_p
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
	jmp	label1
l5:
	movq	-152(%rbp), %r14
	movq	-160(%rbp), %r12
	movq	-240(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	$0, %r14
	movq	%r14, -256(%rbp)
	movq	-160(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -264(%rbp)
	movq	-256(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -256(%rbp)
	jl	l7
	movq	-256(%rbp), %r12
	movq	-264(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -256(%rbp)
	jl	l8
l7:
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
	callq	_I_outOfBounds_p
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
	jmp	label1
l8:
	movq	-160(%rbp), %r12
	movq	-256(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -272(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-272(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -280(%rbp)
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
	movq	-280(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -288(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-288(%rbp), %r14
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
	jmp	label1
