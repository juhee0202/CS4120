	.text
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
	leaq	_Animal_Ibark_p(%rip), %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	-80(%rbp), %r12
	movq	%r14, (%r12)
	movq	-72(%rbp), %r14
	movq	%r14, _I_vt_Animal(%rip)
	jmp	l3

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$432, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	_I_size_Dog(%rip), %r14
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
	movq	_I_vt_Dog(%rip), %r14
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
	movq	_I_size_Animal(%rip), %r14
	movq	%r14, -128(%rbp)
	movq	$8, %r14
	movq	%r14, -136(%rbp)
	movq	-128(%rbp), %r12
	movq	-136(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-120(%rbp), %r12
	movq	-144(%rbp), %r13
	leaq	(%r12,%r13), %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	$48, %r14
	movq	%r14, -168(%rbp)
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
	callq	_I_alloc_i
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
	movq	-184(%rbp), %r12
	movq	$5, (%r12)
	movq	$8, %r14
	movq	%r14, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	-184(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r12
	movq	$97, (%r12)
	movq	$16, %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r12
	movq	-184(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r12
	movq	$108, (%r12)
	movq	$24, %r14
	movq	%r14, -208(%rbp)
	movq	-208(%rbp), %r12
	movq	-184(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	-208(%rbp), %r12
	movq	$105, (%r12)
	movq	$32, %r14
	movq	%r14, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	-184(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r12
	movq	$99, (%r12)
	movq	$40, %r14
	movq	%r14, -224(%rbp)
	movq	-224(%rbp), %r12
	movq	-184(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-224(%rbp), %r12
	movq	$101, (%r12)
	movq	-184(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -232(%rbp)
	movq	-232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r14
	movq	-160(%rbp), %r12
	movq	%r14, (%r12)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -240(%rbp)
	movq	-240(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -248(%rbp)
	movq	-248(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -256(%rbp)
	movq	-256(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -256(%rbp)
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -264(%rbp)
	movq	$64, %r14
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
	callq	_I_alloc_i
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
	movq	-288(%rbp), %r12
	movq	$7, (%r12)
	movq	$8, %r14
	movq	%r14, -296(%rbp)
	movq	-296(%rbp), %r12
	movq	-288(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -296(%rbp)
	movq	-296(%rbp), %r12
	movq	$110, (%r12)
	movq	$16, %r14
	movq	%r14, -304(%rbp)
	movq	-304(%rbp), %r12
	movq	-288(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -304(%rbp)
	movq	-304(%rbp), %r12
	movq	$111, (%r12)
	movq	$24, %r14
	movq	%r14, -312(%rbp)
	movq	-312(%rbp), %r12
	movq	-288(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -312(%rbp)
	movq	-312(%rbp), %r12
	movq	$116, (%r12)
	movq	$32, %r14
	movq	%r14, -320(%rbp)
	movq	-320(%rbp), %r12
	movq	-288(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -320(%rbp)
	movq	-320(%rbp), %r12
	movq	$84, (%r12)
	movq	$40, %r14
	movq	%r14, -328(%rbp)
	movq	-328(%rbp), %r12
	movq	-288(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -328(%rbp)
	movq	-328(%rbp), %r12
	movq	$111, (%r12)
	movq	$48, %r14
	movq	%r14, -336(%rbp)
	movq	-336(%rbp), %r12
	movq	-288(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -336(%rbp)
	movq	-336(%rbp), %r12
	movq	$98, (%r12)
	movq	$56, %r14
	movq	%r14, -344(%rbp)
	movq	-344(%rbp), %r12
	movq	-288(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -344(%rbp)
	movq	-344(%rbp), %r12
	movq	$105, (%r12)
	movq	-288(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -352(%rbp)
	movq	-352(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -352(%rbp)
	movq	-352(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -288(%rbp)
	movq	-288(%rbp), %r14
	movq	-264(%rbp), %r12
	movq	%r14, (%r12)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -360(%rbp)
	movq	_I_size_Animal(%rip), %r14
	movq	%r14, -368(%rbp)
	movq	$8, %r14
	movq	%r14, -376(%rbp)
	movq	-368(%rbp), %r12
	movq	-376(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -368(%rbp)
	movq	-368(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -384(%rbp)
	movq	-360(%rbp), %r12
	movq	-384(%rbp), %r13
	movq	(%r12,%r13), %r14
	movq	%r14, -392(%rbp)
	movq	-392(%rbp), %r14
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
	movq	-240(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -408(%rbp)
	movq	-408(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -416(%rbp)
	movq	-416(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -416(%rbp)
	movq	-416(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -424(%rbp)
	movq	-424(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -432(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-432(%rbp), %r14
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

	.globl	_Animal_Ibark_p
	.align	4
_Animal_Ibark_p:
	enter	$208, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	$112, %r14
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
	movq	$13, (%r12)
	movq	$8, %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	$65, (%r12)
	movq	$16, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r12
	movq	$78, (%r12)
	movq	$24, %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	$73, (%r12)
	movq	$32, %r14
	movq	%r14, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	$77, (%r12)
	movq	$40, %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	$65, (%r12)
	movq	$48, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	$76, (%r12)
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
	movq	$66, (%r12)
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
	movq	$82, (%r12)
	movq	$88, %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	$75, (%r12)
	movq	$96, %r14
	movq	%r14, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r12
	movq	$33, (%r12)
	movq	$104, %r14
	movq	%r14, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r12
	movq	$33, (%r12)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-192(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -192(%rbp)
	movq	-192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -200(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-200(%rbp), %r14
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

	.globl	_Dog_Ibark_p
	.align	4
_Dog_Ibark_p:
	enter	$192, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	$96, %r14
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
	movq	$11, (%r12)
	movq	$8, %r14
	movq	%r14, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	$84, (%r12)
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
	movq	$66, (%r12)
	movq	$32, %r14
	movq	%r14, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-112(%rbp), %r12
	movq	$73, (%r12)
	movq	$40, %r14
	movq	%r14, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	$32, (%r12)
	movq	$48, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r12
	movq	$66, (%r12)
	movq	$56, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	$65, (%r12)
	movq	$64, %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r12
	movq	$82, (%r12)
	movq	$72, %r14
	movq	%r14, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r12
	movq	$75, (%r12)
	movq	$80, %r14
	movq	%r14, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r12
	movq	$33, (%r12)
	movq	$88, %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	-80(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r12
	movq	$33, (%r12)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-176(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -176(%rbp)
	movq	-176(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-80(%rbp), %r14
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
	enter	$112, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	cmpq	$0, _I_size_Dog(%rip)
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
	movq	$8, %r14
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
	leaq	_Dog_Ibark_p(%rip), %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r14
	movq	-96(%rbp), %r12
	movq	%r14, (%r12)
	movq	-88(%rbp), %r14
	movq	%r14, _I_vt_Dog(%rip)
	jmp	l1


	.bss
	.align 8
.globl _I_vt_Animal
_I_vt_Animal:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_Animal
	.text

	.bss
	.align 8
.globl _I_size_Dog
_I_size_Dog:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_size_Animal
_I_size_Animal:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_vt_Dog
_I_vt_Dog:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_Dog
	.text
