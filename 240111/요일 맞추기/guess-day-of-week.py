day = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"]

num_of_days = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

def get_days(m, d):
    total_day = 0
    
    for i in range(1, m):
        total_day += num_of_days[i]

    total_day = total_day + d

    return total_day

m1, d1, m2, d2 = map(int, input().split())

day1 = get_days(m1, d1)
day2 = get_days(m2, d2)

if day2 >= day1:
    diff = (day2 - day1) % 7
    ans = day[diff]
    print(ans)
else:
    diff = (day1 - day2) % 7
    ans = day[7-diff]
    print(ans)