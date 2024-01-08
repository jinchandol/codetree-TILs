n = int(input())

arr = [0] + list(map(int, input().split()))

for i in range(1, n+1):
    if i % 2 != 0:
        test = arr[1:i+1]
        test.sort()
        print(test[int((i-1)/2)], end = " ")