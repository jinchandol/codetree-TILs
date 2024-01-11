n, b = map(int, input().split())

digit_list = []

def trans(num, digit):
    while True:
        if num < digit:
            digit_list.append(num)
            break
        
        digit_list.append(num % digit)
        num = num // digit

trans(n, b)

for i in digit_list[::-1]:
    print(i, end="")