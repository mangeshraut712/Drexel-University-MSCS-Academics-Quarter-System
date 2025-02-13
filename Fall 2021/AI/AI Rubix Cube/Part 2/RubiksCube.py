#!/usr/bin/env python
import random
import sys
import time

# different moves
# https://ruwix.com/online-puzzle-simulators/2x2x2-pocket-cube-simulator.php
from Cube import Cube
from Solutions import Solutions

c = Cube()
sol = Solutions()
if sys.argv[1] == 'print':
    if len(sys.argv) <= 2:
        print()
        print(c.print())
    else:
        print()
        print(c.print(sys.argv[2]))

elif sys.argv[1] == 'goal':
    print(c.isSolved(sys.argv[2]))

elif sys.argv[1] == 'applyMovesStr':
    if len(sys.argv) <= 3:
        string = c.applyMovesStr(sys.argv[2])
    else:
        string = c.applyMovesStr(sys.argv[2], sys.argv[3])
    print()
    print(c.print(string))
elif sys.argv[1] == 'norm':
    print()
    string = c.norm(sys.argv[2])
    print(c.print(string))
    string = c.shuffle(int(sys.argv[2]))
    print(string[0])
    print()
    print(c.print(string[1]))
elif sys.argv[1] == 'random':
    startTime = time.time()
    result = c.random_walk(sys.argv[2], int(sys.argv[3]))
    print(" ".join(result[1]))
    divided_array = c.divide_in_three(result[0])
    for i in range(0, len(divided_array)):
        print(c.print_multiple_strings(divided_array[i]))
    executionTime = (time.time() - startTime)
    print(result[2])
    print(round(executionTime, 3))
elif sys.argv[1] == 'bfs':
    startTime = time.time()
    string = c.applyMovesStr(sys.argv[2])
    r = sol.bfs(string)
    print(" ".join(r[1]))
    divided_array = c.divide_in_three(r[0])
    for i in range(0, len(divided_array)):
        print(c.print_multiple_strings(divided_array[i]))
    executionTime = (time.time() - startTime)
    print(r[2])
    print(round(executionTime, 3))
elif sys.argv[1] == 'ids':
    startTime = time.time()
    string = c.applyMovesStr(sys.argv[2])
    r = sol.ids(string)
    print(" ".join(r[1]))
    divided_array = c.divide_in_three(r[0])
    for i in range(0, len(divided_array)):
        print(c.print_multiple_strings(divided_array[i]))
    executionTime = (time.time() - startTime)
    print(r[2])
    print(round(executionTime, 3))
elif sys.argv[1] == 'astar':
    start = time.time()
    string = c.applyMovesStr(sys.argv[2])
    r = sol.astar(string)
    print(" ".join(r[1]))
    divided_array = c.divide_in_three(r[0])
    for i in range(0, len(divided_array)):
        print(c.print_multiple_strings(divided_array[i]))
    executionTime = (time.time() - start)
    print(r[2])
    print(round(executionTime, 3))
else:
    print("Invalid command! Try again.")
