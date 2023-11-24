n, m = map(int, input().split())

info = []

max_price = 0

for _ in range(n):
    w, v = map(int, input().split())
    info.append([w, v/w])

info = sorted(info, key = lambda x : -x[1])

for weight, per_price in info:
    if weight < m:
        max_price += weight*per_price
        m -= weight
    
    else:
        max_price += m*per_price
        break

print(f"{max_price:.3f}")