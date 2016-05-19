	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$400, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	_I_size_Student(%rip), %r14
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
	movq	_I_vt_Student(%rip), %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r14
	movq	-96(%rbp), %r12
	movq	%r14, (%r12)
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	_I_size_Person(%rip), %r14
	movq	%r14, -128(%rbp)
	movq	-120(%rbp), %r12
	movq	-128(%rbp), %r13
	leaq	(%r12,%r13), %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	$56, %r14
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
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -160(%rbp)
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
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	$6, (%r12)
	movq	$8, %r14
	movq	%r14, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	-168(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	$84, (%r12)
	movq	$16, %r14
	movq	%r14, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	-168(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	$104, (%r12)
	movq	$24, %r14
	movq	%r14, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	-168(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	$111, (%r12)
	movq	$32, %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r12
	movq	-168(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r12
	movq	$109, (%r12)
	movq	$40, %r14
	movq	%r14, -208(%rbp)
	movq	-208(%rbp), %r12
	movq	-168(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	-208(%rbp), %r12
	movq	$97, (%r12)
	movq	$48, %r14
	movq	%r14, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	-168(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	$115, (%r12)
	movq	-168(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-224(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -224(%rbp)
	movq	-224(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r14
	movq	-144(%rbp), %r12
	movq	%r14, (%r12)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -240(%rbp)
	movq	_I_size_Person(%rip), %r14
	movq	%r14, -248(%rbp)
	movq	-240(%rbp), %r12
	movq	-248(%rbp), %r13
	movq	(%r12,%r13), %r14
	movq	%r14, -256(%rbp)
	movq	-232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -264(%rbp)
	movq	-264(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -272(%rbp)
	movq	-272(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -272(%rbp)
	movq	-272(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -280(%rbp)
	movq	-280(%rbp), %r12
	movq	-256(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -280(%rbp)
	je	l0
	movq	$32, %r14
	movq	%r14, -288(%rbp)
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
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -296(%rbp)
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
	movq	-296(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -304(%rbp)
	movq	-304(%rbp), %r12
	movq	$3, (%r12)
	movq	$8, %r14
	movq	%r14, -312(%rbp)
	movq	-312(%rbp), %r12
	movq	-304(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -312(%rbp)
	movq	-312(%rbp), %r12
	movq	$121, (%r12)
	movq	$16, %r14
	movq	%r14, -320(%rbp)
	movq	-320(%rbp), %r12
	movq	-304(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -320(%rbp)
	movq	-320(%rbp), %r12
	movq	$101, (%r12)
	movq	$24, %r14
	movq	%r14, -328(%rbp)
	movq	-328(%rbp), %r12
	movq	-304(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -328(%rbp)
	movq	-328(%rbp), %r12
	movq	$115, (%r12)
	movq	-304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -336(%rbp)
	movq	-336(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -336(%rbp)
	movq	-336(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -304(%rbp)
	movq	-304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -344(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-344(%rbp), %r14
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
l2:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l0:
	movq	$24, %r14
	movq	%r14, -352(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-352(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -360(%rbp)
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
	movq	-360(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -368(%rbp)
	movq	-368(%rbp), %r12
	movq	$2, (%r12)
	movq	$8, %r14
	movq	%r14, -376(%rbp)
	movq	-376(%rbp), %r12
	movq	-368(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -376(%rbp)
	movq	-376(%rbp), %r12
	movq	$110, (%r12)
	movq	$16, %r14
	movq	%r14, -384(%rbp)
	movq	-384(%rbp), %r12
	movq	-368(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -384(%rbp)
	movq	-384(%rbp), %r12
	movq	$111, (%r12)
	movq	-368(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -392(%rbp)
	movq	-392(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -392(%rbp)
	movq	-392(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -368(%rbp)
	movq	-368(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -400(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-400(%rbp), %r14
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
	jmp	l2

	.globl	_I_init_Student
	.align	4
_I_init_Student:
	enter	$128, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Student(%rip)
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
	callq	_I_init_Person
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
	movq	_I_size_Person(%rip), %r14
	movq	%r14, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, _I_size_Student(%rip)
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
	leaq	_Person_Iinit_p(%rip), %r14
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
	leaq	_Student_Istudy_p(%rip), %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	-112(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, _I_vt_Student(%rip)
	jmp	l6

	.globl	_Person_Iinit_p
	.align	4
_Person_Iinit_p:
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

	.globl	_I_init_Person
	.align	4
_I_init_Person:
	enter	$96, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Person(%rip)
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
	movq	$2, _I_size_Person(%rip)
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
	leaq	_Person_Iinit_p(%rip), %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	-80(%rbp), %r12
	movq	%r14, (%r12)
	movq	-72(%rbp), %r14
	movq	%r14, _I_vt_Person(%rip)
	jmp	l4

	.globl	_Student_Istudy_p
	.align	4
_Student_Istudy_p:
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
.globl _I_vt_Student
_I_vt_Student:
	.zero 16
	.text

.section .ctors
	.align 8
	.quad _I_init_Student
	.text

	.bss
	.align 8
.globl _I_vt_Person
_I_vt_Person:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_Person
	.text

	.bss
	.align 8
.globl _I_size_Person
_I_size_Person:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_size_Student
_I_size_Student:
	.zero 8
	.text
