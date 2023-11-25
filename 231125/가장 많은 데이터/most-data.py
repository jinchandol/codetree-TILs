n = int(input())

freq = dict()

for _ in range(n):
    color = input()

    if color in freq:
        freq[color] += 1
    else:
        freq[color] = 1

freq = dict(sorted(freq.items(), key=lambda x: -x[1]))

print(list(freq.values())[0])