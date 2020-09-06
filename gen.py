import os
import sys
from shutil import copy
arg = sys.argv
os.mkdir(arg[1])
os.chdir(arg[1])
cur = 'a'