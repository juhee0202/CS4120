	.text
	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$496, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %r12
	movq	%r12, -56(%rbp)
	movq	_I_size_Person(%rip), %r14
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
	movq	_I_vt_Person(%rip), %r14
	movq	%r14, -104(%rbp)
	movq	-104(%rbp), %r14
	movq	-96(%rbp), %r12
	movq	%r14, (%r12)
	movq	-96(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -112(%rbp)
	movq	$3, %r14
	movq	%r14, -120(%rbp)
	movq	$2, %r14
	movq	%r14, -128(%rbp)
	movq	-120(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -136(%rbp)
	movq	-136(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -136(%rbp)
	movq	-136(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	$8, %r14
	movq	%r14, -152(%rbp)
	movq	-144(%rbp), %r12
	movq	-152(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -144(%rbp)
	movq	-144(%rbp), %r14
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
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -168(%rbp)
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
	movq	-168(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	-120(%rbp), %r14
	movq	-176(%rbp), %r12
	movq	%r14, (%r12)
	movq	-176(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -184(%rbp)
	movq	-184(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -184(%rbp)
	movq	-184(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -176(%rbp)
	movq	$0, %r14
	movq	%r14, -192(%rbp)
l0:
	movq	-192(%rbp), %r12
	movq	-120(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -192(%rbp)
	jl	l1
	movq	-176(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -200(%rbp)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -208(%rbp)
	movq	-208(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -216(%rbp)
	movq	-216(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -216(%rbp)
	movq	-200(%rbp), %r14
	movq	-216(%rbp), %r12
	movq	%r14, (%r12)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -224(%rbp)
	movq	-224(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -232(%rbp)
	movq	-232(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -232(%rbp)
	movq	-232(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -240(%rbp)
	movq	$0, %r14
	movq	%r14, -248(%rbp)
	movq	-240(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -256(%rbp)
	movq	-256(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -256(%rbp)
	movq	-256(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -264(%rbp)
	movq	-248(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -248(%rbp)
	jl	l7
	movq	-248(%rbp), %r12
	movq	-264(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -248(%rbp)
	jl	l8
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
l1:
	movq	-176(%rbp), %r12
	movq	-192(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -272(%rbp)
	movq	-272(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -280(%rbp)
	movq	-128(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -288(%rbp)
	movq	-288(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -288(%rbp)
	movq	-288(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -296(%rbp)
	movq	$8, %r14
	movq	%r14, -304(%rbp)
	movq	-296(%rbp), %r12
	movq	-304(%rbp), %r14
	imulq	%r14, %r12
	movq	%r12, -296(%rbp)
	movq	-296(%rbp), %r14
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
	callq	_I_alloc_i
	movq	%rax, %r12
	movq	%r12, -320(%rbp)
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
	movq	-320(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -328(%rbp)
	movq	-128(%rbp), %r14
	movq	-328(%rbp), %r12
	movq	%r14, (%r12)
	movq	-328(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -336(%rbp)
	movq	-336(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -336(%rbp)
	movq	-336(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -328(%rbp)
	movq	$0, %r14
	movq	%r14, -344(%rbp)
l3:
	movq	-344(%rbp), %r12
	movq	-128(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -344(%rbp)
	jl	l4
	movq	-328(%rbp), %r14
	movq	-280(%rbp), %r12
	movq	%r14, (%r12)
	movq	-192(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -352(%rbp)
	movq	-352(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -352(%rbp)
	movq	-352(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -192(%rbp)
	jmp	l0
l4:
	movq	-328(%rbp), %r12
	movq	-344(%rbp), %r13
	leaq	(%r12,%r13,8), %r14
	movq	%r14, -360(%rbp)
	movq	-360(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -368(%rbp)
	movq	$0, %r14
	movq	%r14, -376(%rbp)
	movq	-376(%rbp), %r14
	movq	-368(%rbp), %r12
	movq	%r14, (%r12)
	movq	-344(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -384(%rbp)
	movq	-384(%rbp), %r14
	addq	$1, %r14
	movq	%r14, -384(%rbp)
	movq	-384(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -344(%rbp)
	jmp	l3
l8:
	movq	-240(%rbp), %r12
	movq	-248(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -392(%rbp)
	movq	$0, %r14
	movq	%r14, -400(%rbp)
	movq	-392(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -408(%rbp)
	movq	-408(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -408(%rbp)
	movq	-408(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -416(%rbp)
	movq	-400(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -400(%rbp)
	jl	l10
	movq	-400(%rbp), %r12
	movq	-416(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -400(%rbp)
	jl	l11
l10:
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
	jmp	_label0
l11:
	movq	-392(%rbp), %r12
	movq	-400(%rbp), %r13
	movq	$0, (%r12,%r13,8)
	movq	-112(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -424(%rbp)
	movq	-424(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -432(%rbp)
	movq	-432(%rbp), %r14
	addq	$8, %r14
	movq	%r14, -432(%rbp)
	movq	-432(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -440(%rbp)
	movq	$0, %r14
	movq	%r14, -448(%rbp)
	movq	-440(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -456(%rbp)
	movq	-456(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -456(%rbp)
	movq	-456(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -464(%rbp)
	movq	-448(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -448(%rbp)
	jl	l13
	movq	-448(%rbp), %r12
	movq	-464(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -448(%rbp)
	jl	l14
l13:
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
	jmp	_label0
l14:
	movq	-440(%rbp), %r12
	movq	-448(%rbp), %r13
	movq	(%r12,%r13,8), %r14
	movq	%r14, -472(%rbp)
	movq	$1, %r14
	movq	%r14, -480(%rbp)
	movq	-472(%rbp), %r14
	movq	%r14, %r12
	movq	%r12, -488(%rbp)
	movq	-488(%rbp), %r14
	subq	$8, %r14
	movq	%r14, -488(%rbp)
	movq	-488(%rbp), %r12
	movq	(%r12), %r14
	movq	%r14, -496(%rbp)
	movq	-480(%rbp), %r14
	cmpq	$0, %r14
	movq	%r14, -480(%rbp)
	jl	l16
	movq	-480(%rbp), %r12
	movq	-496(%rbp), %r14
	cmpq	%r14, %r12
	movq	%r12, -480(%rbp)
	jl	l17
l16:
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
	jmp	_label0
l17:
	movq	-472(%rbp), %r12
	movq	-480(%rbp), %r13
	movq	$1, (%r12,%r13,8)
	jmp	_label0

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
	je	l18
l19:
	movq	-8(%rbp), %rbx
	movq	-16(%rbp), %rbp
	movq	-24(%rbp), %r12
	movq	-32(%rbp), %r13
	movq	-40(%rbp), %r14
	movq	-48(%rbp), %r15
	leave
	ret
l18:
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
	jmp	l19


	.bss
	.align 8
.globl _I_size_Person
_I_size_Person:
	.zero 8
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
