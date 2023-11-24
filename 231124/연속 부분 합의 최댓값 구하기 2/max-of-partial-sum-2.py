n = int(input())

num_list = list(map(int, input().split()))

maxi = 0
total = 0

for i in range(n):
    total += num_list[i]

    if total < 0:
        maxi = total
        total = 0
    
    elif maxi <= total:
        maxi = total

print(maxi)