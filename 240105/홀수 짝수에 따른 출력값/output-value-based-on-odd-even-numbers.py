n = int(input())

def recur(num):
    if num == 1 or num == 0:
        return num
    
    return recur(num-2) + num

ans = recur(n)

print(ans)