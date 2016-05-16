# Compiled using "xic" by Owen Arden, Catalin Dumitru, Wenzel Jakob, and Danfeng Zhang
# Command line: java -jar xic.jar -I runtime/include/ -I xth/tests/pa5 QtXi/examples/onewidget.xi

.file "QtXi/examples/onewidget.xi"
.att_syntax prefix
.text

.globl _Imain_paai
_Imain_paai:
	pushq %rbx
	pushq %r12
	pushq %r14
	pushq %r15
	subq $24, %rsp
	movq %rdi, %rsi
	movq %rsp, %rdi
	call _Iqapplication_t2o12QApplicationaaiaai
	movq (%rsp), %r14
	movq 8(%rsp), %r12
	movq -8(%r12), %r15
	cmpq $0, %r15
	jle .L2
	cmpq $0, %r15
	jbe call_abort
	movq (%r12), %rdi
	call _Iprintln_pai
	movq $1, %rbx
.L0:
	cmpq %r15, %rbx
	jge .L2
	cmpq %r15, %rbx
	jae call_abort
	movq (%r12,%rbx,8), %rdi
	call _Iprintln_pai
	incq %rbx
	jmp .L0
.L2:
	leaq .L4(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iqs_o7QStringai
	movq %rax, %rdi
	call _Iqpushbutton_o11QPushButtono7QString
	movq %rax, %rdi
	movq (%rax), %rax
	callq *32(%rax)
	movq %r14, %rdi
	movq (%r14), %rax
	callq *8(%rax)
	addq $24, %rsp
	popq %r15
	popq %r14
	popq %r12
	popq %rbx
	ret

strdup:
	pushq %rbx
	pushq %r14
	subq $8, %rsp
	movq %rdi, %r14
	movq (%r14), %rbx
	leaq 8(,%rbx,8), %rdi
	call _I_alloc_i
.L5:
	movq (%r14,%rbx,8), %rcx
	movq %rcx, (%rax,%rbx,8)
	decq %rbx
	cmpq $0, %rbx
	jge .L5
	addq $8, %rax
	addq $8, %rsp
	popq %r14
	popq %rbx
	ret

	.section .rodata
	.align 8
.L4:
	.quad 2
	.quad 104
	.quad 105
	.text

call_abort:
	call _I_outOfBounds_p
