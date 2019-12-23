//5.5:float
let x:ref float =new 20.5 in
	let y:ref ref float=new x in
		y:= new 5.5; print(!!y)
	end
end;;
