n, m = map(int, input().split())
seq = list(map(int, input().split()))
question = list(map(int, input().split()))

num_to_count = {}

for i in seq:
    if i in num_to_count:
        num_to_count[i] += 1
    else:
        num_to_count[i] = 1

for num in question:
    if num in num_to_count:
        print(num_to_count[num], end = " ")
    else:
        print(0, end = " ")