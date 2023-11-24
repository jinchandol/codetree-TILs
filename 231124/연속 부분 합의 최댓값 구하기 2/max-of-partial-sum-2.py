import sys

n = int(input())

num_list = list(map(int, input().split()))

maxi = -sys.maxsize
total = 0

for i in range(n):
    total += num_list[i]

    if total < 0:
        if maxi < total:
            maxi = total
        
        total = 0
    
    elif maxi <= total:
        maxi = total

print(maxi)