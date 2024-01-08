n = int(input())
arr = []

for _ in range(n):
    arr.append(input())

arr.sort()

for string in arr:
    print(string)