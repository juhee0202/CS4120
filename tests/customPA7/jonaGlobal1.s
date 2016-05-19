	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$208, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	_I_g_x_aai(%rip), %r14
	movq	%r14, -64(%rbp)
	movq	$2, %r14
	movq	%r14, -72(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -80(%rbp)
	movq	-80(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -88(%rbp)
	movq	-72(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -72(%rbp)
	jl	l7
	movq	-72(%rbp), %r12
	movq	-88(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -72(%rbp)
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

	.globl	_label0
	.align	4
_label0:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l8:
	movq	-64(%rbp), %r12
	movq	-72(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -96(%rbp)
	movq	$3, %r14
	movq	%r14, -104(%rbp)
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -120(%rbp)
	movq	-104(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -104(%rbp)
	jl	l10
	movq	-104(%rbp), %r12
	movq	-120(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -104(%rbp)
	jl	l11
l10:
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
	jmp	_label0
l11:
	movq	-96(%rbp), %r12
	movq	-104(%rbp), %r13
	movq	$5, (%r12,%r13,8)
	movq	_I_g_x_aai(%rip), %r14
	movq	%r14, -128(%rbp)
	movq	$2, %r14
	movq	%r14, -136(%rbp)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -152(%rbp)
	movq	-136(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -136(%rbp)
	jl	l13
	movq	-136(%rbp), %r12
	movq	-152(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -136(%rbp)
	jl	l14
l13:
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
	jmp	_label0
l14:
	movq	-128(%rbp), %r12
	movq	-136(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -160(%rbp)
	movq	$3, %r14
	movq	%r14, -168(%rbp)
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -184(%rbp)
	movq	-168(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -168(%rbp)
	jl	l16
	movq	-168(%rbp), %r12
	movq	-184(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -168(%rbp)
	jl	l17
l16:
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
	jmp	_label0
l17:
	movq	-160(%rbp), %r12
	movq	-168(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -192(%rbp)
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
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -200(%rbp)
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
	movq	-200(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -208(%rbp)
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
	jmp	_label0

	.globl	_I_init_x_aai
	.align	4
_I_init_x_aai:
	enter	$256, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	$5, %r14
	movq	%r14, -56(%rbp)
	movq	$5, %r14
	movq	%r14, -64(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	$8, %r14
	movq	%r14, -88(%rbp)
	movq	-80(%rbp), %r12
	movq	-88(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-96(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -104(%rbp)
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
	movq	-104(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-56(%rbp), %r14
	movq	-112(%rbp), %r12
	movq	%r14, (%r12)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	$0, %r14
	movq	%r14, -128(%rbp)
l0:
	movq	-128(%rbp), %r12
	movq	-56(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -128(%rbp)
	jl	l1
	movq	-112(%rbp), %r14
	movq	%r14, _I_g_x_aai(%rip)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l1:
	movq	-112(%rbp), %r12
	movq	-128(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	$8, %r14
	movq	%r14, -168(%rbp)
	movq	-160(%rbp), %r12
	movq	-168(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
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
	callq	_I_alloc_i
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
	movq	-64(%rbp), %r14
	movq	-192(%rbp), %r12
	movq	%r14, (%r12)
	movq	-192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	$0, %r14
	movq	%r14, -208(%rbp)
l3:
	movq	-208(%rbp), %r12
	movq	-64(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -208(%rbp)
	jl	l4
	movq	-192(%rbp), %r14
	movq	-144(%rbp), %r12
	movq	%r14, (%r12)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -216(%rbp)
	movq	-216(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	jmp	l0
l4:
	movq	-192(%rbp), %r12
	movq	-208(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -224(%rbp)
	movq	-224(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	$0, %r14
	movq	%r14, -240(%rbp)
	movq	-240(%rbp), %r14
	movq	-232(%rbp), %r12
	movq	%r14, (%r12)
	movq	-208(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -248(%rbp)
	movq	-248(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -248(%rbp)
	movq	-248(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -208(%rbp)
	jmp	l3


	.section .data
	.align 8
.globl _I_g_x_aai
_I_g_x_aai:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_x_aai
	.text
