n = int(input())

def recur(num):
    if num == 0:
        return
    print(num, end = ' ')
    recur(num-1)
    print(num, end = ' ')

recur(n)