numbers = list(map(int, input().split()))

num = 1

for i in numbers:
    num *= i

def recur(n):
    if n < 10:
        return n
    
    return recur(n//10) + n%10

ans = recur(num)

print(ans)