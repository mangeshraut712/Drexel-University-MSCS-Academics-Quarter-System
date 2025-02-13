#!/usr/bin/env python
import random
import sys
import time

# different moves
# https://ruwix.com/online-puzzle-simulators/2x2x2-pocket-cube-simulator.php

MOVES = {
    "U": [2, 0, 3, 1, 20, 21, 6, 7, 4, 5, 10, 11, 12, 13, 14, 15, 8, 9, 18, 19, 16, 17, 22, 23],
    "U'": [1, 3, 0, 2, 8, 9, 6, 7, 16, 17, 10, 11, 12, 13, 14, 15, 20, 21, 18, 19, 4, 5, 22, 23],
    "R": [0, 9, 2, 11, 6, 4, 7, 5, 8, 13, 10, 15, 12, 22, 14, 20, 16, 17, 18, 19, 3, 21, 1, 23],
    "R'": [0, 22, 2, 20, 5, 7, 4, 6, 8, 1, 10, 3, 12, 9, 14, 11, 16, 17, 18, 19, 15, 21, 13, 23],
    "F": [0, 1, 19, 17, 2, 5, 3, 7, 10, 8, 11, 9, 6, 4, 14, 15, 16, 12, 18, 13, 20, 21, 22, 23],
    "F'": [0, 1, 4, 6, 13, 5, 12, 7, 9, 11, 8, 10, 17, 19, 14, 15, 16, 3, 18, 2, 20, 21, 22, 23],
    "D": [0, 1, 2, 3, 4, 5, 10, 11, 8, 9, 18, 19, 14, 12, 15, 13, 16, 17, 22, 23, 20, 21, 6, 7],
    "D'": [0, 1, 2, 3, 4, 5, 22, 23, 8, 9, 6, 7, 13, 15, 12, 14, 16, 17, 10, 11, 20, 21, 18, 19],
    "L": [23, 1, 21, 3, 4, 5, 6, 7, 0, 9, 2, 11, 8, 13, 10, 15, 18, 16, 19, 17, 20, 14, 22, 12],
    "L'": [8, 1, 10, 3, 4, 5, 6, 7, 12, 9, 14, 11, 23, 13, 21, 15, 17, 19, 16, 18, 20, 2, 22, 0],
    "B": [5, 7, 2, 3, 4, 15, 6, 14, 8, 9, 10, 11, 12, 13, 16, 18, 1, 17, 0, 19, 22, 20, 23, 21],
    "B'": [18, 16, 2, 3, 4, 0, 6, 1, 8, 9, 10, 11, 12, 13, 7, 5, 14, 17, 15, 19, 21, 23, 20, 22],
}

'''
sticker indices:

      0  1
      2  3
16 17  8  9   4  5  20 21
18 19  10 11  6  7  22 23
      12 13
      14 15

face colors:

    0
  4 2 1 5
    3

moves:
[ U , U', R , R', F , F', D , D', L , L', B , B']
'''

class TwoWayDict(dict):

    def __len__(self):

        return dict.__len__(self) / 2

    def __setitem__(self, key, value):
        dict.__setitem__(self, key, value)
        dict.__setitem__(self, value, key)


class cube:
    moves = []
    reference_corner = TwoWayDict()
    normalized_position = {}
    ormalized_position_opposite = {}

    def __init__(self, string="WWWW RRRR GGGG YYYY OOOO BBBB"):
        self.moves = ["U", "U'", "R", "R'", "F", "F'",
                      "D", "D'", "L", "L'", "B", "B'"]
        # normalize stickers relative to a fixed corner
        self.reference_corner['G'] = 'B'
        self.reference_corner['Y'] = 'W'
        self.reference_corner['O'] = 'R'

        self.normalized_position = {
            10: 'G',
            12: 'Y',
            19: 'O',
        }
        self.normalized_position_opposite = {
            10: 'B',
            12: 'W',
            19: 'R',
        }

        # apply a move to a state
    def applyMove(self, move, string):
        newString = ""
        shift = MOVES[move]
        for i in range(0, len(shift)):
            index = shift[i]
            newString += string[index]

        return newString

        # apply a string sequence of moves to a state

    def applyMovesStr(self, alg, string="WWWW RRRR GGGG YYYY OOOO BBBB"):
        string = string.replace(" ", "")
        if len(string) != 24:
            return "Invalid Input! Enter a valid string for the cube"
        alg = alg.split()
        for i in range(0, len(alg)):
            string = self.applyMove(alg[i], string)
        return string

        # check if state is solved
    def isSolved(self, cube):
        cube = cube.replace(" ", "")
        if len(cube) != 24:
            return "Invalid Input! Enter a valid string for the cube"
        return cube == "WWWWRRRRGGGGYYYYOOOOBBBB"

        # print state of the cube
    def print(self, cubeflattened="WWWW RRRR GGGG YYYY OOOO BBBB"):
        stringCube = cubeflattened.replace(" ", "")
        if len(stringCube) != 24:
            return "Invalid ! Enter a valid string for the cube"
        stringCube = self.Swap(stringCube)
        index = 0
        flatRepresentationOfCube = []
        for i in range(0, 6):
            row = []
            for j in range(0, 11):
                if j == 3 or j == 4:
                    row.append(stringCube[index])
                    index += 1
                elif (i == 2 or i == 3) and j != 2 and j != 5 and j != 8:
                    row.append(stringCube[index])
                    index += 1
                else:
                    row.append(" ")
            flatRepresentationOfCube.append(row)

        return self.displayCube(flatRepresentationOfCube)

    def generate_random_moves(self, n):
        random_moves = []
        for i in range(0, n):
            random_moves.append(
                self.moves[random.randint(0, len(self.moves)-1)])
        return random_moves

    def random_walk(self, walk, n, string="WWWW RRRR GGGG YYYY OOOO BBBB"):
        number_of_moves = 0
        string = string.replace(" ", "")
        walk = walk.split()
        for i in range(0, len(walk)):
            string = self.applyMove(walk[i], string)
        random_moves = []
        initial = string
        result = ""
        while self.norm(result) != "WWWWRRRRGGGGYYYYOOOOBBBB":
            result = ""
            walk_array = [initial]
            random_moves = self.generate_random_moves(n)
            string = initial
            for i in range(0, n):
                string = self.applyMove(random_moves[i], string)
                walk_array.append(string)
                number_of_moves += 1
            result = string
        return [walk_array, random_moves, number_of_moves]

        # normalize the cube
    def norm(self, string="WWWW RRRR GGGG YYYY OOOO BBBB"):
        string = string.replace(" ", "")
        if len(string) != 24:
            return "Invalid Input! Enter a valid string for the cube"
        current_corner = {
            10: string[10],
            12: string[12],
            19: string[19],
        }
        current_opposite_corner = {
            10: self.reference_corner[current_corner[10]],
            12: self.reference_corner[current_corner[12]],
            19: self.reference_corner[current_corner[19]],
        }

        mapping = {
            current_corner[10]: self.normalized_position[10],
            current_corner[12]: self.normalized_position[12],
            current_corner[19]: self.normalized_position[19],

            # opposite
            current_opposite_corner[10]: self.normalized_position_opposite[10],
            current_opposite_corner[12]: self.normalized_position_opposite[12],
            current_opposite_corner[19]: self.normalized_position_opposite[19],

        }
        newString = ""

        for i in range(len(string)):
            newString += mapping[string[i]]
        return newString

    def Swap(self, string):
        n = 2
        string = [string[i:i + n] for i in range(0, len(string), n)]
        # first swap
        temp = string[2]
        string[2] = string[4]
        string[4] = temp
        temp = string[3]
        string[3] = string[8]
        string[8] = temp
        # second swap
        temp = string[2]
        string[2] = string[3]
        string[3] = temp
        temp = string[5]
        string[5] = string[7]
        string[7] = temp
        #     third swap
        temp = string[5]
        string[5] = string[11]
        string[11] = temp
        temp = string[6]
        string[6] = string[10]
        string[10] = temp
        #     last swap
        temp = string[6]
        string[6] = string[9]
        string[9] = temp
        temp = string[5]
        string[5] = string[9]
        string[9] = temp

        return "".join(string)

    def displayCube(self, string):
        str = ""
        for i in range(0, len(string)):
            for j in range(0, len(string[0])):
                str += string[i][j]
            str += '\n'
        return str

    def shuffle(self, n, string="WWWW RRRR GGGG YYYY OOOO BBBB"):
        string = string.replace(" ", "")
        random_moves = []
        for i in range(0, n):
            random_moves.append(
                self.moves[random.randint(0, len(self.moves)-1)])
        for i in range(0, len(random_moves)):
            string = self.applyMove(random_moves[i], string)

        return[' '.join(random_moves), string]

    def print_multiple_strings(self, array):
        newString = ""
        if len(array) == 1:
            return self.print(array[0])
        elif len(array) == 2:
            string1 = self.print(array[0])
            string2 = self.print(array[1])
            string1 = string1.split("\n")
            string2 = string2.split("\n")
            for i in range(0, len(string1)):
                newString += string1[i]+"  "+string2[i]+"\n"
        else:
            string1 = self.print(array[0])
            string2 = self.print(array[1])
            string3 = self.print(array[2])
            string1 = string1.split("\n")
            string2 = string2.split("\n")
            string3 = string3.split("\n")
            newString = ""
            for i in range(0, len(string1)):
                newString += string1[i]+"  "+string2[i]+"  "+string2[i]+"\n"
        return newString


c = cube()
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
elif sys.argv[1] == 'shuffle':
    print()
    string = c.shuffle(int(sys.argv[2]))
    print(string[0])
    print()
    print(c.print(string[1]))
elif sys.argv[1] == 'random':
    startTime = time.time()
    string = ""
    result = c.random_walk(sys.argv[2], int(sys.argv[3]))
    print(" ".join(result[1]))
    divided_array = c.divide_in_three(result[0])
    for i in range(0, len(divided_array)):
        print(c.print_multiple_strings(divided_array[i]))
    executionTime = (time.time() - startTime)
    print(result[2])
    print(executionTime)
else:
    print("Invalid Option")
