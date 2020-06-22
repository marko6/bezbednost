import time
from datetime import datetime
import random

def getRandom(start, end):
    return int(random.uniform(start, end))

def now():
    return datetime.now().strftime("%d.%m.%Y %H:%M:%S")

logsData = [
    'LOG SISTEM UPDATE [VERBOSE][{0}]: System updating ....',
    'LOGIN ERROR LOG[ERROR][{0}]: WRONG PASSWORD',
    'LOGIN INFO[INFO][{0}]: USER TEST LOGED IN',
    'DATABASE UPDATE INFO [INFO][{0}]: Data read from server',
    'MILITARY APP1 LOG[ERROR][{0}]: NULL POINT EXCEPTION',
]

file = open('locations.config', 'r')
logFiles = []

for line in file.readlines():
    logFiles.append(line.strip())

file.close()

fileCount = len(logFiles)
logsCount = len(logsData)

print('simulator')

while(True):
    time.sleep(3)

    index = getRandom(0, fileCount)

    file = logFiles[index]
    index = getRandom(0, logsCount)

    log = logsData[index].format(now())

    f = open(file, 'a+')
    f.write(log + '\n')
    f.close()
    print(log)


