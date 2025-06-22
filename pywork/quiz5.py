from random import *

count = 0

for i in range(1,51):
    time = randint(5,50)

    can = ""

    if(5 <= time <= 15):
        can = "o"
        count += 1
    
    print(f"[{can}] {i}번째 손님 (소요시간 : {time}분)")

print(f"총 탑승 승객 : {count}분")
