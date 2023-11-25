n, k = map(int, input().split())

num = list(map(int, input().split()))

count = dict()

for elem in num:
    if elem in count:
        count[elem] += 1
    
    else:
        count[elem] = 1

count = dict(sorted(count.items(), key = lambda x : (-x[1], -x[0])))

ans = list(count.keys())[:k]

for elem2 in ans:
    print(elem2, end = " ")