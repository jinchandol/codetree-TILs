n = int(input())

arr1 = list(map(int, input().split()))
set1 = set(arr1)

m = int(input())
arr2 = list(map(int, input().split()))

for num in arr2:
    if num not in set1:
        print(0)
    else:
        print(1)