	.text
	.globl	_Igcd_iii
	.align	4
_Igcd_iii:
	enter	$128, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%r14, %r12
	movq	%r12, -56(%rbp)
	movq	%r14, %r12
	movq	%r12, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
l0:
	movq	-80(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -80(%rbp)
	jne	l1
	movq	-72(%rbp), %r14
	movq	%r14, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l1:
	movq	-80(%rbp), %r12
	movq	-72(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -80(%rbp)
	jl	l3
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r12
	movq	-72(%rbp), %r14
	subq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
l5:
	movq	-104(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	jmp	l0
l3:
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	-80(%rbp), %r14
	subq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	jmp	l5

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
	movq	%r14, %r12
	movq	%r12, -56(%rbp)
	movq	$1000, %r14
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
	callq	_Ilargestprime_ii
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
	callq	_IunparseInt_aii
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
	movq	$272, %r14
	movq	%r14, -104(%rbp)
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
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -112(%rbp)
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
	movq	%r12, -120(%rbp)
	movq	-120(%rbp), %r12
	movq	$33, (%r12)
	movq	-120(%rbp), %r12
	movq	$76, 8(%r12)
	movq	-120(%rbp), %r12
	movq	$97, 16(%r12)
	movq	-120(%rbp), %r12
	movq	$114, 24(%r12)
	movq	-120(%rbp), %r12
	movq	$103, 32(%r12)
	movq	-120(%rbp), %r12
	movq	$101, 40(%r12)
	movq	-120(%rbp), %r12
	movq	$115, 48(%r12)
	movq	-120(%rbp), %r12
	movq	$116, 56(%r12)
	movq	-120(%rbp), %r12
	movq	$32, 64(%r12)
	movq	-120(%rbp), %r12
	movq	$112, 72(%r12)
	movq	-120(%rbp), %r12
	movq	$114, 80(%r12)
	movq	-120(%rbp), %r12
	movq	$105, 88(%r12)
	movq	-120(%rbp), %r12
	movq	$109, 96(%r12)
	movq	-120(%rbp), %r12
	movq	$101, 104(%r12)
	movq	-120(%rbp), %r12
	movq	$32, 112(%r12)
	movq	-120(%rbp), %r12
	movq	$108, 120(%r12)
	movq	-120(%rbp), %r12
	movq	$101, 128(%r12)
	movq	-120(%rbp), %r12
	movq	$115, 136(%r12)
	movq	-120(%rbp), %r12
	movq	$115, 144(%r12)
	movq	-120(%rbp), %r12
	movq	$32, 152(%r12)
	movq	-120(%rbp), %r12
	movq	$116, 160(%r12)
	movq	-120(%rbp), %r12
	movq	$104, 168(%r12)
	movq	-120(%rbp), %r12
	movq	$97, 176(%r12)
	movq	-120(%rbp), %r12
	movq	$110, 184(%r12)
	movq	-120(%rbp), %r12
	movq	$32, 192(%r12)
	movq	-120(%rbp), %r12
	movq	$49, 200(%r12)
	movq	-120(%rbp), %r12
	movq	$44, 208(%r12)
	movq	-120(%rbp), %r12
	movq	$48, 216(%r12)
	movq	-120(%rbp), %r12
	movq	$48, 224(%r12)
	movq	-120(%rbp), %r12
	movq	$48, 232(%r12)
	movq	-120(%rbp), %r12
	movq	$32, 240(%r12)
	movq	-120(%rbp), %r12
	movq	$105, 248(%r12)
	movq	-120(%rbp), %r12
	movq	$115, 256(%r12)
	movq	-120(%rbp), %r12
	movq	$32, 264(%r12)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r14
	movq	-96(%rbp), %r12
	addq	-8(%r12), %r14
	movq	%r14, -144(%rbp)
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -160(%rbp)
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -168(%rbp)
	movq	-168(%rbp), %r14
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
	movq	-152(%rbp), %r14
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
	movq	%r12, -208(%rbp)
	movq	$0, %r14
	movq	%r14, -216(%rbp)
	movq	-216(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
l16:
	movq	-136(%rbp), %r12
	movq	-8(%r12), %r14
	movq	%r14, -232(%rbp)
	movq	-224(%rbp), %r12
	movq	-232(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -224(%rbp)
	jl	l17
	movq	$0, %r14
	movq	%r14, -240(%rbp)
	movq	-232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -248(%rbp)
	movq	-240(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -256(%rbp)
l19:
	movq	-256(%rbp), %r14
	movq	-96(%rbp), %r12
	cmpq	-8(%r12), %r14
	movq	%r14, -256(%rbp)
	jl	l20
	movq	-208(%rbp), %r14
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
	callq	_Iprint_pai
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
l17:
	movq	-136(%rbp), %r12
	movq	-224(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -272(%rbp)
	movq	-272(%rbp), %r14
	movq	-208(%rbp), %r12
	movq	-224(%rbp), %r13
	movq	%r14, (%r12,%r13,8)
	movq	-224(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -280(%rbp)
	movq	-280(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -280(%rbp)
	movq	-280(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -288(%rbp)
	movq	-288(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
	jmp	l16
l20:
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -296(%rbp)
	movq	-296(%rbp), %r12
	movq	-248(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -296(%rbp)
	movq	-296(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -304(%rbp)
	movq	-304(%rbp), %r14
	imulq	$8, %r14
	movq	%r14, -304(%rbp)
	movq	-208(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -312(%rbp)
	movq	-312(%rbp), %r12
	movq	-304(%rbp), %r14
	addq	%r14, %r12
	movq	%r12, -312(%rbp)
	movq	-96(%rbp), %r12
	movq	-256(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -320(%rbp)
	movq	-320(%rbp), %r14
	movq	-312(%rbp), %r12
	movq	%r14, (%r12)
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -328(%rbp)
	movq	-328(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -328(%rbp)
	movq	-328(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -336(%rbp)
	movq	-336(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -256(%rbp)
	jmp	l19

	.globl	_Iisprime_bi
	.align	4
_Iisprime_bi:
	enter	$176, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%r14, %r12
	movq	%r12, -56(%rbp)
	movq	$2, %r14
	movq	%r14, -64(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
l6:
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	-72(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -104(%rbp)
	movq	-104(%rbp), %r12
	movq	-56(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -104(%rbp)
	jle	l7
	movq	$1, %rax
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
label2:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l7:
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-56(%rbp), %r14
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
	movq	%r14, %rsi
	movq	-136(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Igcd_iii
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
	cmpq	$1, %r14
	movq	%r14, -152(%rbp)
	jne	l9
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-160(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -160(%rbp)
	movq	-160(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-168(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	jmp	l6
l9:
	movq	$0, %rax
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -120(%rbp)
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	jmp	label2

	.globl	_Ilargestprime_ii
	.align	4
_Ilargestprime_ii:
	enter	$160, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%r14, %r12
	movq	%r12, -56(%rbp)
	movq	$1, %r14
	movq	%r14, -64(%rbp)
	movq	$1, %r14
	movq	%r14, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
l11:
	movq	-96(%rbp), %r12
	movq	-56(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -96(%rbp)
	jl	l12
	movq	-80(%rbp), %r14
	movq	%r14, %rax
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l12:
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-112(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_Iisprime_bi
	movq	%rax, %r12
	movq	%r12, -120(%rbp)
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
	movq	%r12, -128(%rbp)
	movq	-120(%rbp), %r12
	movq	-120(%rbp), %r14
	testq	%r14, %r12
	movq	%r12, -120(%rbp)
	jnz	l14
l15:
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-144(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -104(%rbp)
	jmp	l11
l14:
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-152(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	jmp	l15
