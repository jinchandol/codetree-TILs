n, k, T = input().split()
n, k = int(n), int(k)

arr = []

for _ in range(n):
    str1 = input()
    if str1.startswith(T):
        arr.append(str1)

arr.sort()

print(arr[k-1])