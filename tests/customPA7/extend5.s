	.text
	.globl	_I_init_Organism
	.align	4
_I_init_Organism:
	enter	$96, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Organism(%rip)
	je	l3
l4:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l3:
	movq	$2, _I_size_Organism(%rip)
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
	leaq	_Organism_Ievolve_p(%rip), %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	-80(%rbp), %r12
	movq	%r14, (%r12)
	movq	-72(%rbp), %r14
	movq	%r14, _I_vt_Organism(%rip)
	jmp	l4

	.globl	_I_init_Animal
	.align	4
_I_init_Animal:
	enter	$144, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Animal(%rip)
	je	l5
l6:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l5:
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
	callq	_I_init_Organism
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
	movq	_I_size_Organism(%rip), %r14
	movq	%r14, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, _I_size_Animal(%rip)
	movq	$24, %r14
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
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	addq	$0, %r14
	movq	%r14, -96(%rbp)
	leaq	_Organism_Ievolve_p(%rip), %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r14
	movq	-96(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -112(%rbp)
	leaq	_Animal_Isleep_pi(%rip), %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	-112(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r14
	addq	$16, %r14
	movq	%r14, -128(%rbp)
	leaq	_Animal_Iwake_p(%rip), %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	-128(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, _I_vt_Animal(%rip)
	jmp	l6

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$160, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	$64, %r14
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
	movq	$7, (%r12)
	movq	$8, %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	$119, (%r12)
	movq	$16, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	$111, (%r12)
	movq	$24, %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	$114, (%r12)
	movq	$32, %r14
	movq	%r14, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	$107, (%r12)
	movq	$40, %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	$105, (%r12)
	movq	$48, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	$110, (%r12)
	movq	$56, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	$103, (%r12)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, _I_g_everyone_ai(%rip)
	movq	_I_g_everyone_ai(%rip), %r14
	movq	%r14, -152(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-152(%rbp), %r14
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

	.globl	_Animal_Iwake_p
	.align	4
_Animal_Iwake_p:
	enter	$176, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$16, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r12
	movq	(%r12), %r14
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
	callq	_IunparseInt_aii
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
	movq	$48, %r14
	movq	%r14, -96(%rbp)
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
	movq	-112(%rbp), %r12
	movq	$5, (%r12)
	movq	$8, %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	-112(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	$87, (%r12)
	movq	$16, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	-112(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	$79, (%r12)
	movq	$24, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	-112(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	$82, (%r12)
	movq	$32, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	-112(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	$76, (%r12)
	movq	$40, %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	-112(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	$68, (%r12)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -160(%rbp)
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r14
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

	.globl	_Organism_Ievolve_p
	.align	4
_Organism_Ievolve_p:
	enter	$160, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r12
	movq	$10, (%r12)
	movq	$64, %r14
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
	movq	$7, (%r12)
	movq	$8, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	$87, (%r12)
	movq	$16, %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	$79, (%r12)
	movq	$24, %r14
	movq	%r14, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	$80, (%r12)
	movq	$32, %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	$32, (%r12)
	movq	$40, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	$87, (%r12)
	movq	$48, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	$79, (%r12)
	movq	$56, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	$80, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
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

	.globl	_Animal_Isleep_pi
	.align	4
_Animal_Isleep_pi:
	enter	$144, $0
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
	movq	$48, %r14
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
	movq	$5, (%r12)
	movq	$8, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	$72, (%r12)
	movq	$16, %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	$69, (%r12)
	movq	$24, %r14
	movq	%r14, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	$76, (%r12)
	movq	$32, %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	$76, (%r12)
	movq	$40, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	$79, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-144(%rbp), %r14
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

	.globl	_Dog_Ieat_p
	.align	4
_Dog_Ieat_p:
	enter	$176, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	$88, %r14
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
	movq	$10, (%r12)
	movq	$8, %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	$68, (%r12)
	movq	$16, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	$79, (%r12)
	movq	$24, %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	$71, (%r12)
	movq	$32, %r14
	movq	%r14, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	$32, (%r12)
	movq	$40, %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	$73, (%r12)
	movq	$48, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	$83, (%r12)
	movq	$56, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	$32, (%r12)
	movq	$64, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	$69, (%r12)
	movq	$72, %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	$65, (%r12)
	movq	$80, %r14
	movq	%r14, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	$84, (%r12)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
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

	.globl	_Dog_Isleep_pi
	.align	4
_Dog_Isleep_pi:
	enter	$176, $0
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
	movq	$72, %r14
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
	movq	$8, (%r12)
	movq	$8, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	$78, (%r12)
	movq	$16, %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	$79, (%r12)
	movq	$24, %r14
	movq	%r14, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	$32, (%r12)
	movq	$32, %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	$72, (%r12)
	movq	$40, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	$69, (%r12)
	movq	$48, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	$76, (%r12)
	movq	$56, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	$76, (%r12)
	movq	$64, %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	-88(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	$79, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -160(%rbp)
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
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

	.globl	_I_init_Dog
	.align	4
_I_init_Dog:
	enter	$160, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Dog(%rip)
	je	l7
l8:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
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
	callq	_I_init_Animal
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
	movq	_I_size_Animal(%rip), %r14
	movq	%r14, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, _I_size_Dog(%rip)
	movq	$32, %r14
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
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	addq	$0, %r14
	movq	%r14, -96(%rbp)
	leaq	_Organism_Ievolve_p(%rip), %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r14
	movq	-96(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -112(%rbp)
	leaq	_Dog_Isleep_pi(%rip), %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	-112(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r14
	addq	$16, %r14
	movq	%r14, -128(%rbp)
	leaq	_Animal_Iwake_p(%rip), %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	-128(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r14
	addq	$24, %r14
	movq	%r14, -144(%rbp)
	leaq	_Dog_Ieat_p(%rip), %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r14
	movq	-144(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, _I_vt_Dog(%rip)
	jmp	l8

	.globl	_I_init_everyone_ai
	.align	4
_I_init_everyone_ai:
	enter	$160, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	$6, %r14
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
	movq	%r14, _I_g_everyone_ai(%rip)
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


	.section .data
	.align 8
.globl _I_g_x_i
_I_g_x_i:
	.quad 0
	.text

	.bss
	.align 8
.globl _I_size_Dog
_I_size_Dog:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_vt_Dog
_I_vt_Dog:
	.zero 32
	.text

.section .ctors
	.align 8
	.quad _I_init_Dog
	.text

	.bss
	.align 8
.globl _I_size_Animal
_I_size_Animal:
	.zero 8
	.text

	.section .data
	.align 8
.globl _I_g_z_i
_I_g_z_i:
	.quad 0
	.text

	.section .data
	.align 8
.globl _I_g_everyone_ai
_I_g_everyone_ai:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_everyone_ai
	.text

	.bss
	.align 8
.globl _I_vt_Animal
_I_vt_Animal:
	.zero 24
	.text

.section .ctors
	.align 8
	.quad _I_init_Animal
	.text

	.section .data
	.align 8
.globl _I_g_y_i
_I_g_y_i:
	.quad 0
	.text

	.bss
	.align 8
.globl _I_size_Organism
_I_size_Organism:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_vt_Organism
_I_vt_Organism:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_Organism
	.text