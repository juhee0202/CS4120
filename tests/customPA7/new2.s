	.text
	.globl	_I_init_Animal
	.align	4
_I_init_Animal:
	enter	$80, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Animal(%rip)
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
	movq	$4, _I_size_Animal(%rip)
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
	movq	%r14, _I_vt_Animal(%rip)
	jmp	l4

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
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r14
	addq	$24, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	-136(%rbp), %r14
	testq	%r14, %r12
	movq	%r12, -136(%rbp)
	jnz	l0
	movq	$104, %r14
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
	movq	$12, (%r12)
	movq	$8, %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	$73, (%r12)
	movq	$16, %r14
	movq	%r14, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	$39, (%r12)
	movq	$24, %r14
	movq	%r14, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	$109, (%r12)
	movq	$32, %r14
	movq	%r14, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	$32, (%r12)
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
	movq	$32, (%r12)
	movq	$56, %r14
	movq	%r14, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	$102, (%r12)
	movq	$64, %r14
	movq	%r14, -224(%rbp)
	movq	-224(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-224(%rbp), %r12
	movq	$101, (%r12)
	movq	$72, %r14
	movq	%r14, -232(%rbp)
	movq	-232(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r12
	movq	$109, (%r12)
	movq	$80, %r14
	movq	%r14, -240(%rbp)
	movq	-240(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -240(%rbp)
	movq	-240(%rbp), %r12
	movq	$97, (%r12)
	movq	$88, %r14
	movq	%r14, -248(%rbp)
	movq	-248(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -248(%rbp)
	movq	-248(%rbp), %r12
	movq	$108, (%r12)
	movq	$96, %r14
	movq	%r14, -256(%rbp)
	movq	-256(%rbp), %r12
	movq	-160(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -256(%rbp)
	movq	-256(%rbp), %r12
	movq	$101, (%r12)
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -264(%rbp)
	movq	-264(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -264(%rbp)
	movq	-264(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
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
	movq	$88, %r14
	movq	%r14, -280(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-280(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -288(%rbp)
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
	movq	-288(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -296(%rbp)
	movq	-296(%rbp), %r12
	movq	$10, (%r12)
	movq	$8, %r14
	movq	%r14, -304(%rbp)
	movq	-304(%rbp), %r12
	movq	-296(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -304(%rbp)
	movq	-304(%rbp), %r12
	movq	$73, (%r12)
	movq	$16, %r14
	movq	%r14, -312(%rbp)
	movq	-312(%rbp), %r12
	movq	-296(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -312(%rbp)
	movq	-312(%rbp), %r12
	movq	$39, (%r12)
	movq	$24, %r14
	movq	%r14, -320(%rbp)
	movq	-320(%rbp), %r12
	movq	-296(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -320(%rbp)
	movq	-320(%rbp), %r12
	movq	$109, (%r12)
	movq	$32, %r14
	movq	%r14, -328(%rbp)
	movq	-328(%rbp), %r12
	movq	-296(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -328(%rbp)
	movq	-328(%rbp), %r12
	movq	$32, (%r12)
	movq	$40, %r14
	movq	%r14, -336(%rbp)
	movq	-336(%rbp), %r12
	movq	-296(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -336(%rbp)
	movq	-336(%rbp), %r12
	movq	$97, (%r12)
	movq	$48, %r14
	movq	%r14, -344(%rbp)
	movq	-344(%rbp), %r12
	movq	-296(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -344(%rbp)
	movq	-344(%rbp), %r12
	movq	$32, (%r12)
	movq	$56, %r14
	movq	%r14, -352(%rbp)
	movq	-352(%rbp), %r12
	movq	-296(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -352(%rbp)
	movq	-352(%rbp), %r12
	movq	$109, (%r12)
	movq	$64, %r14
	movq	%r14, -360(%rbp)
	movq	-360(%rbp), %r12
	movq	-296(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -360(%rbp)
	movq	-360(%rbp), %r12
	movq	$97, (%r12)
	movq	$72, %r14
	movq	%r14, -368(%rbp)
	movq	-368(%rbp), %r12
	movq	-296(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -368(%rbp)
	movq	-368(%rbp), %r12
	movq	$108, (%r12)
	movq	$80, %r14
	movq	%r14, -376(%rbp)
	movq	-376(%rbp), %r12
	movq	-296(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -376(%rbp)
	movq	-376(%rbp), %r12
	movq	$101, (%r12)
	movq	-296(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -384(%rbp)
	movq	-384(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -384(%rbp)
	movq	-384(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -296(%rbp)
	movq	-296(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -392(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-392(%rbp), %r14
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
