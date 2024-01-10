day = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"]

num_of_days = [0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

def get_days(m, d):
    total_day = 0
    
    for i in range(1, m):
        total_day += num_of_days[i]

    total_day = total_day + d

    return total_day

m1, d1, m2, d2 = map(int, input().split())
A_day = input()

target = day.index(A_day)

day1 = get_days(m1, d1)
day2 = get_days(m2, d2)


diff_week = (day2 - day1) // 7
diff_day = (day2 - day1) % 7

if diff_day <= target:
    diff_week += 1

print(diff_week)