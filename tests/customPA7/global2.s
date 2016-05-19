	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$336, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	$16, %r14
	movq	%r14, -64(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-64(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
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
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r12
	movq	$1, (%r12)
	movq	$8, %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	$46, (%r12)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	y(%rip), %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -104(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -112(%rbp)
	movq	-104(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	-112(%rbp), %r12
	addq	(%r12), %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	$8, %r14
	movq	%r14, -152(%rbp)
	movq	-144(%rbp), %r12
	movq	-152(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-160(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -168(%rbp)
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
	movq	-168(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-128(%rbp), %r14
	movq	-176(%rbp), %r12
	movq	%r14, (%r12)
	movq	-176(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -184(%rbp)
	movq	-184(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	$0, %r14
	movq	%r14, -192(%rbp)
l6:
	movq	y(%rip), %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -200(%rbp)
	movq	-192(%rbp), %r14
	movq	-200(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -192(%rbp)
	jl	l7
	movq	$0, %r14
	movq	%r14, -208(%rbp)
	movq	y(%rip), %r14
	movq	%r14, -216(%rbp)
	movq	-216(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -224(%rbp)
l9:
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -232(%rbp)
	movq	-208(%rbp), %r14
	movq	-232(%rbp), %r12
	cmpq	(%r12), %r14
	movq	%r14, -208(%rbp)
	jl	l10
	movq	-176(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -240(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-240(%rbp), %r14
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
	movq	_I_g_z_ai(%rip), %r14
	movq	%r14, -248(%rbp)
	movq	-248(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -248(%rbp)
	movq	-248(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -256(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-256(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -264(%rbp)
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
	movq	-264(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -272(%rbp)
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
l7:
	movq	-192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -280(%rbp)
	movq	$8, %r14
	movq	%r14, -288(%rbp)
	movq	-280(%rbp), %r12
	movq	-288(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -280(%rbp)
	movq	y(%rip), %r14
	movq	%r14, -296(%rbp)
	movq	-296(%rbp), %r12
	movq	-280(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -296(%rbp)
	movq	-296(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -304(%rbp)
	movq	-304(%rbp), %r14
	movq	-176(%rbp), %r12
	movq	-192(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -312(%rbp)
	movq	-312(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -312(%rbp)
	movq	-312(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	jmp	l6
l10:
	movq	-208(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -320(%rbp)
	movq	-320(%rbp), %r12
	movq	-224(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -320(%rbp)
	movq	-80(%rbp), %r12
	movq	-208(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -328(%rbp)
	movq	-328(%rbp), %r14
	movq	-176(%rbp), %r12
	movq	-320(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-208(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -336(%rbp)
	movq	-336(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -336(%rbp)
	movq	-336(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -208(%rbp)
	jmp	l9

	.globl	_I_init_y_ai
	.align	4
_I_init_y_ai:
	enter	$160, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	_I_g_x_i(%rip), %r14
	movq	%r14, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	$8, %r14
	movq	%r14, -80(%rbp)
	movq	-72(%rbp), %r12
	movq	-80(%rbp), %r14
	imulq	%r14, %r12
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
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -96(%rbp)
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
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-56(%rbp), %r14
	movq	-104(%rbp), %r12
	movq	%r14, (%r12)
	movq	-104(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -112(%rbp)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	$0, %r14
	movq	%r14, -120(%rbp)
l0:
	movq	-120(%rbp), %r12
	movq	-56(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -120(%rbp)
	jl	l1
	movq	-104(%rbp), %r14
	movq	%r14, _I_g_y_ai(%rip)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l1:
	movq	-104(%rbp), %r12
	movq	-120(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	$0, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r14
	movq	-136(%rbp), %r12
	movq	%r14, (%r12)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	jmp	l0

	.globl	_I_init_z_ai
	.align	4
_I_init_z_ai:
	enter	$160, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	_I_g_x_i(%rip), %r14
	movq	%r14, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	$8, %r14
	movq	%r14, -80(%rbp)
	movq	-72(%rbp), %r12
	movq	-80(%rbp), %r14
	imulq	%r14, %r12
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
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -96(%rbp)
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
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-56(%rbp), %r14
	movq	-104(%rbp), %r12
	movq	%r14, (%r12)
	movq	-104(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -112(%rbp)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	$0, %r14
	movq	%r14, -120(%rbp)
l3:
	movq	-120(%rbp), %r12
	movq	-56(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -120(%rbp)
	jl	l4
	movq	-104(%rbp), %r14
	movq	%r14, _I_g_z_ai(%rip)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l4:
	movq	-104(%rbp), %r12
	movq	-120(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	$0, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r14
	movq	-136(%rbp), %r12
	movq	%r14, (%r12)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	jmp	l3


	.section .data
	.align 8
.globl _I_g_x_i
_I_g_x_i:
	.quad 5
	.text

	.section .data
	.align 8
.globl _I_g_y_ai
_I_g_y_ai:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_y_ai
	.text

	.section .data
	.align 8
.globl _I_g_z_ai
_I_g_z_ai:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_z_ai
	.text
