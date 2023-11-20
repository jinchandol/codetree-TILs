n = int(input())

num_list = list(map(int, input().split()))

maxi = 0 

for i in range(n):
    maxi += num_list[i]

    if maxi < 0:
        maxi = 0

print(maxi)