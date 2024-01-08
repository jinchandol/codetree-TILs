n = int(input())

arr1 = list(map(int, input().split()))
arr2 = list(map(int, input().split()))

arr1.sort()
arr2.sort()

is_same = True

for n1, n2 in zip(arr1, arr2):
    if n1 != n2:
        is_same = False
        break

if is_same:
    print("Yes")
else:
    print("No")