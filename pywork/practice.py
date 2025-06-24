# score_file = open("score.txt", "a", encoding="utf8")
# print("수학 : 110", file=score_file)
# print("영어 : 5011",file=score_file)
# score_file.close()

# score_file = open("score.txt","r",encoding="utf8")
# print(score_file.readline())
# print(score_file.readline())
# score_file.close()

# import pickle

# # profile_file = open("profile.txt","wb")
# # profile = {"aa":10, "ssdsd":"sssds"}
# # pickle.dump(profile, profile_file)
# # profile_file.close()

# # profile_file = open("profile.txt","rb")
# # profile = pickle.load(profile_file)
# # print(profile)
# # profile_file.close()

# with open("score.txt","r",encoding="utf8") as f:
#     print(f.readlines())

# import theater_module

# theater_module.price(3)
# theater_module.price_morning(3)

# from travel import *

# trip_to = thailand.ThailandPackage()
# # trip_to = vietnam.VietnamPackage()
# trip_to.detail()

import inspect
import random
from travel import *
print(inspect.getfile(random))
print(inspect.getfile(thailand))