from gi.overrides.keysyms import percent
skills1 = dict()
skills2 = dict()
skills3 = dict()
f = open('/home/manikanta/Desktop/Resume Extractor/skills.txt')
line = f.readline()
for line in f:
    line = line.strip()
    if(' ' in line):
        words = line.split()
        if(words[0] not in skills2):
            skills2.update({words[0] : 0})
            skills3.update({words[1] : 0})
    else:
        if(line not in skills1):         
            skills1.update({line : 0})
f.close()
# for x,y in skills.items():
#     print x,y
resume1 = dict()
resume2 = dict()
f = open('/home/manikanta/Desktop/Resume Extractor/sample_resumes/akasha_resume.txt')
line = f.readline()
for line in f:
    line = line.strip()
    words =  line.split()
    for i in range(len(words)):
        if(words[i] in skills1):
            if(words[i] not in resume1):
                resume1.update({words[i] :1})
        else:
            if(words[i] in skills2):
                if((i+1)<(len(words))):
                    if(words[i+1] in skills3):
                        temp = words[i]+words[i+1]
                        if(temp not in resume1):
                            resume1.update({temp : 1})
    
f.close()
# print resume1

f = open('/home/manikanta/Desktop/Resume Extractor/sample_resumes/ostarion_resume.txt')
line = f.readline()
for line in f:
    line = line.strip()
    words =  line.split()
    for i in range(len(words)):
        if(words[i] in skills1):
            if(words[i] not in resume2):
                resume2.update({words[i] :1})
        else:
            if(words[i] in skills2):
                if((i+1)<(len(words))):
                    if(words[i+1] in skills3):
                        temp = words[i]+words[i+1]
                        if(temp not in resume2):
                            resume2.update({temp : 1})
    
f.close()

print resume1
print resume2

common = 0
if(len(resume1)<len(resume2)):
    for key,value in resume1.items():
        if(key in resume2):
            common+=1
if(len(resume1)>=len(resume2)):
    for key,value in resume2.items():
        if(key in resume1):
            common+=1
            
percent = common+0.0
percent = percent/(len(resume1)+len(resume2))
print percent