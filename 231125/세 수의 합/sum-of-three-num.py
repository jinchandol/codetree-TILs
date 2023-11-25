n, k = map(int, input().split())
num = list(map(int, input().split()))

count = dict()

for elem in num:
    if elem in count:
        count[elem] += 1
    
    else:
        count[elem] = 1

ans = 0
for i in range(n):
    count[num[i]] -= 1

    for j in range(i):
        diff = k - num[i] - num[j]

        if diff in count:
            ans += count[diff]

print(ans)