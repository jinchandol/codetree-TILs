# n = int(input())

# s = set()

# def do_it(p, q):
#     global s
#     if p == 'find':
#         if q in s:
#             print("true")
#         else:
#             print("false")

#     elif p == 'add':
#         s.add(q)
    
#     elif p == 'remove':
#         s.remove(q)

# for _ in range(n):
#     action, num = input().split()
#     do_it(action, num)


# 변수 선언 및 입력:
# 큰 차이는 없지만 아래와 같이 코드를 적용해도 괜찮음.
n = int(input())
s = set()

for _ in range(n):
    command, x = tuple(input().split())
    x = int(x)

    if command == "add":
        s.add(x)
    elif command == "remove":
        s.remove(x)
    else:
        if x in s:
            print("true")
        else:
            print("false")