n = int(input())

cnt = 0

def recur(num):
    global cnt
    if num == 1:
        return

    cnt += 1

    if num % 2 == 0:
        return recur(num//2)
    else:
        return recur(num//3)

recur(n)

print(cnt)