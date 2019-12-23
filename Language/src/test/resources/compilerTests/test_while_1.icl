//2:int,1:int
let x:ref int= new 2 in
	while !x > 0 do
		print(!x);
		x:=!x-1
	end
end;;
