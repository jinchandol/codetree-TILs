n = int(input())

arr = list(map(int, input().split()))

arr.sort()

ans = -1

for i in range(2*n):
    ans = max(ans, arr[2*n-1-i]+arr[i])

print(ans)