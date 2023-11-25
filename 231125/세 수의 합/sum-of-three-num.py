# n, k = map(int, input().split())
# num = list(map(int, input().split()))

# count = dict()

# for elem in num:
#     if elem in count:
#         count[elem] += 1
    
#     else:
#         count[elem] = 1

# ans = 0
# for i in range(n):
#     count[num[i]] -= 1

#     for j in range(i):
#         diff = k - num[i] - num[j]

#         if diff in count:
#             ans += count[diff]

# print(ans)

n,k = map(int,input().split())

num = list(map(int,input().split()))

count= {}

ans = 0

for i in range(n): 
    for j in range(i+1, n): 
        elem1 = num[i]
        elem2 = num[j]
        diff = k - elem1 - elem2
        
        if diff in count:
            ans += count[diff]
        
            
    if num[i] in count:
        count[num[i]] += 1 
    else : 
        count[num[i]] = 1 

print(ans)