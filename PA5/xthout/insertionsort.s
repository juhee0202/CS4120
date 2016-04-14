	.text
	.globl	_Isort_pai
	.align	4
_Isort_pai:
	enter	$192, $0
	movq	%rdi, %rcx
	movq	%rcx, -8(%rbp)
	movq	$0, %rcx
	movq	%rcx, -16(%rbp)
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rcx
	subq	$8, %rcx
	movq	%rcx, -24(%rbp)
	movq	-24(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -32(%rbp)
l0:
	movq	-16(%rbp), %rcx
	movq	-32(%rbp), %rdx
	cmpq	%rdx, %rcx
	movq	%rcx, -16(%rbp)
	jl	l1
l2:
	leave
	ret
l1:
	movq	-16(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
l3:
	movq	-40(%rbp), %rcx
	cmpq	$0, %rcx
	movq	%rcx, -40(%rbp)
	jg	l4
l5:
	movq	-16(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -48(%rbp)
	movq	-48(%rbp), %rcx
	addq	$1, %rcx
	movq	%rcx, -48(%rbp)
	movq	-16(%rbp), %rcx
	movq	-48(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -16(%rbp)
	jmp	l0
l4:
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -56(%rbp)
	movq	-56(%rbp), %rcx
	subq	$1, %rcx
	movq	%rcx, -56(%rbp)
	movq	$8, %rcx
	movq	%rcx, -64(%rbp)
	movq	-64(%rbp), %rcx
	movq	-56(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -64(%rbp)
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	-72(%rbp), %rcx
	movq	-64(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -72(%rbp)
	movq	$8, %rcx
	movq	%rcx, -80(%rbp)
	movq	-80(%rbp), %rcx
	movq	-40(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -80(%rbp)
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	-80(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -88(%rbp)
	movq	-88(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -96(%rbp)
	movq	-96(%rbp), %r11
	movq	-72(%rbp), %rcx
	cmpq	%r11, 0(%rcx)
	jg	l6
l7:
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -104(%rbp)
	movq	-104(%rbp), %rcx
	subq	$1, %rcx
	movq	%rcx, -104(%rbp)
	movq	-40(%rbp), %rcx
	movq	-104(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -40(%rbp)
	jmp	l3
l6:
	movq	$8, %rcx
	movq	%rcx, -112(%rbp)
	movq	-112(%rbp), %rcx
	movq	-40(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -112(%rbp)
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	-112(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -120(%rbp)
	movq	-120(%rbp), %rcx
	movq	0(%rcx), %r11
	movq	%r11, -128(%rbp)
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -136(%rbp)
	movq	-136(%rbp), %rcx
	subq	$1, %rcx
	movq	%rcx, -136(%rbp)
	movq	$8, %rcx
	movq	%rcx, -144(%rbp)
	movq	-144(%rbp), %rcx
	movq	-136(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -144(%rbp)
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	-152(%rbp), %rcx
	movq	-144(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -152(%rbp)
	movq	$8, %rcx
	movq	%rcx, -160(%rbp)
	movq	-160(%rbp), %rcx
	movq	-40(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -160(%rbp)
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
	movq	-168(%rbp), %rcx
	movq	-160(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -168(%rbp)
	movq	-152(%rbp), %rcx
	movq	0(%rcx), %rcx
	movq	-168(%rbp), %rcx
	movq	%rcx, 0(%rcx)
	movq	-40(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -176(%rbp)
	movq	-176(%rbp), %rcx
	subq	$1, %rcx
	movq	%rcx, -176(%rbp)
	movq	$8, %rcx
	movq	%rcx, -184(%rbp)
	movq	-184(%rbp), %rcx
	movq	-176(%rbp), %rdx
	imulq	%rdx, %rcx
	movq	%rcx, -184(%rbp)
	movq	-8(%rbp), %rdx
	movq	%rdx, %rcx
	movq	%rcx, -192(%rbp)
	movq	-192(%rbp), %rcx
	movq	-184(%rbp), %rdx
	addq	%rdx, %rcx
	movq	%rcx, -192(%rbp)
	movq	-128(%rbp), %r11
	movq	-192(%rbp), %rcx
	movq	%r11, 0(%rcx)
	jmp	l7
