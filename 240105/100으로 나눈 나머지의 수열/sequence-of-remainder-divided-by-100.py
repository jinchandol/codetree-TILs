# 수열의 값을 대상으로 하는 재귀함수
n = int(input())

# num => a_num 을 의미한다.
def recur(num):
    if num == 1:
        return 2
    if num == 2:
        return 4
    
    return (recur(num-1)*recur(num-2))%100

print(recur(n))