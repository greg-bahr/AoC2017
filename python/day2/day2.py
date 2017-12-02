input = open('input').read().strip()
input = [map(int, x.split()) for x in input.split('\n')]

total = 0

for row in input:
    total += max(row)-min(row)

print total

# Part 2

total = 0

for row in input:
    for x in range(len(row)):
        for y in range(len(row)-1):
                if row[x] % row[(x + y + 1)%len(row)] == 0:
                    total += row[x] / row[(x + y + 1)%len(row)]

print total
