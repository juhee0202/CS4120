	.text
	.globl	_Animal__Isleep_pi
	.align	4
_Animal__Isleep_pi:
	enter	$112, $0
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
	movq	$121, (%r12)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
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

	.globl	_I_init_Animal
	.align	4
_I_init_Animal:
	enter	$96, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Animal(%rip)
	je	_l0

	.globl	_l1
	.align	4
_l1:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret

	.globl	_l0
	.align	4
_l0:
	movq	$2, _I_size_Animal(%rip)
	movq	$8, %r14
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
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	addq	$0, %r14
	movq	%r14, -80(%rbp)
	movq	_Animal__Isleep_pi(%rip), %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	-80(%rbp), %r12
	movq	%r14, (%r12)
	movq	-72(%rbp), %r14
	movq	%r14, _I_vt_Animal(%rip)
	jmp	_l1

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$144, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	_I_size_Animal(%rip), %r14
	movq	%r14, -64(%rbp)
	movq	$8, %r14
	movq	%r14, -72(%rbp)
	movq	-64(%rbp), %r12
	movq	-72(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
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
	movq	_I_vt_Animal(%rip), %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r14
	movq	-96(%rbp), %r12
	movq	%r14, (%r12)
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	$10, %r14
	movq	%r14, -120(%rbp)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -144(%rbp)
	movq	$5, %r14
	movq	%r14, -144(%rbp)
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret


	.bss
	.align 8
.globl _I_vt_Animal
_I_vt_Animal:
	.zero 0
	.text

.section .ctors
	.align 8
	.quad _I_init_Animal
	.text

	.bss
	.align 8
.globl _I_size_Animal
_I_size_Animal:
	.zero 8
	.text

	.section .data
	.align 8
.globl _I_g__I_g_y_i
_I_g__I_g_y_i:
	.quad 0
	.text

	.section .data
	.align 8
.globl _I_g__I_g_x_i
_I_g__I_g_x_i:
	.quad 0
	.text

	.section .data
	.align 8
.globl _I_g__I_g_z_i
_I_g__I_g_z_i:
	.quad 0
	.text
