input = open('input').read().strip()

total = 0

for x in range(len(input)):
    if input[x] == input[(x+1)%len(input)]:
        total += int(input[x])

print total

# Part 2

total = 0

for x in range(len(input)):
    if input[x] == input[(x+(len(input)/2))%len(input)]:
        total += int(input[x])

print total
