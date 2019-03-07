	.text
	.global diff
	.type diff, @function
diff:
	sltu	$t0, $a0, $a1	#check if a < b
	beq		$t0, $zero, L1	#if a > b jump to L1
	subu	$v0, $a1, $a0	#v0 = b - a
	jr		$ra				#return to caller
L1:								
	subu	$v0, $a0, $a1	#v0 = a - b
	jr		$ra
