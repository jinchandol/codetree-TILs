n = int(input())

def recur(num):
    if num == 1:
        return 0

    if num % 2 == 0:
        return recur(num//2) + 1
    else:
        return recur(3*num+1) + 1

print(recur(n))