import pdb

def parse():
	lines = []
	with open('PA3_Summary.txt', 'r') as f:
		lines = f.readlines()

	# list of index where the test cases start
	indices = [idx for idx,line in enumerate(lines) if line == 'Content of test case:\n']

	names = []
	tests = []
	sols = []
	for idx,index in enumerate(indices):
		# filename
		names.append(lines[index+1].split('/')[-1][:-1])
		
		i = index+2
		
		# test file
		test = ''
		while (lines[i] != 'Compiler\'s standard output:\n'):
			line = lines[i]
			try:
				test += line[line.index(' ')+1:]
			except ValueError:
				test += '\n'
			i += 1
		tests.append(test)

		# solution file
		solution = ''
		while (i < len(lines) and 'Expected result for ' in lines[i]):
			print "***"
			print lines[i]
			print i
			print "***"
			i += 1
		i += 2
		pdb.set_trace()
		while (lines[i][:3] != 'xic'):
			line = lines[i]
			try:
				solution += line[line.index(' ')+1:]
			except ValueError:
				pass
			i += 1
		sols.append(solution)

	return names,tests,sols

def save(names,tests,sols):
	for i in range(len(names)):
		with open('files/' + names[i],'w') as f:
			f.write(tests[i])
		with open('files/' + names[i][:-3]+'.ssol.nml','w') as f:
			f.write(sols[i])


if __name__ == "__main__":
	names,tests,sols = parse()
	save(names,tests,sols)