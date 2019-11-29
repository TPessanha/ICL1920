//5:int
let x=1 in
	let f = fun y -> y+x end in
		let g = fun x -> x+f(x) end in
			print(g(2))
		end
	end
end;;
