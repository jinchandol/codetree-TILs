num_of_days = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

m1, d1, m2, d2 = map(int, input().split())

ans = sum(num_of_days[m1:m2]) - d1 + d2 + 1

print(ans)