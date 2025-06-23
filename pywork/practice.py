# score_file = open("score.txt", "a", encoding="utf8")
# print("수학 : 110", file=score_file)
# print("영어 : 5011",file=score_file)
# score_file.close()

# score_file = open("score.txt","r",encoding="utf8")
# print(score_file.readline())
# print(score_file.readline())
# score_file.close()

import pickle

# profile_file = open("profile.txt","wb")
# profile = {"aa":10, "ssdsd":"sssds"}
# pickle.dump(profile, profile_file)
# profile_file.close()

# profile_file = open("profile.txt","rb")
# profile = pickle.load(profile_file)
# print(profile)
# profile_file.close()

with open("score.txt","r",encoding="utf8") as f:
    print(f.readlines())