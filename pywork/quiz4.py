from random import *

comment = list(range(1,21))

ch = sample(comment, 1)
chicken = ch
comment.remove(ch[0])

co = sample(comment, 3)
coffee = co

print(f"-- 당첨자 발표 --\n치킨 당첨자 {chicken[0]}\n커피 당첨자 : {coffee}\n-- 축하합니다 --")



