n = int(input())

def recur(num):
    if num == 1 or num == 2:
        return num
    
    return recur(int(num/3)) + recur(num-1)

ans = recur(n)

print(ans)