n = int(input())

def recur(num):
    if num == 0:
        return
    for _ in range(num):
        print("*", end = ' ')
    print()
    recur(num-1)
    for _ in range(num):
        print("*", end = ' ')
    print()

recur(n)