import os
import sys
from shutil import copy
arg = sys.argv
os.mkdir(arg[1])
print("Created directory: "+arg[1])
cur = 0
n = int(arg[2])
s = ""
for i in range(0,n):
    copy("template/"+(str(chr(cur+65)))+".java",str(arg[1])+"/"+(str(chr(cur+65)))+".java")
    s = s + (str(chr(cur+65)))+".java "
    cur+=1
print("Created files: " + s)
print("Opening files...")
os.chdir(arg[1])
os.system('code '+s)