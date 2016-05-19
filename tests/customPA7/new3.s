	.text
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
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	$56, %r14
	movq	%r14, -144(%rbp)
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
	callq	_I_alloc_i
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
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	$6, (%r12)
	movq	$8, %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	$84, (%r12)
	movq	$16, %r14
	movq	%r14, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	$104, (%r12)
	movq	$24, %r14
	movq	%r14, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	$111, (%r12)
	movq	$32, %r14
	movq	%r14, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	$109, (%r12)
	movq	$40, %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r12
	movq	$97, (%r12)
	movq	$48, %r14
	movq	%r14, -208(%rbp)
	movq	-208(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	-208(%rbp), %r12
	movq	$115, (%r12)
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -216(%rbp)
	movq	-216(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	movq	-136(%rbp), %r12
	movq	%r14, (%r12)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-224(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r14
	addq	$24, %r14
	movq	%r14, -232(%rbp)
	movq	-232(%rbp), %r12
	movq	$8, (%r12)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -240(%rbp)
	movq	-240(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -248(%rbp)
	movq	-248(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -248(%rbp)
	movq	-248(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -256(%rbp)
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -264(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-264(%rbp), %r14
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
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -272(%rbp)
	movq	-272(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -280(%rbp)
	movq	-280(%rbp), %r14
	addq	$24, %r14
	movq	%r14, -280(%rbp)
	movq	-280(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -288(%rbp)
	movq	-288(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -296(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-296(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -304(%rbp)
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
	movq	-304(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -312(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-312(%rbp), %r14
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

	.globl	_I_init_Student
	.align	4
_I_init_Student:
	enter	$80, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Student(%rip)
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
	addq	$2, %r14
	movq	%r14, -56(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, _I_size_Student(%rip)
	movq	$0, %r14
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
	movq	-80(%rbp), %r14
	movq	%r14, _I_vt_Student(%rip)
	jmp	l3

	.globl	_I_init_Person
	.align	4
_I_init_Person:
	enter	$80, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Person(%rip)
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
	movq	$2, _I_size_Person(%rip)
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
	movq	%r14, _I_vt_Person(%rip)
	jmp	l1


	.bss
	.align 8
.globl _I_vt_Student
_I_vt_Student:
	.zero 0
	.text

.section .ctors
	.align 8
	.quad _I_init_Student
	.text

	.bss
	.align 8
.globl _I_vt_Person
_I_vt_Person:
	.zero 0
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
