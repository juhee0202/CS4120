	.text
	.globl	_Iusage_p
	.align	4
_Iusage_p:
	enter	$80, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	$240, %r14
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
	movq	-64(%rbp), %r12
	movq	$29, (%r12)
	movq	-64(%rbp), %r12
	movq	$80, 8(%r12)
	movq	-64(%rbp), %r12
	movq	$108, 16(%r12)
	movq	-64(%rbp), %r12
	movq	$101, 24(%r12)
	movq	-64(%rbp), %r12
	movq	$97, 32(%r12)
	movq	-64(%rbp), %r12
	movq	$115, 40(%r12)
	movq	-64(%rbp), %r12
	movq	$101, 48(%r12)
	movq	-64(%rbp), %r12
	movq	$32, 56(%r12)
	movq	-64(%rbp), %r12
	movq	$115, 64(%r12)
	movq	-64(%rbp), %r12
	movq	$112, 72(%r12)
	movq	-64(%rbp), %r12
	movq	$101, 80(%r12)
	movq	-64(%rbp), %r12
	movq	$99, 88(%r12)
	movq	-64(%rbp), %r12
	movq	$105, 96(%r12)
	movq	-64(%rbp), %r12
	movq	$102, 104(%r12)
	movq	-64(%rbp), %r12
	movq	$121, 112(%r12)
	movq	-64(%rbp), %r12
	movq	$32, 120(%r12)
	movq	-64(%rbp), %r12
	movq	$116, 128(%r12)
	movq	-64(%rbp), %r12
	movq	$104, 136(%r12)
	movq	-64(%rbp), %r12
	movq	$101, 144(%r12)
	movq	-64(%rbp), %r12
	movq	$32, 152(%r12)
	movq	-64(%rbp), %r12
	movq	$105, 160(%r12)
	movq	-64(%rbp), %r12
	movq	$110, 168(%r12)
	movq	-64(%rbp), %r12
	movq	$112, 176(%r12)
	movq	-64(%rbp), %r12
	movq	$117, 184(%r12)
	movq	-64(%rbp), %r12
	movq	$116, 192(%r12)
	movq	-64(%rbp), %r12
	movq	$32, 200(%r12)
	movq	-64(%rbp), %r12
	movq	$115, 208(%r12)
	movq	-64(%rbp), %r12
	movq	$105, 216(%r12)
	movq	-64(%rbp), %r12
	movq	$122, 224(%r12)
	movq	-64(%rbp), %r12
	movq	$101, 232(%r12)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -72(%rbp)
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

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$288, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%r14, %r12
	movq	%r12, -56(%rbp)
	movq	$11, %r14
	movq	%r14, -64(%rbp)
	movq	$2, %r14
	movq	%r14, -72(%rbp)
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
	movq	%r14, %rsi
	movq	-72(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IAck_iii
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
	movq	$56, %r14
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
	movq	$6, (%r12)
	movq	-120(%rbp), %r12
	movq	$65, 8(%r12)
	movq	-120(%rbp), %r12
	movq	$99, 16(%r12)
	movq	-120(%rbp), %r12
	movq	$107, 24(%r12)
	movq	-120(%rbp), %r12
	movq	$40, 32(%r12)
	movq	-120(%rbp), %r12
	movq	$50, 40(%r12)
	movq	-120(%rbp), %r12
	movq	$44, 48(%r12)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -128(%rbp)
	movq	-128(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -128(%rbp)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r14
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
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
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
	callq	_IunparseInt_aii
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
	movq	$32, %r14
	movq	%r14, -176(%rbp)
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
	movq	-192(%rbp), %r12
	movq	$3, (%r12)
	movq	-192(%rbp), %r12
	movq	$41, 8(%r12)
	movq	-192(%rbp), %r12
	movq	$58, 16(%r12)
	movq	-192(%rbp), %r12
	movq	$32, 24(%r12)
	movq	-192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-200(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -200(%rbp)
	movq	-200(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	-208(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -216(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-216(%rbp), %r14
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
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-224(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IunparseInt_aii
	movq	%rax, %r12
	movq	%r12, -232(%rbp)
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
	movq	-232(%rbp), %r14
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
	movq	$8, %r14
	movq	%r14, -248(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-248(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -256(%rbp)
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
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -264(%rbp)
	movq	-264(%rbp), %r12
	movq	$0, (%r12)
	movq	-264(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -272(%rbp)
	movq	-272(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -272(%rbp)
	movq	-272(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -280(%rbp)
	movq	-280(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -288(%rbp)
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

	.globl	_IAck_iii
	.align	4
_IAck_iii:
	enter	$256, $0
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
	movq	-56(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -56(%rbp)
	je	l0
	movq	-64(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -64(%rbp)
	je	l3
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -72(%rbp)
	movq	-72(%rbp), %r14
	addq	$-1, %r14
	movq	%r14, -72(%rbp)
	movq	-72(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -80(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -88(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -96(%rbp)
	movq	-96(%rbp), %r14
	addq	$-1, %r14
	movq	%r14, -96(%rbp)
	movq	-96(%rbp), %r14
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
	movq	%r14, %rsi
	movq	-88(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IAck_iii
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
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-120(%rbp), %r14
	movq	%r14, %rsi
	movq	-80(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IAck_iii
	movq	%rax, %r12
	movq	%r12, -128(%rbp)
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
	movq	-128(%rbp), %r14
	movq	%r14, %rax
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	-80(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	-104(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	-88(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	%r14, %r12
	movq	%r12, -216(%rbp)
label4:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l0:
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-224(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -224(%rbp)
	movq	-224(%rbp), %r14
	movq	%r14, %rax
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	%r14, %r12
	movq	%r12, -216(%rbp)
	jmp	label4
l3:
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r14
	addq	$-1, %r14
	movq	%r14, -232(%rbp)
	movq	-232(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -240(%rbp)
	movq	$1, %r14
	movq	%r14, -248(%rbp)
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	-248(%rbp), %r14
	movq	%r14, %rsi
	movq	-240(%rbp), %r14
	movq	%r14, %rdi
	subq	$8, %rsp
	callq	_IAck_iii
	movq	%rax, %r12
	movq	%r12, -256(%rbp)
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
	movq	-256(%rbp), %r14
	movq	%r14, %rax
	movq	-256(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-56(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	%r14, %r12
	movq	%r12, -152(%rbp)
	movq	%r14, %r12
	movq	%r12, -160(%rbp)
	movq	-240(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -168(%rbp)
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-64(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	movq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	-248(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -216(%rbp)
	jmp	label4
