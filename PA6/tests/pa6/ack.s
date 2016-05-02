	.text
	.globl	_Iusage_p
	.align	4
_Iusage_p:
	enter	$48, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	$240, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rdi
	callq	_I_alloc_i
	movq	%rax, %r12
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	$29, (%r12)
	movq	$80, 8(%r12)
	movq	$108, 16(%r12)
	movq	$101, 24(%r12)
	movq	$97, 32(%r12)
	movq	$115, 40(%r12)
	movq	$101, 48(%r12)
	movq	$32, 56(%r12)
	movq	$115, 64(%r12)
	movq	$112, 72(%r12)
	movq	$101, 80(%r12)
	movq	$99, 88(%r12)
	movq	$105, 96(%r12)
	movq	$102, 104(%r12)
	movq	$121, 112(%r12)
	movq	$32, 120(%r12)
	movq	$116, 128(%r12)
	movq	$104, 136(%r12)
	movq	$101, 144(%r12)
	movq	$32, 152(%r12)
	movq	$105, 160(%r12)
	movq	$110, 168(%r12)
	movq	$112, 176(%r12)
	movq	$117, 184(%r12)
	movq	$116, 192(%r12)
	movq	$32, 200(%r12)
	movq	$115, 208(%r12)
	movq	$105, 216(%r12)
	movq	$122, 224(%r12)
	movq	$101, 232(%r12)
	addq	$8, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rdi
	callq	_Iprintln_pai
	popq	%rsp
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
	enter	$112, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	pushq	%r9
	movq	-56(%rbp), %r9
	popq	%r9
	movq	$11, %rbx
	movq	$2, %r14
	movq	%rbx, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rsi
	movq	%r14, %rdi
	callq	_IAck_iii
	movq	%rax, %r12
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	%r12, %r14
	movq	$56, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rdi
	callq	_I_alloc_i
	movq	%rax, %r12
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	pushq	%r9
	movq	-80(%rbp), %r9
	pushq	%r9
	movq	-80(%rbp), %r9
	movq	%r9, -80(%rbp)
	popq	%r9
	movq	%r9, -80(%rbp)
	popq	%r9
	movq	$6, (%r12)
	movq	$65, 8(%r12)
	movq	$99, 16(%r12)
	movq	$107, 24(%r12)
	movq	$40, 32(%r12)
	movq	$50, 40(%r12)
	movq	$44, 48(%r12)
	pushq	%r9
	movq	-80(%rbp), %r9
	pushq	%r9
	movq	-80(%rbp), %r9
	movq	%r9, -80(%rbp)
	popq	%r9
	movq	%r9, -80(%rbp)
	popq	%r9
	addq	$8, %r12
	pushq	%r9
	movq	-80(%rbp), %r9
	pushq	%r9
	movq	-80(%rbp), %r9
	movq	%r9, -80(%rbp)
	popq	%r9
	movq	%r9, -80(%rbp)
	popq	%r9
	pushq	%r9
	movq	-80(%rbp), %r9
	pushq	%r9
	movq	-80(%rbp), %r9
	movq	%r9, -80(%rbp)
	popq	%r9
	movq	%r9, -80(%rbp)
	popq	%r9
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rdi
	callq	_Iprint_pai
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	%rbx, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rdi
	callq	_IunparseInt_aii
	movq	%rax, %r12
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	pushq	%r9
	movq	-88(%rbp), %r9
	pushq	%r9
	movq	-88(%rbp), %r9
	movq	%r9, -88(%rbp)
	popq	%r9
	movq	%r9, -88(%rbp)
	popq	%r9
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rdi
	callq	_Iprint_pai
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	$32, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rdi
	callq	_I_alloc_i
	movq	%rax, %r12
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	pushq	%r9
	movq	-96(%rbp), %r9
	pushq	%r9
	movq	-96(%rbp), %r9
	movq	%r9, -96(%rbp)
	popq	%r9
	movq	%r9, -96(%rbp)
	popq	%r9
	movq	$3, (%r12)
	movq	$41, 8(%r12)
	movq	$58, 16(%r12)
	movq	$32, 24(%r12)
	pushq	%r9
	movq	-96(%rbp), %r9
	pushq	%r9
	movq	-96(%rbp), %r9
	movq	%r9, -96(%rbp)
	popq	%r9
	movq	%r9, -96(%rbp)
	popq	%r9
	addq	$8, %r12
	pushq	%r9
	movq	-96(%rbp), %r9
	pushq	%r9
	movq	-96(%rbp), %r9
	movq	%r9, -96(%rbp)
	popq	%r9
	movq	%r9, -96(%rbp)
	popq	%r9
	pushq	%r9
	movq	-96(%rbp), %r9
	pushq	%r9
	movq	-96(%rbp), %r9
	movq	%r9, -96(%rbp)
	popq	%r9
	movq	%r9, -96(%rbp)
	popq	%r9
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rdi
	callq	_Iprint_pai
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	%r14, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rdi
	callq	_IunparseInt_aii
	movq	%rax, %r12
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	pushq	%r9
	movq	-72(%rbp), %r9
	pushq	%r9
	movq	-72(%rbp), %r9
	movq	%r9, -72(%rbp)
	popq	%r9
	movq	%r9, -72(%rbp)
	popq	%r9
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rdi
	callq	_Iprint_pai
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	$8, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rdi
	callq	_I_alloc_i
	movq	%rax, %r12
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	pushq	%r9
	movq	-64(%rbp), %r9
	pushq	%r9
	movq	-64(%rbp), %r9
	movq	%r9, -64(%rbp)
	popq	%r9
	movq	%r9, -64(%rbp)
	popq	%r9
	movq	$0, (%r12)
	pushq	%r9
	movq	-64(%rbp), %r9
	pushq	%r9
	movq	-64(%rbp), %r9
	movq	%r9, -64(%rbp)
	popq	%r9
	movq	%r9, -64(%rbp)
	popq	%r9
	addq	$8, %r12
	pushq	%r9
	movq	-64(%rbp), %r9
	pushq	%r9
	movq	-64(%rbp), %r9
	movq	%r9, -64(%rbp)
	popq	%r9
	movq	%r9, -64(%rbp)
	popq	%r9
	pushq	%r9
	movq	-64(%rbp), %r9
	pushq	%r9
	movq	-64(%rbp), %r9
	movq	%r9, -64(%rbp)
	popq	%r9
	movq	%r9, -64(%rbp)
	popq	%r9
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rdi
	callq	_Iprintln_pai
	popq	%rsp
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
	enter	$176, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	pushq	%r9
	movq	-72(%rbp), %r9
	pushq	%r9
	movq	%r9, -136(%rbp)
	popq	%r9
	popq	%r9
	pushq	%r9
	movq	-56(%rbp), %r9
	pushq	%r9
	movq	%r9, -80(%rbp)
	popq	%r9
	popq	%r9
	pushq	%r9
	movq	-136(%rbp), %r9
	cmpq	$0, %r9
	popq	%r9
	je	l0
	pushq	%r9
	movq	-80(%rbp), %r9
	cmpq	$0, %r9
	popq	%r9
	je	l3
	pushq	%r9
	movq	-136(%rbp), %r9
	movq	%r9, %r12
	popq	%r9
	addq	$-1, %r12
	movq	%r12, %r14
	pushq	%r9
	movq	-136(%rbp), %r9
	movq	%r9, %r15
	popq	%r9
	pushq	%r9
	movq	-80(%rbp), %r9
	movq	%r9, %r12
	popq	%r9
	addq	$-1, %r12
	movq	%r12, %rbx
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%rbx, %rsi
	movq	%r15, %rdi
	callq	_IAck_iii
	movq	%rax, %r13
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	%r13, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rsi
	movq	%r14, %rdi
	callq	_IAck_iii
	pushq	%r9
	movq	%rax, %r9
	movq	%r9, -64(%rbp)
	popq	%r9
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	pushq	%r9
	movq	-64(%rbp), %r9
	movq	%r9, %rax
	popq	%r9
	pushq	%r9
	movq	-144(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-80(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-160(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-64(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-136(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-152(%rbp), %r9
	popq	%r9
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
	pushq	%r9
	movq	-80(%rbp), %r9
	popq	%r9
	addq	$1, %r9
	movq	%r9, %rax
	pushq	%r9
	movq	-112(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-144(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-80(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-104(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-160(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-128(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-136(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-152(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-120(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-96(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-88(%rbp), %r9
	popq	%r9
	jmp	label4
l3:
	pushq	%r9
	movq	-136(%rbp), %r9
	movq	%r9, %r12
	popq	%r9
	addq	$-1, %r12
	movq	%r12, %r14
	movq	$1, %r12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	pushq	%rsp
	movq	%r12, %rsi
	movq	%r14, %rdi
	callq	_IAck_iii
	movq	%rax, %rbx
	popq	%rsp
	popq	%rsi
	popq	%rdi
	popq	%r11
	popq	%r10
	popq	%r9
	popq	%r8
	popq	%rdx
	popq	%rcx
	popq	%rax
	movq	%rbx, %rax
	pushq	%r9
	movq	-112(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-80(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-104(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-128(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-136(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-120(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-96(%rbp), %r9
	popq	%r9
	pushq	%r9
	movq	-88(%rbp), %r9
	popq	%r9
	jmp	label4
