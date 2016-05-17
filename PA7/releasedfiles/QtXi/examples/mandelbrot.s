# Compiled using "xic" by Owen Arden, Catalin Dumitru, Wenzel Jakob, and Danfeng Zhang
# Command line: java -jar xic.jar -I runtime/include/ -I xth/tests/pa5 runtime/QtXi/examples/mandelbrot.xi

.file "runtime/QtXi/examples/mandelbrot.xi"
.att_syntax prefix
.text

.globl _I_Color_init_o5Coloriii
_I_Color_init_o5Coloriii:
	movq %rdi, %rax
	movq %rsi, 8(%rax)
	movq %rdx, 16(%rax)
	movq %rcx, 24(%rax)
	ret

.globl _I_MdbCalc_calc_p
_I_MdbCalc_calc_p:
	pushq %rbx
	pushq %r14
	pushq %r15
	movq %rdi, %r15
	movq _I_size_QTimer(%rip), %r14
	movq _I_g_plots_aaai(%rip), %rax
	movq _I_g_buffer_i(%rip), %rcx
	cmpq -8(%rax), %rcx
	jae call_abort
	movq (%rax,%rcx,8), %rbx
	movq _I_g_WINSIZE_i(%rip), %rax
	cmpq %rax, 16(%r15,%r14,1)
	jge .L81
	cmpq %rax, 24(%r15,%r14,1)
	jge .L83
	movq 32(%r15,%r14,1), %rdi
	movq 40(%r15,%r14,1), %rsi
	call _Imandelbrot_iii
	movq 16(%r15,%r14,1), %rcx
	cmpq -8(%rbx), %rcx
	jae call_abort
	movq (%rbx,%rcx,8), %rdx
	movq 24(%r15,%r14,1), %rcx
	cmpq -8(%rdx), %rcx
	jae call_abort
	movq %rax, (%rdx,%rcx,8)
	movq _I_g_zoom_i(%rip), %rax
	addq %rax, 40(%r15,%r14,1)
	incq 24(%r15,%r14,1)
.L84:
.L85:
	jmp .E5
.L81:
	movq $1, _I_g_done_b(%rip)
	jmp .L85
.L83:
	movq _I_g_zoom_i(%rip), %rax
	addq %rax, 32(%r15,%r14,1)
	movq _I_g_y__offset_i(%rip), %rax
	movq %rax, 40(%r15,%r14,1)
	movq $0, 24(%r15,%r14,1)
	incq 16(%r15,%r14,1)
	jmp .L84
.E5:
	popq %r15
	popq %r14
	popq %rbx
	ret

.globl _I_MdbCalc_init_o7MdbCalco9MdbWidget
_I_MdbCalc_init_o7MdbCalco9MdbWidget:
	pushq %rbx
	movq %rdi, %rbx
	movq _I_size_QTimer(%rip), %rax
	movq %rsi, 8(%rbx,%rax,1)
	movq _I_g_x__offset_i(%rip), %rcx
	movq %rcx, 32(%rbx,%rax,1)
	movq _I_g_y__offset_i(%rip), %rcx
	movq %rcx, 40(%rbx,%rax,1)
	movq $0, 16(%rbx,%rax,1)
	movq $0, 24(%rbx,%rax,1)
	call _Ireset__histogram_p
	movq %rbx, %rax
	popq %rbx
	ret

.globl _I_MdbCalc_timeout_p
_I_MdbCalc_timeout_p:
	pushq %rbx
	pushq %r12
	pushq %r14
	pushq %r15
	subq $8, %rsp
	movq %rdi, %r15
	movq _I_size_QTimer(%rip), %rbx
	movq _I_g_done_b(%rip), %rax
	movq $1, %rcx
	xorq %rax, %rcx
	testq %rcx, %rcx
	jnz .L67
.L68:
	jmp .E3
.L67:
	movq $1, %rcx
	xorq %rax, %rcx
	testq %rcx, %rcx
	jnz .L72
.L71:
	movq $1, %rcx
	xorq %rax, %rcx
	testq %rcx, %rcx
	jnz .L68
	movq 8(%r15,%rbx,1), %r14
	movq _I_g_WINSIZE_i(%rip), %rcx
	movq %rcx, %rdx
	xorq %rsi, %rsi
	xorq %rdi, %rdi
	call _Iqrect_o5QRectiiii
	movq %rax, %rsi
	movq %r14, %rdi
	movq (%r14), %rax
	callq *216(%rax)
	sarq $1, _I_g_size_i(%rip)
	leaq .L77(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iprint_pai
	movq _I_g_size_i(%rip), %rdi
	call _IunparseInt_aii
	movq %rax, %rdi
	call _Iprint_pai
	leaq .L50(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iprint_pai
	movq _I_g_final__size_i(%rip), %rax
	cmpq %rax, _I_g_size_i(%rip)
	jg .L79
	movq (%r15), %rax
	movq %r15, %rdi
	callq *56(%rax)
	jmp .L68
.L72:
	movq (%r15), %rax
	movq 96(%rax), %r12
	movq %r15, %rdi
	callq *%r12
	movq $1999, %r14
.L69:
	movq _I_g_done_b(%rip), %rax
	testq %rax, %rax
	jnz .L71
	cmpq $0, %r14
	jle .L71
	movq %r15, %rdi
	callq *%r12
	decq %r14
	jmp .L69
.L79:
	sarq $1, _I_g_zoom_i(%rip)
	movq _I_g_size_i(%rip), %rax
	sarq $1, %rax
	addq %rax, _I_g_x__offset_i(%rip)
	movq _I_g_size_i(%rip), %rax
	sarq $1, %rax
	addq %rax, _I_g_y__offset_i(%rip)
	movq _I_g_size_i(%rip), %rax
	movq _I_g_x__offset_i(%rip), %rcx
	addq %rax, %rcx
	movq %rcx, _I_g_x__final_i(%rip)
	movq _I_g_size_i(%rip), %rcx
	movq _I_g_y__offset_i(%rip), %rax
	addq %rcx, %rax
	movq %rax, _I_g_y__final_i(%rip)
	movq _I_g_x__offset_i(%rip), %rax
	movq %rax, 32(%r15,%rbx,1)
	movq _I_g_y__offset_i(%rip), %rax
	movq %rax, 40(%r15,%rbx,1)
	movq $0, 16(%r15,%rbx,1)
	movq $0, 24(%r15,%rbx,1)
	call _Iupdate__maxiter_p
	call _Ireset__histogram_p
	movq $0, _I_g_done_b(%rip)
	movq $1, %rax
	subq _I_g_buffer_i(%rip), %rax
	movq %rax, _I_g_buffer_i(%rip)
	jmp .L68
.E3:
	addq $8, %rsp
	popq %r15
	popq %r14
	popq %r12
	popq %rbx
	ret

.globl _I_MdbWidget_init_o9MdbWidget
_I_MdbWidget_init_o9MdbWidget:
	pushq %rbx
	pushq %r14
	subq $8, %rsp
	movq %rdi, %rbx
	leaq .L89(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iqs_o7QStringai
	movq (%rbx), %r14
	movq %rax, %rsi
	movq %rbx, %rdi
	callq *120(%r14)
	movq _I_g_WINSIZE_i(%rip), %rdi
	movq %rdi, %rsi
	call _Iqsize_o5QSizeii
	movq %rax, %rsi
	movq %rbx, %rdi
	callq *264(%r14)
	movq %rbx, %rax
	addq $8, %rsp
	popq %r14
	popq %rbx
	ret

.globl _I_MdbWidget_mouseDoubleClickEvent_po11QMouseEvent
_I_MdbWidget_mouseDoubleClickEvent_po11QMouseEvent:
	ret

.globl _I_MdbWidget_paintEvent_po11QPaintEvent
_I_MdbWidget_paintEvent_po11QPaintEvent:
	pushq %rbx
	pushq %rbp
	pushq %r12
	pushq %r13
	pushq %r14
	pushq %r15
	subq $328, %rsp
	movq %rdi, %rbx
	movq %rsi, %rdi
	movq (%rsi), %rax
	callq *16(%rax)
	movq %rbx, %rdi
	call _Iqpainter_o8QPaintero12QPaintDevice
	movq %rax, 32(%rsp)
	movq 32(%rsp), %rax
	movq %rax, 32(%rsp)
	movq _I_g_done_b(%rip), %rax
	movq %rax, 160(%rsp)
	movq $1, %rax
	xorq 160(%rsp), %rax
	testq %rax, %rax
	jnz .L92
	movq _I_g_buffer_i(%rip), %rax
.L93:
	movq _I_g_plots_aaai(%rip), %rcx
	cmpq -8(%rcx), %rax
	jae call_abort
	movq (%rcx,%rax,8), %r14
	movq _I_g_WINSIZE_i(%rip), %rcx
	movq %rcx, 232(%rsp)
	movq $0, 8(%rsp)
.L94:
	movq 8(%rsp), %rdx
	cmpq 232(%rsp), %rdx
	jge .L96
	xorq %r12, %r12
	cmpq $0, 232(%rsp)
	jle .L99
	movq -8(%r14), %rax
	movq %rax, 288(%rsp)
	xorq %r13, %r13
	movq 8(%rsp), %rax
	cmpq 288(%rsp), %rax
	setbb %r13b
	movq $1, 272(%rsp)
	movq 272(%rsp), %rcx
	xorq %r13, 272(%rsp)
	movq 272(%rsp), %rax
	testq %rax, 272(%rsp)
	jnz call_abort
	movq 8(%rsp), %rcx
	movq (%r14,%rcx,8), %rcx
	movq %rcx, 248(%rsp)
	movq 248(%rsp), %rax
	cmpq -8(%rax), %r12
	jae call_abort
	movq 248(%rsp), %rdx
	movq (%rdx,%r12,8), %rax
	movq %rax, 120(%rsp)
	movq 120(%rsp), %rdi
	call _Iassign__color_o5Colori
	movq %rax, %rbx
	movq 8(%rbx), %rax
	movq %rax, 64(%rsp)
	movq 16(%rbx), %rax
	movq %rax, 72(%rsp)
	movq 24(%rbx), %rax
	movq %rax, 216(%rsp)
	movq 216(%rsp), %rdx
	movq 72(%rsp), %rsi
	movq 64(%rsp), %rdi
	call _Iqcolor_o6QColoriii
	movq %rax, 192(%rsp)
	movq 192(%rsp), %rax
	movq %rax, 192(%rsp)
	movq 192(%rsp), %rdi
	call _Iqpen_o4QPeno6QColor
	movq %rax, 176(%rsp)
	movq 176(%rsp), %rax
	movq %rax, 176(%rsp)
	movq 32(%rsp), %rcx
	movq (%rcx), %rcx
	movq %rcx, 296(%rsp)
	movq 296(%rsp), %rax
	movq 112(%rax), %r15
	movq 176(%rsp), %rsi
	movq 32(%rsp), %rdi
	callq *%r15
	movq 8(%rbx), %rax
	movq %rax, 88(%rsp)
	movq 16(%rbx), %rax
	movq %rax, 56(%rsp)
	movq 24(%rbx), %rax
	movq %rax, 184(%rsp)
	movq 184(%rsp), %rdx
	movq 56(%rsp), %rsi
	movq 88(%rsp), %rdi
	call _Iqcolor_o6QColoriii
	movq %rax, 128(%rsp)
	movq 128(%rsp), %rax
	movq %rax, 128(%rsp)
	movq 128(%rsp), %rdi
	call _Iqbrush_o6QBrusho6QColor
	movq %rax, 280(%rsp)
	movq 280(%rsp), %rax
	movq %rax, 280(%rsp)
	movq 32(%rsp), %rax
	movq (%rax), %rax
	movq %rax, 152(%rsp)
	movq 152(%rsp), %rcx
	addq $128, 152(%rsp)
	movq 152(%rsp), %rax
	movq %rax, 152(%rsp)
	movq 152(%rsp), %rcx
	movq (%rcx), %rbx
	movq 280(%rsp), %rsi
	movq 32(%rsp), %rdi
	callq *%rbx
	movq $2, %rcx
	movq $2, %rdx
	movq %r12, %rsi
	movq 8(%rsp), %rdi
	call _Iqrect_o5QRectiiii
	movq %rax, 48(%rsp)
	movq 48(%rsp), %rax
	movq %rax, 48(%rsp)
	movq 32(%rsp), %rcx
	movq (%rcx), %rax
	movq %rax, 256(%rsp)
	movq 256(%rsp), %rcx
	addq $120, 256(%rsp)
	movq 256(%rsp), %rax
	movq %rax, 256(%rsp)
	movq 256(%rsp), %rcx
	movq (%rcx), %rbp
	movq 32(%rsp), %rdi
	callq *%rbp
	movq %rax, 168(%rsp)
	movq 168(%rsp), %rax
	movq %rax, 168(%rsp)
	movq 32(%rsp), %rax
	movq (%rax), %rax
	movq %rax, 312(%rsp)
	movq 312(%rsp), %rcx
	addq $200, 312(%rsp)
	movq 312(%rsp), %rax
	movq %rax, 312(%rsp)
	movq 312(%rsp), %rcx
	movq (%rcx), %rax
	movq %rax, (%rsp)
	movq 168(%rsp), %rdx
	movq 48(%rsp), %rsi
	movq 32(%rsp), %rdi
	callq *(%rsp)
	incq %r12
.L97:
	movq _I_g_WINSIZE_i(%rip), %rax
	movq %rax, 232(%rsp)
	cmpq 232(%rsp), %r12
	jge .L99
	movq $1, 112(%rsp)
	movq 112(%rsp), %rax
	xorq %r13, 112(%rsp)
	movq 112(%rsp), %rcx
	testq %rcx, 112(%rsp)
	jnz call_abort
	movq 8(%rsp), %rax
	movq (%r14,%rax,8), %rdx
	movq %rdx, 144(%rsp)
	movq 144(%rsp), %rcx
	cmpq -8(%rcx), %r12
	jae call_abort
	movq 144(%rsp), %rax
	movq (%rax,%r12,8), %rax
	movq %rax, 104(%rsp)
	movq 104(%rsp), %rdi
	call _Iassign__color_o5Colori
	movq %rax, 16(%rsp)
	movq 16(%rsp), %rcx
	movq %rcx, 16(%rsp)
	movq 16(%rsp), %rax
	movq 8(%rax), %rax
	movq %rax, 24(%rsp)
	movq 16(%rsp), %rcx
	movq 16(%rcx), %rax
	movq %rax, 136(%rsp)
	movq 16(%rsp), %rax
	movq 24(%rax), %rax
	movq %rax, 320(%rsp)
	movq 320(%rsp), %rdx
	movq 136(%rsp), %rsi
	movq 24(%rsp), %rdi
	call _Iqcolor_o6QColoriii
	movq %rax, 224(%rsp)
	movq 224(%rsp), %rax
	movq %rax, 224(%rsp)
	movq 224(%rsp), %rdi
	call _Iqpen_o4QPeno6QColor
	movq %rax, 208(%rsp)
	movq 208(%rsp), %rax
	movq %rax, 208(%rsp)
	movq 208(%rsp), %rsi
	movq 32(%rsp), %rdi
	callq *%r15
	movq 16(%rsp), %rdx
	movq 8(%rdx), %rax
	movq %rax, 80(%rsp)
	movq 16(%rsp), %rcx
	movq 16(%rcx), %rax
	movq %rax, 96(%rsp)
	movq 16(%rsp), %rax
	movq 24(%rax), %rax
	movq %rax, 304(%rsp)
	movq 304(%rsp), %rdx
	movq 96(%rsp), %rsi
	movq 80(%rsp), %rdi
	call _Iqcolor_o6QColoriii
	movq %rax, 200(%rsp)
	movq 200(%rsp), %rax
	movq %rax, 200(%rsp)
	movq 200(%rsp), %rdi
	call _Iqbrush_o6QBrusho6QColor
	movq %rax, 240(%rsp)
	movq 240(%rsp), %rax
	movq %rax, 240(%rsp)
	movq 240(%rsp), %rsi
	movq 32(%rsp), %rdi
	callq *%rbx
	movq $2, %rcx
	movq $2, %rdx
	movq %r12, %rsi
	movq 8(%rsp), %rdi
	call _Iqrect_o5QRectiiii
	movq %rax, 40(%rsp)
	movq 40(%rsp), %rax
	movq %rax, 40(%rsp)
	movq 32(%rsp), %rdi
	callq *%rbp
	movq %rax, 264(%rsp)
	movq 264(%rsp), %rax
	movq %rax, 264(%rsp)
	movq 264(%rsp), %rdx
	movq 40(%rsp), %rsi
	movq 32(%rsp), %rdi
	callq *(%rsp)
	incq %r12
	jmp .L97
.L92:
	movq $1, %rax
	subq _I_g_buffer_i(%rip), %rax
	jmp .L93
.L96:
	movq 32(%rsp), %rcx
	movq (%rcx), %rax
	movq 32(%rsp), %rdi
	callq *16(%rax)
	jmp .E9
.L99:
	incq 8(%rsp)
	jmp .L94
.E9:
	addq $328, %rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r12
	popq %rbp
	popq %rbx
	ret

.globl _I_init_Color
_I_init_Color:
	ret

.globl _I_init_MdbCalc
_I_init_MdbCalc:
	subq $8, %rsp
	cmpq $0, _I_size_MdbCalc(%rip)
	jne .L86
	call _I_init_QTimer
	movq _I_size_QTimer(%rip), %rax
	addq $40, %rax
	movq %rax, _I_size_MdbCalc(%rip)
	xorq %rax, %rax
.L87:
	cmpq $10, %rax
	jge .L88
	leaq _I_vt_QTimer(%rip), %rcx
	movq (%rcx,%rax,8), %rcx
	leaq _I_vt_MdbCalc(%rip), %rdx
	movq %rcx, (%rdx,%rax,8)
	incq %rax
	jmp .L87
.L88:
	leaq _I_MdbCalc_timeout_p(%rip), %rax
	movq %rax, _I_vt_MdbCalc+64(%rip)
	movq $0, _I_vt_MdbCalc+80(%rip)
	leaq _I_MdbCalc_init_o7MdbCalco9MdbWidget(%rip), %rax
	movq %rax, _I_vt_MdbCalc+88(%rip)
	leaq _I_MdbCalc_calc_p(%rip), %rax
	movq %rax, _I_vt_MdbCalc+96(%rip)
.L86:
	addq $8, %rsp
	ret

.globl _I_init_MdbWidget
_I_init_MdbWidget:
	subq $8, %rsp
	cmpq $0, _I_size_MdbWidget(%rip)
	jne .L101
	call _I_init_QWidget
	movq _I_size_QWidget(%rip), %rax
	movq %rax, _I_size_MdbWidget(%rip)
	xorq %rax, %rax
.L102:
	cmpq $78, %rax
	jge .L103
	leaq _I_vt_QWidget(%rip), %rcx
	movq (%rcx,%rax,8), %rdx
	leaq _I_vt_MdbWidget(%rip), %rcx
	movq %rdx, (%rcx,%rax,8)
	incq %rax
	jmp .L102
.L103:
	leaq _I_MdbWidget_paintEvent_po11QPaintEvent(%rip), %rax
	movq %rax, _I_vt_MdbWidget+496(%rip)
	leaq _I_MdbWidget_mouseDoubleClickEvent_po11QMouseEvent(%rip), %rax
	movq %rax, _I_vt_MdbWidget+520(%rip)
	movq $0, _I_vt_MdbWidget+624(%rip)
	leaq _I_MdbWidget_init_o9MdbWidget(%rip), %rax
	movq %rax, _I_vt_MdbWidget+632(%rip)
.L101:
	addq $8, %rsp
	ret

.globl _I_init_colors_ao5Color
_I_init_colors_ao5Color:
	pushq %rbx
	movq _I_g_HISTLEN_i(%rip), %rbx
	leaq 8(,%rbx,8), %rdi
	call _I_alloc_i
	addq $8, %rax
	movq %rbx, -8(%rax)
	movq %rax, _I_g_colors_ao5Color(%rip)
	popq %rbx
	ret

.globl _I_init_histogram_ai
_I_init_histogram_ai:
	pushq %rbx
	movq _I_g_HISTLEN_i(%rip), %rbx
	leaq 8(,%rbx,8), %rdi
	call _I_alloc_i
	addq $8, %rax
	movq %rbx, -8(%rax)
	movq %rax, _I_g_histogram_ai(%rip)
	popq %rbx
	ret

.globl _I_init_plots_aaai
_I_init_plots_aaai:
	subq $8, %rsp
	movq $24, %rdi
	call _I_alloc_i
	addq $8, %rax
	movq $2, -8(%rax)
	movq %rax, _I_g_plots_aaai(%rip)
	addq $8, %rsp
	ret

.globl _Iassign__color_o5Colori
_Iassign__color_o5Colori:
	pushq %rbx
	pushq %r12
	pushq %r13
	pushq %r14
	pushq %r15
	movq %rdi, %r12
	movq _I_g_colors_ao5Color(%rip), %rax
	cmpq -8(%rax), %r12
	jae call_abort
	movq (%rax,%r12,8), %rax
	cmpq $0, %rax
	je .L115
.L116:
	jmp .E6
.L115:
	movq _I_g_maxiter_i(%rip), %rax
	cmpq %rax, %r12
	jne .L118
	movq _I_size_Color(%rip), %rdi
	call _I_alloc_i
	leaq _I_vt_Color(%rip), %rcx
	movq %rcx, (%rax)
	movq _I_g_colors_ao5Color(%rip), %rdx
	movq _I_g_maxiter_i(%rip), %rcx
	cmpq -8(%rdx), %rcx
	jae call_abort
	movq %rax, (%rdx,%rcx,8)
	jmp .L116
.L118:
	movq %r12, %r15
	movq %r15, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq _I_g_RAMPSIZE_i(%rip)
	movq %rax, %r15
	movq _I_g_RAMPS_i(%rip), %rsi
	movq _I_g_RAMPSIZE_i(%rip), %rcx
	movq %r15, %rdi
	movq %rdi, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rsi
	movq %rdx, %rdi
	movq %r12, %r14
	movq %r14, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rcx
	movq %rdx, %r14
	movq %r15, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rsi
	movq %rax, %r15
	movq %r15, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rsi
	movq %rdx, %r15
	call _Ibase__color_o5Colori
	movq %rax, %rbx
	cmpq $0, %r15
	jne .L120
	movq _I_size_Color(%rip), %r15
	movq %r15, %rdi
	call _I_alloc_i
	leaq _I_vt_Color(%rip), %rcx
	movq %rcx, (%rax)
	movq %rax, %r8
.L121:
	sarq $1, 8(%r8)
	sarq $1, 16(%r8)
	sarq $1, 24(%r8)
	movq _I_g_RAMPSIZE_i(%rip), %rsi
	subq $1, %rsi
	movq 8(%r8), %rdi
	movq 8(%rbx), %rax
	subq %rdi, %rax
	imulq %r14, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rsi
	addq %rax, %rdi
	movq 16(%r8), %rcx
	movq 16(%rbx), %rax
	subq %rcx, %rax
	imulq %r14, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rsi
	addq %rax, %rcx
	movq 24(%r8), %r8
	movq 24(%rbx), %rax
	subq %r8, %rax
	imulq %r14, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rsi
	addq %rax, %r8
	movq %r8, %rsi
	movq $255, %rdx
	subq %rdi, %rdx
	movq %rdx, %rax
	imulq %rdx, %rax
	movq $255, %rdi
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rdi
	movq $255, %r14
	subq %rax, %r14
	movq $255, %rdx
	subq %rcx, %rdx
	movq %rdx, %rax
	imulq %rdx, %rax
	movq $255, %rcx
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rcx
	movq $255, %rbx
	subq %rax, %rbx
	movq $255, %rcx
	subq %rsi, %rcx
	movq %rcx, %rax
	imulq %rcx, %rax
	movq $255, %rcx
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rcx
	movq $255, %r13
	subq %rax, %r13
	movq %r15, %rdi
	call _I_alloc_i
	leaq _I_vt_Color(%rip), %rcx
	movq %rcx, (%rax)
	movq %r13, %rcx
	movq %rbx, %rdx
	movq %r14, %rsi
	movq %rax, %rdi
	movq (%rax), %rax
	callq *8(%rax)
	movq _I_g_colors_ao5Color(%rip), %rcx
	cmpq -8(%rcx), %r12
	jae call_abort
	movq %rax, (%rcx,%r12,8)
	movq _I_g_colors_ao5Color(%rip), %rax
	cmpq -8(%rax), %r12
	jae call_abort
	movq (%rax,%r12,8), %rax
	jmp .L116
.L120:
	movq %r15, %rdi
	call _Ibase__color_o5Colori
	movq _I_size_Color(%rip), %r15
	movq %rax, %r8
	jmp .L121
.E6:
	popq %r15
	popq %r14
	popq %r13
	popq %r12
	popq %rbx
	ret

.globl _Ibase__color_o5Colori
_Ibase__color_o5Colori:
	pushq %rbx
	pushq %r12
	pushq %r14
	pushq %r15
	subq $8, %rsp
	movq %rdi, %rbx
	movq _I_size_Color(%rip), %rdi
	call _I_alloc_i
	movq %rax, %r14
	leaq _I_vt_Color(%rip), %rax
	movq %rax, (%r14)
	leaq 6(%rbx), %rdi
	call _Iwheel_ii
	movq %rax, %r15
	leaq 10(%rbx), %rdi
	call _Iwheel_ii
	movq %rax, %r12
	addq $2, %rbx
	movq %rbx, %rdi
	call _Iwheel_ii
	movq %rax, %rcx
	movq %r12, %rdx
	movq %r15, %rsi
	movq %r14, %rdi
	movq (%r14), %rax
	callq *8(%rax)
	addq $8, %rsp
	popq %r15
	popq %r14
	popq %r12
	popq %rbx
	ret

.globl _Imain_paai
_Imain_paai:
	pushq %rbx
	pushq %r14
	pushq %r15
	subq $16, %rsp
	movq %rdi, %rbx
	call _IsetupGlobals_p
	movq %rbx, %rsi
	movq %rsp, %rdi
	call _Iqapplication_t2o12QApplicationaaiaai
	movq (%rsp), %rbx
	movq 8(%rsp), %rcx
	xorq %rdx, %rdx
	movq -8(%rcx), %rax
	cmpq $2, %rax
	jl .L19
	cmpq $1, %rax
	jbe call_abort
	movq 8(%rcx), %rsi
	movq %rsp, %rdi
	call _IparseInt_t2ibai
	movq (%rsp), %rdx
.L19:
	cmpq $8, %rdx
	jne .L21
	movq $-2670932, _I_g_x__offset_i(%rip)
	movq $17309052, _I_g_y__offset_i(%rip)
	movq $200000, _I_g_final__size_i(%rip)
.L22:
	movq _I_g_size_i(%rip), %rax
	sarq $1, %rax
	subq %rax, _I_g_x__offset_i(%rip)
	movq _I_g_size_i(%rip), %rax
	sarq $1, %rax
	subq %rax, _I_g_y__offset_i(%rip)
	movq _I_g_size_i(%rip), %rax
	movq _I_g_x__offset_i(%rip), %rcx
	addq %rax, %rcx
	movq %rcx, _I_g_x__final_i(%rip)
	movq _I_g_size_i(%rip), %rax
	movq _I_g_y__offset_i(%rip), %rcx
	addq %rax, %rcx
	movq %rcx, _I_g_y__final_i(%rip)
	movq _I_size_MdbWidget(%rip), %rdi
	call _I_alloc_i
	leaq _I_vt_MdbWidget(%rip), %rcx
	movq %rcx, (%rax)
	movq %rax, %rdi
	movq (%rax), %rax
	callq *632(%rax)
	movq %rax, %r14
	movq %r14, %rdi
	movq (%r14), %rax
	callq *32(%rax)
	movq _I_size_MdbCalc(%rip), %rdi
	call _I_alloc_i
	leaq _I_vt_MdbCalc(%rip), %rcx
	movq %rcx, (%rax)
	movq %r14, %rsi
	movq %rax, %rdi
	movq (%rax), %rax
	callq *88(%rax)
	movq %rax, %r14
	movq (%r14), %r15
	xorq %rsi, %rsi
	movq %r14, %rdi
	callq *40(%r15)
	movq $5, %rsi
	movq %r14, %rdi
	callq *32(%r15)
	movq %r14, %rdi
	callq *48(%r15)
	movq %rbx, %rdi
	movq (%rbx), %rax
	callq *8(%rax)
	jmp .E4
.L38:
	movq $-11884460, _I_g_x__offset_i(%rip)
	movq $5703649, _I_g_y__offset_i(%rip)
	movq _I_g_WINSIZE_i(%rip), %rax
	leaq (,%rax,2), %rax
	movq %rax, _I_g_final__size_i(%rip)
.L40:
.L37:
.L34:
.L31:
.L28:
.L25:
	jmp .L22
.L39:
	movq $0, _I_g_x__offset_i(%rip)
	movq $0, _I_g_y__offset_i(%rip)
	movq _I_g_size_i(%rip), %rax
	movq %rax, _I_g_final__size_i(%rip)
	jmp .L40
.L21:
	cmpq $1, %rdx
	jne .L24
	movq $-12560912, _I_g_x__offset_i(%rip)
	movq $1677722, _I_g_y__offset_i(%rip)
	movq $20000, _I_g_final__size_i(%rip)
	jmp .L25
.L24:
	cmpq $2, %rdx
	jne .L27
	movq $4563275, _I_g_x__offset_i(%rip)
	movq $8073700, _I_g_y__offset_i(%rip)
	movq _I_g_WINSIZE_i(%rip), %rax
	leaq (,%rax,2), %rax
	movq %rax, _I_g_final__size_i(%rip)
	jmp .L28
.L27:
	cmpq $3, %rdx
	jne .L30
	movq $-22960316, _I_g_x__offset_i(%rip)
	movq $83888, _I_g_y__offset_i(%rip)
	movq $10000, _I_g_final__size_i(%rip)
	jmp .L31
.L30:
	cmpq $4, %rdx
	jne .L33
	movq $-29490000, _I_g_x__offset_i(%rip)
	movq $0, _I_g_y__offset_i(%rip)
	movq $400000, _I_g_final__size_i(%rip)
	jmp .L34
.L33:
	cmpq $5, %rdx
	jne .L36
	movq $-1476396, _I_g_x__offset_i(%rip)
	movq $10978300, _I_g_y__offset_i(%rip)
	movq $20000, _I_g_final__size_i(%rip)
	jmp .L37
.L36:
	cmpq $7, %rdx
	je .L38
	jmp .L39
.E4:
	addq $16, %rsp
	popq %r15
	popq %r14
	popq %rbx
	ret

.globl _Imandelbrot_iii
_Imandelbrot_iii:
	pushq %rbx
	pushq %rbp
	pushq %r12
	pushq %r13
	pushq %r14
	pushq %r15
	subq $80, %rsp
	movq $0, (%rsp)
	movq _I_g_SCALEROOT_i(%rip), %r9
	salq $2, %r9
	imulq _I_g_SCALEROOT_i(%rip), %r9
	movq _I_g_SCALEROOT_i(%rip), %r11
	cmpq $0, %r9
	jle .L124
	movq _I_g_maxiter_i(%rip), %rbp
	cmpq $0, %rbp
	jle .L124
	xorq %r8, %r8
	movq %r8, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq _I_g_SCALEROOT_i(%rip)
	movq %rax, %r8
	xorq %rcx, %rcx
	movq %rcx, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq _I_g_SCALEROOT_i(%rip)
	movq %rdx, %rcx
	movq %r8, %r10
	imulq %r8, %r10
	movq %r8, 64(%rsp)
	movq 64(%rsp), %rax
	imulq %rcx, %rax
	movq %rax, 64(%rsp)
	movq 64(%rsp), %rdx
	leaq (,%rdx,2), %rdx
	movq %rdx, 24(%rsp)
	movq 24(%rsp), %rax
	movq %rax, 24(%rsp)
	movq 24(%rsp), %rax
	movq 24(%rsp), %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %r11
	movq %rax, 24(%rsp)
	addq 24(%rsp), %r10
	movq %r10, %r12
	movq %r8, %r10
	imulq %r8, %r10
	movq %r8, 72(%rsp)
	movq 72(%rsp), %rax
	imulq %rcx, %rax
	movq %rax, 72(%rsp)
	movq 72(%rsp), %rdx
	leaq (,%rdx,2), %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %r11
	addq %rax, %r10
	movq %r10, %r14
	leaq (%rdi,%r12,1), %rax
	subq %r14, %rax
	movq %rax, %r15
	leaq (,%r8,2), %rdx
	movq %rdx, 48(%rsp)
	movq 48(%rsp), %rax
	movq %rax, 48(%rsp)
	movq 48(%rsp), %rdx
	imulq %r8, %rdx
	movq %rdx, 48(%rsp)
	movq %rsi, 32(%rsp)
	movq 32(%rsp), %rax
	addq 48(%rsp), %rax
	movq %rax, 32(%rsp)
	movq %r8, %rax
	imulq %rcx, %rax
	leaq (,%rax,2), %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %r11
	movq 32(%rsp), %rdx
	movq %rdx, 32(%rsp)
	movq 32(%rsp), %rdx
	addq %rax, 32(%rsp)
	imulq %rcx, %r8
	leaq (,%r8,2), %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %r11
	movq 32(%rsp), %rcx
	movq %rcx, 32(%rsp)
	movq 32(%rsp), %rcx
	addq %rax, 32(%rsp)
	movq 32(%rsp), %rcx
	movq $1, (%rsp)
.L122:
	addq %r14, %r12
	cmpq %r9, %r12
	jge .L124
	cmpq %rbp, (%rsp)
	jge .L124
	movq %r15, %r8
	movq %r8, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %r11
	movq %rax, %r8
	movq %r15, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %r11
	movq %rdx, %r15
	movq %r15, %r10
	movq %rcx, %rbx
	movq %rbx, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %r11
	movq %rax, %rbx
	movq %rcx, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %r11
	movq %rdx, %rcx
	movq %r8, %r14
	imulq %r8, %r14
	movq %r8, 56(%rsp)
	movq 56(%rsp), %rax
	imulq %r10, %rax
	movq %rax, 56(%rsp)
	movq 56(%rsp), %rdx
	leaq (,%rdx,2), %rdx
	movq %rdx, 40(%rsp)
	movq 40(%rsp), %rax
	movq %rax, 40(%rsp)
	movq 40(%rsp), %rax
	movq 40(%rsp), %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %r11
	movq %rax, 40(%rsp)
	addq 40(%rsp), %r14
	movq %r14, %r12
	movq %rbx, %r14
	imulq %rbx, %r14
	movq %rbx, %rax
	imulq %rcx, %rax
	leaq (,%rax,2), %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %r11
	addq %rax, %r14
	leaq (%rdi,%r12,1), %rax
	subq %r14, %rax
	movq %rax, %r15
	leaq (,%r8,2), %rax
	imulq %rbx, %rax
	leaq (%rsi,%rax,1), %r13
	imulq %rcx, %r8
	leaq (,%r8,2), %rax
	movq %rax, 8(%rsp)
	movq 8(%rsp), %rcx
	movq %rcx, 8(%rsp)
	movq 8(%rsp), %rcx
	movq 8(%rsp), %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %r11
	movq %rax, 8(%rsp)
	addq 8(%rsp), %r13
	imulq %r10, %rbx
	leaq (,%rbx,2), %rax
	movq %rax, 16(%rsp)
	movq 16(%rsp), %rcx
	movq %rcx, 16(%rsp)
	movq 16(%rsp), %rcx
	movq 16(%rsp), %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %r11
	movq %rax, 16(%rsp)
	movq %r13, %rcx
	addq 16(%rsp), %rcx
	incq (%rsp)
	jmp .L122
.L128:
	jmp .E7
.L124:
	movq _I_g_histogram_ai(%rip), %rcx
	movq (%rsp), %rdx
	cmpq -8(%rcx), %rdx
	jae call_abort
	movq (%rsp), %rax
	cmpq -8(%rcx), %rax
	jae call_abort
	movq (%rsp), %rdx
	incq (%rcx,%rdx,8)
	movq (%rsp), %rax
	movq %rax, (%rsp)
	jmp .L128
.E7:
	movq (%rsp), %rax
	addq $80, %rsp
	popq %r15
	popq %r14
	popq %r13
	popq %r12
	popq %rbp
	popq %rbx
	ret

.globl _ImkMatrix_aaii
_ImkMatrix_aaii:
	pushq %rbx
	pushq %r12
	pushq %r14
	pushq %r15
	subq $8, %rsp
	movq %rdi, %r15
	leaq 8(,%r15,8), %rdi
	call _I_alloc_i
	leaq 8(%rax), %r14
	movq %r15, -8(%r14)
	cmpq $0, %r15
	jle .L5
	leaq 8(,%r15,8), %rdi
	call _I_alloc_i
	addq $8, %rax
	movq %r15, -8(%rax)
	movq -8(%r14), %rbx
	cmpq $0, %rbx
	jbe call_abort
	movq %rax, (%r14)
	movq $1, %r12
.L3:
	cmpq %r15, %r12
	jge .L5
	leaq 8(,%r15,8), %rdi
	call _I_alloc_i
	addq $8, %rax
	movq %r15, -8(%rax)
	cmpq %rbx, %r12
	jae call_abort
	movq %rax, (%r14,%r12,8)
	incq %r12
	jmp .L3
.L13:
	jmp .E1
.L5:
	jmp .L13
.E1:
	movq %r14, %rax
	addq $8, %rsp
	popq %r15
	popq %r14
	popq %r12
	popq %rbx
	ret

.globl _Iprint__histogram_p
_Iprint__histogram_p:
	pushq %rbx
	xorq %rbx, %rbx
	movq _I_g_maxiter_i(%rip), %rcx
	cmpq $0, %rcx
	jl .L57
	movq _I_g_histogram_ai(%rip), %rdx
	cmpq $0, -8(%rdx)
	jbe call_abort
	cmpq $0, (%rdx)
	je .L64
	xorq %rdi, %rdi
	call _IunparseInt_aii
	movq %rax, %rdi
	call _Iprint_pai
	leaq .L60(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iprint_pai
	movq _I_g_histogram_ai(%rip), %rcx
	cmpq $0, -8(%rcx)
	jbe call_abort
	movq (%rcx), %rdi
	call _IunparseInt_aii
	movq %rax, %rdi
	call _Iprint_pai
	leaq .L61(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iprint_pai
	movq _I_g_maxiter_i(%rip), %rcx
	movq $1, %rbx
.L64:
.L55:
	cmpq %rcx, %rbx
	jg .L57
	movq _I_g_histogram_ai(%rip), %rax
	cmpq -8(%rax), %rbx
	jae call_abort
	cmpq $0, (%rax,%rbx,8)
	je .L59
	movq %rbx, %rdi
	call _IunparseInt_aii
	movq %rax, %rdi
	call _Iprint_pai
	leaq .L60(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iprint_pai
	movq _I_g_histogram_ai(%rip), %rcx
	cmpq -8(%rcx), %rbx
	jae call_abort
	movq (%rcx,%rbx,8), %rdi
	call _IunparseInt_aii
	movq %rax, %rdi
	call _Iprint_pai
	leaq .L61(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iprint_pai
	movq _I_g_maxiter_i(%rip), %rcx
	incq %rbx
.L59:
	jmp .L55
.L57:
	leaq .L50(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iprint_pai
	popq %rbx
	ret

.globl _Ireset__histogram_p
_Ireset__histogram_p:
	movq _I_g_maxiter_i(%rip), %rax
	cmpq $0, %rax
	jl .L53
	movq _I_g_histogram_ai(%rip), %rax
	cmpq $0, -8(%rax)
	jbe call_abort
	movq $0, (%rax)
	movq $1, %rax
.L51:
	movq _I_g_maxiter_i(%rip), %rcx
	cmpq %rcx, %rax
	jg .L53
	movq _I_g_histogram_ai(%rip), %rcx
	cmpq -8(%rcx), %rax
	jae call_abort
	movq $0, (%rcx,%rax,8)
	incq %rax
	jmp .L51
.L53:
	ret

.globl _IsetupGlobals_p
_IsetupGlobals_p:
	pushq %rbx
	movq _I_g_SCALEROOT_i(%rip), %rax
	movq %rax, %rcx
	imulq %rax, %rcx
	movq %rcx, _I_g_scale_i(%rip)
	movq _I_g_scale_i(%rip), %rax
	imulq $6, %rax
	movq %rax, _I_g_size_i(%rip)
	movq _I_g_WINSIZE_i(%rip), %rcx
	movq _I_g_size_i(%rip), %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rcx
	movq %rax, _I_g_zoom_i(%rip)
	movq $200000, _I_g_final__size_i(%rip)
	movq $0, _I_g_buffer_i(%rip)
	movq $0, _I_g_done_b(%rip)
	movq _I_g_WINSIZE_i(%rip), %rax
	leaq 1(%rax), %rdi
	call _ImkMatrix_aaii
	movq _I_g_plots_aaai(%rip), %rcx
	cmpq $0, -8(%rcx)
	jbe call_abort
	movq %rax, (%rcx)
	movq $1, %rbx
.L14:
	cmpq $2, %rbx
	jge .L16
	movq _I_g_WINSIZE_i(%rip), %rdi
	addq $1, %rdi
	call _ImkMatrix_aaii
	movq _I_g_plots_aaai(%rip), %rcx
	cmpq -8(%rcx), %rbx
	jae call_abort
	movq %rax, (%rcx,%rbx,8)
	incq %rbx
	jmp .L14
.L16:
	popq %rbx
	ret

.globl _Iupdate__maxiter_p
_Iupdate__maxiter_p:
	subq $8, %rsp
	movq _I_g_maxiter_i(%rip), %rdx
	movq %rdx, %rdi
	subq $5, %rdi
	xorq %rax, %rax
	cmpq %rdx, %rdi
	jge .L43
	movq _I_g_histogram_ai(%rip), %rcx
	cmpq -8(%rcx), %rdi
	jae call_abort
	movq (%rcx,%rdi,8), %rax
	incq %rdi
.L41:
	cmpq %rdx, %rdi
	jge .L43
	cmpq -8(%rcx), %rdi
	jae call_abort
	addq (%rcx,%rdi,8), %rax
	incq %rdi
	jmp .L41
.L45:
	movq _I_g_maxiter_i(%rip), %rax
	leaq (%rax,%rax,2), %rax
	sarq $1, %rax
	movq %rax, _I_g_maxiter_i(%rip)
	movq _I_g_MAXITER_i(%rip), %rax
	cmpq %rax, _I_g_maxiter_i(%rip)
	jle .L48
	movq %rax, _I_g_maxiter_i(%rip)
.L48:
	leaq .L49(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iprint_pai
	movq _I_g_maxiter_i(%rip), %rdi
	call _IunparseInt_aii
	movq %rax, %rdi
	call _Iprint_pai
	leaq .L50(%rip), %rdi
	call strdup
	movq %rax, %rdi
	call _Iprint_pai
.L46:
	jmp .E2
.L43:
	movq _I_g_WINSIZE_i(%rip), %rcx
	imulq $10000, %rax
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rcx
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rcx
	cmpq _I_g_GROW__ITER__PERCENT_i(%rip), %rax
	jg .L45
	jmp .L46
.E2:
	addq $8, %rsp
	ret

.globl _Iwheel_ii
_Iwheel_ii:
	movq %rdi, %rax
	movq $12, %rcx
	movq %rax, %rdx
	sarq $63, %rdx
	idivq %rcx
	movq %rdx, %rax
	cmpq $5, %rax
	jne .L107
.L105:
	movq $128, %rax
.L108:
	jmp .E22
.L107:
	cmpq $11, %rax
	je .L105
	cmpq $5, %rax
	jge .L111
	movq $255, %rax
	jmp .L108
.L111:
	xorq %rax, %rax
	jmp .L108
.E22:
	ret

strdup:
	pushq %rbx
	pushq %r14
	subq $8, %rsp
	movq %rdi, %r14
	movq (%r14), %rbx
	leaq 8(,%rbx,8), %rdi
	call _I_alloc_i
.L129:
	movq (%r14,%rbx,8), %rcx
	movq %rcx, (%rax,%rbx,8)
	decq %rbx
	cmpq $0, %rbx
	jge .L129
	addq $8, %rax
	addq $8, %rsp
	popq %r14
	popq %rbx
	ret

	.bss
	.align 8
.globl _I_size_MdbCalc
_I_size_MdbCalc:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_vt_MdbCalc
_I_vt_MdbCalc:
	.zero 104
	.text

.section .ctors
	.align 8
	.quad _I_init_MdbCalc
	.text

	.bss
	.align 8
.globl _I_size_MdbWidget
_I_size_MdbWidget:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_vt_MdbWidget
_I_vt_MdbWidget:
	.zero 640
	.text

.section .ctors
	.align 8
	.quad _I_init_MdbWidget
	.text

	.section .rodata
	.align 8
.globl _I_size_Color
_I_size_Color:
	.quad 32
	.text

	.section .rodata
	.align 8
.globl _I_vt_Color
_I_vt_Color:
	.quad 0
	.quad _I_Color_init_o5Coloriii
	.text

	.section .rodata
	.align 8
.L50:
	.quad 1
	.quad 10
	.text

	.section .rodata
	.align 8
.L61:
	.quad 1
	.quad 32
	.text

	.section .rodata
	.align 8
.L60:
	.quad 1
	.quad 58
	.text

	.section .rodata
	.align 8
.L89:
	.quad 10
	.quad 77
	.quad 97
	.quad 110
	.quad 100
	.quad 101
	.quad 108
	.quad 98
	.quad 114
	.quad 111
	.quad 116
	.text

	.section .rodata
	.align 8
.L77:
	.quad 15
	.quad 99
	.quad 117
	.quad 114
	.quad 114
	.quad 101
	.quad 110
	.quad 116
	.quad 32
	.quad 115
	.quad 105
	.quad 122
	.quad 101
	.quad 32
	.quad 61
	.quad 32
	.text

	.section .rodata
	.align 8
.L49:
	.quad 10
	.quad 109
	.quad 97
	.quad 120
	.quad 105
	.quad 116
	.quad 101
	.quad 114
	.quad 32
	.quad 61
	.quad 32
	.text

call_abort:
	call _I_outOfBounds_p
	.section .data
	.align 8
.globl _I_g_WINSIZE_i
_I_g_WINSIZE_i:
	.quad 256
	.text

	.section .data
	.align 8
.globl _I_g_MAXITER_i
_I_g_MAXITER_i:
	.quad 2000
	.text

	.section .data
	.align 8
.globl _I_g_HISTLEN_i
_I_g_HISTLEN_i:
	.quad 2001
	.text

	.section .data
	.align 8
.globl _I_g_maxiter_i
_I_g_maxiter_i:
	.quad 100
	.text

	.section .data
	.align 8
.globl _I_g_GROW__ITER__PERCENT_i
_I_g_GROW__ITER__PERCENT_i:
	.quad 1
	.text

	.section .data
	.align 8
.globl _I_g_SCALEROOT_i
_I_g_SCALEROOT_i:
	.quad 4096
	.text

	.bss
	.align 8
.globl _I_g_scale_i
_I_g_scale_i:
	.zero 8
	.text

	.section .data
	.align 8
.globl _I_g_final__size_i
_I_g_final__size_i:
	.quad 200000
	.text

	.section .data
	.align 8
.globl _I_g_buffer_i
_I_g_buffer_i:
	.quad 0
	.text

	.section .data
	.align 8
.globl _I_g_done_b
_I_g_done_b:
	.quad 0
	.text

	.section .data
	.align 8
.globl _I_g_RAMPSIZE_i
_I_g_RAMPSIZE_i:
	.quad 8
	.text

	.section .data
	.align 8
.globl _I_g_RAMPS_i
_I_g_RAMPS_i:
	.quad 12
	.text

	.bss
	.align 8
.globl _I_g_size_i
_I_g_size_i:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_g_zoom_i
_I_g_zoom_i:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_g_x__offset_i
_I_g_x__offset_i:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_g_y__offset_i
_I_g_y__offset_i:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_g_x__final_i
_I_g_x__final_i:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_g_y__final_i
_I_g_y__final_i:
	.zero 8
	.text

	.bss
	.align 8
.globl _I_g_histogram_ai
_I_g_histogram_ai:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_histogram_ai
	.text

	.bss
	.align 8
.globl _I_g_colors_ao5Color
_I_g_colors_ao5Color:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_colors_ao5Color
	.text

	.bss
	.align 8
.globl _I_g_plots_aaai
_I_g_plots_aaai:
	.zero 8
	.text

.section .ctors
	.align 8
	.quad _I_init_plots_aaai
	.text

