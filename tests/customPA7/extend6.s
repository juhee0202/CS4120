	.text
	.globl	_I_init_Animal
	.align	4
_I_init_Animal:
	enter	$0, $0
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
	movq	$8, %_temp0
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp0, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp1
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
	movq	%_temp1, %_t15
	movq	%_t15, %_tileRegister0
	addq	$0, %_tileRegister0
	leaq	_Animal_Ibark_p(%rip), %_tileRegister1
	movq	%_tileRegister1, (%_tileRegister0)
	movq	%_t15, _I_vt_Animal(%rip)
	jmp	l3

	.globl	_Imain_paai
	.align	4
_Imain_paai:
	enter	$0, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %args
	movq	_I_size_Dog(%rip), %_tileRegister2
	movq	$8, %_tileRegister3
	imulq	%_tileRegister3, %_tileRegister2
	movq	%_tileRegister2, %_temp2
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp2, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp3
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
	movq	%_temp3, %_t0
	movq	_I_vt_Dog(%rip), %_tileRegister4
	movq	%_tileRegister4, (%_t0)
	movq	%_t0, %dog
	movq	%dog, %_t1
	movq	_I_size_Animal(%rip), %_t2
	leaq	(%_t1,%_t2), %_tileRegister5
	movq	%_tileRegister5, %_temp6
	movq	$48, %_temp4
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp4, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp5
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
	movq	%_temp5, %_t3
	movq	$5, (%_t3)
	movq	$8, %_tileRegister7
	addq	%_t3, %_tileRegister7
	movq	$97, (%_tileRegister7)
	movq	$16, %_tileRegister8
	addq	%_t3, %_tileRegister8
	movq	$108, (%_tileRegister8)
	movq	$24, %_tileRegister9
	addq	%_t3, %_tileRegister9
	movq	$105, (%_tileRegister9)
	movq	$32, %_tileRegister10
	addq	%_t3, %_tileRegister10
	movq	$99, (%_tileRegister10)
	movq	$40, %_tileRegister11
	addq	%_t3, %_tileRegister11
	movq	$101, (%_tileRegister11)
	movq	%_t3, %_tileRegister12
	addq	$8, %_tileRegister12
	movq	%_tileRegister12, %_t3
	movq	%_t3, (%_temp6)
	movq	%dog, %a
	movq	%a, %_t4
	movq	%_t4, %_tileRegister13
	addq	$8, %_tileRegister13
	movq	%_tileRegister13, %_temp9
	movq	$64, %_temp7
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp7, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp8
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
	movq	%_temp8, %_t5
	movq	$7, (%_t5)
	movq	$8, %_tileRegister14
	addq	%_t5, %_tileRegister14
	movq	$110, (%_tileRegister14)
	movq	$16, %_tileRegister15
	addq	%_t5, %_tileRegister15
	movq	$111, (%_tileRegister15)
	movq	$24, %_tileRegister16
	addq	%_t5, %_tileRegister16
	movq	$116, (%_tileRegister16)
	movq	$32, %_tileRegister17
	addq	%_t5, %_tileRegister17
	movq	$84, (%_tileRegister17)
	movq	$40, %_tileRegister18
	addq	%_t5, %_tileRegister18
	movq	$111, (%_tileRegister18)
	movq	$48, %_tileRegister19
	addq	%_t5, %_tileRegister19
	movq	$98, (%_tileRegister19)
	movq	$56, %_tileRegister20
	addq	%_t5, %_tileRegister20
	movq	$105, (%_tileRegister20)
	movq	%_t5, %_tileRegister21
	addq	$8, %_tileRegister21
	movq	%_tileRegister21, %_t5
	movq	%_t5, (%_temp9)
	movq	%dog, %_t6
	movq	_I_size_Animal(%rip), %_t7
	movq	(%_t6,%_t7), %_t8
	movq	%_t8, %_temp10
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp10, %rdi
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
	enter	$0, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %_t13
	movq	$112, %_temp12
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp12, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp13
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
	movq	%_temp13, %_t14
	movq	$13, (%_t14)
	movq	$8, %_tileRegister24
	addq	%_t14, %_tileRegister24
	movq	$65, (%_tileRegister24)
	movq	$16, %_tileRegister25
	addq	%_t14, %_tileRegister25
	movq	$78, (%_tileRegister25)
	movq	$24, %_tileRegister26
	addq	%_t14, %_tileRegister26
	movq	$73, (%_tileRegister26)
	movq	$32, %_tileRegister27
	addq	%_t14, %_tileRegister27
	movq	$77, (%_tileRegister27)
	movq	$40, %_tileRegister28
	addq	%_t14, %_tileRegister28
	movq	$65, (%_tileRegister28)
	movq	$48, %_tileRegister29
	addq	%_t14, %_tileRegister29
	movq	$76, (%_tileRegister29)
	movq	$56, %_tileRegister30
	addq	%_t14, %_tileRegister30
	movq	$32, (%_tileRegister30)
	movq	$64, %_tileRegister31
	addq	%_t14, %_tileRegister31
	movq	$66, (%_tileRegister31)
	movq	$72, %_tileRegister32
	addq	%_t14, %_tileRegister32
	movq	$65, (%_tileRegister32)
	movq	$80, %_tileRegister33
	addq	%_t14, %_tileRegister33
	movq	$82, (%_tileRegister33)
	movq	$88, %_tileRegister34
	addq	%_t14, %_tileRegister34
	movq	$75, (%_tileRegister34)
	movq	$96, %_tileRegister35
	addq	%_t14, %_tileRegister35
	movq	$33, (%_tileRegister35)
	movq	$104, %_tileRegister36
	addq	%_t14, %_tileRegister36
	movq	$33, (%_tileRegister36)
	movq	%_t14, %_tileRegister37
	addq	$8, %_tileRegister37
	movq	%_tileRegister37, %_t14
	movq	%_t14, %_temp14
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp14, %rdi
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
	enter	$0, $0
	movq	%rbx, -8(%rbp)
	movq	%rbp, -16(%rbp)
	movq	%r12, -24(%rbp)
	movq	%r13, -32(%rbp)
	movq	%r14, -40(%rbp)
	movq	%r15, -48(%rbp)
	movq	%rdi, %_t9
	movq	$96, %_temp16
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp16, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp17
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
	movq	%_temp17, %_t10
	movq	$11, (%_t10)
	movq	$8, %_tileRegister38
	addq	%_t10, %_tileRegister38
	movq	$84, (%_tileRegister38)
	movq	$16, %_tileRegister39
	addq	%_t10, %_tileRegister39
	movq	$79, (%_tileRegister39)
	movq	$24, %_tileRegister40
	addq	%_t10, %_tileRegister40
	movq	$66, (%_tileRegister40)
	movq	$32, %_tileRegister41
	addq	%_t10, %_tileRegister41
	movq	$73, (%_tileRegister41)
	movq	$40, %_tileRegister42
	addq	%_t10, %_tileRegister42
	movq	$32, (%_tileRegister42)
	movq	$48, %_tileRegister43
	addq	%_t10, %_tileRegister43
	movq	$66, (%_tileRegister43)
	movq	$56, %_tileRegister44
	addq	%_t10, %_tileRegister44
	movq	$65, (%_tileRegister44)
	movq	$64, %_tileRegister45
	addq	%_t10, %_tileRegister45
	movq	$82, (%_tileRegister45)
	movq	$72, %_tileRegister46
	addq	%_t10, %_tileRegister46
	movq	$75, (%_tileRegister46)
	movq	$80, %_tileRegister47
	addq	%_t10, %_tileRegister47
	movq	$33, (%_tileRegister47)
	movq	$88, %_tileRegister48
	addq	%_t10, %_tileRegister48
	movq	$33, (%_tileRegister48)
	movq	%_t10, %_tileRegister49
	addq	$8, %_tileRegister49
	movq	%_tileRegister49, %_t10
	movq	%_t10, %_temp18
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp18, %rdi
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
	enter	$0, $0
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
	movq	_I_size_Animal(%rip), %_t11
	movq	%_t11, %_tileRegister50
	addq	$1, %_tileRegister50
	movq	%_tileRegister50, _I_size_Dog(%rip)
	movq	$8, %_temp21
	pushq	%rax
	pushq	%rcx
	pushq	%rdx
	pushq	%r8
	pushq	%r9
	pushq	%r10
	pushq	%r11
	pushq	%rdi
	pushq	%rsi
	movq	%_temp21, %rdi
	subq	$8, %rsp
	callq	_I_alloc_i
	movq	%rax, %_temp22
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
	movq	%_temp22, %_t12
	movq	%_t12, %_tileRegister51
	addq	$0, %_tileRegister51
	leaq	_Dog_Ibark_p(%rip), %_tileRegister52
	movq	%_tileRegister52, (%_tileRegister51)
	movq	%_t12, _I_vt_Dog(%rip)
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
.globl _I_vt_Dog
_I_vt_Dog:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_Dog
	.text

	.bss
	.align 8
.globl _I_size_Animal
_I_size_Animal:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_size_Dog
_I_size_Dog:
	.zero 8
	.text
