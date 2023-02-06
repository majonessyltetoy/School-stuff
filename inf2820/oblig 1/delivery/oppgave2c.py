import re

L2 = r'^(a|bba)(((bb)?a){2})*(bb)?$'

def regex(reg, word):
    if re.search(reg, word):
        return "accept"
    else:
        return "reject"

print "\nValid words"
print "i:\t" + regex(L2, "a")
print "ii:\t" + regex(L2, "bba")
print "iii:\t" + regex(L2, "abbaa")
print "iv:\t" + regex(L2, "abbabbabbaaaa")
print "v:\t" + regex(L2, "bbaaa")

print "\nInvalid words"
print "i:\t" + regex(L2, "b")
print "ii:\t" + regex(L2, "aa")
print "iii:\t" + regex(L2, "ababa")
print "iv:\t" + regex(L2, "abbbaa")
print "v:\t" + regex(L2, "abbaabbaaa")