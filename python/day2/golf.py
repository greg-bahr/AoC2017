import itertools

input = [map(int, x.split()) for x in open('input').read().strip().split('\n')]

print sum([max(x)-min(x) for x in input])
print sum([max(i) / min(i)  for x in input for i in itertools.combinations(x, 2) if max(i)%min(i)==0])
