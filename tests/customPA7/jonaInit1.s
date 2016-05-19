	.text
	.globl	_I_init_Organism
	.align	4
_I_init_Organism:
	enter	$80, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Organism(%rip)
	je	l0
l1:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l0:
	movq	$1, _I_size_Organism(%rip)
	movq	$0, %r14
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
	movq	%r14, _I_vt_Organism(%rip)
	jmp	l1

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$320, $0
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
	movq	-64(%rbp), %r14
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
	movq	-64(%rbp), %r14
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
l4:
	movq	-128(%rbp), %r12
	movq	-64(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -128(%rbp)
	jl	l5
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	$0, %r14
	movq	%r14, -152(%rbp)
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -168(%rbp)
	movq	-152(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -152(%rbp)
	jl	l8
	movq	-152(%rbp), %r12
	movq	-168(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -152(%rbp)
	jl	l9
l8:
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
l5:
	movq	-112(%rbp), %r12
	movq	-128(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -176(%rbp)
	movq	-176(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	$0, %r14
	movq	%r14, -192(%rbp)
	movq	-192(%rbp), %r14
	movq	-184(%rbp), %r12
	movq	%r14, (%r12)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	jmp	l4
l9:
	movq	-144(%rbp), %r12
	movq	-152(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -208(%rbp)
	movq	-208(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	_I_size_Person(%rip), %r14
	movq	%r14, -224(%rbp)
	movq	$8, %r14
	movq	%r14, -232(%rbp)
	movq	-224(%rbp), %r12
	movq	-232(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-224(%rbp), %r14
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
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -248(%rbp)
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
	movq	-248(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -256(%rbp)
	movq	_I_vt_Person(%rip), %r14
	movq	%r14, -264(%rbp)
	movq	-264(%rbp), %r14
	movq	-256(%rbp), %r12
	movq	%r14, (%r12)
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -272(%rbp)
	movq	-272(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -280(%rbp)
	movq	-280(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -288(%rbp)
	movq	-288(%rbp), %r14
	addq	$0, %r14
	movq	%r14, -288(%rbp)
	movq	-288(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -296(%rbp)
	movq	-272(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -304(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-304(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-296(%rbp), %r14
	callq	*%r14
	movq	%rax, %r12
	movq	%r12, -312(%rbp)
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
	movq	-312(%rbp), %r14
	movq	-216(%rbp), %r12
	movq	%r14, (%r12)
	jmp	_label0

	.globl	_I_init_Person
	.align	4
_I_init_Person:
	enter	$128, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Person(%rip)
	je	l2
l3:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l2:
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
	addq	$0, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, _I_size_Person(%rip)
	movq	$16, %r14
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
	leaq	_Person_Iinit_o6Person(%rip), %r14
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
	leaq	_Person_Ieat_p(%rip), %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	-112(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, _I_vt_Person(%rip)
	jmp	l3

	.globl	_Person_Iinit_o6Person
	.align	4
_Person_Iinit_o6Person:
	enter	$80, $0
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
	movq	-56(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -72(%rbp)
	movq	-72(%rbp), %r14
	addq	$8, %r14
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
	movq	-64(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	movq	-72(%rbp), %r12
	callq	*(%r12)
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
	movq	%r14, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret

	.globl	_Person_Ieat_p
	.align	4
_Person_Ieat_p:
	enter	$64, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
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
.globl _I_size_Person
_I_size_Person:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_vt_Organism
_I_vt_Organism:
	.zero 0
	.text

.section .ctors
	.align 8
	.quad _I_init_Organism
	.text

	.bss
	.align 8
.globl _I_vt_Person
_I_vt_Person:
	.zero 16
	.text

.section .ctors
	.align 8
	.quad _I_init_Person
	.text

	.bss
	.align 8
.globl _I_size_Organism
_I_size_Organism:
	.zero 8
	.text
