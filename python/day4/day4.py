from itertools import permutations

input = open('input').read().strip().split('\n')
input = [x.split() for x in input]

total = 0
out = False

def valid(phrase):
    for word in phrase:
        if phrase.count(word) > 1:
            return False
        for perm in set([''.join(x) for x in permutations(word)]):
            if perm != word and perm in phrase:
                return False
    return True

for x in input:
    if valid(x):
        total += 1

print total
