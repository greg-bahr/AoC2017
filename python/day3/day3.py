from itertools import cycle

input = 289326

moves = cycle([lambda x, y: (x+1, y), lambda x, y: (x, y+1), lambda x, y: (x-1, y), lambda x, y: (x, y-1)])

coords = {(0, 0): 1}

def isNeighbor(orig, new):
    return abs(orig[0] - new[0]) <= 1 and abs(orig[1] - new[1]) <= 1

def spiral():
    curr_position = (0, 0)
    move_amt = 1
    total = 1
    while True:
        for x in range(2):
            move = next(moves)
            for y in range(move_amt):
                if total >= input:
                    return curr_position
                curr_position = move(*curr_position)
                total += 1
        move_amt += 1

def spiral2():
    curr_position = (0, 0)
    move_amt = 1
    total = 0
    while True:
        for x in range(2):
            move = next(moves)
            for y in range(move_amt):
                if total >= input:
                    return total
                curr_position = move(*curr_position)
                total = 0
                for coord, val in coords.iteritems():
                    if isNeighbor(coord, curr_position):
                        total += val
                coords[curr_position] = total
        move_amt += 1

if __name__ == "__main__":
    print sum(map(abs, spiral()))
    print spiral2()
