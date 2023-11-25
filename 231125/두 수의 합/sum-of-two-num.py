n, k = map(int, input().split())
num = list(map(int, input().split()))

s = dict()

ans = 0

for elem in num:
    diff = k - elem
    
    if diff in s:
        ans += s[diff]

    if elem in s:
        s[elem] += 1
    
    else:
        s[elem] = 1
    
print(ans)