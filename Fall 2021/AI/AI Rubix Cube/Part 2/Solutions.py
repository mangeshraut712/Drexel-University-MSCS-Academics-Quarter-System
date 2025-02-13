import heapq
import queue
import sys
import time
from audioop import max
from collections import deque
from operator import abs

from Cube import TwoWayDict, Cube


class TreeNode:
    def __init__(self, data, count, move="", depth=0):
        self.data = data
        self.children = []
        self.parent = None
        self.move = move
        self.count = count
        self.depth = depth

    def add_child(self, child):
        child.parent = self
        self.children.append(child)


class Solutions:
    path = {}
    count = 0
    path_reconstructed = []
    moves_reconstructed = []
    inverse_moves = TwoWayDict()
    complement_moves = TwoWayDict()

    def __init__(self):
        self.inverse_moves["U"] = "U'"
        self.inverse_moves["R"] = "R'"
        self.inverse_moves["F"] = "F'"
        self.inverse_moves["D"] = "D'"
        self.inverse_moves["L"] = "L'"
        self.inverse_moves["B"] = "B'"

        self.complement_moves["U"] = "D'"
        self.complement_moves["U'"] = "D"
        self.complement_moves["L'"] = "R"
        self.complement_moves["L"] = "R'"
        self.complement_moves["F"] = "B'"
        self.complement_moves["F'"] = "B"

    def bfs(self, string):
        q = queue.Queue()
        current_move = ""
        number_of_moves = 0
        current = string
        initial = string
        root = TreeNode(current, self.count, current_move)
        current = TreeNode(current, self.count, current_move)

        while c.norm(current.data) != "WWWWRRRRGGGGYYYYOOOOBBBB":
            num, neighbours = self.find_neighbours(current, current.data, 0)
            number_of_moves += num
            self.make_path(current, neighbours)
            for n in neighbours:
                q.put(n)
            current = q.get()
        s, p = self.reconstruct_path(root, current)
        return [s, p, number_of_moves]

    def unique_hr(self, array, hr):
        while hr in array:
            hr += .001
        return hr

    def find_node(self, cur, node, neighbours):
        if cur.data == node.data and cur.move == node.move:
            for i in range(len(neighbours)):
                cur.add_child(neighbours[i])
            return
        else:
            for ch in cur.children:
                self.find_node(ch, node, neighbours)

    def make_path(self, current, neighbours):
        for n in neighbours:
            self.path[n.data + " " + str(n.count) + " " + n.move] = current.data + " " + str(
                current.count) + " " + current.move

    def reconstruct_path(self, initial, last):
        p = []
        s = []
        current = last.data + " " + str(last.count) + " " + last.move
        comp = last.data
        while comp != initial.data:
            t = current.split()
            p.append(t[2])
            s.append(t[0])
            current = self.path[current]
            t = current.split()
            comp = t[0]
        s.append(initial.data)
        p.reverse()
        s.reverse()
        return s, p


    def find_neighbours(self, cu, current, number_of_moves):
        neighbours_move = []
        s_move = ""
        move = cu.move
        self.count += 1
        for m in c.moves:
            # validating the moves
            if self.inverse_moves[m] == move:
                continue
            elif m == move:
                t = self.path[cu.data + " " + str(cu.count) + " " + cu.move].split()
                if len(t) < 3:
                    s_move = c.applyMove(m, current)
                    number_of_moves += 1
                    neighbours_move.append(TreeNode(s_move, self.count, m, cu.depth + 1))
                    self.count += 1
                    continue
                t = self.path[t[0] + " " + t[1] + " " + t[2]].split()
                if len(t) < 3:
                    s_move = c.applyMove(m, current)
                    number_of_moves += 1
                    neighbours_move.append(TreeNode(s_move, self.count, m, cu.depth + 1))
                    self.count += 1
                else:
                    previous = t[2]
                    if previous == m:
                        continue
                    else:
                        s_move = c.applyMove(m, current)
                        number_of_moves += 1
                        neighbours_move.append(TreeNode(s_move, self.count, m, cu.depth + 1))
                        self.count += 1
            elif self.complement_moves[m] == move:
                continue

            else:
                s_move = c.applyMove(m, current)
                number_of_moves += 1
                neighbours_move.append(TreeNode(s_move, self.count, m, cu.depth + 1))
                self.count += 1

        return number_of_moves, neighbours_move

    def ids(self, string):
        l = 100
        print(f"Depth: 0 d: 0")
        for i in range(1, l):
            result = (self.dfs(string, i))
            print(f"Depth: {i} d: {result[2]}")
            if len(result[0]) > 0:
                print(f"IDS found a solution a depth {i}")
                return result[0], result[1], result[2]
                break

    def max(self, num1, num2, num3, num4):
        return int((num1 + num2 + num3 + num4) / 4)

    def dfs(self, string, l):
        stack = deque()
        cur_move = ""
        number_of_moves = 0
        current = string
        initial = string
        root = TreeNode(current, self.count, cur_move, 0)
        current = TreeNode(current, self.count, cur_move, 0)
        generate = -1

        while c.norm(current.data) != "WWWWRRRRGGGGYYYYOOOOBBBB":
            if current.depth > l:
                return [[], [], number_of_moves]
            if current.depth + 1 <= l:
                num, neighbours = self.find_neighbours(current, current.data, 0)
                number_of_moves += num
                self.make_path(current, neighbours)
                generate = 0
            if generate != -1:
                for n in neighbours:
                    stack.append(n)
            generate = -1
            if len(stack) > 0:
                current = stack.pop()
            else:
                return [[], [], number_of_moves]
        s, p = self.reconstruct_path(root, current)
        return [s, p, number_of_moves]

c = Cube()
sol = Solutions()
