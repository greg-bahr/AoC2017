import itertools

input = [map(int, x.split()) for x in open('input').read().strip().split('\n')]

total = 0
for row in input:
    total += max(row)-min(row)

print total

print sum([max(x)-min(x) for x in input])

# Part 2

total = 0

for row in input:
    for x in range(len(row)):
        for y in range(len(row)-1):
                if row[x] % row[(x + y + 1)%len(row)] == 0:
                    total += row[x] / row[(x + y + 1)%len(row)]

print total

print sum([max(i) / min(i)  for x in input for i in itertools.combinations(x, 2) if max(i)%min(i)==0])
