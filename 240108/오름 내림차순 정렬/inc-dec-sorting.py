n = int(input())
arr = list(map(int, input().split()))
arr.sort()

for i in range(n):
    print(arr[i], end = " ")

print()
arr.sort(reverse = True)

for i in range(n):
    print(arr[i], end = " ")