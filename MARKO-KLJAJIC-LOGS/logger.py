import re
import string
import time
from datetime import datetime
import requests
import http.client
import json

file = open('locations.config')
files = []

for line in file.readlines():
    files.append(line.strip())


lastTime = None

while True:
    time.sleep(5)

    for file in files:

        f = open(file, 'r')

        for data in f.readlines():

            mach = re.search(r'\d{2}.\d{2}.\d{4} \d{2}:\d{2}:\d{2}', data)

            if mach == None or 'ERROR' not in data.upper():
                continue

            date = datetime.strptime(mach.group(), '%d.%m.%Y %H:%M:%S')

            if lastTime != None and lastTime < date:
                continue

            print(data.strip())
            lastTime = datetime.now()

            requestData = {
                'date': mach.group(),
                'agent': 'PC1',
                'alarm': data
            }

            connection = http.client.HTTPConnection("localhost:8083")
            connection.request('POST', '/api/log', json.dumps(requestData), {'Content-type': 'application/json'})
            response = connection.getresponse()

            print(response.read().decode())

        f.close()
