n = int(input())

arr1 = tuple(map(int, input().split()))
set1 = set(arr1)

m = int(input())

arr2 = tuple(map(int, input().split()))

for num in arr2:
    if num in set1:
        print(1, end=' ')
    else:
        print(0, end=' ')