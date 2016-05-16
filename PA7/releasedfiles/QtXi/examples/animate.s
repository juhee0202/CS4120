# Compiled using "xic" by Owen Arden, Catalin Dumitru, Wenzel Jakob, and Danfeng Zhang
# Command line: java -jar xic.jar -I runtime/include/ QtXi/examples/animate.xi

.file "QtXi/examples/animate.xi"
.att_syntax prefix
.text

.globl _I_AnimationTimer_timeout_po6QTimer
_I_AnimationTimer_timeout_po6QTimer:
	pushq %rbx
	call _IballRect_o5QRect
	movq %rax, %rbx
	movq _I_g_dx_i(%rip), %rax
	addq %rax, _I_g_x_i(%rip)
	movq _I_g_dy_i(%rip), %rax
	addq %rax, _I_g_y_i(%rip)
	movq _I_g_x_i(%rip), %rcx
	movq _I_g_R_i(%rip), %rax
	leaq (%rcx,%rax,1), %rdx
	cmpq _I_g_DIM_i(%rip), %rdx
	jl .L6
.L4:
	negq _I_g_dx_i(%rip)
.L5:
	movq _I_g_R_i(%rip), %rdx
	movq _I_g_DIM_i(%rip), %rcx
	movq _I_g_y_i(%rip), %rdi
	leaq (%rdi,%rdx,1), %rax
	cmpq %rcx, %rax
	jl .L9
.L7:
	negq _I_g_dy_i(%rip)
.L8:
	call _IballRect_o5QRect
	movq %rbx, %rsi
	movq %rax, %rdi
	movq (%rax), %rax
	callq *368(%rax)
	movq _I_g_mainWidget_o7QWidget(%rip), %rbx
	movq $5, %r8
	movq $5, %rcx
	movq $-5, %rdx
	movq $-5, %rsi
	movq %rax, %rdi
	movq (%rax), %rax
	callq *16(%rax)
	movq %rax, %rsi
	movq %rbx, %rdi
	movq (%rbx), %rax
	callq *224(%rax)
	jmp .E2
.L6:
	subq _I_g_R_i(%rip), %rcx
	cmpq $0, %rcx
	jle .L4
	jmp .L5
.L9:
	subq %rdx, %rdi
	cmpq $0, %rdi
	jle .L7
	jmp .L8
.E2:
	popq %rbx
	ret

.globl _I_BallWidget_paintEvent_po11QPaintEvent
_I_BallWidget_paintEvent_po11QPaintEvent:
	pushq %rbx
	pushq %r12
	pushq %r13
	pushq %r14
	pushq %r15
	movq %rdi, %r12
	movq %rsi, %r14
	movq _I_g_backBuffer_o7QPixmap(%rip), %rbx
	movq $192, %rdx
	movq $255, %rsi
	movq $255, %rdi
	call _Iqcolor_o6QColoriii
	movq %rax, %rsi
	movq %rbx, %rdi
	movq (%rbx), %rax
	callq *48(%rax)
	movq _I_g_backBuffer_o7QPixmap(%rip), %rdi
	call _Iqpainter_o8QPaintero12QPaintDevice
	movq %rax, %r13
	movq (%r13), %rbx
	movq $1, %rsi
	movq %r13, %rdi
	callq *32(%rbx)
	movq $255, %rdx
	xorq %rsi, %rsi
	xorq %rdi, %rdi
	call _Iqcolor_o6QColoriii
	movq %rax, %rdi
	call _Iqpen_o4QPeno6QColor
	movq %rax, %r15
	movq $5, %rsi
	movq %r15, %rdi
	movq (%r15), %rax
	callq *104(%rax)
	movq %r15, %rsi
	movq %r13, %rdi
	callq *112(%rbx)
	xorq %rdx, %rdx
	xorq %rsi, %rsi
	movq $255, %rdi
	call _Iqcolor_o6QColoriii
	movq %rax, %rdi
	call _Iqbrush_o6QBrusho6QColor
	movq %rax, %rsi
	movq %r13, %rdi
	callq *128(%rbx)
	call _IballRect_o5QRect
	movq %rax, %rsi
	movq %r13, %rdi
	callq *152(%rbx)
	movq %r13, %rdi
	callq *16(%rbx)
	movq %r14, %rdi
	movq (%r14), %rax
	callq *48(%rax)
	movq %rax, %rbx
	movq %r12, %rdi
	call _Iqpainter_o8QPaintero12QPaintDevice
	movq %rax, %r14
	movq %rbx, %rdi
	movq (%rbx), %rax
	callq *336(%rax)
	movq _I_g_backBuffer_o7QPixmap(%rip), %rdx
	movq (%r14), %r15
	movq %rbx, %rcx
	movq %rax, %rsi
	movq %r14, %rdi
	callq *216(%r15)
	movq %r14, %rdi
	callq *16(%r15)
	popq %r15
	popq %r14
	popq %r13
	popq %r12
	popq %rbx
	ret

.globl _I_init_AnimationTimer
_I_init_AnimationTimer:
	subq $8, %rsp
	cmpq $0, _I_size_AnimationTimer(%rip)
	jne .L10
	call _I_init_TimerListener
	movq _I_size_TimerListener(%rip), %rax
	movq %rax, _I_size_AnimationTimer(%rip)
	xorq %rcx, %rcx
.L11:
	cmpq $2, %rcx
	jge .L12
	leaq _I_vt_TimerListener(%rip), %rax
	movq (%rax,%rcx,8), %rdx
	leaq _I_vt_AnimationTimer(%rip), %rax
	movq %rdx, (%rax,%rcx,8)
	incq %rcx
	jmp .L11
.L12:
	leaq _I_AnimationTimer_timeout_po6QTimer(%rip), %rax
	movq %rax, _I_vt_AnimationTimer+8(%rip)
.L10:
	addq $8, %rsp
	ret

.globl _I_init_BallWidget
_I_init_BallWidget:
	subq $8, %rsp
	cmpq $0, _I_size_BallWidget(%rip)
	jne .L1
	call _I_init_QWidget
	movq _I_size_QWidget(%rip), %rax
	movq %rax, _I_size_BallWidget(%rip)
	xorq %rax, %rax
.L2:
	cmpq $78, %rax
	jge .L3
	leaq _I_vt_QWidget(%rip), %rcx
	movq (%rcx,%rax,8), %rdx
	leaq _I_vt_BallWidget(%rip), %rcx
	movq %rdx, (%rcx,%rax,8)
	incq %rax
	jmp .L2
.L3:
	leaq _I_BallWidget_paintEvent_po11QPaintEvent(%rip), %rax
	movq %rax, _I_vt_BallWidget+496(%rip)
.L1:
	addq $8, %rsp
	ret

.globl _IballRect_o5QRect
_IballRect_o5QRect:
	subq $8, %rsp
	movq _I_g_x_i(%rip), %rdi
	movq _I_g_R_i(%rip), %rax
	subq %rax, %rdi
	movq _I_g_y_i(%rip), %rsi
	subq %rax, %rsi
	movq _I_g_R_i(%rip), %rax
	leaq (,%rax,2), %rcx
	movq %rcx, %rdx
	call _Iqrect_o5QRectiiii
	addq $8, %rsp
	ret

.globl _Imain_paai
_Imain_paai:
	pushq %rbx
	pushq %r14
	pushq %r15
	subq $16, %rsp
	movq %rdi, %rsi
	movq %rsp, %rdi
	call _Iqapplication_t2o12QApplicationaaiaai
	movq (%rsp), %r14
	movq _I_size_BallWidget(%rip), %rdi
	call _I_alloc_i
	leaq _I_vt_BallWidget(%rip), %rcx
	movq %rcx, (%rax)
	movq %rax, _I_g_mainWidget_o7QWidget(%rip)
	movq _I_g_DIM_i(%rip), %rax
	movq _I_g_DIM_i(%rip), %rsi
	movq %rax, %rdi
	call _Iqpixmap_o7QPixmapii
	movq %rax, _I_g_backBuffer_o7QPixmap(%rip)
	call _Iqtimer_o6QTimer
	movq %rax, %rbx
	movq _I_size_AnimationTimer(%rip), %rdi
	call _I_alloc_i
	leaq _I_vt_AnimationTimer(%rip), %rcx
	movq %rcx, (%rax)
	movq (%rbx), %r15
	movq %rax, %rsi
	movq %rbx, %rdi
	callq *72(%r15)
	xorq %rsi, %rsi
	movq %rbx, %rdi
	callq *40(%r15)
	movq $40, %rsi
	movq %rbx, %rdi
	callq *32(%r15)
	movq %rbx, %rdi
	callq *48(%r15)
	movq _I_g_mainWidget_o7QWidget(%rip), %rbx
	movq _I_g_DIM_i(%rip), %rdi
	movq %rdi, %rsi
	call _Iqsize_o5QSizeii
	movq %rax, %rsi
	movq %rbx, %rdi
	movq (%rbx), %rax
	callq *264(%rax)
	movq _I_g_mainWidget_o7QWidget(%rip), %rax
	movq %rax, %rdi
	movq (%rax), %rax
	callq *32(%rax)
	movq %r14, %rdi
	movq (%r14), %rax
	callq *8(%rax)
	addq $16, %rsp
	popq %r15
	popq %r14
	popq %rbx
	ret

	.bss
	.align 8
.globl _I_size_BallWidget
_I_size_BallWidget:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_vt_BallWidget
_I_vt_BallWidget:
	.zero 632
	.text

.section .ctors
	.align 8
	.quad _I_init_BallWidget
	.text

	.bss
	.align 8
.globl _I_size_AnimationTimer
_I_size_AnimationTimer:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_vt_AnimationTimer
_I_vt_AnimationTimer:
	.zero 24
	.text

.section .ctors
	.align 8
	.quad _I_init_AnimationTimer
	.text

	.section .data
	.align 8
.globl _I_g_DIM_i
_I_g_DIM_i:
	.quad 256
	.text

	.section .data
	.align 8
.globl _I_g_R_i
_I_g_R_i:
	.quad 16
	.text

	.section .data
	.align 8
.globl _I_g_x_i
_I_g_x_i:
	.quad 100
	.text

	.section .data
	.align 8
.globl _I_g_y_i
_I_g_y_i:
	.quad 50
	.text

	.section .data
	.align 8
.globl _I_g_dx_i
_I_g_dx_i:
	.quad 2
	.text

	.section .data
	.align 8
.globl _I_g_dy_i
_I_g_dy_i:
	.quad 1
	.text

	.bss
	.align 8
.globl _I_g_mainWidget_o7QWidget
_I_g_mainWidget_o7QWidget:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_g_backBuffer_o7QPixmap
_I_g_backBuffer_o7QPixmap:
	.zero 8
	.text

