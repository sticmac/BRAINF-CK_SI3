#!/usr/bin/env ruby

#tests
def miaou(name = "Nyan")
	puts "Miaou #{name}!"
end
miaou
#end tests

memory = Array.new(30000, 0)
i = 0

memory[i] += 1 #incr
memory[i] -= 1 #decr
i += 1 #right
i -= 1 #left
puts memory[i] #out
#in
while memory[i] != 0 #jump
end #back

memory[i] += 70
print memory[i].chr #out
memory[i] += 1
print memory[i].chr #out

for i in (0...30000)
	if memory[i] != 0 then
    	puts memory[i]
	end
end
