def subwords (w , n ):
	""" List all subwords of word w that have length n.
	>>> 'conc ' + 'atenate '
	'concatenate '
	>>> w = 'spot '
	>>> w[len (w):]
	''
	>>> subwords (w, 2)
	[ 'sp ', 'so ', 'st ', 'po ', 'pt ', 'ot ']
	>>> subwords (w, 3)
	[ 'spo ', 'spt ', 'sot ', 'pot ']
	"""
	if n == 0:
		return ['']
	if w == '':
		return []
	r = [w[0] + x for x in w[1:]]
	return r + subwords (w[1:] ,n)

def overlap(word1, word2):
	if word1 == word2:
		return word1
	return overlap(word1[1:], word2[:len(word2) - 1])